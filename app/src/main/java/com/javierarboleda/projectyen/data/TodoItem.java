package com.javierarboleda.projectyen.data;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created on 9/27/16.
 */
public class TodoItem extends RealmObject {

    @Required
    private String mTitle;

    private Date mDate;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        this.mDate = date;
    }
}
