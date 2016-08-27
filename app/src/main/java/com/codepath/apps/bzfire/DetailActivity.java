package com.codepath.apps.bzfire;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.bzfire.models.Contractor;

import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setupToolbar();

        Contractor contractor = (Contractor) Parcels.unwrap(getIntent().getParcelableExtra("contractor"));
        Log.e("details", contractor.getBusinessName());

        TextView tvAbout = (TextView) findViewById(R.id.tvAbout);
        tvAbout.setText(contractor.getAbout());
        ImageView ivProfile = (ImageView) findViewById(R.id.ivProfile);
        ivProfile.setImageResource(0);
        Glide.with(this).load(contractor.getThumbnailUrl()).into(ivProfile);
    }

    public void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
