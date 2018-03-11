package economic_freedom.com.likha;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.ArrayList;


public class YoutubeAdapter extends RecyclerView.Adapter<YoutubeAdapter.ViewHolder> {

    ArrayList<Youtube_pojo> youtube_list;
    private Context mContext;
    int previousPosition = 0;
    private ItemClickListener clickListener;

    // Pass in the country array into the constructor
    public YoutubeAdapter(ArrayList<Youtube_pojo> youtube_list, Context context) {
        this.youtube_list = youtube_list;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.youtube_row_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.title.setText(youtube_list.get(position).getTitle());

        final YouTubeThumbnailLoader.OnThumbnailLoadedListener  onThumbnailLoadedListener = new YouTubeThumbnailLoader.OnThumbnailLoadedListener(){
            @Override
            public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

            }

            @Override
            public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                youTubeThumbnailView.setVisibility(View.VISIBLE);
            }
        };

        holder.youTubeThumbnailView.initialize("AIzaSyCizOZGIIuBZRahBwT8_Ayp3tXWZPuLDmE", new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {


                youTubeThumbnailLoader.setVideo(youtube_list.get(position).getId());
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(onThumbnailLoadedListener);
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                //write something for failure
            }
        });


        //Getting the position of items in recyclerview
        if (position > previousPosition) { //we are scrolling DOWN

            AnimationUtil.animate(holder, true);

        } else {  //we are scrolling UP

            AnimationUtil.animate(holder, false);
        }

        previousPosition = position;

    }

    @Override
    public int getItemCount() {
        return youtube_list == null ? 0 : youtube_list.size();
    }

    public Youtube_pojo youtube(int position) {
        return youtube_list.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* Your holder should contain a member variable
     for any view that will be set as you render a row */
        TextView title;
        YouTubeThumbnailView youTubeThumbnailView;


        public ViewHolder(View itemView) {

            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            youTubeThumbnailView = (YouTubeThumbnailView) itemView.findViewById(R.id.imageView_thumbnail);
            title = (TextView) itemView.findViewById(R.id.textView_title);

            itemView.setTag(itemView);
            itemView.setOnClickListener(this);// bind the listener
        }

        @Override
        public void onClick(View view) {

            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());

            String y_url = youtube(getAdapterPosition()).getUrl();

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(y_url));
            mContext.startActivity(intent);
        }
    }
}