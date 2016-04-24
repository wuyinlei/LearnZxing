package com.example.wuyin.learnzxing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class MainActivity extends AppCompatActivity {

    private Button btnStartScan;

    private TextView tvResult;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //扫描二维码
        btnStartScan = (Button) findViewById(R.id.btn_start_scan);
        btnStartScan.setOnClickListener(new MyClickListener());
        tvResult = (TextView) findViewById(R.id.result);


    }


    class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btn_start_scan) {
                startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class), 0);
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //接受返回的二维码数据
            Bundle bundle = data.getExtras();
            //这个key是在CaptureActivity中的this.setResult(RESULT_OK, resultIntent)查到的
            String result = bundle.getString("result");
            tvResult.setText(result);
        }
    }
}
