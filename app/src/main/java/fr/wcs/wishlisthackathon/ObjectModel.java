package fr.wcs.wishlisthackathon;

/**
 * Created by apprenti on 10/30/17.
 */

public class ObjectModel {

    private String object_description;
    private String object_image;
    boolean object_offered;
    String object_user_id;
    String pigeon_user_id;

    public ObjectModel() {
        // Needed for firebase
    }

    public ObjectModel(String object_description, String object_image, boolean object_offered, String object_user_id, String pigeon_user_id) {
        this.object_description = object_description;
        this.object_image = object_image;
        this.object_offered = object_offered;
        this.object_user_id = object_user_id;
        this.pigeon_user_id = pigeon_user_id;
    }

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
}
