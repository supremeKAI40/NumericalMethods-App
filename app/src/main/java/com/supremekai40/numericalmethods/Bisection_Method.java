package com.supremekai40.numericalmethods;
import org.mariuszgromada.math.mxparser.Function;
import java.util.ArrayList;

public class Bisection_Method {

    protected Function f ;

    Bisection_Method(String i) {
        f = new Function("f(x) = "+i);
    }

    public ArrayList <BisectionDType> bisectionM(double a, double b) {
        ArrayList<BisectionDType> data = new ArrayList<>();
        if (f.calculate(a) * f.calculate(b) > 0) {
            //System.out.println
            return data;
        }
        double c;
            while ((b - a) >= 0.001) {
                BisectionDType row = new BisectionDType();
                //Find middle point
                c = (a + b) / 2;
                if(f.calculate(a)==0.0){
                    row.parameterA = String.valueOf(a);
                    row.parameterB= String.valueOf(b);
                    row.parameterC= String.valueOf(c);
                    data.add(row);
                    break;
                }

                else {
                    //DecimalFormat decimalFormat = new DecimalFormat("#.#####");
                    //decimalFormat.setRoundingMode(RoundingMode.CEILING);
                    //String a_clipped = decimalFormat.format(a);
                    //String b_clipped = decimalFormat.format(b);
                    //String c_clipped = decimalFormat.format(c);

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
