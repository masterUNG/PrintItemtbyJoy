package jp.co.casio.caios.sddatabasecsv;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView.BufferType;


/**
 * SD card database (SUMMARY.DB) convert to csv file specified table name and condition.
 */
//อยากให้สร้างปุ่มแบบมีมิติ
//    เมื่อยังไม่กดเลือกหัวข้อใดๆ แล้วกด Export จะมีข้อความเตือน
//    เมื่อกดเลือกหัวข้อใดที่ไม่มี ในDatabase ให้มีข้อความเตือนว่า ไม่มีข้อมูลนี้
public class SdDatabaseCsv_SampleActivity extends Activity implements OnClickListener {

    private EditText mEditText1;
    private EditText mEditText2;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        Button button;
        button = (Button) findViewById(R.id.export);
        button.setOnClickListener(this);
        button = (Button) findViewById(R.id.close);
        button.setOnClickListener(this);


        mEditText1 = (EditText) findViewById(R.id.editText1);
//		mEditText2 = (EditText) findViewById(R.id.editText2);
//		mTextView = (TextView) findViewById(R.id.textView1);

        mEditText1.setText("20151211", BufferType.SPANNABLE);
//		mEditText2.setText("000001", BufferType.NORMAL);   ไม่ใช้ Zcounter

        CheckBox cb;

        cb = (CheckBox) findViewById(R.id.css012); //css012
        cb.setChecked(false);
        cb = (CheckBox) findViewById(R.id.css013); //css0113
        cb.setChecked(false);
        cb = (CheckBox) findViewById(R.id.csso14); //css014
        cb.setChecked(false);
        cb = (CheckBox) findViewById(R.id.css015); //css015
        cb.setChecked(false);
        cb = (CheckBox) findViewById(R.id.css016); //css016
        cb.setChecked(false);
        cb = (CheckBox) findViewById(R.id.css017); //css017
        cb.setChecked(false);
//		cb =  (CheckBox) findViewById(R.id.checkbox7);
//		cb.setChecked(true);
        cb = (CheckBox) findViewById(R.id.css019); //css019
        cb.setChecked(false);
        cb = (CheckBox) findViewById(R.id.css110);
        cb.setChecked(false);


    }


    @Override
    public void onClick(View arg0) {

        switch (arg0.getId()) {
//		case R.id.button1:	// SEARCH
//			String dbName	= "SUMMARY.DB";		// DB file name
//			String table	= mEditText1.getText().toString(); // Enter Date i.e. "20130701"
//			String condition= mEditText2.getText().toString(); // Enter Zcount i.e. "000001"
//			String csvName	= table+".CSV";		// CSV file name
//			ConvertDatabaseToCsv convert = new ConvertDatabaseToCsv();
//			String data = convert.convertDatabaseToCsv(dbName,table,condition,csvName);
////			mTextView.setText(table+":"+condition+"\n"+data);
//			break;

            case R.id.export:    // Z CVS TO SD CARD

                final Button btnExport = (Button) findViewById(R.id.export);
                btnExport.setBackgroundColor(Color.GRAY);
                Handler objHandler = new Handler();
                objHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    btnExport.setBackgroundColor(0xFF8b90f3);
                    }
                }, 700);

                String bizDate = mEditText1.getText().toString();
//			String zcounter = mEditText2.getText().toString();


                CheckBox cb;
                DatabaseCsvService sd = new DatabaseCsvService();
                cb = (CheckBox) findViewById(R.id.css012);
                boolean checked1 = cb.isChecked();
                if (checked1) sd.convertCSS012(bizDate);

                cb = (CheckBox) findViewById(R.id.css013);
                boolean checked2 = cb.isChecked();
                if (checked2) sd.convertCSS013(bizDate);

                cb = (CheckBox) findViewById(R.id.csso14);
                boolean checked3 = cb.isChecked();
                if (checked3) sd.convertCSS014(bizDate);

                cb = (CheckBox) findViewById(R.id.css015);
                boolean checked4 = cb.isChecked();
                if (checked4) sd.convertCSS015(bizDate);

                cb = (CheckBox) findViewById(R.id.css016);
                boolean checked5 = cb.isChecked();
                if (checked5) sd.convertCSS016(bizDate);

                cb = (CheckBox) findViewById(R.id.css017);
                boolean checked6 = cb.isChecked();
                if (checked6) sd.convertCSS017(bizDate);

//			cb =  (CheckBox) findViewById(R.id.checkbox7);
//			boolean checked7 = cb.isChecked();
//                if (checked7) sd.convertCSS018( bizDate);

                cb = (CheckBox) findViewById(R.id.css019);
                boolean checked8 = cb.isChecked();
                if (checked8) sd.convertCSS019(bizDate);

                cb = (CheckBox) findViewById(R.id.css110);
                boolean checked9 = cb.isChecked();
                if (checked9) sd.convertCSS110(bizDate);
                Log.d("tag", "Write?");


                //Check CheckBox
                if (!(checked1 || checked2 || checked3 || checked4 || checked5 || checked6 || checked8 || checked9)) {
                    showAlerDialog();
                } else {

                    final ProgressDialog progressDialog = new ProgressDialog(SdDatabaseCsv_SampleActivity.this);
//                progressDialog.setMax(100); // ใช้กับ STYLE_HORIZONTAL
                    progressDialog.setTitle("Exporting");
                    progressDialog.setMessage("Please wait........");
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.setCancelable(true);

                    progressDialog.show();

                    new Thread() {

                        public void run() {
                            while (progressStatus < 100)
                                progressStatus += 1;

                            try {

                                sleep(2000);

                            } catch (Exception e) {

                                Log.e("tag", e.getMessage());

                            }
// dismiss the progress dialog
                            progressDialog.dismiss();
                            //ลองทำแบบ STYLE_HORIZONTAL
//                        handler.post(new Runnable() {
//                            public void run() {
//
//                                // Show the progress on TextView
//
//                                progressDialog.setProgress(progressStatus);
//                                progressDialog.setMessage(progressStatus + "");
//                            }
//
//                        });progressDialog.dismiss();
                        }

//                }.start();

                    }.start();

                }   // if


//			=================================================================================================
//			สร้าง dialog





//=============================================================================================================
                break;  // break of export

            case R.id.close:    // Close
          finish();
                break;
        }
    }//onclick
public void showAlerDialog() {

    AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
    objBuilder.setIcon(R.drawable.danger);
    objBuilder.setTitle("เลือกรายงาน");
    objBuilder.setMessage("คุณยังไม่ได้เลือกรายงาน");
    objBuilder.setCancelable(false);
    objBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    });
    objBuilder.show();
}

}//main
