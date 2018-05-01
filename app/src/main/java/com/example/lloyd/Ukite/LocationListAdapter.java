package com.example.lloyd.Ukite;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by lloyd on 30-Apr-18.
 */

public class LocationListAdapter extends RecyclerView.Adapter<LocationListAdapter.LocationViewHolder>{
    private static final String TAG = LocationListAdapter.class.getSimpleName();

    private Context mContext;
    private ArrayList<LocationItem> mLocationList;
    private OnItemClickListener mListener;

    public LocationListAdapter(Context context, ArrayList<LocationItem> locationList) {
        mContext = context;
        mLocationList = locationList;
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.location_item, parent, false);
        return new LocationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(LocationViewHolder holder, int position) {
        LocationItem currentItem = mLocationList.get(position);
        Log.i(TAG, "onBindViewHolder: LocationItem");
        String imageUrl = currentItem.getImageUrl();
        String locationName = currentItem.getLocation();
        int speed = currentItem.getSpeed();

        holder.mTextViewLocation.setText(locationName);
        holder.mTextViewSpeed.setText("Windspeed: " + speed + "MPH");
        Picasso.with(mContext).load(imageUrl).resize(600,600).centerCrop().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mLocationList.size();
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextViewLocation;
        public TextView mTextViewSpeed;

        public LocationViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mTextViewLocation = itemView.findViewById(R.id.text_view_location);
            mTextViewSpeed = itemView.findViewById(R.id.text_view_speed);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.OnItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


}
