package com.example.richardchen.javabasics;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
