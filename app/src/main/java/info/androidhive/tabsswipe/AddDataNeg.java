package info.androidhive.tabsswipe;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import info.androidhive.tabsswipe.adapter.DatabaseHandlerAddData;


public class AddDataNeg extends Activity {
    Button btn_type;
    EditText edt_amnt;
    EditText edt_note;
    Button enter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data_neg);
        final String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("STRING_I_NEED");
                btn_type = (Button)findViewById(R.id.button_type_1);
                btn_type.setText(newString);

            }
        } else {
            newString= (String) savedInstanceState.getSerializable("STRING_I_NEED");
        }
        edt_amnt = (EditText)findViewById(R.id.editText_amount);
        edt_note = (EditText)findViewById(R.id.edt_note);
        String amnts = edt_amnt.getText().toString();
        final int amnt = Integer.parseInt(amnts);
        final String note = edt_note.getText().toString();
        final DatabaseHandlerAddData db = new DatabaseHandlerAddData(this);
        enter = (Button)findViewById(R.id.button_enter);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addNegData(newString,note,amnt,);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_data_neg, menu);
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
