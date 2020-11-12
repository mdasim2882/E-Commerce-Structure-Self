package com.example.sweetcartapp.ShoppersRoom.FragmentsBaseActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sweetcartapp.R;
import com.example.sweetcartapp.ShoppersRoom.Commons.BroadCasterInfo;
import com.example.sweetcartapp.ShoppersRoom.OrderedItemsActivity;
import com.example.sweetcartapp.ShoppersRoom.RecyclerViewSetup.Adapters.ProductCardRecyclerViewAdapter;
import com.example.sweetcartapp.ShoppersRoom.RecyclerViewSetup.ProductGridItemDecoration;
import com.example.sweetcartapp.ShoppersRoom.Settings;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {
    public static final String ITEM_ADDED_TO_CART = "Item added to cart";
    LocalBroadcastManager localBMFragments;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView textCartItemCount;
    public static int mCartItemCount = 0;
    Handler h = new Handler();

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            setupBadge();
            final AlertDialog dialog = alertDialog.create();
            dialog.show();
            //Dismiss dialog after 3 seconds
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    if (dialog.isShowing())
                        dialog.dismiss();
                }
            };
            h.postDelayed(runnable, 3000);
        }
    };
    private AlertDialog.Builder alertDialog;

    public Home() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    Integer[] imageId = {
            R.drawable.rasgulla,
            R.drawable.gulabjamun,
            R.drawable.barfi,
            R.drawable.jalebi,
            R.drawable.samosa,
            R.drawable.lassi
    };
    String titleID[] = {"Rasgulla", "Gulab Jamun", "Barfi", "Jalebi", "Samosa", "Lassi"};
    String priceID[] = {"Rs 21", "Rs 34", "Rs 16", "Rs 24", "Rs 7", "Rs 14"};


    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);
        localBMFragments = LocalBroadcastManager.getInstance(getActivity());
        localBMFragments.registerReceiver(broadcastReceiver, new IntentFilter(BroadCasterInfo.CART_BADGE));
        alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setCancelable(true);
        alertDialog.setTitle("Item added to cart");
        alertDialog.setIcon(getResources().getDrawable(R.drawable.ic_outline_local_offer_24));
        alertDialog.setMessage("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_shoppers_stop, container, false);
        // Inflate the layout for this fragment
        setUpToolbar(view);
        setRecyclerView(view);
        return view;
    }

    private void setRecyclerView(View view) {

        // Set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        /*
         * Pass parameter as list of type ProductEntry
         * Must be retrieved from database to here only
         * ProductEntry contains three fields:
         * ImageView productImage
         * TextView productName, productCost;
         * */
        ProductCardRecyclerViewAdapter adapter = new ProductCardRecyclerViewAdapter(getActivity(), imageId, titleID, priceID);
        recyclerView.setAdapter(adapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.updown_product_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.side_product_grid_spacing_small);
        recyclerView.addItemDecoration(new ProductGridItemDecoration(largePadding, smallPadding));
    }

    private void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
            activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.shopper_toolbar_menu, menu);
        setMenuOperationForBadge(menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    private void setMenuOperationForBadge(Menu menu) {
        final MenuItem menuItem = menu.findItem(R.id.action_cart);
        View actionView = menuItem.getActionView();
        textCartItemCount = actionView.findViewById(R.id.cart_badge);


        //TODO: Call setupBadge() function for every newly added item to cart
        setupBadge();

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });
    }

    public void setupBadge() {
        //TODO: Increase text on cart by 1 for each click on add item to cart
        Log.d("Home", "setupBadge: called with count: " + mCartItemCount);
        if (mCartItemCount == 0) {
            if (textCartItemCount.getVisibility() != View.GONE) {
                textCartItemCount.setVisibility(View.GONE);
            }
        } else {

            textCartItemCount.setText(String.valueOf(Math.min(mCartItemCount, 99)));
            if (textCartItemCount.getVisibility() != View.VISIBLE) {
                textCartItemCount.setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.settingiconclick) {
            Intent i = new Intent(getActivity(), Settings.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.action_cart) {
            // TODO: Do something with cart fragment here
            Intent i = new Intent(getActivity(), OrderedItemsActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        localBMFragments.unregisterReceiver(broadcastReceiver);

    }
}