package com.cas.veritasapp.core.helpers;

import android.view.MenuItem;

import androidx.annotation.IdRes;
import androidx.annotation.MenuRes;

/**
 * Created by funmiayinde on 2019-10-11.
 */
public class FragmentToolbarHelper {

    public static int NO_TOOLBAR = -1;

    @IdRes
    public int resId;

    public String title;

    @MenuRes
    public int menuId;

    public MenuItem.OnMenuItemClickListener onMenuItemClickListener;

    public FragmentToolbarHelper(int resId, int menuId, MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.resId = resId;
        this.menuId = menuId;
        this.onMenuItemClickListener = onMenuItemClickListener;
    }

    public static class Builder {
        private int resId = -1;
        private String title = null;
        private int menuId = -1;
        private MenuItem.OnMenuItemClickListener onMenuItemClickListener;

        public Builder withId(@IdRes int resId) {
            this.resId = resId;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withMenuId(int menuId) {
            this.menuId = menuId;
            return this;
        }

        public Builder setMenuItemListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
            this.onMenuItemClickListener = onMenuItemClickListener;
            return this;
        }

        public FragmentToolbarHelper build() {
            return new FragmentToolbarHelper(resId, menuId, onMenuItemClickListener);
        }
    }
}
