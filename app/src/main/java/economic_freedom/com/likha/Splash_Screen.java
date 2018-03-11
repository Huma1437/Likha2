package economic_freedom.com.likha;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash_Screen extends Activity {

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
        static final int LOCATION_REQUEST = 1;
        ImageView imageView;

        public static String str_login_test;

        public static SharedPreferences sh;
        public static SharedPreferences.Editor editor;


        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash_screen);

            imageView = (ImageView) findViewById(R.id.img);

            Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
            imageView.startAnimation(myFadeInAnimation);


            sh = getSharedPreferences("myprefe", 0);
            editor = sh.edit();

            // check here if user is login or not
            str_login_test = sh.getString("loginTest", null);


            if (getIntent().getBooleanExtra("EXIT", false)) {
                finish();
                return;
            }
            if (!runtime_permissions())
            nextactivity(1);

        }

    private boolean runtime_permissions() {
        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //Alert boz to show user what permissions has to be granted
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("App Permissions");
            alertDialogBuilder.setMessage(R.string.ALLinone);
            alertDialogBuilder.setCancelable(false);
            //If user allow ask permission
            alertDialogBuilder.setPositiveButton("Allow",
                    new DialogInterface.OnClickListener() {

                        @TargetApi(Build.VERSION_CODES.M)
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            requestPermissions(new String[]{
                                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.ACCESS_NETWORK_STATE
                            }, REQUEST_ID_MULTIPLE_PERMISSIONS);
                        }
                    });

            alertDialogBuilder.setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //If user deny then close app
                    finish();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();


            return true;
        }
        return false;
    }

        public void nextactivity(final int requestCode) {

//            flipit(image);
            /****** Create Thread that will sleep for 5 seconds *************/
            Thread background = new Thread() {
                public void run() {

                    try {
                        // Thread will sleep for 5 seconds
                        sleep(5 * 1000);

                        if (str_login_test != null && !str_login_test.toString().trim().equals("")) {
                            Intent send = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(send);
                        } else
                        {
                            Intent send = new Intent(getApplicationContext(), Login_Activity.class);
                            startActivity(send);

                        }
                        //Remove activity
                        finish();

                    } catch (Exception e) {

                    }
                }
            };

            // start thread
            background.start();
        }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_ID_MULTIPLE_PERMISSIONS) {
            if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {

         nextactivity(1);

            } else

            {
                runtime_permissions();

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }

    }

