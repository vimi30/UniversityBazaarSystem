package com.example.universitybazaarsystem;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Product> productList;
    OnProductListener onProductListener;

    CustomAdapter(Context context, ArrayList productList,OnProductListener onProductListener){

        this.context = context;
        this.productList = new ArrayList<Product>(productList);
        this.onProductListener = onProductListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_items_layout,parent,false);

        return new MyViewHolder(view,onProductListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.proName.setText(String.valueOf(productList.get(position).getProName()));
        holder.proPrice.setText(String.valueOf(productList.get(position).getProPrice()));
        byte[] pImage = productList.get(position).getProImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(pImage, 0, pImage.length);
        holder.proImage.setImageBitmap(bitmap);


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView proName, proPrice;
        ImageView proImage;
        OnProductListener onProductListener;

        public MyViewHolder(@NonNull View itemView,OnProductListener onProductListener) {
            super(itemView);
            proName = itemView.findViewById(R.id.product_name);
            proPrice = itemView.findViewById(R.id.product_price);
            proImage = itemView.findViewById(R.id.product_image);
            this.onProductListener = onProductListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            onProductListener.onProductClick(getAdapterPosition());

        }
    }


    public interface OnProductListener{

        void onProductClick(int position);

    }
}
