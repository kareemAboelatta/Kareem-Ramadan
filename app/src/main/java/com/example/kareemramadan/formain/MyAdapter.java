package com.example.kareemramadan.formain;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kareemramadan.book.BooksActivity;
import com.example.kareemramadan.sebha.SebhaContentActivity;
import com.example.kareemramadan.forquran.QuranActivity;
import com.example.kareemramadan.R;
import com.example.kareemramadan.forsalatimes.SalatTimesActivity;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> implements Filterable {

    Context context;
    ArrayList<Model> models,filterList;
    CustomFilter filter;

    public MyAdapter(Context context, ArrayList<Model> models) {
        this.context = context;
        this.models = models;
        this.filterList=models;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_main_iem,null);
        MyHolder holder =new MyHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.txtName.setText(models.get(position).getName());
        holder.img.setImageResource(models.get(position).getImg());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //General
                if (models.get(pos).getName().equals("القران الكريم")) {

                    //start GeneralActivity
                    context.startActivity(new Intent(context, QuranActivity.class));
                }

                //Device Id
                else   if (models.get(pos).getName().equals("مواقيت الصلاة")) {
                    //start DeviceIDActivity
                    Intent intent =new Intent(context, SalatTimesActivity.class);
                    context.startActivity(intent);

                }
                //Display
                else   if (models.get(pos).getName().equals("السبحة الالكترونية")) {
                    Intent intent =new Intent(context, SebhaContentActivity.class);
                    context.startActivity(intent);
                }

                //Display
                else   if (models.get(pos).getName().equals("كتب اسلامية")) {
                    Intent intent =new Intent(context, BooksActivity.class);
                    context.startActivity(intent);
                }

                else   if (models.get(pos).getName().equals("تفسير")) {
                    Toast.makeText(context, "Not worked yet", Toast.LENGTH_SHORT).show();
                }
                else   if (models.get(pos).getName().equals("المسلم في رمضان")) {
                    Toast.makeText(context, "Not worked yet", Toast.LENGTH_SHORT).show();

                }

                else   if (models.get(pos).getName().equals("etc..")) {
                    Toast.makeText(context, "Not worked yet", Toast.LENGTH_SHORT).show();

                }
                /*
                */
                // ....

            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }



    //return filter obj
    @Override
    public Filter getFilter() {
        if (filter==null){
            filter  =new CustomFilter(filterList,this);
        }
        return filter;
    }


    class MyHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        ImageView img ;
        TextView txtName ;
        ItemClickListener itemClickListener;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.modelImage);
            txtName=itemView.findViewById(R.id.modelTxt);
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
