package com.example.signaturepad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExportActivity extends AppCompatActivity {

    private Bitmap bitmap;
    private int color;

    public static final String EXTRA_BITMAP = "EXTRA_BITMAP";
    public static final String EXTRA_COLOR = "EXTRA_COLOR";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.img_export_result_img)
    ImageView imgExportResultImg;
    @BindView(R.id.btn_save)
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);

        ButterKnife.bind(this);


        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_previous);

        toolbar.setNavigationOnClickListener(v -> {

            goBack();

        });

        getBitmap();

        imgExportResultImg.setImageBitmap(bitmap);
        imgExportResultImg.setBackgroundColor(color);

        btnSave.setOnClickListener(v -> {

        });

    }

    public void goBack() {

        Intent intent = new Intent(ExportActivity.this, SignatureCreatorActivity.class);
        startActivity(intent);
        finish();
    }

    public void getBitmap() {

        Intent intent = getIntent();

        if (intent != null) {

            byte[] byteArray = intent.getByteArrayExtra(EXTRA_BITMAP);
            bitmap = byteArrayToBitmap(byteArray);
            color = intent.getIntExtra(EXTRA_COLOR, 0);
        }

    }

    public Bitmap byteArrayToBitmap(byte[] byteArray) {

        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }
}