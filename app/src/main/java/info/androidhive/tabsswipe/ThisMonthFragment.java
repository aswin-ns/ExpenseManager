package info.androidhive.tabsswipe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import info.androidhive.tabsswipe.adapter.DataClass;
import info.androidhive.tabsswipe.adapter.DatabaseHandlerAddData;

public class ThisMonthFragment extends Fragment {
    ImageButton img;
    TextView expense;
    TextView income;
    TextView balance;
    String date = "6/6/2015";
//    final DatabaseHandlerAddData db = new DatabaseHandlerAddData(getActivity());
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {


		View rootView = inflater.inflate(R.layout.fragment_this_month, container, false);
        expense = (TextView)rootView.findViewById(R.id.expense_trt);
        income = (TextView)rootView.findViewById(R.id.income_trt);
        balance = (TextView)rootView.findViewById(R.id.balance_trt);

        img = (ImageButton)rootView.findViewById(R.id.img_trt);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main_to_tab2 = new Intent(getActivity(),Tab2.class);
                startActivity(main_to_tab2);
            }
        });
        DatabaseHandlerAddData db = new DatabaseHandlerAddData(getActivity());
        List<DataClass> contacts = db.getPosData(date);

        for (DataClass cn : contacts) {
            String log = Integer.toString(cn.getAmnt());
            // Writing Contacts to log
           income.setText(log);
        }
//        List<DataClass> new1 = db.getNegData(date);
//
//        for (DataClass cn : new1) {
//            String log = Integer.toString(cn.getNeg_amnt());
//            // Writing Contacts to log
//
//            expense.setText(log);
//            Log.d("Name: ", log);
//        }
		return rootView;
	}
}
