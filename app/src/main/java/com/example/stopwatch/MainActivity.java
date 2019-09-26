package com.example.stopwatch;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnstart,btnreset,btnpause;
    ImageView icanchor;
    Animation roundingalone;
    Chronometer timeHere;
    boolean running;
    long pauseOffSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnstart = findViewById(R.id.btnstart);
        icanchor = findViewById(R.id.icanchor);
        btnreset = findViewById(R.id.btnReset);
        timeHere = findViewById(R.id.timehere);
        btnpause=findViewById(R.id.btnpause);

        //optional animation
        btnreset.setAlpha(0);

        //load animation
        roundingalone = AnimationUtils.loadAnimation(this,R.anim.roundingalone);

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!running)
                {
                    //passing animation
                    icanchor.startAnimation(roundingalone);
                    //start time
                    timeHere.setBase(SystemClock.elapsedRealtime()- pauseOffSet);
                    timeHere.start();
                    running = true;
                    btnstart.animate().alpha(0).setDuration(300).start();
                    btnreset.animate().alpha(1).setDuration(300).start();
                    btnpause.animate().alpha(1).setDuration(300).start();
                }

            }
        });

        btnpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(running)
                {
                    timeHere.stop();
                    pauseOffSet = SystemClock.elapsedRealtime() - timeHere.getBase();
                    btnstart.animate().alpha(2).setDuration(300).start();
                    btnreset.animate().alpha(2).setDuration(300).start();
                    btnpause.animate().alpha(2).setDuration(300).start();
                    running = false;
                    icanchor.clearAnimation();
                }
            }
        });

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeHere.stop();
                timeHere.setBase(SystemClock.elapsedRealtime());
                pauseOffSet = 0;
                icanchor.clearAnimation();
            }
        });
    }
}
