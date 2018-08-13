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
import com.lnews.evgen.locationnews.utils.GlideApp;
import de.hdodenhof.circleimageview.CircleImageView;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder> {
    private List<Article> articles;
    private final OnItemClickListener listener;

    public NewsRecyclerAdapter(List<Article> articles, OnItemClickListener listener) {
        this.articles = articles;
        this.listener = listener;
    }

    public void setItems(List<Article> articles) {
        this.articles = articles;
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
        holder.bind(articles.get(position), listener);
    }

    @Override
    public int getItemCount() {
        if (articles != null) {
            return articles.size();
        } else {
            return 0;
        }
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        @SuppressLint("SimpleDateFormat")

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

        public void bind(Article article, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(article);
                }
            });
            textViewDate.setText(article.getPublishedAt());
            textViewSource.setText(article.getAuthor());
            textViewTitle.setText(article.getTitle());
            GlideApp.with(itemView.getContext())
                .load(article.getUrlToImage())
                .error(R.drawable.ic_broken_image)
                .into(imageView);
        }
    }
}
