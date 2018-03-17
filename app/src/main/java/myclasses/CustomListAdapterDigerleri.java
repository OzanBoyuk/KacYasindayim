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
public class CustomListAdapterDigerleri extends ArrayAdapter<String> {

    private Activity context=null;
    String adSoyad[]=null;
    String dogTarihi[]=null;
    String bilgi[]=null;


    public CustomListAdapterDigerleri(Activity context, String[] adSoyad , String[] dogTarihi, String[] bilgi) {
        super(context, R.layout.listeyapisi_digerleri,adSoyad);

        this.context = context;
        this.adSoyad = adSoyad;
        this.dogTarihi=dogTarihi;
        this.bilgi=bilgi;

    }

    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listeyapisi_digerleri, null,true);

        TextView tvAdSoyadDigGor = (TextView) rowView.findViewById(R.id.tvAdSoyadDigGor);
        TextView tvDogTarihiDigGor = (TextView) rowView.findViewById(R.id.tvDogTarihiDigGor);
        TextView tvBilgiDigGor = (TextView) rowView.findViewById(R.id.tvBilgiDigGor);


        tvAdSoyadDigGor.setText(adSoyad[position]);
        tvDogTarihiDigGor.setText(dogTarihi[position]);
        tvBilgiDigGor.setText(bilgi[position]);

        return rowView;

    };
}
