package com.example.root.mytest;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
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
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class Third extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>,AdapterView.OnItemClickListener{


    public Third() {
        // Required empty public constructor
    }

    private final static String[] COLOUMNs= {
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Email.ADDRESS


    };


    private static final String[] PROJECTION =new String[]
            {       ContactsContract.Contacts._ID,
                    ContactsContract.CommonDataKinds.Email.DATA,
                    ContactsContract.CommonDataKinds.Email.ADDRESS,
                    ContactsContract.Contacts.DISPLAY_NAME,



            };


    private final static int[] IDS={R.id.contact_email,R.id.email_};

    private ListView listView;
    private SimpleCursorAdapter cursorAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_third, container, false);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView=(ListView)getActivity().findViewById(R.id.email);


        cursorAdapter=new SimpleCursorAdapter(getActivity(),R.layout.all_contact_email_single_row,null,
                COLOUMNs,IDS,0);


        listView.setAdapter(cursorAdapter);

        listView.setOnItemClickListener(this);

        getLoaderManager().initLoader(12,null,this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), ContactsContract.CommonDataKinds.Email.CONTENT_URI,PROJECTION,
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
