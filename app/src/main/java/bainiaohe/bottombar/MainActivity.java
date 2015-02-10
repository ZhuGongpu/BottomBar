package bainiaohe.bottombar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";
    /**
     * Fragments
     */
    private List<Fragment> fragments = new ArrayList<Fragment>();

    /**
     * Bottom Bar
     */
    private LinearLayout bottomBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }


    /**
     * onCreate时初始化
     */
    private void init() {
        setupBottomBar();

        setupFragments();

        //默认选中第0个
        onTabSelected(bottomBar.getChildAt(0));
    }

    /**
     * 设置bottom bar
     */
    private void setupBottomBar() {
        bottomBar = (LinearLayout) findViewById(R.id.main_bottom_bar);

//TODO 设置bottom bar 例如:(drawable为selector)
//        bottomBar.addView(BottomBarTab.genTab(this, R.drawable.tab_info, R.string.tab_internship));
//        bottomBar.addView(BottomBarTab.genTab(this, R.drawable.tab_info, R.string.tab_info));
//        bottomBar.addView(BottomBarTab.genTab(this, R.drawable.tab_message, R.string.tab_messages));
//        bottomBar.addView(BottomBarTab.genTab(this, R.drawable.tab_friends, R.string.tab_friends));
//        bottomBar.addView(BottomBarTab.genTab(this, R.drawable.tab_personal_center, R.string.tab_personal_center));
    }

    /**
     * 添加fragments
     */
    private void setupFragments() {
        //TODO Setup Fragments，例如:
//        this.fragments.add(new InternshipFragment());
//        this.fragments.add(new InfoFragment());
//        this.fragments.add(new MessageFragment());
//        this.fragments.add(new FriendsFragment());
//        this.fragments.add(new PersonalCenterFragment());
    }

    /**
     * 当页面被选中时调用
     * 在xml中绑定点击事件
     *
     * @param view
     */
    public void onTabSelected(View view) {

        //调整tab颜色
        view.setSelected(true);
        int tabIndex = 0;
        for (int i = 0; i < bottomBar.getChildCount(); i++) {
            if (bottomBar.getChildAt(i) != view) {
                bottomBar.getChildAt(i).setSelected(false);
            } else
                tabIndex = i;

        }

        Log.e(TAG, "tab index = " + tabIndex);


        //Set Fragment
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        for (int i = 0; i < this.fragments.size(); i++) {
            if (i != tabIndex) {
                if (this.fragments.get(i).isAdded())
                    transaction.hide(this.fragments.get(i));
            }
        }

        if (this.fragments.get(tabIndex).isAdded())
            transaction.show(this.fragments.get(tabIndex));
        else
            transaction.add(R.id.fragment_container, this.fragments.get(tabIndex));

        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        finish();
    }


}
