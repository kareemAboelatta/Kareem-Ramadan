package com.example.kareemramadan.sebha;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kareemramadan.R;

import java.util.List;

public class AdapterAzkar extends RecyclerView.Adapter<AdapterAzkar.MyHolder> {


    List<ModelAzakar> list;
    Context context;
    public AdapterAzkar(List<ModelAzakar> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_iem_sora,parent,false);


        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        final ModelAzakar azakar=list.get(position);
        final int count= azakar.count;
        final String text= azakar.text;
        holder.text.setText(azakar.text);
        holder.count.setText(azakar.count+"");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context, SebhaActivity.class);
                intent.putExtra("count",count);
                intent.putExtra("text",text);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
       return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView text,count;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            text=itemView.findViewById(R.id.name);
            count=itemView.findViewById(R.id.page);
        }

    }
}
