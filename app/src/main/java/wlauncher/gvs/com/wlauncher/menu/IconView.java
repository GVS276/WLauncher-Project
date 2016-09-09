package wlauncher.gvs.com.wlauncher.menu;

import android.content.ClipData;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import wlauncher.gvs.com.wlauncher.R;
import wlauncher.gvs.com.wlauncher.Utils;
import wlauncher.gvs.com.wlauncher.secure.SecureConfig;
import wlauncher.gvs.com.wlauncher.state;

/**
 * Created by dns1 on 04.09.2016.
 */
public class IconView extends LinearLayout implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener {

    private Context context;
    private TextView mTitle;
    private ImageView mIcon;
    private boolean mAttrMenu = true;

    public void setMenuValue(boolean value)
    {
        mAttrMenu = value;
    }

    public boolean getMenuValue()
    {
        return mAttrMenu;
    }

    public void setTitle(String value)
    {
        mTitle.setText(value);
    }

    public void setIcon(Drawable value)
    {
        mIcon.setImageDrawable(value);
    }

    public void setTag()
    {
        mTitle.setTag(state.DragPkg + "|" + state.DragCls);
    }

    public IconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (attrs != null)
        {
            mAttrMenu = attrs.getAttributeBooleanValue(null, "menu", true);
        }
        this.context = context;
        setClickable(true);
        setLongClickable(true);
        setOnClickListener(this);
        setOnLongClickListener(this);
        setOnTouchListener(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTitle = (TextView) findViewById(R.id.title);
        mIcon = (ImageView) findViewById(R.id.icon);
    }

    @Override
    public void onClick(View view) {
        SecureConfig.SecureConfigStart();
        /*
        Utils.RunIntent(context,
                new ComponentName(
                        Utils.getPersistedValue(1, (String) mTitle.getTag()),
                        Utils.getPersistedValue(2, (String) mTitle.getTag())));
                        */
    }

    @Override
    public boolean onLongClick(View view) {
        mIcon.setAlpha(1f);

        if (mAttrMenu)
        {
            state.DragState = state.STATE_DRAG_MENUTODESKTOP;
            state.DragIcon = mIcon.getDrawable();
            state.DragTitle = mTitle.getText().toString();
            state.DragPkg = Utils.getPersistedValue(1, (String) mTitle.getTag());
            state.DragCls = Utils.getPersistedValue(2, (String) mTitle.getTag());

            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            view.startDrag(data, shadowBuilder, view, 0);

            Utils.closeDrawer();
        } else {
            state.DragState = state.STATE_DRAG_DESKTOPTOCELL;
            state.DragCellID = ((LinearLayout)getParent()).getId();
        }

        return false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            mIcon.setAlpha(0.6f);
        }
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            mIcon.setAlpha(1f);
        }
        if (motionEvent.getAction() == MotionEvent.ACTION_CANCEL) {
            mIcon.setAlpha(1f);
        }
        return false;
    }

}
