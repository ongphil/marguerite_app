package marguerite.marguerite;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SignupFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SignupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    String telephone_index_selected;


    private EditText editText_first_name;
    private TextInputLayout input_first_name;
    private EditText editText_last_name;
    private TextInputLayout input_last_name;
    private RadioGroup radio_button_gender;
    private RadioButton button_m;
    private  RadioButton button_mme;

    private Button continuer;
    private Button ajouter;


    private OnFragmentInteractionListener mListener;

    public SignupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignupFragment newInstance(String param1, String param2) {
        SignupFragment fragment = new SignupFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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

        View view=inflater.inflate(R.layout.fragment_signup, container, false);

        spinner =(Spinner)view.findViewById(R.id.Spiner_telephone_index);
        adapter = ArrayAdapter.createFromResource(getActivity(),R.array.telephone_index,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setSelection(0);

        editText_first_name= (EditText)view.findViewById(R.id.editText_first_name);
        editText_last_name=(EditText)view.findViewById(R.id.editText_last_name);

        radio_button_gender = (RadioGroup)view.findViewById(R.id.radio_button_gender);
        radio_button_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                // checkedId is the RadioButton selected

                switch(checkedId)
                {
                    case R.id.radioButton_m:
                        break;
                    case R.id.radioButton_mme:
                        break;
                }
            }
        });

        continuer = (Button)view.findViewById(R.id.button_continuer);

        continuer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // custom dialog
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.background_add_card);
                Window window=dialog.getWindow();
                window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));





                // set the custom dialog components - text, image and button

                dialog.show();
            }
        });
        ajouter=(Button)view.findViewById(R.id.button_add_card);
        ajouter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                FragmentTransaction trans = getFragmentManager()
                        .beginTransaction();
                /*
                 * IMPORTANT: We use the "root frame" defined in
                 * "root_fragment.xml" as the reference to replace fragment
                 */
                trans.replace(R.id.root_home_fragment, new ToOrderFragment());

                /*
                 * IMPORTANT: The following lines allow us to add the fragment
                 * to the stack and return to it later, by pressing back
                 */
                trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.addToBackStack(null);

                trans.commit();
            }
        });
        return view;
    }

    public void openDialog() {

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();

/*
        LinearLayout layout=new LinearLayout(getActivity());


        // Set Custom Title
        TextView title = new TextView(getActivity());

        // Title Properties
        title.setText("Bonjour Morgan");
        title.setPadding(10, 10, 10, 10);   // Set Position
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.BLACK);
        title.setTextSize(20);
        alertDialog.setCustomTitle(title);

        // Set Message
        TextView msg = new TextView(getActivity());
        // Message Properties
        msg.setText("Veux-tu ajouter un moyen de paiement maintenant ? ");
        msg.setGravity(Gravity.CENTER_HORIZONTAL);
        msg.setTextColor(Color.BLACK);
        alertDialog.setView(msg);

        // Set Button
        // you can more buttons
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,"Ajouter", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Perform Action on Button
            }
        });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"Plus tard", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Perform Action on Button
            }
        });

        new Dialog(getActivity());
        alertDialog.show();


        final Button ajouter = alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL);
        LinearLayout.LayoutParams neutralBtnLP = (LinearLayout.LayoutParams) ajouter.getLayoutParams();
        neutralBtnLP.gravity = Gravity.FILL_HORIZONTAL;
        ajouter.setPadding(50, 10, 10, 10);   // Set Position
        ajouter.setTextColor(Color.BLUE);
        ajouter.setLayoutParams(neutralBtnLP);

        final Button plus_tard = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        LinearLayout.LayoutParams negBtnLP = (LinearLayout.LayoutParams) ajouter.getLayoutParams();
        negBtnLP.gravity = Gravity.FILL_HORIZONTAL;
        plus_tard.setTextColor(Color.RED);
        plus_tard.setLayoutParams(negBtnLP);*/
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        telephone_index_selected= parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), telephone_index_selected,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
