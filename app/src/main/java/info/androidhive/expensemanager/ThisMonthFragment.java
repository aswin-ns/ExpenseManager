package info.androidhive.expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import info.androidhive.expensemanager.adapter.ViewSummary;
import info.androidhive.tabsswipe.R;
import info.androidhive.expensemanager.adapter.DataClass;
import info.androidhive.expensemanager.adapter.DatabaseHandlerAddData;

public class ThisMonthFragment extends Fragment {
    ImageButton img;
    TextView expense;
    TextView income;
    TextView balance;
    Button  viewsummary;
//    final DatabaseHandlerAddData db = new DatabaseHandlerAddData(getActivity());
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {


		View rootView = inflater.inflate(R.layout.fragment_this_month, container, false);


        expense = (TextView)rootView.findViewById(R.id.expense_trt);
        income = (TextView)rootView.findViewById(R.id.income_trt);
        balance = (TextView)rootView.findViewById(R.id.balance_trt);
        viewsummary =(Button)rootView.findViewById(R.id.btn_view_summary);
        viewsummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewSummary = new Intent(getActivity(),ViewSummary.class);
                startActivity(viewSummary);
            }
        });
        img = (ImageButton)rootView.findViewById(R.id.img_trt);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main_to_tab2 = new Intent(getActivity(),Tab2.class);
                startActivity(main_to_tab2);
            }
        });
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        String newyear = String.valueOf(year);
        String newmonth = String.valueOf(month+1);
        String newdate = newmonth+"/"+newyear;
        Toast.makeText(getActivity(),newdate,Toast.LENGTH_LONG).show();
        DatabaseHandlerAddData db = new DatabaseHandlerAddData(getActivity());
        List<DataClass> contacts = db.getPosData(newdate);

        for (DataClass cn : contacts) {
            String log = Integer.toString(cn.getAmnt());
            // Writing Contacts to log
           income.setText(log);
        }
        List<DataClass> new1 = db.getNegData(newdate);

        for (DataClass cn : new1) {
            String log = Integer.toString(cn.getNeg_amnt());
            // Writing Contacts to log

            expense.setText(log);
            Log.d("Name: ", log);
        }
		return rootView;
	}

}
