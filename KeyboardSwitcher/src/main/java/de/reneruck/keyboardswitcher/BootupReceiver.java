package de.reneruck.keyboardswitcher;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * Created by Rene on 30/07/13.
 */
public class BootupReceiver extends BroadcastReceiver {

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent openInputSwitcherIntent = new Intent(context, InputMethodSwitcher.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, openInputSwitcherIntent, 0);
        Notification notification = new Notification.Builder(context)
                .setContentTitle(context.getString(R.string.notification_title))
                .setContentIntent(pIntent)
                .setSmallIcon(R.drawable.ic_stat_hardware_keyboard)
                .setContentText(context.getString(R.string.notification_content))
                .build();
        notification.flags |= Notification.FLAG_NO_CLEAR;
        notification.flags |= Notification.FLAG_ONGOING_EVENT;

        notificationManager.notify(1337, notification);
    }

    private void showInputMethodPicker(Context context) {
        InputMethodManager imeManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imeManager != null) {
            imeManager.showInputMethodPicker();
        } else {
            Toast.makeText(context, "Not possible", Toast.LENGTH_LONG).show();
        }
    }
}
