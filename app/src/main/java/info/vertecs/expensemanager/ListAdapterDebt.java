package info.vertecs.expensemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import info.vertecs.expensemanager.R;


/**
 * Created by USER on 03-06-2015.
 */
public class ListAdapterDebt extends ArrayAdapter<String> {
    private final Context context;
    private final String[] name;
    private final String[] type;
    private final int[] amnt;
    public ListAdapterDebt(Context context, String[] name,String[] type,int[] amnt) {
        super(context, R.layout.list_item,name );
        this.context = context;
        this.name = name;
        this.amnt = amnt;
        this.type = type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_item_debt, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.name);
        TextView textView2 = (TextView) rowView.findViewById(R.id.code);
        TextView textView3 = (TextView) rowView.findViewById(R.id.amount);

        textView.setText(name[position]);
textView2.setText(type[position]);
//        textView3.setText(amnt[position]);
        // Change icon based on name



        return rowView;
    }
}