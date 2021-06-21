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
        list3.add("Tic Tac Toe");


        List<String> list4 = new ArrayList<String>();
        list4.add("GUID creation");
        list4.add("Latin  Cyrillic");
        list4.add("Image");

        List<String> list5 = new ArrayList<String>();
        list5.add("Android studio");
        list5.add("Xamarin");

        List<String> menu6 = new ArrayList<String>();

        List<String> list7 = new ArrayList<String>();
        List<String> menu8 = new ArrayList<String>();
        list7.add("Language");
        LoginTabFragment loginTabFragment=new LoginTabFragment();
        MainActivity activity=new MainActivity();
        if (loginTabFragment.getUserId(c).equals(0)) {
            expandableListDetail.put("Home", menu1);
            expandableListDetail.put("News", menu2);
            expandableListDetail.put("Online games", list3);
            expandableListDetail.put("Services", list4);
            expandableListDetail.put("Tutorials", list5);
            expandableListDetail.put("Contact", menu6);
            expandableListDetail.put("Sttings", list7);
            expandableListDetail.put("Log in", menu8);
            }
        else {
            list7.add("My Account");
            list7.add("Control");
            expandableListDetail.put("Home", menu1);
            expandableListDetail.put("News", menu2);
            expandableListDetail.put("Online games", list3);
            expandableListDetail.put("Services", list4);
            expandableListDetail.put("Tutorials", list5);
            expandableListDetail.put("Contact", menu6);
            expandableListDetail.put("Sttings", list7);
            expandableListDetail.put("Log off", menu8);
        }





        return expandableListDetail;
    }
}
