package info.vertecs.expensemanager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;


public class Splash extends Activity {

    private static int SPLASH_TIME_OUT = 1500;
    // TODO: Move this to a BaseActivity
    public static final String PREFS_NAME = "preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Xpense Manager");
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        SharedPreferences pref = getSharedPreferences(PREFS_NAME,0);
        Boolean already_logged_in = pref.getBoolean("logged_in",false);
        if (already_logged_in) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(Splash.this, MainActivity.class);
                    startActivity(i);

                }
            }, SPLASH_TIME_OUT);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(Splash.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }

    }

}
