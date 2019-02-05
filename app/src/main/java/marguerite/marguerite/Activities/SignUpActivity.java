package marguerite.marguerite.Activities;

import android.app.Dialog;
import android.app.FragmentManager;
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

import marguerite.marguerite.Fragments.MeansOfPaymentFragment;
import marguerite.marguerite.Fragments.RestaurantMenuFragment;
import marguerite.marguerite.Fragments.RootSignUpFragment;
import marguerite.marguerite.Fragments.SignUpFragment;
import marguerite.marguerite.R;

public class SignUpActivity extends AppCompatActivity {


private SignUpFragment signUpFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpFragment=new SignUpFragment();


        android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.sign_up_activity,signUpFragment).commit();


    }
}
