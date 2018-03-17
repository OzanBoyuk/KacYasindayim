package oyun.ozan.kacyasindayim;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Calendar;

import myclasses.VeriTabaniIslemleriDiger;

public class DigerleriEkle extends AppCompatActivity {

    private VeriTabaniIslemleriDiger v1;

    private TextView tvDigerDogumTarihi;
    private Button btnDigerEkle;

    private EditText etGirilen;

    private DatePicker datePicker;
    private int year;
    private int month;
    private int day;
    static final int DATE_DIALOG_ID = 999;

    //private StartAppAd startAppAd = new StartAppAd(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digerleri_ekle);

        /////ADMOB
        /////ADMOB banner
        AdView mAdView = (AdView) findViewById(R.id.adViewDigerleriEkle);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        ////
        /////

        ///StartApp
       // StartAppSDK.init(this, "211407729", true);

        v1= new VeriTabaniIslemleriDiger(this);

        tvDigerDogumTarihi=(TextView)findViewById(R.id.tvDigerDogumTarihi);
        btnDigerEkle=(Button)findViewById(R.id.btnDigerEkle);
        etGirilen=(EditText)findViewById(R.id.etGirilenDiger);
        datePicker=(DatePicker)findViewById(R.id.datePicker4);


        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        tvDigerDogumTarihi.setOnClickListener(click);
        btnDigerEkle.setOnClickListener(click);

    }

    private void ekle(String ad, String tarih) {

        SQLiteDatabase db = v1.getWritableDatabase();
        ContentValues cv1 = new ContentValues();

        cv1.put("ad",ad);
        cv1.put("tarih",tarih);

        db.insertOrThrow("digerleri", null, cv1);

    }

    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.tvDigerDogumTarihi:
                    showDialog(DATE_DIALOG_ID);
                    break;
                case R.id.btnDigerEkle:

                    if (etGirilen.getText().toString().equals("")) {
                        Toast.makeText(getApplicationContext(), R.string.mesaj_isim_gir, Toast.LENGTH_LONG).show();

                    }else if (tvDigerDogumTarihi.getText().toString().equals("Date of Birth")||
                            tvDigerDogumTarihi.getText().toString().equals("Doğum Tarihi")){
                        Toast.makeText(getApplicationContext(), R.string.mesaj_tarih_sec, Toast.LENGTH_LONG).show();

                    }else {
                        ekle("  "+etGirilen.getText().toString()+"  ",tvDigerDogumTarihi.getText().toString());
                        Toast.makeText(getApplicationContext(),R.string.mesaj_eklendi, Toast.LENGTH_LONG).show();
                        finish();

                    }


                    break;
            }
        }
    };

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, datePickerListener, year,month, day);

        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener =  new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int selectedYear, int monthOfYear, int dayOfMonth)
        {
            year = selectedYear;
            month = monthOfYear;
            day = dayOfMonth;

            tvDigerDogumTarihi.setText(new StringBuilder().append(padding_str(day))
                    .append("/").append(padding_str(month+1))
                    .append("/").append(padding_str(year))
            );


        }
    };

    private static String padding_str(int c)
    {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

}
