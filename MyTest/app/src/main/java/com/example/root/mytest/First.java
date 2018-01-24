package com.example.root.mytest;


import android.Manifest;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.net.URI;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class First extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>,AdapterView.OnItemClickListener {

    private final static String[] COLOUMNs= {
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            ContactsContract.Contacts.DISPLAY_NAME
    };




    private static final String[] PROJECTION =new String[]
            {
                    ContactsContract.Contacts._ID,
                    ContactsContract.Contacts.LOOKUP_KEY,
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
                    ContactsContract.Contacts.DISPLAY_NAME

            };



    private final static int[] IDS={R.id.contact_name};

    private ListView listView;

    private SimpleCursorAdapter cursorAdapter;

    public First() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_first, container, false);

       // Typeface typeface=Typeface.createFromAsset(getActivity().getAssets(),"Roboto-ThinItalic.ttf");

       // TextView textView=view.findViewById(R.id.contact_number);
       // textView.setTypeface(typeface);



        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        listView=(ListView)getActivity().findViewById(R.id.all_list);


        cursorAdapter=new SimpleCursorAdapter(getActivity(),R.layout.all_contact_single_row,null,
                COLOUMNs,IDS,0);


        listView.setAdapter(cursorAdapter);

        listView.setOnItemClickListener(this);

        getLoaderManager().initLoader(2,null,this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {


        return new CursorLoader(getActivity(),
                ContactsContract.Contacts.CONTENT_URI,PROJECTION,
                null,null,null);
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

    }
}
