package com.haseebahmed.covid19tracker.Fragments;


import android.icu.text.CompactDecimalFormat;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.haseebahmed.covid19tracker.API.APIUtilities;
import com.haseebahmed.covid19tracker.Models.GlobalData;
import com.haseebahmed.covid19tracker.Models.GlobalHistoricalData;
import com.haseebahmed.covid19tracker.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorldStatFragment extends Fragment {

    private TextView confirmedCases,recovered,deaths;
    private TextView active,serious;
    private AnyChartView anyChart;

    private Map<String,Long> caseMap;
    private Map<String,Long> recoveredMap;
    private Map<String,Long> deathMap;



    private SwipeRefreshLayout swipeRefreshLayout;
    private Button todayBtn,totalBtn;
    private ProgressBar progressBar;

    GlobalData globalData;
    GlobalHistoricalData globalHistoricalData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState)
    {
        ViewGroup root= (ViewGroup) inflater.inflate(R.layout.fragment_worldstat,container,false);

        return root;

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        swipeRefreshLayout= view.findViewById(R.id.swipeRefreshLayoutGlobal);
        confirmedCases=view.findViewById(R.id.confirmTextViewId);
        recovered=view.findViewById(R.id.recoveredTextViewId);
        deaths=view.findViewById(R.id.deathsTextViewId);
        active= view.findViewById(R.id.activeTextViewId);
        serious= view.findViewById(R.id.seriousTextViewId);
        progressBar=view.findViewById(R.id.progressBarId);
        todayBtn=view.findViewById(R.id.todayBtnId);
        totalBtn=view.findViewById(R.id.totalBtnId);
        anyChart=view.findViewById(R.id.any_chart_view);

        swipeRefreshLayout.setEnabled(false);
        swipeRefreshLayout.setRefreshing(false);


        todayBtn.setOnClickListener(view12 -> {

            progressBar.setVisibility(View.VISIBLE);
            confirmedCases.setText(prettyCount(globalData.getTodayCases())+"");
            recovered.setText(prettyCount(globalData.getTodayRecovered())+"");
            deaths.setText(prettyCount(globalData.getTodayDeaths())+"");

            progressBar.setVisibility(View.INVISIBLE);

        });

        totalBtn.setOnClickListener(view1 -> {

            progressBar.setVisibility(View.VISIBLE);
            confirmedCases.setText(prettyCount(globalData.getCases())+"");
            recovered.setText(prettyCount(globalData.getRecovered())+"");
            deaths.setText(prettyCount(globalData.getDeaths())+"");

            progressBar.setVisibility(View.INVISIBLE);

        });

        setGlobalData();
        setGlobalHistoricalData();

    }


  public void setGlobalData()
  {
      APIUtilities.getAPIInterfae().getGlobalData().enqueue(new Callback<GlobalData>() {
          @RequiresApi(api = Build.VERSION_CODES.N)
          @Override
          public void onResponse(Call<GlobalData> call, Response<GlobalData> response) {


              CompactDecimalFormat compactDecimalFormat= CompactDecimalFormat.getInstance(Locale.ENGLISH,CompactDecimalFormat.CompactStyle.SHORT);



              globalData= (GlobalData) response.body();
              confirmedCases.setText(prettyCount(globalData.getCases())+"");
              recovered.setText( prettyCount(globalData.getRecovered())+"");
              deaths.setText(prettyCount(globalData.getDeaths())+"");
              active.setText(prettyCount(globalData.getActive())+"");
              serious.setText(prettyCount(globalData.getCritical())+"");

              progressBar.setVisibility(View.INVISIBLE);

          }

          @Override
          public void onFailure(Call<GlobalData> call, Throwable t) {

              Toast.makeText(getActivity(),"Check your internet connection",Toast.LENGTH_LONG).show();
          }
      });


  }


  public void setGlobalHistoricalData()
  {

      APIUtilities.getAPIInterfae().getGlobalHistoricalData().enqueue(new Callback<GlobalHistoricalData>() {
          @RequiresApi(api = Build.VERSION_CODES.N)
          @Override
          public void onResponse(Call<GlobalHistoricalData> call, Response<GlobalHistoricalData> response)
          {
              globalHistoricalData= (GlobalHistoricalData) response.body();

              caseMap= globalHistoricalData.getCaseMap();
              recoveredMap=globalHistoricalData.getRecoveredMap();
              deathMap=globalHistoricalData.getDeathMap();
              setUpAnyChart();

          }

          @Override
          public void onFailure(Call<GlobalHistoricalData> call, Throwable t) {

          }
      });

  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  public void setUpAnyChart()
  {
      Cartesian cartesian=AnyChart.line();
      cartesian.animation(true);
      cartesian.yAxis(false);
      cartesian.xAxis(true).label(false);
      cartesian.xScroller(true);

      cartesian.title("Number in Millions");
      cartesian.crosshair().yLabel(false)
              .yStroke((Stroke) null, null,null, (String) null, (String) null);

      cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

      List<DataEntry> seriesData= new ArrayList<>();


      for (Map.Entry<String, Long> entry : caseMap.entrySet()) {
          String key = entry.getKey();
          Long value = entry.getValue();
          seriesData.add(new ValueDataEntry(key,value));

      }


      List<DataEntry> seriesData1= new ArrayList<>();

      for (Map.Entry<String, Long> entry : recoveredMap.entrySet()) {
          String key = entry.getKey();
          Long value = entry.getValue();
          seriesData1.add(new ValueDataEntry(key,value));

      }

      List<DataEntry> seriesData2= new ArrayList<>();

      for (Map.Entry<String, Long> entry : deathMap.entrySet()) {
          String key = entry.getKey();
          Long value = entry.getValue();
          seriesData2.add(new ValueDataEntry(key,value));

      }

      Set set = Set.instantiate();
      set.data(seriesData);

      Set set1= Set.instantiate();
      set1.data(seriesData1);

      Set set2= Set.instantiate();
      set2.data(seriesData2);

      Mapping seriesMapping = set.mapAs("{ value2: 'value2', x: 'x' }");
      Mapping series1Mapping = set1.mapAs("{ value2: 'value2', x: 'x' }");
      Mapping series2Mapping = set2.mapAs("{ value2: 'value2', x: 'x' }");


      Line series=cartesian.line(seriesMapping);

      series.hovered().markers().enabled(true);
      series.name("Confirmed");
      series.stroke("#418BCA");
      series.hovered().markers()
              .type(MarkerType.CROSS)
              .size(8.0);

      series.tooltip()
              .position("right")
              .anchor(Anchor.LEFT_CENTER)
              .offsetX(5.0)
              .offsetY(5.0);

      Line series1=cartesian.line(series1Mapping);
      series1.name("Recovered");
      series1.hovered().markers().enabled(true);
      series1.stroke("#02B686");
      series1.hovered().markers()
              .type(MarkerType.CIRCLE)
              .size(8.0);

      series1.tooltip()
              .position("right")
              .anchor(Anchor.LEFT_CENTER)
              .offsetX(5.0)
              .offsetY(5.0);

      Line series2=cartesian.line(series2Mapping);

      series2.stroke("#FF4947");
      series2.name("Death");
      series2.hovered().markers().enabled(true);
      series2.hovered().markers()
              .type(MarkerType.CIRCLE)
              .size(8.0)
              .stroke("blue");

      series2.tooltip()
              .position("right")
              .anchor(Anchor.LEFT_CENTER)
              .offsetX(5.0)
              .offsetY(5.0);

      anyChart.setChart(cartesian);
  }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String prettyCount(Number number) {
        char[] suffix = {' ', 'k', 'M', 'B', 'T', 'P', 'E'};
        long numValue = number.longValue();
        int value = (int) Math.floor(Math.log10(numValue));
        int base = value / 3;
        if (value >= 3 && base < suffix.length) {
            return new DecimalFormat("#0.0").format(numValue / Math.pow(10, base * 3)) + suffix[base];
        } else {
            return new DecimalFormat("#,##0").format(numValue);
        }
    }


}

