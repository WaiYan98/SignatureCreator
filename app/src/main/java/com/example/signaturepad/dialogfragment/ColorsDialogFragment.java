package com.example.signaturepad.dialogfragment;

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

    public static final String EXTRA_BTN_NAME = "EXTRA_BTN_NAME";

    @BindView(R.id.recy_colors)
    RecyclerView recyColors;
    private ColorsAdapter.CallBack callBack;
    private String btnName;

    private ColorsDialogFragment() {

    }

    public void setCallBack(ColorsAdapter.CallBack callBack) {
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.dialog_fragment_colors, null, false);
        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();

        if (bundle != null) {

            btnName = bundle.getString(EXTRA_BTN_NAME);
        }

        Dialog dialog = new AlertDialog.Builder(getContext())
                .setView(view)
                .create();

        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.y = 800;
        window.setAttributes(params);
        window.setBackgroundDrawableResource(R.drawable.dialog_background);

        ColorsAdapter colorsAdapter = new ColorsAdapter(getContext(), MyColor.getColors(), callBack);
        colorsAdapter.setBtnName(btnName);

        recyColors.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        recyColors.setAdapter(colorsAdapter);
        recyColors.addItemDecoration(new VerticalSpaceItemDecoration(12));

        return dialog;
    }

    public static ColorsDialogFragment getNewInstance(String btnName) {

        ColorsDialogFragment colorsDialogFragment = new ColorsDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_BTN_NAME, btnName);
        colorsDialogFragment.setArguments(bundle);
        return colorsDialogFragment;
    }
}
