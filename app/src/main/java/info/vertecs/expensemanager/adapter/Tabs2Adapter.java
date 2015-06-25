package info.vertecs.expensemanager.adapter;


import info.vertecs.expensemanager.ExpenseTab;
import info.vertecs.expensemanager.IncomeTab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class Tabs2Adapter extends FragmentPagerAdapter {

    public Tabs2Adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Top Rated fragment activity
                return new IncomeTab();
            case 1:
                // Movies fragment activity
                return new ExpenseTab();


        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }

}
