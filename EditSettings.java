package com.example.oli.calculator;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;

public class EditSettings extends AppCompatActivity {

    private Button B1, B2;
    private EditText E1, E2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_settings);

        B1 = (Button) findViewById(R.id.B1);
        B2 = (Button) findViewById(R.id.B2);

        E1 = (EditText) findViewById(R.id.E2);
        E2 = (EditText) findViewById(R.id.E1);

        File f = new File("Calculator++SettingsHSH.txt");
        File f2 = new File("Calculator++SettingsDMT.txt");

        if(!f.exists())
        {
            try{boolean b = f.createNewFile();} catch(Exception e){}
        }
        if(!f2.exists())
        {
            try{boolean b = f2.createNewFile();} catch(Exception e){}
        }
        B1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Menu.class));

            }
        });
        B2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

                Log.d("COMP", E1.getText().toString());


                //new Filer(getApplicationContext()).Write("SecuritySettings.txt", new String(CryptEngine.getSinglehash(E1.getText().toString().getBytes(), Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID))));



                //String E1H = new String(CryptEngine.getSinglehash(E1.getText().toString().getBytes(), Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID)));
               // String E2H = new String(CryptEngine.getSinglehash(E2.getText().toString().getBytes(), Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID)));
                Log.d("WHAt it should be", String.valueOf(Double.parseDouble(E1.getText().toString()) ));
                new Filer(getApplicationContext()).Write("Calculator++SettingsHSH.txt", String.valueOf(Double.parseDouble(E1.getText().toString()) ));
                new Filer(getApplicationContext()).Write("Calculator++SettingsDMT.txt", String.valueOf(Double.parseDouble(E2.getText().toString()) ));
            }});
    }
}
