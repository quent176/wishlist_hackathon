package fr.wcs.wishlisthackathon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import static fr.wcs.wishlisthackathon.R.layout.tab3_tooffer;

/**
 * Created by apprenti on 10/30/17.
 */

public class Tab3_ToOffer extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(tab3_tooffer, container, false);

        return rootView;
    }
}
