package com.example.oli.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    private Button B1, B2 , B3, B4, B5, B6, B7;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        B1 = (Button) findViewById(R.id.B1);
        B2 = (Button) findViewById(R.id.B2);
        B3 = (Button) findViewById(R.id.B3);
        B4 = (Button) findViewById(R.id.B4);
        B5 = (Button) findViewById(R.id.B5);
        B6 = (Button) findViewById(R.id.B6);
        B7 = (Button) findViewById(R.id.B7);
        B1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                try {
                    Bundle b = new Bundle();
                    b.putString("Action", "E");

                    Intent i  = new Intent(getApplicationContext(), NewFile.class);//new file intent goes here

                    i.putExtras(b);

                    startActivity(i);
                }
                catch (Exception e) { Log.d("myTag", e.getMessage()); }
            }
        });
        B2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                try {
                    Bundle b = new Bundle();
                    b.putString("Action", "W");

                    Intent i  = new Intent(getApplicationContext(), FilePicker.class);//new file intent goes here

                    i.putExtras(b);

                    startActivity(i);
                }
                catch (Exception e) { Log.d("myTag", e.getMessage()); }
            }
        });
        B3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                try {
                    Bundle b = new Bundle();
                    b.putString("Action", "E");

                    Intent i  = new Intent(getApplicationContext(), FilePicker.class);//new file intent goes here

                    i.putExtras(b);

                    startActivity(i);
                }
                catch (Exception e) { Log.d("myTag", e.getMessage()); }
            }
        });
        B4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                try {
                    Bundle b = new Bundle();
                    b.putString("Action", "X");

                    Intent i  = new Intent(getApplicationContext(), FilePicker.class);//new file intent goes here

                    i.putExtras(b);

                    startActivity(i);
                }
                catch (Exception e) { Log.d("myTag", e.getMessage()); }
            }
        });
        B5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                try {
                  // back to calculator
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
                catch (Exception e) { Log.d("myTag", e.getMessage()); }
            }
        });
        B6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                try {
                    startActivity(new Intent(getApplicationContext(), Erase.class));
                }
                catch (Exception e) { Log.d("myTag", e.getMessage()); }
            }
        });
        B6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                try {
                    startActivity(new Intent(getApplicationContext(), Erase.class));
                }
                catch (Exception e) { Log.d("myTag", e.getMessage()); }
            }
        });
        B7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                try {
                    startActivity(new Intent(getApplicationContext(), EditSettings.class));
                }
                catch (Exception e) { Log.d("myTag", e.getMessage()); }
            }
        });









    }
}
