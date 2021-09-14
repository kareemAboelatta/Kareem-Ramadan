package com.example.kareemramadan.book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.example.kareemramadan.R;

import java.util.ArrayList;
import java.util.List;


public class BooksActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BookAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        recyclerView=findViewById(R.id.rec_books);





        //set its properties
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //adapter
        adapter=new BookAdapter(getModel(),this);

        recyclerView.setAdapter(adapter);

    }

    private List<Book> getModel(){
        List<Book> books=new ArrayList<>();

        Book b;

        b=new Book(R.drawable.qr2an,"القرأن",569);
        b.setBookSource("quran.pdf");
        b.setRating(5f);
        books.add(b);

        b=new Book(R.drawable.tafsir_image,"التفسير",557);
        b.setBookSource("tafsir.pdf");
        b.setRating(4f);
        books.add(b);

        b=new Book(R.drawable.english_quran,"القرأن انجليزي&عربي",668);
        b.setBookSource("quran_arabic_english.pdf");
        b.setRating(4.5f);
        books.add(b);

        b=new Book(R.drawable.khotab_image,"خطب الرسول",133);
        b.setBookSource("khotab_el_rasol.pdf");
        books.add(b);
        b.setRating(5f);


        b=new Book(R.drawable.ser_image,"سر تأخر العرب و المسلمين",133);
        b.setBookSource("secret_arab.pdf");
        b.setRating(4.5f);
        books.add(b);

        b=new Book(R.drawable.qr2an,"القرأن",569);
        b.setBookSource("quran.pdf");
        b.setRating(5f);
        books.add(b);

        b=new Book(R.drawable.tafsir_image,"التفسير",557);
        b.setBookSource("tafsir.pdf");
        b.setRating(4f);
        books.add(b);

        b=new Book(R.drawable.english_quran,"القرأن انجليزي&عربي",668);
        b.setBookSource("quran_arabic_english.pdf");
        b.setRating(4.5f);
        books.add(b);

        b=new Book(R.drawable.khotab_image,"خطب الرسول",133);
        b.setBookSource("khotab_el_rasol.pdf");
        books.add(b);
        b.setRating(5f);


        b=new Book(R.drawable.ser_image,"سر تأخر العرب و المسلمين",133);
        b.setBookSource("secret_arab.pdf");
        b.setRating(4.5f);
        books.add(b);






        return books;
    }
}