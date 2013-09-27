package com.wieik.bluetoothchat;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.widget.RemoteViews;

public class BTBroadcastReceiver extends BroadcastReceiver {
	
	private static final int STATE_DISABLED = 0;
	private static final int STATE_ENABLED = 1;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
		PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "YOUR TAG");
		wl.acquire();

		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.widget_layout);
		
		int state =  Utility.getBluetoothState(context);
		
		if (state == STATE_ENABLED) {
            remoteViews.setTextViewText(R.id.update, "ON");
            ComponentName thiswidget = new ComponentName(context, BTWidgetProvider.class);
    		AppWidgetManager manager = AppWidgetManager.getInstance(context);
    		manager.updateAppWidget(thiswidget, remoteViews);
    		wl.release();
		} else if (state == STATE_DISABLED) {
			remoteViews.setTextViewText(R.id.update, "OFF");
			ComponentName thiswidget = new ComponentName(context, BTWidgetProvider.class);
			AppWidgetManager manager = AppWidgetManager.getInstance(context);
			manager.updateAppWidget(thiswidget, remoteViews);
			wl.release();
		}
	}
	public void setOnetimeTimer(Context context){
		AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, BTBroadcastReceiver.class);
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
		am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi);
	}
}