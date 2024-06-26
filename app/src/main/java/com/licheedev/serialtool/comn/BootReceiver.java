package com.licheedev.serialtool.comn;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            Intent toIntent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
            context.startActivity(toIntent);
        }
    }
}

