package com.incava.tp_baseball;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText et100; //유저 첫번째 숫자
    EditText et10; //유저 두번째 숫자
    EditText et1; //유저 세번째 숫자
    Button btn; //유저 버튼
    TextView tv; // 결과값

    int[] comAry; //컴퓨터 숫자가 들어 있는 배열
    int[] userAry; //유저 숫자가 들어 있는 배열

    int strike; // 스트라이크
    int ball; // 볼

    String lastText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random rnd = new Random();
        int temp;
        comAry = new int[]{-1, -1, -1};
        for(int i = 0;i < 3; i++){ //여기가 com 숫자 만드는 메서드
            temp = rnd.nextInt(10);
            if (temp == comAry[0] || temp == comAry[1] || temp == comAry[2] ) {
                i--;
                continue;
            }
            comAry[i] = temp;
        }
        Log.d("com : ", ""+comAry[0] + comAry[1] + comAry[2]);
        btn = findViewById(R.id.btn_submit);
        et100 = findViewById(R.id.et_handrad);
        et10 = findViewById(R.id.et_ten);
        et1 = findViewById(R.id.et_one);
        tv = findViewById(R.id.tv_result);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                userAry = new int[3];
                strike = 0;
                ball = 0;
                try {
                    userAry[0] = Integer.parseInt(et100.getText().toString());
                    userAry[1] = Integer.parseInt(et10.getText().toString());
                    userAry[2] = Integer.parseInt(et1.getText().toString());
                    for (int i = 0; i < 3; i++) { //strike 확인
                       if (comAry[i]==userAry[i]) {
                           strike += 1;
                           continue;
                       }
                       //같지 않으므로 볼 확인
                       for(int j = 0; j < 3; j++){
                           if (comAry[i] == userAry[j]){
                               ball+=1;
                               break;
                           }
                       }
                    }
                    lastText = tv.getText().toString();
                    // 지난 텍스트에 덧붙여 다시 쓰기. tip!! 끝에 \n을 붙여야 한다.
                    tv.setText(lastText+userAry[0]+userAry[1]+userAry[2] +" : " + strike + "Strike " + ball + "Ball\n");
                    et100.setText(""); //초기화
                    et10.setText("");
                    et1.setText("");
                    if(strike == 3){ //만약에 다 맞춘 경우.
                        tv.setText(tv.getText().toString()+"축하합니다.\n 맞추셧어요!!짝짝");
                    }
                } catch (Exception e) {
                    Log.d("Error :", e.toString());
                }
            }
        });
    }
}