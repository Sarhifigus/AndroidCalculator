package com.example.oli.calculator;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.logging.Logger;

public class Filer
{
    private Context c;
    public Filer(Context c)
    {
        this.c = c;
    }
    public String getDir()
    {
        File path = c.getFilesDir();
        try {


        }
        catch (Exception e) { Log.d("myTag2", e.getMessage()); }

        return path.toString();

    }
    public void Write(String filename, byte[] data)
    {


        File path = c.getFilesDir();

        File file = new File(path, filename);
        try {
            FileOutputStream stream = new FileOutputStream(file);
            try {
                stream.write(data);
            } finally {
                stream.close();
            }
        }
        catch (Exception e){}

    }

    public void Write(String filename, String data)
    {


        File path = c.getFilesDir();

        File file = new File(path, filename);
        try {
            FileOutputStream stream = new FileOutputStream(file);
            try {
                stream.write(data.getBytes());
            } finally {
                stream.close();
            }
        }
        catch (Exception e){}

    }
    public byte[] read(String filename)
    {


        File path = c.getFilesDir();

        File file = new File(path, filename);
        int size = (int) file.length();
        byte[] bytes = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
            buf.read(bytes, 0, bytes.length);
            buf.close();
        } catch (Exception  e) {Log.d("CryptError", e.getMessage()); }


       return bytes;
    }

    public String Read(String fileName)
    {

        Context context = c;
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader in = null;

        try {

            in = new BufferedReader(new FileReader(new File(context.getFilesDir(), fileName)));

            while ((line = in.readLine()) != null) stringBuilder.append(line);

        } catch (Exception e) {
            Log.d("myTag", e.getMessage());
        }

        return stringBuilder.toString();


    }



}