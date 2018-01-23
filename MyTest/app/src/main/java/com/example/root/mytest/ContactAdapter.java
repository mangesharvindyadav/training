package com.example.root.mytest;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by root on 17/1/18.
 */

public class ContactAdapter extends BaseExpandableListAdapter {

    private static final int CALL_PERMISSION = 5;
    private Context context;
    private ArrayList<Name> nameArrayList;
    private ProfileFragment fragment;


    public ContactAdapter(Context context, ArrayList<Name> nameArrayList) {
        this.context = context;
        this.nameArrayList = nameArrayList;
    }

    @Override
    public int getGroupCount() {
        return nameArrayList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ArrayList<Model> arrayList=nameArrayList.get(groupPosition).getModelArrayList();

        return arrayList.size();
    }

    @Override
    public Object getGroup(int i) {
        return nameArrayList.get(i);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        ArrayList<Model> models=nameArrayList.get(groupPosition).getModelArrayList();
        return models.get(childPosition);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        Name name= (Name) getGroup(i);
        if (view==null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.name, null);

            TextView n=view.findViewById(R.id.name);
            n.setText(name.getP_name());
        }

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {

        Model model= (Model) getChild(groupPosition,childPosition);
        if (view==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.phone, null);
        }
        ImageView l=view.findViewById(R.id.location);
        ImageView  c=view.findViewById(R.id.call);
     final TextView  p =view.findViewById(R.id.phone);
        TextView d=view.findViewById(R.id.text_desc);
        final EditText a=view.findViewById(R.id.place);

        p.setText(model.getNumber());
        d.setText(model.getDescrip());
        a.setText(model.getAddress());


        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n=a.getText().toString().trim();

                try {
                    getLocation(n);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               myPermission(p.getText().toString().trim());
            }
        });


        return view;
    }


    private void getLocation(String l) throws IOException {
        Geocoder geocoder=new Geocoder(context);
        List<Address> addresses;
        addresses=geocoder.getFromLocationName(l,5);

        if (addresses==null)
        {
            Toast.makeText(context, "No Such Place", Toast.LENGTH_SHORT).show();
        }
        else {
            Address my_Loc=addresses.get(0);
            String uri = String.format(Locale.ENGLISH, "geo:%f,%f",
                    my_Loc.getLatitude(), my_Loc.getLongitude());

            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
        }
    }


    public  void myPermission(String p)
    {

        if (ActivityCompat.checkSelfPermission(context.getApplicationContext(),
                Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
        {    // Log.d(TAG, "myPermission:Working ");



         //   Log.d(TAG, "myPermission: checker");
            AlertDialog.Builder dialog=new AlertDialog.Builder(context.getApplicationContext());
            dialog.setTitle("Require Permission");
            dialog.setMessage("App need To Access Phone Call");
            dialog.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    dialogInterface.dismiss();

                    ActivityCompat.requestPermissions(fragment.getActivity(),new String[]{Manifest.permission.CALL_PHONE},CALL_PERMISSION);




                }
            });
            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    Toast.makeText(fragment.getActivity(), "You Won't Be able make Calls Unless Allowed", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.create().show();


        }else {
            context.startActivity(new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+p)));
//            Log.d(TAG, "myPermission:Working else");
        }
    }









    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
