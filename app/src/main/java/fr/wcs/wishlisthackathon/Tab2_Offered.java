package fr.wcs.wishlisthackathon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import static fr.wcs.wishlisthackathon.R.layout.tab2_offered;


/**
 * Created by apprenti on 10/30/17.
 */

public class Tab2_Offered extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(tab2_offered, container, false);
        ImageView testPicasso = (ImageView) rootView.findViewById(R.id.imageViewTest1);

        Picasso.with(getContext()).load("http://i.imgur.com/DvpvklR.png").into(testPicasso);

        return rootView;
    }
}
