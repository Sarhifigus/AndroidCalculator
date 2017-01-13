package com.example.oli.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewFile extends AppCompatActivity {

    private EditText E1;
    private TextView T1;
    private Button B1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_file);
        E1 = (EditText) findViewById(R.id.E1);
        T1 = (TextView) findViewById(R.id.T1);
        T1.setText(new Filer(getApplicationContext()).getDir());
        B1 = (Button) findViewById(R.id.B1);
        E1.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                T1.setText(new Filer(getApplicationContext()).getDir() + "/" + E1.getText() + ".txt");

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        B1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                try
                {
                    String filename = E1.getText().toString();


                    Filer f = new Filer(getApplicationContext());
                    f.Write(filename + ".txt","");
                    Bundle b = new Bundle();
                    b.putString("filename", filename+".txt");

                    Intent i  = new Intent(getApplicationContext(), FileWriter.class);
                    i.putExtras(b);

                    startActivity(i);

                }
                catch (Exception e) { Log.d("myTag", e.getMessage()); }
            }
        });

    }
}
