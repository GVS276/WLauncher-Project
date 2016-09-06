package wlauncher.gvs.com.wlauncher.desktop;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import wlauncher.gvs.com.wlauncher.R;
import wlauncher.gvs.com.wlauncher.Utils;
import wlauncher.gvs.com.wlauncher.state;

/**
 * Created by dns1 on 03.09.2016.
 */
public class GridCell extends RelativeLayout {

    private Context context = null;
    private LinearLayout cells = null;
    private int left = 0;
    private int top = 0;
    private int topShow = 0;
    private int mAttrV = 6;
    private int mAttrH = 4;
    private int mAttrBackground = 0;
    private int cell_local_x = 0;
    private int cell_local_y = 0;

    public void setVerticalValue(int value)
    {
        mAttrV = value;
    }

    public void setHorizontalValue(int value)
    {
        mAttrH = value;
    }

    public int getVerticalValue()
    {
        return mAttrV;
    }

    public int getHorizontalValue()
    {
        return mAttrH;
    }

    public GridCell(Context context) {
        super(context);
        this.context = context;
        updateGridCell();
    }

    public GridCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        if (attrs != null) {
            mAttrV = attrs.getAttributeIntValue(null, "verticalValue", 6); // Y
            mAttrH = attrs.getAttributeIntValue(null, "horizontalValue", 4); // X
            mAttrBackground = attrs.getAttributeResourceValue(null, "backgroundCell", 0);
            updateGridCell();
        }
    }

    private void oneCell()
    {
        state.CountIndexCell++;
        cells = new LinearLayout(context);
        cells.setOrientation(LinearLayout.VERTICAL);
        cells.setGravity(Gravity.CENTER_HORIZONTAL);
        cells.setId(state.CountIndexCell);
        cells.setOnDragListener(new OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                int action = dragEvent.getAction();
                if (action == DragEvent.ACTION_DRAG_STARTED) {
                    view.setBackgroundResource(R.drawable.add);
                    Utils.DeleterVisible(true);
                }
                if (action == DragEvent.ACTION_DROP) {
                    if (state.DragState.equals(state.STATE_DRAG_MENUTODESKTOP)) {
                        ((ViewGroup) view).addView(Utils.createIcon(context));
                    }
                    if (state.DragState.equals(state.STATE_DRAG_DESKTOPTOCELL)) {

                    }
                }
                if (action == DragEvent.ACTION_DRAG_ENDED) {
                    view.setBackgroundColor(Color.TRANSPARENT);
                    Utils.DeleterVisible(false);
                }
                return true;
            }
        });
        cells.destroyDrawingCache();
    }

    private void updateCells(int width, int height)
    {
        oneCell();
        if (cells != null)
        {
            left++;
            cell_local_x = mAttrH;
            cell_local_y = mAttrV;
            if (mAttrBackground != 0) cells.setBackgroundResource(mAttrBackground);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
            if (mAttrH == 4) {
                if (left == 1) {
                    params.leftMargin = 0;
                    if (topShow == 1) {
                        top = top + height;
                    }
                }
                if (left == 2) {
                    params.leftMargin = width;
                }
                if (left == 3) {
                    params.leftMargin = width * 2;
                }
                if (left == 4) {
                    params.leftMargin = width * 3;
                    left = 0;
                    topShow = 1;
                }
            }
            if (mAttrH == 5) {
                if (left == 1) {
                    params.leftMargin = 0;
                    if (topShow == 1) {
                        top = top + height;
                    }
                }
                if (left == 2) {
                    params.leftMargin = width;
                }
                if (left == 3) {
                    params.leftMargin = width * 2;
                }
                if (left == 4) {
                    params.leftMargin = width * 3;
                }
                if (left == 5) {
                    params.leftMargin = width * 4;
                    left = 0;
                    topShow = 1;
                }
            }
            if (mAttrH == 6) {
                if (left == 1) {
                    params.leftMargin = 0;
                    if (topShow == 1) {
                        top = top + height;
                    }
                }
                if (left == 2) {
                    params.leftMargin = width;
                }
                if (left == 3) {
                    params.leftMargin = width * 2;
                }
                if (left == 4) {
                    params.leftMargin = width * 3;
                }
                if (left == 5) {
                    params.leftMargin = width * 4;
                }
                if (left == 6) {
                    params.leftMargin = width * 5;
                    left = 0;
                    topShow = 1;
                }
            }
            if (mAttrH == 7) {
                if (left == 1) {
                    params.leftMargin = 0;
                    if (topShow == 1) {
                        top = top + height;
                    }
                }
                if (left == 2) {
                    params.leftMargin = width;
                }
                if (left == 3) {
                    params.leftMargin = width * 2;
                }
                if (left == 4) {
                    params.leftMargin = width * 3;
                }
                if (left == 5) {
                    params.leftMargin = width * 4;
                }
                if (left == 6) {
                    params.leftMargin = width * 5;
                }
                if (left == 7) {
                    params.leftMargin = width * 6;
                    left = 0;
                    topShow = 1;
                }
            }

            params.topMargin = top;
            cells.setLayoutParams(params);
            cells.destroyDrawingCache();
            addView(cells);
        }
    }

    public void updateGridCell()
    {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int p1 = mAttrH;
                int p2 = mAttrV;
                if ((cell_local_x != p1) || (cell_local_y != p2))
                {
                    top = 0;
                    left = 0;
                    topShow = 0;
                    removeAllViews();
                    int width = Math.round(getMeasuredWidth() / mAttrH);
                    int height = Math.round(getMeasuredHeight() / mAttrV);
                    int g = mAttrH;
                    int g2 = mAttrV;
                    for (int i = 0; i < g*g2; i++) {
                        updateCells(width, height);
                    }
                }
            }
        });
        requestLayout();
        invalidate();
    }
}
