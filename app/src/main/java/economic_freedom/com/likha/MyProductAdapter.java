package economic_freedom.com.likha;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class MyProductAdapter extends RecyclerView.Adapter<MyProductAdapter.ViewHolder>  {

    ArrayList<My_product_pojo> product_list;
    private Context mContext;
    int previousPosition = 0;

    // Pass in the country array into the constructor
    public MyProductAdapter(ArrayList<My_product_pojo> product_list, Context context) {
        this.product_list = product_list;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_product_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

       switch (position){
           case 0 :
               holder.nImage.setImageResource(R.drawable.art_ione);
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
           case 8 :
               holder.nImage.setImageResource(R.drawable.jwl_one);
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
        return product_list == null ? 0 : product_list.size();
    }

    public My_product_pojo product(int position) {
        return product_list.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* Your holder should contain a member variable
     for any view that will be set as you render a row */
        TextView viewdetails;
        private ImageView nImage;
        Dialog dialog;


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

            dialog = new Dialog(mContext);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.my_product_custom);
            dialog.show();

           TextView name = (TextView) dialog.findViewById(R.id.json_name);
            TextView p_cat = (TextView) dialog.findViewById(R.id.json_cat);
            TextView p_name = (TextView) dialog.findViewById(R.id.json_pname);
            TextView cost = (TextView) dialog.findViewById(R.id.json_cost);
            TextView mat = (TextView) dialog.findViewById(R.id.json_mat);

            name.setText(product(getAdapterPosition()).getName());
            p_cat.setText(product(getAdapterPosition()).getCategory());
            p_name.setText(product(getAdapterPosition()).getProduct_name());
            cost.setText(product(getAdapterPosition()).getCost());
            mat.setText(product(getAdapterPosition()).getMaterial());

            Button close = (Button)dialog.findViewById(R.id.close);

            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

        }
    }
}