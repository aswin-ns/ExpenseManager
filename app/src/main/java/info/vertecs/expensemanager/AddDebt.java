package info.vertecs.expensemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import info.vertecs.expensemanager.adapter.DatabaseHandlerAddDebt;
import info.vertecs.expensemanager.adapter.Debt;


public class AddDebt extends Activity {
    private String array_spinner[];
    EditText amnt;
    AutoCompleteTextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_debt);
       int i = 0;
        DatabaseHandlerAddDebt db = new DatabaseHandlerAddDebt(this);

       String [] getNamesString = db.getName();

        array_spinner=new String[2];
        array_spinner[0]="Borrowed";
        array_spinner[1]="Lended";
        setTitle("Xpense Manager");
        final Spinner spinner_dbt = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(this,
        android.R.layout.simple_spinner_item, array_spinner);
        spinner_dbt.setAdapter(adapter);
name = (AutoCompleteTextView)findViewById(R.id.edt_name_dbt);
        ArrayAdapter<String>autocompleteadapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getNamesString);
        name.setAdapter(autocompleteadapter);
                amnt = (EditText) findViewById(R.id.edt_amnt_dbt);
        Button enter = (Button)findViewById(R.id.btn_enter_dbt);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_s = name.getText().toString();
                int amnt_s = Integer.parseInt(amnt.getText().toString());
                String spinner_s = spinner_dbt.getSelectedItem().toString();
                if ((spinner_s).equals("Borrowed"))
                {
                    amnt_s = amnt_s*-1;
                }
                DatabaseHandlerAddDebt db = new DatabaseHandlerAddDebt(AddDebt.this);
                db.OnAdd(new Debt(name_s,amnt_s,spinner_s));
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
               finish();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_debt, menu);
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
