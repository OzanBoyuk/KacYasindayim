package oyun.ozan.kacyasindayim.navigation.fragments;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import oyun.ozan.kacyasindayim.R;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DefaultFragment extends Fragment {

    private View view=null;


    public DefaultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_default, container, false);

        return view;
    }


}
