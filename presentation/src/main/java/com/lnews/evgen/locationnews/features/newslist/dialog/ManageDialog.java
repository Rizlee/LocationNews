package com.lnews.evgen.locationnews.features.newslist.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.lnews.evgen.locationnews.R;
import com.lnews.evgen.locationnews.features.newslist.OnManageDialogResult;
import java.util.Objects;

public class ManageDialog extends DialogFragment {

    private static final String TABS_TAG = "tabs";

    private OnManageDialogResult dialogResult;

    @BindView(R.id.spinner_newslist_manage)
    Spinner spinnerTabs;

    public static ManageDialog newInstance(String[] tabs) {
        ManageDialog fragment = new ManageDialog();

        Bundle args = new Bundle();
        args.putStringArray(TABS_TAG, tabs);
        fragment.setArguments(args);

        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder =
            new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_manage_tabs, null);
        ButterKnife.bind(this, view);

        setupSpinner();

        builder.setView(view);
        return builder.create();
    }

    @OnClick(R.id.button_newslist_manage_apply)
    public void applyLocationListener() {
        if (dialogResult != null) {
            dialogResult.deleteTabEvent((int) spinnerTabs.getSelectedItemId());
        }
        dismiss();
    }

    @OnClick(R.id.button_newslist_manage_cancel)
    public void cancelListener() {
        dismiss();
    }

    private void setupSpinner() {
        if (getArguments() != null) {
            if (getArguments().containsKey(TABS_TAG)) {
                ArrayAdapter<String> spinnerArrayAdapter =
                    new ArrayAdapter<String>(Objects.requireNonNull(getContext()),
                        R.layout.spinner_item,
                        Objects.requireNonNull(getArguments().getStringArray(TABS_TAG)));

                spinnerTabs.setAdapter(spinnerArrayAdapter);
            }
        }
    }

    public void setDialogResult(OnManageDialogResult dialogResult) {
        this.dialogResult = dialogResult;
    }
}
