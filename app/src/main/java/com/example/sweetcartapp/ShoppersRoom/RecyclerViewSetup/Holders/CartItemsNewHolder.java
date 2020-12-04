package com.example.sweetcartapp.ShoppersRoom.RecyclerViewSetup.Holders;


import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sweetcartapp.R;

public class CartItemsNewHolder extends RecyclerView.ViewHolder {
    public TextView igprice;
    public ImageView igcard;
    public TextView igTitle, priceforX_items, priceTextN_items, totalAmountForSameN_items;
    public CardView detailsCard, priceCard;
    public Button deleteCartItem, saveForLater;

    public CartItemsNewHolder(@NonNull View itemView) {
        super(itemView);
        UpperCard(itemView);
        LowerCard(itemView);
        // TODO: Find and store views from itemView
    }

    private void LowerCard(@NonNull View itemView) {
        priceCard = itemView.findViewById(R.id.priceCard);
        priceforX_items = itemView.findViewById(R.id.priceXitems);
        priceTextN_items = itemView.findViewById(R.id.textpriceN);
        totalAmountForSameN_items = itemView.findViewById(R.id.totalPriceNItems);
    }

    private void UpperCard(@NonNull View itemView) {
        igcard = itemView.findViewById(R.id.myDish);
        igTitle = itemView.findViewById(R.id.titleProduct);
        igprice = itemView.findViewById(R.id.priceItem);
        detailsCard = itemView.findViewById(R.id.detailsCard);
        deleteCartItem = itemView.findViewById(R.id.remove);
        saveForLater = itemView.findViewById(R.id.remove);
    }
}
