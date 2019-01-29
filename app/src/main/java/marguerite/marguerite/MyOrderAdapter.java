package marguerite.marguerite;



import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyOrderAdapter extends ArrayAdapter<Order> {




    private final Context context;
    private List<Order> ord = new ArrayList<Order>();


    public MyOrderAdapter(Context context, ArrayList<Order> order ) {
        super(context,0,order);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.ord=order;




    }

    public View getView(int position,View view,ViewGroup parent) {
        View rowView = view;
        if(rowView == null)
            rowView = LayoutInflater.from(context).inflate(R.layout.background_my_orders,parent,false);

        Order order=ord.get(position);
        String prix_total_cast;
        prix_total_cast=Double.toString(order.getPrixTotal());


        TextView date = (TextView) rowView.findViewById(R.id.date);

        TextView status = (TextView) rowView.findViewById(R.id.status);

        TextView price = (TextView)rowView.findViewById(R.id.price);

        status.setText(order.getStatut());

        price.setText(prix_total_cast);



        return rowView;

    };
}