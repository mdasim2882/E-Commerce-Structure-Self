package com.example.sweetcartapp.ShoppersRoom;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sweetcartapp.R;
import com.example.sweetcartapp.ShoppersRoom.RecyclerViewSetup.Adapters.CartItemsRecyclerViewAdapter;
import com.example.sweetcartapp.ShoppersRoom.RecyclerViewSetup.ProductGridItemDecoration;

public class OrderedItemsActivity extends AppCompatActivity {

    LocalBroadcastManager localBroadcastManager;
    Integer[] imageId = {
            R.drawable.rasgulla,
            R.drawable.gulabjamun,
            R.drawable.barfi,
            R.drawable.jalebi,
            R.drawable.samosa,
            R.drawable.lassi
    };
    String titleID[] = {"Rasgulla", "Gulab Jamun", "Barfi", "Jalebi", "Samosa", "Lassi"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered_items);

        //TODO: Set Recycler View with items loaded from databse
        setRecyclerView();
        setUpToolbar();
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
    }

    private void setRecyclerView() {
        // Set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view_cartitems);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false));
        /*
         * Pass parameter as list of type ProductEntry
         * Must be retrieved from database to here only
         * ProductEntry contains three fields:
         * ImageView productImage
         * TextView productName, productCost;
         * */
        CartItemsRecyclerViewAdapter adapter = new CartItemsRecyclerViewAdapter(this, imageId, titleID);
        recyclerView.setAdapter(adapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.updown_product_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.side_product_grid_spacing_small);
        recyclerView.addItemDecoration(new ProductGridItemDecoration(largePadding, smallPadding));

    }

    private void setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.cart_item_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}