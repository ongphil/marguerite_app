package marguerite.marguerite;

import android.app.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class RestaurantAdapter extends ArrayAdapter<Restaurant> {

    private final Context context;
    private List<Restaurant> res = new ArrayList<Restaurant>();


    public RestaurantAdapter(Context context, ArrayList<Restaurant> restaurant ) {
        super(context,0, restaurant);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.res=restaurant;




    }

    public View getView(int position, View view, ViewGroup parent) {

        View rowView = view;
        if(rowView == null)
            rowView = LayoutInflater.from(context).inflate(R.layout.background_suggestion_restaurant,parent,false);

        Restaurant restaurant=res.get(position);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);

        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);

        titleText.setText(restaurant.getNom());

        subtitleText.setText(restaurant.getAdresse());

        return rowView;

    }
}