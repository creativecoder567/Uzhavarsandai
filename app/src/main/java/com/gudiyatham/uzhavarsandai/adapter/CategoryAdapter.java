package com.gudiyatham.uzhavarsandai.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gudiyatham.uzhavarsandai.R;
import com.gudiyatham.uzhavarsandai.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sarath on 11/16/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryDataViewHolder> {
    List<Product> mCategoryDatas = new ArrayList<>();

    Context ctx;

    public CategoryAdapter(ArrayList<Product> arrayList, Context ctx) {
        this.mCategoryDatas = arrayList;
        this.ctx=ctx;
    }
    @Override
    public CategoryDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item,parent,false);
        CategoryDataViewHolder dataViewHolder = new CategoryDataViewHolder(view);
        return dataViewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryDataViewHolder holder, int position) {
        Product categoryData = mCategoryDatas.get(position);
        holder.textView.setText(categoryData.getName());
        Glide.with(ctx).load(mCategoryDatas.get(position).getImage()).into(holder.image);

//        if (categoryData.getAvailability().equals("1")) {
//            holder.availableTv.setText("buy now");
//        } else{
//            holder.availableTv.setTextColor(Color.RED);
//            holder.availableTv.setText("coming soon");
//        }
    }

    @Override
    public int getItemCount() {
        return mCategoryDatas.size();
    }

    public void setCategoryDatas(List<Product> mCategoryDatas) {
        this.mCategoryDatas = mCategoryDatas;
    }

    public class CategoryDataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView availableTv;
        ImageView image;
        TextView textView;

        public CategoryDataViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            image = (ImageView) itemView.findViewById(R.id.category_name_iv);
            textView = (TextView )itemView.findViewById(R.id.category_name);
            availableTv = (TextView) itemView.findViewById(R.id.availableTv);
        }


        @Override
        public void onClick(View v) {
//            int position = getAdapterPosition();
//            Product categoryData = mCategoryDatas.get(position);
//
//            Intent intent = new Intent(ctx, OrderMilk.class);
//            //intent.putExtra("image", categoryData.getImage());
//
//            if (categoryData.getAvailability().equals("1"))
//                intent.putExtra("productinfo", categoryData.getName());
//            intent.putExtra("productprice",categoryData.getPrice());
//            ctx.startActivity(intent);
        }

    }
}
