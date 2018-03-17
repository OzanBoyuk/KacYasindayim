package myclasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ozan on 19.02.2016.
 */
public class VeritabaniIslemleri extends SQLiteOpenHelper {

    private static final String VERİTABANİ_ADİ="SoyAgaci6";
    private static final int SURUM=1;
    //private static String ID_KOLONU="id";
    private static String OZELLIK_KOLONU="ozellik";
    private static String AD_KOLONU="ad";
    private static String TARIH_KOLONU="tarih";

    private static final String TABLE_ANNE = "anne";
    private static final String TABLE_BABA = "baba";
    private static final String TABLE_ERKEK = "erkek";
    private static final String TABLE_KIZ = "kiz";
    private static final String TABLE_DEDE = "dede";
    private static final String TABLE_BUYUKANNE = "buyukanne";




    public VeritabaniIslemleri(Context context){
        super(context,VERİTABANİ_ADİ,null,SURUM);
    }

    ////Bu alandaki işlmeler yalnızca bir defa yapılır

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+TABLE_ANNE+"(id INTEGER PRIMARY KEY AUTOINCREMENT,ozellik TEXT,ad TEXT,tarih TEXT);");
        db.execSQL("CREATE TABLE "+TABLE_BABA+"(id INTEGER PRIMARY KEY AUTOINCREMENT,ozellik TEXT,ad TEXT,tarih TEXT);");
        db.execSQL("CREATE TABLE "+TABLE_ERKEK+"(id INTEGER PRIMARY KEY AUTOINCREMENT,ozellik TEXT,ad TEXT,tarih TEXT);");
        db.execSQL("CREATE TABLE "+TABLE_KIZ+"(id INTEGER PRIMARY KEY AUTOINCREMENT,ozellik TEXT,ad TEXT,tarih TEXT);");
        db.execSQL("CREATE TABLE "+TABLE_DEDE+"(id INTEGER PRIMARY KEY AUTOINCREMENT,ozellik TEXT,ad TEXT,tarih TEXT);");
        db.execSQL("CREATE TABLE "+TABLE_BUYUKANNE+"(id INTEGER PRIMARY KEY AUTOINCREMENT,ozellik TEXT,ad TEXT,tarih TEXT);");
    }
    ////////////////////////////////////

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXIST anne");
        db.execSQL("DROP TABLE IF EXIST baba");
        db.execSQL("DROP TABLE IF EXIST erkek");
        db.execSQL("DROP TABLE IF EXIST kiz");
        db.execSQL("DROP TABLE IF EXIST dede");
        db.execSQL("DROP TABLE IF EXIST buyukanne");
        onCreate(db);
    }


    public void veriTabaninaEkle(String eklenecekTablo,String ozellik,String ad, String tarih){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(OZELLIK_KOLONU, ozellik);
        values.put(AD_KOLONU, ad);
        values.put(TARIH_KOLONU, tarih);

        db.insertOrThrow(eklenecekTablo, null, values);
        db.close(); //Database Bağlantısını kapattık*/

    }

    /*public ArrayList<HashMap<String, String>> tabloyuListele(String TABLE_NAME){

        //Bu methodda ise tablodaki tüm değerleri alıyoruz
        //ArrayList adı üstünde Array lerin listelendiği bir Array.Burda hashmapleri listeleyeceğiz
        //Herbir satırı değer ve value ile hashmap a atıyoruz. Her bir satır 1 tane hashmap arrayı demek.
        //olusturdugumuz tüm hashmapleri ArrayList e atıp geri dönüyoruz(return).

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                for(int i=0; i<cursor.getColumnCount();i++)
                {
                    map.put(cursor.getColumnName(i), cursor.getString(i));
                }

                dataList.add(map);
            } while (cursor.moveToNext());
        }
        db.close();
        // return kitap liste
        return dataList;
    }

    public void veriTabanindanSil(String TABLE_NAME,int id){ //id si belli olan row u silmek için

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, id + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    } */

}
