package info.androidhive.expensemanager.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import org.achartengine.GraphicalView;

import java.util.List;

import info.androidhive.expensemanager.PieGraph;
import info.androidhive.tabsswipe.R;

public class ViewSummary extends Activity {
    Button piegraph;
    LinearLayout graphView;
Button pie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_summary);

        pie = (Button)findViewById(R.id.btn_pie);
        pie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandlerAddData db = new DatabaseHandlerAddData(ViewSummary.this);
                String [] type = {};
                int [] amnt = {};
                int i = 0;
                List<SumByClass>data = db.getPIEpos("13/06/2015");
                for(SumByClass sln : data) {
                    type[i] = sln.getGroup_name();
                    amnt[i] = sln.getAmnt();

                    i++;
                }

            }
        });


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


