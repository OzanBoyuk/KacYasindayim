package oyun.ozan.kacyasindayim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class DigerleriAraEkran extends AppCompatActivity {

    private Button btnAraEkranDigerleriEkle,btnAraEkranDigerleriGor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digerleri_ara_ekran);

        ///StartApp
        //StartAppSDK.init(this, "211407729", true);

        /////ADMOB
        /////ADMOB banner
        AdView mAdView = (AdView) findViewById(R.id.adViewDigerleriAraEkran);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        ////
        /////


        btnAraEkranDigerleriEkle=(Button)findViewById(R.id.btnDigerleriAraEkranEkle);
        btnAraEkranDigerleriGor=(Button)findViewById(R.id.btnAraEkranDigerleriGor);

        btnAraEkranDigerleriEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DigerleriAraEkran.this,DigerleriEkle.class);
                startActivity(i);

            }
        });

        btnAraEkranDigerleriGor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DigerleriAraEkran.this,DigerleriGor.class);
                startActivity(i);
            }
        });




    }
}
