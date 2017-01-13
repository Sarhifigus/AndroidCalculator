package com.example.oli.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FileWriter extends AppCompatActivity {

    private String filename;
    private String text;
    private Button B1, B2;
    private EditText E1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_writer);
        B1 = (Button) findViewById(R.id.B1);
        B2 = (Button) findViewById(R.id.B2);
        E1 = (EditText) findViewById(R.id.E1);



        Bundle b = getIntent().getExtras();
        this.filename = b.getString("filename");
        text = new String(new Filer(getApplicationContext()).Read(filename));
        E1.setText(text);


        B1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                try
                {
                    new Filer(getApplicationContext()).Write(filename,E1.getText().toString());
                    Intent i  = new Intent(getApplicationContext(), Menu.class);


                    startActivity(i);

                }
                catch (Exception e) { Log.d("myTag", e.getMessage()); }
            }
        });
        B2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                try
                {
                    new Filer(getApplicationContext()).Write(filename,E1.getText().toString());
                }
                catch (Exception e) { Log.d("myTag", e.getMessage()); }
            }
        });

    }
}
