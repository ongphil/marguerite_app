package marguerite.marguerite;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;




public class RestaurantAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private List<Restaurant> res = new ArrayList<Restaurant>();


    public RestaurantAdapter(Activity context, ArrayList<Restaurant> restaurant ) {
        super(context, R.layout.background_suggestion_restaurant);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.res=restaurant;




    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.background_suggestion_restaurant, null,true);



        Restaurant restaurant=res.get(position);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);

        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);

        titleText.setText(restaurant.getNom());

        subtitleText.setText(restaurant.getAdresse());

        return rowView;

    }
}