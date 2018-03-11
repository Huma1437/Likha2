package economic_freedom.com.likha;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dell on 3/1/2018.
 */

class BuyProduct_Adapter extends RecyclerView.Adapter<BuyProduct_Adapter.ViewHolder>  {

    ArrayList<Buy_Product_pojo> buyproduct;
    private Context mContext;
    int previousPosition = 0;

    // Pass in the country array into the constructor
    public BuyProduct_Adapter(ArrayList<Buy_Product_pojo> buyproduct, Context context) {
        this.buyproduct = buyproduct;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buy_product_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        switch (position){
            case 0 :
                holder.nImage.setImageResource(R.drawable.art_itwo);

                break;
            case 1 :
                holder.nImage.setImageResource(R.drawable.candle_img);
                break;
            case 2 :
                holder.nImage.setImageResource(R.drawable.jwl_ione);
                break;

            case 3 :
                holder.nImage.setImageResource(R.drawable.emb_ione);
                break;
            case 4 :
                holder.nImage.setImageResource(R.drawable.custom_img);
                break;
            case 5 :
                holder.nImage.setImageResource(R.drawable.acn_ione);
                break;

            case 6 :
                holder.nImage.setImageResource(R.drawable.jwl_two);
                break;
            case 7 :
                holder.nImage.setImageResource(R.drawable.cust_ione);
                break;
        }
        //Getting the position of items in recyclerview
        if(position > previousPosition){ //we are scrolling DOWN

            AnimationUtil.animate(holder, true);

        }else{  //we are scrolling UP

            AnimationUtil.animate(holder, false);
        }

        previousPosition = position;
    }

    @Override
    public int getItemCount() {
        return buyproduct == null ? 0 : buyproduct.size();
    }

    public Buy_Product_pojo buy(int position) {
        return buyproduct.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* Your holder should contain a member variable
     for any view that will be set as you render a row */
        TextView viewdetails;
        private ImageView nImage;


        /*constructor that accepts the entire item row
             and does the view lookups to find each subview */
        public ViewHolder(View itemView) {

            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nImage = (ImageView) itemView.findViewById(R.id.thumbnail);
            viewdetails = (TextView) itemView.findViewById(R.id.View);

            itemView.setTag(itemView);
            itemView.setOnClickListener(this);// bind the listener
        }

        @Override
        public void onClick(View view) {

            String name = buy(getAdapterPosition()).getName();
            String cat = buy(getAdapterPosition()).getCategory();
            String prod_name = buy(getAdapterPosition()).getProduct_name();
            String cost = buy(getAdapterPosition()).getCost();
            String mat = buy(getAdapterPosition()).getMaterials();

            Intent intent = new Intent(mContext,Buy_details.class);
            intent.putExtra("Person_Name" , name);
            intent.putExtra("Category",cat);
            intent.putExtra("Product_Name",prod_name);
            intent.putExtra("Cost",cost);
            intent.putExtra("Materials",mat);
            mContext.startActivity(intent);

        }

    }
}