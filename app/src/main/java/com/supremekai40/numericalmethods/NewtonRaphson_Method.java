package com.supremekai40.numericalmethods;
import androidx.annotation.NonNull;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class NewtonRaphson_Method {
    private static final double EPSILON = 0.001;
    protected Function f ;
    @NonNull
    private String test_func;

    NewtonRaphson_Method(String i) {
        f = new Function("f(x) = "+i);
        test_func= i;
    }

    // An example function whose solution
    // is determined using BisectionOutput Method.
    // The function is x^3 - x^2 + 2
    double func(double j)
    {
        Argument a= new Argument("x = "+j);
        Expression e= new Expression("der(("+test_func+"),x)",a);
        return e.calculate();
    }

    // Derivative of the above function
    // which is 3*x^x- 2*x
    /*static double derivFunc(double x)
    {
        return 3 * x * x - 2 * x;
    }*/

    // Function to find the root
    public ArrayList <NewtonDType> newtonRaphsonM(double x)
    {
        ArrayList<NewtonDType> zoomNR = new ArrayList<>();

        if(f.calculate(x)==0.0) {
            return zoomNR;
        }

        else {
            Double h = f.calculate(x) / func(x);
            while (Math.abs(h) >= 0.001) {
                NewtonDType row = new NewtonDType();
                h = f.calculate(x) / func(x);
                //DecimalFormat decimalFormat= new DecimalFormat("#.#####");
                //decimalFormat.setRoundingMode(RoundingMode.CEILING);
                x = x - h;
                //String fn_clipped= decimalFormat.format(f.calculate(x));
                //String derivfn_clipped= decimalFormat.format(func(x));
                //String x_clipped= decimalFormat.format(x);
                row.parameterX = String.valueOf(x);
                row.parameterFn = String.valueOf(f.calculate(x));
                row.parameterderivFn = String.valueOf(func(x));
                row.parameterH = String.valueOf(h);
                //x(i+1) = x(i) - f(x) / f'(x)
                zoomNR.add(row);
            }
            return zoomNR;
        }
    }
}
