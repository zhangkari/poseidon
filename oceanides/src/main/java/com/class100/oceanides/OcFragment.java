package com.class100.oceanides;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.class100.atropos.env.context.AtViewPieces;
import com.class100.atropos.generic.AtTracker;

public class OcFragment extends Fragment {
    protected AtViewPieces viewPiece;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle bundle) {
        return inflater.inflate(R.layout.oc_fragment, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        viewPiece = new AtViewPieces(view);
        incubate();
        init();
        AtTracker.track(getPageName(), "onCreate");
    }

    @Override
    public void onResume() {
        super.onResume();
        AtTracker.track(getPageName(), "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        AtTracker.track(getPageName(), "onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AtTracker.track(getPageName(), "onDestroy");
    }

    public void showProgress() {
        viewPiece.setVisibility(R.id.oc_layout_progress, View.VISIBLE);
    }

    public void hideProgress() {
        viewPiece.setVisibility(R.id.oc_layout_progress, View.GONE);
    }

    private void incubate() {
        int titleId = getTitleLayout();
        if (titleId != 0) {
            viewPiece.attach(R.id.oc_layout_title, titleId);
        }

        int contentId = getContentLayout();
        if (contentId != 0) {
            viewPiece.attach(R.id.oc_layout_content, contentId);
        }

        int progressId = getProgressLayout();
        if (progressId != 0) {
            viewPiece.attach(R.id.oc_layout_progress, progressId);
        }
    }

    protected void init() {

    }

    protected int getTitleLayout() {
        return R.layout.oc_layout_title;
    }

    protected int getContentLayout() {
        return 0;
    }

    protected int getProgressLayout() {
        return R.layout.oc_layout_progress;
    }

    protected String getPageName() {
        return getClass().getSimpleName();
    }
}
