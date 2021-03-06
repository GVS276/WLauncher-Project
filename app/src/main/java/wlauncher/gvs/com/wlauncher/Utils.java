package wlauncher.gvs.com.wlauncher;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.GravityCompat;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import wlauncher.gvs.com.wlauncher.desktop.DesktopViewPager;
import wlauncher.gvs.com.wlauncher.menu.IconView;
import wlauncher.gvs.com.wlauncher.view.DragListener;
import wlauncher.gvs.com.wlauncher.view.NewDrawerLayout;

/**
 * Created by dns1 on 04.09.2016.
 */
public class Utils {

    public static String getPersistedValue(int value, String str) {
        String[] values = str.split("\\|");
        if (value == 1) {
            try {
                return values[0];
            } catch (NumberFormatException e) {
                return null;
            }
        } else {
            try {
                return values[1];
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }

    public static void RunIntent(Context context, ComponentName s) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setComponent(s);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        context.startActivity(intent);
    }

    public static boolean closeDrawer()
    {
        NewDrawerLayout drawer = (NewDrawerLayout) state.launcher.findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
        return false;
    }

    public static ViewGroup createIcon(Context context)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.icons, null);

        IconView icon = (IconView) view.findViewById(R.id.IconView);
        icon.setMenuValue(false);
        icon.setTitle(state.DragTitle);
        icon.setIcon(state.DragIcon);
        icon.setTag();

        return icon;
    }

    public static void DeleterVisible(final Context c, boolean value, final boolean pass)
    {
        int i;
        if (value) { i = View.VISIBLE; } else { i = View.GONE; }

        RelativeLayout deleter = (RelativeLayout) state.launcher.findViewById(R.id.deleter);
        deleter.setVisibility(i);

        TextView info_or_pass = (TextView) state.launcher.findViewById(R.id.deleter_info_and_pass);
        if (pass) {
            info_or_pass.setText(c.getResources().getString(R.string.deleter_text_password));
        } else {
            info_or_pass.setText(c.getResources().getString(R.string.deleter_text_info));
        }

        info_or_pass.setTag(pass);
        info_or_pass.setOnDragListener(new DragListener(c, info_or_pass.getId()));
    }

    public static void addNewDesktop()
    {
        ((DesktopViewPager) state.launcher.findViewById(R.id.desktop)).addDesktop();
    }
}
