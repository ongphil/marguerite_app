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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Calendar;





public class RestaurantAdapter extends ArrayAdapter<Restaurant> {

    private final Context context;
    private List<Restaurant> res;


    public RestaurantAdapter(Context context, ArrayList<Restaurant> restaurant ) {
        super(context,0, restaurant);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.res=restaurant;


    }

    public View getView(int position, View view, ViewGroup parent) {

        View rowView = view;
        res= new ArrayList<>();

        if(rowView == null)
            rowView = LayoutInflater.from(context).inflate(R.layout.background_suggestion_restaurant,parent,false);

        Restaurant restaurant=res.get(position);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);

        TextView resto_addressText = (TextView) rowView.findViewById(R.id.resto_address);

        TextView resto_schedules = (TextView) rowView.findViewById(R.id.resto_schedules);

        titleText.setText(restaurant.getNom());

        resto_addressText.setText(restaurant.getAdresse());

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());

        switch(c.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.MONDAY:
                resto_schedules.setText("Horaires d'ouverture : " + restaurant.getHoraires().get("lundi").toString());
                break;
            case  Calendar.TUESDAY:
                resto_schedules.setText("Horaires d'ouverture : " + restaurant.getHoraires().get("mardi").toString());
                break;
            case Calendar.WEDNESDAY:
                resto_schedules.setText("Horaires d'ouverture : " + restaurant.getHoraires().get("mercredi").toString());
                break;
            case Calendar.THURSDAY:
                resto_schedules.setText("Horaires d'ouverture : " + restaurant.getHoraires().get("jeudi").toString());
                break;
            case  Calendar.FRIDAY:
                resto_schedules.setText("Horaires d'ouverture : " + restaurant.getHoraires().get("vendredi").toString());
                break;
            case Calendar.SATURDAY:
                resto_schedules.setText("Horaires d'ouverture : " + restaurant.getHoraires().get("samedi").toString());
                break;
            case Calendar.SUNDAY:
                resto_schedules.setText("Horaires d'ouverture : " + restaurant.getHoraires().get("dimanche").toString());
                break;
        }

        return rowView;

    }
}