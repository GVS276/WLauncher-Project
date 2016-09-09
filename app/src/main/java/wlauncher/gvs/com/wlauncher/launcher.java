package wlauncher.gvs.com.wlauncher;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import java.util.ArrayList;

import wlauncher.gvs.com.wlauncher.menu.IconInfo;
import wlauncher.gvs.com.wlauncher.menu.MenuAdapter;
import wlauncher.gvs.com.wlauncher.menu.MenuLoader;
import wlauncher.gvs.com.wlauncher.secure.SecureConfig;

public class launcher extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<IconInfo>>{

    private GridView mGridView;
    private MenuAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        SecureConfig.SecureConfigInit(this);

        mGridView = (GridView)findViewById(R.id.applications);
        mAdapter = new MenuAdapter(this);
        mGridView.setAdapter(mAdapter);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onStart() {
        super.onStart();
        state.launcher = this;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SecureConfig.SecureConfigDestroy();
    }

    @Override
    public void onBackPressed() {
        Utils.closeDrawer();
    }

    @Override
    public Loader<ArrayList<IconInfo>> onCreateLoader(int i, Bundle bundle) {
        return new MenuLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<IconInfo>> loader, ArrayList<IconInfo> iconInfos) {
        mAdapter.setData(iconInfos);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<IconInfo>> loader) {
        mAdapter.setData(null);
    }
}
