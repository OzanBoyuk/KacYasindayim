package oyun.ozan.kacyasindayim;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Locale;

public class DilSecimEkrani extends AppCompatActivity {

    private ImageButton btnTurkce,btnIngilizce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dil_secim_ekrani);

        /////ADMOB banner
        AdView mAdView = (AdView) findViewById(R.id.adViewDilSecim);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        ////

        btnTurkce=(ImageButton)findViewById(R.id.imageButton);
        btnIngilizce=(ImageButton)findViewById(R.id.imageButton2);

        btnTurkce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Locale locale = new Locale("tr");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale=locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());

                //finish();
                startActivity(new Intent(DilSecimEkrani.this,Giris.class));
                Toast.makeText(getApplicationContext(),R.string.dil, Toast.LENGTH_LONG).show();

            }
        });

        btnIngilizce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Locale locale = new Locale("en");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale=locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());

                //  finish();

                startActivity(new Intent(DilSecimEkrani.this,Giris.class));

                Toast.makeText(getApplicationContext(), R.string.dil, Toast.LENGTH_LONG).show();

            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dil_secim_ekrani, menu);
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
