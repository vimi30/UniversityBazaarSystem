package com.example.universitybazaarsystem;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterClubs extends RecyclerView.Adapter<CustomAdapterClubs.MyViewHolder> {

    private Context context;
    private ArrayList<Clubs> club_list;
    //CustomAdapter.OnProductListener onProductListener;
    OnProductListenerClubs onProductListener;

    CustomAdapterClubs(Context context, ArrayList club_list, OnProductListenerClubs onProductListener){

        this.context = context;
        this.club_list = new ArrayList<Clubs>(club_list);
        this.onProductListener = onProductListener;
    }



//    public CustomAdapterClubs(ClubsListingPage context, ArrayList<Clubs> club_list, ClubsListingPage clubsListingPage) {
//        this.context = context;
//        this.club_list = new ArrayList<Clubs>(club_list);
//        this.onProductListener = onProductListener;
//    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.clubs_items,parent,false);

        return new MyViewHolder(view,onProductListener);

    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterClubs.MyViewHolder holder, int position) {
        holder.clubName.setText(String.valueOf(club_list.get(position).getC_name()));
        //holder.clubDesc.setText(String.valueOf(club_list.get(position).getC_desc()));
        byte[] club_img = club_list.get(position).getClub_img();
        Bitmap bitmap = BitmapFactory.decodeByteArray(club_img, 0, club_img.length);
        holder.clubImage.setImageBitmap(bitmap);

    }

    @Override
    public int getItemCount() {
        return club_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView clubName;
        ImageView clubImage;
        //CustomAdapter.OnProductListener onProductListener;
        OnProductListenerClubs onProductListener;

        public MyViewHolder(@NonNull View itemView, OnProductListenerClubs onProductListener) {
            super(itemView);
            clubName = itemView.findViewById(R.id.club_name);
            clubImage = itemView.findViewById(R.id.club_image);
            this.onProductListener = onProductListener;

            itemView.setOnClickListener(this);

        }



        @Override
        public void onClick(View v) {

            onProductListener.onProductClick(getAdapterPosition());

        }
    }

    public interface OnProductListenerClubs{

        void onProductClick(int position);

    }

}
