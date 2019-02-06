package marguerite.marguerite.Activities;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import marguerite.marguerite.Fragments.MeansOfPaymentFragment;
import marguerite.marguerite.Fragments.RootSignUpFragment;
import marguerite.marguerite.Fragments.SignUpFragment;
import marguerite.marguerite.R;

public class SignUpActivity extends AppCompatActivity implements RootSignUpFragment.OnFragmentInteractionListener, SignUpFragment.OnFragmentInteractionListener, MeansOfPaymentFragment.OnFragmentInteractionListener {


private RootSignUpFragment rootSignUpFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        rootSignUpFragment=new RootSignUpFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fl_signup, rootSignUpFragment).commit();



    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
