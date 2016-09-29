package com.javierarboleda.projectyen.ui;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.javierarboleda.projectyen.R;
import com.javierarboleda.projectyen.data.TodoItem;
import com.javierarboleda.projectyen.util.Constants;
import com.javierarboleda.projectyen.util.Mode;

import org.w3c.dom.Text;

import io.realm.Realm;

public class TodoDetailsEditActivity extends AppCompatActivity {

    private final String TAG = TodoDetailsEditActivity.class.getName();

    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_details_edit);

        mRealm = Realm.getDefaultInstance();

        Mode mode = (Mode) getIntent().getSerializableExtra(Constants.MODE);

        if (mode == Mode.DETAILS) {
            String title = getIntent().getStringExtra(Constants.TITLE);
            TextView titleTextView = (TextView) findViewById(R.id.details_title_text_view);
            titleTextView.setText(title);
        }
    }


    public void editTitle(View view) {

        final String title = ((TextView) view).getText().toString();

        new MaterialDialog.Builder(this)
                .title(R.string.new_todo_item_dialog)
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input("", "", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        updateTodoItemInDb(title, input.toString());
                    }
                }).show();
    }

    private void updateTodoItemInDb(final String originalTitle, final String updatedTitle) {

        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                TodoItem todoItem = realm.where(TodoItem.class)
                        .equalTo(TodoItem.TITLE, originalTitle).findFirst();
                todoItem.setTitle(updatedTitle);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "TodoItem title edited to db: new title = " + updatedTitle);
                updateTitleTextView(updatedTitle);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e(TAG, "Error when updating edited title to db: " + error.getMessage());
            }
        });
    }

    private void updateTitleTextView(String text) {
        TextView titleTextView = (TextView) findViewById(R.id.details_title_text_view);
        titleTextView.setText(text);
    }

}
