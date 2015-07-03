package info.vertecs.expensemanager;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import info.vertecs.expensemanager.adapter.DatabaseHandlerAddDebt;
import info.vertecs.expensemanager.adapter.Debt;

public class ViewDebt extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_debt);
        DatabaseHandlerAddDebt db = new DatabaseHandlerAddDebt(this);
        setTitle("Xpense Manager");
        ArrayList<Debt>newlist = (ArrayList<Debt>)db.getDebtData();
//

        ListView ls= (ListView)findViewById(R.id.listView);
        ls.setAdapter(new ListAdapterDebt(getApplicationContext(),newlist));

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
