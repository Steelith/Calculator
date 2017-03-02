package kisielarobert.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    public static final String DISPLAY_KEY = "display";
    public static final String ACCUMULATOR_KEY = "accumulator";
    public static final String OPERATION_KEY = "operation";
    private String display = "0";
    private double accumulator = 0.0;
    private Operation currentOpperation = Operation.NONE;
    private TextView displayViewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        displayViewById = (TextView) findViewById(R.id.textView1);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(DISPLAY_KEY, display);
        outState.putDouble(ACCUMULATOR_KEY, accumulator);
        outState.putString(OPERATION_KEY, currentOpperation.name());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        display = savedInstanceState.getString(DISPLAY_KEY, "0");
        accumulator = savedInstanceState.getDouble(ACCUMULATOR_KEY);
        currentOpperation = Operation.valueOf(savedInstanceState.getString(OPERATION_KEY));
        updateDisplay();
    }

    public void keyClicked(View view) {

        Button button = (Button) view;
        //read key string
        String key = button.getText().toString();

        switch (key){
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                if(display.equals("0")){
                    display = "";
                }
                display += key;
                break;
            case ".":
                if (!display.contains(".")){
                    display += key;
            }
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                calculateOperation(key);
                break;
            case "SQRT (x)":
                calculateSquareRoot();
                break;
            case "X^2":
                calculateSquare();
                break;
            case "=":
                calculateResult();
                break;
            case "CE":
                if (display.length() > 1){
                    display = display.substring(0, display.length() - 1);
                } else {
                    display = "0";
                }
                break;
            case "C":
                display = "0";
                accumulator = 0.0;
                currentOpperation = Operation.NONE;
                break;
        }
        updateDisplay();
    }

    private void calculateSquare() {
        double squareValue = Double.parseDouble(display);
        displayResult(squareValue * squareValue);
    }

    private void calculateSquareRoot() {
        double squareRootValue = Double.parseDouble(display);
        displayResult(Math.sqrt(squareRootValue));
    }

    private void updateDisplay() {
        displayViewById.setText(display);
    }

    private void calculateResult() {
        double displayValue = Double.parseDouble(display);
        switch (currentOpperation){
            case ADD:
                displayResult(accumulator + displayValue);
                break;
            case SUBTRACT:
                displayResult(accumulator - displayValue);
                break;
            case MULTIPLY:
                displayResult(accumulator * displayValue);
                break;
            case DIVIDE:
                if (displayValue != 0) {
                    displayResult(accumulator / displayValue);
                    break;
                } else {
                    display = "Cannot divide by 0!";
                }
        }
        accumulator = 0.0;
        currentOpperation = Operation.NONE;
    }

    private void displayResult(double result) {
        if (result == (long) result) {
            display = String.format("%d", (long) result);
        } else {
            display = String.format("%s", result);
        }

    }

    private void calculateOperation(String key) {
        currentOpperation = Operation.operationFromKey(key);
        accumulator = Double.parseDouble(display);
        display = "0";
    }
}
