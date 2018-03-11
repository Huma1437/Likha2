package economic_freedom.com.likha;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.List;

public class MainScreenAdapter extends RecyclerView.Adapter<MainScreenAdapter.MyViewHolder> {

    private Context mContext;
    private List<Card> albumList;
    String Title;
    String Category;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }


    public MainScreenAdapter(Context mContext, List<Card> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_card, parent, false);
        Category = Splash_Screen.sh.getString("Language", null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Card card = albumList.get(position);
        holder.title.setText(card.getName());
        // loading album cover using Glide library
        Glide.with(mContext).load(card.getThumbnail()).into(holder.thumbnail);

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Title = albumList.get(position).getName();

                Log.e("ONCLICK","?????????????????????//" +Title);

                if(Title == "Inspiration"){
                    Intent intent = new Intent(mContext,Inspiration.class);
                    mContext.startActivity(intent);
                }

                if(Title == "Discover"){
                    Intent intent = new Intent(mContext,Discover_Activity.class);
                    mContext.startActivity(intent);
                }

                if(Title == "Startup"){
                    if(Category == null){
                        Intent intent = new Intent(mContext,Select_category.class);
                        mContext.startActivity(intent);
                    }else {
                        Intent intent = new Intent(mContext,Start_up_main.class);
                        mContext.startActivity(intent);
                    }
                }

                if(Title == "Buy"){
                    Intent intent = new Intent(mContext,Buy_Product.class);
                    mContext.startActivity(intent);
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}