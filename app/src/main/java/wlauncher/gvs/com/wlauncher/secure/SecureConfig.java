package wlauncher.gvs.com.wlauncher.secure;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Created by dns1 on 08.09.2016.
 */
public class SecureConfig {

    private static SecureDialog dialog;
    private static BroadcastReceiver init;
    private static Context mContext;

    public static String securePassword = "1234";
    public static boolean secureEnabled = true;
    public static ComponentName[] secureApps;

    public static void SecureConfigStart()
    {
        mContext.sendBroadcast(new Intent("StartSecurity"));
    }

    public static void SecureConfigInit(Context context)
    {
        mContext = context;

        dialog = new SecureDialog(mContext);
        dialog.setStartApplication(false);

        init = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                dialog.show();
            }
        };
        
        context.registerReceiver(init, new IntentFilter("StartSecurity"));
    }

    public static void SecureConfigDestroy()
    {
        mContext.unregisterReceiver(init);
    }
}
