package com.javierarboleda.projectyen.util;

import android.util.Log;

import com.javierarboleda.projectyen.data.TodoItem;

import java.util.Date;

import io.realm.Realm;

/**
 * Created on 9/29/16.
 */
public class RealmUtil {

    private static final String TAG = RealmUtil.class.getName();

    public static void addTodoItemToDb(Realm realm, final String title, final Date date) {

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                TodoItem todoItem = bgRealm.createObject(TodoItem.class);
                todoItem.setTitle(title);
                todoItem.setDate(date);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "New todoItem added to db: title = " + title + ", date = " + date);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e(TAG, "Error when adding new todoItem to db: " + error.getMessage());
            }
        });
    }

    public static void updateTodoItemInDb(Realm realm, final String originalTitle,
                                    final String updatedTitle) {

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                TodoItem todoItem = bgRealm.where(TodoItem.class)
                        .equalTo(TodoItem.TITLE, originalTitle).findFirst();
                todoItem.setTitle(updatedTitle);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "TodoItem title edited to db: new title = " + updatedTitle);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e(TAG, "Error when updating edited title to db: " + error.getMessage());
            }
        });
    }
}
