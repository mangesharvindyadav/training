package com.example.root.mytest;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PowerManager;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

/**
 * Created by root on 19/1/18.
 */

public class AlarmBroadcastReceiver extends BroadcastReceiver {




    @Override
    public void onReceive(Context context, Intent intent) {

        PowerManager manager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = manager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wl.acquire();
        String extra = intent.getStringExtra("my");
        StringBuilder builder = new StringBuilder();

        if (extra!=null&&extra.equals("true")) ;
        {
            builder.append("myTime");
            Intent intent1=new Intent(context,LoginActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            SharedPreferences.Editor editor=LoginActivity.preferences.edit();
           editor.putBoolean("flag",false).apply();

           context.startActivity(intent1);


        }
        Format formatter=new SimpleDateFormat("dd/mm/yyyy hh:MM:ss");
        builder.append(formatter.format(new Date()));

        Toast.makeText(context, builder, Toast.LENGTH_LONG).show();

        wl.release();

    }



    public void  SetAlaram(Context context, String flag)
    {
        AlarmManager alarmManager= (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent=new Intent(context,AlarmBroadcastReceiver.class);
        intent.putExtra("my",flag);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(context,2,intent,0);

        alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+5000,pendingIntent);




    }
}
