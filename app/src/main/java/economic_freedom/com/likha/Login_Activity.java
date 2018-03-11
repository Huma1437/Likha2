package economic_freedom.com.likha;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login_Activity extends AppCompatActivity {

    String str_UserName, str_Password;

    EditText edt_UName, edt_Password;

    Button login;

    TextView not_register, pswd_reset,login_title;

    AlertDialog alertDialog=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        login = (Button) findViewById(R.id.btn_login);
        edt_UName = (EditText) findViewById(R.id.edt_userName);
        edt_Password = (EditText) findViewById(R.id.edt_password);
        not_register = (TextView) findViewById(R.id.link_signup);
        pswd_reset = (TextView) findViewById(R.id.forgot_pswd);
       /* login_title = (TextView) findViewById(R.id.txt_title);

        login_title.setText("LIKHA");
*/
        pswd_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pswdreset = new Intent(getApplicationContext(), Forgot_password_Activity.class);
                startActivity(pswdreset);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        not_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registeration = new Intent(getApplicationContext(),
                        Registration.class);
                startActivity(registeration);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub

                str_UserName = edt_UName.getText().toString();
                str_Password = edt_Password.getText().toString();

        /* make edittext condition for empty, input etc match */

                if (str_UserName.isEmpty() & str_Password.isEmpty()) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your login User Name and Password",
                            Toast.LENGTH_LONG).show();
                } else if (str_UserName.isEmpty()) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your User Name", Toast.LENGTH_LONG).show();
                } else if (str_Password.isEmpty()) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your Password", Toast.LENGTH_LONG).show();
                }

                    String getUsername = Splash_Screen.sh.getString("uname" , null);
                    String getPassword = Splash_Screen.sh.getString("password", null);

                    if(getUsername == null && getPassword == null){
                        Toast.makeText(Login_Activity.this, "No Internet Connection!!! Please Enable Internet", Toast.LENGTH_LONG).show();
                    }else{
                        if(str_UserName.matches(getUsername) && str_Password.matches(getPassword)){


                                Toast.makeText(getApplicationContext(),
                                        "You have successfuly login", Toast.LENGTH_LONG).show();

                             /* without commit data will not saved to shared preference */
                            Splash_Screen.editor.putString("loginTest", "true");
                            Splash_Screen.editor.commit();

                                Intent sendToLogout = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(sendToLogout);
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                        }
                    }

            }
        });
    }




    // on back key press exit the application.

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }
}