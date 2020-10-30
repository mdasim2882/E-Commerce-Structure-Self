package com.example.sweetcartapp.ViewPagerAdapter.FragmentsStartUp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.sweetcartapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SweetShop#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SweetShop extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

 static SweetShop instance;

    public SweetShop() {
        // Required empty public constructor
    }
    public static SweetShop getInstance() {

        if (instance == null)
            instance = new SweetShop();
        return instance;
    }


    public static SweetShop newInstance(String param1, String param2) {
        SweetShop fragment = new SweetShop();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sweet_shop, container, false);
    }
}