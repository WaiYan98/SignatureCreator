package com.example.signaturepad.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.signaturepad.R;
import com.example.signaturepad.adapter.ColorsAdapter;
import com.example.signaturepad.util.MyColor;
import com.example.signaturepad.util.VerticalSpaceItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColorsDialogFragment extends DialogFragment {

    @BindView(R.id.recy_colors)
    RecyclerView recyColors;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.dialog_fragment_colors, null, false);
        ButterKnife.bind(this, view);

        Dialog dialog = new AlertDialog.Builder(getContext())
                .setView(view)
                .create();

        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.y = 800;
        window.setAttributes(params);
        window.setBackgroundDrawableResource(R.drawable.dialog_background);

        recyColors.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        recyColors.setAdapter(new ColorsAdapter(getContext(), MyColor.getColors()));
        recyColors.addItemDecoration(new VerticalSpaceItemDecoration(12));

        return dialog;
    }
}
