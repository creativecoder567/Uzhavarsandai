package com.gudiyatham.uzhavarsandai.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.gudiyatham.uzhavarsandai.R;
import com.gudiyatham.uzhavarsandai.SplashScreen;
import com.gudiyatham.uzhavarsandai.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sarath on 11/22/2017.
 */

public class UpdateAdapter extends RecyclerView.Adapter<UpdateAdapter.UpdateDataViewHolder> {
    private final CallBack callBack;
    List<Product> mCategoryDatas = new ArrayList<>();
    Context ctx;
    //   public static Set<Integer> price = new HashSet<>();
    public HashMap<String, Integer> productPriceMap = new HashMap<String, Integer>();

    public UpdateAdapter(ArrayList<Product> arrayList, Context ctx,CallBack  callBack) {
        this.mCategoryDatas = arrayList;
        this.ctx = ctx;
        this.callBack=callBack;
    }
public  interface CallBack{
        void delete(int pos);
}
    public HashMap<String, Integer> getProductPriceMap() {
        return productPriceMap;
    }

    @Override
    public UpdateAdapter.UpdateDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.updateprice, parent, false);
        UpdateAdapter.UpdateDataViewHolder dataViewHolder = new UpdateAdapter.UpdateDataViewHolder(view);
        return dataViewHolder;
    }

    @Override
    public void onBindViewHolder(final UpdateAdapter.UpdateDataViewHolder holder, int position) {
        final Product product = mCategoryDatas.get(position);
        holder.category_name.setText(product.getName());
        Glide.with(ctx).load(mCategoryDatas.get(position).getImage()).into(holder.image);
        holder.etPrice.setText(product.getPrice() + "");
        holder.etPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String value = holder.etPrice.getText().toString();
                Pattern p = Pattern.compile("(\\d+)");
                if (!TextUtils.isEmpty(value) && p.matcher(value).matches())
                    try {
                        productPriceMap.put(product.getId(), Integer.valueOf(value));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }

            }


            @Override
            public void afterTextChanged(Editable editable) {


            }
        });
    }

    @Override
    public int getItemCount() {
        return mCategoryDatas.size();
    }

    public void setCategoryDatas(List<Product> mCategoryDatas) {
        this.mCategoryDatas = mCategoryDatas;
    }

    public class UpdateDataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image;
        TextView category_name, tvPrice;
        EditText etPrice;

        public UpdateDataViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            image = (ImageView) view.findViewById(R.id.category_name_iv);
            category_name = (TextView) view.findViewById(R.id.category_name);
            //tvPrice=view.findViewById(R.id.tvPrice);
            etPrice = view.findViewById(R.id.etPrice);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                public AlertDialog alertDialog;

                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(ctx, android.R.style.Theme_Material_Light_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(ctx);
                    }


                    builder.setTitle("Are You sure Want to Delete?")
                            .setMessage("")
                            .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                   callBack.delete(getLayoutPosition());
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    alertDialog.dismiss();
                                }
                            })
                            .setCancelable(false)
                            .setIcon(R.mipmap.ic_launcher);
                    alertDialog = builder.show();
                   // callBack.delete(getLayoutPosition());
                    return true;
                }
            });
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

