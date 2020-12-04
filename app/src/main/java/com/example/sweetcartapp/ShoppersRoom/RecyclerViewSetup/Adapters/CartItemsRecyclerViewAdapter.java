package com.example.sweetcartapp.ShoppersRoom.RecyclerViewSetup.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sweetcartapp.R;
import com.example.sweetcartapp.ShoppersRoom.Commons.BroadCasterInfo;
import com.example.sweetcartapp.ShoppersRoom.Commons.CartItemsAndImagesList;
import com.example.sweetcartapp.ShoppersRoom.FragmentsBaseActivity.Home;
import com.example.sweetcartapp.ShoppersRoom.HelperMethods.ProductEntry;
import com.example.sweetcartapp.ShoppersRoom.RecyclerViewSetup.Holders.CartItemsNewHolder;

import java.util.LinkedList;
import java.util.List;

public class CartItemsRecyclerViewAdapter extends RecyclerView.Adapter<CartItemsNewHolder> {


    public final String TAG = getClass().getSimpleName();
    Context context;
    private List<ProductEntry> productList;
    private List<Integer> cardImages = new LinkedList<>();
    private List<Double> cardPrices = new LinkedList<>();
    List<String> cardTitle = new LinkedList<>();
    Activity activity;

    LocalBroadcastManager localBroadcastManager;

    public CartItemsRecyclerViewAdapter(Context context, List<Integer> imageList, List<Double> cardPrices, List<String> cardTitle) {
        this.cardImages = imageList;
        this.cardTitle = cardTitle;
        this.cardPrices = cardPrices;
        this.context = context;
        activity = (Activity) context;
        localBroadcastManager = LocalBroadcastManager.getInstance(activity);
    }

    @Override
    public CartItemsNewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_full_cards, parent, false);

        return new CartItemsNewHolder(layoutView);
    }

    private void startMyItemAddedBroadCast() {
        Intent intent = new Intent(BroadCasterInfo.CART_BADGE);
        localBroadcastManager.sendBroadcast(intent);
    }

    private void startMyButtonReplacementBroadCast() {
        Intent intent = new Intent(BroadCasterInfo.PLACE_ORDER_BUTTON);
        localBroadcastManager.sendBroadcast(intent);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemsNewHolder holder, int position) {
        holder.igcard.setImageResource(cardImages.get(position));
        holder.igTitle.setText(cardTitle.get(position));
        holder.igprice.setText("Rs " + cardPrices.get(position));
        holder.totalAmountForSameN_items.setText("Rs " + cardPrices.get(position));
        holder.deleteCartItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Home.mCartItemCount > 0) {
                    int value = --Home.mCartItemCount;
                    try {
                        Log.d("BadgeCOUNT", "onClick: REMOVEValueInCart: " + value);
                        BroadCasterInfo.CART_TYPE = true;
                        CartItemsAndImagesList remover = new CartItemsAndImagesList();
                        remover.deletetitleId(cardTitle.get(position));
                        remover.deleteImageId(cardImages.get(position));
                        remover.deletePriceId(cardPrices.get(position));
                        //holder.cd.setVisibility(View.GONE);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, cardImages.size());

            /*BroadCast Listener: Requirement FOR SENDER
             * Create field in activity that wants to send BroadCast
             *  LocalBroadcastManager localBroadcastManager;
             * localBroadcastManager = LocalBroadcastManager.getInstance(this);
             *  Create a function to send BroadCast() defined using
             * Intents having common variable in receiver and sender;
             * For e.g.,
             *  private void startMyItemAddedBroadCast() {
                             Intent intent = new Intent(BroadCasterInfo.CART_BADGE);
                            localBroadcastManager.sendBroadcast(intent);
                            }
             *
             * Requirement: FOR RECEIVER
             *      LocalBroadcastManager localBMFragments;
             *
             * Set listener action for text change or any other purpose
             *   private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                         setupBadge();
                    }
                };
             *
             * In onCreate(){
              localBMFragments = LocalBroadcastManager.getInstance(getActivity());
    localBMFragments.registerReceiver(broadcastReceiver, new IntentFilter(BroadCasterInfo.CART_BADGE));
             }
             *
             * Override an onDestroyMethod() to unregister Receiver
             *  @Override
                    public void onDestroy() {
                        super.onDestroy();
                        localBMFragments.unregisterReceiver(broadcastReceiver);

                    }
             *
             * *
             * */
                        startMyButtonReplacementBroadCast();
                        startMyItemAddedBroadCast();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
//                if(Home.mCartItemCount==0)
//                    startMyButtonReplacementBroadCast();
            }
        });
    }


    @Override
    public int getItemCount() {
        return cardImages.size();
    }
}
