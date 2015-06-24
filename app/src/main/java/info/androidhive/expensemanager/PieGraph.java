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


int size = values.length;

int [] colors = new int[size];


     CategorySeries series = new CategorySeries("Pie Graph");
     int k = 0;
     for (int value : values) {
         series.add("Section " + ++k, value);
     }
            for(int i=0;i<size;i++) {
                colors[i] = Color.argb(((i*i)+50),((i*100)%255),((i*200)%255),((i*300)%255));

            }
        DefaultRenderer renderer = new DefaultRenderer();
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
            renderer.setPanEnabled(false);
        }
        renderer.setChartTitle("Pie Chart Demo");
        renderer.setChartTitleTextSize(10);
        renderer.setZoomButtonsVisible(false);

         return ChartFactory.getPieChartView(context,series,renderer);

    }
}


