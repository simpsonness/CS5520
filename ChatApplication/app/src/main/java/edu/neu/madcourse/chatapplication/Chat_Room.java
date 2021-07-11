package edu.neu.madcourse.chatapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Chat_Room extends AppCompatActivity {
    private Button btn_send_msg;
    private EditText input_msg;
    private TextView chat_conversation;

    private String user_name, room_name;
    private DatabaseReference root;
    private String temp_key;
    private String chat_msg, chat_user_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_room);

        this.btn_send_msg = (Button) findViewById(R.id.btn_send);
        this.input_msg = (EditText) findViewById(R.id.msg_input);
        this.chat_conversation = (TextView) findViewById(R.id.textView);

        this.user_name = getIntent().getExtras().get("user_name").toString();
        this.room_name = getIntent().getExtras().get("room_name").toString();
        setTitle("Room - " + this.room_name);

        this.root = FirebaseDatabase.getInstance().getReference().child(this.room_name);

        this.btn_send_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<String, Object>();
                temp_key = root.push().getKey();
                root.updateChildren(map);

                DatabaseReference message_root = root.child(temp_key);
                Map<String,Object> map2 = new HashMap<String,Object>();
                map2.put("name", user_name);
                map2.put("msg", input_msg.getText().toString());

                message_root.updateChildren(map2);

                sendNotification(v);
            }
        });

        this.root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                append_chat_conversation(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s){
                append_chat_conversation(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshotsnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s){}

            @Override
            public void onCancelled(DatabaseError error) {}
        });
    }

    private void append_chat_conversation(DataSnapshot dataSnapshot) {
        Iterator i = dataSnapshot.getChildren().iterator();
        while (i.hasNext()){
            this.chat_msg = (String) ((DataSnapshot)i.next()).getValue();
            this.chat_user_name = (String) ((DataSnapshot)i.next()).getValue();
            this.chat_conversation.append(this.chat_user_name + " : " + this.chat_msg + " \n");
        }
    }


    public void sendNotification(View view) {
        Intent intent = new Intent(this, Chat_Room.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);
        PendingIntent callIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(),
                new Intent(this, FakeCallActivity.class), 0);

        String channelId = "1";

        Bitmap foo = BitmapFactory.decodeResource(getResources(), R.drawable.foo);


        NotificationCompat.Builder notifyBuild = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.foo)
                .setContentTitle("New mail from " + this.user_name)
                .setContentText(this.input_msg.getText())
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(false)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(foo))
                //.addAction(R.drawable.foo, "Call", callIntent)
                .setContentIntent(pIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0, notifyBuild.build());

    }
}