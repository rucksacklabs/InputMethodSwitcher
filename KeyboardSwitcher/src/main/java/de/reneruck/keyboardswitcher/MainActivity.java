package de.reneruck.keyboardswitcher;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.provider.Settings;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private boolean isSet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button registerButton = (Button) findViewById(R.id.toggleButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                if(isSet) {
                    notificationManager.cancel(1337);
                    isSet = false;
                } else {


                    Intent openInputSwitcherIntent = new Intent(getApplicationContext(), InputMethodSwitcher.class);
                    PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), 0, openInputSwitcherIntent, 0);
                    Notification notification = new Notification.Builder(getApplicationContext())
                            .setContentTitle(getApplicationContext().getString(R.string.notification_title))
                            .setContentIntent(pIntent)
                            .setSmallIcon(R.drawable.ic_stat_hardware_keyboard)
                            .setContentText(getString(R.string.notification_content))
                            .build();
                    notification.flags |= Notification.FLAG_NO_CLEAR;
                    notification.flags |= Notification.FLAG_ONGOING_EVENT;

                    notificationManager.notify(1337,notification);
                    isSet = true;

                }
            }
        });
    }

    private void showInputMethodPicker(Context context) {
        InputMethodManager imeManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imeManager != null) {
            imeManager.showInputMethodPicker();
        } else {
            Toast.makeText(context, "Not possible", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
