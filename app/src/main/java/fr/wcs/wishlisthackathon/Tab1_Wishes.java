package fr.wcs.wishlisthackathon;

/**
 * Created by apprenti on 10/30/17.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import static fr.wcs.wishlisthackathon.R.layout.tab1_wishes;


public class Tab1_Wishes extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(tab1_wishes, container, false);
        return rootView;
    }
}
