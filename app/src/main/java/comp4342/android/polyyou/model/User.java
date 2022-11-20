package comp4342.android.polyyou.model;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String id;
    private String email;
    private String headImage;

    public User() {
        this.name = null;
        id = null; email = null; headImage = null;
    }

    public User(String name, String id, String email, String headImage) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.headImage = headImage;
    }

    public void updateUser(String[] content) {
        this.name = content[0];
        this.id = content[1];
        this.email = content[2];
        this.headImage = content[3];
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setEmail(String email) { this.email = email; }

    public String getEmail() { return this.email; }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getHeadImage() {
        return this.headImage;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", headImage='" + headImage + '\'' +
                '}';
    }

    public String toString1() {
        return name + " " + id + " " + email + " " + headImage;
    }
}
