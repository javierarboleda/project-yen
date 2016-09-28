package com.javierarboleda.projectyen.data;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created on 9/27/16.
 */
public class TodoItem extends RealmObject {

    public static final String TITLE = "title";

    @Required
    private String title;

    private Date date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
