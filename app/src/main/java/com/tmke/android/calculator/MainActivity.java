package com.tmke.android.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView result;
    TextView phrase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result_textview);
        phrase = findViewById(R.id.phrase_textview);

        Button plusBtn = findViewById(R.id.plus_button);
        plusBtn.setOnClickListener(v -> {
            if (phrase.getText().equals("")) {
                phrase.setText(result.getText() + "+");
            } else {
                writePhrase('+');
            }
        });
        Button minusBtn = findViewById(R.id.minus_button);
        minusBtn.setOnClickListener(v -> {
            if (phrase.getText().equals("")) {
                phrase.setText(result.getText() + "-");
            } else {
                writePhrase('-');
            }
        });
        Button mulButton = findViewById(R.id.multiply_button);
        mulButton.setOnClickListener(v -> {
            if (phrase.getText().equals("")) {
                phrase.setText(result.getText() + "*");
            } else {
                writePhrase('*');
            }
        });
        Button divButton = findViewById(R.id.divise_button);
        divButton.setOnClickListener(v -> {
            if (phrase.getText().equals("")) {
                phrase.setText(result.getText() + "/");
            } else {
                writePhrase('/');
            }
        });

        Button openParenthesisBtn = findViewById(R.id.open_parenthesis_button);
        openParenthesisBtn.setOnClickListener(v -> writePhrase('('));
        Button closeParenthesisBtn = findViewById(R.id.close_parenthesis_button);
        closeParenthesisBtn.setOnClickListener(v -> writePhrase(')'));
        Button resetBtn = findViewById(R.id.reset_button);
        resetBtn.setOnClickListener(v -> {
            result.setText("0");
            phrase.setText("");
        });
        Button signBtn = findViewById(R.id.sign_button);
        signBtn.setOnClickListener(v -> {
            // Log.d("Test", "1");
            String s = (String) result.getText();
            if (!s.equals("0")) {
                char[] ss = s.toCharArray();
                if (ss[0] == '-') {
                    s = String.copyValueOf(ss, 1, s.length()-1);
                    // Log.d("Test", s);
                    result.setText(s);
                } else {
                    result.setText("-" + s);
                }
            }
        });

        Button resultBtn = findViewById(R.id.result_button);
        resultBtn.setOnClickListener(v -> {
            try {
                Double calcResult = Calculation.doCalculate((String) phrase.getText());
                // Log.d("Test", calcResult.toString());
                String cc = calcResult.toString();
                boolean b = false;
                int i = cc.length() - 1; // start from end
                while(!b && i >= 0) {
                    if (cc.toCharArray()[i] == 'E') b = true;
                    else i--;
                }
                String ccc = "";
                if (b && cc.length() > 9) {
                    for (int j = 0; j < cc.length(); j++) {
                        ccc = ccc + cc.toCharArray()[j];
                        if (cc.toCharArray()[j] == '.') {
                            int k;
                            for (k = j + 1; k < j + 3; k++) {
                                ccc = ccc + cc.toCharArray()[k];
                                if (cc.toCharArray()[k] == 'E') break;
                            }
                            for (int l = k; l < cc.length(); l++) {
                                if (cc.toCharArray()[l] == 'E') {
                                    ccc = ccc + cc.toCharArray()[l];
                                    for (int m = l + 1; m < cc.length(); m++) ccc = ccc + cc.toCharArray()[m];
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }
                if (ccc.equals("")) {
                    Log.d("Test", cc);
                    calcResult = (double)Math.round(calcResult * 100000d) / 100000d;
                    // Log.d("Test", calcResult.toString());
                    if (calcResult - calcResult.intValue() == 0.0) {
                        result.setText(String.valueOf(calcResult.intValue()));
                    } else {
                        result.setText(String.valueOf(calcResult));
                    }
                } else {
                    result.setText(ccc);
                }
                phrase.setText("");
            } catch (Exception e) {
                Toast.makeText(this, "Invalid Expression", Toast.LENGTH_SHORT).show();
            }

        });
        Button pointBtn = findViewById(R.id.point_button);
        pointBtn.setOnClickListener(v -> writePhrase('.'));

        Button zeroBtn = findViewById(R.id.zero_button);
        zeroBtn.setOnClickListener(v -> writePhrase('0'));
        Button oneBtn = findViewById(R.id.one_button);
        oneBtn.setOnClickListener(v -> writePhrase('1'));
        Button twoBtn = findViewById(R.id.two_button);
        twoBtn.setOnClickListener(v -> writePhrase('2'));
        Button threeBtn = findViewById(R.id.three_button);
        threeBtn.setOnClickListener(v -> writePhrase('3'));
        Button fourBtn = findViewById(R.id.four_button);
        fourBtn.setOnClickListener(v -> writePhrase('4'));
        Button fiveBtn = findViewById(R.id.five_button);
        fiveBtn.setOnClickListener(v -> writePhrase('5'));
        Button sixBtn = findViewById(R.id.six_button);
        sixBtn.setOnClickListener(v -> writePhrase('6'));
        Button sevenBtn = findViewById(R.id.seven_button);
        sevenBtn.setOnClickListener(v -> writePhrase('7'));
        Button eightBtn = findViewById(R.id.eight_button);
        eightBtn.setOnClickListener(v -> writePhrase('8'));
        Button nineBtn = findViewById(R.id.nine_button);
        nineBtn.setOnClickListener(v -> writePhrase('9'));
    }

    public void writePhrase(char c) {
        String s = (String) phrase.getText();
        phrase.setText(s + c);
    }
}