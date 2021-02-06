package com.example.signaturepad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_previous);

        toolbar.setNavigationOnClickListener(v -> {

            goBack();

        });
    }

    public void goBack() {
        Intent intent = new Intent(InfoActivity.this, SignatureCreatorActivity.class);
        startActivity(intent);
        finish();
    }
}