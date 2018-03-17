package oyun.ozan.kacyasindayim;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import myclasses.CustomListAdapterSoyAgaci;
import myclasses.VeritabaniIslemleri;

public class SoyAgaciGor extends AppCompatActivity {

    private VeritabaniIslemleri v1;
    private VeritabaniIslemleri v2;
    private VeritabaniIslemleri v3;
    private VeritabaniIslemleri v4;
    private VeritabaniIslemleri v5;
    private VeritabaniIslemleri v6;

    private SQLiteDatabase db=null;

    private int year;
    private int month;
    private int day;

    private ListView listViewSoyAgaci;
    private Spinner spinner;
    /*private TextView tvUnvanSoyagaci,tvAdSoyadSoyAgaci,
    tvDogTarSoyAgaci,tvSonucSoyAgaci;*/

    ///////////////////////Bildirim değişkenleri
    private static final int NOTIFY_ME_ID = 1337;
    private Button btnTikla;
    NotificationManager notificationManager;
    Notification note;
    PendingIntent pendingIntent;
    //////////////////////////////////////////////


    String anneSonuc[];
    String babaSonuc[];
    String kizEvlatSonuc[];
    String erkekEvlatSonuc[];
    String dedeSonuc[];
    String nineSonuc[];


    CustomListAdapterSoyAgaci adapter = null,adapter2=null,adapter3=null;
    CustomListAdapterSoyAgaci adapter4 = null,adapter5=null,adapter6=null;


    String unvanAnne[] = null;
    String adAnne[] = null;
    String tarihAnne[] = null;

    String unvanBaba[] = null;
    String adBaba[] = null;
    String tarihBaba[] = null;

    String unvanKiz[] = null;
    String adKiz[] = null;
    String tarihKiz[] = null;

    String unvanErkek[] = null;
    String adErkek[] = null;
    String tarihErkek[] = null;

    String unvanDede[] = null;
    String adDede[] = null;
    String tarihDede[] = null;

    String unvanNine[] = null;
    String adNine[] = null;
    String tarihNine[] = null;

    ArrayList<HashMap<String, String>> dataList;
    String data_adlari[];
    int data_idler[];
    private int dataPosition =0,idd=0;

    InterstitialAd mInterstitialAd; //Geçiş reklamı için

    private Typeface font;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soy_agaci_gor);

        font = Typeface.createFromAsset(getAssets(), "font/RAVIE.TTF");

        listViewSoyAgaci = (ListView) findViewById(R.id.listViewSoyagaci);
        spinner= (Spinner) findViewById(R.id.spinner2);
       /* tvUnvanSoyagaci = (TextView )findViewById(R.id.tvUnvanSoyagaci);
        tvAdSoyadSoyAgaci = (TextView )findViewById(R.id.tvAdSoyadSoyAgaci);
        tvDogTarSoyAgaci = (TextView )findViewById(R.id.tvDogTarSonucu);
        tvSonucSoyAgaci = (TextView )findViewById(R.id.tvSonucSoyAgaci);*/


        //registerForContextMenu(listViewSoyAgaci);




//////Spinner
        ArrayAdapter<CharSequence> spAdapter = ArrayAdapter.createFromResource(this,R.array.spinnerim,
                android.R.layout.simple_spinner_item);
        spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spAdapter);
        spinner.setOnItemSelectedListener(new OnItemSelectedLıstener());
////////

        ///////////////////////////////////////////////////////Geçiş reklamı

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-4823957878150792/5111424262");
        requestNewInterstitial();

        ////////////////////////////////////////////////////

        v1 = new VeritabaniIslemleri(this);
        v2 = new VeritabaniIslemleri(this);
        v3 = new VeritabaniIslemleri(this);
        v4 = new VeritabaniIslemleri(this);
        v5 = new VeritabaniIslemleri(this);
        v6 = new VeritabaniIslemleri(this);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH) + 1;
        day = c.get(Calendar.DAY_OF_MONTH);

        ///StartApp
        // StartAppSDK.init(this, "211407729", true);

        Cursor okunanlarAnne = cursorAnne();
        Cursor okunanlarBaba = cursorBaba();
        Cursor okunanlarKizEvlat = cursorKiz();
        Cursor okunanlarErkekEvlat = cursorErkek();
        Cursor okunanlarDede = cursorDede();
        Cursor okunanlarNine = cursorBuyukAnne();


        unvanAnne = new String[okunanlarAnne.getCount()];
        adAnne = new String[okunanlarAnne.getCount()];
        tarihAnne = new String[okunanlarAnne.getCount()];
        anneSonuc = new String[okunanlarAnne.getCount()];

        unvanBaba = new String[okunanlarBaba.getCount()];
        adBaba = new String[okunanlarBaba.getCount()];
        tarihBaba = new String[okunanlarBaba.getCount()];
        babaSonuc = new String[okunanlarBaba.getCount()];

        unvanKiz = new String[okunanlarKizEvlat.getCount()];
        adKiz = new String[okunanlarKizEvlat.getCount()];
        tarihKiz = new String[okunanlarKizEvlat.getCount()];
        kizEvlatSonuc = new String[okunanlarKizEvlat.getCount()];

        unvanErkek = new String[okunanlarErkekEvlat.getCount()];
        adErkek = new String[okunanlarErkekEvlat.getCount()];
        tarihErkek = new String[okunanlarErkekEvlat.getCount()];
        erkekEvlatSonuc = new String[okunanlarErkekEvlat.getCount()];

        unvanDede = new String[okunanlarDede.getCount()];
        adDede = new String[okunanlarDede.getCount()];
        tarihDede = new String[okunanlarDede.getCount()];
        dedeSonuc = new String[okunanlarDede.getCount()];

        unvanNine = new String[okunanlarNine.getCount()];
        adNine = new String[okunanlarNine.getCount()];
        tarihNine = new String[okunanlarNine.getCount()];
        nineSonuc = new String[okunanlarNine.getCount()];

        bilgileriGosterAnne();
        anneDogumGununeKalaniBul();

        bilgileriGosterBaba();
        babaDogumGununeKalaniBul();

        bilgileriGosterKiz();
        kizEvlatDogumGununeKalaniBul();

        bilgileriGosterErkek();
        erkekEvlatDogumGununeKalaniBul();

        bilgileriGosterDede();
        dedeDogumTarihineKalaniBul();

        bilgileriGosterBuyukAnne();
        buyukanneDogumTarihineKalaniBul();



        adapter = new CustomListAdapterSoyAgaci(this, unvanAnne, adAnne, tarihAnne, anneSonuc);
        adapter2 = new CustomListAdapterSoyAgaci(this, unvanBaba, adBaba, tarihBaba, babaSonuc);
        adapter3=new CustomListAdapterSoyAgaci(this,unvanKiz,adKiz,tarihKiz,kizEvlatSonuc);
        adapter4=new CustomListAdapterSoyAgaci(this,unvanErkek,adErkek,tarihErkek,erkekEvlatSonuc);
        adapter5=new CustomListAdapterSoyAgaci(this,unvanDede,adDede,tarihDede,dedeSonuc);
        adapter6=new CustomListAdapterSoyAgaci(this,unvanNine,adNine,tarihNine,nineSonuc);




        listViewSoyAgaci.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              /*  idLeriDondur("anne");
                dataPosition = position;
                idd = (int)data_idler[position];
                Toast.makeText(getApplicationContext(),""+idd,Toast.LENGTH_SHORT).show();*/

            }
        });




        font = Typeface.createFromAsset(getAssets(), "font/Gecko_PersonalUseOnly.ttf");


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




    public class OnItemSelectedLıstener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(parent.getItemAtPosition(position).toString().equals(getResources().getString(R.string.anne))){
               /* tvUnvanSoyagaci.setTypeface(font);
                tvAdSoyadSoyAgaci.setTypeface(font);
                tvDogTarSoyAgaci.setTypeface(font);
                tvSonucSoyAgaci.setTypeface(font);*/
                listViewSoyAgaci.setAdapter(adapter);

            }else if(parent.getItemAtPosition(position).toString().equals(getResources().getString(R.string.baba))) {
                listViewSoyAgaci.setAdapter(adapter2);
            }else if(parent.getItemAtPosition(position).toString().equals(getResources().getString(R.string.kiz))){
                listViewSoyAgaci.setAdapter(adapter3);
            }else if(parent.getItemAtPosition(position).toString().equals(getResources().getString(R.string.erkek))){
                listViewSoyAgaci.setAdapter(adapter4);
            }else if(parent.getItemAtPosition(position).toString().equals(getResources().getString(R.string.dede))){
                listViewSoyAgaci.setAdapter(adapter5);
            }else if(parent.getItemAtPosition(position).toString().equals(getResources().getString(R.string.nine))){
                listViewSoyAgaci.setAdapter(adapter6);
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

  /*  public void idLeriDondur(String tableName) {

        VeritabaniIslemleri db = new VeritabaniIslemleri(getApplicationContext()); // Db bağlantısı oluşturuyoruz. İlk seferde database oluşturulur.
        dataList = db.tabloyuListele(tableName);//kitap listesini alıyoruz

        if (dataList.size() == 0) {//kitap listesi boşsa
            Toast.makeText(getApplicationContext(), "Henüz Kitap Eklenmemiş.\nYukarıdaki + Butonundan Ekleyiniz", Toast.LENGTH_LONG).show();
        } else {
            data_adlari = new String[dataList.size()]; // kitap adlarını tutucamız string arrayi olusturduk.
            data_idler = new int[dataList.size()]; // kitap id lerini tutucamız string arrayi olusturduk.
            for (int i = 0; i < dataList.size(); i++) {
                data_adlari[i] = dataList.get(i).get("ad");
                //kitap_liste.get(0) bize arraylist içindeki ilk hashmap arrayini döner. Yani tablomuzdaki ilk satır değerlerini
                //kitap_liste.get(0).get("kitap_adi") //bize arraylist içindeki ilk hashmap arrayin anahtarı kitap_adi olan value döner

                data_idler[i] = Integer.parseInt(dataList.get(i).get("id"));
                //Yukarıdaki ile aynı tek farkı değerleri integer a çevirdik.
            }
        }
    }

    /////////Context menu

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.context_menum, menu);

    } */

    /*@Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.sil:
                VeritabaniIslemleri vt = new VeritabaniIslemleri(getApplicationContext());

                vt.veriTabanindanSil("anne",idd);
                adapter.notifyDataSetChanged();

            case R.id.duzenle:


            default:

        }

        return true;

    }


/////////////////////////////// */



    private void anneDogumGununeKalaniBul() {


        String dizim[] = new String[3];
        String gun = "", ay = "";


        int gunInt = 0, ayInt = 0;
        int sonucGun = 0, sonucAy = 0;

        int monthh = 0;
        int dayy = 0;



        for (int i = 0; i < tarihAnne.length; i++) {

            dizim = tarihAnne[i].split("/");


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
                anneSonuc[i] = getResources().getString(R.string.mesaj_bugun_dogum_gunu);
                         /*   ////////////////////////////////////////Bildirim
                            Intent intent = new Intent(getApplicationContext(), SoyAgaciGor.class);
                            pendingIntent = PendingIntent.getActivity(SoyAgaciGor.this, 0, intent, 0);
                            notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                            note = new Notification(R.mipmap.ic_launcher, "Bildirim", 5000);
                            note.setLatestEventInfo(SoyAgaciGor.this, "Bildirim başlığı", "Bildirim içeriği", pendingIntent);
                            notificationManager.notify("bildirimim", NOTIFY_ME_ID, note); */
            } else
                anneSonuc[i] = sonucAy + " " + getResources().getString(R.string.ay) + " "
                        + sonucGun + " " + getResources().getString(R.string.gun) + " " +
                        getResources().getString(R.string.kaldi);


        }


    }

    private void babaDogumGununeKalaniBul() {
        String dizim[] = new String[3];
        String gun = "", ay = "";


        int gunInt = 0, ayInt = 0;
        int sonucGun = 0, sonucAy = 0;

        int monthh = 0;
        int dayy = 0;



        for (int i = 0; i < tarihBaba.length; i++) {

            dizim = tarihBaba[i].split("/");


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
                babaSonuc[i] = getResources().getString(R.string.mesaj_bugun_dogum_gunu);
                         /*   ////////////////////////////////////////Bildirim
                            Intent intent = new Intent(getApplicationContext(), SoyAgaciGor.class);
                            pendingIntent = PendingIntent.getActivity(SoyAgaciGor.this, 0, intent, 0);
                            notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                            note = new Notification(R.mipmap.ic_launcher, "Bildirim", 5000);
                            note.setLatestEventInfo(SoyAgaciGor.this, "Bildirim başlığı", "Bildirim içeriği", pendingIntent);
                            notificationManager.notify("bildirimim", NOTIFY_ME_ID, note); */
            } else
                babaSonuc[i] = sonucAy + " " + getResources().getString(R.string.ay) + " "
                        + sonucGun + " " + getResources().getString(R.string.gun) + " " +
                        getResources().getString(R.string.kaldi);


        }

    }

    private void kizEvlatDogumGununeKalaniBul() {

        String dizim[] = new String[3];
        String gun = "", ay = "";


        int gunInt = 0, ayInt = 0;
        int sonucGun = 0, sonucAy = 0;

        int monthh = 0;
        int dayy = 0;



        for (int i = 0; i < tarihKiz.length; i++) {

            dizim = tarihKiz[i].split("/");


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
                kizEvlatSonuc[i] = getResources().getString(R.string.mesaj_bugun_dogum_gunu);
                         /*   ////////////////////////////////////////Bildirim
                            Intent intent = new Intent(getApplicationContext(), SoyAgaciGor.class);
                            pendingIntent = PendingIntent.getActivity(SoyAgaciGor.this, 0, intent, 0);
                            notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                            note = new Notification(R.mipmap.ic_launcher, "Bildirim", 5000);
                            note.setLatestEventInfo(SoyAgaciGor.this, "Bildirim başlığı", "Bildirim içeriği", pendingIntent);
                            notificationManager.notify("bildirimim", NOTIFY_ME_ID, note); */
            } else
                kizEvlatSonuc[i] = sonucAy + " " + getResources().getString(R.string.ay) + " "
                        + sonucGun + " " + getResources().getString(R.string.gun) + " " +
                        getResources().getString(R.string.kaldi);


        }
    }

    private void erkekEvlatDogumGununeKalaniBul() {

        String dizim[] = new String[3];
        String gun = "", ay = "";


        int gunInt = 0, ayInt = 0;
        int sonucGun = 0, sonucAy = 0;

        int monthh = 0;
        int dayy = 0;



        for (int i = 0; i < tarihErkek.length; i++) {

            dizim = tarihErkek[i].split("/");


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
                erkekEvlatSonuc[i] = getResources().getString(R.string.mesaj_bugun_dogum_gunu);
                         /*   ////////////////////////////////////////Bildirim
                            Intent intent = new Intent(getApplicationContext(), SoyAgaciGor.class);
                            pendingIntent = PendingIntent.getActivity(SoyAgaciGor.this, 0, intent, 0);
                            notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                            note = new Notification(R.mipmap.ic_launcher, "Bildirim", 5000);
                            note.setLatestEventInfo(SoyAgaciGor.this, "Bildirim başlığı", "Bildirim içeriği", pendingIntent);
                            notificationManager.notify("bildirimim", NOTIFY_ME_ID, note); */
            } else
                erkekEvlatSonuc[i] = sonucAy + " " + getResources().getString(R.string.ay) + " "
                        + sonucGun + " " + getResources().getString(R.string.gun) + " " +
                        getResources().getString(R.string.kaldi);


        }

    }

    private void dedeDogumTarihineKalaniBul() {

        String dizim[] = new String[3];
        String gun = "", ay = "";


        int gunInt = 0, ayInt = 0;
        int sonucGun = 0, sonucAy = 0;

        int monthh = 0;
        int dayy = 0;



        for (int i = 0; i < tarihDede.length; i++) {

            dizim = tarihDede[i].split("/");


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
                dedeSonuc[i] = getResources().getString(R.string.mesaj_bugun_dogum_gunu);
                         /*   ////////////////////////////////////////Bildirim
                            Intent intent = new Intent(getApplicationContext(), SoyAgaciGor.class);
                            pendingIntent = PendingIntent.getActivity(SoyAgaciGor.this, 0, intent, 0);
                            notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                            note = new Notification(R.mipmap.ic_launcher, "Bildirim", 5000);
                            note.setLatestEventInfo(SoyAgaciGor.this, "Bildirim başlığı", "Bildirim içeriği", pendingIntent);
                            notificationManager.notify("bildirimim", NOTIFY_ME_ID, note); */
            } else
                dedeSonuc[i] = sonucAy + " " + getResources().getString(R.string.ay) + " "
                        + sonucGun + " " + getResources().getString(R.string.gun) + " " +
                        getResources().getString(R.string.kaldi);


        }
    }

    private void buyukanneDogumTarihineKalaniBul() {


        String dizim[] = new String[3];
        String gun = "", ay = "";


        int gunInt = 0, ayInt = 0;
        int sonucGun = 0, sonucAy = 0;

        int monthh = 0;
        int dayy = 0;



        for (int i = 0; i < tarihNine.length; i++) {

            dizim = tarihNine[i].split("/");


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
                nineSonuc[i] = getResources().getString(R.string.mesaj_bugun_dogum_gunu);
                         /*   ////////////////////////////////////////Bildirim
                            Intent intent = new Intent(getApplicationContext(), SoyAgaciGor.class);
                            pendingIntent = PendingIntent.getActivity(SoyAgaciGor.this, 0, intent, 0);
                            notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                            note = new Notification(R.mipmap.ic_launcher, "Bildirim", 5000);
                            note.setLatestEventInfo(SoyAgaciGor.this, "Bildirim başlığı", "Bildirim içeriği", pendingIntent);
                            notificationManager.notify("bildirimim", NOTIFY_ME_ID, note); */
            } else
                nineSonuc[i] = sonucAy + " " + getResources().getString(R.string.ay) + " "
                        + sonucGun + " " + getResources().getString(R.string.gun) + " " +
                        getResources().getString(R.string.kaldi);


        }

    }


    private static String padding_str(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    private String[] sutunlarAnne = {"id","ozellik", "ad", "tarih"};
    private String[] sutunlar = {"ozellik", "ad", "tarih"};

    private void bilgileriGosterAnne() {


        Cursor okunanlar = cursorAnne();


        int index = 0;

        while (okunanlar.moveToNext()) {
            unvanAnne[index] = okunanlar.getString(okunanlar.getColumnIndex("ozellik"));
            adAnne[index] = okunanlar.getString(okunanlar.getColumnIndex("ad"));
            tarihAnne[index] = okunanlar.getString(okunanlar.getColumnIndex("tarih"));
            index++;
        }


    }


    private Cursor cursorAnne() {
        SQLiteDatabase db = v1.getReadableDatabase();
        Cursor okunanlar = db.query("anne", sutunlarAnne, null, null, null, null, null);
        return okunanlar;
    }

    private void bilgileriGosterBaba() {

        Cursor okunanlar = cursorBaba();


        int index = 0;

        while (okunanlar.moveToNext()) {
            unvanBaba[index] = okunanlar.getString(okunanlar.getColumnIndex("ozellik"));
            adBaba[index] = okunanlar.getString(okunanlar.getColumnIndex("ad"));
            tarihBaba[index] = okunanlar.getString(okunanlar.getColumnIndex("tarih"));
            index++;
        }

     /*   Cursor okunanlar1 = cursorBaba();

        String s[] = new String[okunanlar1.getCount()];

        String ozellik[] = new String[okunanlar1.getCount()];
        String ad[] = new String[okunanlar1.getCount()];
        String tarih[] = new String[okunanlar1.getCount()];

        int index = 0;

        while (okunanlar1.moveToNext()) {
            ozellik[index] = okunanlar1.getString(okunanlar1.getColumnIndex("ozellik"));
            ad[index] = okunanlar1.getString(okunanlar1.getColumnIndex("ad"));
            tarih[index] = okunanlar1.getString(okunanlar1.getColumnIndex("tarih"));
            index++;
        }

        for (int i = 0; i < ozellik.length; i++) {
            s[i] = ozellik[i] + ">>>" + ad[i] + "-" + tarih[i];
        }


        return s; */
    }

    private Cursor cursorBaba() {
        SQLiteDatabase db = v2.getReadableDatabase();
        Cursor okunanlar = db.query("baba", sutunlar, null, null, null, null, null);
        return okunanlar;
    }

    private void bilgileriGosterErkek() {


        Cursor okunanlar = cursorErkek();


        int index = 0;

        while (okunanlar.moveToNext()) {
            unvanErkek[index] = okunanlar.getString(okunanlar.getColumnIndex("ozellik"));
            adErkek[index] = okunanlar.getString(okunanlar.getColumnIndex("ad"));
            tarihErkek[index] = okunanlar.getString(okunanlar.getColumnIndex("tarih"));
            index++;
        }
    }

    private Cursor cursorErkek() {
        SQLiteDatabase db = v3.getReadableDatabase();
        Cursor okunanlar = db.query("erkek", sutunlar, null, null, null, null, null);
        return okunanlar;
    }

    private void bilgileriGosterKiz() {

        Cursor okunanlar = cursorKiz();

        int index = 0;

        while (okunanlar.moveToNext()) {
            unvanKiz[index] = okunanlar.getString(okunanlar.getColumnIndex("ozellik"));
            adKiz[index] = okunanlar.getString(okunanlar.getColumnIndex("ad"));
            tarihKiz[index] = okunanlar.getString(okunanlar.getColumnIndex("tarih"));
            index++;
        }
    }

    private Cursor cursorKiz() {
        SQLiteDatabase db = v4.getReadableDatabase();
        Cursor okunanlar = db.query("kiz", sutunlar, null, null, null, null, null);
        return okunanlar;
    }

    private void bilgileriGosterDede() {

        Cursor okunanlar = cursorDede();

        int index = 0;

        while (okunanlar.moveToNext()) {
            unvanDede[index] = okunanlar.getString(okunanlar.getColumnIndex("ozellik"));
            adDede[index] = okunanlar.getString(okunanlar.getColumnIndex("ad"));
            tarihDede[index] = okunanlar.getString(okunanlar.getColumnIndex("tarih"));
            index++;
        }
    }

    private Cursor cursorDede() {
        SQLiteDatabase db = v5.getReadableDatabase();
        Cursor okunanlar = db.query("dede", sutunlar, null, null, null, null, null);
        return okunanlar;
    }

    private void bilgileriGosterBuyukAnne() {

        Cursor okunanlar = cursorBuyukAnne();

        int index = 0;

        while (okunanlar.moveToNext()) {
            unvanNine[index] = okunanlar.getString(okunanlar.getColumnIndex("ozellik"));
            adNine[index] = okunanlar.getString(okunanlar.getColumnIndex("ad"));
            tarihNine[index] = okunanlar.getString(okunanlar.getColumnIndex("tarih"));
            index++;
        }
    }

    private Cursor cursorBuyukAnne() {
        SQLiteDatabase db = v6.getReadableDatabase();
        Cursor okunanlar = db.query("buyukanne", sutunlar, null, null, null, null, null);
        return okunanlar;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menum, menu);
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

        switch (id) {
            case R.id.VerileriTemizle:
               /* if(veritabaniniTemizle()){

                    finish();
                } */

        }

        return super.onOptionsItemSelected(item);
    }





   /* private boolean veritabaniniTemizle(){

        if (tvSoyAgaciGorSonuc.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),R.string.mesaj_zaten_silinmis,Toast.LENGTH_LONG).show();
        }

        else{
            SQLiteDatabase db1 = v1.getReadableDatabase();
            SQLiteDatabase db2=  v2.getReadableDatabase();
            SQLiteDatabase db3 = v3.getReadableDatabase();
            SQLiteDatabase db4 = v4.getReadableDatabase();
            SQLiteDatabase db5 = v5.getReadableDatabase();
            SQLiteDatabase db6 = v6.getReadableDatabase();

            Cursor  okunanlar1 = db1.query("anne", sutun, null, null, null, null, null);
            Cursor  okunanlar2 = db2.query("baba", sutun, null, null, null, null, null);
            Cursor  okunanlar3 = db3.query("kiz", sutun, null, null, null, null, null);
            Cursor  okunanlar4 = db4.query("erkek", sutun, null, null, null, null, null);
            Cursor  okunanlar5 = db5.query("dede", sutun, null, null, null, null, null);
            Cursor  okunanlar6 = db6.query("buyukanne", sutun, null, null, null, null, null);

            String ad="";



            while (okunanlar1.moveToNext()) {

                ad = okunanlar1.getString(okunanlar1.getColumnIndex("ad"));
                db1.delete("anne","ad"+"=?",new String[]{ad});


            }

            while (okunanlar2.moveToNext()) {


                ad = okunanlar2.getString(okunanlar2.getColumnIndex("ad"));
                db2.delete("baba","ad"+"=?",new String[]{ad});


            }

            while (okunanlar3.moveToNext()) {


                ad = okunanlar3.getString(okunanlar3.getColumnIndex("ad"));
                db3.delete("kiz","ad"+"=?",new String[]{ad});

            }

            while (okunanlar4.moveToNext()) {


                ad = okunanlar4.getString(okunanlar4.getColumnIndex("ad"));
                db4.delete("erkek","ad"+"=?",new String[]{ad});


            }

            while (okunanlar5.moveToNext()) {



                ad = okunanlar5.getString(okunanlar5.getColumnIndex("ad"));
                db5.delete("dede","ad"+"=?",new String[]{ad});

            }

            while (okunanlar6.moveToNext()) {


                ad = okunanlar6.getString(okunanlar6.getColumnIndex("ad"));
                db6.delete("buyukanne","ad"+"=?",new String[]{ad});

            }

            tvSoyAgaciGorSonuc.setText("");
            Toast.makeText(getApplicationContext(),R.string.mesaj_kayitlar_silindi,Toast.LENGTH_LONG).show();

        }


        return true;
    } */

}
