package economic_freedom.com.likha;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Youtube_activity extends AppCompatActivity {

    //Declairing ArrayList for Storing Country name and price
    private ArrayList<Youtube_pojo> youtube_list;
    private RecyclerView recyclerView;
    private YoutubeAdapter Uadapter;
    private ProgressDialog pDialog;
    String selected_cat;

    AlertDialog alertDialog=null;
    NetworkChangeReceiver br;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        br = new NetworkChangeReceiver();
        mContext = this;

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        selected_cat = Splash_Screen.sh.getString("Language", "");

        // Set layout manager to position the items
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        if(haveNetworkConnection()){

            new Youtube().execute();
        }else{
            Toast.makeText(Youtube_activity.this,"No Internet Connection!!! Please Enable Internet",Toast.LENGTH_LONG).show();
        }

    }


    public void dialogBoxForInternet() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Youtube_activity.this);
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


    class Youtube extends AsyncTask<Void, Void, Void> {

        protected void onPreExecute() {

            // Showing progress dialog
            pDialog = new ProgressDialog(Youtube_activity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(true);
            pDialog.show();
        }



        @Override
        protected Void doInBackground(Void... arg0) {

            youtube_list = new ArrayList<Youtube_pojo>();

            // Reading json file from assets folder
            StringBuffer sb = new StringBuffer();
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(getApplicationContext().getAssets().open("listview.json")));
                String temp;
                while ((temp = br.readLine()) != null)
                    sb.append(temp);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close(); // stop reading
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            String myjsonstring = sb.toString();
            // Try to parse JSON
            try {
                Youtube_pojo u_pojo;

                // Creating JSONObject from String
                JSONObject jsonObjMain = new JSONObject(myjsonstring);

                // Creating JSONArray from JSONObject
                JSONArray jsonArray = jsonObjMain.getJSONArray("select_category");

                // JSONArray has x JSONObject
                for (int i = 0; i < jsonArray.length(); i++) {  //Language Array

                    // Creating JSONObject from JSONArray
                    JSONObject jsonObj = jsonArray.getJSONObject(i);

                    // Getting data from individual JSONObject
                    String category = jsonObj.getString("category");

                    JSONArray subArray = jsonObj.getJSONArray("Youtube_links");

                    for (int y = 0; y < subArray.length(); y++) {

                        JSONObject jObj = subArray.getJSONObject(y);

                        String u_title = jObj.getString("title");
                        String u_url = jObj.getString("url");
                        String u_id = jObj.getString("id");

                        if (category.equals(selected_cat)) {
                            u_pojo = new Youtube_pojo(u_title, u_url,u_id);
                            youtube_list.add(u_pojo);

                        }
                    }

                }
            }catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // Create adapter passing in the sample user data
            Uadapter = new YoutubeAdapter(youtube_list, Youtube_activity.this);
            // Attach the adapter to the recyclerview to populate itemsce
            recyclerView.setAdapter(Uadapter);
            pDialog.dismiss();
        }
    }

}
