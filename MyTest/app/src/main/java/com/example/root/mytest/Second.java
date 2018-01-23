package com.example.root.mytest;


import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Second extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>,AdapterView.OnItemClickListener{



    private final static String[] COLOUMNs= {
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER


    };


    private static final String[] PROJECTION =new String[]
            {       ContactsContract.Contacts._ID,
                    ContactsContract.CommonDataKinds.Phone.NUMBER,
                    ContactsContract.Contacts.DISPLAY_NAME,
                    ContactsContract.Contacts.HAS_PHONE_NUMBER


            };


    private final static int[] IDS={R.id.contact_number,R.id.no};

    private ListView listView;
    private SimpleCursorAdapter cursorAdapter;
    private TextView textView;



    public Second() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_second, container, false);

              textView=view.findViewById(R.id.contact_number);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        listView=(ListView)getActivity().findViewById(R.id.phone_number);


        cursorAdapter=new SimpleCursorAdapter(getActivity(),R.layout.all_contact_phone_single_row,null,
                COLOUMNs,IDS,0);


        listView.setAdapter(cursorAdapter);

        listView.setOnItemClickListener(this);

        getLoaderManager().initLoader(23,null,this);
    }

    @Override

    public Loader<Cursor> onCreateLoader(int id, Bundle args) {


        return new CursorLoader(getActivity(),
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,PROJECTION,
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
