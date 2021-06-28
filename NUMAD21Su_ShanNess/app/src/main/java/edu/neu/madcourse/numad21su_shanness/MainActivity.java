package edu.neu.madcourse.numad21su_shanness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity{

    TextView textview, timeview;
    Button showBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button secondActivity = (Button) findViewById(R.id.secondActivityBtn);

        secondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        Button linksActivity = (Button) findViewById(R.id.buttonLink);

        linksActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLink = new Intent(MainActivity.this, ActivityLinkCollector.class);
                startActivity(intentLink);
            }
        });

        Button locationActivity = (Button) findViewById(R.id.buttonLocation);

        locationActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLocation = new Intent(MainActivity.this, LocationActivity.class);
                startActivity(intentLocation);
            }
        });

        Button webServiceActivity = (Button) findViewById(R.id.buttonWeb);

        webServiceActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentWebService = new Intent(MainActivity.this,ActivityWebService.class);
                startActivity(intentWebService);
            }
        });




        textview = (TextView) findViewById(R.id.name_txt);
        showBtn = (Button) findViewById(R.id.show_name);
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textview.setText("My name is Shan.\nMy email is 'shanliness@gmail.com'.");
            }
        });

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String time = "Current time: " + format.format(calendar.getTime());

        timeview = findViewById(R.id.time);
        timeview.setText(time);

    }


}










