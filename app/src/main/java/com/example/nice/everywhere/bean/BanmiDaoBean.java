package com.example.nice.everywhere.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class BanmiDaoBean {

    @Id
    private Long lid;
    private int following;
    private String name;
    private String location;
    private String occupation;
    private String photo;
    @Generated(hash = 1058337992)
    public BanmiDaoBean(Long lid, int following, String name, String location,
            String occupation, String photo) {
        this.lid = lid;
        this.following = following;
        this.name = name;
        this.location = location;
        this.occupation = occupation;
        this.photo = photo;
    }
    @Generated(hash = 1449961721)
    public BanmiDaoBean() {
    }
    public Long getLid() {
        return this.lid;
    }
    public void setLid(Long lid) {
        this.lid = lid;
    }
    public int getFollowing() {
        return this.following;
    }
    public void setFollowing(int following) {
        this.following = following;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLocation() {
        return this.location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getOccupation() {
        return this.occupation;
    }
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
    public String getPhoto() {
        return this.photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
