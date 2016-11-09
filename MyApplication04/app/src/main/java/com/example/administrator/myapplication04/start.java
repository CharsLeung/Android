package com.example.administrator.myapplication04;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by Administrator on 2016-11-06.
 */
public class start extends AppCompatActivity {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        getWindow().setFormat(PixelFormat.RGBA_8888);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DITHER);

        setContentView(R.layout.start_world);

        //Display the current version number
        PackageManager pm = getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo("com.example.administrator.myapplication04", 0);
            TextView versionNumber = (TextView) findViewById(R.id.versionNumber);
            versionNumber.setText("Version " + pi.versionName);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                /* Create an Intent that will start the Main WordPress Activity. */
//                    String gifPath = "file:///android_asset/progress.gif";
//                    WebView wvGif = (WebView) findViewById(R.id.webGif);
//                    wvGif.loadUrl(gifPath);
                    Intent mainIntent = new Intent(start.this, MainActivity.class);
                    start.this.startActivity(mainIntent);
                    start.this.finish();
                }
            }, 2900); //2900 for release
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }



    }
}
