package com.rodrigolmti.anipix.view.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by rodrigolmti on 05/12/17.
 */

public class TextViewBold extends AppCompatTextView {

    public TextViewBold(final Context context) {
        super(context);
        init();
    }

    public TextViewBold(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewBold(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Bold.ttf"));
        }
    }
}