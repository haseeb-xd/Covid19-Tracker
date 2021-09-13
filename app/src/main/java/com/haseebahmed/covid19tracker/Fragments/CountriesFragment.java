package com.haseebahmed.covid19tracker.Fragments;
import android.app.ProgressDialog;
import android.icu.text.CompactDecimalFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.haseebahmed.covid19tracker.API.APIUtilities;
import com.haseebahmed.covid19tracker.HelperClasses.CountryAdapter;
import com.haseebahmed.covid19tracker.Models.CountryData;
import com.haseebahmed.covid19tracker.Models.GlobalData;
import com.haseebahmed.covid19tracker.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountriesFragment extends Fragment {

    private TextView confirmedCases, recovered, deaths;
    GlobalData globalData;
    private List<CountryData> list;
    private RecyclerView recyclerView;
    private CountryAdapter adapter;
    private ProgressDialog progressDialog;
    private SwipeRefreshLayout swipeRefreshLayout;
    EditText searchBar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_countries, container, false);
        return root;

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        confirmedCases = view.findViewById(R.id.confirmTextViewId);
        recovered = view.findViewById(R.id.recoveredTextViewId);
        deaths = view.findViewById(R.id.deathsTextViewId);
        recyclerView = view.findViewById(R.id.countriesRecyclerViewId);
        swipeRefreshLayout= view.findViewById(R.id.swipeRefreshLayout);
        searchBar= view.findViewById(R.id.searchTxt);

        list = new ArrayList<>();
        adapter = new CountryAdapter(getContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setEnabled(false);
        swipeRefreshLayout.setRefreshing(false);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        setCountriesData();
        setGlobalData();

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                    filter(s.toString());
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setGlobalData() {
        APIUtilities.getAPIInterfae().getGlobalData().enqueue(new Callback<GlobalData>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<GlobalData> call, Response<GlobalData> response) {


                CompactDecimalFormat compactDecimalFormat = CompactDecimalFormat.getInstance(Locale.ENGLISH, CompactDecimalFormat.CompactStyle.SHORT);


                globalData = (GlobalData) response.body();
                confirmedCases.setText(WorldStatFragment.prettyCount(globalData.getCases()));
                recovered.setText(WorldStatFragment.prettyCount(globalData.getRecovered()));
                deaths.setText(WorldStatFragment.prettyCount(globalData.getDeaths()));


            }

            @Override
            public void onFailure(Call<GlobalData> call, Throwable t) {

                Toast.makeText(getActivity(), "Check your internet connection", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void setCountriesData()
    {

        APIUtilities.getAPIInterfae().geCountriesData().enqueue(new Callback<List<CountryData>>() {
            @Override
            public void onResponse(Call<List<CountryData>> call, Response<List<CountryData>> response) {
                list.addAll(response.body());
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<CountryData>> call, Throwable t) {
                Toast.makeText(getActivity(), "Check your internet connection", Toast.LENGTH_LONG).show();
            }
        });


    }

    private void filter(String txt)
    {
        List<CountryData> filterList=new ArrayList<>();

        for(CountryData items: list)
        {
            if(items.getCountry().toLowerCase().contains(txt.toLowerCase()))
            {
                filterList.add(items);
            }
        }

        adapter.fiterList(filterList);

    }


}