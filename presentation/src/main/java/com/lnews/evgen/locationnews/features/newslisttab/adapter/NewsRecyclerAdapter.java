package com.lnews.evgen.locationnews.features.newslisttab.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.lnews.evgen.domain.entities.Article;
import com.lnews.evgen.domain.entities.RootObject;
import com.lnews.evgen.locationnews.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.text.Format;
import java.text.SimpleDateFormat;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder> {
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private RootObject rootObject;

    public void setItems(RootObject rootObject) {
        this.rootObject = rootObject;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
            LayoutInflater.from(parent.getContext()).inflate(R.layout.item_newslist, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bind(rootObject.getArticles().get(position));
    }

    @Override
    public int getItemCount() {
        if (rootObject != null) {
            return rootObject.getArticles().size();
        } else {
            return 0;
        }
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        @SuppressLint("SimpleDateFormat")
        private final Format formatter = new SimpleDateFormat(DATE_FORMAT);

        @BindView(R.id.circleimage_newslist_item)
        CircleImageView imageView;
        @BindView(R.id.textview_newslist_item_date)
        TextView textViewDate;
        @BindView(R.id.textview_newslist_item_title)
        TextView textViewTitle;
        @BindView(R.id.textview_newslist_item_source)
        TextView textViewSource;

        NewsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(Article article) {
            textViewDate.setText(formatter.format(article.getPublishedAt()));
            textViewSource.setText(article.getAuthor());
            textViewTitle.setText(article.getTitle());
            Glide.with(itemView.getContext()).load(article.getUrlToImage()).into(imageView);
        }
    }
}
