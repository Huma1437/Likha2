package economic_freedom.com.likha;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;



public class Select_category extends Activity {
    String selected;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);

        setTitle("Select Category");

        if (!Stroage_permission())

        //find the list view from xml file
        listView = (ListView) findViewById(R.id.simplelist);

        // make an array which stores the list items
        final String[] myList = {"Art and Craft","Candle Making","Content Developer","Custom made Gifts","Embroidery Unit", "E-Teaching", "Jwellery Making",
                "Lunch Delivery Service","Event Organizing"
        };


        //array adapter takes 4 parameters, context, layout reference, id of text view, array of items
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, android.R.id.text1, myList);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // Add a header to the ListView
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.listview_header, listView, false);
        listView.addHeaderView(header);

        // set the list item on click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                listView.getSelectedItem();
                listView.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Select Category : " + listView.getItemAtPosition(position), Toast.LENGTH_LONG).show();
                Splash_Screen.editor.putString("Language", String.valueOf(listView.getItemAtPosition(position)));
                Splash_Screen.editor.commit();
                Intent intent = new Intent(getApplicationContext(), Start_up_main.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

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


    @Override
    public void onBackPressed() {
        finish();

    }
}

