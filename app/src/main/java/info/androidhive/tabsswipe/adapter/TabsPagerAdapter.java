package info.androidhive.tabsswipe.adapter;


import info.androidhive.tabsswipe.NextMonthFragment;
import info.androidhive.tabsswipe.ThisMonthFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Top Rated fragment activity
			return new ThisMonthFragment();
		case 1:
            // Movies fragment activity
            return new NextMonthFragment();


		}

		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 2;
	}

}
