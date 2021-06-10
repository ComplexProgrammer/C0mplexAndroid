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
import complexprogrammer.uz.models.ExpandListModel;

public class MainActivity_old extends AppCompatActivity {



    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_old);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

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
    ArrayList<ExpandListModel> listDataHeader=new ArrayList<>();
    HashMap<ExpandListModel,List<ExpandListModel>> listDataChild=new HashMap<>();
    ExpandListAdapter listAdapter;


    private void enableExpandableList() {
        listDataHeader = new ArrayList<ExpandListModel>();
        listDataChild = new HashMap<ExpandListModel, List<ExpandListModel>>();
        expListView = (ExpandableListView) findViewById(R.id.navigationmenu);

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

    private void prepareListData(List<ExpandListModel> listDataHeader, Map<ExpandListModel,
                List<ExpandListModel>> listDataChild) {


        ExpandListModel item1 = new ExpandListModel();
        item1.setIconName("Home");
        item1.setIconImg(R.drawable.ic_menu_camera);
        // Adding data header
        listDataHeader.add(item1);

        ExpandListModel item2 = new ExpandListModel();
        item2.setIconName("News");
        item2.setIconImg(R.drawable.ic_baseline_construction_24);
//        item2.setIconImg(R.drawable.ic_baseline_list_24);
        listDataHeader.add(item2);

        ExpandListModel item3 = new ExpandListModel();
        item3.setIconName("test");
        item3.setIconImg(R.drawable.ic_baseline_list_24);
//        item3.setIconImg(R.drawable.ic_baseline_apps_24);
        listDataHeader.add(item3);

        // Adding child data
        List<ExpandListModel> heading1 = new ArrayList<ExpandListModel>();
        heading1.add(item1);

        List<ExpandListModel> heading2 = new ArrayList<ExpandListModel>();
        heading2.add(item2);
        heading2.add(item2);
        heading2.add(item2);

        listDataChild.put(listDataHeader.get(0), heading1);// Header, Child data
        listDataChild.put(listDataHeader.get(1), heading2);
        listDataChild.put(listDataHeader.get(2), heading2);


    }
}