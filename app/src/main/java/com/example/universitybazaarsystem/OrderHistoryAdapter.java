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

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderViewHolder> {

    private Context context;
    private ArrayList<Orders> orderList;

    public OrderHistoryAdapter(Context context, ArrayList<Orders> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.order_history_element,parent,false);

        return new OrderViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {

        holder.nameOfProduct.setText(orderList.get(position).getProductName());
        holder.priceOfProduct.setText(orderList.get(position).getProductPrice());
        holder.sellerName.setText("Seller: "+orderList.get(position).getSellerName());

        byte[] pImage = orderList.get(position).getProductImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(pImage, 0, pImage.length);
        holder.imagePro.setImageBitmap(bitmap);


    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }


    public class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView nameOfProduct, priceOfProduct, sellerName;
        ImageView imagePro;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            nameOfProduct = itemView.findViewById(R.id.nameProduct);
            sellerName = itemView.findViewById(R.id.seller);
            priceOfProduct = itemView.findViewById(R.id.priceProduct);
            imagePro = itemView.findViewById(R.id.imageProduct);

        }
    }
}
