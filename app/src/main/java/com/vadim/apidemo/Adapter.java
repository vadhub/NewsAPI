package com.vadim.apidemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private List<Article> articles;

    private OnItemClickListenerNews onItemClickListenerNews;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mini_news, parent, false);
        return new MyViewHolder(view, onItemClickListenerNews);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get().load(articles.get(position).getUrlToImage()).into(holder.imageView);
        holder.title.setText(articles.get(position).getTitle());
        holder.description.setText(articles.get(position).getContent());

    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void setOnItemClickListenerNews(OnItemClickListenerNews onItemClickListenerNews) {
        this.onItemClickListenerNews = onItemClickListenerNews;
    }

    public interface OnItemClickListenerNews{
        void onClickItem(View view, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView title;
        private TextView description;
        private OnItemClickListenerNews onItemClickListenerNews;
        public MyViewHolder(@NonNull View itemView, OnItemClickListenerNews onItemClickListenerNews) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.img_mini);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            this.onItemClickListenerNews = onItemClickListenerNews;

            itemView.setOnClickListener(v -> {
                if(onItemClickListenerNews!=null)
                    onItemClickListenerNews.onClickItem(v,getAdapterPosition());
            });
        }

    }
}
