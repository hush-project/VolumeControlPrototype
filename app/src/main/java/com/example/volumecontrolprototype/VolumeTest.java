package com.example.volumecontrolprototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.AudioManager;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class VolumeTest extends AppCompatActivity
{
    Button silentB, vibrateB, ringB;

    AudioManager aManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volume_test);

        aManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        Context context = getApplicationContext();

        ringB = (Button)findViewById(R.id.ringB);
        silentB = (Button)findViewById(R.id.silentB);
        vibrateB = (Button)findViewById(R.id.vibrateB);

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

    }//end method onCreate

    public void clickSilent(View v)
    {
        //if silent button is clicked
        silentB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                aManager.setRingerMode(0);
                Toast.makeText(getApplicationContext(), "Silent Mode Activated", Toast.LENGTH_SHORT).show();

                //change the color of the silent button to be lighter
                ringB.setBackgroundResource(R.color.colorPrimary);
                vibrateB.setBackgroundResource(R.color.colorPrimary);
                silentB.setBackgroundResource(R.color.colorAccent);
            }
        });//end code for silent button listener
    }

    public void clickVibrate(View v)
    {
        //if vibrate button is clicked
        vibrateB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                aManager.setRingerMode(1);
                Toast.makeText(getApplicationContext(), "Vibrate Mode Activated", Toast.LENGTH_SHORT).show();

                //change the color of the silent button to be lighter
                ringB.setBackgroundResource(R.color.colorPrimary);
                silentB.setBackgroundResource(R.color.colorPrimary);
                vibrateB.setBackgroundResource(R.color.colorAccent);
            }
        });//end code for vibrate button listener
    }//end clickVibrate

    public void clickRing(View v)
    {
        //if ring button is clicked
        ringB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                aManager.setRingerMode(2);
                Toast.makeText(getApplicationContext(), "Ring Mode Activated", Toast.LENGTH_SHORT).show();

                //change the color of the silent button to be lighter
                silentB.setBackgroundResource(R.color.colorPrimary);
                vibrateB.setBackgroundResource(R.color.colorPrimary);
                ringB.setBackgroundResource(R.color.colorAccent);
            }
        });//end code for ring button listener
    }
}//end class VolumeTest
