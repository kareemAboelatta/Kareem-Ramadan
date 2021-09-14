package com.example.kareemramadan.book;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kareemramadan.R;
import com.example.kareemramadan.formain.ItemClickListener;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyHolder> {

    List<Book> models ;
    Context context ;

    public BookAdapter(List<Book> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book,null);


        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {

        holder.book_image.setImageResource(models.get(position).image);
        holder.book_name.setText(models.get(position).name);
        holder.book_page.setText(models.get(position).page +" page");
        holder.book_rating.setRating(models.get(position).getRating());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

                Intent intent=new Intent(context,PDFActivity.class);
                intent.putExtra("bookSource",models.get(position).getBookSource());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class MyHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        ImageView book_image ;
        TextView book_name ;
        TextView book_page;
        RatingBar book_rating;
        ItemClickListener itemClickListener;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            book_image=itemView.findViewById(R.id.book_image);
            book_name=itemView.findViewById(R.id.book_name);
            book_page=itemView.findViewById(R.id.book_page);
            book_rating=itemView.findViewById(R.id.book_rating);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            this.itemClickListener.onItemClick(v,getLayoutPosition());
        }

        public void setItemClickListener(ItemClickListener ic){
            this.itemClickListener=ic;
        }
    }
}
