package info.androidhive.expensemanager.adapter;

import android.app.Activity;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;

import java.util.Calendar;
import java.util.List;

import info.androidhive.expensemanager.PieGraph;
import info.androidhive.tabsswipe.R;

public class ViewSummary extends Activity {
    Button piegraph;
    LinearLayout graphView;
Button pie;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;

    String date;
    String month_passed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_summary);

        dateView = (TextView) findViewById(R.id.txt_date_summary);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1);
        date = dateView.getText().toString();
        pie = (Button)findViewById(R.id.btn_pie);
        final DatabaseHandlerAddData db = new DatabaseHandlerAddData(this);
        pie.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

              List<SumByClass>a = db.getPie("date");
                String [] type = new String[a.size()];
                  int [] amnt = new int[a.size()];
                  int i = 0;
                int k=a.size();
                for (SumByClass cn : a) {
                    type[i] = cn.getGroup_name();
                     amnt[i] = cn.getAmnt();
                    i++;

                }

                Log.d("Size Of A",String.valueOf(a.size()));

                PieGraph graph = new PieGraph();
                GraphicalView gView = graph.getView(ViewSummary.this,type,amnt);
                LinearLayout graph_l = (LinearLayout)findViewById(R.id.chart);
                graph_l.addView(gView);
            }
        });


    }
    public void ShowDateNew(View v)
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
            showDate(arg1, arg2+1);
        }
    };

    private void showDate(int year, int month) {
        dateView.setText(new StringBuilder()
                .append(month).append("/").append(year));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_summary, menu);
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
public void oNPie(View v) {

}
//    PieGraph graph = new PieGraph();
//    GraphicalView gView = graph.getView(this,type,amnt);
//    LinearLayout graph_l = (LinearLayout)findViewById(R.id.chart);
//    graph_l.addView(gView);



}


