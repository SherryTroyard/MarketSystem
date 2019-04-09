package indi.apst.marketsystem;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import indi.apst.marketsystem.fragment.CommodityFragment;
import indi.apst.marketsystem.fragment.FruitFragment;
import indi.apst.marketsystem.fragment.MeatFragment;
import indi.apst.marketsystem.fragment.MineFragment;
import indi.apst.marketsystem.fragment.ShoppingFragment;
import indi.apst.marketsystem.fragment.VegetableFragment;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationBar.OnTabSelectedListener{

    private BottomNavigationBar bottomNavigationBar;
    private String TAG = MainActivity.class.getSimpleName();
    private TextBadgeItem badgeItem; //添加角标
    private CommodityFragment commodityFragment;
    private ShoppingFragment shoppingFragment;
    private MineFragment mineFragment;

    private String[] titles;
    private ListView drawerList;
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBottomNavigationBar();
        setDefaultFragment();

        titles = getResources().getStringArray(R.array.titles);
        drawerList = findViewById(R.id.drawer);
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerList.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_activated_1,titles));
        drawerList.setOnItemClickListener(new DrawerItemClickListerer());
    }

    private class DrawerItemClickListerer implements ListView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            selectItem(position);
        }
    }

    private void selectItem(int position){   //抽屉有问题
        Fragment fragment;
        switch (position){
            case 1:
                fragment = new MeatFragment();
                break;
            case 2:
                fragment = new VegetableFragment();
                break;
            case 3:
                fragment = new FruitFragment();
                break;
            default:
                fragment = new MeatFragment();
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame,fragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initBottomNavigationBar(){
        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setTabSelectedListener(this);
        badgeItem = new TextBadgeItem()
                .setHideOnSelect(true) //设置被选中时隐藏角标
                .setBackgroundColor(Color.RED)
                .setText("99");
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.baseline_store_mall_directory_white_24dp, "商店").setActiveColorResource(R.color.white))
                .addItem(new BottomNavigationItem(R.drawable.baseline_shopping_cart_white_24dp, "购物车").setActiveColorResource(R.color.white))
                .addItem(new BottomNavigationItem(R.drawable.baseline_person_white_24dp, "个人中心").setActiveColorResource(R.color.white))
                .setFirstSelectedPosition(0)
                .initialise();
    }

    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        commodityFragment = CommodityFragment.newInstance("首页");
        transaction.replace(R.id.tb, commodityFragment);
        getSupportActionBar().show();   //warning
        transaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
        Log.d(TAG, "onTabSelected() called with: " + "position = [" + position + "]");
        FragmentManager fm = this.getSupportFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                if (commodityFragment == null) {
                    commodityFragment = CommodityFragment.newInstance("");
                }
                transaction.replace(R.id.tb, commodityFragment);
                getSupportActionBar().show();   //warning
                break;
            case 1:
                if (shoppingFragment == null) {
                    shoppingFragment = ShoppingFragment.newInstance("");
                }
                transaction.replace(R.id.tb, shoppingFragment);
                getSupportActionBar().hide();   //warning
//                try {
//                    getSupportActionBar().getClass().getDeclaredMethod("setShowHideAnimationEnabled", boolean.class).invoke(getSupportActionBar(), false);
//                }
//                catch (Exception exception) {
//                    // The animation will still be displayed if an exception was thrown.
//                }
                break;
            case 2:
                if (mineFragment == null) {
                    mineFragment = MineFragment.newInstance("");
                }
                transaction.replace(R.id.tb, mineFragment);
                getSupportActionBar().hide();   //warning
                break;


            default:
                break;
        }

        transaction.commit();// 事务提交
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }


}
