package com.gudiyatham.uzhavarsandai;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.gudiyatham.uzhavarsandai.adapter.CategoryAdapter;
import com.gudiyatham.uzhavarsandai.fragment.AboutFragment;
import com.gudiyatham.uzhavarsandai.fragment.HomeFragment;
import com.gudiyatham.uzhavarsandai.model.Product;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;


    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    private HomeFragment mainFragment;


    //DatabaseReference databaseItems;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mainFragment = new HomeFragment();
                    Bundle bundle = new Bundle();
                    mFragmentManager = getSupportFragmentManager();
                    mFragmentTransaction = mFragmentManager.beginTransaction();
                    mainFragment.setArguments(bundle);
                    mFragmentTransaction.replace(R.id.containerView, mainFragment).commit();
                   // mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    AboutFragment about = new AboutFragment();
                    //Bundle bundle = new Bundle();
                    mFragmentManager = getSupportFragmentManager();
                    mFragmentTransaction = mFragmentManager.beginTransaction();
                    //mainFragment.setArguments(bundle);
                    mFragmentTransaction.replace(R.id.containerView, about).commit();
                   // mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                   // mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //databaseItems = FirebaseDatabase.getInstance().getReference("items");
        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mainFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mainFragment.setArguments(bundle);
        mFragmentTransaction.replace(R.id.containerView, mainFragment).commit();
    }



}
