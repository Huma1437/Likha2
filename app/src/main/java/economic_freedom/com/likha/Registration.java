package economic_freedom.com.likha;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration extends Activity {
    Button register;
    String str_Name,str_Uname, str_Password, str_RePassword, str_Email, str_Mobile,str_dob,str_address;
    private RadioButton rbmale,rbfmale;
    Calendar myCalendar = Calendar.getInstance();
    EditText edt_Name,edt_Uname, edt_Password, edt_RePassword, edt_Email, edt_Mobile,et_bday,address;
    TextView reg_title;
    static final int DATE_DIALOG_ID = 0;

    String Gender = "";

    AlertDialog alertDialog=null;
    NetworkChangeReceiver br;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        if (!Stroage_permission())

        br = new NetworkChangeReceiver();
        mContext = this;


        if(haveNetworkConnection()){

        }else{
            Toast.makeText(Registration.this,"No Internet Connection!!! Please Enable Internet",Toast.LENGTH_LONG).show();
        }

        register = (Button) findViewById(R.id.btn_register);
        edt_Name = (EditText) findViewById(R.id.edt_Rname);

        edt_Name.setFilters(new InputFilter[] {
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence cs, int start,
                                               int end, Spanned spanned, int dStart, int dEnd) {
                        // TODO Auto-generated method stub
                        if(cs.equals("")){ // for backspace
                            return cs;
                        }
                        if(cs.toString().matches("[a-zA-Z ]+")){
                            return cs;
                        }
                        return "";
                    }
                }
        });


        edt_Uname = (EditText) findViewById(R.id.edt_Uname);
        edt_Password = (EditText) findViewById(R.id.edt_Rpassword);
        edt_RePassword = (EditText) findViewById(R.id.edt_RRepassword);
        edt_Mobile = (EditText) findViewById(R.id.edt_Rmobile);
        edt_Email = (EditText) findViewById(R.id.edt_email);
        rbmale = (RadioButton)findViewById(R.id.rbmale);
        rbfmale= (RadioButton)findViewById(R.id.rbfmale);
        et_bday = (EditText) findViewById(R.id.edt_bday);
        reg_title = (TextView) findViewById(R.id.txt_rtitle);

        et_bday.setInputType(InputType.TYPE_NULL);

        et_bday.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event) {
                if(v == et_bday)
                    showDialog(DATE_DIALOG_ID);
                return false;
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                str_Name = edt_Name.getText().toString();
                str_Uname = edt_Uname.getText().toString();
                str_Password = edt_Password.getText().toString();
                str_RePassword = edt_RePassword.getText().toString();
                str_Mobile = edt_Mobile.getText().toString();
                str_Email = edt_Email.getText().toString();
                str_dob = et_bday.getText().toString();

                if (str_Name.isEmpty()) {
//                    Toast.makeText(getApplicationContext(), "Please enter your Name",
//                            Toast.LENGTH_LONG).show();
                    edt_Name.setError("Please enter your Name");
                } else if (str_Uname.isEmpty()) {
//                    Toast.makeText(getApplicationContext(), "Please enter your Name",
//                            Toast.LENGTH_LONG).show();
                    edt_Uname.setError("Please enter unique Username");

                } else if (!rbfmale.isChecked() && !rbmale.isChecked()) {
                    Toast.makeText(getApplicationContext(),
                            "Please select gender", Toast.LENGTH_LONG).show();
                } else if (et_bday.length() == 0) {
//                    Toast.makeText(getApplicationContext(),
//                            "Please select your Birthday", Toast.LENGTH_LONG).show();
                    et_bday.setError("Please select your Birthday");
                } else if (str_Mobile.isEmpty()) {
//                    Toast.makeText(getApplicationContext(),
//                            "Please enter your mobile number", Toast.LENGTH_LONG)
//                            .show();
                    edt_Mobile.setError("Please enter your mobile number");
                } else if (str_Email.length() == 0 || (!isEmailValid(str_Email))) {
//                    Toast.makeText(getApplicationContext(),
//                            "Please enter your Email Id", Toast.LENGTH_LONG).show();
                    edt_Email.setError("Please enter your Email Id");
                } else if (str_Password.isEmpty()) {
//                    Toast.makeText(getApplicationContext(),
//                            "Confirm Password does not match", Toast.LENGTH_LONG)
//                            .show();
                    edt_Password.setError("Please enter your Password");
                } else if (str_RePassword.isEmpty() || !str_Password.equals(str_RePassword)) {
//                    Toast.makeText(getApplicationContext(),
//                            "Please Re-enter your Password", Toast.LENGTH_LONG).show();
                    edt_RePassword.setError("Please Re-enter your Password");
                } else {
                    if (rbmale.isChecked()) {
                        Gender = "Male";
                    } else if (rbfmale.isChecked()) {
                        Gender = "Female";
                        str_dob = et_bday.getText().toString();
                    }

                }


                    if(!str_Name.isEmpty() && !str_Uname.isEmpty() && !str_Password.isEmpty()
                            && !str_RePassword.isEmpty() && str_RePassword.equals(str_Password)&& !str_Mobile.isEmpty()&& !str_Email.isEmpty()
                            && str_dob.length() != 0) {

                        Splash_Screen.editor.putString("Name", str_Name);
                        Splash_Screen.editor.putString("uname", str_Uname);
                        Splash_Screen.editor.putString("DOB", str_dob);
                        Splash_Screen.editor.putString("Gender", Gender);
                        Splash_Screen.editor.putString("Mobile", str_Mobile);
                        Splash_Screen.editor.putString("Email", str_Email);
                        Splash_Screen.editor.putString("password", str_Password);
                        Splash_Screen.editor.putString("Confirm_pwd", str_RePassword);

                        Splash_Screen.editor.commit();

                        Log.e("SharedP Values REg","?????????/" +str_Name + str_Uname +str_dob +str_Mobile +str_Email +str_Password +str_RePassword +str_address);

                        Intent sendtoLogin = new Intent(getApplicationContext(), Login_Activity.class);

                        Toast.makeText(getApplicationContext(),
                                "You have successfully registered", Toast.LENGTH_LONG)
                                .show();

                        startActivity(sendtoLogin);

                    }
            }
        });
}
    public boolean Stroage_permission(){

        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {

            requestPermissions(new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},10);

            return true;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {
            case 10:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    finish();
                    overridePendingTransition( 0, 0);
                    startActivity(getIntent());
                    overridePendingTransition( 0, 0);
                } else {
                    // Permission Denied
                    finish();
                    overridePendingTransition( 0, 0);
                    startActivity(getIntent());
                    overridePendingTransition( 0, 0);
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }





    public void dialogBoxForInternet() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Registration.this);
        alertDialogBuilder.setTitle("No Internet Connection.");
        alertDialogBuilder
                .setMessage("Go to Settings to enable Internet Connectivity")
                .setCancelable(false)
                .setPositiveButton("Settings",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                startActivityForResult(
                                        new Intent(android.provider.Settings.ACTION_SETTINGS),0);
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();

        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    public class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (haveNetworkConnection()) {
                if (alertDialog != null && alertDialog.isShowing()) {
                    alertDialog.dismiss();
                }

            } else {

                if (alertDialog != null && alertDialog.isShowing()) {
                    alertDialog.dismiss();
                }

                dialogBoxForInternet();
            }
        }
    }


    public boolean isEmailValid(String email)
    {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        Calendar c = Calendar.getInstance();
        int cyear = c.get(Calendar.YEAR);
        int cmonth = c.get(Calendar.MONTH);
        int cday = c.get(Calendar.DAY_OF_MONTH);
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,  mDateSetListener,  cyear, cmonth, cday);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        // onDateSet method
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String date_selected = String.valueOf(dayOfMonth)+"/"+String.valueOf(monthOfYear+1)+"/"+String.valueOf(year);
            //Toast.makeText(getApplicationContext(), "Selected Date is ="+date_selected, Toast.LENGTH_SHORT).show();
            et_bday.setText(date_selected);
        }
    };

    @Override
    public void onBackPressed() {
       finish();

    }
}
