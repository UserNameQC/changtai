package com.changtai.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.changtai.R;

/**
 * Created by qjcjo on 2018/3/13.
 */

public class FragmentMine extends Fragment {

    private View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.index_my, null);
        return mView;
    }
}
