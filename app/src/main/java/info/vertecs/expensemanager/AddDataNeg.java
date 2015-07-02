package info.vertecs.expensemanager;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import info.vertecs.expensemanager.adapter.DataClass;
import info.vertecs.expensemanager.adapter.DatabaseHandlerAddData;


public class AddDataNeg extends BaseActivity {
    Button btn_type;
    EditText edt_amnt;
    EditText edt_note;
    Button enter;
    String date;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;


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
        dateView = (TextView) findViewById(R.id.textView_date_current_1);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);
        date = dateView.getText().toString();
        edt_amnt = (EditText)findViewById(R.id.editText_amount);
        edt_note = (EditText)findViewById(R.id.edt_text_note);
        setTitle("Xpense Manager");
        final DatabaseHandlerAddData db = new DatabaseHandlerAddData(this);
        enter = (Button)findViewById(R.id.button_enter);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int amnt = Integer.parseInt(edt_amnt.getText().toString());
                String note = edt_note.getText().toString();
                db.addNegData(new DataClass(newString,note,amnt,date));
                Intent intent = new Intent(getApplicationContext(),Tab2.class);
                startActivity(intent);
            }
        });


    }
    public void OnChangeDateNeg(View v)
    {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "Date Selected", Toast.LENGTH_SHORT)
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
