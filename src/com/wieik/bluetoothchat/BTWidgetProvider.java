package com.wieik.bluetoothchat;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

public class BTWidgetProvider extends AppWidgetProvider {

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		//Toast.makeText(context, "TimeWidgetRemoved id(s):"+appWidgetIds, Toast.LENGTH_SHORT).show();
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onDisabled(Context context) {
		//Toast.makeText(context, "onDisabled():last widget instance removed", Toast.LENGTH_SHORT).show(); 
		Intent intent = new Intent(context, BTBroadcastReceiver.class);
		PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		alarmManager.cancel(sender);
		super.onDisabled(context);
	}

	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
		AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, BTBroadcastReceiver.class);
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
		am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+ 100 * 1, 1000 , pi);
	}
	
	private static final int STATE_DISABLED = 0;
	private static final int STATE_ENABLED = 1;
	
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		ComponentName thisWidget = new ComponentName(context, BTWidgetProvider.class);

		for (int widgetId : appWidgetManager.getAppWidgetIds(thisWidget)) {
			BluetoothAdapter bm = BluetoothAdapter.getDefaultAdapter();
			int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
			RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
			int state =  Utility.getBluetoothState(context);
			Intent intent = new Intent(context, BTWidgetProvider.class);
			intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
			intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);
			PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			remoteViews.setOnClickPendingIntent(R.id.update, pendingIntent);
			
			if (state == STATE_ENABLED) {
	            bm.disable();
	            remoteViews.setTextViewText(R.id.update, "OFF");
	            appWidgetManager.updateAppWidget(widgetId, remoteViews);
			} else if (state == STATE_DISABLED) {
				bm.enable();
				remoteViews.setTextViewText(R.id.update, "ON");
				appWidgetManager.updateAppWidget(widgetId, remoteViews);
			}
		}
	}

}
