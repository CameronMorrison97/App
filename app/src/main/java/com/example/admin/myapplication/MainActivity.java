package com.example.admin.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.media.audiofx.Visualizer;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
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
        Permissions[0] = "android.permission.READ_EXTERNAL_STORAGE";
        Permissions[1] = "android.permission.READ_EXTERNAL_STORAGE";
        ActivityCompat.requestPermissions(MainActivity.this, Permissions,requestCode);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermissions();

        final Button Cancel = (Button) findViewById(R.id.Cancel);
        final Button Update = (Button) findViewById(R.id.Update);
        final ConstraintLayout constraint = (ConstraintLayout) findViewById(R.id.Constraint);

        Cancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AnotherActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                startActivity(intent);
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // set the background
                getWindow().setBackgroundDrawableResource(R.drawable.download);

                Toast toast = Toast.makeText(getApplicationContext(), "Updated settings!", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}
