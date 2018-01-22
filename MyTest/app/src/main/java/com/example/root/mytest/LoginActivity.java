package com.example.root.mytest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private AutoCompleteTextView mUsername;
    private Button btn_login;
    private EditText mPassword;
    private CheckBox remem;
    private Context context;
    public static SharedPreferences preferences;
    public static   SharedPreferences.Editor editor;
    private AlarmBroadcastReceiver alarmBroadcastReceiver;
    private String[] list = new String[]{"ajay", "mangesh", "varun"};
    String name, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;


        preferences=PreferenceManager.getDefaultSharedPreferences(this  );


        alarmBroadcastReceiver = new AlarmBroadcastReceiver();

        mUsername = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);
        remem = findViewById(R.id.checkbox);
        btn_login = findViewById(R.id.login);


        mUsername.setThreshold(1);


      if (preferences.getBoolean("flag",false))
      {
          startActivity(new Intent(context,MainActivity.class));
          finish();
      }

        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, list);
        mUsername.setAdapter(adapter);


        mPassword.setText("ajay");


        if (preferences.getString("name", null) != null) {
            mUsername.setText((preferences.getString("name", null)));
        }


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                name = mUsername.getText().toString().trim();
                pass = mPassword.getText().toString().trim();


                if (name.equals("") && pass.equals("")) {
                    Toast.makeText(context, "Please enter all Fields", Toast.LENGTH_SHORT).show();
                } else if (name.equals("")) {
                    mUsername.setError("Enter Username");
                } else if (pass.equals("")) {
                    mPassword.setError("Enter your Password");
                }
                if (!name.equals("") && !pass.equals("")) {
                    for (int i = 0; i < list.length; i++) {
                        if (name.equals(list[i]) && pass.equals("ajay") && !remem.isChecked()) {
                            Intent intent = new Intent(context, MainActivity.class);
                            intent.putExtra("namee", list[i]);
                            startActivity(intent);

                            startRepeate(String.valueOf(remem.isChecked()));
                            editor = preferences.edit();
                            editor.putString("name", list[i]);
                            editor.putBoolean("flag", true);
                            editor.apply();

                            finish();
                        } else if ((name.equals(list[i]) && pass.equals("ajay")) && remem.isChecked()) {

                            editor = preferences.edit();
                            editor.putString("name", list[i]);
                            editor.putBoolean("flag", true);
                            editor.apply();

                            Intent intent = new Intent(context, MainActivity.class);
                            intent.putExtra("namee", list[i]);
                            startActivity(intent);

                            finish();

                        }
                    }


                }


            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void startRepeate(String flag) {
        Context context = this.getApplicationContext();

        if (alarmBroadcastReceiver != null) {
            alarmBroadcastReceiver.SetAlaram(context, flag);
        }


    }
}
