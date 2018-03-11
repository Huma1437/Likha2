package economic_freedom.com.likha;

import android.app.Activity;
import android.app.ProgressDialog;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NewsFeed extends AppCompatActivity {

    //Declairing ArrayList for Storing Country name and price
    private ArrayList<NewsFeed_pojo> newsfeed;
    private RecyclerView recyclerView;
    private NewsFeed_Adapter NFadapter;
    private ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // Set layout manager to position the items
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        new GetNewsFeed().execute();


    }
    @Override
    public void onBackPressed() {
        finish();
    }

   class GetNewsFeed extends AsyncTask<Void, Void, Void> {

       protected void onPreExecute() {

           // Showing progress dialog
           pDialog = new ProgressDialog(NewsFeed.this);
           pDialog.setMessage("Please wait...");
           pDialog.setCancelable(true);
           pDialog.show();
       }



       @Override
       protected Void doInBackground(Void... arg0) {

           newsfeed = new ArrayList<NewsFeed_pojo>();

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

               NewsFeed_pojo npojo;
               // Creating JSONObject from String
               JSONObject magazines = new JSONObject(myjsonstring);

               // Creating JSONArray from JSONObject
               JSONArray mag = magazines.getJSONArray("News_Feed");

               for (int y = 0; y < mag.length(); y++) {

                   JSONObject jObj = mag.getJSONObject(y);

                   String name = jObj.getString("name");
                   String category = jObj.getString("category");
                   String product_name = jObj.getString("product_name");
                   String cost = jObj.getString("cost");
                   String materials = jObj.getString("materials");

                   npojo = new NewsFeed_pojo(name,category,product_name,cost,materials);
                   newsfeed.add(npojo);
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
           NFadapter = new NewsFeed_Adapter(newsfeed, NewsFeed.this);
           // Attach the adapter to the recyclerview to populate itemsce
           recyclerView.setAdapter(NFadapter);
           pDialog.dismiss();
       }
   }
}
