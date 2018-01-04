package com.example.richardchen.javabasics;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;


public class MainActivity extends Activity {
    String TAG = "javabasics";
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
//String
        String s = "NOW";
        String t = s + " is the time.";
        String t1 = s + " " + 23.4;
        char c = t.charAt(2);
        Log.d(TAG,t1);
        int len = t1.length();
        Log.d(TAG,String.valueOf(len));
//Array
        char[] text = "Now is the time".toCharArray();
        char[] copy = new char[100];
        System.arraycopy(text,4,copy,0,10);
//        Log.d(TAG,String.valueOf(copy));

        System.arraycopy(text,4,copy,0,10);
//Byte Array
        byte[] data = new byte[100];
        Arrays.fill(data, (byte)-1);
        Arrays.fill(data, 5,10, (byte)-2);

//        File homedir=new File(System.getProperty("user.home"));
//        File f = new File (homedir, ".configfile");
        File f = new File("/storage/usbdisk6/config1");
        long filelenght=f.length();
        boolean b1 = f.exists();
        boolean b2 = f.canRead();


        try {
//          Basic usage
//          FileReader fr = new FileReader("/storage/usbdisk6/config");
            File cfg = new File("/storage/usbdisk6/config2");
            FileReader fr = new FileReader(cfg);
            BufferedReader br = new BufferedReader(fr);
            while (br.ready()) {
                System.out.println(br.readLine());
            }
            fr.close();

//Try context
            InputStream inputStream = this.openFileInput("config");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                //ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }




        File dir1 = new File ("/storage/usbdisk6");
        String[] allfiles = dir1.list();

        String[] somefiles = dir1.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith("NVR");
                //return false;
            }
        });

        File dir2 = new File ("/storage/usbdisk6/subdir");
        dir2.mkdir();
 //       f.renameTo(new File(dir2,"VVVV"));

        File file2 = new File(dir2,"VVVV");

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
