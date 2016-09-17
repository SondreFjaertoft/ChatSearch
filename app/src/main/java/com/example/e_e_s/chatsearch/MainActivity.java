package com.example.e_e_s.chatsearch;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    EditText inputSearch;
    public ListView Fornavn;
    private static final int PERMISSION_REQUEST_READ_CONTACTS = 100;
    ArrayAdapter<String> adapter;
    public final static String hello = "hello";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.Fornavn = (ListView) findViewById(R.id.Fornavn);
        inputSearch = (EditText) findViewById(R.id.inputSearch);

        showContacts();
    }



    public void showContacts()
    {

        //Skjekker om SDK versonen har allerede gitt tilatelse eller ikke.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{android.Manifest.permission.READ_CONTACTS}, PERMISSION_REQUEST_READ_CONTACTS);
        }
        else
        {
            List<String> contacts = getContactNames();
            adapter = new ArrayAdapter<>(this, R.layout.list_item, R.id.contact_name, contacts);
            Fornavn.setAdapter(adapter);

            inputSearch.addTextChangedListener(new TextWatcher()
            {
                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3)
                {

                }

                @Override
                public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3)
                {
                    MainActivity.this.adapter.getFilter().filter(cs);
                }

                @Override
                public void afterTextChanged(Editable arg0)
                {

                }
            });
            Fornavn.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                public void onItemClick(AdapterView<?> av, View view, int i, long l)
                {
                    Intent intent = new Intent(MainActivity.this, Message.class);

                    intent.putExtra(hello, "hello");
                    startActivity(intent);
                }

            });
        }

    }


    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
    {
        if (requestCode == PERMISSION_REQUEST_READ_CONTACTS)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                //WE HAVE A LIF OF BOYS!
                showContacts();
            }
            else
            {
                Toast.makeText(this, "Me treng tilatelsen din bro.. ok? ty", Toast.LENGTH_SHORT).show();
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }

        }
    }

    private List<String> getContactNames()
    {
        List<String> contacts = new ArrayList<>();

        // FInner contentresolver
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        //skjekker om cursor er tom og flytter den til første.
        if (cursor.moveToFirst())
        {
            //ittererer gjennom cursor
            do
            {
                //Finner kontakt navnene
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                contacts.add(name);
            }

            while (cursor.moveToNext());

        }
        // avslutter cursor
        cursor.close();
        return contacts;

    }


}
