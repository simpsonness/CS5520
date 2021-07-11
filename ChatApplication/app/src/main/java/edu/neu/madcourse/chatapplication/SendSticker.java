package edu.neu.madcourse.chatapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import edu.neu.madcourse.chatapplication.models.Message;

public class SendSticker extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_sticker);


        //this hides the keyboard when you're done entering a username
        EditText editText = (EditText) findViewById(R.id.usernameInputSend);
        editText.setOnEditorActionListener((v, actionId, event) -> {
            boolean handled = false;
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
                handled = true;
            }
            return handled;
        });

        //get username of current user
        username = Utils.readUsername(SendSticker.this);
        if (username.equals("")) {
            request_user_name();
        }

        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    //copied from MainActivity2
    private void request_user_name() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter name:");

        final EditText input_field = new EditText(this);

        builder.setView(input_field);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                username = input_field.getText().toString();
                Utils.writeUsername(SendSticker.this, username);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                request_user_name();

            }
        });
        builder.show();
    }

    public void sendSticker(String sticker) {
        //get destination username
        //update that user's messages
        //find user and get token to send a cloud notification -- this wasn't working for me, sill need to solve

        EditText editText = (EditText) findViewById(R.id.usernameInputSend);
        String to = editText.getText().toString();


        //add message to that user's messages
        Message newMessage = new Message(username, sticker, Utils.time());
        //push ok without transaction
        DatabaseReference newMessRef = mDatabase.child("stickerMessages").child(to).push();
        newMessRef.setValue(newMessage);
        Toast.makeText(SendSticker.this, "Your " + sticker + " was sent to " + to, Toast.LENGTH_SHORT).show();

        //clear the input
        editText.setText("");
    }

    public void clickMustache(View view) {
        sendSticker("mustache");
    }

    public void clickDead(View view) {
        sendSticker("dead");
    }

    public void clickDrooling(View view) {
        sendSticker("drooling");
    }

    public void clickFamous(View view) {
        sendSticker("famous");
    }

    public void clickMonocle(View view) {
        sendSticker("monocle");
    }

    public void clickPlusOne(View view) {
        sendSticker("plus_one");
    }

    public void clickParty(View view) {
        sendSticker("party");
    }

    public void clickSilly(View view) {
        sendSticker("silly");
    }
}
