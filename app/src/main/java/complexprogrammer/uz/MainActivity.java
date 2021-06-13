package complexprogrammer.uz;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;

import complexprogrammer.uz.ui.gallery.GalleryFragment;
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
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#FF018786"));
        getSupportActionBar().setTitle(R.string.nav_header_title);
        getSupportActionBar().setBackgroundDrawable(colorDrawable);



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
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp() ;
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
    private void ShowFragment(int itemId) {

        Fragment fragment = null;

        switch (itemId) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;
            case R.id.nav_news:
                fragment = new NewsFragment();
                break;
            case R.id.nav_gallery:
                fragment = new GalleryFragment();
                break;

        }


        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override

    public boolean onNavigationItemSelected(MenuItem item) {


        //Calling the ShowFragment() method here to show the our created menu as default menus.
        ShowFragment(item.getItemId());


        return true;
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