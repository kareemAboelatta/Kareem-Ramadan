package com.example.kareemramadan.sebha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.kareemramadan.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SebhaContentActivity extends AppCompatActivity {

    AdapterAzkar adapterAzkar;
    RecyclerView recyclerView;
    List<ModelAzakar> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sebha_content);

        recyclerView=findViewById(R.id.rec_Azakar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        list=new ArrayList<>();
        list.add(new ModelAzakar("سبحان الله",33));
       list.add(new ModelAzakar("استغفر اللَه",33));
        list.add(new ModelAzakar("الله اكبر",33));
        list.add(new ModelAzakar("لا اله الا الله ",33));
        list.add(new ModelAzakar("لا إله إلا الله وحده لا شريك له , له الملك و له الحمد و هو على كل شئ قدير",9));
        list.add(new ModelAzakar("قول هو الله احد",3));
        list.add(new ModelAzakar("سبحان الله",33));
        list.add(new ModelAzakar("سبحان الله و بحمده",100));
        list.add(new ModelAzakar(" الحمد لله ",33));



        adapterAzkar=new AdapterAzkar(list,this);
        recyclerView.setAdapter(adapterAzkar);





    }


}