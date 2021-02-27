package com.supremekai40.numericalmethods;
import org.mariuszgromada.math.mxparser.Function;
import java.util.ArrayList;

public class Regula_Method {

    protected Function f ;

    Regula_Method(String i) {
        f = new Function("f(x) = "+i);
    }

    public ArrayList<RegulaDType> regulaM(double a, double b) {
        ArrayList<RegulaDType> data = new ArrayList<>();
        if (f.calculate(a) * f.calculate(b) > 0) {
            //System.out.println
            return data;
        }
        double c;
        while ((b - a) >= 0.001) {
            RegulaDType row = new RegulaDType();
            //Find middle point
            c = (a *f.calculate(b)- b*f.calculate(a)) / (f.calculate(b)-f.calculate(a));
            if(f.calculate(a)==0.0){
                row.parameterA = String.valueOf(a);
                row.parameterB= String.valueOf(b);
                row.parameterC= String.valueOf(c);
                data.add(row);
                break;
            }
            else {
                row.parameterA = String.valueOf(a);
                row.parameterB = String.valueOf(b);
                row.parameterC = String.valueOf(c);
                data.add(row);
                //Check if middle point is root
                if (f.calculate(c) == 0.0)
                    break;
                    //Decide the side to repeat the steps
                else if (f.calculate(c) * f.calculate(a) < 0)
                    b = c;
                else
                    a = c;
            }
        }
        return data;
    }
}
