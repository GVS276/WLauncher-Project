package wlauncher.gvs.com.wlauncher.view;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.View;

import wlauncher.gvs.com.wlauncher.R;

/**
 * Created by dns1 on 04.09.2016.
 */
public class NewDrawerLayout extends DrawerLayout {

    public NewDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setScrimColor(context.getResources().getColor(R.color.drawer_scrim_color));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        // полный width
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(widthSize, heightSize);

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            LayoutParams lp = (LayoutParams) child.getLayoutParams();

            int contentWidthSpec = MeasureSpec.makeMeasureSpec(
                    widthSize - lp.leftMargin - lp.rightMargin, MeasureSpec.EXACTLY);

            int contentHeightSpec = MeasureSpec.makeMeasureSpec(
                    heightSize - lp.topMargin - lp.bottomMargin, MeasureSpec.EXACTLY);

            child.measure(contentWidthSpec, contentHeightSpec);
        }
    }
}
