package economic_freedom.com.likha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class Materials_Purchase extends AppCompatActivity {
    String selected_category;
    ImageView imageView1,imageView2;
    Button btn_one,btn_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materials_purchase);

        imageView1 = (ImageView) findViewById(R.id.img_one);
        imageView2 = (ImageView) findViewById(R.id.img_two);

        btn_one = (Button) findViewById(R.id.button_1);
        btn_two = (Button) findViewById(R.id.button_2);


                selected_category = Splash_Screen.sh.getString("Language", "");
                if(selected_category.equals("Art and Craft")){
                   imageView1.setImageResource(R.drawable.art_ione);
                    imageView2.setImageResource(R.drawable.art_itwo);
                    btn_one.setText("Buy Materials");
                    btn_two.setText("Buy Materials");

                }else if(selected_category.equals("Candle Making")){
                    imageView1.setImageResource(R.drawable.acn_ione);
                    imageView2.setImageResource(R.drawable.candle_img);
                    btn_one.setText("Buy Materials");
                    btn_two.setText("Buy Materials");
                }
                else if(selected_category.equals("Content Developer")){
                    imageView1.setImageResource(R.drawable.dev_one);
                    imageView2.setImageResource(R.drawable.dev_two);
                    btn_one.setText("Browse Ideas");
                    btn_two.setText("Browse Ideas");
                }
                else if(selected_category.equals("Custom made Gifts")){
                    imageView1.setImageResource(R.drawable.cust_ione);
                    imageView2.setImageResource(R.drawable.custom_img);
                    btn_one.setText("Buy Materials");
                    btn_two.setText("Buy Materials");
                }
                else if(selected_category.equals("Embroidery Unit")){
                    imageView1.setImageResource(R.drawable.emb_img);
                    imageView2.setImageResource(R.drawable.emb_ione);
                    btn_one.setText("Buy Materials");
                    btn_two.setText("Buy Materials");

                }
                else if(selected_category.equals("E-Teaching")){

                    imageView1.setImageResource(R.drawable.e_teach_two);
                    imageView2.setImageResource(R.drawable.e_teach_img);
                    btn_one.setText("Browse Ideas");
                    btn_two.setText("Browse Ideas");
                }
                else if(selected_category.equals("Jwellery Making")){
                    imageView1.setImageResource(R.drawable.jwl_one);
                    imageView2.setImageResource(R.drawable.jwl_ione);
                    btn_one.setText("Buy Materials");
                    btn_two.setText("Buy Materials");

                }
                else if(selected_category.equals("Lunch Delivery Service")){
                    imageView1.setImageResource(R.drawable.lun_one);
                    imageView2.setImageResource(R.drawable.lun_two);
                    btn_one.setText("Browse Ideas");
                    btn_two.setText("Browse Ideas");

                }
                else if(selected_category.equals("Event Organizing")){

                    imageView1.setImageResource(R.drawable.event_img);
                    imageView2.setImageResource(R.drawable.event_ione);
                    btn_one.setText("Browse Ideas");
                    btn_two.setText("Browse Ideas");
                }

        btn_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selected_category.equals("Art and Craft")){
                    Intent intent = new Intent(getApplicationContext(), Discover_Activity.class)
                            .putExtra("type",1)
                            .putExtra("url","https://www.craftslane.com/");
                    startActivity(intent);

                }else if(selected_category.equals("Candle Making")){

                    Intent intent = new Intent(getApplicationContext(), Discover_Activity.class)
                            .putExtra("type",1)
                            .putExtra("url","https://www.aromashop.in/candle-making");
                    startActivity(intent);
                }
                else if(selected_category.equals("Content Developer")){
                    Intent intent = new Intent(getApplicationContext(), Discover_Activity.class)
                            .putExtra("type",1)
                            .putExtra("url","https://www.zyxware.com/articles/5008/who-is-a-content-developer-and-what-are-the-basic-skills-and-qualifications-required-for-the-job");
                    startActivity(intent);
                }
                else if(selected_category.equals("Custom made Gifts")){
                    Intent intent = new Intent(getApplicationContext(), Discover_Activity.class)
                            .putExtra("type",1)
                            .putExtra("url"," https://m.fnp.com/personalised-gifts");
                    startActivity(intent);
                }
                else if(selected_category.equals("Embroidery Unit")){
                    Intent intent = new Intent(getApplicationContext(), Discover_Activity.class)
                            .putExtra("type",1)
                            .putExtra("url","http://www.ponycraftstore.com/product/productgroup/embroidery-accessories-11");
                    startActivity(intent);

                }
                else if(selected_category.equals("E-Teaching")){
                    Intent intent = new Intent(getApplicationContext(), Discover_Activity.class)
                            .putExtra("type",1)
                            .putExtra("url","https://readwrite.com/2009/07/21/seven_e-learning_and_teaching_resources/");
                    startActivity(intent);

                }
                else if(selected_category.equals("Jwellery Making")){
                    Intent intent = new Intent(getApplicationContext(), Discover_Activity.class)
                            .putExtra("type",1)
                            .putExtra("url","https://khushihandicrafts.com/");
                    startActivity(intent);

                }
                else if(selected_category.equals("Lunch Delivery Service")){
                    Intent intent = new Intent(getApplicationContext(), Discover_Activity.class)
                            .putExtra("type",1)
                            .putExtra("url","https://money.howstuffworks.com/how-to-start-box-lunch-business.html");
                    startActivity(intent);

                }
                else if(selected_category.equals("Event Organizing")){
                    Intent intent = new Intent(getApplicationContext(), Discover_Activity.class)
                            .putExtra("type",1)
                            .putExtra("url","https://www.thebalance.com/how-to-start-a-wedding-planning-business-1223903");
                    startActivity(intent);

                }
            }
        });


        btn_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selected_category.equals("Art and Craft")){
                    Intent intent = new Intent(getApplicationContext(), Discover_Activity.class)
                            .putExtra("type",1)
                            .putExtra("url","https://www.itsybitsy.in/");
                    startActivity(intent);

                }else if(selected_category.equals("Candle Making")){
                    Intent intent = new Intent(getApplicationContext(), Discover_Activity.class)
                            .putExtra("type",1)
                            .putExtra("url","http://candlemould.com/");
                    startActivity(intent);

                }
                else if(selected_category.equals("Content Developer")){
                    Intent intent = new Intent(getApplicationContext(), Discover_Activity.class)
                            .putExtra("type",1)
                            .putExtra("url"," https://www.tbhcreative.com/web-content-creation/");
                    startActivity(intent);
                }
                else if(selected_category.equals("Custom made Gifts")){
                    Intent intent = new Intent(getApplicationContext(), Discover_Activity.class)
                            .putExtra("type",1)
                            .putExtra("url"," http://www.prestogifts.com/mobile/products/Personalised-Photo-Mugs");
                    startActivity(intent);
                }
                else if(selected_category.equals("Embroidery Unit")){

                    Intent intent = new Intent(getApplicationContext(), Discover_Activity.class)
                            .putExtra("type",1)
                            .putExtra("url","http://www.pradhanembroiderystores.com/category");
                    startActivity(intent);
                }
                else if(selected_category.equals("E-Teaching")){

                    Intent intent = new Intent(getApplicationContext(), Discover_Activity.class)
                            .putExtra("type",1)
                            .putExtra("url","http://www.pradhanembroiderystores.com/category");
                    startActivity(intent);
                }
                else if(selected_category.equals("Jwellery Making")){

                    Intent intent = new Intent(getApplicationContext(), Discover_Activity.class)
                            .putExtra("type",1)
                            .putExtra("url","https://www.jewelrysupply.com/");
                    startActivity(intent);
                }
                else if(selected_category.equals("Lunch Delivery Service")){

                    Intent intent = new Intent(getApplicationContext(), Discover_Activity.class)
                            .putExtra("type",1)
                            .putExtra("url","http://smallbusiness.chron.com/open-box-lunch-delivery-service-13209.html");
                    startActivity(intent);
                }
                else if(selected_category.equals("Event Organizing")){

                    Intent intent = new Intent(getApplicationContext(), Discover_Activity.class)
                            .putExtra("type",1)
                            .putExtra("url","https://www.entrepreneur.com/article/37892");
                    startActivity(intent);
                }
            }
        });

    }
}
