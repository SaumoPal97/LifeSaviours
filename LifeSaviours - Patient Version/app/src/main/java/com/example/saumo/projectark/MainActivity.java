package com.example.saumo.projectark;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

public class MainActivity extends Activity {
    TextView barcodeResult;
    Button clearResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barcodeResult= (TextView)findViewById(R.id.barcode_result);
        clearResult = (Button) findViewById(R.id.refresh);
    }
    public void scanBarcode(View v){
        Intent intent = new Intent(this, ScanBarcodeActivity.class);
        startActivityForResult(intent,0);

    }

    public void onClick(View v){
        if(v == clearResult){
            barcodeResult.setText("");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==0){
            if(requestCode== CommonStatusCodes.SUCCESS){
                if(data!=null){
                    Barcode barcode = data.getParcelableExtra("barcode");
                    String first = "This food is ";
                    String positive = "allergic";
                    String negative = "not allergic";
                    String nextPositive = "<font color='#FF0000'>";
                    String nextNegative = "<font color='#00FF00'>";
                    String next = "</next>";
                    if(barcode.rawValue.equals("8902080011438")) {
                        barcodeResult.setText(Html.fromHtml(first + nextPositive + positive + next));
                    } else {
                        barcodeResult.setText(Html.fromHtml(first + nextNegative + negative + next));
                    }
                } else {
                    barcodeResult.setText("No barcode found");
                }
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
