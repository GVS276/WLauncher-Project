package wlauncher.gvs.com.wlauncher;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by dns1 on 04.09.2016.
 */
public class state {
    public static String STATE_DRAG_MENUTODESKTOP = "IconToDesktop";
    public static String STATE_DRAG_DESKTOPTOCELL = "IconToCell";

    public static String DragState;
    public static Drawable DragIcon;
    public static String DragPkg;
    public static String DragCls;
    public static String DragTitle;
    public static int DragCellID;

    public static int CountIndexCell = -1;
    public static int CountDesktopPage = -1;

    public static AppCompatActivity launcher = null;
}
