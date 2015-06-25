package info.vertecs.expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import info.vertecs.tabsswipe.R;
import info.vertecs.expensemanager.adapter.ListAdapter;

/**
 * Created by USER on 03-06-2015.
 */
public class ExpenseTab extends Fragment {
    static final String[] MOBILE_OS =
            new String[] { "Food", "Travel", "Entertainment", "Miscallenous"};
 ListView lst;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.expense_tab, container, false);
       lst = (ListView)rootView.findViewById(R.id.lst_expense);
        lst.setAdapter(new ListAdapter(getActivity(), MOBILE_OS));
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String sl =(String)(lst.getItemAtPosition(i));
                Intent intent = new Intent(getActivity(), AddDataNeg.class);
                String keyIdentifer  = null;
                intent.putExtra("STRING_I_NEED", sl);
                startActivity(intent);
            Toast.makeText(getActivity(),sl,Toast.LENGTH_LONG).show();
            }
        });
        return rootView;
    }


}
