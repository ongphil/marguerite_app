package marguerite.marguerite;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends Activity {

    EditText et_login_email;
    EditText et_login_password;
    Button buttonConnection;

    String email;
    String password;

    private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_login_email = (EditText)findViewById(R.id.et_login_email);
        et_login_password = (EditText)findViewById(R.id.et_login_password);
        buttonConnection = (Button)findViewById(R.id.button_connexion);

        Auth= FirebaseAuth.getInstance();

        Auth.signOut();

        ///LOGIN FOR SPECIFIC USER
        buttonConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = et_login_email.getText().toString();
                password = et_login_password.getText().toString();

                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password))
                {
                    Auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.e("TAG", "User ID : " + Auth.getCurrentUser().getUid());
                                Intent sendToMain = new Intent (LoginActivity.this, MainActivity.class);
                                startActivity(sendToMain);
                                finish();

                            } else {
                                Toast.makeText(LoginActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    /*Intent sendToMain = new Intent (LoginActivity.this, MainActivity.class);
                    startActivity(sendToMain);
                    finish();*/
                }
            }
        });
    }
}
