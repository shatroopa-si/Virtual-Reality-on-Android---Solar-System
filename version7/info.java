package com.example.android.version7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class info extends AppCompatActivity {

    private static final int NUMBER_OF_COMPONENTS = 9;

    TextView info;
    String msg;
    int rings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*android.app.ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);*/

        setContentView(R.layout.activity_info);

        for(int i = 0; i < NUMBER_OF_COMPONENTS; i++)
        {
            if(WorldData.getRings(i)) {
                rings = 2;
            }
            else {
                rings = 0;
            }

            msg = "Name: " + WorldData.getName(i) + "\n" +
                    "Radius: " + WorldData.actualRadius[i] + "km\n" +
                    "Speed of rotation: " + WorldData.actualOrbitalPeriod[i] + "km/s\n" +
                    "Distance from the sun: " + WorldData.actualDistance[i] + "million km\n" +
                    "Number of moons: " + WorldData.getMoons(i) + "\n" +
                    "Has Rings: " + rings;

            switch(i) {
                case 0:
                    info = (TextView) findViewById(R.id.info_sun);
                    info.setText(msg);
                    break;
                case 1:
                    info = (TextView) findViewById(R.id.info_mercury);
                    info.setText(msg);
                    break;
                case 2:
                    info = (TextView) findViewById(R.id.info_venus);
                    info.setText(msg);
                    break;
                case 3:
                    info = (TextView) findViewById(R.id.info_earth);
                    info.setText(msg);
                    break;
                case 4:
                    info = (TextView) findViewById(R.id.info_mars);
                    info.setText(msg);
                    break;
                case 5:
                    info = (TextView) findViewById(R.id.info_jupiter);
                    info.setText(msg);
                    break;
                case 6:
                    info = (TextView) findViewById(R.id.info_saturn);
                    info.setText(msg);
                    break;
                case 7:
                    info = (TextView) findViewById(R.id.info_uranus);
                    info.setText(msg);
                    break;
                case 8:
                    info = (TextView) findViewById(R.id.info_neptune);
                    info.setText(msg);
                    break;
                default:
                    break;
            }
        }
    }

    public void gotoHome(View view){
        Intent mainIntent = new Intent(info.this, homePage.class);
        info.this.startActivity(mainIntent);
        info.this.finish();
    }
}
