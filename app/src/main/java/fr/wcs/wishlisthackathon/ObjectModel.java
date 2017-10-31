package fr.wcs.wishlisthackathon;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by apprenti on 10/30/17.
 */

public class ObjectModel implements Parcelable {

    private String object_description;
    private String object_image;
    private String object_url;
    private boolean object_offered;
    private String object_user_id;
    private String object_user_name;
    private String pigeon_user_id;

    public ObjectModel() {
        // Needed for firebase
    }

    public ObjectModel(String object_description, String object_image, String object_url, boolean object_offered, String object_user_id, String object_user_name, String pigeon_user_id) {
        this.object_description = object_description;
        this.object_image = object_image;
        this.object_url = object_url;
        this.object_offered = object_offered;
        this.object_user_id = object_user_id;
        this.object_user_name = object_user_name;
        this.pigeon_user_id = pigeon_user_id;
    }

    public static final Creator<ObjectModel> CREATOR = new Creator<ObjectModel>() {
        @Override
        public ObjectModel createFromParcel(Parcel in) {
            return new ObjectModel(in);
        }

        @Override
        public ObjectModel[] newArray(int size) {
            return new ObjectModel[size];
        }
    };

    public String getObject_description() {
        return object_description;
    }

    public void setObject_description(String object_description) {
        this.object_description = object_description;
    }

    public String getObject_image() {
        return object_image;
    }

    public void setObject_image(String object_image) {
        this.object_image = object_image;
    }

    public String getObject_url() {
        return object_url;
    }

    public void setObject_url(String object_url) {
        this.object_url = object_url;
    }


    public boolean isObject_offered() {
        return object_offered;
    }

    public void setObject_offered(boolean object_offered) {
        this.object_offered = object_offered;
    }

    public String getObject_user_id() {
        return object_user_id;
    }

    public void setObject_user_id(String object_user_id) {
        this.object_user_id = object_user_id;
    }

    public String getPigeon_user_id() {
        return pigeon_user_id;
    }

    public void setPigeon_user_id(String pigeon_user_id) {
        this.pigeon_user_id = pigeon_user_id;
    }

    public String getObject_user_name() {
        return object_user_name;
    }

    public void setObject_user_name(String object_user_name) {
        this.object_user_name = object_user_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(object_description);
        parcel.writeString(object_image);
        parcel.writeString(object_url);
        parcel.writeByte((byte) (object_offered ? 1 : 0));
        parcel.writeString(object_user_id);
        parcel.writeString(object_user_name);
        parcel.writeString(pigeon_user_id);
    }

    private ObjectModel(Parcel in) {
        object_description  = in.readString();
        object_image  = in.readString();
        object_url  = in.readString();
        object_offered  = in.readByte() != 0;
        object_user_id = in.readString();
        object_user_name = in.readString();
        pigeon_user_id = in.readString();
    }
}
