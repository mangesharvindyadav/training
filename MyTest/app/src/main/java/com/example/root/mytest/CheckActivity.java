package com.example.root.mytest;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class CheckActivity extends AppCompatActivity {

    private static final int CONTACT_CODE = 5;
    private String my;
    private ArrayList<String> permission=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        my = LoginActivity.preferences.getString("name", null);
        permission.add(Manifest.permission.READ_CONTACTS);
        permission.add(Manifest.permission.READ_EXTERNAL_STORAGE);


        if (my != null) {

            if (checkPermission()) {
                Intent intent = new Intent(CheckActivity.this, MainActivity.class);
                intent.putExtra("my", my);
                startActivity(intent);
                finish();
//            } else {
//                startActivity(new Intent(CheckActivity.this, LoginActivity.class));
//                SharedPreferences.Editor editor = LoginActivity.preferences.edit();
//                editor.putBoolean("flag", false).apply();
//                finish();
//            }

            }

        }

    }



    public boolean checkPermission()
    {


            if (ContextCompat.checkSelfPermission(CheckActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(CheckActivity.this, Manifest.permission.READ_CONTACTS)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(CheckActivity.this);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("READ Contacts permission is necessary !!");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(CheckActivity.this, new String[]{Manifest.permission.READ_CONTACTS,Manifest.permission.READ_EXTERNAL_STORAGE}, CONTACT_CODE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions(CheckActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, CONTACT_CODE);
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
                    Intent intent=new Intent(CheckActivity.this,MainActivity.class);
                    intent.putExtra("my",my);
                    startActivity(intent);
                    finish();
                } else {
                    //code for deny
                    startActivity(new Intent(CheckActivity.this,LoginActivity.class));

                    SharedPreferences.Editor editor = LoginActivity.preferences.edit();
                    editor.putBoolean("flag", false).apply();
                    finish();
                }



        }
    }
}
