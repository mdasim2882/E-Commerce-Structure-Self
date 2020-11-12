package com.example.sweetcartapp.ShoppersRoom.RecyclerViewSetup.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sweetcartapp.R;
import com.example.sweetcartapp.ShoppersRoom.HelperMethods.ProductEntry;
import com.example.sweetcartapp.ShoppersRoom.ProductOverview;
import com.example.sweetcartapp.ShoppersRoom.RecyclerViewSetup.Holders.CartItemsViewHolder;

import java.util.List;

public class CartItemsRecyclerViewAdapter extends RecyclerView.Adapter<CartItemsViewHolder> {


    public final String TAG = getClass().getSimpleName();
    Context context;
    private List<ProductEntry> productList;
    private Integer[] cardImages;
    String[] cardTitle;
    Activity activity;

    public CartItemsRecyclerViewAdapter(Context context, Integer[] imageList, String[] cardTitle) {
        this.cardImages = imageList;
        this.cardTitle = cardTitle;

        this.context = context;
        activity = (Activity) context;
    }

    @Override
    public CartItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_cards, parent, false);

        return new CartItemsViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemsViewHolder holder, int position) {
        holder.igcard.setImageResource(cardImages[position]);
        holder.igTitle.setText(cardTitle[position]);
    }


    private void goToProductdetailsActivity(View v, String title, Integer imageID) {
        Intent i = new Intent(v.getContext(), ProductOverview.class);
        i.putExtra("Title", title);
        i.putExtra("ImageID", imageID);
        v.getContext().startActivity(i);
        // NOTE: Remember the important feature of Activity typecasting in constructor of Adapter
        // in order to use overridePendingTransition() method
        activity.overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }


    @Override
    public int getItemCount() {
        return cardImages.length;
    }
}
