package info.androidhive.tabsswipe;

import android.content.Context;
import android.content.Intent;

import org.achartengine.model.TimeSeries;

/**
 * Created by ajaleelp on 15/6/15.
 */
public class Graphing {
    public Intent getIntent(Context context){
        int[] x = {1,2,3,4,5};
        int[] y = {1,4,3,7,3};

        TimeSeries series = new TimeSeries("Line1");
        for (int i=0; i<x.length; i++){
            series.add(x[i],y[i]);
        }
        return null;
    }
}
