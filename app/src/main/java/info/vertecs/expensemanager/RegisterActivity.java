package info.vertecs.expensemanager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.MultipleCategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import java.util.ArrayList;
import java.util.List;

import info.vertecs.expensemanager.adapter.DatabaseHandler;
import info.vertecs.expensemanager.adapter.Login;


public class RegisterActivity extends Activity {
EditText username;
    EditText password;
    Button authenticate_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_register);
        username = (EditText)findViewById(R.id.edt_usr_name_register);
        password = (EditText)findViewById(R.id.edt_psw_wrd_register);
        authenticate_register = (Button)findViewById(R.id.btn_authenticate_login_register);
        final DatabaseHandler db = new DatabaseHandler(this);
        authenticate_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usr_name = username.getText().toString();
                String psw_wrd = password.getText().toString();
                setTitle("Xpense Manager");
                db.addContact(new Login(usr_name,psw_wrd));
                Toast.makeText(getApplicationContext(),"Registration Sucessfull",Toast.LENGTH_LONG).show();
                List<Login> login = db.getAllContacts();

                for (Login cn : login) {
                    String log = "Id: "+cn.getUsername()+" ,Name: " + cn.getUsername() + " ,Phone: " + cn.getPassword();
                    // Writing Contacts to log
                    Log.d("Name: ", log);
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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

    public static class DonutActivity extends Activity {

        GraphicalView gv;
        RelativeLayout rl;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_donut);
            List<double[]> values1 = new ArrayList<double[]>();

            values1.add(new double[]{15, 5});


            gv = createIntent(values1);

            rl = (RelativeLayout) findViewById(R.id.re1);
            rl.addView(gv);
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_add_debt, menu);
            return true;
        }

        public GraphicalView createIntent(List<double[]> values1) {

            List<String[]> titles = new ArrayList<String[]>();

            titles.add(new String[]{" ", " "});
            int[] colors = new int[]{Color.BLUE, Color.GREEN};

            DefaultRenderer renderer = buildCategoryRenderer(colors);
            renderer.setApplyBackgroundColor(true);
            renderer.setShowLegend(false);

            renderer.setShowLabels(false);
            renderer.setStartAngle(270);
            renderer.setBackgroundColor(Color.rgb(222, 222, 200));
            renderer.setLabelsColor(Color.GRAY);


            return ChartFactory.getDoughnutChartView(DonutActivity.this,
                    buildMultipleCategoryDataset("Project budget", titles, values1),
                    renderer);
        }

        protected MultipleCategorySeries buildMultipleCategoryDataset(String title,
                                                                      List<String[]> titles, List<double[]> values) {
            MultipleCategorySeries series = new MultipleCategorySeries(title);
            int k = 0;
            for (double[] value : values) {
                series.add(2007 + k + "", titles.get(k), value);
                k++;
            }
            return series;
        }

        protected DefaultRenderer buildCategoryRenderer(int[] colors) {
            DefaultRenderer renderer = new DefaultRenderer();
            renderer.setLabelsTextSize(15);
            renderer.setLegendTextSize(15);
            renderer.setMargins(new int[]{20, 30, 15, 0});
            for (int color : colors) {
                SimpleSeriesRenderer r = new SimpleSeriesRenderer();
                r.setColor(color);
                renderer.addSeriesRenderer(r);
            }
            return renderer;
        }
    }
}
