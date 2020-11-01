package com.example.sweetcartapp.ShoppersRoom.RecyclerViewSetup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sweetcartapp.R;
import com.example.sweetcartapp.ShoppersRoom.HelperMethods.ProductEntry;

import java.util.List;

public class ProductCardRecyclerViewAdapter extends RecyclerView.Adapter<ProductCardViewHolder> {

    Context context;
    private List<ProductEntry> productList;
    private Integer[] cardImages;
    String[] cardTitle;
    String[] cardSubtitle;

    public ProductCardRecyclerViewAdapter(Context context, Integer[] imageList, String[] cardTitle, String[] cardSubtitle) {
        this.cardImages = imageList;
        this.cardTitle = cardTitle;
        this.cardSubtitle = cardSubtitle;
        this.context = context;

    }

    @NonNull
    @Override
    public ProductCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);
        return new ProductCardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductCardViewHolder holder, int position) {
        // TODO: Put Recycler ViewHolder Cards binding code here in MDC-102
        holder.imgCard.setImageResource(cardImages[position]);
        holder.productTitle.setText(cardTitle[position]);
        holder.productPrice.setText(cardSubtitle[position]);
    }

    @Override
    public int getItemCount() {
        return cardImages.length;
    }
}
