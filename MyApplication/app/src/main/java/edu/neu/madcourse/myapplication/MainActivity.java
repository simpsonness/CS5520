package edu.neu.madcourse.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {

    int number_of_clicks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        //find the button and save it to text
        TextView text = (TextView) findViewById(R.id.textView);
        number_of_clicks++;
        text.setText("Button clicked "+number_of_clicks+" times.");
    }
}