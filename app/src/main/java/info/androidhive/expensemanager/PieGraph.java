package info.androidhive.expensemanager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

/**
 * Created by USER on 17-06-2015.
 */
public class PieGraph {
    public GraphicalView getView(Context context,String type[],int values[]) {







        CategorySeries series = new CategorySeries("Pie Graph");
        int k = 0;
        for (int value : values) {
            series.add("Section " + ++k, value);
        }

        int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.MAGENTA, Color.LTGRAY};

        DefaultRenderer renderer = new DefaultRenderer();
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
            renderer.setPanEnabled(false);
        }
        renderer.setChartTitle("Pie Chart Demo");
        renderer.setChartTitleTextSize(10);
        renderer.setZoomButtonsVisible(true);

         return ChartFactory.getPieChartView(context,series,renderer);

    }
}


