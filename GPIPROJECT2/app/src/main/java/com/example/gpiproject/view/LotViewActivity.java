package com.example.gpiproject.view;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gpiproject.R;
import com.example.gpiproject.Viewmodel.LotCreationViewModel;
import com.example.gpiproject.model.LotCreationModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LotViewActivity extends AppCompatActivity {
    TableLayout lot_table;
    String RefId;
    List<LotCreationModel> lotCreations;
    String[] colors = new String[]{"#EFF0EA", "#DFDFDF"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lot_view);
        lot_table = (TableLayout) findViewById(R.id.score_table1);
        //fillCountryTable();

        LotCreationViewModel lotCreationViewModel = new LotCreationViewModel(getApplication(), LotViewActivity.this);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String strDate = formatter.format(new Date());

        Intent intent = getIntent();
        RefId = intent.getStringExtra("RefId");
//        OrgCode = intent.getStringExtra("Orgcode");
//        code = intent.getStringExtra("code");
        String headerId = "M01" + strDate;

        lotCreations = lotCreationViewModel.getLotCreations(headerId);

        if (lotCreations.size()!=0) {

            TableLayout table = (TableLayout) findViewById(R.id.score_table1);

            TableRow tbrow0 = new TableRow(this);

            TextView tv0 = new TextView(this);
            tv0.setText(" Sl.No ");
            tv0.setTextSize(22);
            tv0.setTextColor(Color.WHITE);
            tv0.setBackgroundColor(Color.parseColor("#956175"));
            tv0.setHeight(50);
            tv0.setGravity(Gravity.CENTER);
            tbrow0.addView(tv0);

            TextView tv1 = new TextView(this);
            tv1.setText(" HEADER ID ");
            tv1.setTextSize(22);
            tv1.setTextColor(Color.WHITE);
            tv1.setBackgroundColor(Color.parseColor("#956175"));
            tv1.setGravity(Gravity.CENTER);
            tv1.setHeight(50);
            tbrow0.addView(tv1);

            TextView tv2 = new TextView(this);
            tv2.setText(" FARMER ID ");
            tv2.setTextSize(22);
            tv2.setTextColor(Color.WHITE);
            tv2.setBackgroundColor(Color.parseColor("#956175"));
            tv2.setGravity(Gravity.CENTER);
            tv2.setHeight(50);
            tbrow0.addView(tv2);

            TextView tv3 = new TextView(this);
            tv3.setText(" BALE NO ");
            tv3.setTextSize(22);
            tv3.setTextColor(Color.WHITE);
            tv3.setBackgroundColor(Color.parseColor("#956175"));
            tv3.setGravity(Gravity.CENTER);
            tv3.setHeight(50);
            tbrow0.addView(tv3);
            table.addView(tbrow0);

            for (int i = 0; i < lotCreations.size(); i++) {


                TableRow row = new TableRow(LotViewActivity.this);

                //TableRow.LayoutParams row = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);

                @SuppressWarnings("unchecked")
                LotCreationModel data1 = lotCreations.get(i);
                int val = i % 2;
                ShapeDrawable border = new ShapeDrawable(new RectShape());
                border.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
                border.getPaint().setColor(Color.parseColor(colors[val]));


                TextView slno = new TextView(this);
                slno.setText("" + (i + 1));
                slno.setTextSize(22);
                slno.setGravity(Gravity.CENTER);
                slno.setHeight(100);
                slno.setWidth(100);
                slno.setBackground(border);
                //tbrow.addView(t1v);

                TextView headerView = new TextView(LotViewActivity.this);
                headerView.setText(data1.getHeaderId().toString());
                headerView.setGravity(Gravity.CENTER);
                headerView.setTextSize(22);
                headerView.setHeight(100);
                headerView.setWidth(200);
                headerView.setBackground(border);


                TextView farmerview = new TextView(LotViewActivity.this);
                farmerview.setTextSize(22);
                farmerview.setText(data1.getFarmerCode().toString());
                farmerview.setGravity(Gravity.CENTER);
                farmerview.setHeight(100);
                farmerview.setWidth(200);
                farmerview.setBackground(border);

                TextView baleView = new TextView(LotViewActivity.this);

                baleView.setText(data1.getBaleNumber().toString());
                baleView.setTextSize(22);
                baleView.setGravity(Gravity.CENTER);
                baleView.setHeight(100);
                baleView.setWidth(280);
                //baleView.setLayoutParams(params);
                baleView.setBackground(border);
                // row.addView(taskhour);


                row.addView(slno);
                row.addView(headerView);
                row.addView(farmerview);
                row.addView(baleView);


                table.addView(row);

            /*table.addView(row, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
*/

            }
        }

    }

}
