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
import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.lnews.evgen.locationnews.R;
import com.lnews.evgen.locationnews.features.newslist.OnCategoryDialogResult;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CategoryDialog extends DialogFragment {
    private static final String TABS_TAG = "categories";
    private OnCategoryDialogResult dialogResult;
    private List<String> categoriesExisting;
    private List<String> categoriesAvailable;

    @BindView(R.id.spinner_newslist_category)
    Spinner spinnerCategories;
    @BindArray(R.array.newslist_categories)
    String[] categoriesAll;

    public static CategoryDialog newInstance(ArrayList<String> categories) {
        CategoryDialog fragment = new CategoryDialog();

        Bundle args = new Bundle();
        args.putStringArrayList(TABS_TAG, categories);
        fragment.setArguments(args);

        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder =
            new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_category, null);
        ButterKnife.bind(this, view);

        initCategories();

        setupSpinner();

        builder.setView(view);
        return builder.create();
    }

    private void initCategories() {
        assert getArguments() != null;
        categoriesExisting = getArguments().getStringArrayList(TABS_TAG);

        categoriesAvailable = Arrays.asList(categoriesAll);

        assert categoriesExisting != null;
        for (int i = 0; i < categoriesExisting.size(); i++) {
            categoriesAvailable.remove(categoriesExisting.get(i));
        }
    }

    private void setupSpinner() {
        ArrayAdapter<String> spinnerArrayAdapter =
            new ArrayAdapter<>(Objects.requireNonNull(getContext()),
                R.layout.spinner_item, categoriesExisting);

        spinnerCategories.setAdapter(spinnerArrayAdapter);
    }

    @OnClick(R.id.button_newslist_category_apply)
    public void applyCategoryListener() {
        if (dialogResult != null) {
            dialogResult.categorySelectEvent(
                categoriesExisting.get((int) spinnerCategories.getSelectedItemId()));
        }
        dismiss();
    }

    @OnClick(R.id.button_newslist_category_cancel)
    public void cancelListener() {
        dismiss();
    }

    public void setDialogResult(OnCategoryDialogResult dialogResult) {
        this.dialogResult = dialogResult;
    }
}
