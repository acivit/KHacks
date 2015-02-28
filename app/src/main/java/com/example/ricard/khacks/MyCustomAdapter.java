package com.example.ricard.khacks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by pau on 28/02/15.
 */
public class MyCustomAdapter extends ArrayAdapter {
    private List<Hackathon> mHackathons;
    private Context mContext;
    private int mResource;

    public MyCustomAdapter(Context context, List<Hackathon> data) {
        super(context, R.layout.custom_item, data);
        mContext = context;
        mHackathons = data;
        mResource = R.layout.custom_item;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater =
                (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(mResource, parent, false);

        TextView name =  (TextView) rowView.findViewById(R.id.name);
        TextView location =  (TextView) rowView.findViewById(R.id.location);
        TextView date =  (TextView) rowView.findViewById(R.id.date);
        ImageView img = (ImageView) rowView.findViewById(R.id.image);

        Hackathon hackathon = mHackathons.get(position);
        name.setText(hackathon.getName());
        location.setText(hackathon.getLocation());
        date.setText(hackathon.getDate());
        img.setImageBitmap(hackathon.getImage());

        return rowView;
    }
}