package com.calculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class CalculatorController  {
    @FXML
    private TextField txt_result;
    String op ="";
    double number1, number2;
    boolean afterOP = false;  // controlling the sequence of numbers 1 and 2
    boolean dot= true;   // just 1 dot per number

    //method receiving the numbers and dot
    public void Number (ActionEvent ae){
        String no = ((Button)ae.getSource()).getText();
        //checking if the number already contain dot
            if(no.equals(".")){
                if(dot){txt_result.setText(txt_result.getText()+no);}
                dot= false;
                return;
            }
        // before operation, we receive number 1, after operation receive number 2
        if(!afterOP){
            // first number.
            txt_result.setText(txt_result.getText()+no);
        }else{
            //  second number.
            txt_result.setText(no);
            dot=true;
            afterOP=false;
        }
    }
    // method for all the operation - "+", "-","*", "/", "+/-", "="
    public void Operation (ActionEvent ae){
        String operation = ((Button)ae.getSource()).getText();
            //when clicking "="
        if (operation.equals("=")){
            clear(op); //clear if clicking on "=" again
            //the second number is the string sequence after the operation string
            number2 = Double.parseDouble(txt_result.getText().substring(txt_result.getText().indexOf(op)+1));
            calculate(number1, number2, op);
            op="";
            //when clicking "+", "-","*", "/", "+/-"
        }else {
            op = operation;
            //receiving number 1
            number1 = Double.parseDouble(txt_result.getText());
            if(op.equals("+/-")){txt_result.setText((number1*-1) + "");
                op="";
            }else{txt_result.setText(number1 + op);
                dot=true;
            }
        }
    }
    public void calculate (double n1, double n2, String op){
        dot=true;
        switch (op){
            case "" : txt_result.setText("");break;
            case "+" :{txt_result.setText(n1 + n2 + "");afterOP=true;}break;
            case "-" :{txt_result.setText(n1 - n2 + "");afterOP=true;}break;
            case "*" :{txt_result.setText(n1 * n2 + "");afterOP=true;}break;
            case "/" :if(n2 == 0){txt_result.setText("0"); afterOP=true; break;}
                txt_result.setText(n1/n2+ ""); afterOP=true; break;
        }
    }

    //clear text field when clicking "="
    public void clear (String op){if(op.equals("")){txt_result.setText("");}}
}
