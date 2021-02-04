package com.example.signaturepad.dialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.signaturepad.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PenSizeDialogFragment extends DialogFragment {

    @BindView(R.id.seek_bar_pen_size)
    SeekBar seekBarPenSize;
    @BindView(R.id.txt_pen_size)
    TextView txtPenSize;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.dialog_fragment_pen_size, null, false);

        ButterKnife.bind(this, view);

        Dialog dialog = new AlertDialog.Builder(getContext())
                .setView(view)
                .create();

        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_background);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.y = 800;
        window.setAttributes(params);

        seekBarPenSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                txtPenSize.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return dialog;
    }
}
