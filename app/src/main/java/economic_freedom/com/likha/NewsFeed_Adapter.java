package economic_freedom.com.likha;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Hashtable;

public class NewsFeed_Adapter extends RecyclerView.Adapter<NewsFeed_Adapter.ViewHolder>  {

    ArrayList<NewsFeed_pojo> newsfeed;
    private Context mContext;
    int previousPosition = 0;

    // Pass in the country array into the constructor
    public NewsFeed_Adapter(ArrayList<NewsFeed_pojo> newsfeed, Context context) {
        this.newsfeed = newsfeed;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsfeed_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.name.setText(newsfeed.get(position).getName());
        holder.cat.setText(newsfeed.get(position).getCategory());
        holder.pname.setText(newsfeed.get(position).getProduct_name());
        holder.cost.setText(newsfeed.get(position).getCost());
        holder.mat.setText(newsfeed.get(position).getMaterials());


        if(position == 0){
            holder.nImage.setImageResource(R.drawable.art_ione);
        }
        if(position == 1){
            holder.nImage.setImageResource(R.drawable.candle_img);
        }
        if(position == 2){
            holder.nImage.setImageResource(R.drawable.dev_two);
        }
        if(position == 3){
            holder.nImage.setImageResource(R.drawable.cust_ione);
        }
        if(position == 4){
            holder.nImage.setImageResource(R.drawable.emb_ione);
        }
        if(position == 5){
            holder.nImage.setImageResource(R.drawable.e_teach_two);
        }
        if(position == 6){
            holder.nImage.setImageResource(R.drawable.jwl_one);
        }
        if(position == 7){
            holder.nImage.setImageResource(R.drawable.lun_two);
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
        return newsfeed == null ? 0 : newsfeed.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* Your holder should contain a member variable
     for any view that will be set as you render a row */
        TextView name,cat,pname,cost,mat;
        private ImageView nImage;


        /*constructor that accepts the entire item row
             and does the view lookups to find each subview */
        public ViewHolder(View itemView) {

            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nImage = (ImageView) itemView.findViewById(R.id.thumbnail);
            name = (TextView) itemView.findViewById(R.id.name);
            cat = (TextView) itemView.findViewById(R.id.cat);
            pname = (TextView) itemView.findViewById(R.id.product);
            cost = (TextView) itemView.findViewById(R.id.cost);
            mat = (TextView) itemView.findViewById(R.id.material);

            itemView.setTag(itemView);
            itemView.setOnClickListener(this);// bind the listener
        }


        @Override
        public void onClick(View view) {

        }

    }

}



