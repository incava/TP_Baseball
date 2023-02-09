package com.incava.tp_baseball;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText et;
    Button btn;
    TextView tv;

    int com;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random rnd = new Random();
        com = rnd.nextInt(501) + 500;


        et = findViewById(R.id.et_number);
        btn = findViewById(R.id.btn_submit);
        tv = findViewById(R.id.tv_result);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num;
                try {
                    num = Integer.parseInt(et.getText().toString());
                    if (num > com) tv.setText(num + "보다 작습니다.");
                    else if (num < com) tv.setText(num + "보다 큽니다.");
                    else tv.setText("축하합니다\n 정답입니다.");
                    et.setText("");
                } catch (Exception e) {
                    Log.d("Error :", e.toString());
                }
            }
        });
    }
}