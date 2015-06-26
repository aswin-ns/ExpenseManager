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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.achartengine.GraphicalView;

import java.util.Calendar;
import java.util.List;

import info.vertecs.expensemanager.adapter.DatabaseHandlerAddData;
import info.vertecs.expensemanager.adapter.PieGraph;
import info.vertecs.expensemanager.adapter.SumByClass;

public class ViewSummary extends BaseActivity {
    Button piegraph;
    LinearLayout graphView;
Button pie;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    String date;
    TextView ErrorMessage;
    final DatabaseHandlerAddData db = new DatabaseHandlerAddData(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_summary);
        ErrorMessage = (TextView) findViewById(R.id.txt_error_message);
        //TODO GETTING PUT EXTRA FROM THE FRAGMENT//

        String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                newString = null;
            } else {
                newString = extras.getString("STRING_I_NEED");
            }
        } else {
            newString = (String) savedInstanceState.getSerializable("STRING_I_NEED");
        }
        //TODO CODE SNIPPET END//


        //TODO CALLING PIE CHART FUNCTIONS FOR THE GIVEN SET OF DATAS//
        //TODO CALLING DB FUNCTION IF DATA THEN PIE CHART FUNCTIONS ELSE ERROR MESSAGE SET//

        List<SumByClass> ThisMonthPos = db.getPie(newString);
        List<SumByClass> ThisMonthNegPie = db.getNegPie(newString);
        if (ThisMonthPos == null) {
            ErrorMessage.setVisibility(View.VISIBLE);
        } else {
            String[] thismonpostype = new String[ThisMonthPos.size()];
            int[] thismonposamnt = new int[ThisMonthPos.size()];
            int i = 0;
            int k = ThisMonthPos.size();

            for (SumByClass cn : ThisMonthPos) {
                thismonpostype[i] = cn.getGroup_name();
                thismonposamnt[i] = cn.getAmnt();
                i++;

            }
            PieGraph graph = new PieGraph();

            GraphicalView gViewtp = graph.getView(ViewSummary.this, thismonpostype, thismonposamnt);
            LinearLayout graph_l = (LinearLayout) findViewById(R.id.chart_1);
            graph_l.addView(gViewtp);


            String[] thismonnegtype = new String[ThisMonthNegPie.size()];
            int[] thismonnegamnt = new int[ThisMonthNegPie.size()];
            int in = 0;
            int kn = ThisMonthNegPie.size();

            for (SumByClass cn : ThisMonthNegPie) {
                thismonnegtype[in] = cn.getGroup_name();
                thismonnegamnt[in] = cn.getAmnt();
                in++;

            }
            PieGraph graphnp = new PieGraph();
            GraphicalView gViewnp = graphnp.getView(ViewSummary.this, thismonnegtype, thismonnegamnt);
            LinearLayout graph_np = (LinearLayout) findViewById(R.id.chart_2);
            graph_np.addView(gViewnp);
        }


        dateView = (TextView) findViewById(R.id.txt_date_summary);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1);

        pie = (Button)findViewById(R.id.btn_pie);
        Toast.makeText(getApplicationContext(), date, Toast.LENGTH_LONG).show();
       pie.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               date = dateView.getText().toString();
               Log.d("enter",date);

               List<SumByClass>a = db.getPie(date);
               String [] type = new String[a.size()];
               int [] amnt = new int[a.size()];
               int ip = 0;
               int k=a.size();
               for (SumByClass cn : a) {
                   type[ip] = cn.getGroup_name();
                   amnt[ip]= cn.getAmnt();
                   ip++;

               }

               Log.d("Size Of A",String.valueOf(a.size()));

               PieGraph graphdp = new PieGraph();


               GraphicalView gViewdp = graphdp.getView(ViewSummary.this,type,amnt);
               LinearLayout graph_l = (LinearLayout)findViewById(R.id.chart_1);
               graph_l.addView(gViewdp);



               List<SumByClass>b = db.getNegPie(date);
               String [] neg_type = new String[b.size()];
               int [] neg_amnt = new int[b.size()];
               int l = 0;
               int m = b.size();
               for(SumByClass dn : b)
               {
                   neg_type[l] = dn.getNegative_groupname();
                   neg_amnt[l] = dn.getAmnt();
                   l++;
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


}






