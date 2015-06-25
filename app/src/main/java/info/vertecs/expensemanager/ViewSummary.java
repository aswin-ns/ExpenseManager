package info.vertecs.expensemanager;

import android.app.DatePickerDialog;
import android.app.Dialog;
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

import java.util.Calendar;
import java.util.List;

import info.vertecs.expensemanager.adapter.DatabaseHandlerAddData;
import info.vertecs.expensemanager.adapter.PieGraph;
import info.vertecs.expensemanager.adapter.SumByClass;
import info.vertecs.tabsswipe.R;

public class ViewSummary extends BaseActivity {
    Button piegraph;
    LinearLayout graphView;
Button pie;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    String date;

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

              List<SumByClass>a = db.getPie("6/2015");
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
                LinearLayout graph_l = (LinearLayout)findViewById(R.id.chart_1);
                graph_l.addView(gView);
                List<SumByClass>b = db.getNegPie("6/2015");
                String [] neg_type = new String[b.size()];
                int [] neg_amnt = new int[b.size()];
                int l = 0;
                int m = b.size();
                for(SumByClass dn : b)
                {
                    neg_type[i] = dn.getNegative_groupname();
                    neg_amnt[i] = dn.getAmnt();
                    i++;
                }

                PieGraph graph1 = new PieGraph();
                GraphicalView gView1 = graph1.getView(ViewSummary.this,neg_type,neg_amnt);
                LinearLayout graph_l_1 = (LinearLayout)findViewById(R.id.chart_2);
                graph_l_1.addView(gView1);
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
        dateView.setText(new StringBuilder().append(month).append("/").append(year));

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
public void oNPie(View v) {

}
//    PieGraph graph = new PieGraph();
//    GraphicalView gView = graph.getView(this,type,amnt);
//    LinearLayout graph_l = (LinearLayout)findViewById(R.id.chart);
//    graph_l.addView(gView);



}


