package com.example.bmicalculators026;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //リスナ・オブジェクトの用意
        HelloListener listener = new HelloListener();
        //クリアボタンであるButtonオブジェクトを取得
        Button btClear = findViewById(R.id.bt_Clear);
        //クリアボタンであるButtonオブジェクトを取得
        Button btkeisan = findViewById(R.id.bt_keisan);
        //クリアボタンにリスナを設定
        btClear.setOnClickListener(listener);
        //クリアボタンにリスナを設定
        btkeisan.setOnClickListener(listener);
    }

    private class HelloListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            EditText input1 = findViewById(R.id.et_nenrei);
            EditText input2 = findViewById(R.id.et_sintilyou);
            EditText input3 = findViewById(R.id.et_taijuu);

            TextView kelutka1 = findViewById(R.id.keltuka1);
            TextView kelutka2 = findViewById(R.id.keltuka2);
            TextView kelutka3 = findViewById(R.id.keltuka3);
            TextView kelutka4 = findViewById(R.id.keltuka4);
            TextView kelutka5 = findViewById(R.id.keltuka5);




            String str1 = input1.getText().toString();
            String str2 = input2.getText().toString();
            String str3 = input3.getText().toString();
             if(str1.isEmpty() || str2.isEmpty() || str3.isEmpty()){
                 return;
             }

            int nenrei = Integer.parseInt(str1);
            double cm = Double.parseDouble(str2);
            double kg = Double.parseDouble(str3);
            double m = cm / 100;

            double bmi = kg / (m * m);

            double teki = (m*m) * 22;
            String formatteki = String.format("%.1f",teki);
            int id = view.getId();

            if (id == R.id.bt_Clear) {
                input1.setText("");
                input2.setText("");
                input3.setText("");
                kelutka1.setText("");
                kelutka2.setText("");
                kelutka3.setText("");
                kelutka4.setText("");
                kelutka5.setText("");
            }

            if(id == R.id.bt_keisan){
                String str;
                if(bmi < 18.5){
                    str = "低体重";
                }
                else if(18.5 <= bmi && bmi < 25){
                     str = "普通体重";
                }
                else if(25 <= bmi && bmi < 30){
                    str = "肥満(1度)";
                }
                else if(30 <= bmi && bmi < 35){
                    str = "肥満(2度)";
                }
                else if(35 <= bmi && bmi < 40){
                    str = "肥満(3度)";
                }else{
                    str = "肥満(4度)";
                }
                kelutka1.setText("あなたの肥満度は");
                kelutka2.setText(str);
                kelutka3.setText("あなたの適正体重は");
                kelutka4.setText(String.valueOf(formatteki));
                kelutka5.setText("kg");
                if(nenrei < 16){
                    showAgeDialog();
                }


            }
        }
        private void showAgeDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("適切な肥満度を求めるためには、６歳未満の場合はカウブ指数が、６歳から１５歳まではローレル指数が使われます。本アプリはBMIのみに対応しています。")
                    .setTitle("警告")
                    .setPositiveButton("確認", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}