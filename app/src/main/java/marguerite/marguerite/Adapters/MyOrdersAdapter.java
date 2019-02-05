package marguerite.marguerite.Adapters;



import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import marguerite.marguerite.Classes.OrderClass;
import marguerite.marguerite.R;

public class MyOrdersAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<OrderClass>> expandableListDetail;


    public MyOrdersAdapter(Context context, List<String> expandableListTitle,
                           HashMap<String, List<OrderClass>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;

    }



    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final OrderClass ord = (OrderClass) getChild(listPosition, expandedListPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.background_my_orders, null);
        }

        TextView date=(TextView)convertView.findViewById(R.id.date);
        TextView status=(TextView)convertView.findViewById(R.id.status);
        TextView price=(TextView)convertView.findViewById(R.id.price);


        //Cast
        double cast_prix_toal=ord.getPrixTotal();
        String prix_total=String.valueOf(cast_prix_toal);

        //Modifications des textviews
        status.setText(ord.getStatut());
        price.setText(prix_total+"€");



        String status_order = ord.getStatut().toString();
        switch (status_order)
        {
            case "Prete":
                date.setText(ord.getDate().get("ordered").toString());
                break;
            case "En preparation":
                date.setText(ord.getDate().get("ordered").toString());
                break;
            case "Terminee":
                date.setText(ord.getDate().get("delivered").toString());
                break;
            default:
                break;
        }

        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.background_category_title_of_the_product, null);
        }


        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);

        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}