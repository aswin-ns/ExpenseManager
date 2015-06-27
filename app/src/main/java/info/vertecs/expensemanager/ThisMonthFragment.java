package info.vertecs.expensemanager;

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

import java.util.Calendar;
import java.util.List;

import info.vertecs.expensemanager.adapter.DataClass;
import info.vertecs.expensemanager.adapter.DatabaseHandlerAddData;

public class ThisMonthFragment extends Fragment {
    ImageButton img;
    TextView expense;
    TextView income;
    TextView balance;
    Button  viewsummary;
    Button adddebt;
    Button viewdebt;
//    // TODO: Move this to a BaseActivity
//    public static final String PREFS_NAME = "preferences";
//    final DatabaseHandlerAddData db = new DatabaseHandlerAddData(getActivity());
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {


		View rootView = inflater.inflate(R.layout.fragment_this_month, container, false);
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        //TODO: need to account for end month December
        String newyear = String.valueOf(year);
        String newmonth = String.valueOf(month+1);
        final String newdate = newmonth+"/"+newyear;

        expense = (TextView)rootView.findViewById(R.id.txt_expense);
        income = (TextView)rootView.findViewById(R.id.txt_income);
        balance = (TextView)rootView.findViewById(R.id.txt_bal);
        viewsummary =(Button)rootView.findViewById(R.id.btn_view_summary_this_month);
 viewdebt = (Button)rootView.findViewById(R.id.btn_view_debt);
        viewdebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ViewDebt.class);
                startActivity(intent);
            }
        });
        DatabaseHandlerAddData db = new DatabaseHandlerAddData(getActivity());
        List<DataClass> thismonthdata = db.getPosData(newdate);

        for (DataClass cn : thismonthdata) {
            String log = Integer.toString(cn.getAmnt());
            // Writing Contacts to log
            income.setText(log);
        }
        List<DataClass> thismonthnegdata = db.getNegData(newdate);

        for (DataClass cn : thismonthnegdata) {
            String log2 = Integer.toString(cn.getNeg_amnt());
            // Writing Contacts to log

            expense.setText(log2);
            Log.d("Name: ", log2);
        }
        viewsummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent viewSummary = new Intent(getActivity(),ViewSummary.class);
                viewSummary.putExtra("STRING_I_NEED", newdate);
                startActivity(viewSummary);
            }
        });
        img = (ImageButton)rootView.findViewById(R.id.img_btn_this_month);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main_to_tab2 = new Intent(getActivity(),Tab2.class);
                startActivity(main_to_tab2);
            }
        });
adddebt = (Button)rootView.findViewById(R.id.btn_add_debt_this_month);
        adddebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main_activity_to_add_debt = new Intent(getActivity(),AddDebt.class);
                startActivity(main_activity_to_add_debt);
            }
        });

        //TODO: these toasts should not be in onCreateView. Consider using onStart or onResume






        int exp = Integer.parseInt(expense.getText().toString());
        int inc = Integer.parseInt(income.getText().toString());
        int bal = (inc-exp);
        String baln = String.valueOf(bal);
        balance.setText(baln);
		return rootView;
	}

}
