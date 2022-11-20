package com.example.mycalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String workings ="2+3+8^2-9";

    TextView workingsTV;
    TextView resultsTV;
    private String tempFormula;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void checkForPowerof()
    {
        ArrayList<Integer> indexOfPowers = new ArrayList<>();
        for(int i = 0; i < workings.length(); i++)
        {
            if(workings.charAt(i) == '^')
                indexOfPowers.add(i);

        }
        String formula = workings;
        tempFormula = workings;
        for(int i =0; i < indexOfPowers.size() ; i++)
        {
            changeFormula(indexOfPowers.get((i)));
        }
        formula = tempFormula;

    }
    private void changeFormula(Integer index)
    {
        String numberLeft = "";
        String numberRight = "";

        for(int i = index + 1; i < workings.length(); i++)
        {
            if(isNumeric(workings.charAt(i)))
            {
                numberRight = numberRight + workings.charAt(i);
            }
            else
                break;
        }

        for(int i = index - 1; i > 0 ; i--)
        {
            if(isNumeric(workings.charAt(i)))
            {
                numberLeft = numberLeft + workings.charAt(i);
            }
            else
                break;
        }
        String original = numberLeft + "^" + numberRight;
        String changed = "Math.pow("+numberLeft+","+numberRight+")";
        tempFormula = tempFormula.replace(original,changed);
    }
    private boolean isNumeric(char c)
    {
        if((c <= '9' && c>= '0') || c == '.')
            return true;
        else
            return false;
    }
    public void clearOnclick(View view)
    {
        workingsTV.setText("");
        workings="";
        resultsTV.setText("");
        boolean leftBracket = true;
    }


}