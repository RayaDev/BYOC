package com.rayadev.byoc.ui.main;

import android.content.ClipData;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rayadev.byoc.MainActivity;
import com.rayadev.byoc.R;
import com.rayadev.byoc.room.Converter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HomeSetRecyclerViewAdapter extends RecyclerView.Adapter<HomeSetRecyclerViewAdapter.ConverterBoxViewHolder> {

    private ArrayList<Converter> mConverterArrayList; //Holds the Converter data to be populated into the RecyclerView
    private LayoutInflater mLayoutInflater;
    private ConverterClickListener mClickListener;


    //Passes the data into the constructor the Adapter to use.
    public HomeSetRecyclerViewAdapter(Context context, ConverterClickListener converterClickListener) {
        mLayoutInflater = LayoutInflater.from(context);
        this.mClickListener = converterClickListener;

    }

    @NonNull
    @Override
    public ConverterBoxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mItemView = mLayoutInflater.inflate(R.layout.converter_box, parent, false);
//        setClickListener(mClickListener);
        return new ConverterBoxViewHolder(mItemView);
    }


    //This is where the magic happens. Pushes all the Converter data on the View.
    @Override
    public void onBindViewHolder(@NonNull ConverterBoxViewHolder holder, int position) {

        if(mConverterArrayList != null) {
            Converter mConverter = mConverterArrayList.get(position);

           //Set the views for the holder.
            holder.mConverterUnitA_Name.setText(mConverter.getConverterUnitA_Name());
            holder.mConverterUnitB_Name.setText(mConverter.getConverterUnitB_Name());
            //holder.mConverterImageView.setImageResource(mConverter.getConverterBoxImageID());

            //Pass the data from converter down to the holder.
            holder.unitAName = mConverter.getConverterUnitA_Name();
            holder.unitBName = mConverter.getConverterUnitB_Name();
            holder.unitAValue = mConverter.getConverterUnitA_Value();
            holder.unitBValue = mConverter.getConverterUnitB_Value();

            if (position%2 == 0) {
                holder.mConverterImageView.setImageResource(R.drawable.ic_baseline_weight);

            }
            else {
                holder.mConverterImageView.setImageResource(R.drawable.ic_baseline_distance_icon);
            }

        }
        else {
            Log.i("TAG", "ConverterArrayList error");
        }
    }

    void setConverterArrayList(ArrayList<Converter> converterList){
        mConverterArrayList = converterList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        //null check, size can't be null.
        if (mConverterArrayList != null)
            return mConverterArrayList.size();
        else return 0;
    }

    //Constructs the actually View from the Converter object in the ArrayList<Converter>
    public class ConverterBoxViewHolder extends RecyclerView.ViewHolder{

        private final TextView mConverterUnitA_Name;
        private final TextView mConverterUnitB_Name;
        private final ImageView mConverterImageView;
        //private int mConverterBoxImageID;


        //Converter Data stuff
        private String unitAName;
        private String unitBName;
        private double unitAValue;
        private double unitBValue;



        public ConverterBoxViewHolder(View itemView) {
            super(itemView);
            mConverterUnitA_Name = itemView.findViewById(R.id.converter_box_distance_unit_placeholder_1);
            mConverterUnitB_Name = itemView.findViewById(R.id.converter_box_distance_unit_placeholder_2);
            mConverterImageView = itemView.findViewById(R.id.converter_box_image_view);

            //Implements the interface onto the ViewHolder
            mConverterImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onItemClick(unitAName, unitBName, unitAValue, unitBValue);
                }
            });

        }

    }

    // parent fragment will implement this method to respond to click events
    public interface ConverterClickListener {
        //Passes all the Converter info to the fragment
        void onItemClick(String converterUnitA_Name, String converterUnitB_Name, double converterUnitA_Value, double convertUnitB_Value);
    }
}
