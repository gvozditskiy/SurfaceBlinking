package com.gvozditskiy.surfaceblinking;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    MySurfaceView mySurfaceView;


    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mySurfaceView = (MySurfaceView) view.findViewById(R.id.surface_view);
        mySurfaceView.setVisibility(View.GONE);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_fragment_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.vis:
                if (mySurfaceView.getVisibility()==View.VISIBLE) {
                    item.setIcon(getResources().getDrawable(R.drawable.ic_action_show));
                    mySurfaceView.setVisibility(View.GONE);
                } else {
                    item.setIcon(getResources().getDrawable(R.drawable.ic_action_hide));
                    mySurfaceView.setVisibility(View.VISIBLE);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
