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
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import marguerite.marguerite.Adapters.MyOrdersAdapter;
import marguerite.marguerite.Classes.OrderClass;
import marguerite.marguerite.R;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyOrdersFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyOrdersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyOrdersFragment extends Fragment {
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
    private HashMap<String, List<OrderClass>> expandableListDetail;

    private String cast_creneau_param;
    private String numero_commande_param;




    public MyOrdersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyCardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyOrdersFragment newInstance(String param1, String param2) {
        MyOrdersFragment fragment = new MyOrdersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    // TODO: Rename and change types and number of parameters


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

        View view=inflater.inflate(R.layout.fragment_my_orders, container, false);

        expandableListView = (ExpandableListView)view.findViewById(R.id.expandable_status);

        FirebaseFirestore firestore;
        firestore=FirebaseFirestore.getInstance();

        final HashMap<String, List<OrderClass>> expandableListDetail = new HashMap<String, List<OrderClass>>();


        final ArrayList<OrderClass> orders = new ArrayList<>();

        final List<OrderClass> prete = new ArrayList<>();
        final List<OrderClass> en_prepa = new ArrayList<>();
        final List<OrderClass> terminee = new ArrayList<>();

        firestore.collection("Commandes").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                               String statut=document.getData().get("statut").toString();

                               //Cast
                               int cast_creneau;
                               cast_creneau=Integer.parseInt(document.getData().get("creneau_attente").toString());

                               double cast_prix_total;
                               cast_prix_total=Double.parseDouble(document.getData().get("prix_total").toString());

                                Map<String,Object> date=(Map<String,Object>) document.getData().get("date");

                                //Ajout des commandes selon son statut; prête, en prépa ou terminée
                               switch(statut)
                               {
                                   case "Prete":
                                       prete.add(new OrderClass((document.getData().get("commentaire")).toString(),(document.getData().get("statut")).toString(),
                                               cast_creneau,cast_prix_total,(document.getDocumentReference("restaurant_id")),
                                               (document.getDocumentReference("utilisateur_id")),date,(document.getData().get("numéro_commande")).toString()));
                                       break;

                                   case "En preparation":
                                       en_prepa.add(new OrderClass((document.getData().get("commentaire")).toString(),(document.getData().get("statut")).toString(),
                                               cast_creneau,cast_prix_total,(document.getDocumentReference("restaurant_id")),
                                               (document.getDocumentReference("utilisateur_id")),date,(document.getData().get("numéro_commande")).toString()));
                                       break;
                                   case "Terminee":
                                       terminee.add(new OrderClass((document.getData().get("commentaire")).toString(),(document.getData().get("statut")).toString(),
                                               cast_creneau,cast_prix_total,(document.getDocumentReference("restaurant_id")),
                                               (document.getDocumentReference("utilisateur_id")),date,(document.getData().get("numéro_commande")).toString()));
                                       break;
                                   default:
                                       break;

                               }

                               //Ajout dans l'expandableListView
                                expandableListDetail.put("En préparation", en_prepa);
                                expandableListDetail.put("Prêtes", prete);
                                expandableListDetail.put("Terminées", terminee);
                                cast_creneau_param=document.getData().get("creneau_attente").toString();
                                numero_commande_param=document.getData().get("numéro_commande").toString();




                            }


                            //Adapteur dans le layout
                            expandableListTitle = new ArrayList<String>();
                            //expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
                            expandableListTitle.add("Prêtes");
                            expandableListTitle.add("En préparation");
                            expandableListTitle.add("Terminées");
                            //java.util.Collections.sort(expandableListTitle);
                            expandableListAdapter = new MyOrdersAdapter(getActivity(), expandableListTitle, expandableListDetail);
                            expandableListView.setAdapter(expandableListAdapter);



                            expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                                @Override
                                public boolean onChildClick(ExpandableListView parent, View view, int groupPosition, int childPosition, long id) {

                                    FragmentTransaction trans = getFragmentManager().beginTransaction();

                                    trans.replace(R.id.root_orders_fragment, OrderIsReadyFragment.newInstance(groupPosition,
                                            cast_creneau_param,numero_commande_param));


                                    trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                                    trans.addToBackStack(null);

                                    trans.commit();

                                    return true;

                                }
                            });





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