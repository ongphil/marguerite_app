package marguerite.marguerite;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ToOrder.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ToOrder#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToOrder extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;



    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;

    public ToOrder() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static ToOrder newInstance(String param1, String param2) {
        ToOrder fragment = new ToOrder();
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

        View view=inflater.inflate(R.layout.fragment_to_order, container, false);

        expandableListView = (ExpandableListView)view.findViewById(R.id.expandableListView);

        FirebaseFirestore firestore;
        firestore=FirebaseFirestore.getInstance();

        final HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        final ArrayList<Category> category;
        category=new ArrayList<>();

        final List<String> boisson = new ArrayList<String>();
        final List<String> entree = new ArrayList<String>();
        final List<String> plat = new ArrayList<String>();
        final List<String> dessert = new ArrayList<String>();


        firestore.collection("Restaurants").document("8tWygSWs1VATB4O4fSLk").collection("Carte").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                String nom=document.getData().get("categorie").toString();
                                switch(nom)
                                {
                                    case "Entrée":
                                        entree.add(document.getData().get("nom").toString());
                                        break;

                                    case "Boisson":
                                        boisson.add(document.getData().get("nom").toString());
                                        break;

                                    case "Plat":
                                        plat.add(document.getData().get("nom").toString());
                                        break;

                                    case "Dessert":
                                        dessert.add(document.getData().get("nom").toString());
                                        break;
                                    default:
                                        break;

                                }
                                expandableListDetail.put("BOISSONS", boisson);
                                expandableListDetail.put("ENTREES", entree);
                                expandableListDetail.put("PLATS", plat);
                                expandableListDetail.put("DESSERTS", dessert);

                            }


                            expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
                            expandableListAdapter = new ToOrderAdapter(getActivity(), expandableListTitle, expandableListDetail);
                            expandableListView.setAdapter(expandableListAdapter);

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });





        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
        void onFragmentInteraction(Uri uri);
    }
}
