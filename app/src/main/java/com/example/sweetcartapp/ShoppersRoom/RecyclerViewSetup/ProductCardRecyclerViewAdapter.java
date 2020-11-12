package com.example.sweetcartapp.ShoppersRoom.RecyclerViewSetup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sweetcartapp.R;
import com.example.sweetcartapp.ShoppersRoom.HelperMethods.ProductEntry;
import com.example.sweetcartapp.ShoppersRoom.ProductOverview;

import java.util.List;

public class ProductCardRecyclerViewAdapter extends RecyclerView.Adapter<ProductCardViewHolder> {

    public final String TAG = getClass().getSimpleName();
    Context context;
    private List<ProductEntry> productList;
    private Integer[] cardImages;
    String[] cardTitle;
    String[] cardSubtitle;
    Activity activity;

    public ProductCardRecyclerViewAdapter(Context context, Integer[] imageList, String[] cardTitle, String[] cardSubtitle) {
        this.cardImages = imageList;
        this.cardTitle = cardTitle;
        this.cardSubtitle = cardSubtitle;
        this.context = context;
        activity = (Activity) context;
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
        holder.productCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Material Card clicked " + cardTitle[position] + " : " + context.getClass());

                //TODO: Perform card clicked working
                Context c = v.getContext();
                goToProductdetailsActivity(v, cardTitle[position], cardImages[position]);
            }
        });
 /*       holder.addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Item added to cart", Toast.LENGTH_SHORT).show();
            }
        });*/

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
