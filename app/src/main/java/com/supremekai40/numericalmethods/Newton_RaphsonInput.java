package com.supremekai40.numericalmethods;
//This is the newton Raphson Page to be worked upon.
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


public class Newton_RaphsonInput extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Button calculate;
    EditText NewtonRaphson_eqn, parameterx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newton_raphson_input);

        calculate= findViewById(R.id.btnNR);
        parameterx = findViewById(R.id.parameterx);
        NewtonRaphson_eqn= findViewById(R.id.tvNR);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navmenu);


        navigationView.setItemIconTintList(null);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
        toggle.syncState();

        getSupportActionBar().setTitle("Newton Raphson Method");

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input_eqn= NewtonRaphson_eqn.getText().toString();
                String paramX= parameterx.getText().toString().trim();

                if(input_eqn.isEmpty())
                {
                    Toast toast= Toast.makeText(getApplicationContext(),"Fill the equation strictly in terms of x only", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if(paramX.isEmpty())
                {
                    Toast toast= Toast.makeText(getApplicationContext(),"Fill the parameter", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {
                    double x= Double.parseDouble(paramX);
                    Intent i = new Intent(Newton_RaphsonInput.this, Newton_RaphsonOutput.class);

                    i.putExtra("eqn_data",input_eqn);
                    i.putExtra("parameterX", x);
                    startActivity(i);
                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.nav_home:
                Intent h= new Intent(Newton_RaphsonInput.this,MainActivity.class);
                h.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(h);
                break;
            case R.id.nav_bisection:
                Intent i= new Intent(Newton_RaphsonInput.this, BisectionInput.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                break;
            case R.id.nav_newton:
                this.drawerLayout.closeDrawer(GravityCompat.START);
                break;
            /*case R.id.nav_grid:
                Intent k= new Intent(Newton_RaphsonInput.this, gridView_try.class);
                k.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(k);
                break;*/
            case R.id.nav_regula:
                Intent j = new Intent(Newton_RaphsonInput.this, RegulaInput.class);
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
            Intent i = new Intent(Newton_RaphsonInput.this, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    }
}