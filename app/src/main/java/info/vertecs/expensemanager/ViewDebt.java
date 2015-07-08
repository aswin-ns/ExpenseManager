package info.vertecs.expensemanager;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import info.vertecs.expensemanager.adapter.DatabaseHandlerAddDebt;
import info.vertecs.expensemanager.adapter.Debt;

public class ViewDebt extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewDebt.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ViewDebt.this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        setContentView(R.layout.activity_view_debt);
        DatabaseHandlerAddDebt db = new DatabaseHandlerAddDebt(this);
        setTitle("Xpense Manager");
        ArrayList<Debt> newlist = (ArrayList<Debt>) db.getDebtData();
//
        if (newlist.isEmpty()) {
            AlertDialog alertDialog = new AlertDialog.Builder (ViewDebt.this).create();
// Setting Dialog Title
            alertDialog.setTitle("Hurrraayyy !!!");

            // Setting Dialog Message
            alertDialog.setMessage("You Have No Debts");

            // Setting Icon to Dialog


            // Setting OK Button
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Write your code here to execute after dialog closed
                    Toast.makeText(getApplicationContext(), "THank You", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }
            });

            // Showing Alert Message
            alertDialog.show();

        } else {

            ListView ls = (ListView) findViewById(R.id.listView);
            ls.setAdapter(new ListAdapterDebt(getApplicationContext(), newlist));

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_debt, menu);
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
    private void populateListView()
    {

    }

}

