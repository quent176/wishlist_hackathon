package fr.wcs.wishlisthackathon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static fr.wcs.wishlisthackathon.R.layout.tab4_friendslists;

public class Tab4_FriendsLists extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(tab4_friendslists, container, false);

        return rootView;
    }
}
