package info.vertecs.expensemanager;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        setContentView(R.layout.activity_view_summary);
//        ErrorMessage = (TextView) findViewById(R.id.txt_error_message);
        //TODO GETTING PUT EXTRA FROM THE FRAGMENT//
        setTitle("Xpense Manager");
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
        int i ;
        if(ThisMonthPos.isEmpty() || ThisMonthNegPie.isEmpty())
        {
            if (ThisMonthPos.isEmpty())
            {
                View view3 = (View)findViewById(R.id.view_1);
                LinearLayout gp3 = (LinearLayout)findViewById(R.id.chart_1);
                view3.setVisibility(View.VISIBLE);
            }
           else if (ThisMonthNegPie.isEmpty())
            {
                View view4 = (View)findViewById(R.id.view_2);
                LinearLayout gp4 = (LinearLayout)findViewById(R.id.chart_2);
                view4.setVisibility(View.VISIBLE);
            }


        }
else
        {   GraphicalView gViewpp = getGraphViewForMonth(newString, "exp");
            LinearLayout graph_pp = (LinearLayout) findViewById(R.id.chart_1);
            graph_pp.addView(gViewpp);

            GraphicalView gViewnp = getGraphViewForMonth(newString, "inc");
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


       pie.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               View view1 = (View) findViewById(R.id.view_1);
               LinearLayout gpl = (LinearLayout) findViewById(R.id.chart_1);
               view1.setVisibility(View.GONE);
               date = dateView.getText().toString();
               List<SumByClass> list = db.getPie(date);
               List<SumByClass> list2 =db.getNegPie(date);
               if ((list.isEmpty())||(list2.isEmpty())) {

                   view1.setVisibility(View.VISIBLE);
               } else {


                   GraphicalView gViewdp = getGraphViewForMonth(date, "exp");
                   LinearLayout graph_l = (LinearLayout) findViewById(R.id.chart_1);
                   graph_l.removeAllViews();
                   graph_l.addView(gViewdp);

                   GraphicalView gView1 = getGraphViewForMonth(date, "inc");
                   LinearLayout graph_l_1 = (LinearLayout) findViewById(R.id.chart_2);
                   graph_l_1.removeAllViews();
                   graph_l_1.addView(gView1);
               }
           }




       });
    }

    public void ShowDateNew(View v)
    {
        showDialog(999);

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

    // Function which return the GraphicalView for the given month,
    private GraphicalView getGraphViewForMonth(String date,String transactionType) {

        List<SumByClass> data = (transactionType.equals("exp")) ? db.getPie(date) : db.getNegPie(date);
        String title = (transactionType.equals("exp")) ? "INCOME" : "EXPENDITURE";

        String[] categories = stripCategory(data);
        int[] amounts = stripAmount(data);

            PieGraph graph = new PieGraph();
            GraphicalView gView = graph.getView(ViewSummary.this, categories, amounts, title);
            return gView;

    }

    // Function to return an array of categories in the SumByClass list.
    private String [] stripCategory(List<SumByClass> data){
        String [] category = new String[data.size()];
        int i=0;
        for (SumByClass k : data){
            category[i] = k.getGroup_name();
            i++;
        }
        return category;
    }

    // Function to return an array of amounts in the SumByClass list.
    private int [] stripAmount(List<SumByClass> data){
        int [] amount = new int[data.size()];
        int i=0;
        for (SumByClass k : data){
            amount[i] = k.getAmnt();
            i++;
        }
        return amount;
    }

}






