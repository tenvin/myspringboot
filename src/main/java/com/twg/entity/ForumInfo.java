package com.twg.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by tenvin on 2017/6/28.
 */
@Entity
public class ForumInfo {
    @Id
    @GeneratedValue
    private Long id;

    private String userame;

    private String password;

    private String url;

    public ForumInfo() {
    }

    public String getUserame() {
        return userame;
    }

    public void setUserame(String userame) {
        this.userame = userame;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
