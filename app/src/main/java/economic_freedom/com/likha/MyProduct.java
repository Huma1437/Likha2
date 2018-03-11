package economic_freedom.com.likha;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MyProduct extends AppCompatActivity {

    ArrayList<My_product_pojo> product_list;
    private RecyclerView recyclerView;
    private MyProductAdapter Productadapter;
    private ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_product);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // Set layout manager to position the items
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        new GetProduct().execute();

    }

    @Override
    public void onBackPressed() {
        finish();
    }


    private class GetProduct extends AsyncTask<Void, Void, Void> {

        protected void onPreExecute() {

            // Showing progress dialog
            pDialog = new ProgressDialog(MyProduct.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(true);
            pDialog.show();
        }



        @Override
        protected Void doInBackground(Void... arg0) {

            product_list = new ArrayList<My_product_pojo>();

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

               My_product_pojo mp;
                // Creating JSONObject from String
                JSONObject buy = new JSONObject(myjsonstring);

                // Creating JSONArray from JSONObject
                JSONArray mag = buy.getJSONArray("MyProducts");

                for (int y = 0; y < mag.length(); y++) {

                    JSONObject jObj = mag.getJSONObject(y);

                    String name = jObj.getString("name");
                    String category = jObj.getString("category");
                    String product_name = jObj.getString("product_name");
                    String materials = jObj.getString("materials");
                    String cost = jObj.getString("cost");

                    mp = new My_product_pojo(name,product_name,category,cost,materials);
                    product_list.add(mp);
                }

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // Create adapter passing in the sample user data
            Productadapter = new MyProductAdapter(product_list, MyProduct.this);
            // Attach the adapter to the recyclerview to populate itemsce
            recyclerView.setAdapter(Productadapter);
            pDialog.dismiss();
        }
    }
}
