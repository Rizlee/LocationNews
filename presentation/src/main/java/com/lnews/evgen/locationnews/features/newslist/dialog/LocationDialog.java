package com.lnews.evgen.locationnews.features.newslist.dialog;

import android.annotation.SuppressLint;
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
import com.lnews.evgen.locationnews.features.newslist.OnLocationDialogResult;
import java.util.Objects;

public class LocationDialog extends DialogFragment {

    private OnLocationDialogResult dialogResult;

    @BindView(R.id.spinner_newslist_location)
    Spinner spinnerCountries;
    @BindArray(R.array.newslist_countries_code)
    String[] countriesCode;
    @BindArray(R.array.newslist_countries)
    String[] countries;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();

        @SuppressLint("InflateParams")
        View view = inflater.inflate(R.layout.dialog_location, null);
        ButterKnife.bind(this, view);

        builder.setView(view);
        return builder.create();
    }

    @OnClick(R.id.button_newslist_location_apply)
    public void applyLocationListener() {
        if (dialogResult != null) {
            int selectedItemId = (int) spinnerCountries.getSelectedItemId();
            dialogResult.countrySelectEvent(countries[selectedItemId], countriesCode[selectedItemId]);
        }
        dismiss();
    }

    @OnClick(R.id.button_newslist_current_location_apply)
    public void applyCurrentLocationListener() {
        if (dialogResult != null) {
            dialogResult.currentLocationEvent();
        }
        dismiss();
    }

    @OnClick(R.id.button_newslist_location_cancel)
    public void cancelListener(){
        dismiss();
    }

    public void setDialogResult(OnLocationDialogResult dialogResult) {
        this.dialogResult = dialogResult;
    }
}
