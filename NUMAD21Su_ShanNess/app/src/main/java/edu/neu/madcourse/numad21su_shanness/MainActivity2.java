package edu.neu.madcourse.numad21su_shanness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button button1 = findViewById(R.id.botton1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this); 
        button6.setOnClickListener(this);

    }

    public void switchActivity(View view) {
        Intent toy = new Intent(MainActivity2.this,MainActivity.class);
        startActivity(toy);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.botton1:
                Toast toast1 = Toast.makeText(this, "Pressed:A", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.LEFT, 0, -90);
                toast1.show();
                break;
            case R.id.button2:
                Toast toast2 = Toast.makeText(this, "Pressed:B", Toast.LENGTH_SHORT);
                toast2.setGravity(Gravity.LEFT, 0, -90);
                toast2.show();
                break;
            case R.id.button3:
                Toast toast3 = Toast.makeText(this, "Pressed:C", Toast.LENGTH_SHORT);
                toast3.setGravity(Gravity.LEFT, 0, -90);
                toast3.show();
                break;
            case R.id.button4:
                Toast toast4 = Toast.makeText(this, "Pressed:D", Toast.LENGTH_SHORT);
                toast4.setGravity(Gravity.LEFT, 0, -90);
                toast4.show();
                break;
            case R.id.button5:
                Toast toast5 = Toast.makeText(this, "Pressed:E", Toast.LENGTH_SHORT);
                toast5.setGravity(Gravity.LEFT, 0, -90);
                toast5.show();
                break;
            case R.id.button6:
                Toast toast6 = Toast.makeText(this, "Pressed:F", Toast.LENGTH_SHORT);
                toast6.setGravity(Gravity.LEFT, 0, -90);
                toast6.show();
                break;

        }

    }
}