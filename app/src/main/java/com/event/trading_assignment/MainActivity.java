package com.event.trading_assignment;

import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;

import com.event.trading_assignment.adapters.SelectorStatePagerAdapter;
import com.event.trading_assignment.dbHelper.DBHelper;
import com.event.trading_assignment.fragment.MarketWatchFragment;
import com.event.trading_assignment.fragment.MyProfile;
import com.event.trading_assignment.utilities.Navigator;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewPager;

    private static Navigator navigator;
    private DBHelper mydb ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mydb = new DBHelper(MainActivity.this);

        viewPager = findViewById(R.id.fragmentContainer);
        setupViewPager(viewPager);

        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

//        navigator.goTo(new HomeTabFragment());


    }



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_market:
                    viewPager.setCurrentItem(0);
                    return true;
//                case R.id.navigation_order:
//                    viewPager.setCurrentItem(1);
//                    return true;
                case R.id.navigation_user:
                    viewPager.setCurrentItem(1);
                    return true;

            }

            return false;
        }
    };


    private void setupViewPager(ViewPager viewPager){

        SelectorStatePagerAdapter adapter = new SelectorStatePagerAdapter(getSupportFragmentManager());
//        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new MarketWatchFragment(), "Market Watcher");
//        adapter.addFragment(new MyProfile(), "Portfolio");
        adapter.addFragment(new MyProfile(), "Orders");

//        adapter.addFragment(new SettingsFragment(), "Settings");
        viewPager.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.navigation_order) {

            viewAll();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public Navigator getNavigator() {
        return navigator;
    }






    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void viewAll() {


        Cursor res = mydb.getAllData();

        if(res.getCount() == 0) {
            // show message
            showMessage("My Cart: ","EMPTY !");
            return;
        }


        StringBuffer buffer = new StringBuffer();

        while (res.moveToNext()) {

            buffer.append("Order No :"+ res.getString(0)+"\n");
            buffer.append("Name :"+ res.getString(1)+"\n");
            buffer.append("Price :"+ res.getString(2)+"\n");
            buffer.append("Id :"+ res.getString(3)+"\n");
            buffer.append("Exchange :"+ res.getString(4)+"\n");
            buffer.append("Quantity :"+ res.getString(5)+"\n");
            buffer.append("Type :"+ res.getString(6)+"\n\n");


        }

        // Show all data

        showMessage("My Cart: ",buffer.toString());

    }



    public void showMessage(String title,String Message){


        TextView showText = new TextView(this);
        showText.setText(Message);
        showText.setTextIsSelectable(true);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);

        builder.setMessage(Message);
        builder.show();


    }







}
