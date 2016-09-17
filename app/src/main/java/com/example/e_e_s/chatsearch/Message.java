package com.example.e_e_s.chatsearch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



import static android.R.attr.value;

/**
 * Created by e-e-s on 16.09.2016.
 */

public class Message extends AppCompatActivity
{
    //TextView mottaker;
    EditText message;
    Button sendButton;
    TextView sendText;
    Bundle icicle;

    @Override
    protected void onCreate(@Nullable Bundle icicle)
    {
        if(icicle != null)
        {
            value = icicle.getLong("param");
        }
        //super.onCreate(savedInstanceState);
        setContentView(R.layout.message_text);

        Intent intent = getIntent();
        String nameOfClicked = intent.getStringExtra(MainActivity.hello);


        getSupportActionBar().setTitle(nameOfClicked);

        message = (EditText) findViewById(R.id.inputMessage);
        sendButton = (Button) findViewById(R.id.sendButton);
        sendText = (TextView) findViewById(R.id.dinText);
        TextView friendText = (TextView) findViewById(R.id.getText);
        CharSequence theySay = "\nSays: \nDitte e harkoda fordi du\nsuge sondre :(";
        friendText.setText(nameOfClicked + theySay);


        sendButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                InputMethodManager inputManager =
                        (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                CharSequence getTextViewText = sendText.getText();
                String input = message.getText().toString();

                sendText.setText(getTextViewText+ "\n" + input);
                message.setText("");



            }
        });

    }
    protected void onSaveInstanceState(Bundle icicle)
    {
        super.onSaveInstanceState(icicle);
        icicle.putLong("param", value);
    }






}
