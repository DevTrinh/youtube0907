package com.example.youtubeapp.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.youtubeapp.MainActivity;
import com.example.youtubeapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class FragmentBtSheetUser extends BottomSheetDialogFragment {

    private ConstraintLayout clContains;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_user, null);
        mapping(view);
        clContains.getLayoutParams().height = getScreenHeight();
        bottomSheetDialog.setContentView(view);
        return bottomSheetDialog;
    }

    public void mapping(@NonNull View view){
        clContains = view.findViewById(R.id.cl_contains_dialog_user);
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}
