package oyun.ozan.kacyasindayim;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


import java.util.Calendar;

import myclasses.CustomListAdapterDigerleri;
import myclasses.VeriTabaniIslemleriDiger;

public class DigerleriGor extends AppCompatActivity {

    private ListView listViewDigerleri;
    CustomListAdapterDigerleri adapter=null;

    private int year;
    private int month;
    private int day;

    String ad[] = null;
    String tarih[] = null;
    String dogumTarihineKalanSure[] = null;

    private VeriTabaniIslemleriDiger v1;

    String sonucDizisi[];

   InterstitialAd mInterstitialAd; //Geçiş reklamı için

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digerleri_gor);

        listViewDigerleri = (ListView)findViewById(R.id.listViewDigerLeri);
        //registerForContextMenu(listViewDigerleri);

        v1 = new VeriTabaniIslemleriDiger(this);


        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH) + 1;
        day = c.get(Calendar.DAY_OF_MONTH);



        /////ADMOB
        /////ADMOB banner
        AdView mAdView = (AdView) findViewById(R.id.adViewDigerleriSonuc);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        ////
        /////

        ///////////////////////////////////////////////////////Geçiş reklamı

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-4823957878150792/5111424262");
        requestNewInterstitial();

        ////////////////////////////////////////////////////


        ad=new String[cursorDigerleri().getCount()];
        tarih = new String[cursorDigerleri().getCount()];
        dogumTarihineKalanSure = new String[cursorDigerleri().getCount()];

        bilgileriAl();
        kalanGunuHesapla();

        adapter = new CustomListAdapterDigerleri(this, ad, tarih, dogumTarihineKalanSure);

        listViewDigerleri.setAdapter(adapter);

        listViewDigerleri.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



            }
        });

    }

    /*/////////Context menu

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.context_menum,menu);

    }
/////////////////////////////// */

    private void kalanGunuHesapla(){

        String dizim[] = new String[3];
        String gun = "", ay = "";


        int gunInt = 0, ayInt = 0;
        int sonucGun = 0, sonucAy = 0;

        int monthh = 0;
        int dayy = 0;



        for (int i = 0; i < tarih.length; i++) {

            dizim = tarih[i].split("/");


            monthh = month; //bugunun tarihi
            dayy = day;

            gun = dizim[0];
            ay = dizim[1];


            gunInt = Integer.parseInt(gun); //dogum tarihi
            ayInt = Integer.parseInt(ay);


            if (gunInt >= dayy) {
                sonucGun = gunInt - dayy;

                if (ayInt >= monthh) {
                    sonucAy = ayInt - monthh;
                } else {
                    ayInt += 12;
                    sonucAy = ayInt - monthh;
                }

            } else {
                gunInt += 30;
                ayInt--;
                sonucGun = gunInt - dayy;
                if (ayInt >= monthh) {
                    sonucAy = ayInt - monthh;
                } else {
                    ayInt += 12;
                    sonucAy = ayInt - monthh;
                }
            }

            if (sonucGun == 0 && sonucAy == 0) {
                dogumTarihineKalanSure[i] = getResources().getString(R.string.mesaj_bugun_dogum_gunu);
                         /*   ////////////////////////////////////////Bildirim
                            Intent intent = new Intent(getApplicationContext(), SoyAgaciGor.class);
                            pendingIntent = PendingIntent.getActivity(SoyAgaciGor.this, 0, intent, 0);
                            notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                            note = new Notification(R.mipmap.ic_launcher, "Bildirim", 5000);
                            note.setLatestEventInfo(SoyAgaciGor.this, "Bildirim başlığı", "Bildirim içeriği", pendingIntent);
                            notificationManager.notify("bildirimim", NOTIFY_ME_ID, note); */
            } else
                dogumTarihineKalanSure[i] = sonucAy + " " + getResources().getString(R.string.ay) + " "
                        + sonucGun + " " + getResources().getString(R.string.gun) + " " +
                        getResources().getString(R.string.kaldi);


        }

    }

    ///////////////////////////////////////////////////////// */

    private String[] sutunlar = {"ad", "tarih"};

    private void bilgileriAl() {

        Cursor okunanlar = cursorDigerleri();

        int index = 0;

        while (okunanlar.moveToNext()) {

            ad[index] = okunanlar.getString(okunanlar.getColumnIndex("ad"));
            tarih[index] = okunanlar.getString(okunanlar.getColumnIndex("tarih"));
            index++;
        }


    }

    private Cursor cursorDigerleri() {
        SQLiteDatabase db = v1.getReadableDatabase();
        Cursor okunanlar = db.query("digerleri", sutunlar, null, null, null, null, null);
        return okunanlar;
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


  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menum, menu);
        return true;
    } */

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

        switch (id) {
            case R.id.VerileriTemizle:
                if (veritabaniniTemizle()) {

                    finish();
                }

        }

        return super.onOptionsItemSelected(item);
    }


    private String[] sutun = {"ad", "tarih"};

    private boolean veritabaniniTemizle() {


        SQLiteDatabase db1 = v1.getReadableDatabase();


        Cursor okunanlar1 = db1.query("digerleri", sutun, null, null, null, null, null);


        String ad = "";

        while (okunanlar1.moveToNext()) {

            ad = okunanlar1.getString(okunanlar1.getColumnIndex("ad"));
            db1.delete("digerleri", "ad" + "=?", new String[]{ad});


        }


        Toast.makeText(getApplicationContext(), R.string.mesaj_kayitlar_silindi, Toast.LENGTH_LONG).show();


        return true;
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
