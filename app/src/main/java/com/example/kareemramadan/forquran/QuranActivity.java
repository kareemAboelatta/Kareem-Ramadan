package com.example.kareemramadan.forquran;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.kareemramadan.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.dmoral.toasty.Toasty;

public class QuranActivity extends AppCompatActivity {

    private View parent_view;
    private View back_drop;
    private boolean rotate = false;
    private View lyt_put_mark;
    private View lyt_mark;
    private View lyt_content;
    private  View lyt_dark_mood;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;

    String mark="mark";
    int page=0;
    int currentPage=0;

    PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran);

        Intent intent = getIntent();
        page = intent.getIntExtra("page",0);

        pdfView =findViewById(R.id.pdfView);
        pdfView.fromAsset("quran.pdf")
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(page)
                .enableAnnotationRendering(false)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true)
                .spacing(0)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load();

        parent_view = findViewById(android.R.id.content);
        back_drop = findViewById(R.id.back_drop);

            final FloatingActionButton fab_put_mark = findViewById(R.id.fab_put_mark);
            final FloatingActionButton fab_mark = findViewById(R.id.fab_mark);
            final FloatingActionButton fab_contents = findViewById(R.id.fab_contents);
            final FloatingActionButton fab_add = findViewById(R.id.fab_add);
            lyt_put_mark = findViewById(R.id.lyt_put_mark);
            lyt_mark = findViewById(R.id.lyt_mark);
        lyt_content = findViewById(R.id.lyt_content);
        lyt_dark_mood = findViewById(R.id.lyt_dark_mood);

        initShowOut(lyt_put_mark);
        initShowOut(lyt_mark);
        initShowOut(lyt_content);
        initShowOut(lyt_dark_mood);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);


        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggle_dark_mood);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    currentPage=pdfView.getCurrentPage();

                    pdfView.fromAsset("quran.pdf")
                            .enableSwipe(true)
                            .swipeHorizontal(false)
                            .enableDoubletap(true)
                            .defaultPage(currentPage)
                            .nightMode(true)
                            .enableAnnotationRendering(false)
                            .password(null)
                            .scrollHandle(null)
                            .enableAntialiasing(true)
                            .spacing(0)
                            .pageFitPolicy(FitPolicy.WIDTH)
                            .load();




                } else {
                    // The toggle is disabled

                    currentPage=pdfView.getCurrentPage();

                    pdfView.fromAsset("quran.pdf")
                            .enableSwipe(true)
                            .swipeHorizontal(false)
                            .enableDoubletap(true)
                            .defaultPage(currentPage)
                            .nightMode(false)
                            .enableAnnotationRendering(false)
                            .password(null)
                            .scrollHandle(null)
                            .enableAntialiasing(true)
                            .spacing(0)
                            .pageFitPolicy(FitPolicy.WIDTH)
                            .load();



                }

            }
        });



        back_drop.setVisibility(View.GONE);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFabMode(v);
            }
        });

        back_drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFabMode(fab_add);
            }
        });

        fab_put_mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putInt(mark, pdfView.getCurrentPage());
                editor.commit();
                Toasty.success(QuranActivity.this,"تم وضع العلامة", Toast.LENGTH_SHORT, true).show();
                toggleFabMode(fab_add);
            }
        });

        fab_mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toasty.warning(QuranActivity.this,"الرجوع الي اخر علامة...", Toast.LENGTH_SHORT, true).show();

                pdfView.fromAsset("quran.pdf")
                        .enableSwipe(true)
                        .swipeHorizontal(false)
                        .enableDoubletap(true)
                        .defaultPage(sharedpreferences.getInt(mark,0))
                        .enableAnnotationRendering(false)
                        .password(null)
                        .scrollHandle(null)
                        .enableAntialiasing(true)
                        .spacing(0)
                        .pageFitPolicy(FitPolicy.WIDTH)
                        .load();

                toggleFabMode(fab_add);

            }
        });

        fab_contents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuranActivity.this,ContentActivity.class));
                toggleFabMode(fab_add);

            }
        });



    }



    private void toggleFabMode(View v) {
        rotate = rotateFab(v, !rotate);
        if (rotate) {
            showIn(lyt_put_mark);
            showIn(lyt_mark);
            showIn(lyt_content);
            showIn(lyt_dark_mood);
            back_drop.setVisibility(View.VISIBLE);
        } else {
            showOut(lyt_put_mark);
            showOut(lyt_mark);
            showOut(lyt_content);
            showOut(lyt_dark_mood);
            back_drop.setVisibility(View.GONE);
        }
    }

    public static void initShowOut(final View v) {
        v.setVisibility(View.GONE);
        v.setTranslationY(v.getHeight());
        v.setAlpha(0f);
    }

    public static boolean rotateFab(final View v, boolean rotate) {
        v.animate().setDuration(1000)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                })
                .rotation(rotate ? 175f : 0f);
        return rotate;
    }

    public static void showIn(final View v) {
        v.setVisibility(View.VISIBLE);
        v.setAlpha(0f);
        v.setTranslationY(v.getHeight());
        v.animate()
                .setDuration(900)
                .translationY(0)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                })
                .alpha(1f)
                .start();
    }

    public static void showOut(final View v) {
        v.setVisibility(View.VISIBLE);
        v.setAlpha(1f);
        v.setTranslationY(0);
        v.animate()
                .setDuration(200)
                .translationY(v.getHeight())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        v.setVisibility(View.GONE);
                        super.onAnimationEnd(animation);
                    }
                }).alpha(0f)
                .start();
    }
}