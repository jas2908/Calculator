package com.cal.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalculatorController {
    @FXML
    private Label display;
    @FXML
    private Label calculation;
    public void insertNumber(String number){
        display.setText(display.getText()+number);
    }
    public void insertOperator(String operator){
        display.setText(display.getText()+operator);
    }
    public void clearText(){
        display.setText("");
        calculation.setText("");
    }
    public Label getDisplay(){
        return display;
    }

    public Label getCalculation(){
        return calculation;
    }
    public void setCalculation(String newResult){
        this.calculation.setText(newResult);
    }
    public void deleteLast(){
        if (!getDisplay().getText().isEmpty()){
            StringBuilder lastNumber =new StringBuilder(getDisplay().getText());
            lastNumber.deleteCharAt(lastNumber.length()-1);
            getDisplay().setText(lastNumber.toString());
        }
    }
    public void onMouseClick(MouseEvent mouseEvent) {

        Button button=(Button) mouseEvent.getSource();
        String buttonClicked= button.getText();

        switch (buttonClicked){
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "0":
            case ".":
                insertNumber(buttonClicked);
                break;
            case "+":
            case "-":
            case "x":
            case "÷":
            case "(":
            case ")":
                insertOperator(buttonClicked);
                break;
            case "AC":
                clearText();
                break;
            case "=":
                double result= Calculator.evaluate(this.getDisplay().getText());
                DecimalFormat df = new DecimalFormat("#.###");
                setCalculation(String.valueOf(df.format(result)));
                break;
            case "DEL":
                deleteLast();
                break;
        }
    }
}
