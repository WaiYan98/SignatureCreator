package com.example.signaturepad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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


            Bitmap bitmap = drawableToBitmap(imgExportResultImg.getDrawable());
            saveToInternalStorage(bitmap);
            goBack();
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

    public String saveToInternalStorage(Bitmap bitmap) {

        FileOutputStream fos = null;

        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File directory = contextWrapper.getDir("imgDir", Context.MODE_PRIVATE);

        File myPath = new File(directory, "signature.jpg");

        try {

            fos = new FileOutputStream(myPath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } finally {

            try {

                fos.close();

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    public Bitmap drawableToBitmap(Drawable drawable) {

        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap bitmap = bitmapDrawable.getBitmap();
        return bitmap;
    }

}