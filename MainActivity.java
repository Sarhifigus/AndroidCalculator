package com.example.oli.calculator;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
    private String text = "";
    private TextView T1;
    private Button B1, B2, B3, B4, B5, B6, B7, B8, B9, B10, B11, B12, B13,B14,B15, B16;
    private boolean ev = false;
    private String key, Key;// key is hash for access, Key is hash for dead mans trigger

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.B1:
                text+="1"; T1.setText(text); break;
            case R.id.B2:
                text+="2"; T1.setText(text); break;
            case R.id.B3:
                text+="3"; T1.setText(text); break;
            case R.id.B4:
                text+="4"; T1.setText(text); break;
            case R.id.B5:
                text+="5"; T1.setText(text); break;
            case R.id.B6:
                text+="6"; T1.setText(text); break;
            case R.id.B7:
                text+="7"; T1.setText(text); break;
            case R.id.B8:
                text+="8"; T1.setText(text); break;
            case R.id.B9:
                text+="9"; T1.setText(text);
                startActivity(new Intent(getApplicationContext(), Menu.class)); break;
            case R.id.B10:
                 if(ev) { T1.setText(String.valueOf(eval(T1.getText().toString()) )); text = T1.getText().toString(); ev = false; } else{   ev = true;} if(ev) { text+="+"; } T1.setText(text); break;

            case R.id.B11:
                text+="0"; T1.setText(text); break;
            case R.id.B12:
                if(ev) { T1.setText(String.valueOf(eval(T1.getText().toString()) )); text = T1.getText().toString(); ev = false; } else{   ev = true;} text+="-"; T1.setText(text); break;

            case R.id.B15:
                if(ev) { T1.setText(String.valueOf(eval(T1.getText().toString()) )); text = T1.getText().toString(); ev = false; } else{   ev = true;} text+="/"; T1.setText(text); break;

            case R.id.B13:
                if(ev) { T1.setText(String.valueOf(eval(T1.getText().toString()) )); text = T1.getText().toString(); ev = false; } else{   ev = true;} text+="*"; T1.setText(text); break;

            case R.id.B16:
                text+="."; T1.setText(text); break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File dir = getFilesDir();
        File f = new File(dir, "Calculator++SettingsHSH.txt");
        File f2 = new File(dir, "Calculator++SettingsDMT.txt");
        Log.d("EE", String.valueOf(f.exists()));
        if(f.exists() && f2.exists())
        {
            Log.d("fg", "true");
        }
        else {
            startActivity(new Intent(getApplicationContext(), EditSettings.class));
            Log.d("fg", "false");
        }

        key = new String(new Filer(getApplicationContext()).read("Calculator++SettingsHSH.txt"));
        Key = new String(new Filer(getApplicationContext()).read("Calculator++SettingsDMT.txt"));

        T1 = (TextView) findViewById(R.id.T1);
        T1.setText("0");
        B1 = (Button) findViewById(R.id.B1);
        B2 = (Button) findViewById(R.id.B1);
        B3 = (Button) findViewById(R.id.B3);
        B4 = (Button) findViewById(R.id.B4);
        B5 = (Button) findViewById(R.id.B5);
        B6 = (Button) findViewById(R.id.B6);
        B7 = (Button) findViewById(R.id.B7);
        B8 = (Button) findViewById(R.id.B8);
        B9 = (Button) findViewById(R.id.B9);
        B10 = (Button) findViewById(R.id.B10);
        B11 = (Button) findViewById(R.id.B11);
        B12 = (Button) findViewById(R.id.B12);
        B13 = (Button) findViewById(R.id.B13);
        B14 = (Button) findViewById(R.id.B14);
        B15 = (Button) findViewById(R.id.B15);
        B16 = (Button) findViewById(R.id.B16);
        B1.setOnClickListener(this);
        B2.setOnClickListener(this);
        B3.setOnClickListener(this);
        B4.setOnClickListener(this);
        B5.setOnClickListener(this);
        B6.setOnClickListener(this);
        B7.setOnClickListener(this);
        B8.setOnClickListener(this);
        B9.setOnClickListener(this);
        B10.setOnClickListener(this);
        B11.setOnClickListener(this);
        B12.setOnClickListener(this);
        B13.setOnClickListener(this);
        B15.setOnClickListener(this);
        B16.setOnClickListener(this);

        B14.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                try {
                    T1.setText(String.valueOf(eval(T1.getText().toString())));
                    // Log.d("A1", key);
                    // Log.d("A2", T1.getText().toString());
                    ev = false;
                    text = T1.getText().toString();
                    Log.d("V1", text);
                    Log.d("V2", key);

                    if (key.equals(text)) {
                        startActivity(new Intent(getApplicationContext(), Menu.class));
                    }
                    if (Key.equals(text)) {
                        String[] Files;
                        File path = getApplicationContext().getFilesDir();
                        Files = path.list();

                        File dir = getFilesDir();
                        for (int h = 0; h < Files.length; h++) {
                            File file = new File(dir, Files[h]);

                            try {
                                FileOutputStream stream = new FileOutputStream(file);
                                try {
                                    stream.write(CryptEngine.getRandomBytes(file.length()));
                                    stream.write(CryptEngine.getRandomBytes(file.length()));
                                    stream.write(CryptEngine.getRandomBytes(file.length()));
                                    stream.write(CryptEngine.getRandomBytes(file.length()));
                                    stream.write(CryptEngine.getRandomBytes(file.length()));
                                    stream.write(CryptEngine.getRandomBytes(file.length()));
                                    stream.write(CryptEngine.getRandomBytes(file.length()));
                                    stream.write(CryptEngine.getRandomBytes(file.length()));
                                } finally {
                                    stream.close();
                                }
                            } catch (Exception e) {
                            }
                            boolean del = file.delete();

                        }
                    }
                }
                catch (Exception e) { Log.d("myTag", e.getMessage()); }
            }
        });
        // Example of a call to a native method
        //TextView tv = (TextView) findViewById(R.id.sample_text);
        //tv.setText(stringFromJNI());
    }
    private double eval(String equation) {
        String A =  new String();
        double k;
        String B =  new String();
        boolean n = false;
        int op = 1;
        for(int a = 0; a<equation.length(); a++)
        {
            if(equation.charAt(a)!='+' && equation.charAt(a)!='-' && equation.charAt(a)!='/' && equation.charAt(a)!='*') { //equals
                if(!n){A+=equation.charAt(a);}
                else{ B+=equation.charAt(a);}
            }

            else {
                n = true;
                switch(equation.charAt(a)) {
                    case '+':
                        op = 1;
                        break;
                    case '-':
                        op = 2;
                        break;
                    case '*':
                        op = 3;
                        break;
                    case '/':
                        op = 4;
                        break;
                }
            }
        }
        double a1 = Double.parseDouble(A);
        double a2 = Double.parseDouble(B);
        double ans = 1.0;
        switch(op) {
            case 1:
                ans = a1+a2; break;
            case 2:
                ans = a1-a2; break;
            case 3:
                ans = a1 * a2; break;
            case 4:
                ans = a1 / a2; break;
        }
        return ans;
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
}
