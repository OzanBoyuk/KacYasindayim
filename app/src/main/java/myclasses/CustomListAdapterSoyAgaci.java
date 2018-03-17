package myclasses;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import oyun.ozan.kacyasindayim.R;

/**
 * Created by ozan on 05.02.2016.
 */
public class CustomListAdapterSoyAgaci extends ArrayAdapter<String> {

    private Activity context=null;
    String unvan[]=null;
    String adSoyad[]=null;
    String dogTarihi[]=null;
    String sonuc[]=null;


    public CustomListAdapterSoyAgaci(Activity context, String[] unvan , String[] adSoyad, String[] dogTarihi,String[] sonuc) {
        super(context, R.layout.listeyapisi_soyagaci,unvan);

        this.context = context;
        this.unvan=unvan;
        this.adSoyad = adSoyad;
        this.dogTarihi=dogTarihi;
        this.sonuc=sonuc;

    }

    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listeyapisi_soyagaci, null,true);

        TextView tvUnvanSoyAgaci = (TextView) rowView.findViewById(R.id.tvUnvanSoyagaci);
        TextView tvAdSoyadSoyAgaci = (TextView) rowView.findViewById(R.id.tvAdSoyadSoyAgaci);
        TextView tvDogTarihiSoyAgaci = (TextView) rowView.findViewById(R.id.tvDogTarihiSoyAgaci);
        TextView tvSonucSoyAgaci = (TextView) rowView.findViewById(R.id.tvSonucSoyAgaci);



        tvUnvanSoyAgaci.setText(unvan[position]);
        tvAdSoyadSoyAgaci.setText(adSoyad[position]);
        tvDogTarihiSoyAgaci.setText(dogTarihi[position]);
        tvSonucSoyAgaci.setText(sonuc[position]);

        

        return rowView;

    };
}
