package comp4342.android.polyyou.model;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String id;
    private String headImage;
    private String password;

    public User(String name, String id) {
        this.name = name;
        this.id = id;
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

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getHeadImage() {
        return this.headImage;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", headImage='" + headImage + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
