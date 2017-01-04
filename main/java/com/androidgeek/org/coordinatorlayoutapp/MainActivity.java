package com.androidgeek.org.coordinatorlayoutapp;

import android.animation.Animator;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private CoordinatorLayout coordinatorLayout;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_main);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Coordinator Layout APP");
        collapsingToolbarLayout.setTitleEnabled(false);

        fab.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        revealEffect();
                    }
                }
                ,300);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(coordinatorLayout, "Out of the way fab,year!!", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public void revealEffect(){
        if(Build.VERSION.SDK_INT > 20){
            int cx = fab.getMeasuredWidth()/2;
            int cy = fab.getMeasuredHeight()/2;
            int radius = Math.max(fab.getWidth(),fab.getHeight());
            Animator animator = ViewAnimationUtils.createCircularReveal(fab,cx,cy,0,radius);
            animator.setDuration(1000);
            fab.setVisibility(View.VISIBLE);
            animator.start();
        }
    }
}
