package com.example.signaturepad.dialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.signaturepad.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Alert extends DialogFragment {

    @BindView(R.id.btn_yes)
    Button btnYes;
    @BindView(R.id.btn_no)
    Button btn_no;

    private CallBack callBack;

    private Alert() {

    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.dialog_fragment_alert, null, false);
        ButterKnife.bind(this, view);

        Dialog dialog = new AlertDialog.Builder(getContext())
                .setView(view)
                .create();

        btnYes.setOnClickListener(v -> {

            dialog.cancel();
            callBack.onclickYes();
        });

        btn_no.setOnClickListener(v -> {

            dialog.cancel();

        });

        return dialog;
    }

    public static Alert getNewInstance() {
        Alert alert = new Alert();
        return alert;
    }

    public interface CallBack {

        void onclickYes();
    }

}
