package info.vertecs.expensemanager;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import info.vertecs.expensemanager.adapter.DatabaseHandlerAddDebt;
import info.vertecs.expensemanager.adapter.Debt;

public class ViewDebt extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_debt);
        DatabaseHandlerAddDebt db = new DatabaseHandlerAddDebt(this);

        List<Debt>list = db.getDebtData();
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

        ListView ls= (ListView)findViewById(R.id.listView);
        ls.setAdapter(new ListAdapterDebt(getApplicationContext(),name,type,amnt));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_debt, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void populateListView()
    {

    }
}
