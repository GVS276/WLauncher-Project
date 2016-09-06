package wlauncher.gvs.com.wlauncher.menu;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by dns1 on 03.09.2016.
 */
public class MenuLoader extends AsyncTaskLoader<ArrayList<IconInfo>> {

    private ArrayList<IconInfo> mApps;
    private PackageManager mPm;
    private PackageIntentReceiver mPackageObserver;

    public MenuLoader(Context context) {
        super(context);
        this.mPm = context.getPackageManager();
    }

    public static final Comparator<IconInfo> COMPARATOR = new Comparator<IconInfo>() {
        private final Collator sCollator = Collator.getInstance();
        @Override
        public int compare(IconInfo object1, IconInfo object2) {
            return sCollator.compare(object1.getLabel(), object2.getLabel());
        }
    };

    @Override
    public ArrayList<IconInfo> loadInBackground() {
        Intent main = new Intent(Intent.ACTION_MAIN, null);
        main.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> apps = mPm.queryIntentActivities(main, 0);
        if (apps == null) apps = new ArrayList<ResolveInfo>();

        Context context = getContext();
        ArrayList<IconInfo> items = new ArrayList<IconInfo>(apps.size());

        for (int i = 0; i < apps.size(); i++) {
            String pkg = apps.get(i).activityInfo.packageName;
            if (context.getPackageManager().getLaunchIntentForPackage(pkg) != null) {
                IconInfo app = new IconInfo(context, apps.get(i).activityInfo);
                app.setIcon(apps.get(i).activityInfo.loadIcon(context.getPackageManager()));
                items.add(app);
            }
        }

        Collections.sort(items, COMPARATOR);
        return items;
    }

    @Override
    public void deliverResult(ArrayList<IconInfo> apps) {
        if (isReset()) {
            if (apps != null) {
                onReleaseResources(apps);
            }
        }

        ArrayList<IconInfo> oldApps = apps;
        mApps = apps;

        if (isStarted()) {
            super.deliverResult(apps);
        }
        if (oldApps != null) {
            onReleaseResources(oldApps);
        }
    }

    @Override
    protected void onStartLoading() {
        if (mApps != null) {
            deliverResult(mApps);
        }
        if (mPackageObserver == null) {
            mPackageObserver = new PackageIntentReceiver(this);
        }

        if (takeContentChanged() || mApps == null ) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    public void onCanceled(ArrayList<IconInfo> apps) {
        super.onCanceled(apps);
        onReleaseResources(apps);
    }

    @Override
    protected void onReset() {
        onStopLoading();

        if (mApps != null) {
            onReleaseResources(mApps);
            mApps = null;
        }

        if (mPackageObserver != null) {
            getContext().unregisterReceiver(mPackageObserver);
            mPackageObserver = null;
        }
    }

    protected void onReleaseResources(ArrayList<IconInfo> apps) {
    }
}
