package appinventiv.com.newsbuzz.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import appinventiv.com.newsbuzz.R;
import appinventiv.com.newsbuzz.model.home.headline.Headlines;
import butterknife.BindView;
import butterknife.ButterKnife;


public class HeadlineAdapter extends RecyclerView.Adapter<HeadlineAdapter.HeadlineViewHolder> {


    private ArrayList<Headlines> mHeadlines;
    private View view;

    public HeadlineAdapter(ArrayList<Headlines> list) {
        this.mHeadlines = list;
    }

    @NonNull
    @Override
    public HeadlineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_headlines, parent, false);
        return new HeadlineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeadlineViewHolder holder, int position) {
        holder.tvHeadlineTitle.setText(mHeadlines.get(position).getTitle());
        holder.tvHeadlineDesc.setText(mHeadlines.get(position).getDescription());
        Glide.with(view.getContext())
                .applyDefaultRequestOptions(new RequestOptions().error(R.drawable.news_icon))
                .load(mHeadlines.get(position).getUrlToImage()).into(holder.ivImage);
        holder.tvTime.setText(getTime(mHeadlines.get(position).getPublishedAt()));


    }

    @Override
    public int getItemCount() {
        return mHeadlines.size();
    }

    /**
     * This method is used to return the time of the headline
     * @param time time of the headline
     * @return time
     */
    private String getTime(String time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());

        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a",Locale.getDefault());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date dt = null;
        try {
            dt = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(dt!=null){
            return timeFormat.format(dt);
        }

        return "no time";
    }

    class HeadlineViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_image)
        AppCompatImageView ivImage;
        @BindView(R.id.tv_headline_title)
        TextView tvHeadlineTitle;
        @BindView(R.id.tv_headline_desc)
        TextView tvHeadlineDesc;
        @BindView(R.id.tv_time)
        TextView tvTime;

        HeadlineViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
