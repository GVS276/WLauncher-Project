package wlauncher.gvs.com.wlauncher.menu;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;

/**
 * Created by dns1 on 03.09.2016.
 */
public class IconInfo {

    private final Context mContext;
    private final ActivityInfo mInfo;
    private Drawable mIcon;

    public IconInfo(Context context, ActivityInfo info) {
        mContext = context;
        mInfo = info;
    }

    public ActivityInfo getAppInfo() {
        return mInfo;
    }

    public String getApplicationPackageName() {
        return getAppInfo().packageName;
    }

    public String getApplicationClassName() {
        return getAppInfo().name;
    }

    public String getLabel() {
        return getAppInfo().loadLabel(mContext.getPackageManager()).toString();
    }

    public void setIcon(Drawable icon) {
        mIcon = icon;
    }

    public Drawable getIcon() {
        return mIcon;
    }

}
