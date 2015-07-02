package info.vertecs.expensemanager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import info.vertecs.expensemanager.adapter.DatabaseHandler;

public class LoginActivity extends Activity {
    EditText username;
    EditText password;
    Button Login;
    TextView RegisterIntent;
    private CheckBox remeber_me;
    public static final String PREFS_NAME = "preferences";
    public static final String PREFS_USERNAME = "username";
    public static final String PREFS_PASSWORD = "password";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText)findViewById(R.id.edt_usr_name);
        password = (EditText)findViewById(R.id.edt_psw_wrd);
        setTitle("Xpense Manager");
        remeber_me = (CheckBox)findViewById(R.id.chk_box_remeber_me);
        SharedPreferences pref = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        String usernameg = pref.getString(PREFS_USERNAME, null);
        String passwordg = pref.getString(PREFS_PASSWORD, null);


            username.setText(usernameg);
            password.setText(passwordg);

        String date = "13/06/2015";
        if (username == null || password == null) {
            //Prompt for username and password
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void OnAuthenticate(View v) {
        DatabaseHandler db = new DatabaseHandler(this);
        String usr_name = username.getText().toString();
        String psw_wrd = password.getText().toString();

        if (!db.validatePwd(usr_name,psw_wrd)) {
            Toast.makeText(getApplicationContext(),"Invalid Username/Password",Toast.LENGTH_LONG).show();
        } else {
            if(remeber_me.isChecked()) {
                SharedPreferences pref = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor prefEditor = pref.edit();
                prefEditor.putBoolean("logged_in", true);
                prefEditor.apply();
                getSharedPreferences(PREFS_NAME,MODE_PRIVATE)
                        .edit()
                        .putString(PREFS_USERNAME, usr_name)
                        .commit();
            }
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();


        }

    }
// Test DBAdd Data Class Function;
    public void OnRegisterIntent(View v)
    {
        Intent reg = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(reg);

    }

}
