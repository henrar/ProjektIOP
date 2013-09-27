package com.wieik.bluetoothchat;

/**
 * Created by Jakub Jachym on 21.09.13.
 */
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class NotificationReceiver extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
            setTitle(R.string.new_message_r);
        setContentView(R.layout.r_message);

        TextView txt = (TextView) findViewById(R.id.r_message);
        txt.setText(MainActivity.wiadomosc);


    }
}
