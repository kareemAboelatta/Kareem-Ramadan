package com.example.kareemramadan.sebha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kareemramadan.R;

import es.dmoral.toasty.Toasty;

public class SebhaActivity extends AppCompatActivity {


    Button btn_add_sebha;
    TextView text_sebha_count ,txt_sebha;
    int count=0;
    int county;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sebha);
        if (Build.VERSION.SDK_INT> Build.VERSION_CODES.KITKAT)
        {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimary));
        }

        btn_add_sebha=findViewById(R.id.btn_add_sebha);
        text_sebha_count=findViewById(R.id.text_sebha_count);
        txt_sebha=findViewById(R.id.txt_sebha);
        text_sebha_count.setText(count+"");

        Intent intent = getIntent();
        county = intent.getIntExtra("count",0);
        text = intent.getStringExtra("text");

        txt_sebha.setText(text);

        btn_add_sebha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                text_sebha_count.setText(count+"");


                if (count==county){
                    Toasty.success(SebhaActivity.this,"Done ..", Toast.LENGTH_SHORT, true).show();
                    btn_add_sebha.setVisibility(View.GONE);
                }


            }
        });


    }
}