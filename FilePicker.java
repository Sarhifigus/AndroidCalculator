package com.example.oli.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;

import java.io.File;

public class FilePicker extends AppCompatActivity {

    private String A;
    private File[] files;
    private String[] Files;
    private Button B1;
    private CheckBox C1;

    private ListView V1;

    private Intent i;


    private ArrayAdapter myArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_picker);
        Bundle b = getIntent().getExtras();
        C1 = (CheckBox) findViewById(R.id.C1);
        this.A = b.getString("Action");
        try {
            File path = getApplicationContext().getFilesDir();
            Files = path.list();
            String Files2[] = new String[Files.length - 1];


            myArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Files);


            V1 = (ListView) findViewById(R.id.V1);
            V1.setAdapter(myArrayAdapter);
            V1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapter, View view, int which, long id) {
                    if (C1.isChecked()) {
                        String F = V1.getItemAtPosition(which).toString();
                        File dir = getFilesDir();
                        File file = new File(dir, F);
                        boolean deleted = file.delete();
                        Bundle b = new Bundle();
                        b.putString("Action", A);
                        Intent i = new Intent(getApplicationContext(), FilePicker.class);
                        i.putExtras(b);
                        startActivity(i);
                    } else {

                        try {
                            if (A.equals("W")) {
                                String f = V1.getItemAtPosition(which).toString();
                                Bundle b = new Bundle();
                                b.putString("filename", f);
                                Intent i = new Intent(getApplicationContext(), FileWriter.class);
                                i.putExtras(b);
                                startActivity(i);


                            }
                            else if(A.equals("E")){
                                String f = V1.getItemAtPosition(which).toString();
                                Bundle b = new Bundle();
                                b.putString("filename", f);
                                Intent i = new Intent(getApplicationContext(), Lock.class);
                                i.putExtras(b);
                                startActivity(i);
                            }
                            else if(A.equals("X")){
                                String f = V1.getItemAtPosition(which).toString();
                                Bundle b = new Bundle();
                                b.putString("filename", f);
                                Intent i = new Intent(getApplicationContext(), ExportFile.class);
                                i.putExtras(b);
                                startActivity(i);
                            }

                        } catch (Exception e) {
                            Log.d("myhd", e.getMessage());
                        }
                    }

                }
            });
        } catch (Exception e) {
        }

        B1 = (Button) findViewById(R.id.B1);

        B1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {


                    startActivity(new Intent(getApplicationContext(), Menu.class));


                } catch (Exception e) {
                    Log.d("myTag", e.getMessage());
                }
            }
        });


    }
}
