package myclasses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ozan on 03.08.2015.
 */
public class VeriTabaniIslemleriDiger extends SQLiteOpenHelper{
    private static final String VERİTABANİ_ADİ="DigerKisiler";
    private static final int SURUM=1;

    public VeriTabaniIslemleriDiger(Context context){
        super(context,VERİTABANİ_ADİ,null,SURUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE digerleri(ad TEXT,tarih TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST digerleri");
        onCreate(db);
    }
}
