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

import info.vertecs.expensemanager.adapter.ListAdapter;

/**
 * Created by USER on 03-06-2015.
 */
public class IncomeTab extends Fragment{
    static final String[] LIST_STRING_DATA_INCOME=
            new String[] { "Salary", "Awards", "Gifts", "Miscallenous"};
    ListView lst;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.income_tab, container, false);


        lst = (ListView)rootView.findViewById(R.id.lst_income);
        lst.setAdapter(new ListAdapter(getActivity(), LIST_STRING_DATA_INCOME));

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String list_string_val = (String) (lst.getItemAtPosition(i));
                Intent intent = new Intent(getActivity(), AddData.class);
                String keyIdentifer = null;
                intent.putExtra("STRING_I_NEED", list_string_val);
                Toast.makeText(getActivity(),list_string_val,Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }});
        return rootView;
    }
}
