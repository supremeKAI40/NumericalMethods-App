package com.supremekai40.numericalmethods;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class RegulaInput extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Button calculate;
    EditText regula_eqn, RparameterA, RparameterB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regula_input);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RparameterA= findViewById(R.id.RparameterA);
        RparameterB= findViewById(R.id.RparameterB);
        regula_eqn= findViewById(R.id.tvRegula);
        calculate= findViewById(R.id.btnRegula);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navmenu);
        navigationView.setItemIconTintList(null);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
        toggle.syncState();
        getSupportActionBar().setTitle("Regula Falsi Method");

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input_eqn= regula_eqn.getText().toString();
                String RparamA= RparameterA.getText().toString().trim();
                String RparamB=RparameterB.getText().toString().trim();

                if(input_eqn.isEmpty())
                {
                    Toast toast= Toast.makeText(getApplicationContext(),"Fill the equation in terms of x", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if(RparamA.isEmpty() || RparamB.isEmpty())
                {
                    Toast toast= Toast.makeText(getApplicationContext(),"Fill the parameters", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {
                    double a= Double.parseDouble(RparamA);
                    double b= Double.parseDouble(RparamB);
                    Intent i = new Intent(RegulaInput.this, RegulaOutput.class);

                    i.putExtra("eqn_data",input_eqn);
                    i.putExtra("parameterA", a);
                    i.putExtra("parameterB", b);
                    startActivity(i);
                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id) {
            case R.id.nav_home:
                Intent h= new Intent(RegulaInput.this,MainActivity.class);
                h.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(h);
                break;
            case R.id.nav_bisection:
                Intent i= new Intent(RegulaInput.this, BisectionInput.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                break;
            case R.id.nav_newton:
                Intent g= new Intent(RegulaInput.this, Newton_RaphsonInput.class);
                g.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(g);
                break;
            /*case R.id.nav_grid:
                Intent k= new Intent(BisectionInput.this, gridView_try.class);
                k.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(k);
                break;*/
            case R.id.nav_regula:
                this.drawerLayout.closeDrawer(GravityCompat.START);
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
            Intent i = new Intent(RegulaInput.this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    }
}
