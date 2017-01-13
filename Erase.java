package com.example.oli.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;

public class Erase extends AppCompatActivity
{

    private Button B1, B2;
    private CheckBox C1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erase);
        B1 = (Button) findViewById(R.id.B1);
        B2 = (Button) findViewById(R.id.B2);
        B2.setEnabled(false);
        C1 = (CheckBox) findViewById(R.id.C1);
        B1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                try {
                    // back to calculator
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
                catch (Exception e) { Log.d("myTag", e.getMessage()); }
            }
        });
        C1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                try {
                    // back to calculator
                    if(C1.isChecked())
                    {
                        B2.setEnabled(true);
                    }
                    else
                    {
                        B2.setEnabled(false);
                    }
                }
                catch (Exception e) { Log.d("myTag", e.getMessage()); }
            }
        });
        B2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                try {
                    String[] Files;
                   File path = getApplicationContext().getFilesDir();
                   Files = path.list();

                    File dir = getFilesDir();
                    for(int h = 0; h<Files.length; h++)
                    {
                        File file = new File(dir,Files[h]);

                        try {
                            FileOutputStream stream = new FileOutputStream(file);
                            try {
                                stream.write(CryptEngine.getRandomBytes(file.length()));

                            } finally {
                                stream.close();
                            }
                        }
                        catch (Exception e){}
                        boolean del = file.delete();

                    }


                }
                catch (Exception e) { Log.d("myTag", e.getMessage()); }
            }
        });

    }
}
