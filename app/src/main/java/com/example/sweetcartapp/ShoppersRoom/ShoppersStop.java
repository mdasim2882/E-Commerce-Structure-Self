package com.example.sweetcartapp.ShoppersRoom;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sweetcartapp.R;
import com.example.sweetcartapp.ShoppersRoom.FragmentsBaseActivity.Favourites;
import com.example.sweetcartapp.ShoppersRoom.FragmentsBaseActivity.History;
import com.example.sweetcartapp.ShoppersRoom.FragmentsBaseActivity.Home;
import com.example.sweetcartapp.ShoppersRoom.FragmentsBaseActivity.Offers;
import com.example.sweetcartapp.ShoppersRoom.RecyclerViewSetup.Adapters.ProductCardRecyclerViewAdapter;
import com.example.sweetcartapp.ShoppersRoom.RecyclerViewSetup.ProductGridItemDecoration;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ShoppersStop extends AppCompatActivity {

    Integer[] imageId = {
            R.drawable.rasgulla,
            R.drawable.gulabjamun,
            R.drawable.barfi,
            R.drawable.jalebi,
            R.drawable.samosa,
            R.drawable.lassi
    };
    String titleID[] = {"Rasgulla", "Gulab Jamun", "Barfi", "Jalebi", "Samosa", "Lassi"};
    String priceID[] = {"Rs 21", "Rs 3", "Rs 16", "Rs 24", "Rs 7", "Rs 14"};
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_layout);
//        setUpToolbar();
//        setRecyclerView();
        setBottomNavigationMenu();
    }

    private void setBottomNavigationMenu() {
        bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            Fragment fragment = null;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.action_home:
                        fragment = new Home();
                        break;

                    case R.id.action_offers:
                        fragment = new Offers();
                        break;
                    case R.id.action_history:
                        fragment = new History();
                        break;
                    case R.id.action_favourites:
                        fragment = new Favourites();
                        break;

                }
                return loadFromFragment(fragment);

            }
        });
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }

    private boolean loadFromFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.my_container, fragment)
                    .commit();
            return true;
        }
        return false;
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
        ProductCardRecyclerViewAdapter adapter = new ProductCardRecyclerViewAdapter(this, imageId, titleID, priceID);
        recyclerView.setAdapter(adapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.updown_product_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.side_product_grid_spacing_small);
        recyclerView.addItemDecoration(new ProductGridItemDecoration(largePadding, smallPadding));

    }
/*
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
    }*/
}