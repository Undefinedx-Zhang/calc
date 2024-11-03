package com.example.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText func;
    TextView res;
    String Pression_str, ans;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //显示文本监听
        func = (EditText) findViewById(R.id.edit_text);
        res = (TextView) findViewById(R.id.view_text);
        // 按钮监听
        Button button = (Button) findViewById(R.id.button_clear);
        button.setOnClickListener(this);

        button = (Button) findViewById(R.id.button_div);
        button.setOnClickListener(this);

        button = (Button) findViewById(R.id.button_equal);
        button.setOnClickListener(this);

        button = (Button) findViewById(R.id.button_del);
        button.setOnClickListener(this);

        button = (Button) findViewById(R.id.button_mul);
        button.setOnClickListener(this);

        button = (Button) findViewById(R.id.button_add);
        button.setOnClickListener(this);

        button = (Button) findViewById(R.id.button_sub);
        button.setOnClickListener(this);

        button = (Button) findViewById(R.id.button_point);
        button.setOnClickListener(this);

        button = (Button) findViewById(R.id.button_sin);
        button.setOnClickListener(this);

        button = (Button) findViewById(R.id.button_cos);
        button.setOnClickListener(this);

        button = (Button) findViewById(R.id.button_tan);
        button.setOnClickListener(this);

        button = (Button) findViewById(R.id.button_PI);
        button.setOnClickListener(this);

        button = (Button) findViewById(R.id.button_fac);
        button.setOnClickListener(this);

        button = (Button) findViewById(R.id.button_0);
        button.setOnClickListener(this);

        button = (Button) findViewById(R.id.button_1);
        button.setOnClickListener(this);

        button = (Button) findViewById(R.id.button_2);
        button.setOnClickListener(this);

        button = (Button) findViewById(R.id.button_3);
        button.setOnClickListener(this);

        button = (Button) findViewById(R.id.button_4);
        button.setOnClickListener(this);

        button = (Button) findViewById(R.id.button_5);
        button.setOnClickListener(this);

        button = (Button) findViewById(R.id.button_6);
        button.setOnClickListener(this);

        button = (Button) findViewById(R.id.button_7);
        button.setOnClickListener(this);

        button = (Button) findViewById(R.id.button_8);
        button.setOnClickListener(this);

        button = (Button) findViewById(R.id.button_9);
        button.setOnClickListener(this);
    }
    public boolean isOperator(String s) {
        if ("+".equals(s)){
            return true;
        }
        else if ("-".equals(s)){
            return true;
        }
        else if ("×".equals(s)){
            return true;
        }
        else if ("÷".equals(s)){
            return true;
        }
        return false;
    }
    public boolean isFunction(String s) {
        if ("s".equals(s)) {
            return true;
        }
        else if ("c".equals(s)) {
            return true;
        }
        else if ("t".equals(s)) {
            return true;
        }
        else if ("l".equals(s)) {
            return true;
        }
        else if ("!".equals(s)) {
            return true;
        }
        return false;
    }

    public int priority(String s) {
        if ("+".equals(s) || "-".equals(s)) {
            return 1;
        }
        return 2;
    }


    public void linkString(Stack<String> stack) {
        if (stack.peek().equals("#")) {
            return;
        }
        StringBuilder number = new StringBuilder();
        while (true) {
            String s = stack.peek();
            if (s.equals("#")) {
                break;
            }
            number.insert(0, s);
            stack.pop();
        }
        stack.push(number.toString());
        stack.push("#");
        number.delete(0, number.length());
    }


    public void calculate(Stack<String> numStack, Stack<String> oprStack) {
        double result = 0;
        numStack.pop();
        double rightOperand = Double.parseDouble(numStack.pop());
        numStack.pop();
        double leftOperand = Double.parseDouble(numStack.pop());
        String operator = oprStack.pop();

        if ("+".equals(operator)) {
            result = leftOperand + rightOperand;
        } else if ("-".equals(operator)) {
            result = leftOperand - rightOperand;
        } else if ("×".equals(operator)) {
            result = leftOperand * rightOperand;
        } else if ("÷".equals(operator)) {
            result = leftOperand / rightOperand;
        }

        numStack.push(String.valueOf(result));
        numStack.push("#");
    }

    public double tocal(String s) {
        double num = Double.parseDouble(s);
        return num;
    }

    public double TocalFunc(String op, double num) {
        if ("sin".equals(op)) {
            return Math.sin(num);
        } else if ("cos".equals(op)) {
            return Math.cos(num);
        } else if ("ln".equals(op)) {
            return Math.log(num);
        } else if ("log".equals(op)) {
            return Math.log(num) / Math.log(2);
        } else if ("tan".equals(op)) {
            return Math.tan(num);
        } else if ("!".equals(op)) {
            return calfac(num);
        }
        return 1;
    }

    public double calfac(double Num) {
        double res = 1;
        for (int i = 1; i <= Num; i++) {
            res = res * i;
        }
        return res;
    }

    public void calculateFunction(Stack<String> numStack, Stack<String> oprStack) {
        double result = 0;
        numStack.pop();
        double operand = Double.parseDouble(numStack.pop());
        String operator = oprStack.pop();

        if ("s".equals(operator)) {
            result = Math.sin(operand);
        } else if ("c".equals(operator)) {
            result = Math.cos(operand);
        } else if ("t".equals(operator)) {
            result = Math.tan(operand);
        } else if ("l".equals(operator)) {
            result = Math.log(operand);
        } else if ("!".equals(operator)) {
            result = calfac(operand);
        }

        numStack.push(String.valueOf(result));
        numStack.push("#");
    }


    public String calculateExpression(String expression) {
        Stack<String> numStack = new Stack<String>();
        numStack.push("#");
        Stack<String> oprStack = new Stack<String>();
        String[] tokens = new String[expression.length()];
        for (int i = 0; i < expression.length(); i++) {
            tokens[i] = expression.substring(i, i + 1);
        }

        for (String token : tokens) {
            if (isOperator(token)) {
                linkString(numStack);
                if (oprStack.isEmpty()) {
                    oprStack.push(token);
                } else {
                    while (!oprStack.isEmpty() && priority(token) <= priority(oprStack.peek())) {
                        linkString(numStack);
                        calculate(numStack, oprStack);
                    }
                    oprStack.push(token);
                }
            } else if (isFunction(token)) {
                linkString(numStack);
                oprStack.push(token);
                calculateFunction(numStack, oprStack);
            } else {
                numStack.push(token);
            }
        }

        while (!oprStack.isEmpty()) {
            linkString(numStack);
            calculate(numStack, oprStack);
        }

        numStack.pop();
        return numStack.peek();
    }


    private boolean isAlpha(String s) {
        return s.matches("[a-zA-Z]");
    }

    private boolean isFunctionComplete(String func) {
        return func.matches("sin|cos|tan|ln|log");
    }


    public boolean Is_legal(String now) {
        String[] ss = new String[now.length()];
        for (int i = 0; i < now.length(); i++) {
            ss[i] = now.substring(i, i + 1);
        }
        boolean have_op = true, have_point = false;
        for (int i = 0; i < now.length(); i++) {
            if (ss[i].compareTo("9") <= 0 && ss[i].compareTo("0") >= 0) {
                have_op = false;
            } else if (ss[i].equals(".")) {
                if (have_point) return false;
                if (have_op) return false;
                have_point = true;
            } else if (isOperator(ss[i])) {
                if (have_op) return false;
                have_op = true;
                have_point = false;
            }
        }
        if (have_op) return false;
        return true;
    }

    public String cal(String now) {
        if (!Is_legal(now)) return "illegal";
        return calculateExpression(now);
    }

    public String removeLeadingZero(String input) {
        char[] chars = ("+" + input + "+").toCharArray();
        StringBuilder result = new StringBuilder();

        for (int i = 1; i < chars.length - 1; i++) {
            if (isOperator(String.valueOf(chars[i - 1])) && chars[i] == '0' && (!isOperator(String.valueOf(chars[i + 1])) || chars[i + 1] == '.')) {
                continue;
            }
            result.append(chars[i]);
        }

        return result.toString();
    }


    @Override
    public void onClick(View view) {
        Pression_str = func.getText().toString();
        ans = res.getText().toString();
        if (view.getId() == R.id.button_0) {
            Pression_str += '0';
        }

        if (view.getId() == R.id.button_1) {
            Pression_str += '1';
        }

        if (view.getId() == R.id.button_2) {
            Pression_str += '2';
        }

        if (view.getId() == R.id.button_3) {
            Pression_str += '3';
        }

        if (view.getId() == R.id.button_4) {
            Pression_str += '4';
        }

        if (view.getId() == R.id.button_5) {
            Pression_str += '5';
        }

        if (view.getId() == R.id.button_6) {
            Pression_str += '6';
        }

        if (view.getId() == R.id.button_7) {
            Pression_str += '7';
        }

        if (view.getId() == R.id.button_8) {
            Pression_str += '8';
        }

        if (view.getId() == R.id.button_9) {
            Pression_str += '9';
        }

        if (view.getId() == R.id.button_add) {
            Pression_str += '+';
        }

        if (view.getId() == R.id.button_sub) {
            Pression_str += '-';
        }

        if (view.getId() == R.id.button_mul) {
            Pression_str += '×';
        }

        if (view.getId() == R.id.button_div) {
            Pression_str += '÷';
        }

        if (view.getId() == R.id.button_clear) {
            Pression_str = "";
        }

        if (view.getId() == R.id.button_point) {
            Pression_str += '.';
        }

        if (view.getId() == R.id.button_del) {
            if (Pression_str.length() > 0)
                Pression_str = Pression_str.substring(0, Pression_str.length() - 1);
        }

        if (view.getId() == R.id.button_sin) {
            double num = Double.parseDouble(Pression_str);
            ans = String.valueOf(TocalFunc("sin", num));
            Pression_str = "sin" + "(" + Pression_str + ")";
            func.setText(Pression_str);
            func.setSelection(Pression_str.length());
            res.setText(ans);
            return;
        }
        if (view.getId() == R.id.button_cos) {
            double num = Double.parseDouble(Pression_str);
            ans = String.valueOf(TocalFunc("cos", num));
            Pression_str = "cos" + "(" + Pression_str + ")";
            func.setText(Pression_str);
            func.setSelection(Pression_str.length());
            res.setText(ans);
            return;
        }

        if (view.getId() == R.id.button_tan) {
            double num = Double.parseDouble(Pression_str);
            ans = String.valueOf(TocalFunc("tan", num));
            Pression_str = "tan" + "(" + Pression_str + ")";
            func.setText(Pression_str);
            func.setSelection(Pression_str.length());
            res.setText(ans);
            return;
        }

        if (view.getId() == R.id.button_PI) {
            Pression_str += "3.141592";
        }

        if (view.getId() == R.id.button_fac) {
            double num = Double.parseDouble(Pression_str);
            ans = String.valueOf(TocalFunc("!", num));
            Pression_str = "!" + "(" + Pression_str + ")";
            func.setText(Pression_str);
            func.setSelection(Pression_str.length());
            res.setText(ans);
            return;
        }


        String tmp = new String(Pression_str);
        if (tmp.length() == 0) {
            func.setText("");
            res.setText("");
            return;
        }
        if (tmp.substring(0, 1).equals("-")) tmp = "0" + tmp.substring(0, tmp.length());
        else tmp = "0+" + tmp.substring(0, tmp.length());
        ans = cal(tmp);
        Pression_str = removeLeadingZero(Pression_str);
        func.setText(Pression_str);
        func.setSelection(Pression_str.length());
        res.setText(ans);
        if (ans.equals("illegal")) return;
        if (view.getId() == R.id.button_equal) {
            Pression_str = ans;
            ans = "";
            func.setText(Pression_str);
            func.setSelection(Pression_str.length());
            res.setText(ans);
        }
    }
}
