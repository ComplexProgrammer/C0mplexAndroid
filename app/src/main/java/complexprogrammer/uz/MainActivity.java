package complexprogrammer.uz;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import complexprogrammer.uz.models.ExpandListAdapter;
import complexprogrammer.uz.models.ExpandedMenuModel;

public class MainActivity extends AppCompatActivity {


    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_news,R.id.nav_tic_tac_toe,R.id.nav_guid, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        enableExpandableList();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    ExpandableListView expListView;
    ArrayList<String> listDataHeader=new ArrayList<>();
    HashMap<String,List<String>> listDataChild=new HashMap<>();
    ExpandListAdapter listAdapter;
    private void enableExpandableList() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        expListView = (ExpandableListView) findViewById(R.id.left_drawer);

        prepareListData(listDataHeader, listDataChild);
        listAdapter = new ExpandListAdapter(this, listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);

        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                // till here
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });
    }

    private void prepareListData(List<String> listDataHeader, Map<String,
                List<String>> listDataChild) {
        // Adding child data
        listDataHeader.add("Product1");
        listDataHeader.add("product2");
        listDataHeader.add("Product3");

        // Adding child data
        List<String> top = new ArrayList<String>();
        top.add("x1");
        top.add("x2");
        top.add("x3");
        top.add("x4");
        top.add("x5");


        List<String> mid = new ArrayList<String>();
        mid.add("y1");
        mid.add("y2");
        mid.add("y3");

        List<String> bottom = new ArrayList<String>();
        bottom.add("z1");
        bottom.add("z2");
        bottom.add("z3");



        listDataChild.put(listDataHeader.get(0), top); // Header, Child data
        listDataChild.put(listDataHeader.get(1), mid);
        listDataChild.put(listDataHeader.get(2), bottom);
//        listDataHeader = new ArrayList<String>();
//        listDataChild = new HashMap<String, List<String>>();

//        ExpandedMenuModel item1 = new ExpandedMenuModel();
//        item1.setIconName("Home");
//        item1.setIconImg(android.R.drawable.ic_menu_camera);
//        // Adding data header
//        listDataHeader.add(item1);
//
//        ExpandedMenuModel _item1 = new ExpandedMenuModel();
//        _item1.setIconName("Services");
//        _item1.setIconImg(R.drawable.ic_baseline_construction_24);
//        // Adding data header
////        listDataHeader.add(_item1);
//
//        ExpandedMenuModel item2 = new ExpandedMenuModel();
//        item2.setIconName("News");
//        item2.setIconImg(R.drawable.ic_baseline_list_24);
//        listDataHeader.add(item2);
//
//        ExpandedMenuModel item3 = new ExpandedMenuModel();
//        item3.setIconName("@string/menu_tic_tac_toe");
//        listDataHeader.add(item3);
//        // Adding child data
//        List<ExpandedMenuModel> heading1 = new ArrayList<ExpandedMenuModel>();
//        heading1.add(_item1);
//
//        List<ExpandedMenuModel> heading2 = new ArrayList<ExpandedMenuModel>();
////        heading2.add(item2);
////        heading2.add(item2);
////        heading2.add(item2);
//
//        listDataChild.put(listDataHeader.get(0), heading1);// Header, Child data
//        listDataChild.put(listDataHeader.get(1), heading2);


    }
}