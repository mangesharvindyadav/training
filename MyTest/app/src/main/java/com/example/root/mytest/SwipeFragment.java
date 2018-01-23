package com.example.root.mytest;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.net.URI;


/**
 * A simple {@link Fragment} subclass.
 */
public class SwipeFragment extends Fragment {


    String uri;
    int pos;

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_IMG_URL = "image_url";

    public SwipeFragment() {
        // Required empty public constructor
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        this.pos = args.getInt(ARG_SECTION_NUMBER);

        this.uri = args.getString(ARG_IMG_URL);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_swipe, container, false);

        ImageView imageView=view.findViewById(R.id._image);

        Picasso.with(getContext()).load(uri).into(imageView);



        return view;
    }


    public static SwipeFragment newInstance(int p,String uri)
    {
        SwipeFragment fragment=new SwipeFragment();
        Bundle arg=new Bundle();
        arg.putInt(ARG_SECTION_NUMBER,p);
        arg.putString(ARG_IMG_URL,uri);
        fragment.setArguments(arg);

        return fragment;
    }



}
