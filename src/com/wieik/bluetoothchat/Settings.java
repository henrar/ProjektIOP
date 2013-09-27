
package com.wieik.bluetoothchat;




import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class Settings extends Activity implements OnClickListener {

    CheckBox notify, sound,vibrate;

    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);   
        setTitle(R.string.set1);

        notify = (CheckBox) findViewById(R.id.notify);
        sound = (CheckBox) findViewById(R.id.sound);
        vibrate = (CheckBox) findViewById(R.id.vibrate);

        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(this);
        loadSavedPreferences();
        setResult(Activity.RESULT_CANCELED);
    }
//load preferences
    private void loadSavedPreferences() {
    	
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        boolean checkBoxValue = sharedPreferences.getBoolean("Notify", false);
        boolean checkBoxValue1 = sharedPreferences.getBoolean("Sound", false);
        boolean checkBoxValue2 = sharedPreferences.getBoolean("Vibrate", false);
        //String name = sharedPreferences.getString("storedName", "YourName");
        if (checkBoxValue) {
            notify.setChecked(true);
        } else {
            notify.setChecked(false);
        }
        if (checkBoxValue1) {
            sound.setChecked(true);
        } else {
            sound.setChecked(false);
        }
        if (checkBoxValue2) {
            vibrate.setChecked(true);
        } else {
            vibrate.setChecked(false);
        }


    }
//save boolean
    private void savePreferences(String key, boolean value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }
//save String
    private void savePreferences(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
//on click listener
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        savePreferences("Notify", notify.isChecked());
        savePreferences("Sound", sound.isChecked());
        savePreferences("Vibrate", vibrate.isChecked());
        Toast.makeText(Settings.this, R.string.saved,
                Toast.LENGTH_LONG).show();

        finish();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();


    }

}
