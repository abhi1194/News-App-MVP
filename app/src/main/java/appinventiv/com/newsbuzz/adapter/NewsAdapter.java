package appinventiv.com.newsbuzz.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import appinventiv.com.newsbuzz.R;
import appinventiv.com.newsbuzz.interfaces.OnRowItemClickListener;
import appinventiv.com.newsbuzz.model.home.Source;
import butterknife.BindView;
import butterknife.ButterKnife;



public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private ArrayList<Source> mList;
    private OnRowItemClickListener onRowItemClickListener;

    public NewsAdapter(ArrayList<Source> list,OnRowItemClickListener onRowItemClickListener) {
        this.mList = list;
        this.onRowItemClickListener = onRowItemClickListener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_news_source, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.tvTitle.setText(mList.get(position).getName());
        holder.tvDescription.setText(mList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_description)
        TextView tvDescription;

        NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRowItemClickListener.onRowItemClicked(getAdapterPosition());
                }
            });
        }
    }
}
