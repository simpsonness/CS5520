package edu.neu.madcourse.chatapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mainActivityButton;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityButton = (Button) findViewById(R.id.viewMainActivity2);
        mainActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }



    public void startSendStickerActivity(View view) {
        startActivity(new Intent(MainActivity.this, SendSticker.class));
    }

    public void startViewStickersActivity(View view) {
        startActivity(new Intent(MainActivity.this, ViewStickers.class));
    }


}