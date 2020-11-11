
package com.example.sweetcartapp.ShoppersRoom;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sweetcartapp.R;
import com.example.sweetcartapp.ShoppersRoom.FragmentsBaseActivity.Home;
import com.example.sweetcartapp.ShoppersRoom.HelperMethods.Users;

import java.util.ArrayList;
import java.util.List;

public class ProductOverview extends AppCompatActivity {

    TextView qtyamount, titleProduct;
    ImageView productPhoto;
    Button addToCart, placeOrder;
    private String TAG = getClass().getSimpleName();
    private GestureDetector gestureDetector;
    private View rel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_overview);

        initializeViews();
        setImageAndTitleUsingCallingActivity();
        setSpinner();

        performButtonClickOperation();
        productPhoto.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_DOWN) {
                    finish();
                    Log.i(TAG, "onTouch: DOWN -----DOWN");
                    return true;
                }
                return true;

            }
        });


    }



    private void performButtonClickOperation() {

        performAddtoCartOperation();

    }

    private void performAddtoCartOperation() {
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Home.mCartItemCount++;
                Toast.makeText(ProductOverview.this, "Item added to cart: " + value, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ProductOverview.this, ShoppersStop.class));


            }
        });
    }


    private void setImageAndTitleUsingCallingActivity() {
        String productTitle = getIntent().getStringExtra("Title");
        Integer imagetobeLoaded = getIntent().getIntExtra("ImageID", Integer.MAX_VALUE);
        loadImagesandSetTitle(productTitle, imagetobeLoaded);
    }

    private void initializeViews() {
        qtyamount = findViewById(R.id.qtyTextChange);
        titleProduct = findViewById(R.id.tile_in_overview);
        productPhoto = findViewById(R.id.detailed_activity_image);
        addToCart = findViewById(R.id.addToCartButton);
        rel = findViewById(R.id.myBaseOverview);
    }

    private void loadImagesandSetTitle(String productTitle, Integer imagetobeLoaded) {
        titleProduct.setText(productTitle);
        productPhoto.setImageResource(imagetobeLoaded);
    }

    private void setSpinner() {
        Users ob1 = new Users(1, "Select Quantity");
        Users ob2 = new Users(2, "100 g");
        Users ob3 = new Users(3, "150 g");
        Users ob4 = new Users(4, "250 g");
        Users ob5 = new Users(5, "500 g");
        Users ob6 = new Users(6, "1 kg");
        Users ob7 = new Users(7, "2 kg");
        Users ob8 = new Users(8, "5 kg");

        List<Users> users = new ArrayList<>();
        users.add(ob1);
        users.add(ob2);
        users.add(ob3);
        users.add(ob4);
        users.add(ob5);
        users.add(ob6);
        users.add(ob7);
        users.add(ob8);

        SetupSpinner(users);


    }

    private void SetupSpinner(List<Users> users) {
        Spinner spinner = findViewById(R.id.qtySpinner);
        ArrayAdapter userAdapter = new ArrayAdapter(this, R.layout.spinner, users);
        userAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(userAdapter);

        //Set item selected listener on spinner dropdown
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = "" + userAdapter.getItem(position);
                if (!s.equals("Select Quantity")) {
                    qtyamount.setText(s);
                    //TODO : Run tax calculation class here to maintain invoice for
                    // each value selected by the user
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}