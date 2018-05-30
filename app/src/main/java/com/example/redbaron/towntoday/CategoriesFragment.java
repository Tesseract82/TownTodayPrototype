package com.example.redbaron.towntoday;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CategoriesFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public CategoriesFragment() {
        // Required empty public constructor
    }

    public static CategoriesFragment newInstance(String param1, String param2) {
        CategoriesFragment fragment = new CategoriesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_categories, container, false);

        DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
        Constants.dpHeight = displayMetrics.heightPixels / 3;
        Constants.dpWidth = displayMetrics.widthPixels / 3;

        Constants.selected.clear();
        LinearLayout mainLayout = (LinearLayout) v.findViewById(R.id.mainLayout);
        Log.i("HEIGHT: ", String.valueOf(Constants.dpHeight));
        int counter = 0;

        LinearLayout each = null;
        while(counter < Constants.categories.size()){
            if(counter % 3 == 0){
                if(each != null){
                    mainLayout.addView(each);
                }
                each = new LinearLayout(getActivity());
                each.setOrientation(LinearLayout.HORIZONTAL);
            }
            CategoryView cv = new CategoryView(getActivity(), Constants.categories.get(counter));
            cv.setPadding((int) Constants.dpPadding, (int) Constants.dpPadding,
                    (int) Constants.dpPadding, (int) Constants.dpPadding);
            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((CategoryView) v).checked = !((CategoryView) v).checked;
                    if(((CategoryView) v).checked){
                        Constants.selected.add(((CategoryView) v).identification);
                    } else {
                        Constants.selected.remove(((CategoryView) v).identification);
                    }
                    if(Constants.selected.isEmpty()){
                        ((ImageView) getActivity().findViewById(R.id.bar_center_img)).setImageResource(R.drawable.botcenterbar);
                    } else {
                        ((ImageView) getActivity().findViewById(R.id.bar_center_img)).setImageResource(R.drawable.botcenterbaryellow);
                    }
                    ((CategoryView) v).invalidate();
                }
            });
            each.addView(cv);
            counter++;
        }
        if(Constants.categories.size() % 3 != 0) {
            mainLayout.addView(each);
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
