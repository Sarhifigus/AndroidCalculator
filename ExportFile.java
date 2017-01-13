package com.example.oli.calculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;

public class ExportFile extends AppCompatActivity {

    private Button B1, B2;
    private EditText E1, E2;
    private String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        this.filename = b.getString("filename");
        setContentView(R.layout.activity_export_file);
        B1 = (Button) findViewById(R.id.B1);
        B2 = (Button) findViewById(R.id.B2);
        E1 = (EditText) findViewById(R.id.E1);
        E2 = (EditText) findViewById(R.id.E2);
        B1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                try {
                   startActivity(new Intent(getApplicationContext(), Menu.class) );
                }
                catch (Exception e) { Log.d("myTag", e.getMessage()); }
            }
        });
        B2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                try {
                    String emailaddr = E1.getText().toString();
                    String subject = E2.getText().toString();

                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.putExtra(Intent.EXTRA_EMAIL, new String[]{emailaddr});
                    //email.putExtra(Intent.EXTRA_CC, new String[]{ to});
                    //email.putExtra(Intent.EXTRA_BCC, new String[]{to});
                    email.putExtra(Intent.EXTRA_SUBJECT, subject);
                    //email.putExtra(Intent.EXTRA_TEXT, new Filer(getApplicationContext()).read(filename));

                    String sdCard = Environment.getExternalStorageDirectory().getAbsolutePath();
                    Uri uri = Uri.fromFile(new File(sdCard +
                            new String(new char[sdCard.replaceAll("[^/]", "").length()])
                                    .replace("\0", "/..") + getFilesDir() + "/" + filename));
                    email.putExtra(android.content.Intent.EXTRA_STREAM, uri);


                    //need this to prompts email client only
                    email.setType("message/rfc822");

                    startActivity(Intent.createChooser(email, "Choose an Email client :"));

                }
                catch (Exception e) { Log.d("myTag", e.getMessage()); }
            }
        });
    }
}
