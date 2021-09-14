package com.example.kareemramadan.forquran;

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

public class AdapterSora extends RecyclerView.Adapter<AdapterSora.MyHolder> {

    List<ModelSora> list;
    Context context;

    public AdapterSora(List<ModelSora> list, Context context) {
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
        final ModelSora sora=list.get(position);
         final int page= sora.page;
        holder.name.setText(sora.name);
        holder.page.setText(sora.page+"");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context, QuranActivity.class);
                intent.putExtra("page",page);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{

        TextView name,page;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            page=itemView.findViewById(R.id.page);
        }
    }

}
