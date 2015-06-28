package info.vertecs.expensemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import info.vertecs.expensemanager.R;
import info.vertecs.expensemanager.adapter.DatabaseHandlerAddDebt;
import info.vertecs.expensemanager.adapter.Debt;


/**
 * Created by USER on 03-06-2015.
 */
public class ListAdapterDebt extends ArrayAdapter<Debt> {
    private final Context context;

    private ArrayList<Debt>listnew;

    public ListAdapterDebt(Context context,ArrayList<Debt>listnew) {
        super(context, R.layout.list_item,listnew );
        this.context = context;
this.listnew = listnew;
        }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Debt item = listnew.get(position);
        View rowView = inflater.inflate(R.layout.list_item_debt, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.name);
        TextView textView2 = (TextView) rowView.findViewById(R.id.code);
        TextView textView3 = (TextView) rowView.findViewById(R.id.amount);
if(item != null) {
    textView2.setText(item.getName());

if(item.getAmnt()<0)
{
    textView.setText("Is Owed");
}
    else
{
    textView.setText("Owes You");
}
    textView3.setText(String.valueOf(item.getAmnt()));
//        // Change icon based on name
}


        return rowView;
    }
}