package com.codepath.apps.bzfire;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.bzfire.models.Contractor;

import java.util.ArrayList;

/**
 * Created by lee on 8/26/16.
 */
public class ContractorArrayAdapter extends ArrayAdapter<Contractor> {

    public ContractorArrayAdapter(Context context, ArrayList<Contractor> contractors) {
        super(context, R.layout.item, contractors);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contractor contractor = getItem(position);
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item, parent, false);
        }

        TextView tvBusinessName = (TextView) convertView.findViewById(R.id.tvBusinessName);
        tvBusinessName.setText(contractor.getBusinessName());
        TextView tvScore = (TextView) convertView.findViewById(R.id.tvScore);
        tvScore.setText(Integer.toString(contractor.getScore()));

        ImageView ivProfile = (ImageView) convertView.findViewById(R.id.ivProfile);
        ivProfile.setImageResource(0);
        Glide.with(getContext()).load("http:" + contractor.getThumbnailUrl()).into(ivProfile);

        return convertView;
    }
}
