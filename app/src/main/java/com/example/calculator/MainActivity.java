package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
     TextView resultTv,solutionTv;
     MaterialButton buttonC,buttonbrackopen,buttonbrackclose;
     MaterialButton buttondivide,buttonmultiply,buttonadd,buttonsubstract,buttonequals;
     MaterialButton button1,button2,button3,button4,button5,button6, button7,button8,button9,button0;
     MaterialButton buttonAC,buttondot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);
        assign(buttonC,R.id.buttons_c);
        assign(buttonAC,R.id.buttons_AC);
        assign(buttonequals,R.id.buttons_equals);
        assign(buttondot,R.id.buttons_dot);
        assign(button0,R.id.buttons_0);
        assign(button1,R.id.buttons_1);
        assign(button2,R.id.buttons_2);
        assign(button3,R.id.buttons_3);
        assign(buttonsubstract,R.id.buttons_substract);
        assign(button4,R.id.buttons_4);
        assign(button5,R.id.buttons_5);
        assign(button6,R.id.buttons_6);
        assign(buttonadd,R.id.buttons_add);
        assign(button7,R.id.buttons_7);
        assign(button8,R.id.buttons_8);
        assign(button9,R.id.buttons_9);
        assign(buttonmultiply,R.id.buttons_multiply);
        assign(buttonbrackclose,R.id.buttons_close_bracket);
        assign(buttonbrackopen,R.id.buttons_open_bracket);
        assign(buttondivide,R.id.buttons_divide);


    }
    void assign(MaterialButton btn,int id)
    {
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view){
        MaterialButton button=(MaterialButton) view;
        String buttonText = button.getText().toString();
        String datatocal=solutionTv.getText().toString();
        if(buttonText.equals("AC"))
        {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("="))
        {
            solutionTv.setText(resultTv.getText());
            return;
        }
        if(buttonText.equals("C")){
            datatocal= datatocal.substring(0,datatocal.length()-1);
        }else{
            datatocal=datatocal+buttonText;
        }
        solutionTv.setText(datatocal);
       String finalresult=result(datatocal);
      if(!finalresult.equals("Err"))
      {
          resultTv.setText(finalresult);
      }


    }
   String result(String data){
        try {
            Context context =Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
          String finalans =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalans.endsWith(".0"))
            {
                finalans=finalans.replace(".0","");
            }
          return finalans;
        }catch (Exception e)
        {
            return "Err";
        }
   }
}