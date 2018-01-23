package com.example.root.mytest;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class GallaryActivity extends AppCompatActivity {

    ImageView imageView;
    private static final String TAG = "GallaryActivity";
    private ArrayList<Uri> arrayList;
    Uri im;
    int po;

    private ViewPager pager;
    private GallaryPager adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallary);
        arrayList=new ArrayList<>();

        arrayList=getIntent().getParcelableArrayListExtra("mylist");
        po=getIntent().getIntExtra("position",0);


            pager=findViewById(R.id.view_pager);



       // Log.d(TAG, "onCreate: "+im.toString());

//        imageView=findViewById(R.id._image);

//       imageView.setImageURI(im);

       setupViewPager(pager);
       pager.setPageTransformer(true,new ZoomOutPageTransformer());

       pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
           @Override
           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

           }

           @Override
           public void onPageSelected(int position) {

           }

           @Override
           public void onPageScrollStateChanged(int state) {

           }
       });
    }


    private void setupViewPager(ViewPager viewPager) {
        adapter = new GallaryPager(getSupportFragmentManager(),arrayList);

        viewPager.setAdapter(adapter);

        viewPager.setCurrentItem(po);

    }
}
