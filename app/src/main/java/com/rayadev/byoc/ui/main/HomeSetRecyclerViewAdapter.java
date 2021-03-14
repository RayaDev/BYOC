package com.rayadev.byoc.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rayadev.byoc.R;
import com.rayadev.byoc.room.Converter;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HomeSetRecyclerViewAdapter extends RecyclerView.Adapter<HomeSetRecyclerViewAdapter.ConverterBoxViewHolder> {

    private ArrayList<Converter> mConverterArrayList; //Holds the Converter data to be populated into the RecyclerView
    private LayoutInflater mLayoutInflater;
    private ItemClickListener mClickListener;

    //Passes the data into the constructor the Adapter to use.
    public HomeSetRecyclerViewAdapter(Context context, ArrayList<Converter> arrayList) {
        this.mConverterArrayList = arrayList;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ConverterBoxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mItemView = mLayoutInflater.inflate(R.layout.converter_box, parent, false);
        return new ConverterBoxViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ConverterBoxViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    //Constructs the actually View from the Converter object in the ArrayList<Converter>
    public class ConverterBoxViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final TextView mConverterUnitA_Name;
        private final TextView mConverterUnitB_Name;
        private final ImageView mConverterImageView;

        //this data does'nt need to be passed into this ViewHolder. Not where dealing with this happens.
//        private int mConverterBoxImageID;
//        private int mConverterRatioAB;
//        private int mConverterRatioBA;

        private final HomeSetRecyclerViewAdapter mHomeSetRecyclerViewAdapter; //Not sure if i need this or not.

        public ConverterBoxViewHolder(View itemView, HomeSetRecyclerViewAdapter adapter) {
            super(itemView);
            mConverterUnitA_Name = itemView.findViewById(R.id.converter_box_distance_unit_placeholder_1);
            mConverterUnitB_Name = itemView.findViewById(R.id.converter_box_distance_unit_placeholder_2);
            mConverterImageView = itemView.findViewById(R.id.converter_box_image_view);
            this.mHomeSetRecyclerViewAdapter = adapter;

        }


        //Method to needed to trigger the start of passing the converter data into the main activity.
        @Override
        public void onClick(View v) {

        }

    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
