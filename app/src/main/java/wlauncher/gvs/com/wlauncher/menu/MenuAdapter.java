package wlauncher.gvs.com.wlauncher.menu;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;

import wlauncher.gvs.com.wlauncher.R;

/**
 * Created by dns1 on 03.09.2016.
 */
public class MenuAdapter extends ArrayAdapter<IconInfo> {

    private LayoutInflater mInflater;
    private Context context;

    public MenuAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_2);
        this.context = context;
        mInflater = LayoutInflater.from(this.context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view;

        if (convertView == null) {
            view = mInflater.inflate(R.layout.icons, parent, false);
        } else {
            view = convertView;
        }

        IconInfo item = getItem(position);
        ((ImageView)view.findViewById(R.id.icon)).setImageDrawable(item.getIcon());
        ((TextView)view.findViewById(R.id.title)).setText(item.getLabel());
        ((TextView)view.findViewById(R.id.title)).setTag(item.getApplicationPackageName()+"|"+item.getApplicationClassName());

        return view;
    }

    public void setData(ArrayList<IconInfo> data) {
        clear();
        if (data != null) {
            addAll(data);
        }
    }

    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void addAll(Collection<? extends IconInfo> items) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            super.addAll(items);
        }else{
            for(IconInfo item: items){
                super.add(item);
            }
        }
    }

}
