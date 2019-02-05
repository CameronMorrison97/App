package com.example.admin.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.audiofx.Visualizer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*
    * Request App Permissions.
    * */
    public void requestPermissions(){
        int requestCode = 0;
        String[] Permissions = new String[2];
        Permissions[0] = "android.permission.WRITE_EXTERNAL_STORAGE";
        Permissions[1] = "android.permission.READ_EXTERNAL_STORAGE";
        ActivityCompat.requestPermissions(MainActivity.this, Permissions,requestCode);
    }

    /*
    *   Queries the file system to run ls on the android device.
    * */
    public void queryFileSystem() throws IOException {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            System.out.println("Permissions Denied READ");

            return;
        }

        System.out.println("Permissions Granted READ");

        String s;
        Process p;
        int lineNum = 0;
        try {
            p = Runtime.getRuntime().exec("ls");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null)
                Log.d("line" + lineNum++, "line: " + s);
            p.waitFor();
            System.out.println ("exit: " + p.exitValue());
            p.destroy();
        } catch (Exception e) {}

    }

    /* Code 'borrowed' form JustinMorris. On Stack Overflow: https://stackoverflow.com/questions/2661536/how-to-programmatically-take-a-screenshot*/
    /* Returns the current view as a bitmap image. Can return null if the permissions have been denied.
    * In this case the background for the config screen will just be a single colour.*/
    public Bitmap screenShot(View view) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            System.out.println("Permissions Denied WRITE");

            return null;
        }
            System.out.println("Permissions Granted WRITE");
            Bitmap bitmap = Bitmap.createBitmap(view.getWidth(),
                    view.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            view.draw(canvas);
            return bitmap;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermissions();

        final Button Cancel = (Button) findViewById(R.id.Cancel);
        final Button Update = (Button) findViewById(R.id.Update);

        Cancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Bitmap btmap = screenShot(view);

                try {
                    queryFileSystem();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(MainActivity.this, AnotherActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                startActivity(intent);
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "Updated settings!", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}
