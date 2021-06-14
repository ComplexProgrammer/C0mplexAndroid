package complexprogrammer.uz.ui.account;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import complexprogrammer.uz.R;

public class LoginActivity extends AppCompatActivity {

    LoginAdapter adapter;
    FrameLayout login_tab_fragment;
    TabLayout tabLayout;
    ViewPager viewPager;
    FloatingActionButton facebook,google,twitter;
    float v=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.view_pager);
        facebook=findViewById(R.id.fab_facebook);
        google=findViewById(R.id.fab_google);
        twitter=findViewById(R.id.fab_twitter);

        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Sign Up"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        adapter = new LoginAdapter(getSupportFragmentManager(),this,tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    facebook.setVisibility(View.VISIBLE);
                    google.setVisibility(View.VISIBLE);
                    twitter.setVisibility(View.VISIBLE);


                    facebook.setTranslationY(300);
                    google.setTranslationY(300);
                    twitter.setTranslationY(300);

                    facebook.setAlpha(v);
                    google.setAlpha(v);
                    twitter.setAlpha(v);

                    facebook.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
                    google.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
                    twitter.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
                } else if (tab.getPosition() == 1) {
                    facebook.setVisibility(View.GONE);
                    google.setVisibility(View.GONE);
                    twitter.setVisibility(View.GONE);


                }

                viewPager.setAdapter(adapter);
                viewPager.setCurrentItem(tab.getPosition());

                tabLayout.setTranslationY(300);

                tabLayout.setAlpha(v);

                tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(100).start();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }





}