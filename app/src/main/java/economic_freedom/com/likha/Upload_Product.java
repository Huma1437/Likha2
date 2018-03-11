package economic_freedom.com.likha;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Upload_Product extends AppCompatActivity {

    private static final String TAG = "Upload_Product_Activity" ;
    EditText pname,prod_name,cost,materials,contact;
    Spinner categorySpinner;
    TextView errorText;
    String spinner_String;
    Button upload_btn,image_btn;
    String get_pname,get_prod_name, get_cost, get_contact;
    ImageView image;

    AlertDialog alertDialog = null;
    NetworkChangeReceiver br;
    private static final int SELECT_PICTURE = 100;
    String realPath;

    ArrayList<My_product_pojo> product_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_product);

        product_list = new ArrayList<My_product_pojo>();

        br = new NetworkChangeReceiver();
        image = (ImageView) findViewById(R.id.imageView);
        image_btn = (Button) findViewById(R.id.upload_btn);

        image.setVisibility(View.INVISIBLE);
        image_btn.setVisibility(View.VISIBLE);

        image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFolder();
            }
        });

        pname = (EditText) findViewById(R.id.pname);

        pname.setFilters(new InputFilter[] {
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

        prod_name = (EditText) findViewById(R.id.prod_name);
        cost = (EditText) findViewById(R.id.cost);
        materials = (EditText) findViewById(R.id.cost);
        contact = (EditText) findViewById(R.id.phonenumber);
        upload_btn = (Button) findViewById(R.id.upload);


        categorySpinner = (Spinner) findViewById(R.id.category);

        // Initializing a String Array
        final String[] category = new String[]{ "Select Product Category","Art & Craft", "Candle Making" ,"Content Developer", "Custom made Gifts","Embroidery Unit", "E-Teaching", "Jwellery Making","Lunch Delivery Service","Event Organizing"};

        final List<String> category_list = new ArrayList<>(Arrays.asList(category));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,category_list){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                errorText = (TextView)categorySpinner.getSelectedView();

                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        // Drop down layout style - list view with radio button
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);

        // attaching data adapter to spinner
        categorySpinner.setAdapter(spinnerArrayAdapter);

        // Spinner click listener
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                spinner_String = parent.getItemAtPosition(position).toString();

                if(spinner_String.equals("Select Product Category")){
                    spinner_String = "";
                }


                Log.e("SPINNER ITEM",">>>>>>>>>>>>>>>>..." + spinner_String);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO Auto-generated method stub
                get_pname = pname.getText().toString().trim();
                get_prod_name = prod_name.getText().toString().trim();
                get_cost = cost.getText().toString().trim();
                get_contact = contact.getText().toString().trim();

                if(get_contact.length() < 10){
                    contact.requestFocus();
                    contact.setError("Must exceed 10 characters!");
                }

                if (get_pname.isEmpty()) {
//                    Toast.makeText(getApplicationContext(), "Please enter your Name",
//                            Toast.LENGTH_LONG).show();
                    pname.setError("Please enter your Name");
                } else  if (get_prod_name.isEmpty()) {
//                    Toast.makeText(getApplicationContext(), "Please enter your Name",
//                            Toast.LENGTH_LONG).show();
                    prod_name.setError("Please enter product Name");
                }else  if (get_cost.isEmpty()) {
//                    Toast.makeText(getApplicationContext(), "Please enter your Name",
//                            Toast.LENGTH_LONG).show();
                    cost.setError("Please enter cost");
                }

                else if (get_contact.isEmpty()) {
//                    Toast.makeText(getApplicationContext(),
//                            "Please select your Birthday", Toast.LENGTH_LONG).show();
                    contact.setError("Please enter your mobile number");
                }
                else if (spinner_String.equals("")) {
//                    Toast.makeText(getApplicationContext(),
//                            "Please enter your mobile number", Toast.LENGTH_LONG)
//                            .show();
                    setSpinnerError(categorySpinner);
                }

               /* if(haveNetworkConnection()){*/

                    if(!get_pname.isEmpty() && !get_prod_name.isEmpty() && !get_cost.isEmpty() && !get_contact.isEmpty()) {
                   /* Intent intent = new Intent(Upload_Product.this, MyProduct.class);
                    startActivity(intent);*/

                        Toast.makeText(Upload_Product.this, "Product Uploaded Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Upload_Product.this, Start_up_main.class);
                        startActivity(intent);

                    }
                     /* }
                }else{
                    Toast.makeText(Upload_Product.this,"No Internet Connection!!! Please Enable Internet",Toast.LENGTH_LONG).show();
                }*/
            }

        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {


                // SDK < API11
                if (Build.VERSION.SDK_INT < 11)
                    realPath = RealPathUtil.getRealPathFromURI_BelowAPI11(this, data.getData());

                    // SDK >= 11 && SDK < 19
                else if (Build.VERSION.SDK_INT < 19)
                    realPath = RealPathUtil.getRealPathFromURI_API11to18(this, data.getData());

                    // SDK > 19 (Android 4.4)
                else
                    realPath = RealPathUtil.getRealPathFromURI_API19(this, data.getData());

                // Get the url from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // Get the path from the Uri
                    Log.i(TAG, "Image Path : " + realPath);
                    // Set the image in ImageView
                    image.setVisibility(View.VISIBLE);
                    image.setImageURI(selectedImageUri);
                    image_btn.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }


    private void openFolder() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    private void setSpinnerError(Spinner categorySpinner){
        View selectedView = categorySpinner.getSelectedView();

        if (selectedView != null && selectedView instanceof TextView) {
            categorySpinner.requestFocus();
            TextView selectedTextView1 = (TextView) selectedView;
            selectedTextView1.setError("error"); // any name of the error will do
            selectedTextView1.setTextColor(Color.RED); //text color in which you want your error message to be displayed
            selectedTextView1.setText("Please Select Category"); // actual error message
            categorySpinner.performClick(); // to open the spinner list if error is found.

        }
    }

    public void dialogBoxForInternet() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Upload_Product.this);
        alertDialogBuilder.setTitle("No Internet Connection.");
        alertDialogBuilder
                .setMessage("Go to Settings to enable Internet Connectivity")
                .setCancelable(false)
                .setPositiveButton("Settings",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                startActivityForResult(
                                        new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
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
}