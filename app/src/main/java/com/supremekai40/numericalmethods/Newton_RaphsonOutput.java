package com.supremekai40.numericalmethods;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

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

public class Newton_RaphsonOutput extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    TextView err_NR;

    private TableLayout nTableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newton__raphson_output);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        err_NR= findViewById(R.id.error_msg_NR);
        err_NR.setVisibility(View.GONE);

        nTableLayout= findViewById(R.id.tableInvoicesNR);
        nTableLayout.setStretchAllColumns(true);

        drawerLayout = findViewById(R.id.drawerNR);
        navigationView = findViewById(R.id.navmenu);
        navigationView.setItemIconTintList(null);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
        toggle.syncState();
        loadDataNR();
    }
    public void loadDataNR(){
        int leftRowMargin=0;
        int topRowMargin=0;
        int rightRowMargin=0;
        int bottomRowMargin = 0;
        int smallTextSize;

        smallTextSize = (int) getResources().getDimension(R.dimen.font_size_small);


        String input_eqn = getIntent().getExtras().getString("eqn_data");
        double x;
        x= getIntent().getDoubleExtra("parameterX", 0.000);

        NewtonRaphson_Method newtonRaphson_methodOBJ= new NewtonRaphson_Method(input_eqn);
        ArrayList <NewtonDType> list = newtonRaphson_methodOBJ.newtonRaphsonM(x);
        DecimalFormat decimalFormat = new DecimalFormat("#.#####");
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
        nTableLayout.removeAllViews();
        int rows= list.size();
        if(rows==0){
            getSupportActionBar().setTitle("Result");
            err_NR.setVisibility(View.VISIBLE);
            String resultFinal = decimalFormat.format(x);
            err_NR.setText(String.format("Parameter entered is root itself. The root is: %s", resultFinal));
        }
        else {
            getSupportActionBar().setTitle("Solved in : " + rows + " Steps");
            TextView textSpacer = null;

            for (int i = -1; i < rows; i++) {
                NewtonDType row = null;
                if (i > -1) {
                    row = list.get(i);
                } else {
                    textSpacer = new TextView(this);
                    textSpacer.setText("");
                }

                final TextView tvParameterX = new TextView(this);
                tvParameterX.setLayoutParams(new
                        TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tvParameterX.setGravity(Gravity.START);
                tvParameterX.setPadding(5, 15, 0, 15);
                if (i == -1) {
                    tvParameterX.setGravity(Gravity.CENTER);
                    tvParameterX.setText("x: ");
                    tvParameterX.setBackgroundColor(Color.parseColor("#f0f0f0"));
                    tvParameterX.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                } else {
                    tvParameterX.setBackgroundColor(Color.parseColor("#f8f8f8"));
                    double resX = Double.parseDouble(row.parameterX);
                    String res = decimalFormat.format(resX);
                    tvParameterX.setText(res);
                    tvParameterX.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                }

                final TextView tvParameterFn = new TextView(this);
                tvParameterFn.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tvParameterFn.setGravity(Gravity.START);
                tvParameterFn.setPadding(10, 15, 0, 15);

                if (i == -1) {
                    tvParameterFn.setText("F(x)");
                    tvParameterFn.setBackgroundColor(Color.parseColor("#f0f0f0"));
                    tvParameterFn.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                } else {
                    tvParameterFn.setBackgroundColor(Color.parseColor("#f8f8f8"));
                    double resFn = Double.parseDouble(row.parameterFn);
                    String res = decimalFormat.format(resFn);
                    tvParameterFn.setText(res);
                    tvParameterFn.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                }

                final TextView tvParameterDerivFn = new TextView(this);
                tvParameterDerivFn.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.MATCH_PARENT));
                tvParameterDerivFn.setGravity(Gravity.START);
                tvParameterDerivFn.setPadding(5, 15, 0, 15);

                if (i == -1) {

                    tvParameterDerivFn.setText("Fn'(x)");
                    tvParameterDerivFn.setBackgroundColor(Color.parseColor("#f0f0f0"));
                    tvParameterDerivFn.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                } else {
                    tvParameterDerivFn.setBackgroundColor(Color.parseColor("#f8f8f8"));
                    double resDerivFn = Double.parseDouble(row.parameterderivFn);
                    String res = decimalFormat.format(resDerivFn);
                    tvParameterDerivFn.setText(res);
                    tvParameterDerivFn.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                }

                final TextView tvParameterH = new TextView(this);
                tvParameterH.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tvParameterH.setGravity(Gravity.START);
                tvParameterH.setPadding(10, 15, 0, 15);
                if (i == -1) {

                    tvParameterH.setText("h");
                    tvParameterH.setBackgroundColor(Color.parseColor("#f0f0f0"));
                    tvParameterH.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                } else {
                    tvParameterH.setBackgroundColor(Color.parseColor("#f8f8f8"));
                    double resH = Double.parseDouble(row.parameterH);
                    String res = decimalFormat.format(resH);
                    tvParameterH.setText(res);
                    tvParameterH.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
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
                tr.addView(tvParameterX);
                tr.addView(tvParameterFn);
                tr.addView(tvParameterDerivFn);
                tr.addView(tvParameterH);

                if (i > -1) {
                    tr.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            TableRow tr = (TableRow) v;
                        }
                    });
                }
                nTableLayout.addView(tr, trParams);

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
                    tvSep.setHeight(1);
                    trSep.addView(tvSep);
                    nTableLayout.addView(trSep, trParamsSep);
                }
            }
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();
        switch (id){

            case R.id.nav_home:
                Intent h= new Intent(Newton_RaphsonOutput.this,MainActivity.class);
                h.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(h);
                break;
            case R.id.nav_bisection:
                Intent i= new Intent(Newton_RaphsonOutput.this, BisectionInput.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                break;
            case R.id.nav_newton:
                Intent g= new Intent(Newton_RaphsonOutput.this, Newton_RaphsonInput.class);
                g.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(g);
                break;
            /*case R.id.nav_grid:
                Intent k= new Intent(Newton_RaphsonOutput.this, gridView_try.class);
                k.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(k);
                break;*/
            case R.id.nav_regula:
                Intent j = new Intent(Newton_RaphsonOutput.this, RegulaInput.class);
                j.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(j);
                break;
        }

        drawerLayout = findViewById(R.id.drawer);
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
