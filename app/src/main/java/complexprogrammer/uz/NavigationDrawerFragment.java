package complexprogrammer.uz;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import complexprogrammer.uz.ui.AdminPanelFragment;
import complexprogrammer.uz.ui.ContactFragment;
import complexprogrammer.uz.ui.LanguageFragment;
import complexprogrammer.uz.ui.account.LoginActivity;
import complexprogrammer.uz.ui.account.LoginTabFragment;
import complexprogrammer.uz.ui.account.MyAccountFragment;
import complexprogrammer.uz.ui.home.HomeFragment;
import complexprogrammer.uz.ui.news.NewsFragment;
import complexprogrammer.uz.ui.online_games.TicTacToeFragment;
import complexprogrammer.uz.ui.services.ChangeTextFragment;
import complexprogrammer.uz.ui.services.CompressImageFragment;
import complexprogrammer.uz.ui.services.GuidFragment;


public class NavigationDrawerFragment extends Fragment {
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

    private NavigationDrawerCallbacks mCallbacks;

    private ActionBarDrawerToggle mDrawerToggle;

    private DrawerLayout mDrawerLayout;
    private ExpandableListView mDrawerListView;
    private View mFragmentContainerView;

    private int mCurrentSelectedPosition = 0;




    ArrayList<String> groupItem = new ArrayList<String>();
    ArrayList<Object> childItem = new ArrayList<Object>();

    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    public NavigationDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Indicate that this fragment would like to influence the set of actions in the action bar.
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        ActionBar actionBar = getActionBar();
//        actionBar.hide();

        mDrawerListView = (ExpandableListView) inflater.inflate(
                R.layout.drawer_drawer, container, false);
        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });

        expandableListDetail = complexprogrammer.uz.ExpandableListDataPump.getData(getContext());
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());


        mDrawerListView.setAdapter(new complexprogrammer.uz.ExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail));

        mDrawerListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });


        mDrawerListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

//                String te=expandableListTitle.get(groupPosition);
//                Toast.makeText(getContext(),te,Toast.LENGTH_SHORT).show();
                boolean retVal = true;

                if (groupPosition == ExpandableListAdapter.ITEM1) {
                    ShowFragment(new HomeFragment());

                } else if (groupPosition == ExpandableListAdapter.ITEM2) {
                    ShowFragment(new NewsFragment());
                } else if (groupPosition == ExpandableListAdapter.ITEM3) {
                    retVal = false;
                    // call some activity here
                } else if (groupPosition == ExpandableListAdapter.ITEM4) {
                    retVal = false;
                    // call some activity here

                }
                else if (groupPosition == ExpandableListAdapter.ITEM5) {
                    retVal = false;
                    // call some activity here
                }
                else if (groupPosition == ExpandableListAdapter.ITEM6) {
                    ShowFragment(new ContactFragment());
                }
                else if (groupPosition == ExpandableListAdapter.ITEM7) {
                    retVal = false;
                    // call some activity here

                }
                else if (groupPosition == ExpandableListAdapter.ITEM8) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
//                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//                    fragmentTransaction.replace(R.id.nav_host_fragment, new MyAccountFragment(1));
//                    fragmentTransaction.commit();
//                    ShowFragment(new LoginFragment());
                }
                return retVal;
            }
        });

        mDrawerListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

//                Toast.makeText(
//                        getContext(),
//                        expandableListTitle.get(groupPosition)
//                                + " : "
//                                + expandableListDetail.get(
//                                expandableListTitle.get(groupPosition)).get(
//                                childPosition), Toast.LENGTH_SHORT)
//                        .show();



                if (groupPosition == ExpandableListAdapter.ITEM3) {
                    if (childPosition == ExpandableListAdapter.SUBITEM1_1) {
                        ShowFragment(new TicTacToeFragment());
                    }
//                    else if (childPosition == ExpandableListAdapter.SUBITEM1_2) {
//
//                        // call activity here
//
//                    }
//                    else if (childPosition == ExpandableListAdapter.SUBITEM1_3) {
//
//                        // call activity here
//
//                    }
//                    else if (childPosition == ExpandableListAdapter.SUBITEM1_4) {
//
//                        // call activity here
//
//                    }


                } else if (groupPosition == complexprogrammer.uz.ExpandableListAdapter.ITEM4) {

                    if (childPosition == complexprogrammer.uz.ExpandableListAdapter.SUBITEM2_1) {
                        ShowFragment(new GuidFragment());
                    }
                    else if (childPosition == complexprogrammer.uz.ExpandableListAdapter.SUBITEM2_2) {
                        ShowFragment(new ChangeTextFragment());
                    }
                    else if (childPosition == complexprogrammer.uz.ExpandableListAdapter.SUBITEM2_3) {
                        ShowFragment(new CompressImageFragment());
                    }
//                    else if (childPosition == ExpandableListAdapter.SUBITEM2_4) {
//
//                        // call activity here
//
//                    }


                }
               else if (groupPosition == complexprogrammer.uz.ExpandableListAdapter.ITEM7) {

                    if (childPosition == complexprogrammer.uz.ExpandableListAdapter.SUBITEM4_1) {
                        ShowFragment(new LanguageFragment());
                    }
                    else
                    if (childPosition == complexprogrammer.uz.ExpandableListAdapter.SUBITEM4_2) {

                        LoginTabFragment loginTabFragment=new LoginTabFragment();
                        ShowFragment(new MyAccountFragment(loginTabFragment.getUserId(getActivity().getApplicationContext())));
                    }
                    else if (childPosition == complexprogrammer.uz.ExpandableListAdapter.SUBITEM4_3) {
                        ShowFragment(new AdminPanelFragment());
                    }
                    //                    else if (childPosition == ExpandableListAdapter.SUBITEM2_4) {
                    //
                    //                        // call activity here
                    //
                    //                    }
               }
                return true;
            }
        });

        mDrawerListView.setItemChecked(mCurrentSelectedPosition, true);

        View header= inflater.inflate(R.layout.drawer_header, null);
        mDrawerListView.addHeaderView(header);

        return mDrawerListView;
    }
    private void ShowFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
            fragmentTransaction.commit();
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }


    public boolean isDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
    }

    /**
     * Users of this fragment must call this method to set up the navigation drawer interactions.
     *
     * @param fragmentId   The android:id of this fragment in its activity's layout.
     * @param drawerLayout The DrawerLayout containing this fragment's UI.
     */
    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.ic_menu_gallery, GravityCompat.START);
        // set up the drawer's list view with items and click listener

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        // ActionBarDrawerToggle ties together the the proper interactions
        // between the navigation drawer and the action bar app icon.
        mDrawerToggle = new ActionBarDrawerToggle(
                getActivity(),                    /* host Activity */
                mDrawerLayout,                    /* DrawerLayout object */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActionBar().setIcon(R.drawable.ic_menu_camera);

                if (!isAdded()) {
                    return;
                }

                getActivity().supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);


                if (!isAdded()) {
                    return;
                }



                getActivity().supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }
        };


        // Defer code dependent on restoration of previous instance state.
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void selectItem(int position) {
        mCurrentSelectedPosition = position;
        if (mDrawerListView != null) {
            mDrawerListView.setItemChecked(position, true);
        }
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mFragmentContainerView);
        }
        if (mCallbacks != null) {
            mCallbacks.onNavigationDrawerItemSelected(position);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Forward the new configuration the drawer toggle component.
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // If the drawer is open, show the global app actions in the action bar. See also
        // showGlobalContextActionBar, which controls the top-left area of the action bar.
//        if (mDrawerLayout != null && isDrawerOpen()) {
//            inflater.inflate(R.menu.main_menu, menu);
//            showGlobalContextActionBar();
//        }
//        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    /**
     * Per the navigation drawer design guidelines, updates the action bar to show the global app
     * 'context', rather than just what's in the current screen.
     */
    private void showGlobalContextActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

    }

    private ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    /**
     * Callbacks interface that all activities using this fragment must implement.
     */
    public static interface NavigationDrawerCallbacks {
        @SuppressWarnings("StatementWithEmptyBody")
        boolean onNavigationItemSelected(MenuItem item);

        /**
         * Called when an item in the navigation drawer is selected.
         */
        void onNavigationDrawerItemSelected(int position);
    }
}
