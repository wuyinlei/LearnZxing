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

    private Button btnStartScan, btnMakeQrcode;

    private TextView tvResult;

    private EditText etZxing;

    private ImageView showQrcode;

    private CheckBox mCheckBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //扫描二维码
        btnStartScan = (Button) findViewById(R.id.btn_start_scan);
        btnStartScan.setOnClickListener(new MyClickListener());
        tvResult = (TextView) findViewById(R.id.result);

        //生成二维码
        btnMakeQrcode = (Button) findViewById(R.id.make_qrcode);
        btnMakeQrcode.setOnClickListener(new MyClickListener());
        etZxing = (EditText) findViewById(R.id.et_zxing);
        showQrcode = (ImageView) findViewById(R.id.showQrcode);
        mCheckBox = (CheckBox) findViewById(R.id.checkbox);
    }


    class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btn_start_scan) {
                startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class), 0);
            } else if (v.getId() == R.id.make_qrcode) {
                makeQrcode();
            }
        }
    }

    /**
     * 生成二维码
     */
    private void makeQrcode() {
        String input = etZxing.getText().toString();
        if (!TextUtils.isEmpty(input)) {
            Bitmap bitmap = EncodingUtils.createQRCode(input, 500, 500, mCheckBox.isChecked() ? BitmapFactory.decodeResource(getResources(), R.mipmap.kenan) : null);
            showQrcode.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "输入不能为空", Toast.LENGTH_SHORT).show();
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
