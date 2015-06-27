package info.vertecs.expensemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import info.vertecs.expensemanager.R;
import info.vertecs.expensemanager.adapter.DatabaseHandlerAddDebt;
import info.vertecs.expensemanager.adapter.Debt;


/**
 * Created by USER on 03-06-2015.
 */
public class ListAdapterDebt extends ArrayAdapter<String> {
    private final Context context;

    public ListAdapterDebt(Context context) {
        super(context, R.layout.list_item );
        this.context = context;
        DatabaseHandlerAddDebt db = new DatabaseHandlerAddDebt(context);
        List<Debt >list = db.getDebtData();
        String [] type = new String[list.size()];
        int [] amnt = new int[list.size()];
        String [] name = new String[list.size()];
        int i=0;
        for(Debt cn:list)
        {
            name[i] = cn.getName();
            type[i] = cn.getType();
            amnt[i] = cn.getAmnt();

        }
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
        textView3.setText(amnt[position]);
        // Change icon based on name



        return rowView;
    }
}