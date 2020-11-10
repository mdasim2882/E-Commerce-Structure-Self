package com.example.sweetcartapp.ShoppersRoom.RecyclerViewSetup;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sweetcartapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProductCardViewHolder extends RecyclerView.ViewHolder {

    CardView productCard;
    ImageView imgCard;
    public TextView productTitle;
    public TextView productPrice;
    FloatingActionButton addtoCart;

    public ProductCardViewHolder(@NonNull View itemView) {
        super(itemView);
        imgCard = itemView.findViewById(R.id.product_image);
        productTitle = itemView.findViewById(R.id.product_title);
        productPrice = itemView.findViewById(R.id.product_price);
        productCard = itemView.findViewById(R.id.cardofproducts);
        // addtoCart=itemView.findViewById(R.id.addtoCartButton);
        // TODO: Find and store views from itemView
    }


}
