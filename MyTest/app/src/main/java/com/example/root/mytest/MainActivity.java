package com.example.root.mytest;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int CONTACT_PERMISSION = 34;
    private static final int CONTACT_CODE = 52;
    private Context context;

    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    private PagerAdapter adapter;
    private TabLayout tabLayout;
    private android.support.v7.widget.Toolbar toolbar;
    private TextView title, n;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
            drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
            toolbar = findViewById(R.id.toolbar);
            ImageView imageView = findViewById(R.id.nav_menu);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("");

            // String name=;

            //Log.d("", "onCreate: "+name);

            n = findViewById(R.id.hed_name);

            title = findViewById(R.id.title);

//        n.setText(LoginActivity.preferences.getString("name",null));
            title.setText(getIntent().getStringExtra("my"));

            NavigationView navigationView = (NavigationView) findViewById(R.id.navi);

            navigationView.setNavigationItemSelectedListener(this);

            viewPager = (ViewPager) findViewById(R.id.view_pager);


            navigationView.getMenu().getItem(0).setChecked(false);


            setupViewPager(viewPager);

            tabLayout = findViewById(R.id.tablay);
            getSupportActionBar().setElevation(0);
            tabLayout.setupWithViewPager(viewPager);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    drawerLayout.openDrawer(GravityCompat.START);

                }
            });


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
        {
            if (checkPermission())
            {

            }
            else {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                SharedPreferences.Editor editor = LoginActivity.preferences.edit();
                editor.putBoolean("flag", false).apply();
                finish();
            }
        }
    }

    public boolean checkPermission()
    {

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_CONTACTS)) {
                android.support.v7.app.AlertDialog.Builder alertBuilder = new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
                alertBuilder.setCancelable(true);
                alertBuilder.setTitle("Permission necessary");
                alertBuilder.setMessage("READ Contacts permission is necessary !!");
                alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, CONTACT_CODE);
                    }
                });
                android.support.v7.app.AlertDialog alert = alertBuilder.create();
                alert.show();
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, CONTACT_CODE);
            }
            return false;
        } else {
            return true;
        }



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode)
        {
            case CONTACT_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent=new Intent(MainActivity.this,MainActivity.class);

                    startActivity(intent);
                    finish();
                } else {
                    //code for deny
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                    SharedPreferences.Editor editor = LoginActivity.preferences.edit();
                    editor.putBoolean("flag", false).apply();
                    finish();
                }
        }
    }


    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();

        } else {

            super.onBackPressed();
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (R.id.logout == item.getItemId()) {

            startActivity(new Intent(context, LoginActivity.class));
            finish();
            SharedPreferences.Editor editor = LoginActivity.preferences.edit();
            editor.putBoolean("flag", false).apply();
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        switch (item.getItemId()) {
            case R.id.item1:

                fragmentTransaction.add(R.id.my_content, new ProfileFragment(), null);
                fragmentTransaction.addToBackStack(null).commit();
                drawerLayout.closeDrawers();


                return true;

            case R.id.item2:

                fragmentTransaction.add(R.id.my_content, new GallaryFragment(), null);
                fragmentTransaction.addToBackStack(null).commit();
                drawerLayout.closeDrawers();
                return true;
        }
        return true;
    }


    private void setupViewPager(ViewPager viewPager) {
        adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.add(new First(), "Tab 1");
        adapter.add(new Second(), "Tab 2");
        adapter.add(new Third(), "Tab 3");

        viewPager.setAdapter(adapter);
    }


}
