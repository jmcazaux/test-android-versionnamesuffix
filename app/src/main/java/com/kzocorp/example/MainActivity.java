package com.kzocorp.example;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Date;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PackageInfo pInfo;

        String output = "";
        String version;
        String name;
        String packageName;
        int versionCode;
        long versionUpdate;

        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            version = pInfo.versionName;
            int labelId = this.getApplicationContext().getApplicationInfo().labelRes;
            name = this.getString(labelId);
            packageName = pInfo.packageName;
            versionCode = pInfo.versionCode;
            versionUpdate = pInfo.lastUpdateTime;

            output += "Application name.... " + name + "\n";
            output += "Version............. " + version + "\n";
            output += "Package name........ " + packageName + "\n";
            output += "Version code........ " + versionCode + "\n";
            output += "Version Update...... " + DateFormat.format("dd/MM/yy kk:mm:ss", new Date(versionUpdate)) + "\n";


        } catch (PackageManager.NameNotFoundException e) {
            output = String.format("Got %s (%s) while getting package information... Ignoring.", e.getClass().getSimpleName(), e.getMessage());
            Log.e("TEST", output , e);
        }

        TextView text = (TextView) this.findViewById(R.id.text);

        text.setText(output);

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
