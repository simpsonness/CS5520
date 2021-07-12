package edu.neu.madcourse.chatapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.neu.madcourse.chatapplication.models.Message;

public class ViewStickers extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private String username;
    private ArrayList<Message> messages;
    private serviceAdapter adapter;
    RecyclerView rvMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_stickers);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        rvMessages = (RecyclerView) findViewById(R.id.messagesRV);
        messages = new ArrayList<Message>();
        adapter = new serviceAdapter(messages);
        rvMessages.setAdapter(adapter);
        rvMessages.setLayoutManager(new LinearLayoutManager(this));


        //get the current user
        //request if not logged in before
        String newUsername = Utils.readUsername(ViewStickers.this);
        if (newUsername.equals("")) {
            request_user_name();
        } else {
            username = newUsername;
            triggerLoadFromDb();
        }


    }

    private void triggerLoadFromDb() {
        //retrieve sticker messages
        mDatabase.child("stickerMessages").child(username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Message> newMessages = new ArrayList<>();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Message message = postSnapshot.getValue(Message.class);
                    newMessages.add(message);
                }

                Collections.reverse(newMessages);

                messages.clear();
                messages.addAll(newMessages);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(ViewStickers.this, "Could not load your stickers :(", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //copied from MainActivity2
    private void request_user_name() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Username:");

        final EditText input_field = new EditText(this);

        builder.setView(input_field);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String newUsername = input_field.getText().toString();
                if (!newUsername.equalsIgnoreCase("")) {
                    Utils.writeUsername(ViewStickers.this, newUsername);
                    username = newUsername;
                    triggerLoadFromDb();
                }
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

    public void openSend(View view) {
        Intent intent = new Intent(this, SendSticker.class);
        startActivity(intent);
    }

    public void switchUser(View view) {
        Utils.writeUsername(ViewStickers.this, "");
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public class serviceAdapter extends RecyclerView.Adapter<serviceAdapter.ViewHolder> {

        private List<Message> mMessages;

        public class ViewHolder extends RecyclerView.ViewHolder {

            public ImageView stickerView;
            public TextView messageSender;
            public TextView messageTime;
            public CardView card;

            public ViewHolder(View itemView) {
                super(itemView);
                stickerView = (ImageView) itemView.findViewById(R.id.stickerImage);
                messageSender = (TextView) itemView.findViewById(R.id.messageSender);
                messageTime = (TextView) itemView.findViewById(R.id.messageTime);
                card = (CardView) itemView.findViewById(R.id.card);
            }
        }

        public serviceAdapter(List<Message> m) {
            mMessages = m;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            View contactView = inflater.inflate(R.layout.item_message, parent, false);

            ViewHolder viewHolder = new ViewHolder(contactView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(serviceAdapter.ViewHolder holder, int position) {
            Message message = mMessages.get(position);

            TextView messageSender = holder.messageSender;
            messageSender.setText(message.fromUsername);
            messageSender.setTextColor(Color.DKGRAY);

            TextView messageTime = holder.messageTime;
            messageTime.setText(message.time);
            messageTime.setTextColor(Color.DKGRAY);

            ImageView stickerImage = holder.stickerView;

            if (message.sticker.equalsIgnoreCase("dead")) {
                stickerImage.setImageResource(R.drawable.dead);
            } else if (message.sticker.equalsIgnoreCase("drooling")) {
                stickerImage.setImageResource(R.drawable.drooling);
            } else if (message.sticker.equalsIgnoreCase("famous")) {
                stickerImage.setImageResource(R.drawable.famous);
            } else if (message.sticker.equalsIgnoreCase("monocle")) {
                stickerImage.setImageResource(R.drawable.monocle);
            } else if (message.sticker.equalsIgnoreCase("mustache")) {
                stickerImage.setImageResource(R.drawable.mustache);
            } else if (message.sticker.equalsIgnoreCase("party")) {
                stickerImage.setImageResource(R.drawable.party);
            } else if (message.sticker.equalsIgnoreCase("plus_one")) {
                stickerImage.setImageResource(R.drawable.plus_one);
            } else if (message.sticker.equalsIgnoreCase("silly")) {
                stickerImage.setImageResource(R.drawable.silly);
            } else {
                stickerImage.setImageResource(R.drawable.dead);
            }


            //this can be used to set onclick for the cards -> idk where to link tho
            /**
             * CardView cardView = holder.card;
             cardView.setOnClickListener( new View.OnClickListener() {
             public void onClick(View v) {
             String url = article.getLink();

             if (!url.startsWith("https://") && !url.startsWith("http://")){
             url = "http://" + url;
             }

             Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
             startActivity(Intent.createChooser(intent, "Browse with"));
             }
             });
             */
        }

        @Override
        public int getItemCount() {
            return messages.size();
        }

    }

}
