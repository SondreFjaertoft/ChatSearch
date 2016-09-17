package com.example.e_e_s.chatsearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by e-e-s on 16.09.2016.
 */

public class Message extends AppCompatActivity
{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_text);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.hello);
        TextView test = new TextView(this);

        test.setText(message);
        ViewGroup layout = (ViewGroup) findViewById(R.id.message_text);
        layout.addView(test);


    }








}
