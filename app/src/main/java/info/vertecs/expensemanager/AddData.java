package info.vertecs.expensemanager;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import info.vertecs.tabsswipe.R;
import info.vertecs.expensemanager.adapter.DataClass;
import info.vertecs.expensemanager.adapter.DatabaseHandlerAddData;


public class AddData extends BaseActivity {
    Button type;
    EditText pos_amnt;
    EditText pos_note;
    EditText pos_date;
    ImageButton change_date;
    Button enter; //enter button//
    String tye,nte,date;
    int amnt,neg_amnt;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("STRING_I_NEED");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("STRING_I_NEED");
        }

        final DatabaseHandlerAddData db = new DatabaseHandlerAddData(this);
        dateView = (TextView) findViewById(R.id.textView2);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        pos_amnt = (EditText)findViewById(R.id.edt_amnt);
        pos_note = (EditText)findViewById(R.id.edt_note);


        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);
        type = (Button)findViewById(R.id.btn_type);
        type.setText(newString);
        enter = (Button)findViewById(R.id.button3);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("enter","enter");
                tye = type.getText().toString();
                amnt = Integer.parseInt(pos_amnt.getText().toString());
                nte = pos_note.getText().toString();
                date = dateView.getText().toString();
               db.addPosData(new DataClass(tye,amnt,nte,date));
                Log.d("Sucess",date);
                List<DataClass> contacts = db.getPosData(date);

                for (DataClass cn : contacts) {
                    String log = Integer.toString(cn.getAmnt());
                    // Writing Contacts to log
                    Log.d("Name: ", log);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void OnChangeDate(View v)
    {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT)
                .show();

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2+1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    // use shared_menu from inherited BaseActivity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
