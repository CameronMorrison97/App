package com.example.admin.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button Cancel = (Button) findViewById(R.id.Cancel);
        final Button Update = (Button) findViewById(R.id.Update);

        Cancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "Back to the main Screen", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AnotherActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                startActivity(intent);
            }
        });
    }
}
