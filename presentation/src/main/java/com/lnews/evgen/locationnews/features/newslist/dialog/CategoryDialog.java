package com.lnews.evgen.locationnews.features.newslist.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;
import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.lnews.evgen.locationnews.R;
import com.lnews.evgen.locationnews.features.newslist.OnCategoryDialogResult;

public class CategoryDialog extends DialogFragment {

    private OnCategoryDialogResult dialogResult;

    @BindView(R.id.spinner_newslist_category)
    Spinner spinnerCategories;
    @BindArray(R.array.newslist_categories)
    String[] categories;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_category, null);
        ButterKnife.bind(this, view);

        builder.setView(view);
        return builder.create();
    }

    @OnClick(R.id.button_newslist_category_apply)
    public void applyCategoryListener() {
        if (dialogResult != null) {
            dialogResult.categorySelectEvent(categories[(int)spinnerCategories.getSelectedItemId()]);
        }
        dismiss();
    }

    @OnClick(R.id.button_newslist_category_cancel)
    public void cancelListener(){
        dismiss();
    }

    public void setDialogResult(OnCategoryDialogResult dialogResult) {
        this.dialogResult = dialogResult;
    }
}
