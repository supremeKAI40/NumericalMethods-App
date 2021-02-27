package com.supremekai40.numericalmethods;
//It is the BisectionOutput Method Page to be completed
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

public class BisectionInput extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Button calculate;
    EditText bisection_eqn, parameterA, parameterB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bisection_input);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        parameterA= findViewById(R.id.parameterA);
        parameterB= findViewById(R.id.parameterB);
        bisection_eqn= findViewById(R.id.tvBisection);
        calculate= findViewById(R.id.btnBisection);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navmenu);
        navigationView.setItemIconTintList(null);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
        toggle.syncState();
        getSupportActionBar().setTitle("Bisection Method");
        
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input_eqn= bisection_eqn.getText().toString();
                String paramA= parameterA.getText().toString().trim();
                String paramB=parameterB.getText().toString().trim();

                if(input_eqn.isEmpty())
                {
                    Toast toast= Toast.makeText(getApplicationContext(),"Fill the equation in terms of x", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if(paramA.isEmpty() || paramB.isEmpty())
                {
                    Toast toast= Toast.makeText(getApplicationContext(),"Fill the parameters", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {
                    double a= Double.parseDouble(paramA);
                    double b= Double.parseDouble(paramB);
                    Intent i = new Intent(BisectionInput.this, BisectionOutput.class);

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
                Intent h= new Intent(BisectionInput.this,MainActivity.class);
                h.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(h);
                break;
            case R.id.nav_bisection:
                this.drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_newton:
                Intent g= new Intent(BisectionInput.this, Newton_RaphsonInput.class);
                g.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(g);
                break;
            /*case R.id.nav_grid:
                Intent k= new Intent(BisectionInput.this, gridView_try.class);
                k.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(k);
                break;*/
            case R.id.nav_regula:
                Intent j = new Intent(BisectionInput.this, RegulaInput.class);
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
            Intent i = new Intent(BisectionInput.this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    }
}
