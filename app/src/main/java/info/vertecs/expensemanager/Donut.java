package info.vertecs.expensemanager;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.widget.RelativeLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.MultipleCategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import java.util.ArrayList;
import java.util.List;

public class Donut extends Activity {

    GraphicalView gv;
    RelativeLayout rl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut2);
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


        return ChartFactory.getDoughnutChartView(Donut.this,
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