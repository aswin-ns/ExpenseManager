package info.androidhive.tabsswipe;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

/**
 * Created by ajaleelp on 15/6/15.
 */
public class Graphing {
    public Intent getIntent(Context context){
        int[] values = {1,2,3,4,5};

        CategorySeries series = new CategorySeries("Pie Graph");
        int k = 0;
        for (int value : values){
            series.add("Section" + ++k,value);
        }

//        TimeSeries series = new TimeSeries("Line1");
//        for (int i=0; i<x.length; i++){
//            series.add(x[i],y[i]);
//        }


        int[] colors = new int[] {Color.BLUE, Color.GREEN, Color.MAGENTA, Color.YELLOW, Color.CYAN};

        DefaultRenderer renderer = new DefaultRenderer();
        for (int color:colors){
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }

//        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
//        dataset.addSeries(series);
//
//        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
//        XYSeriesRenderer renderer = new XYSeriesRenderer();
//        mRenderer.addSeriesRenderer(renderer);

        Intent intent = ChartFactory.getPieChartIntent(context,series,renderer,"Pie");

        return intent;
    }
}
