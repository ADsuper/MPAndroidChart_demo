package com.example.administrator.mpandroidchart_demo;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CandleStickChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChart = findViewById(R.id.candleStickChart);
        mChart.getDescription().setEnabled(false);

        //设置图表绘制可见标签数量最大值. 仅在setDrawValues() 启用时生效
        mChart.setMaxVisibleValueCount(60);

        // 设置 X Y 轴 同时缩放
        mChart.setPinchZoom(true);
        //设置绘制 网格线 时的背景色是否显示
        mChart.setDrawGridBackground(false);


        //获取 X 轴
        XAxis xAxis = mChart.getXAxis();
        //设置 X 轴的位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置是否显示与 X 轴垂直的线（网格线）
        xAxis.setDrawGridLines(false);

        //获取左侧 Y 轴
        YAxis leftAxis = mChart.getAxisLeft();
        //设置 Y 轴 标注个数 ，第二个参数表示是否强制，一般 false ，有可能出现不均匀的情况
        leftAxis.setLabelCount(10, false);
        //设置是否显示与 Y 轴垂直的线（网格线）
        leftAxis.setDrawGridLines(false);
        //设置是否显示 Y 轴的 轴线
        leftAxis.setDrawAxisLine(true);

        //获取 右侧 Y 轴
        YAxis rightAxis = mChart.getAxisRight();
        //设置 右侧 Y 轴是否显示
        rightAxis.setEnabled(false);


        ArrayList<CandleEntry> yVals1 = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            float mult = (20 + 1);
            float val = (float) (Math.random() * 40) + mult;

            float high = (float) (Math.random() * 9) + 8f;
            float low = (float) (Math.random() * 9) + 8f;

            float open = (float) (Math.random() * 6) + 1f;
            float close = (float) (Math.random() * 6) + 1f;

            boolean even = i % 2 == 0;

            yVals1.add(new CandleEntry(
                    i, val + high,
                    val - low,
                    even ? val + open : val - open,
                    even ? val - close : val + close,
                    getResources().getDrawable(R.drawable.ic_launcher_background)
            ));
        }

        CandleDataSet set1 = new CandleDataSet(yVals1, "Data Set");

        set1.setDrawIcons(false);
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
//        set1.setColor(Color.rgb(80, 80, 80));
        set1.setShadowColor(Color.DKGRAY);
        set1.setShadowWidth(0.7f);
        set1.setDecreasingColor(Color.RED);
        set1.setDecreasingPaintStyle(Paint.Style.FILL);
        set1.setIncreasingColor(Color.rgb(122, 242, 84));
        set1.setIncreasingPaintStyle(Paint.Style.STROKE);
        set1.setNeutralColor(Color.BLUE);
        //set1.setHighlightLineWidth(1f);

        CandleData data = new CandleData(set1);

        mChart.setData(data);

    }

}
