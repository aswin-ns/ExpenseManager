package info.androidhive.expensemanager.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import info.androidhive.expensemanager.MainActivity;
import info.androidhive.tabsswipe.R;
import info.androidhive.expensemanager.RegisterActivity;

public class LoginActivity extends Activity {
 EditText username;
    EditText password;
    Button Login;
    TextView RegisterIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText)findViewById(R.id.edt_usr_name);
        password = (EditText)findViewById(R.id.edt_psw_wrd);
        String date = "13/06/2015";


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
    public void OnAuthenticate(View v)
    {
        DatabaseHandler db = new DatabaseHandler(this);

        String usr_name = username.getText().toString();
        String psw_wrd = password.getText().toString();
       List<Login>login =  db.getAllContacts();
        for (Login ln : login) {
            String usr_name_get = ln.getUsername();
            String psw_wrd_get = ln.getPassword();
            Log.d(psw_wrd,usr_name);
            if((usr_name.equals(usr_name_get))&&(psw_wrd.equals(psw_wrd_get)))
            {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(),"Incorrect Values Try Again With Correct Credentials",Toast.LENGTH_LONG).show();
            }

        }
// Test DBAdd Data Class Function;


    }
    public void OnRegisterIntent(View v)
    {
        Intent reg = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(reg);
    }
}
