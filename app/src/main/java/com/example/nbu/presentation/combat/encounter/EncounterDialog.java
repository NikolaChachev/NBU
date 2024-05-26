package com.example.nbu.presentation.combat.encounter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.nbu.R;

public class EncounterDialog extends DialogFragment {

    private static final String BUNDLE_MESSAGE = "message";

    public static EncounterDialog newInstance(final @NonNull String message) {
        final EncounterDialog dialog = new EncounterDialog();
        final Bundle b = new Bundle();
        b.putString(BUNDLE_MESSAGE, message);
        dialog.setArguments(b);
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final String title = "";
        final String message = getArguments() != null ? getArguments().getString(BUNDLE_MESSAGE) : "Error getting message";

        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.encounter_dialog_continue_button, (dialogInterface, i) -> {
                    //continue adventure
                })
                .setNegativeButton(R.string.encounter_dialog_return_to_town_button, (dialogInterface, i) -> {
                    //return to Town
                })
                .create();
    }
}
