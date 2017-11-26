package com.gudiyatham.uzhavarsandai.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.gudiyatham.uzhavarsandai.R;
import com.gudiyatham.uzhavarsandai.adapter.UpdateAdapter;
import com.gudiyatham.uzhavarsandai.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {


    RecyclerView recyclerView;
    private UpdateAdapter updateAdapter;
    ArrayList<Product> arrayList = new ArrayList<>();
    Button btn_update;
    DatabaseReference databaseProducts;
    //HashMap<String, Integer> productPriceMap = new HashMap<String,Integer>();

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        databaseProducts = FirebaseDatabase.getInstance().getReference("products");
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_about, container, false);

        recyclerView = v.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        updateAdapter = new UpdateAdapter(arrayList, getContext());
        recyclerView.setAdapter(updateAdapter);

        btn_update = v.findViewById(R.id.btn_update);


        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Integer> map1 = updateAdapter.productPriceMap;
                for (Map.Entry<String, Integer> entry : map1.entrySet()) {
                    String key = entry.getKey();
                    Integer value = entry.getValue();
                    Log.i(TAG, "onClick: "+key);
                    if (value!=null)
                    databaseProducts.child(key).child("price").setValue(value).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
                        }
                    });
                }}
        });

       /* for (int i = 0; i <20 ; i++) {
            Product  product=new Product("names "+i,"Urll",10+i);
            databaseProducts.push().setValue(product).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getContext(), "sss", Toast.LENGTH_SHORT).show();
                }
            });
        }*/

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
                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    Product product = postSnapshot.getValue(Product.class);
                    product.setId(postSnapshot.getKey());
                    Log.d("product", "onDataChange: " + product.toString());
                    // Toast.makeText(MainActivity.this, ""+product.toString(), Toast.LENGTH_SHORT).show();
                    //adding artist to the list
                    arrayList.add(product);
                }



                updateAdapter.notifyDataSetChanged();
                Log.i(TAG, "onDataChange:getItemCount "+updateAdapter.getItemCount());
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("error", "onCancelled: " + databaseError);
            }
        });


    }
}
