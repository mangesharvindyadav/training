package com.example.root.mytest;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Context context;

    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    private PagerAdapter adapter;
    private TabLayout tabLayout;
    private android.support.v7.widget.Toolbar toolbar;
    private TextView title,n;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;

        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        toolbar=findViewById(R.id.toolbar);
        ImageView imageView=findViewById(R.id.nav_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

       // String name=;

        //Log.d("", "onCreate: "+name);

           n=findViewById(R.id.hed_name);

        title=findViewById(R.id.title);

//        n.setText(LoginActivity.preferences.getString("name",null));
        title.setText(getIntent().getStringExtra("namee"));


         NavigationView navigationView=(NavigationView)findViewById(R.id.navi);

         navigationView.setNavigationItemSelectedListener(this);

         viewPager=(ViewPager)findViewById(R.id.view_pager);



         navigationView.getMenu().getItem(0).setChecked(false);


         setupViewPager(viewPager);

          tabLayout=findViewById(R.id.tablay);
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
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawers();

        }
        else {

            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (R.id.logout==item.getItemId())
        {

           startActivity(new Intent(context,LoginActivity.class));
           finish();
            SharedPreferences.Editor editor=LoginActivity.preferences.edit();
          editor.putBoolean("flag",false).apply();
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {



        android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();

        switch (item.getItemId())
        {
            case R.id.item1:

                fragmentTransaction.add(R.id.my_content,new ProfileFragment(),null);
                fragmentTransaction.addToBackStack(null)
                 .commit();
                drawerLayout.closeDrawers();


                return true;

            case R.id.item2:

                Toast.makeText(context, "Nothing to Show", Toast.LENGTH_SHORT).show();
                return true;
        }
        return true;
    }


    private void setupViewPager(ViewPager viewPager)
    {
        adapter=new PagerAdapter(getSupportFragmentManager());
        adapter.add(new First(),"Tab 1");
        adapter.add(new Second(),"Tab 2");
        adapter.add(new Third(),"Tab 3");

        viewPager.setAdapter(adapter);
    }
}
