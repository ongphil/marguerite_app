package marguerite.marguerite.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import marguerite.marguerite.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OrderIsReadyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OrderIsReadyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderIsReadyFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private TextView status;
    private TextView order_nb_or_estimation;
    private ImageView hourglass;



    public OrderIsReadyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @param groupPosition Parameter 1.
     * @param creneau Parameter 2.
     * @param num_commande Parameter 3.


     * @return A new instance of fragment OrderIsRreadyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderIsReadyFragment newInstance(Integer groupPosition,String creneau,String num_commande) {

        OrderIsReadyFragment fragment = new OrderIsReadyFragment();
        Bundle args = new Bundle();
        args.putInt("groupPosition",groupPosition);
        args.putString("creneau",creneau);
        args.putString("num_commande",num_commande);


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
        View view= inflater.inflate(R.layout.fragment_order_is_ready, container, false);

        status=(TextView)view.findViewById(R.id.text_status);
        order_nb_or_estimation = (TextView)view.findViewById(R.id.order_nb_or_estimation);
        hourglass = (ImageView)view.findViewById(R.id.hourglass);

        Integer groupPosition = getArguments().getInt("groupPosition",0);
        String temp_creneau = getArguments().getString("creneau",null);
        String numero_commande = getArguments().getString("num_commande",null);

        if(groupPosition==0)
        {
            status.setText("prête");
            order_nb_or_estimation.setText("Numéro de commande : " + numero_commande);
        }


        if(groupPosition==1)
        {
            status.setText("en préparation");
            order_nb_or_estimation.setText("Temps d'estimation : " + temp_creneau + " min");
            hourglass.setVisibility(View.VISIBLE);

        }


        if(groupPosition==2)
        {
            status.setText("terminée");
            order_nb_or_estimation.setText("Numéro de commande : " + numero_commande);
        }

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
