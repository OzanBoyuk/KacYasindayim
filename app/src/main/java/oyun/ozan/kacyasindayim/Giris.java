package oyun.ozan.kacyasindayim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.google.android.gms.ads.*;


public class Giris extends AppCompatActivity {

    private Button btnYasHesapla,btnSoyAgaci,btnNeKadarBuyuk,btnDiger;

    ////////////Admob
    private InterstitialAd mInterstitialAd;


    ///////////////



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

        btnYasHesapla=(Button)findViewById(R.id.btnYasHesapla);
        btnSoyAgaci=(Button)findViewById(R.id.btnSoyAgaci);
        btnDiger=(Button)findViewById(R.id.btnDiger);
        btnNeKadarBuyuk=(Button)findViewById(R.id.btnNeKadarBuyuk);

        btnSoyAgaci.setOnClickListener(new SoyAgaciListener());
        btnYasHesapla.setOnClickListener(new YasHesaplaListener());
        btnNeKadarBuyuk.setOnClickListener(new NeKadarBuyukListener());
        btnDiger.setOnClickListener(new DigerListener());

        /////ADMOB banner
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        ////

        ///////////////////////////////////////////////////////Geçiş reklamı

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-4823957878150792/5111424262");
        requestNewInterstitial();

        ////////////////////////////////////////////////////

        ///StartApp
        // StartAppSDK.init(this, "211407729", true);

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

    private class SoyAgaciListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent i = new Intent(Giris.this,SoyAgaciAraEkran.class);
            startActivity(i);
        }
    }

    private class YasHesaplaListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent i = new Intent(Giris.this , YasHesapla.class);
            startActivity(i);
        }
    }

    private class DigerListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent i = new Intent(Giris.this , DigerleriAraEkran.class);
            startActivity(i);
        }
    }

    private class NeKadarBuyukListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent i = new Intent(Giris.this , NeKadarBuyuk.class);
            startActivity(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_giris, menu);
        return true;
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Intent.ACTION_MAIN);

        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
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

}
