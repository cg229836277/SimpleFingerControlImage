package com.example.mixoperateimagedemo;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.TextView;
import org.w3c.dom.Text;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
        
        SingleFingerView testView = new SingleFingerView(this, BitmapFactory.decodeResource(getResources(), R.drawable.example));
        setContentView(testView);
    }
}
