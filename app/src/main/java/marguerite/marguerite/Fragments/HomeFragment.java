package marguerite.marguerite.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

import marguerite.marguerite.Adapters.RestaurantsAdapter;
import marguerite.marguerite.Classes.RestaurantClass;
import marguerite.marguerite.R;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    ListView list;
    RestaurantsAdapter restaurantsAdapter;


    String nom;
    String adresse;



    private FirebaseFirestore firestore;



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private  ArrayList<RestaurantClass> title;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final String[] temp = new String[1];
        View view;
        view=inflater.inflate(R.layout.fragment_home, container, false);

        list=(ListView)view.findViewById(R.id.list);

        title =new ArrayList<>();


        firestore=FirebaseFirestore.getInstance();


        firestore.collection("Restaurants")
        .get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());


                        Map<String,Object> horaires=(Map<String,Object>) document.getData().get("horaires");
                        title.add(new RestaurantClass((document.getData().get("nom")).toString(),(document.getData().get("adresse")).toString(),horaires));
                        temp[0] =title.get(0).getNom().toString();
                        restaurantsAdapter =new RestaurantsAdapter(getActivity(),title);
                        list.setAdapter(restaurantsAdapter);

                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            public void onItemClick(AdapterView<?> parent, View view,
                                                    int position, long id) {
                                FragmentTransaction trans = getFragmentManager()
                                        .beginTransaction();
                                /*
                                 * IMPORTANT: We use the "root frame" defined in
                                 * "root_fragment.xml" as the reference to replace fragment
                                 */
                                trans.replace(R.id.root_home_fragment, new RestaurantMenuFragment());

                                /*
                                 * IMPORTANT: The following lines allow us to add the fragment
                                 * to the stack and return to it later, by pressing back
                                 */
                                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                                trans.addToBackStack(null);

                                trans.commit();
                            }
                            }
                            );
                        int i=0;

                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });




        /*
        firestore.collection("Restaurants").document("8tWygSWs1VATB4O4fSLk").get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        nom=documentSnapshot.getString("nom");
                        adresse=documentSnapshot.getString("adresse");



                        String[] maintitle ={
                                nom,"Restaurant",
                                "Restaurant","Restaurant",
                                "Restaurant",
                        };


                        String[] subtitle ={
                                adresse,"Nom, adresse restaurant",
                                "Nom, adresse restaurant","Nom, adresse restaurant",
                                "Nom, adresse restaurant",
                        };

                        MyListAdapter adapter=new MyListAdapter(getActivity(), maintitle, subtitle);
                        list.setAdapter(adapter);

                    }
                });


*/





        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteractionHome(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteractionHome(Uri uri);
    }
}
