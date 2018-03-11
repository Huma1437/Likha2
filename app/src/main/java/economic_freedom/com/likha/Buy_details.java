package economic_freedom.com.likha;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Buy_details extends AppCompatActivity {

    ImageView image;
    Button call;
    TextView name,cat,pname,cost,mat;

    String get_name,get_cat,get_pname,get_cost,get_mat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_details);

        image = (ImageView) findViewById(R.id.imageView);
        call = (Button) findViewById(R.id.call);
        name = (TextView) findViewById(R.id.json_name);
        cat = (TextView) findViewById(R.id.json_cat);
        pname = (TextView) findViewById(R.id.json_pname);
        cost = (TextView) findViewById(R.id.json_cost);
        mat = (TextView) findViewById(R.id.json_mat);


        get_name = getIntent().getStringExtra("Person_Name");
        get_cat = getIntent().getStringExtra("Category");
        get_pname = getIntent().getStringExtra("Product_Name");
        get_cost = getIntent().getStringExtra("Cost");
        get_mat = getIntent().getStringExtra("Materials");

        name.setText(get_name);
        cat.setText(get_cat);
        pname.setText(get_pname);
        cost.setText(get_cost);
        mat.setText(get_mat);

        if(get_name.equals("Person1")){
            image.setImageResource(R.drawable.art_itwo);
        }else if(get_name.equals("Person2")){
            image.setImageResource(R.drawable.candle_img);
        }else if(get_name.equals("Person3")){
            image.setImageResource(R.drawable.jwl_ione);
        }else if(get_name.equals("Person4")){
            image.setImageResource(R.drawable.emb_ione);
        }else if(get_name.equals("Person5")){
            image.setImageResource(R.drawable.custom_img);
        }else if(get_name.equals("Person6")){
            image.setImageResource(R.drawable.acn_ione);
        }else if(get_name.equals("Person7")){
            image.setImageResource(R.drawable.jwl_two);
        }else if(get_name.equals("Person8")){
            image.setImageResource(R.drawable.cust_ione);
        }
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Calling " + get_name, Toast.LENGTH_LONG).show();
            }
        });
    }
}
