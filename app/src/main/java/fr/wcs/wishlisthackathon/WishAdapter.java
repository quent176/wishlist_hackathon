package fr.wcs.wishlisthackathon;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
// import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
// import com.firebase.ui.storage.images.FirebaseImageLoader;


import java.util.List;

/**
 * Created by wilder on 30/10/17.
 */

public class WishAdapter extends RecyclerView.Adapter<WishAdapter.ViewHolder> {

    private Context context;
    public static List<ObjectModel> myList;
    public FirebaseStorage mStorage = FirebaseStorage.getInstance();

    public WishAdapter(Context context, List<ObjectModel> myList) {
        this.myList = myList;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        public ImageView imageView;
        public ProgressBar loadingProgressBar;

        public ViewHolder(final View itemView) {
            super(itemView);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(WishAdapter.ViewHolder holder, int position) {
        final ProgressBar progressBar = holder.loadingProgressBar;
        progressBar.setVisibility(View.VISIBLE);
        ObjectModel myObject = myList.get(position);
        String upload = myObject.getObject_image();
        StorageReference gsReference = mStorage.getReferenceFromUrl(upload);
        /*
        Glide.with(context)
                .using(new FirebaseImageLoader())
                .load(gsReference)
                .listener(new RequestListener<StorageReference, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, StorageReference model, Target<GlideDrawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        Log.d("tag", "fail loading Image");
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, StorageReference model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                //TODO Import image .placeholder(R.drawable.placeholder)
                .into(holder.imageView);
                */
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }
}
