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

public class NeKadarBuyuk extends AppCompatActivity {

    private TextView tvDogTar1,tvDogTar2,tvSonuc;
    private Button btnDogTar1,btnDogTar2,btnYasFarki;
    private DatePicker datePicker;

    static final int DATE_DIALOG_ID = 999;
    static final int DATE_DIALOG_ID2 = 1000;




    private int year;
    private int month;
    private int day;

    Typeface font;

    //private StartAppAd startAppAd = new StartAppAd(this);
    InterstitialAd mInterstitialAd; //Geçiş reklamı için

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ne_kadar_buyuk);

        ///StartApp
        // StartAppSDK.init(this, "211407729", true);

        /////ADMOB
        /////ADMOB banner
        AdView mAdView = (AdView) findViewById(R.id.adViewNeKadarBuyuk);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        ////
        /////

        ///////////////////////////////////////////////////////Geçiş reklamı

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-4823957878150792/5111424262");
        requestNewInterstitial();

        ////////////////////////////////////////////////////

        font = Typeface.createFromAsset(getAssets(), "font/Gecko_PersonalUseOnly.ttf");



        tvDogTar1=(TextView)findViewById(R.id.tvDogTar1);
        tvDogTar2=(TextView)findViewById(R.id.tvDogTar2);

        tvDogTar1.setText("");
        tvDogTar2.setText("");


        tvSonuc=(TextView)findViewById(R.id.tvDogTarSonucu);
        btnDogTar1=(Button)findViewById(R.id.btnDogTar1);
        btnDogTar2=(Button)findViewById(R.id.btnDogTar2);
        btnYasFarki=(Button)findViewById(R.id.btnYasFarki);

        datePicker=(DatePicker)findViewById(R.id.datePicker3);

        tvSonuc.setTypeface(font);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        btnDogTar1.setOnClickListener(click);
        btnDogTar2.setOnClickListener(click);
        btnYasFarki.setOnClickListener(new yasFarkiListener());

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
                case R.id.btnDogTar1:
                    showDialog(DATE_DIALOG_ID);
                    break;
                case R.id.btnDogTar2:
                    showDialog(DATE_DIALOG_ID2);
                    break;
            }
        }
    };

    private class yasFarkiListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            String dogTar1 = "", dogTar2 = "";


            dogTar1 = tvDogTar1.getText().toString();
            dogTar2 = tvDogTar2.getText().toString();

            if (dogTar1.equals("")) {
                Toast.makeText(getApplicationContext(), R.string.mesaj_dogum_tarihi_girmelisin, Toast.LENGTH_LONG).show();

            }
            else if (dogTar2.equals("")) {
                Toast.makeText(getApplicationContext(), R.string.mesaj_dogum_tarihi_girmelisin, Toast.LENGTH_LONG).show();

            }else {

                String tarih1[] = new String[3];
                String tarih2[] = new String[3];

                tarih1 = dogTar1.split("/");
                tarih2 = dogTar2.split("/");

                int gun1 = 0, ay1 = 0, yil1 = 0;
                int gun2 = 0, ay2 = 0, yil2 = 0;

                int sonucGun = 0, sonucAy = 0, sonucYil = 0;

                gun1 = Integer.parseInt(tarih1[0]);
                gun2 = Integer.parseInt(tarih2[0]);

                ay1 = Integer.parseInt(tarih1[1]);
                ay2 = Integer.parseInt(tarih2[1]);

                yil1 = Integer.parseInt(tarih1[2]);
                yil2 = Integer.parseInt(tarih2[2]);

                if (yil1 < yil2) {
                    if (gun2 < gun1) {
                        ay2--;
                        gun2 = gun2 + 30;
                        sonucGun = gun2 - gun1;
                    } else {

                        sonucGun = gun2 - gun1;
                    }
                    if (ay2 < ay1) {
                        yil2--;
                        ay2 = ay2 + 12;
                        sonucAy = ay2 - ay1;
                    } else {
                        sonucAy = ay2 - ay1;
                    }

                    sonucYil = yil2 - yil1;

                    tvSonuc.setText(sonucYil+ " "+ getResources().getString(R.string.yil)+" " +
                            sonucAy + " "+ getResources().getString(R.string.ay) +" " +
                            sonucGun + " "+ getResources().getString(R.string.gun) +" "+
                            getResources().getString(R.string.buyuksun));


                }else if(yil1 > yil2){

                    if (gun2 > gun1) {
                        ay1--;
                        gun1 = gun1 + 30;
                        sonucGun = gun1 - gun2;
                    } else {

                        sonucGun = gun1 - gun2;
                    }
                    if (ay1 < ay2) {
                        yil1--;
                        ay1 = ay1 + 12;
                        sonucAy = ay1 - ay2;
                    } else {
                        sonucAy = ay1 - ay2;
                    }

                    sonucYil = yil1 - yil2;

                    tvSonuc.setText(sonucYil + ""+ getResources().getString(R.string.yil)+" " +
                            sonucAy + " "+ getResources().getString(R.string.ay) +" " +
                            sonucGun + " "+ getResources().getString(R.string.gun) +" "+
                            getResources().getString(R.string.kucuksun));
                }else if(yil1==yil2)
                {
                    if (ay1>ay2){
                        if (gun1>gun2){
                            sonucGun=gun1-gun2;
                            sonucAy=ay1-ay2;
                            sonucYil=yil1-yil2;

                            tvSonuc.setText(sonucYil + ""+ getResources().getString(R.string.yil)+" " +
                                    sonucAy + " "+ getResources().getString(R.string.ay) +" " +
                                    sonucGun + " "+ getResources().getString(R.string.gun) +" "+
                                    getResources().getString(R.string.kucuksun));

                        }else if(gun2>gun1){
                            ay1--;
                            gun1=gun1+30;
                            sonucGun=gun1-gun2;
                            sonucAy=ay1-ay2;
                            sonucYil=yil1-yil2;

                            tvSonuc.setText(sonucYil + ""+ getResources().getString(R.string.yil)+" " +
                                    sonucAy + " "+ getResources().getString(R.string.ay) +" " +
                                    sonucGun + " "+ getResources().getString(R.string.gun) +" "+
                                    getResources().getString(R.string.kucuksun));

                        }else if(gun1==gun2){
                            sonucGun=gun1-gun2;
                            sonucAy=ay1-ay2;
                            sonucYil=yil1-yil2;

                            tvSonuc.setText(sonucYil + ""+ getResources().getString(R.string.yil)+" " +
                                    sonucAy + " "+ getResources().getString(R.string.ay) +" " +
                                    sonucGun + " "+ getResources().getString(R.string.gun) +" "+
                                    getResources().getString(R.string.kucuksun));
                        }
                    }else if(ay2>ay1){
                        if(gun1>gun2){
                            ay2--;
                            gun2=gun2+30;
                            sonucGun=gun2-gun1;
                            sonucAy=ay2-ay1;
                            sonucYil=yil2-yil1;

                            tvSonuc.setText(sonucYil + ""+ getResources().getString(R.string.yil)+" " +
                                    sonucAy + " "+ getResources().getString(R.string.ay) +" " +
                                    sonucGun + " "+ getResources().getString(R.string.gun) +" "+
                                    getResources().getString(R.string.buyuksun));

                        }else if(gun2>gun1){
                            sonucGun=gun2-gun1;
                            sonucAy=ay2-ay1;
                            sonucYil=yil2-yil1;

                            tvSonuc.setText(sonucYil + ""+ getResources().getString(R.string.yil)+" " +
                                    sonucAy + " "+ getResources().getString(R.string.ay) +" " +
                                    sonucGun + " "+ getResources().getString(R.string.gun) +" "+
                                    getResources().getString(R.string.buyuksun));

                        }else  if(gun1==gun2){
                            sonucGun=gun2-gun1;
                            sonucAy=ay2-ay1;
                            sonucYil=yil2-yil1;

                            tvSonuc.setText(sonucYil + ""+ getResources().getString(R.string.yil)+" " +
                                    sonucAy + " "+ getResources().getString(R.string.ay) +" " +
                                    sonucGun + " "+ getResources().getString(R.string.gun) +" "+
                                    getResources().getString(R.string.buyuksun));
                        }

                    }else if(ay2==ay1){
                        if(gun1>gun2){
                            sonucGun=gun1-gun2;
                            sonucAy=ay1-ay2;
                            sonucYil=yil1-yil2;

                            tvSonuc.setText(sonucYil + ""+ getResources().getString(R.string.yil)+" " +
                                    sonucAy + " "+ getResources().getString(R.string.ay) +" " +
                                    sonucGun + " "+ getResources().getString(R.string.gun) +" "+
                                    getResources().getString(R.string.kucuksun));

                        }else if(gun2>gun1){
                            sonucGun=gun2-gun1;
                            sonucAy=ay2-ay1;
                            sonucYil=yil2-yil1;

                            tvSonuc.setText(sonucYil + ""+ getResources().getString(R.string.yil)+" " +
                                    sonucAy + " "+ getResources().getString(R.string.ay) +" " +
                                    sonucGun + " "+ getResources().getString(R.string.gun) +" "+
                                    getResources().getString(R.string.buyuksun));
                        }else if(gun1==gun2){

                            tvSonuc.setText(getResources().getString(R.string.ayni_yastasiniz));
                        }

                    }


                }

            }




        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ne_kadar_buyuk, menu);
        return true;
    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, datePickerListener, year,month, day);
            case DATE_DIALOG_ID2:
                return new DatePickerDialog(this,datePickerListener2,year,month,day);
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

            tvDogTar1.setText(new StringBuilder().append(padding_str(day))
                    .append("/").append(padding_str(month+1))
                    .append("/").append(padding_str(year))
            );


        }
    };

    private DatePickerDialog.OnDateSetListener datePickerListener2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int yearr, int monthOfYear, int dayOfMonth) {

            year = yearr;
            month = monthOfYear;
            day = dayOfMonth;

            tvDogTar2.setText(new StringBuilder().append(padding_str(day))
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

    ///StartApp Geçiş Reklamı
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
