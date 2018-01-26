package com.ameliariely.callswiper;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ameliariely.callswiper.data.DbHelper;
import com.ameliariely.callswiper.data.model.Mom;

import java.util.ArrayList;


public class JavaSwipingActivity extends AppCompatActivity {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private final static int MY_PERMISSIONS_REQUEST_CALL = 456;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swiping);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        DbHelper dbHelper = ((CallSwiperApp) getApplication()).getDbHelper();

        // Set up the ViewPager with the sections adapter.
        ViewPager container = findViewById(R.id.container);
        container.setAdapter(mSectionsPagerAdapter);

    }

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        private ArrayList moms;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void setMomList(ArrayList<Mom> moms) {
            this.moms = moms;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return BigCallFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return moms.size();
        }

    }

    public static class BigCallFragment extends Fragment {

        private static String ARG_SECTION_NUMBER = "section_number";

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_swiping, container, false);
            ((TextView) rootView.findViewById(R.id.section_label)).setText("Mom" + getArguments().getInt(ARG_SECTION_NUMBER));
            ImageView circleButton = rootView.findViewById(R.id.circle_button);
            circleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkPermissionsAndPlaceCall();
                }
            });
            return rootView;
        }

        private void checkPermissionsAndPlaceCall() {
            if (ActivityCompat.checkSelfPermission(getContext(),
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_CALL);
                return;
            }
            placeCall();
        }

        private void placeCall() {
            String url = "tel:3334444";
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
            startActivity(intent);
        }

        @Override
        public void onRequestPermissionsResult(int requestCode,
                                               String permissions[], int[] grantResults) {
            switch (requestCode) {
                case MY_PERMISSIONS_REQUEST_CALL: {
                    // If request is cancelled, the result arrays are empty.
                    if (grantResults.length > 0
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        placeCall();
                    } else {
                        Toast.makeText(getContext(), "Boo. Give us permission.", Toast.LENGTH_SHORT).show();
                    }
                    // return here if more cases
                }

                // other 'case' lines to check for other
                // permissions this app might request.
            }
        }

        public static BigCallFragment newInstance(int sectionNumber) {
            BigCallFragment fragment = new BigCallFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
    }
}
