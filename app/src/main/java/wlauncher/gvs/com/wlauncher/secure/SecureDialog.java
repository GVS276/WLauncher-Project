package wlauncher.gvs.com.wlauncher.secure;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import wlauncher.gvs.com.wlauncher.R;

/**
 * Created by dns1 on 08.09.2016.
 */
public class SecureDialog {

    private String TAG = "SecureDialog";
    private Context context;
    private Dialog dialog;
    private boolean isStartApplication;
    private ComponentName appInfo;

    public boolean isShow;

    public SecureDialog(Context context)
    {
        if (SecureConfig.secureEnabled)
        {
            this.context = context;
            dialog = new Dialog(context);
            dialog.setContentView(R.layout.secure);
            dialog.setTitle(context.getResources().getString(R.string.secure_title));
            WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
            wmlp.width = context.getResources().getDisplayMetrics().widthPixels;
            wmlp.gravity = Gravity.LEFT | Gravity.RIGHT;
        }
    }

    public void setStartApplication(boolean value)
    {
        // value = false : All applications
        if (SecureConfig.secureEnabled) this.isStartApplication = value;
    }

    public void setApplication(ComponentName appInfo)
    {
        if (SecureConfig.secureEnabled) this.appInfo = appInfo;
    }

    public void show()
    {
        if (SecureConfig.secureEnabled)
        {
            this.isShow = true;
            dialog.show();
        }
    }

    public void hide()
    {
        if (SecureConfig.secureEnabled)
        {
            this.isShow = false;
            dialog.hide();
        }
    }

    public boolean isGood()
    {
        if (SecureConfig.secureEnabled)
        {
            EditText passwordView = (EditText) dialog.findViewById(R.id.passwordView);
            if (SecureConfig.securePassword.equals(passwordView.getText().toString())) return true;
        }
        return false;
    }
}
