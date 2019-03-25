package com.example.volumecontrolprototype;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.AudioManager;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import android.app.NotificationManager;
import android.content.Intent;
import android.provider.Settings;


public class VolumeTest extends AppCompatActivity
{
    Button silentB, vibrateB, ringB, permissionB;
    private CheckBox permissionCheckbox;

    AudioManager aManager;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volume_test);

        aManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        context = getApplicationContext();

        ringB = (Button)findViewById(R.id.btnRing);
        silentB = (Button)findViewById(R.id.btnSilent);
        vibrateB = (Button)findViewById(R.id.btnVibrate);

        //Set a default button state based on current volume setting
        int currentMode = aManager.getRingerMode(); //Ringer Mode is silent by default

        if(currentMode == AudioManager.RINGER_MODE_NORMAL) // RINGER_MODE_NORMAL == 2
        {
            ringB.setBackgroundResource(R.color.colorAccent);
        }
        else if(currentMode == AudioManager.RINGER_MODE_VIBRATE) //RINGER_MODE_VIBRATE == 1
        {
            vibrateB.setBackgroundResource(R.color.colorAccent);
        }
        else if(currentMode == AudioManager.RINGER_MODE_SILENT) //RINGER_MODE_SILENT == 0
        {
            silentB.setBackgroundResource(R.color.colorAccent);
        }


//        permissionB = (Button) findViewById(R.id.btnPermission);
//        permissionB.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                onPermissionsClicked();
//            }
//        });
    }//end method onCreate

    public void onPermissionsClicked(View v)
    {
        Intent intent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M)
        {
            intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
        }
        startActivity(intent);
    }


    public void clickSilent(View v)
    {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT < 24 || (Build.VERSION.SDK_INT >= 24 && notificationManager.isNotificationPolicyAccessGranted()))
        {
            aManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            Toast.makeText(getApplicationContext(),"Silent Mode Activated",Toast.LENGTH_SHORT).show();

            ringB.setBackgroundResource(R.color.colorPrimary);
            silentB.setBackgroundResource(R.color.colorAccent);
            vibrateB.setBackgroundResource(R.color.colorPrimary);
        }
    }//end clickSilent

    public void clickVibrate(View v)
    {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT < 24 || (Build.VERSION.SDK_INT >= 24 && notificationManager.isNotificationPolicyAccessGranted()))
        {
            aManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
            Toast.makeText(getApplicationContext(),"Vibrate Mode Activated",Toast.LENGTH_SHORT).show();

            ringB.setBackgroundResource(R.color.colorPrimary);
            vibrateB.setBackgroundResource(R.color.colorAccent);
            silentB.setBackgroundResource(R.color.colorPrimary);
        }
    }//end clickVibrate

    public void clickRing(View v)
    {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT < 24 || (Build.VERSION.SDK_INT >= 24 && notificationManager.isNotificationPolicyAccessGranted()))
        {
            aManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            Toast.makeText(getApplicationContext(),"Ring Mode Activated",Toast.LENGTH_SHORT).show();

            silentB.setBackgroundResource(R.color.colorPrimary);
            ringB.setBackgroundResource(R.color.colorAccent);
            vibrateB.setBackgroundResource(R.color.colorPrimary);
        }
    }//end clickRing
}//end class VolumeTest
