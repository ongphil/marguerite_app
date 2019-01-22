package marguerite.marguerite;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements ViewPager.OnPageChangeListener, HomeFragment.OnFragmentInteractionListener,
        OrdersFragment.OnFragmentInteractionListener,
        ProfileFragment.OnFragmentInteractionListener,SignupFragment.OnFragmentInteractionListener,
        MeansOfPaymentFragment.OnFragmentInteractionListener,
        OrderStatusWaitingTimeFragment.OnFragmentInteractionListener,
        OrderIsReadyFragment.OnFragmentInteractionListener{

    private ViewPager viewPager;
    private BottomNavigationView navigation;

    private HomeFragment homeFragment;
    private OrdersFragment ordersFragment;
    private ProfileFragment profileFragment;
    private SignupFragment signupFragment;
    private MeansOfPaymentFragment meansOfPaymentFragment;
    private OrderStatusWaitingTimeFragment orderStatusWaitingTimeFragment;
    private OrderIsReadyFragment orderIsReadyFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener((ViewPager.OnPageChangeListener) this);

        setTitle("Accueil");

        homeFragment = new HomeFragment();
        ordersFragment = new OrdersFragment();
        profileFragment = new ProfileFragment();
        signupFragment = new SignupFragment();
        meansOfPaymentFragment= new MeansOfPaymentFragment();
        orderStatusWaitingTimeFragment= new OrderStatusWaitingTimeFragment();
        orderIsReadyFragment= new OrderIsReadyFragment();

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return homeFragment;
                    case 1:
                        return ordersFragment;
                    case 2:
                        return meansOfPaymentFragment;
                                //orderStatusWaitingTimeFragment;
                                //orderIsReadyFragment;
                              //  signupFragment;
                        //return profileFragment;
                }
                return null;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    setTitle("Accueil");
                    return true;
                case R.id.navigation_orders:
                    viewPager.setCurrentItem(1);
                    setTitle("Mes commandes");
                    return true;
                case R.id.navigation_profile:
                    viewPager.setCurrentItem(2);
                    setTitle("Mon compte");
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onFragmentInteractionHome(Uri uri) {

    }

    @Override
    public void onFragmentInteractionOrders(Uri uri) {

    }

    @Override
    public void onFragmentInteractionProfile(Uri uri) {

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                setTitle("Accueil");
                break;
            case 1:
                setTitle("Mes commandes");
                break;
            case 2:
                setTitle("Mon compte");
                break;
        }
        navigation.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
