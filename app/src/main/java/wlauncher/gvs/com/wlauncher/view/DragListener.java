package wlauncher.gvs.com.wlauncher.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import wlauncher.gvs.com.wlauncher.R;
import wlauncher.gvs.com.wlauncher.Utils;
import wlauncher.gvs.com.wlauncher.menu.IconView;
import wlauncher.gvs.com.wlauncher.state;

/**
 * Created by dns1 on 09.09.2016.
 */
public class DragListener implements View.OnDragListener {

    private int id;
    private Context context;

    public DragListener(Context context, int id)
    {
        this.id = id;
        this.context = context;
    }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        int action = dragEvent.getAction();
        if (id == R.id.deleter_info_and_pass)
        {
            if (action == DragEvent.ACTION_DROP) {
                IconView icon = (IconView) dragEvent.getLocalState();
                String pkg = Utils.getPersistedValue(1, icon.getTag().toString());

                if ((boolean) ((RelativeLayout) state.launcher.findViewById(id)).getTag()) {
                    Log.i("DeleterVisible", "set change pass");
                } else {
                    Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("package:" + pkg));
                    context.startActivity(intent);
                }
            }
        } else
        {
            if (action == DragEvent.ACTION_DRAG_STARTED) {
                view.setBackgroundResource(R.drawable.add);

                if (state.DragState.equals(state.STATE_DRAG_MENUTODESKTOP))
                    Utils.DeleterVisible(context, true, true);

                if (state.DragState.equals(state.STATE_DRAG_DESKTOPTOCELL))
                    Utils.DeleterVisible(context, true, false);
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
                Utils.DeleterVisible(context, false, false);
            }
        }
        return true;
    }

}
