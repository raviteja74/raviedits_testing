package development.ravi.picedit.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import development.ravi.picedit.R;
import development.ravi.picedit.adapters.ToolsAdapter;
import development.ravi.picedit.core.ImageEditorView;
import development.ravi.picedit.models.Tool;
import development.ravi.picedit.presentation.presenters.fragment.ToolsPresenter;
import development.ravi.picedit.presentation.views.fragment.ToolsView;
import development.ravi.picedit.ui.activities.EditorActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static development.ravi.picedit.core.enums.EditorTool.NONE;

public class ToolsFragment extends MvpAppCompatFragment implements ToolsView {
    @InjectPresenter
    ToolsPresenter mPresenter;

    @BindView(R.id.recycler_view_tools)
    RecyclerView mRecyclerView;

    private Unbinder mUnbinder;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tools, container, false);

        mUnbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ImageEditorView imageEditorView =
                (ImageEditorView) getActivity().findViewById(R.id.image_editor_view);
        imageEditorView.changeTool(NONE);

        Button undoButton = (Button) getActivity().findViewById(R.id.button_undo);

        if (undoButton.getText().equals("0")) {
            undoButton.setVisibility(View.GONE);
        } else {
            undoButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void setupTools(List<Tool> tools) {
        ToolsAdapter adapter = new ToolsAdapter(tools);
        adapter.setOnToolsClickListener(tool -> ( (EditorActivity)getActivity()).setupFragment(tool.getFragment()));

        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)
        );
        mRecyclerView.setAdapter(adapter);
    }
}