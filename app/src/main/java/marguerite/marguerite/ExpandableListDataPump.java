package marguerite.marguerite;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.ContentValues.TAG;

public class ExpandableListDataPump {

    public static HashMap<String, List<String>> getData() {

        final  HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        final ArrayList<Category> category;
        category=new ArrayList<>();

        FirebaseFirestore firestore;
        firestore=FirebaseFirestore.getInstance();

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
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });




        /*boisson.add("Coca");
        boisson.add("Sprite");



        entree.add("Salade");
        entree.add("Maïs");



        plat.add("Pates");
        plat.add("Steak");


        dessert.add("Glace");
        dessert.add("Gateau");*/


        return expandableListDetail;




    }
}