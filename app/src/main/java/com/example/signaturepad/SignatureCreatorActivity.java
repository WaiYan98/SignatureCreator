package com.example.signaturepad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.signaturepad.adapter.ColorsAdapter;
import com.example.signaturepad.dialogfragment.Alert;
import com.example.signaturepad.dialogfragment.ColorsDialogFragment;
import com.example.signaturepad.dialogfragment.PenSizeDialogFragment;
import com.github.gcacace.signaturepad.views.SignaturePad;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignatureCreatorActivity extends AppCompatActivity implements Alert.CallBack, ColorsAdapter.CallBack, PenSizeDialogFragment.CallBack {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.img_pen)
    ImageView imgPen;
    @BindView(R.id.img_color)
    ImageView imgColor;
    @BindView(R.id.img_background_color)
    ImageView imgBackgroundColor;
    @BindView(R.id.img_clear_pad)
    ImageView imgClearPad;
    @BindView(R.id.signature_pad)
    SignaturePad signaturePad;
    private ColorsDialogFragment colorsDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature_creator);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);


        imgPen.setOnClickListener(v -> {

            PenSizeDialogFragment penSizeDialogFragment = PenSizeDialogFragment.getNewInstance();
            penSizeDialogFragment.setCallBack(this);
            penSizeDialogFragment.show(getSupportFragmentManager(), "");

        });

        imgColor.setOnClickListener(v -> {

            colorsDialogFragment = ColorsDialogFragment.getNewInstance(ColorsAdapter.PEN_COLOR_BTN);
            colorsDialogFragment.setCallBack(this);
            colorsDialogFragment.show(getSupportFragmentManager(), "");

        });

        imgBackgroundColor.setOnClickListener(v -> {

            colorsDialogFragment = ColorsDialogFragment.getNewInstance(ColorsAdapter.BACKGROUND_COLOR_BTN);
            colorsDialogFragment.setCallBack(this);
            colorsDialogFragment.show(getSupportFragmentManager(), "");

        });

        imgClearPad.setOnClickListener(v -> {

            Alert alert = Alert.getNewInstance();
            alert.setCallBack(this);
            alert.show(getSupportFragmentManager(), "");

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_export) {
            Log.d("tag", "onOptionsItemSelected: export");
        }

        if (id == R.id.menu_info) {
            Log.d("tag", "onOptionsItemSelected: info");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onclickYes() {

        signaturePad.clear();
    }

    @Override
    public void onClickPenColorIcon(int color) {

        signaturePad.setPenColor(getResources().getColor(color));
    }

    @Override
    public void onSlideSeekBar(int penSize) {

        signaturePad.setMaxWidth(penSize);
    }

    @Override
    public void onClickBackgroundColorIcon(int color) {

        signaturePad.setBackgroundColor(getResources().getColor(color));

    }
}