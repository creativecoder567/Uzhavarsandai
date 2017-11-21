package com.gudiyatham.uzhavarsandai.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gudiyatham.uzhavarsandai.R;
import com.gudiyatham.uzhavarsandai.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sarath on 11/22/2017.
 */

public class UpdateAdapter extends RecyclerView.Adapter<UpdateAdapter.UpdateDataViewHolder> {
    List<Product> mCategoryDatas = new ArrayList<>();

    Context ctx;

    public UpdateAdapter(ArrayList<Product> arrayList, Context ctx) {
        this.mCategoryDatas = arrayList;
        this.ctx=ctx;
    }
    @Override
    public UpdateAdapter.UpdateDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.updateprice,parent,false);
        UpdateAdapter.UpdateDataViewHolder dataViewHolder = new UpdateAdapter.UpdateDataViewHolder(view);
        return dataViewHolder;
    }

    @Override
    public void onBindViewHolder(UpdateAdapter.UpdateDataViewHolder holder, int position) {
        Product categoryData = mCategoryDatas.get(position);
        holder.category_name.setText(categoryData.getName());
        Glide.with(ctx).load(mCategoryDatas.get(position).getImage()).into(holder.image);
        holder.etPrice.setText(categoryData.getPrice()+"");
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

    public class UpdateDataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView image;
        TextView category_name,tvPrice;
        EditText etPrice;

        public UpdateDataViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            image = (ImageView) itemView.findViewById(R.id.category_name_iv);
            category_name = (TextView )itemView.findViewById(R.id.category_name);
            //tvPrice=itemView.findViewById(R.id.tvPrice);
            etPrice=itemView.findViewById(R.id.etPrice);
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

