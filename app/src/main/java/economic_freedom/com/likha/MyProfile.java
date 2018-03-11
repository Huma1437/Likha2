package economic_freedom.com.likha;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class MyProfile extends AppCompatActivity {

    ImageView profile;
    TextView name,uname,dob,gender,number,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        name = (TextView) findViewById(R.id.tname);
        uname = (TextView) findViewById(R.id.username);
        dob = (TextView) findViewById(R.id.tbday);
        gender = (TextView) findViewById(R.id.tgen);
        number = (TextView) findViewById(R.id.tmob);
        email = (TextView) findViewById(R.id.temail);

        String getname = Splash_Screen.sh.getString("Name" , null);
        String getUsername = Splash_Screen.sh.getString("uname" , null);
        String getDOB = Splash_Screen.sh.getString("DOB", null);

        String get_gender = Splash_Screen.sh.getString("Gender" , null);
        String get_number = Splash_Screen.sh.getString("Mobile" , null);
        String get_email = Splash_Screen.sh.getString("Email", null);

        name.setText(getname);
        uname.setText(getUsername);
        gender.setText(get_gender);
        dob.setText(getDOB);
        number.setText(get_number);
        email.setText(get_email);
    }
}
