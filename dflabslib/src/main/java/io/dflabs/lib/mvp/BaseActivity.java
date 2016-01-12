package io.dflabs.lib.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.InflateException;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Daniel García Alvarado on 10/10/15.
 * Crédito Real - danielgarcia
 */
@SuppressWarnings("unused")
public abstract class BaseActivity extends AppCompatActivity {

    Toolbar mToolbar;
    private BasePresenter mPresenter;
    private boolean toolbarEnabled;
    private boolean homeAsUpEnabled;

    protected void setToolbarEnabled(boolean enabled) {
        this.toolbarEnabled = enabled;
    }

    protected void setHomeAsUpEnabled(boolean enabled) {
        this.homeAsUpEnabled = enabled;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbarEnabled = true;
        homeAsUpEnabled = true;
        mPresenter = getPresenter();
        if (mPresenter != null) mPresenter.onCreate();
    }

    protected abstract BasePresenter getPresenter();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        if (toolbarEnabled) {
            View toolbar = findToolbar(findViewById(android.R.id.content));
            if (toolbar != null) {
                mToolbar = (Toolbar) toolbar;
                setupToolbar();
            } else {
                throw new InflateException("You must add a Toolbar on the Activity or " +
                        "setToolbarEnabled(false) before setContentView()");
            }
        }
    }

    private View findToolbar(View view) {
        if (view instanceof Toolbar) return view;
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View child = ((ViewGroup) view).getChildAt(i);
                View possibleToolbar = findToolbar(child);
                if (possibleToolbar != null && possibleToolbar instanceof Toolbar)
                    return possibleToolbar;
            }
        }
        return null;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mPresenter != null) mPresenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPresenter != null) mPresenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPresenter != null) mPresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.onDestroy();
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null && homeAsUpEnabled)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected <T extends BasePresenter> void setupPresenter(T basePresenter) {
        this.mPresenter = basePresenter;
    }
}
