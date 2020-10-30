package com.example.sweetcartapp.ViewPagerAdapter.FragmentsStartUp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.sweetcartapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FastDelivery#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FastDelivery extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    static FastDelivery instance;


    public FastDelivery() {
        // Required empty public constructor
    }

    public static FastDelivery getInstance() {

        if (instance == null)
            instance = new FastDelivery();
        return instance;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FastDelivery.
     */
    // TODO: Rename and change types and number of parameters
    public static FastDelivery newInstance(String param1, String param2) {
        FastDelivery fragment = new FastDelivery();
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
        return inflater.inflate(R.layout.fragment_fast_delivery, container, false);
    }
}