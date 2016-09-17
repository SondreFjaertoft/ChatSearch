package com.example.e_e_s.chatsearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by e-e-s on 16.09.2016.
 */

public class Message extends AppCompatActivity
{
    //TextView mottaker;
    EditText message;
    Button sendButton;
    TextView sendText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_text);

        Intent intent = getIntent();
        String nameOfClicked = intent.getStringExtra(MainActivity.hello);


        getSupportActionBar().setTitle(nameOfClicked);

        message = (EditText) findViewById(R.id.inputMessage);
        sendButton = (Button) findViewById(R.id.sendButton);
        sendText = (TextView) findViewById(R.id.dinText);

        sendButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sendText.setText(message.getText().toString());
            }
        });
    }






}
