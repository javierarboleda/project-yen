package com.javierarboleda.projectyen.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.javierarboleda.projectyen.R;
import com.javierarboleda.projectyen.data.Note;
import com.javierarboleda.projectyen.data.TodoItem;
import com.javierarboleda.projectyen.util.Constants;
import com.javierarboleda.projectyen.util.Mode;
import com.javierarboleda.projectyen.util.RealmUtil;

import org.w3c.dom.Text;

import java.util.Date;

import io.realm.Realm;

public class TodoDetailsEditActivity extends AppCompatActivity {

    private final String TAG = TodoDetailsEditActivity.class.getName();

    private Realm mRealm;
    private TodoItem mTodoItem;

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
            mTodoItem = mRealm.where(TodoItem.class)
                    .equalTo(TodoItem.TITLE, title).findFirst();
            initRecyclerView();
        }
        else {
            new MaterialDialog.Builder(this)
                    .title(R.string.new_todo_item_dialog)
                    .inputType(InputType.TYPE_CLASS_TEXT)
                    .input("", "", new MaterialDialog.InputCallback() {
                        @Override
                        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                            addTodoItemToDb(input.toString(), null);
                        }
                    }).show();
        }

        initFab();
    }

    private void initFab() {

        FloatingActionButton newNoteFab = (FloatingActionButton) findViewById(R.id.new_note_fab);

        newNoteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNewNoteDialog();
            }
        });
    }

    private void showNewNoteDialog() {

        new MaterialDialog.Builder(this)
                .title(R.string.input_note_text)
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input("", "", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        mRealm.beginTransaction();
                        Note newNote = new Note();
                        newNote.setNoteText(input.toString());
                        Note note = mRealm.copyToRealm(newNote);
                        mTodoItem.getNotes().add(note);
                        mRealm.commitTransaction();
                    }
                }).show();

    }

    private void initRecyclerView() {
        RecyclerView noteRecyclerView =
                (RecyclerView) findViewById(R.id.todo_details_notes_recycler_view);

        noteRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        NoteAdapter noteAdapter = new NoteAdapter(this, mTodoItem.getNotes());

        noteRecyclerView.setAdapter(noteAdapter);

        noteRecyclerView.setHasFixedSize(true);
    }

    public void editTitle(View view) {

        final String title = ((TextView) view).getText().toString();

        new MaterialDialog.Builder(this)
                .title(R.string.edit_todo_name)
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input("", "", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        updateTodoItemInDb(input.toString());
                    }
                }).show();
    }

    private void updateTodoItemInDb(final String updatedTitle) {

        final String title = mTodoItem.getTitle();

        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                TodoItem todoItem = bgRealm.where(TodoItem.class)
                        .equalTo(TodoItem.TITLE, title).findFirst();

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

    private void addTodoItemToDb(final String title, final Date date) {

        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                TodoItem todoItem = bgRealm.createObject(TodoItem.class);
                todoItem.setTitle(title);
                todoItem.setDate(date);
                mTodoItem = todoItem;
                initRecyclerView();
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "New todoItem added to db: title = " + title + ", date = " + date);
                updateTitleTextView(title);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e(TAG, "Error when adding new todoItem to db: " + error.getMessage());
            }
        });
    }

    private void updateTitleTextView(String text) {
        TextView titleTextView = (TextView) findViewById(R.id.details_title_text_view);
        titleTextView.setText(text);
    }

    public void editDate(View view) {



    }
}
