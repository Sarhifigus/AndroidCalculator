package com.example.oli.calculator;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import java.net.URLEncoder;
import java.util.List;

import static java.security.AccessController.getContext;

public class Lock extends AppCompatActivity {

    private RadioButton R1, R2, R3, R4, R5;
    private String a, password, filename;
    private Button B1, B2, B3;
    private CheckBox C1;
    private EditText E1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lock);
            Bundle b = getIntent().getExtras();
            this.filename = b.getString("filename");
            R1 = (RadioButton) findViewById(R.id.R1);
            R2 = (RadioButton) findViewById(R.id.R2);
            R3 = (RadioButton) findViewById(R.id.R3);
            R4 = (RadioButton) findViewById(R.id.R4);
            R5 = (RadioButton) findViewById(R.id.R5);
            C1 = (CheckBox) findViewById(R.id.checkBox);
            B1 = (Button) findViewById(R.id.B1);
            B2 = (Button) findViewById(R.id.B2);
            B3 = (Button) findViewById(R.id.B3);
            E1 = (EditText) findViewById(R.id.E1);

            B3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Menu.class));
                }
            });
            C1.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {

                                          List<ApplicationInfo> packages;
                                          PackageManager pm;
                                          pm = getPackageManager();
                                          //get a list of installed apps.
                                          packages = pm.getInstalledApplications(0);

                                          ActivityManager mActivityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);

                                          for (ApplicationInfo packageInfo : packages) {
                                              if ((packageInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1) continue;
                                              if (packageInfo.packageName.equals("com.example.oli.calculator")) continue;
                                              mActivityManager.killBackgroundProcesses(packageInfo.packageName);
                                          }


                                      }
                                  }
            );
            R1.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {
                                          R2.setChecked(false);
                                          R3.setChecked(false);
                                          R4.setChecked(false);
                                          R5.setChecked(false);
                                          a = "AES128";

                                      }
                                  }
            );
            R2.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {
                                          R1.setChecked(false);
                                          R3.setChecked(false);
                                          R4.setChecked(false);
                                          R5.setChecked(false);
                                          a = "AES256";

                                      }
                                  }
            );


            R3.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {
                                          R1.setChecked(false);
                                          R2.setChecked(false);
                                          R4.setChecked(false);
                                          R5.setChecked(false);
                                          a = "BLOWFISH384";

                                      }
                                  }
            );
            R4.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {
                                          R1.setChecked(false);
                                          R2.setChecked(false);
                                          R3.setChecked(false);
                                          R5.setChecked(false);
                                          a = "KA28";

                                      }
                                  }
            );

            B1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    try {
                        password = E1.getText().toString();
                        Encrypt();
                    } catch (Exception e) {
                        Log.d("myTag", e.getMessage());
                    }
                }
            });
            B2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    try {
                        password = E1.getText().toString();
                        Decrypt();
                    } catch (Exception e) {
                        Log.d("myTag", e.getMessage());
                    }
                }
            });

        }
        catch(Exception ex){Log.d("SHIT", ex.getMessage()); }
    }
    public native String stringFromJNI(String filename,String key,boolean mode);

    private void Encrypt()
    {

        String salt = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);


        CryptEngine E = new CryptEngine(a, salt);


            Filer f = new Filer(getApplicationContext());
            byte[] t = f.read(filename);

            byte[] enc = E.Encrypt(password, t);



            f.Write(filename, enc);







    }
    private void Decrypt()
    {
        String salt = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);

        CryptEngine E = new CryptEngine(a, salt);



            Filer f = new Filer(getApplicationContext());
           byte[] t = f.read(filename);


            byte[] enc = E.Decrypt(password, t);



            f.Write(filename, enc);





        }



}
