package wlauncher.gvs.com.wlauncher.desktop;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import wlauncher.gvs.com.wlauncher.state;

/**
 * Created by dns1 on 05.09.2016.
 */
public class DesktopViewPager extends ViewPager {

    private Context context;
    private List<View> pages = new ArrayList<View>();

    public DesktopViewPager(Context context) {
        super(context);
        this.context = context;
    }

    public DesktopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        if (attrs!=null)
        {
            for (int i = 0; i < attrs.getAttributeIntValue(null, "desktopCount", 1); i++)
                addDesktop();
        }
    }

    public void addDesktop()
    {
        state.CountDesktopPage++;
        GridCell newDesktop = new GridCell(context);
        newDesktop.setId(state.CountDesktopPage);
        newDesktop.setHorizontalValue(4);
        newDesktop.setVerticalValue(6);
        pages.add(newDesktop);
        DesktopAdapter pagerAdapter = new DesktopAdapter(pages);
        setAdapter(pagerAdapter);

    }

}
