package oyun.ozan.kacyasindayim;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Calendar;

public class YasHesapla extends AppCompatActivity {

     private Button btnBugununTarihi,btnDogumTarihi,btnHesapla;
    private TextView tvBugun,tvDogum,tvSonuc;
    private DatePicker datePicker;
    private int year;
    private int month;
    private int day;
    static final int DATE_DIALOG_ID = 999;
    static final int DATE_DIALOG_ID2 = 1000;


   // private StartAppAd startAppAd = new StartAppAd(this);

    InterstitialAd mInterstitialAd; //Geçiş reklamı için

    private Typeface font;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yas_hesapla);


        /////ADMOB
        /////ADMOB banner
        AdView mAdView = (AdView) findViewById(R.id.adViewYasHesapla);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        ////
        /////
        ///////////////////////////////////////////////////////Geçiş reklamı

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-4823957878150792/5111424262");
        //requestNewInterstitial();

        ////////////////////////////////////////////////////

        ///StartApp
        ///StartAppSDK.init(this, "211407729", true);

        font = Typeface.createFromAsset(getAssets(), "font/RAVIE.TTF");

        btnBugununTarihi=(Button)findViewById(R.id.btnBugununTarihi);
        btnDogumTarihi=(Button)findViewById(R.id.btnDogumTarihi);
        btnHesapla = (Button)findViewById(R.id.btnHesapla);

        tvBugun = (TextView)findViewById(R.id.tvBugun);
        tvDogum = (TextView)findViewById(R.id.tvDogum);
        tvSonuc = (TextView)findViewById(R.id.tvSonuc);

        tvSonuc.setTypeface(font);
        tvBugun.setTypeface(font);
        tvDogum.setTypeface(font);


        datePicker=(DatePicker)findViewById(R.id.datePicker);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        tvBugun.setText(new StringBuilder().append(padding_str(day))
                .append("/").append(padding_str(month+1))
                .append("/").append(padding_str(year)));

        tvDogum.setText(new StringBuilder().append(padding_str(day))
                .append("/").append(padding_str(month+1))
                .append("/").append(padding_str(year)));


        btnBugununTarihi.setOnClickListener(click);
        btnDogumTarihi.setOnClickListener(click);
        btnHesapla.setOnClickListener(btnHesaplaClick);

    }


    //////////////////////////////////////////////////--Geçiş reklamı için
    private void requestNewInterstitial() {


        com.google.android.gms.ads.AdRequest adRequest = new com.google.android.gms.ads.AdRequest.Builder()
                .addTestDevice("YOUR_DEVICE_HASH")
                .build();

        mInterstitialAd.loadAd(adRequest);


    }

    public void displayInterstitial(){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode==event.KEYCODE_BACK){
            displayInterstitial();
        }
        return super.onKeyDown(keyCode, event);
    }

/////////////////////////////////////////////////////////

    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.btnBugununTarihi:
                    Toast.makeText(getApplicationContext(),"Bugünün tarihi değiştirilemez!",Toast.LENGTH_LONG).show();
                    break;
                case R.id.btnDogumTarihi:
                    showDialog(DATE_DIALOG_ID2);
                    break;
            }
        }
    };



    int bugununGunu=0,dogumGunu=0,bugununAyi=0,dogumAyi=0,bugununYili=0,dogumYili=0;
    int sonucGun=0,sonucAy=0,sonucYil=0;

    private View.OnClickListener btnHesaplaClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String dogumTarihi="",bugununTarihi="";


            dogumTarihi = tvDogum.getText().toString();
            bugununTarihi=tvBugun.getText().toString();

            String dogumTarihiDizisi[]= new String[3];
            String bugununTarihiDizisi[] = new String[3];

            dogumTarihiDizisi = dogumTarihi.split("/");
            bugununTarihiDizisi=bugununTarihi.split("/");



            bugununGunu = Integer.parseInt(bugununTarihiDizisi[0]);
            dogumGunu = Integer.parseInt(dogumTarihiDizisi[0]);

            bugununAyi = Integer.parseInt(bugununTarihiDizisi[1]);
            dogumAyi = Integer.parseInt(dogumTarihiDizisi[1]);

            bugununYili = Integer.parseInt(bugununTarihiDizisi[2]);
            dogumYili = Integer.parseInt(dogumTarihiDizisi[2]);


            if (bugununYili==dogumYili&&bugununAyi==dogumAyi) {
                if (dogumGunu>bugununGunu){
                    Toast.makeText(getApplicationContext(),R.string.mesaj_dogum_gunu_ilerde_olamaz,Toast.LENGTH_LONG
                    ).show();
                    finish();
                }
                else
                    hesapla();
            }
            else if(bugununYili==dogumYili) {
                if (dogumAyi > bugununAyi) {
                    Toast.makeText(getApplicationContext(),R.string.mesaj_dogum_gunu_ilerde_olamaz, Toast.LENGTH_LONG
                    ).show();
                    finish();
                } else
                    hesapla();
            }
            else if(dogumYili>bugununYili) {
                Toast.makeText(getApplicationContext(), R.string.mesaj_dogum_gunu_ilerde_olamaz, Toast.LENGTH_LONG
                ).show();
                finish();
            }
            else
                hesapla();



        }
    };

    private void hesapla() {

        if((bugununGunu-dogumGunu)<0)
        {
            bugununAyi--;
            bugununGunu=bugununGunu+30;

        }

        sonucGun=bugununGunu-dogumGunu;


        if ((bugununAyi-dogumAyi)<0)
        {
            bugununYili--;
            bugununAyi=bugununAyi+12;

        }
        sonucAy=bugununAyi-dogumAyi;

        sonucYil=bugununYili-dogumYili;

        tvSonuc.setText(new StringBuilder().append(String.valueOf(sonucYil)+" "+getResources().getString(R.string.yil) +" ")
                .append(String.valueOf(sonucAy)+" "+getResources().getString(R.string.ay) +" ")
                .append(String.valueOf(sonucGun)+" "+getResources().getString(R.string.gun)));


    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            /*case DATE_DIALOG_ID:
                return new DatePickerDialog(this, datePickerListener, year,month, day); */
            case DATE_DIALOG_ID2:
                return new DatePickerDialog(this,datePickerListener2,year,month,day);
        }
        return null;
    }



    private DatePickerDialog.OnDateSetListener datePickerListener2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int yearr, int monthOfYear, int dayOfMonth) {

            year = yearr;
            month = monthOfYear;
            day = dayOfMonth;

            tvDogum.setText(new StringBuilder().append(padding_str(day))
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onResume() {
        super.onResume();

    }
    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

}
