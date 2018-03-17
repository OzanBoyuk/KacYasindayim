package oyun.ozan.kacyasindayim;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Calendar;

import myclasses.VeritabaniIslemleri;

public class SoyAgaciEkle extends AppCompatActivity {

    /*private VeritabaniIslemleri v1;
    private VeritabaniIslemleri v2;
    private VeritabaniIslemleri v3;
    private VeritabaniIslemleri v4;
    private VeritabaniIslemleri v5;
    private VeritabaniIslemleri v6;*/

    VeritabaniIslemleri vt=null;

    private Spinner spinner;
    private Button btnDogumTarihi,btnEkle;
    private TextView tvDogumTarihi;
    private EditText etGirilen;


    private int buYilinGunu=0,dogumGunu=0;
    private int buYilinAyi=0,dogumAyi=0;
    private int buYilinYili=0,dogumYili=0;

    private String secilen="";
    private String baslangicSpinnerDegeri="";

    private Typeface font;


    static final int DATE_DIALOG_ID = 999;


  //  private StartAppAd startAppAd = new StartAppAd(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soy_agaci_ekle);

        ///StartApp
        //StartAppSDK.init(this, "211407729", true);

        /////ADMOB
        /////ADMOB banner
        AdView mAdView = (AdView) findViewById(R.id.adViewSoyAgaciEkle);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        ////
        /////

        // font = Typeface.createFromAsset(getAssets(), "font/Gecko_PersonalUseOnly.ttf");

       /* v1=new VeritabaniIslemleri(this);
        v2=new VeritabaniIslemleri(this);
        v3=new VeritabaniIslemleri(this);
        v4=new VeritabaniIslemleri(this);
        v5=new VeritabaniIslemleri(this);
        v6=new VeritabaniIslemleri(this); */


        spinner=(Spinner)findViewById(R.id.spinner);
        btnDogumTarihi=(Button)findViewById(R.id.btnDogumTarihi);
        btnEkle=(Button)findViewById(R.id.btnEkle);

        tvDogumTarihi=(TextView)findViewById(R.id.tvDogumTarihi);



        etGirilen=(EditText)findViewById(R.id.etGirilen);



        btnDogumTarihi.setOnClickListener(new DogumTarihiListener());
        btnEkle.setOnClickListener(new EkleListener());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.spinnerElemanlar,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new OnItemSelectedListenerS());

        final Calendar c = Calendar.getInstance();
        buYilinYili = c.get(Calendar.YEAR);
        buYilinAyi = c.get(Calendar.MONTH)+1;
        buYilinGunu = c.get(Calendar.DAY_OF_MONTH);

        //startAppAd.showAd(); // show the ad
        // startAppAd.loadAd(); // load the next ad


    }


    private class DogumTarihiListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            showDialog(DATE_DIALOG_ID);

        }
    }

    //////////////////////////////////////-----DatePicker
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, datePickerListener, buYilinYili,buYilinAyi, buYilinGunu);


        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener =  new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int selectedYear, int monthOfYear, int dayOfMonth)
        {
            dogumYili = selectedYear;
            dogumAyi = monthOfYear+1;
            dogumGunu = dayOfMonth;

            tvDogumTarihi.setText(new StringBuilder().append(padding_str(dogumGunu))
                    .append("/").append(padding_str(dogumAyi+1))
                    .append("/").append(padding_str(dogumYili+1)));


        }
    };


    /////////////////////////////////////////////////////////////

    private static String padding_str(int c)
    {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    /////////////////////////////////////////////////////////////////////7

    private class EkleListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            vt = new VeritabaniIslemleri(getApplicationContext());

            if (baslangicSpinnerDegeri.equals("Seç") || secilen.equals("Choose")) {
                Toast.makeText(getApplicationContext(), R.string.mesaj_unvan_sec, Toast.LENGTH_LONG).show();

            } else if (etGirilen.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), R.string.mesaj_isim_gir, Toast.LENGTH_LONG).show();

            } else if (tvDogumTarihi.getText().toString().equals("Doğum Tarihi")||
                    tvDogumTarihi.getText().toString().equals("Date of Birth") ) {
                Toast.makeText(getApplicationContext(), R.string.mesaj_tarih_sec, Toast.LENGTH_LONG).show();

            } else {
                if (secilen.equals("Anne") || secilen.equals("Mother")) {

                    if(dogumYili > buYilinYili ){
                        Toast.makeText(getApplicationContext(), R.string.yil_ilerde, Toast.LENGTH_LONG).show();
                    }else if(dogumYili == buYilinYili){
                        if(dogumAyi > buYilinAyi){
                            Toast.makeText(getApplicationContext(), R.string.yil_ilerde, Toast.LENGTH_LONG).show();
                        }else if(dogumAyi == buYilinAyi){
                            if (dogumGunu > buYilinGunu) {
                                Toast.makeText(getApplicationContext(), R.string.yil_ilerde, Toast.LENGTH_LONG).show();
                            }else{
                                vt.veriTabaninaEkle("anne",secilen, "  " + etGirilen.getText().toString(), tvDogumTarihi.getText().toString());
                                Toast.makeText(getApplicationContext(), R.string.mesaj_eklendi, Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }else{
                            vt.veriTabaninaEkle("anne",secilen, "  " + etGirilen.getText().toString(), tvDogumTarihi.getText().toString());
                            Toast.makeText(getApplicationContext(), R.string.mesaj_eklendi, Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }else {
                        vt.veriTabaninaEkle("anne",secilen, "  " + etGirilen.getText().toString(), tvDogumTarihi.getText().toString());
                        Toast.makeText(getApplicationContext(), R.string.mesaj_eklendi, Toast.LENGTH_LONG).show();
                        finish();
                    }


                } else if (secilen.equals("Baba") || secilen.equals("Father")) {
                    if(dogumYili > buYilinYili ){
                        Toast.makeText(getApplicationContext(), R.string.yil_ilerde, Toast.LENGTH_LONG).show();
                    }else if(dogumYili == buYilinYili){
                        if(dogumAyi > buYilinAyi){
                            Toast.makeText(getApplicationContext(), R.string.yil_ilerde, Toast.LENGTH_LONG).show();
                        }else if(dogumAyi == buYilinAyi){
                            if (dogumGunu > buYilinGunu) {
                                Toast.makeText(getApplicationContext(), R.string.yil_ilerde, Toast.LENGTH_LONG).show();
                            }else{
                                vt.veriTabaninaEkle("baba",secilen, "  " + etGirilen.getText().toString(), tvDogumTarihi.getText().toString());
                                Toast.makeText(getApplicationContext(), R.string.mesaj_eklendi, Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }else{
                            vt.veriTabaninaEkle("baba",secilen, "  " + etGirilen.getText().toString(), tvDogumTarihi.getText().toString());
                            Toast.makeText(getApplicationContext(), R.string.mesaj_eklendi, Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }else {
                        vt.veriTabaninaEkle("baba",secilen, "  " + etGirilen.getText().toString(), tvDogumTarihi.getText().toString());
                        Toast.makeText(getApplicationContext(), R.string.mesaj_eklendi, Toast.LENGTH_LONG).show();
                        finish();
                    }
                } else if (secilen.equals("Kız Evlat") || secilen.equals("Daughter")) {

                    if(dogumYili > buYilinYili ){
                        Toast.makeText(getApplicationContext(), R.string.yil_ilerde, Toast.LENGTH_LONG).show();
                    }else if(dogumYili == buYilinYili){
                        if(dogumAyi > buYilinAyi){
                            Toast.makeText(getApplicationContext(), R.string.yil_ilerde, Toast.LENGTH_LONG).show();
                        }else if(dogumAyi == buYilinAyi){
                            if (dogumGunu > buYilinGunu) {
                                Toast.makeText(getApplicationContext(), R.string.yil_ilerde, Toast.LENGTH_LONG).show();
                            }else{
                                vt.veriTabaninaEkle("kiz",secilen, "  " + etGirilen.getText().toString(), tvDogumTarihi.getText().toString());
                                Toast.makeText(getApplicationContext(), R.string.mesaj_eklendi, Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }else{
                            vt.veriTabaninaEkle("kiz",secilen, "  " + etGirilen.getText().toString(), tvDogumTarihi.getText().toString());
                            Toast.makeText(getApplicationContext(), R.string.mesaj_eklendi, Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }else {
                        vt.veriTabaninaEkle("kiz",secilen, "  " + etGirilen.getText().toString(), tvDogumTarihi.getText().toString());
                        Toast.makeText(getApplicationContext(), R.string.mesaj_eklendi, Toast.LENGTH_LONG).show();
                        finish();
                    }
                } else if (secilen.equals("Erkek Evlat") || secilen.equals("Son")) {

                    if(dogumYili > buYilinYili ){
                        Toast.makeText(getApplicationContext(), R.string.yil_ilerde, Toast.LENGTH_LONG).show();
                    }else if(dogumYili == buYilinYili){
                        if(dogumAyi > buYilinAyi){
                            Toast.makeText(getApplicationContext(), R.string.yil_ilerde, Toast.LENGTH_LONG).show();
                        }else if(dogumAyi == buYilinAyi){
                            if (dogumGunu > buYilinGunu) {
                                Toast.makeText(getApplicationContext(), R.string.yil_ilerde, Toast.LENGTH_LONG).show();
                            }else{
                                vt.veriTabaninaEkle("erkek",secilen, "  " + etGirilen.getText().toString(), tvDogumTarihi.getText().toString());
                                Toast.makeText(getApplicationContext(), R.string.mesaj_eklendi, Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }else{
                            vt.veriTabaninaEkle("erkek",secilen, "  " + etGirilen.getText().toString(), tvDogumTarihi.getText().toString());
                            Toast.makeText(getApplicationContext(), R.string.mesaj_eklendi, Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }else {
                        vt.veriTabaninaEkle("erkek",secilen, "  " + etGirilen.getText().toString(), tvDogumTarihi.getText().toString());
                        Toast.makeText(getApplicationContext(), R.string.mesaj_eklendi, Toast.LENGTH_LONG).show();
                        finish();
                    }
                } else if (secilen.equals("Dede") || secilen.equals("Grandfather")) {

                    if(dogumYili > buYilinYili ){
                        Toast.makeText(getApplicationContext(), R.string.yil_ilerde, Toast.LENGTH_LONG).show();
                    }else if(dogumYili == buYilinYili){
                        if(dogumAyi > buYilinAyi){
                            Toast.makeText(getApplicationContext(), R.string.yil_ilerde, Toast.LENGTH_LONG).show();
                        }else if(dogumAyi == buYilinAyi){
                            if (dogumGunu > buYilinGunu) {
                                Toast.makeText(getApplicationContext(), R.string.yil_ilerde, Toast.LENGTH_LONG).show();
                            }else{
                                vt.veriTabaninaEkle("dede",secilen, "  " + etGirilen.getText().toString(), tvDogumTarihi.getText().toString());
                                Toast.makeText(getApplicationContext(), R.string.mesaj_eklendi, Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }else{
                            vt.veriTabaninaEkle("dede",secilen, "  " + etGirilen.getText().toString(), tvDogumTarihi.getText().toString());
                            Toast.makeText(getApplicationContext(), R.string.mesaj_eklendi, Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }else {
                        vt.veriTabaninaEkle("dede",secilen, "  " + etGirilen.getText().toString(), tvDogumTarihi.getText().toString());
                        Toast.makeText(getApplicationContext(), R.string.mesaj_eklendi, Toast.LENGTH_LONG).show();
                        finish();
                    }
                } else if (secilen.equals("Büyükanne") || secilen.equals("Grandmother")) {

                    if(dogumYili > buYilinYili ){
                        Toast.makeText(getApplicationContext(), R.string.yil_ilerde, Toast.LENGTH_LONG).show();
                    }else if(dogumYili == buYilinYili){
                        if(dogumAyi > buYilinAyi){
                            Toast.makeText(getApplicationContext(), R.string.yil_ilerde, Toast.LENGTH_LONG).show();
                        }else if(dogumAyi == buYilinAyi){
                            if (dogumGunu > buYilinGunu) {
                                Toast.makeText(getApplicationContext(), R.string.yil_ilerde, Toast.LENGTH_LONG).show();
                            }else{
                                vt.veriTabaninaEkle("buyukanne",secilen, "  " + etGirilen.getText().toString(), tvDogumTarihi.getText().toString());
                                Toast.makeText(getApplicationContext(), R.string.mesaj_eklendi, Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }else{
                            vt.veriTabaninaEkle("buyukanne",secilen, "  " + etGirilen.getText().toString(), tvDogumTarihi.getText().toString());
                            Toast.makeText(getApplicationContext(), R.string.mesaj_eklendi, Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }else {
                        vt.veriTabaninaEkle("buyukanne",secilen, "  " + etGirilen.getText().toString(), tvDogumTarihi.getText().toString());
                        Toast.makeText(getApplicationContext(), R.string.mesaj_eklendi, Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            }

            //}
        }
    }



   /* private void ekleAnne(String ozellik,String ad, String tarih) {

        SQLiteDatabase db = v1.getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        cv1.put("ozellik",ozellik);
        cv1.put("ad",ad);
        cv1.put("tarih",tarih);

        db.insertOrThrow("anne", null, cv1);

    }

    private void ekleBaba(String ozellik,String ad, String tarih) {

        SQLiteDatabase db = v2.getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        cv1.put("ozellik",ozellik);
        cv1.put("ad",ad);
        cv1.put("tarih",tarih);

        db.insertOrThrow("baba", null, cv1);

    }

    private void ekleErkek(String ozellik,String ad, String tarih) {

        SQLiteDatabase db = v3.getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        cv1.put("ozellik",ozellik);
        cv1.put("ad",ad);
        cv1.put("tarih",tarih);

        db.insertOrThrow("erkek", null, cv1);

    }

    private void ekleKiz(String ozellik,String ad, String tarih) {

        SQLiteDatabase db = v4.getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        cv1.put("ozellik",ozellik);
        cv1.put("ad",ad);
        cv1.put("tarih",tarih);

        db.insertOrThrow("kiz", null, cv1);

    }

    private void ekleDede(String ozellik,String ad, String tarih) {

        SQLiteDatabase db = v5.getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        cv1.put("ozellik",ozellik);
        cv1.put("ad",ad);
        cv1.put("tarih",tarih);

        db.insertOrThrow("dede", null, cv1);

    }

    private void ekleBuyukAnne(String ozellik,String ad, String tarih) {

        SQLiteDatabase db = v6.getWritableDatabase();
        ContentValues cv1 = new ContentValues();
        cv1.put("ozellik",ozellik);
        cv1.put("ad",ad);
        cv1.put("tarih",tarih);

        db.insertOrThrow("buyukanne", null, cv1);

    } */

    /////////////////////////////////////////////////////Spinner

    public class OnItemSelectedListenerS implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            secilen = parent.getItemAtPosition(position).toString();
            baslangicSpinnerDegeri=secilen = parent.getItemAtPosition(position).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
    //////////////////////////////////////////////////




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_soy_agaci, menu);
        return true;
    }







    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
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
