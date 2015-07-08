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

public class NextMonthFragment extends Fragment {
    ImageButton img;
    TextView expense;
    TextView income;
    TextView balance;
    Button viewsummary;
    Button adddebt;
    Button viewdebt;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_next_month, container, false);
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        String newyear = String.valueOf(year);
        String newmonth = String.valueOf(month+2);

        final String newdate = newmonth+"/"+newyear;
        expense = (TextView)rootView.findViewById(R.id.txt_expense_next_month);
        income = (TextView)rootView.findViewById(R.id.txt_income_next_month);
        balance = (TextView)rootView.findViewById(R.id.txt_bal_next_month);
        viewsummary =(Button)rootView.findViewById(R.id.btn_view_debt_next_mont);
        viewdebt = (Button)rootView.findViewById(R.id.btn_view_debt_next_mont);
        viewdebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ViewDebt.class);
                startActivity(intent);
            }
        });
        img = (ImageButton)rootView.findViewById(R.id.img_btn_next_month);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main_to_tab2 = new Intent(getActivity(),Tab2.class);
                startActivity(main_to_tab2);
            }
        });
        viewsummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewSummary = new Intent(getActivity(),ViewSummary.class);
                viewSummary.putExtra("STRING_I_NEED", newdate);
                startActivity(viewSummary);
            }
        });
        adddebt = (Button)rootView.findViewById(R.id.btn_add_next_month);
        adddebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main_activity_to_add_debt = new Intent(getActivity(),AddDebt.class);
                startActivity(main_activity_to_add_debt);
            }
        });

        //TODO: these toasts should not be in onCr
        // eateView. Consider using onStart or onResume

        DatabaseHandlerAddData db = new DatabaseHandlerAddData(getActivity());
        List<DataClass> nextmonthlist = db.getPosData(newdate);

        for (DataClass cn : nextmonthlist) {
            String log = Integer.toString(cn.getAmnt());
            // Writing Contacts to log
            income.setText(log);
        }
        List<DataClass> nextmonthexpenselist = db.getNegData(newdate);

        for (DataClass cn : nextmonthexpenselist) {
            String log = Integer.toString(cn.getNeg_amnt());
            // Writing Contacts to log

            expense.setText(log);
            Log.d("Name: ", log);
        }
        int exp,inc;
        exp = Integer.parseInt(expense.getText().toString());
        inc = Integer.parseInt(income.getText().toString());
        int bal = (inc-exp);
        String baln = String.valueOf(bal);
        balance.setText(baln);
		
		return rootView;
	}

}
