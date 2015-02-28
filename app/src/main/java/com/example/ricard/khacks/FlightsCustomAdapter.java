package com.example.ricard.khacks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class FlightsCustomAdapter extends ArrayAdapter {
    private List<FlightsObject> mFlights;
    private Context mContext;
    private int mResource;

    public FlightsCustomAdapter(Context context, List<FlightsObject> data) {
        super(context, R.layout.custom_flights, data);
        mContext = context;
        mFlights = data;
        mResource = R.layout.custom_flights;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater =
                (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(mResource, parent, false);

        TextView name =  (TextView) rowView.findViewById(R.id.name);
        TextView prize =  (TextView) rowView.findViewById(R.id.prize);
        TextView date =  (TextView) rowView.findViewById(R.id.date);
        TextView company =  (TextView) rowView.findViewById(R.id.company);

        FlightsObject flights = mFlights.get(position);
        name.setText(flights.getName());
        prize.setText(flights.getPrize());
        date.setText(flights.getDate());
        company.setText(flights.getCompany());

        return rowView;
    }
}
