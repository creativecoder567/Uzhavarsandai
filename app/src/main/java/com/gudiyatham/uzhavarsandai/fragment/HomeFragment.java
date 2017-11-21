package com.gudiyatham.uzhavarsandai.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.gudiyatham.uzhavarsandai.MainActivity;
import com.gudiyatham.uzhavarsandai.R;
import com.gudiyatham.uzhavarsandai.adapter.CategoryAdapter;
import com.gudiyatham.uzhavarsandai.model.Product;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    ArrayList<Product> arrayList = new ArrayList<>();

    DatabaseReference databaseProducts;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        databaseProducts = FirebaseDatabase.getInstance().getReference("products");
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = v.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        categoryAdapter= new CategoryAdapter(arrayList,getContext());
        recyclerView.setAdapter(categoryAdapter);
        return v;
    }



    @Override
    public void onStart() {
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
                   // Toast.makeText(MainActivity.this, ""+product.toString(), Toast.LENGTH_SHORT).show();
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
