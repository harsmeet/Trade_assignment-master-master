package com.event.trading_assignment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 */
public abstract class BaseFragment extends Fragment {


    private View view;
    private TextView tvTitle;

    public MainActivity getMainActivity() {
        return ((MainActivity) super.getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(getLayout(), container, false);
        } else {
            if (view.getParent() != null) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
        }
        InputMethodManager imm = (InputMethodManager) getMainActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        ButterKnife.bind(this, view);
        /*fabToolbarListener=getMainActivity();
        fabToolbarListener.manageToolbarVisibility(isToolBarVisible());
        fabToolbarListener.manageFabVisibility(isFabVisible());
        *//*mainListener=getMainActivity();
        getMainActivity().showMenuButton(isMenuButtonShown());
        mainListener.manageTitleFab(getTitle(),isFabVisible());*/
        return view;
    }

    protected boolean isFabVisible() {
        return true;
    }

    /*protected String getTitle(){
        return "";
    }*/

    protected abstract @LayoutRes
    int getLayout();

    protected boolean isToolBarVisible() {
        return true;
    }

}

