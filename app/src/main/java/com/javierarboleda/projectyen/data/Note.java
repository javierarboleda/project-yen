package com.javierarboleda.projectyen.data;

import io.realm.RealmObject;
import io.realm.annotations.Required;

/**
 * Created on 9/29/16.
 */
public class Note extends RealmObject {

    public static final String NOTETEXT = "noteText";

    @Required
    private String noteText;

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }
}
