package com.example.sweetcartapp.ShoppersRoom.RecyclerViewSetup.Holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sweetcartapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CartItemsViewHolder extends RecyclerView.ViewHolder {
    public ImageView igcard;
    public TextView igTitle;
    public TextView productPrice;
    public CardView cd;
    FloatingActionButton addtoCart;

    public CartItemsViewHolder(@NonNull View itemView) {
        super(itemView);
        igcard = itemView.findViewById(R.id.product);
        igTitle = itemView.findViewById(R.id.title);
        cd = itemView.findViewById(R.id.productcard);
        // TODO: Find and store views from itemView
    }
}
