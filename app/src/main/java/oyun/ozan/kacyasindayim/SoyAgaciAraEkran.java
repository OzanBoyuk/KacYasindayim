package oyun.ozan.kacyasindayim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class SoyAgaciAraEkran extends AppCompatActivity {

    private Button btnSoyAgaciAraEkranEkle,btnSoyagaciAraEkranGoster;
    InterstitialAd mInterstitialAd; //Geçiş reklamı için

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soy_agaci_ara_ekran);

        ///StartApp
        //StartAppSDK.init(this, "211407729", true);

        /////ADMOB
        /////ADMOB banner
        AdView mAdView = (AdView) findViewById(R.id.adViewSOyAgaciAraEkran);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        ////
        /////

        ///////////////////////////////////////////////////////Geçiş reklamı

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-4823957878150792/5111424262");
        requestNewInterstitial();

        ////////////////////////////////////////////////////

        btnSoyAgaciAraEkranEkle=(Button)findViewById(R.id.btnSoyAgaciAraEkranEkle);
        btnSoyagaciAraEkranGoster=(Button)findViewById(R.id.btnAraEkranSoyAgaciGor);

        btnSoyAgaciAraEkranEkle.setOnClickListener(new SoyAgaciAraEkranEkleListener());
        btnSoyagaciAraEkranGoster.setOnClickListener(new SoyAgaciAraEkranGosterListener());
    }


    private class SoyAgaciAraEkranEkleListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {



            Intent i = new Intent(SoyAgaciAraEkran.this,SoyAgaciEkle.class);
            startActivity(i);
        }
    }

    private class SoyAgaciAraEkranGosterListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent i = new Intent(SoyAgaciAraEkran.this,SoyAgaciGor.class);
            startActivity(i);
        }
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

////////////////////////////////////////////////////////


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_soy_agaci_ara_ekran, menu);
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
}
