package com.supremekai40.numericalmethods;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class BisectionOutput extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    TextView err_bisection;

    private TableLayout mTableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bisection_output);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        err_bisection= findViewById(R.id.error_msg_bisection);
        err_bisection.setVisibility(View.GONE);
        mTableLayout = findViewById(R.id.tableBisection);
        mTableLayout.setStretchAllColumns(true);
        drawerLayout = findViewById(R.id.drawerBisection);
        navigationView = findViewById(R.id.navmenu);
        navigationView.setItemIconTintList(null);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
        toggle.syncState();
        loadDataBisection();
    }

    @SuppressLint("SetTextI18n")
    public void loadDataBisection()
    {
        int leftRowMargin=0;
        int topRowMargin=0;
        int rightRowMargin=0;
        int bottomRowMargin = 0;
        int smallTextSize;

        smallTextSize = (int) getResources().getDimension(R.dimen.font_size_small);


        String input_eqn = Objects.requireNonNull(getIntent().getExtras()).getString("eqn_data");
        double a; double b;
        a= getIntent().getDoubleExtra("parameterA", 0.000);
        b= getIntent().getDoubleExtra("parameterB", 0.000);

        Bisection_Method bisection_methodObject= new Bisection_Method(input_eqn);
        ArrayList<BisectionDType> list = bisection_methodObject.bisectionM(a,b);
        int rows= list.size();
        if(rows==0){
            getSupportActionBar().setTitle("Result");
            err_bisection.setVisibility(View.VISIBLE);
            err_bisection.setText("The given parameter does not enclose a root");
        }
        else {
            getSupportActionBar().setTitle("Solved in : " + rows + " Steps");
            TextView textSpacer = null;
            DecimalFormat decimalFormat = new DecimalFormat("#.#####");
            decimalFormat.setRoundingMode(RoundingMode.CEILING);
            mTableLayout.removeAllViews();
            for (int i = -1; i < rows; i++) {
                BisectionDType row = null;
                if (i > -1) {
                    row = list.get(i);
                } else {
                    textSpacer = new TextView(this);
                    textSpacer.setText("");
                }

                final TextView tvParameterA = new TextView(this);
                tvParameterA.setLayoutParams(new
                        TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tvParameterA.setGravity(Gravity.START);
                tvParameterA.setPadding(5, 15, 0, 15);
                if (i == -1) {
                    tvParameterA.setText("ParameterA");
                    tvParameterA.setBackgroundColor(Color.parseColor("#f0f0f0"));
                    tvParameterA.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                } else {
                    tvParameterA.setBackgroundColor(Color.parseColor("#f8f8f8"));
                    double resA = Double.parseDouble(row.parameterA);
                    String res = decimalFormat.format(resA);
                    tvParameterA.setText(res);
                    tvParameterA.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                }

                final TextView tvParameterB = new TextView(this);
                tvParameterB.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tvParameterB.setGravity(Gravity.START);
                tvParameterB.setPadding(10, 15, 0, 15);

                if (i == -1) {
                    tvParameterB.setText("ParameterB");
                    tvParameterB.setBackgroundColor(Color.parseColor("#f0f0f0"));
                    tvParameterB.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                } else {
                    tvParameterB.setBackgroundColor(Color.parseColor("#f8f8f8"));
                    double resB = Double.parseDouble(row.parameterB);
                    String res = decimalFormat.format(resB);
                    tvParameterB.setText(res);
                    tvParameterB.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                }

                final TextView tvParameterC = new TextView(this);
                tvParameterC.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tvParameterC.setGravity(Gravity.START);
                tvParameterC.setPadding(5, 15, 0, 15);

                if (i == -1) {

                    tvParameterC.setText("ParameterC");
                    tvParameterC.setBackgroundColor(Color.parseColor("#f0f0f0"));
                    tvParameterC.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                } else {
                    tvParameterC.setBackgroundColor(Color.parseColor("#f8f8f8"));
                    double resC = Double.parseDouble(row.parameterC);
                    String res = decimalFormat.format(resC);
                    tvParameterC.setText(res);
                    tvParameterC.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                }

                final TableRow tr = new TableRow(this);
                tr.setId(i + 1);
                TableLayout.LayoutParams trParams = new
                        TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.MATCH_PARENT);
                trParams.setMargins(leftRowMargin, topRowMargin, rightRowMargin,
                        bottomRowMargin);
                tr.setPadding(0, 0, 0, 0);
                tr.setLayoutParams(trParams);
                tr.addView(tvParameterA);
                tr.addView(tvParameterB);
                tr.addView(tvParameterC);

                if (i > -1) {
                    tr.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            TableRow tr = (TableRow) v;
                        }
                    });
                }
                mTableLayout.addView(tr, trParams);

                if (i > -1) {
                    // add separator row
                    final TableRow trSep = new TableRow(this);
                    TableLayout.LayoutParams trParamsSep = new
                            TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT);
                    trParamsSep.setMargins(leftRowMargin, topRowMargin,
                            rightRowMargin, bottomRowMargin);
                    trSep.setLayoutParams(trParamsSep);
                    TextView tvSep = new TextView(this);
                    TableRow.LayoutParams tvSepLay = new
                            TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.WRAP_CONTENT);
                    tvSepLay.span = 4;
                    tvSep.setLayoutParams(tvSepLay);
                    tvSep.setBackgroundColor(Color.parseColor("#d9d9d9"));
                    tvSep.setHeight(2);
                    trSep.addView(tvSep);
                    mTableLayout.addView(trSep, trParamsSep);
                }
            }
            err_bisection.setVisibility(View.VISIBLE);
            BisectionDType result = list.get(rows-1);
            double resultDouble = Double.parseDouble(result.parameterA);
            String resultFinal = decimalFormat.format(resultDouble);
            err_bisection.setText(String.format("The root is: %s", resultFinal));
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();
        switch (id){

            case R.id.nav_home:
                Intent h= new Intent(BisectionOutput.this,MainActivity.class);
                h.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(h);
                break;
            case R.id.nav_bisection:
                Intent i= new Intent(BisectionOutput.this, BisectionInput.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                break;
            case R.id.nav_newton:
                Intent g= new Intent(BisectionOutput.this, Newton_RaphsonInput.class);
                g.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(g);
                break;
            /*case R.id.nav_grid:
                Intent k= new Intent(BisectionOutput.this, gridView_try.class);
                k.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(k);
                break;*/
            case R.id.nav_regula:
                Intent j = new Intent(BisectionOutput.this, RegulaInput.class);
                j.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(j);
                break;
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    //To make navigation drawer go back
    public void onBackPressed(){
        if(this.drawerLayout.isDrawerOpen(GravityCompat.START)){
            this.drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            finish();
        }
    }
}
