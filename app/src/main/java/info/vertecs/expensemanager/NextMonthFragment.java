package info.vertecs.expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import info.vertecs.expensemanager.adapter.DataClass;
import info.vertecs.expensemanager.adapter.DatabaseHandlerAddData;

public class NextMonthFragment extends Fragment {
    ImageButton img;
    TextView expense;
    TextView income;
    TextView balance;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_next_month, container, false);
        expense = (TextView)rootView.findViewById(R.id.txt_expense_next_month);
        income = (TextView)rootView.findViewById(R.id.txt_income_next_month);
        balance = (TextView)rootView.findViewById(R.id.txt_bal_next_month);

        img = (ImageButton)rootView.findViewById(R.id.img_btn_next_month);
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
        String newmonth = String.valueOf(month+2);

        String newdate = newmonth+"/"+newyear;
        //TODO: these toasts should not be in onCreateView. Consider using onStart or onResume
        Toast.makeText(getActivity(), newdate, Toast.LENGTH_LONG).show();
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
//        balance.setText(bal);
		
		return rootView;
	}

}
