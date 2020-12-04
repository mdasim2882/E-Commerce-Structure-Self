package com.example.sweetcartapp.ShoppersRoom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sweetcartapp.R;
import com.example.sweetcartapp.ShoppersRoom.Commons.BroadCasterInfo;
import com.example.sweetcartapp.ShoppersRoom.Commons.CartItemsAndImagesList;
import com.example.sweetcartapp.ShoppersRoom.FragmentsBaseActivity.Home;
import com.example.sweetcartapp.ShoppersRoom.RecyclerViewSetup.Adapters.CartItemsRecyclerViewAdapter;
import com.example.sweetcartapp.ShoppersRoom.RecyclerViewSetup.ProductGridItemDecoration;

public class OrderedItemsActivity extends AppCompatActivity {
    RelativeLayout rlnoOrder;
    LocalBroadcastManager localBroadcastManager;
    Integer[] imageId = {
            R.drawable.rasgulla,
            R.drawable.gulabjamun,
            R.drawable.barfi,
            R.drawable.jalebi,
            R.drawable.samosa,
            R.drawable.lassi
    };
    Integer[] priceIDs = {
            21, 34, 16, 24, 7, 14};

    Button placeOrderBtn;
    String titleID[] = {"Rasgulla", "Gulab Jamun", "Barfi", "Jalebi", "Samosa", "Lassi"};

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("Order Items", "onReceive: Yes perform this");
            setupPlaceOrderButtonVisibility();
            setUpRelativeLayoutVisibility();
        }
    };

    private void setUpRelativeLayoutVisibility() {
        if (Home.mCartItemCount >= 1) {
            rlnoOrder.setVisibility(View.GONE);
        } else
            rlnoOrder.setVisibility(View.VISIBLE);

    }

    private void setupPlaceOrderButtonVisibility() {
        if (Home.mCartItemCount >= 1) {
            placeOrderBtn.setVisibility(View.VISIBLE);
        } else
            placeOrderBtn.setVisibility(View.GONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered_items);
        CartItemsAndImagesList add = new CartItemsAndImagesList();
//        for(String t:titleID) {
//         add.addtitleId(t);
//        }
//        for(Integer a: imageId)
//            add.addImageId(a);


        initializeViews();
        //TODO: Set Recycler View with items loaded from databse
        setRecyclerView();
        setUpToolbar();
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.registerReceiver(broadcastReceiver, new IntentFilter(BroadCasterInfo.PLACE_ORDER_BUTTON));
    }

    private void initializeViews() {
        placeOrderBtn = findViewById(R.id.placeOrderButton);
        rlnoOrder = findViewById(R.id.noitemlayout);
        placeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrderedItemsActivity.this, "Orders Placed Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        setupPlaceOrderButtonVisibility();
        setUpRelativeLayoutVisibility();
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
        CartItemsRecyclerViewAdapter adapter = new CartItemsRecyclerViewAdapter(this, CartItemsAndImagesList.imageId, CartItemsAndImagesList.priceID, CartItemsAndImagesList.titleID);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(broadcastReceiver);

    }
}