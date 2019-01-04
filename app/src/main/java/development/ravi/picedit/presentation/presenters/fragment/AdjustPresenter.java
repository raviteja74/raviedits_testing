package development.ravi.picedit.presentation.presenters.fragment;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import development.ravi.picedit.App;
import development.ravi.picedit.models.Adjust;
import development.ravi.picedit.presentation.views.fragment.AdjustView;

import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;

@InjectViewState
public class AdjustPresenter extends MvpPresenter<AdjustView> {
    @Inject
    Lazy<List<Adjust>> mAdjusts;

    public AdjustPresenter() {
        App.getAppComponent().inject(this);
        getViewState().setupAdapter(mAdjusts.get());
    }

    public void changeAdjust(Adjust adjust) {
        getViewState().adjustChanged(adjust.getFragment());
    }
}