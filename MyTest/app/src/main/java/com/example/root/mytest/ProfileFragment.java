package com.example.root.mytest;

import android.Manifest;
import android.app.AlertDialog;
import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by root on 16/1/18.
 */

public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";

    private ExpandableListView expandableListView;
    private ContactAdapter adapter;
    private ImageView m;


    private ArrayList<Name> details=new ArrayList<>();
    private ArrayList<Model> models=new ArrayList<>();

    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View view=LayoutInflater.from(getContext()).inflate(R.layout.profile,container,false);






         m=view.findViewById(R.id.mail_icon);
        // m.setOnClickListener(this);

        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","mangesh.yadav@inscripts.com", null));
                startActivity(emailIntent);
            }
        });



          expandableListView=view.findViewById(R.id.rv_contact);
          adapter=new ContactAdapter(getContext(),details);


          expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
              @Override
              public boolean onChildClick(ExpandableListView expandableListView, View view,
                                          int groupPosition, int childPosition, long l) {
                  Name name1=details.get(groupPosition);
                  Model model=name1.getModelArrayList().get(childPosition);

                  return false;
              }
          });


         expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
             @Override
             public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {

                 Name name2=details.get(i);

                 return false;
             }
         });

         expandableListView.setIndicatorBounds(0,expandableListView.getWidth()-10);

         models.add(new Model("566655","wfewfwfewf","Thane"));
         models.add(new Model("2453456","Yeiawfhuui","Punjab"));


         details.add(new Name("Mangesh",models));
         details.add(new Name("Ajay",models));

          expandableListView.setAdapter(adapter);


        return view;


    }



}
