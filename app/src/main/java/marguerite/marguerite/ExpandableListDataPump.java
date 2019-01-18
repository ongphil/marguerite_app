package marguerite.marguerite;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();



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