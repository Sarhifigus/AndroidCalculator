package com.example.oli.calculator;

import android.provider.Settings;
import android.util.Log;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import javax.crypto.*;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.spec.*;


public class CryptEngine {
    private String a;
    private byte[] digest;
    private byte[] key;

    private String salt = "";
    private byte[] encrypted;
    private int N;
    private byte[] decrypted;
    private String initVector = "RandomInitVector";// this needs to be randomly generated, potential from secure id however new one should be used each time

    public CryptEngine(String a, String salt)
    {
        this.a =a;
        this.salt = salt;


    }
    static byte[] getRandomBytes(long length)
    {
        char[] array = new char[(int)length];
        Arrays.fill(array, 'z');
        return new String(array).getBytes();



    }
    static byte[] getSinglehash(byte[] text, String salt)
    {
        byte[] digest = null;
        try {
            MessageDigest MD = MessageDigest.getInstance("SHA-512");
            MD.update(salt.getBytes());


            digest = MD.digest(text);

        }
        catch(Exception e){}
        return digest;

    }


    public byte[] Encrypt(String key, byte[] text)

    {
        if (a.equals("OEA512"))
        {
            encrypted = text;// new OEA().OEAEncryptWithABS(new String(text),new String(key),salt, 1 ).getBytes();
        }
        if (a.equals("AES128")) {
            try {
                SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEwithSHA256and128BITAES-CBC-BC");
                KeySpec spec = new PBEKeySpec(key.toCharArray(),salt.getBytes(), 65536, 128);

                SecretKey tmp = factory.generateSecret(spec);
                SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

                IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(Cipher.ENCRYPT_MODE, secret, iv);
                encrypted = cipher.doFinal(text);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }
        if (a.equals("AES256")) {
            try {
                SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEwithSHA256and256BITAES-CBC-BC");
                KeySpec spec = new PBEKeySpec(key.toCharArray(),salt.getBytes(), 65536, 256);

                SecretKey tmp = factory.generateSecret(spec);
                SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

                IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(Cipher.ENCRYPT_MODE, secret, iv);
                encrypted = cipher.doFinal(text);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }
        if (a.equals("BLOWFISH384")) {
            try {
                SecretKeyFactory factory = SecretKeyFactory.getInstance("HmacSHA512");
                KeySpec spec = new PBEKeySpec(key.toCharArray(),salt.getBytes(), 65536, 448);

                SecretKey tmp = factory.generateSecret(spec);
                SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "Blowfish");


                Cipher cipher = Cipher.getInstance("Blowfish");
                cipher.init(Cipher.ENCRYPT_MODE, secret);
                encrypted = cipher.doFinal(text);
            } catch (Exception ex) {System.out.println(ex.getMessage());
            }

        }

        return encrypted;
    }

    public byte[] Decrypt(String key, byte[] text)

    {
        if (a.equals("OEA512"))
        {
            decrypted = text;// new OEA().OEAEncryptWithABS(new String(text),new String(key),salt, 1 ).getBytes();
        }
        if (a.equals("AES128")) {
            try {
                SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEwithSHA256and128BITAES-CBC-BC");
                KeySpec spec = new PBEKeySpec(key.toCharArray(),salt.getBytes(), 65536, 128);

                SecretKey tmp = factory.generateSecret(spec);
                SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

                IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(Cipher.DECRYPT_MODE, secret, iv);
                decrypted = cipher.doFinal(text);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }
        if (a.equals("AES256")) {
            try {
                SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEwithSHA256and256BITAES-CBC-BC");
                KeySpec spec = new PBEKeySpec(key.toCharArray(),salt.getBytes(), 65536, 256);

                SecretKey tmp = factory.generateSecret(spec);
                SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

                IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(Cipher.DECRYPT_MODE, secret, iv);
                decrypted = cipher.doFinal(text);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        }
        if (a.equals("BLOWFISH384")) {
            try {

                SecretKeyFactory factory = SecretKeyFactory.getInstance("HmacSHA512");
                KeySpec spec = new PBEKeySpec(key.toCharArray(),salt.getBytes(), 65536, 448);

                SecretKey tmp = factory.generateSecret(spec);
                SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "Blowfish");


                Cipher cipher = Cipher.getInstance("Blowfish");
                cipher.init(Cipher.DECRYPT_MODE, secret);
                decrypted = cipher.doFinal(text);
            } catch (Exception ex) {System.out.println(ex.getMessage());
            }

        }

        return decrypted;
    }








}
