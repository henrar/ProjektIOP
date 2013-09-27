package com.wieik.bluetoothchat;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;

public class Utility {
	private static final int STATE_DISABLED = 0;
	private static final int STATE_ENABLED = 1;
	private static final int STATE_INTERMEDIATE = 2;

	public static int getBluetoothState(Context context) {
		BluetoothAdapter bm = BluetoothAdapter.getDefaultAdapter();
		int state = bm.getState();
		       
		if(state == BluetoothAdapter.STATE_OFF) {
			return STATE_DISABLED;
		} else if(state == BluetoothAdapter.STATE_ON) {
			return STATE_ENABLED;
		} else {
			return STATE_INTERMEDIATE;
		}
	}
}
