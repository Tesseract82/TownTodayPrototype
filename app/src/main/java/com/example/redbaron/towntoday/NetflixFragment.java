package com.example.redbaron.towntoday;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NetflixFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public NetflixFragment() {
        // Required empty public constructor
    }
    public static NetflixFragment newInstance(String param1, String param2) {
        NetflixFragment fragment = new NetflixFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_netflix, container, false);

        LinearLayout mainLinear = v.findViewById(R.id.netflixMainLinear);

        //Start the following in a separate thread
        Constants.initializeEventThumbs(getActivity());

        int testAdder = 0;

        for(String key : Constants.eventThumbs.keySet()){
            if(!Constants.selected.contains(key)){
                continue;
            }
            TextView categoryTitle = new TextView(getActivity());
            categoryTitle.setTextAppearance(android.R.style.TextAppearance_Holo);
            categoryTitle.setText(key.substring(0, 1).toUpperCase() + key.substring(1).toLowerCase());
            categoryTitle.setTextSize(25);
            categoryTitle.setTextColor(Color.rgb(237, 197, 37));
            mainLinear.addView(categoryTitle);

            LinearLayout linear2 = new LinearLayout(getActivity());
            linear2.setOrientation(LinearLayout.HORIZONTAL);

            for(EventThumbView etv : Constants.eventThumbs.get(key)){
                etv.setPadding(2 * (int) Constants.dpPadding, 2 * (int) Constants.dpPadding,
                        2 * (int) Constants.dpPadding, 2 * (int) Constants.dpPadding);
                etv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        EventFragment efv = new EventFragment();
                        ft.replace(R.id.fragment_area, efv);
                        ft.commit();
                        getActivity().findViewById(R.id.container).setBackgroundColor(getResources().getColor(android.R.color.background_light));
                    }
                });
                linear2.addView(etv);
            }
            HorizontalScrollView sv = new HorizontalScrollView(getActivity());
            sv.addView(linear2);
            mainLinear.addView(sv);
        }

        return v;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
