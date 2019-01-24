package marguerite.marguerite;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();


        FirebaseFirestore firestore;
        firestore=FirebaseFirestore.getInstance();

        firestore.collection("Restaurants").document("8tWygSWs1VATB4O4fSLk").get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {



                    }
                });



        List<String> boisson = new ArrayList<String>();
        boisson.add("Coca");
        boisson.add("Sprite");


        List<String> entree = new ArrayList<String>();
        entree.add("Salade");
        entree.add("Maïs");


        List<String> plats = new ArrayList<String>();
        plats.add("Pates");
        plats.add("Steak");

        List<String> dessert = new ArrayList<String>();
        dessert.add("Glace");
        dessert.add("Gateau");


        expandableListDetail.put("BOISSONS", boisson);
        expandableListDetail.put("ENTREES", entree);
        expandableListDetail.put("PLATS", plats);
        expandableListDetail.put("DESSERTS", dessert);


        return expandableListDetail;
    }
}