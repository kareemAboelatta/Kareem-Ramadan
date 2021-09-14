package com.example.kareemramadan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.kareemramadan.formain.Model;
import com.example.kareemramadan.formain.MyAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        initCollapsingToolBar();

        //recycleView
        mRecyclerView=findViewById(R.id.myRecycler);
        //set its properties
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //adapter
        myAdapter=new MyAdapter(this,getModel());
        mRecyclerView.setAdapter(myAdapter);



    }



    private ArrayList<Model>getModel(){
        ArrayList<Model> Models=new ArrayList<>();
         Model p =new Model();

        p=new Model();
        p.setName("القران الكريم");
        p.setImg(R.drawable.qr2an);
        Models.add(p);

        p=new Model();
        p.setName("مواقيت الصلاة");
        p.setImg(R.drawable.times);
        Models.add(p);

        p=new Model();
        p.setName("كتب اسلامية");
        p.setImg(R.drawable.books);
        Models.add(p);



        p=new Model();
        p.setName("السبحة الالكترونية");
        p.setImg(R.drawable.misbaha);
        Models.add(p);


        p=new Model();
        p.setName("تفسير");
        p.setImg(R.drawable.interpretation);
        Models.add(p);

        p=new Model();
        p.setName("المسلم في رمضان");
        p.setImg(R.drawable.muslim);
        Models.add(p);

        p=new Model();
        p.setName("etc..");
        p.setImg(R.drawable.muslim);
        Models.add(p);



        return Models;
    }
    private void initCollapsingToolBar() {
        CollapsingToolbarLayout collapsingToolbarLayout=findViewById(R.id.collsping_toolbar);
        collapsingToolbarLayout.setTitle("");
        AppBarLayout appBarLayout=findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);


     //   appBarLayout.setBackground(getDrawable(R.drawable.bcc));
        collapsingToolbarLayout.setTitle(getString(R.string.side_ic));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        MenuItem item=menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //this function is called when search Button in keyboard is pressed
                myAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //this function calls whenever you type in searchView
                myAdapter.getFilter().filter(newText);
                return false;
            }
        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id =item.getItemId();
        if (id == R.id.action_setting) {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }



}