package marguerite.marguerite.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import marguerite.marguerite.Adapters.MenuItemsAdapter;
import marguerite.marguerite.Classes.MenuItemClass;
import marguerite.marguerite.R;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RestaurantMenuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RestaurantMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RestaurantMenuFragment extends Fragment {
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
    private HashMap<String, List<MenuItemClass>> hashMap_categorie_menu;
    private ArrayList<MenuItemClass> mon_panier;

    private Button button_panier;

    public RestaurantMenuFragment() {
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
    public static RestaurantMenuFragment newInstance(String param1, String param2) {
        RestaurantMenuFragment fragment = new RestaurantMenuFragment();
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

        View view=inflater.inflate(R.layout.fragment_restaurant_menu, container, false);

        expandableListView = (ExpandableListView)view.findViewById(R.id.expandableListView);
        button_panier = (Button) view.findViewById(R.id.button_panier);


        FirebaseFirestore firestore;
        firestore=FirebaseFirestore.getInstance();

        final HashMap<String, List<MenuItemClass>> hashMap_categorie_menu = new HashMap<String, List<MenuItemClass>>();


        final ArrayList<MenuItemClass> menu = new ArrayList<>();

        final List<MenuItemClass> boisson = new ArrayList<MenuItemClass>();
        final List<MenuItemClass> entree = new ArrayList<MenuItemClass>();
        final List<MenuItemClass> plat = new ArrayList<MenuItemClass>();
        final List<MenuItemClass> dessert = new ArrayList<MenuItemClass>();
        mon_panier= new ArrayList<MenuItemClass>();

        firestore.collection("Restaurants").document("8tWygSWs1VATB4O4fSLk").collection("Carte").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                String nom=document.getData().get("categorie").toString();
                                Double prix_unitaire_cast;
                                prix_unitaire_cast= Double.parseDouble(document.getData().get("prix_unitaire").toString());
                                boolean temp =true;
                                switch(nom)
                                {
                                    case "Entrée":
                                        entree.add(new MenuItemClass((document.getData().get("nom")).toString(),(document.getData().get("categorie")).toString(),
                                                (document.getData().get("description")).toString(),(document.getData().get("disponibilite")).toString(),
                                                prix_unitaire_cast));
                                        break;

                                    case "Boisson":
                                        boisson.add(new MenuItemClass((document.getData().get("nom")).toString(),(document.getData().get("categorie")).toString(),
                                                (document.getData().get("description")).toString(),(document.getData().get("disponibilite")).toString(),
                                                prix_unitaire_cast));
                                        break;

                                    case "Plat":

                                        plat.add(new MenuItemClass((document.getData().get("nom")).toString(),(document.getData().get("categorie")).toString(),
                                                (document.getData().get("description")).toString(),(document.getData().get("disponibilite")).toString(),
                                                prix_unitaire_cast));
                                        break;

                                    case "Dessert":
                                        dessert.add(new MenuItemClass((document.getData().get("nom")).toString(),(document.getData().get("categorie")).toString(),
                                                (document.getData().get("description")).toString(),(document.getData().get("disponibilite")).toString(),
                                                prix_unitaire_cast));
                                        break;
                                    default:
                                        break;

                                }
                                hashMap_categorie_menu.put("BOISSONS", boisson);
                                hashMap_categorie_menu.put("ENTREES", entree);
                                hashMap_categorie_menu.put("PLATS", plat);
                                hashMap_categorie_menu.put("DESSERTS", dessert);

                            }


                            expandableListTitle = new ArrayList<String>(hashMap_categorie_menu.keySet());
                            expandableListAdapter = new MenuItemsAdapter(getActivity(), expandableListTitle, hashMap_categorie_menu,mon_panier);
                            expandableListView.setAdapter(expandableListAdapter);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        button_panier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int j=0;
                for (List<MenuItemClass> value : hashMap_categorie_menu.values()) {
                    for (int i=0; i< value.size();i++)
                    {
                        if (value.get(i).getQuantite()!=0)
                        {
                            mon_panier.add(new MenuItemClass(value.get(i).getNom(),value.get(i).getQuantite()));
                            int k=0;
                        }
                    }
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
