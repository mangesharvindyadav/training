package com.example.root.mytest;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class GallaryFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>,AdapterView.OnItemClickListener {


    private static final int IMAGE_CODE = 525;

    private ArrayList<Uri> uriArrayList;

    public GallaryFragment() {
        // Required empty public constructor
    }


    private final static int[] IDS={R.id.image_view};



    private final static String[] COLOUMNs= {
            MediaStore.Images.Thumbnails.DATA,
            MediaStore.Images.Thumbnails._ID


    };



    private static final String[] PROJECTION =new String[]

            {
                    MediaStore.Images.Thumbnails._ID,
                    MediaStore.Images.Thumbnails.DATA

            };

    private GridView gridView;
    private SimpleCursorAdapter cursorAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        if (checkPermission())
        {
            View view= inflater.inflate(R.layout.fragment_gallary, container, false);

            gridView=(GridView) view.findViewById(R.id.gallary);


            cursorAdapter=new SimpleCursorAdapter(getActivity(),R.layout.imageview,null,
                    COLOUMNs,IDS);


            gridView.setAdapter(cursorAdapter);

            gridView.setOnItemClickListener(this);

            getLoaderManager().initLoader(425,null,this);

            return view;
        }
        return null;

    }


    public boolean checkPermission()
    {


        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
                alertBuilder.setCancelable(true);
                alertBuilder.setTitle("Permission necessary");
                alertBuilder.setMessage("READ Contacts permission is necessary !!");
                alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
                    }
                });
                AlertDialog alert = alertBuilder.create();
                alert.show();
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, IMAGE_CODE);
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
            case IMAGE_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    new GallaryFragment().onCreateLoader(425,null);

                } else {
                    //code for deny

                }



        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {


        return new CursorLoader(getContext(),MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,
                PROJECTION,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        cursorAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        cursorAdapter.swapCursor(null);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Cursor cursor = cursorAdapter.getCursor();

        uriArrayList=new ArrayList<>();

        while (cursor.moveToNext()) {

            String _id = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media._ID));

            Uri uri = Uri.withAppendedPath(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, _id);

            uriArrayList.add(uri);
        }



        Intent intent=new Intent(getContext(),GallaryActivity.class);
        intent.putExtra("position",position);
        intent.putParcelableArrayListExtra("mylist",uriArrayList);

        Log.d("TAG", "onItemClick: "+uriArrayList);

        startActivity(intent);


    }
}
