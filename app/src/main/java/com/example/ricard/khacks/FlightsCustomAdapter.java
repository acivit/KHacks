package com.example.ricard.khacks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class FlightsCustomAdapter extends ArrayAdapter {
    private List<Flight> mFlights;
    private Context mContext;
    private int mResource;

    public FlightsCustomAdapter(Context context, List<Flight> data) {
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

        TextView travel = (TextView) rowView.findViewById(R.id.travel);
        TextView name =  (TextView) rowView.findViewById(R.id.name);
        TextView prize =  (TextView) rowView.findViewById(R.id.prize);
        TextView depdate =  (TextView) rowView.findViewById(R.id.depdate);
        TextView arrdate =  (TextView) rowView.findViewById(R.id.arrdate);
        TextView company =  (TextView) rowView.findViewById(R.id.company);

        Flight flights = mFlights.get(position);
        travel.setText(flights.getDepLoc());
        prize.setText(flights.getPrice());
        depdate.setText(flights.getDepDate());
        arrdate.setText(flights.getArrDate());
        company.setText(flights.getCompany());

        return rowView;
    }
}
