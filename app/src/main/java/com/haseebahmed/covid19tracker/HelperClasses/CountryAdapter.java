package com.haseebahmed.covid19tracker.HelperClasses;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.haseebahmed.covid19tracker.Fragments.CountriesFragment;
import com.haseebahmed.covid19tracker.Fragments.WorldStatFragment;
import com.haseebahmed.covid19tracker.Models.CountryData;
import com.haseebahmed.covid19tracker.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder>
{

    private Context context;
    private List<CountryData> list;


    public CountryAdapter(Context context, List<CountryData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.country_item,parent,false);
       return  new CountryViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {


        CountryData data= list.get(position);
        holder.countryCases.setText(WorldStatFragment.prettyCount(Integer.parseInt(data.getCases())));
        holder.countryName.setText(data.getCountry()+"");
        holder.countryRecovered.setText(WorldStatFragment.prettyCount(Integer.parseInt(data.getRecovered())));
        holder.countryDeaths.setText(WorldStatFragment.prettyCount(Integer.parseInt(data.getDeaths())));

        Map<String,String> img= data.getCountryInfo();
        Glide.with(context).load(img.get("flag")).into(holder.flag);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder {

        private TextView countryName,countryCases,countryRecovered,countryDeaths;
        private ImageView flag;
        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);

            countryName= itemView.findViewById(R.id.countryName);
            countryCases=itemView.findViewById(R.id.confirmTextViewId);
            countryRecovered=itemView.findViewById(R.id.recoveredTextViewId);
            countryDeaths=itemView.findViewById(R.id.deathTextViewId);
            flag= itemView.findViewById(R.id.flagImageView);
        }
    }

    public void fiterList(List<CountryData> filterList){
        list= filterList;
        notifyDataSetChanged();
    }


}
