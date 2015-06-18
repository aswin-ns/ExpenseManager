package info.androidhive.expensemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import info.androidhive.expensemanager.adapter.DatabaseHandler;
import info.androidhive.expensemanager.adapter.LoginActivity;
import info.androidhive.tabsswipe.R;
import info.androidhive.expensemanager.adapter.Login;


public class RegisterActivity extends Activity {
EditText username;
    EditText password;
    Button authenticate_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = (EditText)findViewById(R.id.edt_usr_name_register);
        password = (EditText)findViewById(R.id.edt_psw_wrd_register);
        authenticate_register = (Button)findViewById(R.id.btn_authenticate_login_register);
        final DatabaseHandler db = new DatabaseHandler(this);
        authenticate_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usr_name = username.getText().toString();
                String psw_wrd = password.getText().toString();

                db.addContact(new Login(usr_name,psw_wrd));
                Toast.makeText(getApplicationContext(),"Registration Sucessfull",Toast.LENGTH_LONG).show();
                List<Login> login = db.getAllContacts();

                for (Login cn : login) {
                    String log = "Id: "+cn.getUsername()+" ,Name: " + cn.getUsername() + " ,Phone: " + cn.getPassword();
                    // Writing Contacts to log
                    Log.d("Name: ", log);
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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

}
