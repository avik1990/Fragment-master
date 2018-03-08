package pager.demo.com.fragment;



import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by user2 on 26-02-2018.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new FirstFragment();
        } else if (position == 1){
            return new BedRoom();
        } else if (position == 2){
            return new BathRoom();
        } else {
            return new HallRoom();
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 4;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
          case 0:
                return "Switch";
            case 1:
                return "Bedroom";
            case 2:
                return "BathRoom";
            case 3:
                return "HallRoom";
            default:
                return null;
        }
    }

}