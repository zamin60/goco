package com.zash.goco;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainChatActivity extends AppCompatActivity {

    // TODO: Add member variables here:
    private String mDisplayName;
    private ListView mChatListView;
    private EditText mInputText;
    private ImageButton mSendButton;
    private DatabaseReference mMDataBaseReference;
    private  ChatListAdaptor mAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);

        // TODO: Set up the display name and get the Firebase reference
        setupDisplayName();
        mMDataBaseReference = FirebaseDatabase.getInstance().getReference();


        // Link the Views in the layout to the Java code
        mInputText = (EditText) findViewById(R.id.messageInput);
        mSendButton = (ImageButton) findViewById(R.id.sendButton);
        mChatListView = (ListView) findViewById(R.id.chat_list_view);

        // TODO: Send the message when the "enter" button is pressed
        mInputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                sendMessage();
                return true;
            }
        });


        // TODO: Add an OnClickListener to the sendButton to send a message
           mSendButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   sendMessage();
               }
           });
    }

    // TODO: Retrieve the display name from the Shared Preferences
    private void setupDisplayName(){

        SharedPreferences prefs = getSharedPreferences(RegisterActivity.CHAT_PREFS,MODE_PRIVATE);
        mDisplayName = prefs.getString(RegisterActivity.DISPLAY_NAME_KEY,null);

        if(mDisplayName == null) mDisplayName = "Anonymous";
    }

    private void sendMessage() {
        Log.d("FlashChat","I send Something");
        // TODO: Grab the text the user typed in and push the message to Firebase
          String input = mInputText.getText().toString();
          if(!input.equals("")){
              instantMessage chat = new  instantMessage(input,mDisplayName);
              mMDataBaseReference.child("message").push().setValue(chat);
              mInputText.setText("");
          }
    }

    // TODO: Override the onStart() lifecycle method. Setup the adapter here.
    @Override
    public void onStart(){
        super.onStart();
        mAdaptor = new ChatListAdaptor(this,mMDataBaseReference,mDisplayName);
        mChatListView.setAdapter(mAdaptor);

    }

    @Override
    public void onStop() {
        super.onStop();

        // TODO: Remove the Firebase event listener on the adapter.
         mAdaptor.cleanup();
    }

}
