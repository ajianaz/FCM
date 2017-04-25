package com.viluvasa.fcm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static boolean isService = false;
    MyFirebaseInstanceIDService TokenID = new MyFirebaseInstanceIDService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Button startserviceButton = (Button) findViewById(R.id.button1);
        startserviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RunService();
            }
        });
        Log.e("Token id ", TokenID.getTokenID());

    }
    private void RunService(){
        startService(new Intent(MainActivity.this, BackgroundService.class));
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
        isService = true;
        Log.e("Service :", "Service Started");
    }
    @Override
    protected void onResume() {
        super.onResume();
        stopService(new Intent(MainActivity.this,
                BackgroundService.class));
        if(isService)
        {
            TextView tv = (TextView) findViewById(R.id.textView1);
            tv.setText("Service Resumed");
            Log.e("Service ", "Service Stoped");
            isService = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RunService();
        Log.e("Service Destroy", "Service Started");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
