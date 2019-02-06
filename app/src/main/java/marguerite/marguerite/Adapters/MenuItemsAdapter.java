package marguerite.marguerite.Adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import marguerite.marguerite.Classes.MenuItemClass;
import marguerite.marguerite.R;

public class MenuItemsAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<MenuItemClass>> expandableListDetail;
    private List<MenuItemClass> m_test=new ArrayList<>();
    //private ArrayList<MenuItemClass> mon_panier;


    private int count=0;
    private String temp;
    private Boolean present_dans_la_liste=false;
    public MenuItemsAdapter(Context context, List<String> expandableListTitle,
                            HashMap<String, List<MenuItemClass>> expandableListDetail,ArrayList<MenuItemClass> mon_panier) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
       //this.mon_panier = mon_panier;

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

        final MenuItemClass expandedListText = (MenuItemClass) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.background_increase_decrease_product, null);
        }
        final TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText.getNom());




        final TextView product_name=(TextView) convertView.findViewById(R.id.expandedListItem);
        Button plus=(Button) convertView.findViewById(R.id.buttonPlus);
        Button minus=(Button)convertView.findViewById(R.id.buttonMinus);
        final TextView quantity= (TextView)convertView.findViewById(R.id.quantity);

        plus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int k=0;
                count=expandedListText.getQuantite();
                count+=1;
                temp=String.valueOf(count);
                expandedListText.setQuantite(count);
                quantity.setText(temp);

                /*for ( int i=0; i<mon_panier.size();i++)
                {
                    int k=0;
                    if(product_name.getText().toString().equals(mon_panier.get(i).getNom()))
                    {
                        present_dans_la_liste=true;

                    }
                }
                for ( int i=0; i<=mon_panier.size();i++)
                {
                    int j=0;
                    if (present_dans_la_liste==true)
                        mon_panier.get(i).setQuantite(mon_panier.get(i).getQuantite()+1);
                    else
                        mon_panier.add(new MenuItemClass(product_name.getText().toString(),1));

                }

                int i=0;*/
            }
        });





        minus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                count=expandedListText.getQuantite();
                count-=1;
                temp=String.valueOf(count);
                expandedListText.setQuantite(count);
                quantity.setText(temp);

                int i=0;

            }
        });



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