package complexprogrammer.uz;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import complexprogrammer.uz.ui.home.HomeFragment;
import complexprogrammer.uz.ui.news.NewsFragment;
import complexprogrammer.uz.ui.online_games.TicTacToeFragment;

public class MainActivity extends AppCompatActivity  implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    private DrawerLayout mDrawerLayout;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(R.string.menu_home);


        // Set up the drawer.

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.main_layout));

        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_layout);



        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_news,R.id.nav_tic_tac_toe,R.id.nav_guid, R.id.nav_gallery, R.id.nav_slideshow)
//                .setDrawerLayout(mDrawerLayout)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(mNavigationDrawerFragment, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.navigation_drawer);
        return NavigationUI.navigateUp(navController, mDrawerLayout)
                || super.onSupportNavigateUp();
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

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Toast.makeText(this,"position "+position,Toast.LENGTH_SHORT).show();

        // update the main content by replacing fragments
        if(position==R.id.nav_home){
            Toast.makeText(this,"Home",Toast.LENGTH_SHORT).show();
            HomeFragment fragment=new HomeFragment();
            FragmentManager FM=getSupportFragmentManager();
            FM.beginTransaction().replace(R.id.nav_host_fragment,fragment,fragment.getTag()).commit();
        } else if (position==R.id.nav_news){
            Toast.makeText(this,"News",Toast.LENGTH_SHORT).show();
            NewsFragment fragment=new NewsFragment();
            FragmentManager FM=getSupportFragmentManager();
            FM.beginTransaction().replace(R.id.nav_host_fragment,fragment,fragment.getTag()).commit();
        } else if (position==R.id.nav_tic_tac_toe){
                    Toast.makeText(this,"Tic Tac Toe",Toast.LENGTH_SHORT).show();
                    TicTacToeFragment fragment=new TicTacToeFragment();
                    FragmentManager FM=getSupportFragmentManager();
                    FM.beginTransaction().replace(R.id.nav_host_fragment,fragment,fragment.getTag()).commit();
        } else if (position==R.id.nav_news){
                    Toast.makeText(this,"Home",Toast.LENGTH_SHORT).show();
                    HomeFragment fragment=new HomeFragment();
                    FragmentManager FM=getSupportFragmentManager();
                    FM.beginTransaction().replace(R.id.nav_host_fragment,fragment,fragment.getTag()).commit();
        } else if (position==R.id.nav_news){
                    Toast.makeText(this,"Home",Toast.LENGTH_SHORT).show();
                    HomeFragment fragment=new HomeFragment();
                    FragmentManager FM=getSupportFragmentManager();
                    FM.beginTransaction().replace(R.id.nav_host_fragment,fragment,fragment.getTag()).commit();
        } else if (position==R.id.nav_news){
                    Toast.makeText(this,"Home",Toast.LENGTH_SHORT).show();
                    HomeFragment fragment=new HomeFragment();
                    FragmentManager FM=getSupportFragmentManager();
                    FM.beginTransaction().replace(R.id.nav_host_fragment,fragment,fragment.getTag()).commit();
        } else if (position==R.id.nav_news){
                    Toast.makeText(this,"Home",Toast.LENGTH_SHORT).show();
                    HomeFragment fragment=new HomeFragment();
                    FragmentManager FM=getSupportFragmentManager();
                    FM.beginTransaction().replace(R.id.nav_host_fragment,fragment,fragment.getTag()).commit();
        } else if (position==R.id.nav_news){
                    Toast.makeText(this,"Home",Toast.LENGTH_SHORT).show();
                    HomeFragment fragment=new HomeFragment();
                    FragmentManager FM=getSupportFragmentManager();
                    FM.beginTransaction().replace(R.id.nav_host_fragment,fragment,fragment.getTag()).commit();
        } else if (position==R.id.nav_news){
                    Toast.makeText(this,"Home",Toast.LENGTH_SHORT).show();
                    HomeFragment fragment=new HomeFragment();
                    FragmentManager FM=getSupportFragmentManager();
                    FM.beginTransaction().replace(R.id.nav_host_fragment,fragment,fragment.getTag()).commit();
                }

            }
}