package marguerite.marguerite;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class SignUpActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        spinner =(Spinner)findViewById(R.id.Spiner_telephone_index);
        adapter = ArrayAdapter.createFromResource(this,R.array.telephone_index,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setSelection(0);

        editText_first_name= (EditText)findViewById(R.id.editText_first_name);
        editText_last_name=(EditText)findViewById(R.id.editText_last_name);

        radio_button_gender = (RadioGroup)findViewById(R.id.radio_button_gender);
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

        continuer = (Button)findViewById(R.id.button_continuer);
        int y=0;
        /*continuer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // custom dialog
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.background_add_card);
                Window window=dialog.getWindow();
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));





                // set the custom dialog components - text, image and button

                dialog.show();
            }
        });*/
        ajouter=(Button)findViewById(R.id.button_add_card);
        ajouter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

               /* FragmentTransaction trans = getFragmentManager()
                        .beginTransaction();
                /*
                 * IMPORTANT: We use the "root frame" defined in
                 * "root_fragment.xml" as the reference to replace fragment
                 */
               /* trans.replace(R.id.root_home_fragment, new ToOrderFragment());

                /*
                 * IMPORTANT: The following lines allow us to add the fragment
                 * to the stack and return to it later, by pressing back
                 */
                /*trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                trans.addToBackStack(null);

                trans.commit();*/
            }
        });




    }
}
