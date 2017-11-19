package com.gudiyatham.uzhavarsandai;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
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
import com.gudiyatham.uzhavarsandai.model.Product;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    ArrayList<Product> arrayList = new ArrayList<>();

    DatabaseReference databaseProducts;
    //DatabaseReference databaseItems;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                   // mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
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

        databaseProducts = FirebaseDatabase.getInstance().getReference("products");
        //databaseItems = FirebaseDatabase.getInstance().getReference("items");
        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        categoryAdapter= new CategoryAdapter(arrayList,this);
        recyclerView.setAdapter(categoryAdapter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        databaseProducts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                arrayList.clear();
                Log.d("product", "onDataChange: "+dataSnapshot);
                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                   Product product = postSnapshot.getValue(Product.class);
                    //Product product = postSnapshot.child("name").getValue(Product.class);
                    Log.d("product", "onDataChange: "+product.toString());
                    Toast.makeText(MainActivity.this, ""+product.toString(), Toast.LENGTH_SHORT).show();
                    //adding artist to the list
                    arrayList.add(product);
                }

                //creating adapter
              //  ArtistList artistAdapter = new ArtistList(MainActivity.this, artists);
                //attaching adapter to the listview
              //  listViewArtists.setAdapter(artistAdapter);

                categoryAdapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("error", "onCancelled: "+databaseError);
            }
        });

//        databaseItems.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//              //Product product =  dataSnapshot.getValue(Product.class);
//                //Log.d("product", "onDataChange: "+product.getName());
//                String name= dataSnapshot.child("name").getValue(String.class);
//                Log.d("product", "onDataChange: "+name);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }

}
