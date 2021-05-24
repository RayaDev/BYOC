package com.rayadev.byoc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.rayadev.byoc.room.Converter;
import com.rayadev.byoc.room.ConverterViewModel;
import com.rayadev.byoc.ui.main.CustomConverterActivity;
import com.rayadev.byoc.ui.main.PageAdapter;

public class MainActivity extends AppCompatActivity {

    private ImageView mCustomConverterButton, mAddHomeSetConverterButton;
    private LinearLayout mBottomUI;
    private CardView mConverterCardView;
    public ConverterViewModel mConverterViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initializeUI();

    }

    private void initializeUI() {

        linkViews();
        setOnClicks();
        keyBoardManager();
        setUpToolbar();
        TabLayout tabLayout = setUpTabLayout();
        setUpPageAdapter(tabLayout);

    }

    private void keyBoardManager() {

        //Keeps the keyboard from auto-popping up after onCreate()
        getWindow().setSoftInputMode(WindowManager.
                LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void setOnClicks() {
        mCustomConverterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CustomConverterActivity.class);
                startActivity(intent);
            }
        });

        mAddHomeSetConverterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Converter converter = new Converter("M","FT", R.drawable.converter_icon_distance, 1,1);
                mConverterViewModel.insertConverter(converter);

                Toast.makeText(MainActivity.this, "Add clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void linkViews() {
        mCustomConverterButton = findViewById(R.id.build_button);
        mAddHomeSetConverterButton = findViewById(R.id.add_button);
        mBottomUI = findViewById(R.id.mainActivity_BottomUI_LinearLayout);
//        mConverterCardView = findViewById(R.id.converter_cardlayout_include);
        mConverterViewModel = new ViewModelProvider(this).get(ConverterViewModel.class);

    }


    private void setUpToolbar() {

        //Links the toolbar over the app Actionbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    private TabLayout setUpTabLayout() {

        // Create an instance of the tab layout from the view.
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        // Set the title for each tab.
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_text_1));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_text_2));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_text_3));

        // Set the tabs to fill the entire layout.
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        return tabLayout;

    }

    private void setUpPageAdapter(TabLayout tabLayout) {

        // Use PagerAdapter to manage page views in fragments.

        // Each page is represented by its own fragment
        //PageAdapter populates the screens that the ViewPager passes to the TabLayout
        final ViewPager viewPager = findViewById(R.id.pager);
        final PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        // Setting a listener for clicks
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition()); //Important for clicking over to new tabs.
                Log.i("TAG", String.valueOf(tab.getPosition()));

                //Controls the visibility of the bottom UI
                if( tab.getPosition() == 2) {
                    mBottomUI.setVisibility(View.INVISIBLE);

                }

                else {
                    mBottomUI.setVisibility(View.VISIBLE);
//                    mConverterCardView.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);


        return super.onCreateOptionsMenu(menu);
    }

}