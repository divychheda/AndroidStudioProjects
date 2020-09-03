package com.example.colormatchgame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView time;
    private  TextView score;
    private TextView fs;
    Random rand = new Random();
    private int c, t, count = 0;
    Handler handler = new Handler();
    final String[] colors = {"#000000", "#00FF00", "#FFFF00", "#FF0000", "#0000FF"};
    final String[] textv = {"Black", "Green", "Yellow", "Red", "Blue"};
    private Button but ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time = (TextView) findViewById(R.id.color_text);
        but=(Button)findViewById(R.id.match);
        score=(TextView)findViewById(R.id.score);
        fs=(TextView)findViewById(R.id.final_score);

            time.setText("TimeLeft,Score");
            score.setText("");
        final int[] time1 = {60};
            for (int i = 0; i <=60; i++) {

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        c = rand.nextInt(5);
                        t = rand.nextInt(5);
                        score.setText(Integer.toString(count));
                        time.setText(Integer.toString(time1[0]));
                        time1[0]--;
                        but.setText(textv[t]);
                        but.setTextColor(Color.parseColor(colors[c]));
                        if(time1[0]==0) fs.setText("Final Score: "+count); }
                }, 1000 * (i));
            }




        }

    public void check(View view)
    {
        if(t==c)
            count++;
        else
            count--;



    }

}