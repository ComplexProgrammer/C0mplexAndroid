package complexprogrammer.uz;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import complexprogrammer.uz.ui.account.LoginTabFragment;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData(Context c) {
        LinkedHashMap<String, List<String>> expandableListDetail = new LinkedHashMap<String, List<String>>();

        List<String> menu1 = new ArrayList<String>();
        List<String> menu2 = new ArrayList<String>();

        List<String> list3 = new ArrayList<String>();
        list3.add(c.getString(R.string.menu_tic_tac_toe));
        list3.add(c.getString(R.string.menu_fly));


        List<String> list4 = new ArrayList<String>();
        list4.add(c.getString(R.string.menu_guid));
        list4.add(c.getString(R.string.menu_change_text));
        list4.add(c.getString(R.string.menu_image));

        List<String> list5 = new ArrayList<String>();
        list5.add(c.getString(R.string.android_studi));
        list5.add(c.getString(R.string.xamarin));

        List<String> menu6 = new ArrayList<String>();

        List<String> list7 = new ArrayList<String>();
        List<String> menu8 = new ArrayList<String>();
        list7.add(c.getString(R.string.menu_language));
        LoginTabFragment loginTabFragment=new LoginTabFragment();
        MainActivity activity=new MainActivity();
        if (loginTabFragment.getUserId(c).equals(0)) {
            expandableListDetail.put(c.getString(R.string.menu_home), menu1);
            expandableListDetail.put(c.getString(R.string.menu_news), menu2);
            expandableListDetail.put(c.getString(R.string.menu_games), list3);
            expandableListDetail.put(c.getString(R.string.menu_services), list4);
            expandableListDetail.put(c.getString(R.string.menu_tutorials), list5);
            expandableListDetail.put(c.getString(R.string.menu_contact), menu6);
            expandableListDetail.put(c.getString(R.string.menu_settings), list7);
            expandableListDetail.put(c.getString(R.string.menu_login), menu8);
            }
        else {
            list7.add(c.getString(R.string.menu_my_account));
            list7.add(c.getString(R.string.menu_control));
            expandableListDetail.put(c.getString(R.string.menu_home), menu1);
            expandableListDetail.put(c.getString(R.string.menu_news), menu2);
            expandableListDetail.put(c.getString(R.string.menu_games), list3);
            expandableListDetail.put(c.getString(R.string.menu_services), list4);
            expandableListDetail.put(c.getString(R.string.menu_tutorials), list5);
            expandableListDetail.put(c.getString(R.string.menu_contact), menu6);
            expandableListDetail.put(c.getString(R.string.menu_settings), list7);
            expandableListDetail.put(c.getString(R.string.menu_logoff), menu8);
        }





        return expandableListDetail;
    }
}
