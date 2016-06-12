package com.kree.keehoo.yourcarinfo.BroadCastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.kree.keehoo.yourcarinfo.Services.NotificationService;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context, NotificationService.class);
        serviceIntent.putExtras(intent);
        context.startService(serviceIntent);
    }
}
