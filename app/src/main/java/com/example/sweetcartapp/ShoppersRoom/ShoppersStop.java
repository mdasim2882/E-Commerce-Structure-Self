package com.example.sweetcartapp.ShoppersRoom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;



import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.sweetcartapp.R;
import com.example.sweetcartapp.ShoppersRoom.RecyclerViewSetup.ProductCardRecyclerViewAdapter;
import com.example.sweetcartapp.ShoppersRoom.RecyclerViewSetup.ProductGridItemDecoration;

public class ShoppersStop extends AppCompatActivity {

    Integer[] imageId = {
            R.drawable.locationshop,
            R.drawable.loginpreview,
            R.drawable.orderspic,
            R.drawable.loginpreview,
            R.drawable.locationshop
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppers_stop);
        setUpToolbar();
        setRecyclerView();

    }

    private void setRecyclerView() {
        // Set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        /*
        * Pass parameter as list of type ProductEntry
        * Must be retrieved from database to here only
        * ProductEntry contains three fields:
        * ImageView productImage
        * TextView productName, productCost;
        * */
        ProductCardRecyclerViewAdapter adapter = new ProductCardRecyclerViewAdapter(this,imageId);
        recyclerView.setAdapter(adapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.updown_product_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.side_product_grid_spacing_small);
        recyclerView.addItemDecoration(new ProductGridItemDecoration(largePadding, smallPadding));

    }

    private void setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.app_bar);
       setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.shopper_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.settingiconclick)
        {
            Intent i=new Intent(this,Settings.class);
            startActivity(i);

        }
        return super.onOptionsItemSelected(item);
    }
}