package de.reneruck.keyboardswitcher;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Rene on 30/07/13.
 */
public class InputMethodSwitcher extends Activity {

    private TimerTask timertask;
    private ViewGroup mLayoutRoot;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_switcher_layout);
        checkMyWindowHasFocus();
        mLayoutRoot = (ViewGroup) findViewById(R.id.main_layout);
        InputMethodManager imeManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imeManager != null) {
            imeManager.showInputMethodPicker();
        } else {
            Toast.makeText(this, "Not possible", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public void checkMyWindowHasFocus()
    {
        timertask = new TimerTask() {

            @Override
            public void run() {
                if(mLayoutRoot.hasWindowFocus()) {
                    timer.cancel();
                    finish();
                }
            }
        };
        timer = new Timer();
        timer.schedule(timertask, 500, 50);

    }


}
