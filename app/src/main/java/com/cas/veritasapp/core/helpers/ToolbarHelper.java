package com.cas.veritasapp.core.helpers;

import android.view.Menu;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

/**
 * Created by funmiayinde on 2019-10-11.
 */
public class ToolbarHelper {

    Toolbar toolbar;
    private FragmentToolbarHelper fragmentToolbarHelper;
    private View container;

    public ToolbarHelper(FragmentToolbarHelper fragmentToolbarHelper, View container) {
        this.fragmentToolbarHelper = fragmentToolbarHelper;
        this.container = container;
    }

    public void buildToolbar() {
        if (fragmentToolbarHelper.resId != FragmentToolbarHelper.NO_TOOLBAR) {
            toolbar = container.findViewById(fragmentToolbarHelper.resId);
        }
        if (fragmentToolbarHelper.title != null) {
            toolbar.setTitle(fragmentToolbarHelper.title);
        }
        if (fragmentToolbarHelper.menuId != -1) {
            toolbar.inflateMenu(fragmentToolbarHelper.menuId);
        }
        if (fragmentToolbarHelper.menuId != -1 && fragmentToolbarHelper.onMenuItemClickListener != null) {
            Menu menu = toolbar.getMenu();
            for (int i = 0; i < menu.size(); i++) {
                menu.getItem(i).setOnMenuItemClickListener(fragmentToolbarHelper.onMenuItemClickListener);
            }
        }
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

}
