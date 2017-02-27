package kisielarobert.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
    }

    public void keyClicked(View view) {

        Button button = (Button) view;
        //read key string
        String key = button.getText().toString();
        //read what is on display
        TextView displayViewById = (TextView) findViewById(R.id.textView1);
        //rewrite display value
        displayViewById.setText(displayViewById.getText().toString() + key);
    }
}
