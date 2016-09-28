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

import com.afollestad.materialdialogs.MaterialDialog;
import com.javierarboleda.projectyen.R;
import com.javierarboleda.projectyen.data.TodoItem;

import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmAsyncTask;

public class TodoListActivity extends AppCompatActivity {

    private final String TAG = TodoListActivity.class.getName();

    private RecyclerView mRecyclerView;
    private ArrayList<TodoItem> mTodoItems;
    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        mTodoItems = generateTodoItems(20);

        mRealm = Realm.getDefaultInstance();

        initRecyclerView();

        initFab();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }

    private ArrayList<TodoItem> generateTodoItems(int itemsCount) {

        ArrayList<TodoItem> todoItems = new ArrayList<>();

        for (int i = 0; i < itemsCount; i++) {
            TodoItem todoItem = new TodoItem();
            todoItem.setTitle("Test todo item");

            todoItems.add(todoItem);
        }

        return todoItems;
    }

    private void initRecyclerView() {
        RecyclerView todoRecyclerView = (RecyclerView) findViewById(R.id.todo_list_recycler_view);

        todoRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        TodoAdapter todoAdapter =
                new TodoAdapter(this, mRealm.where(TodoItem.class).findAllAsync());

        todoRecyclerView.setAdapter(todoAdapter);

        todoRecyclerView.setHasFixedSize(true);
    }

    private void initFab() {

        FloatingActionButton newTodoFab = (FloatingActionButton) findViewById(R.id.new_todo_fab);

        newTodoFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNewTodoDialog();
            }
        });

    }

    private void showNewTodoDialog() {

        new MaterialDialog.Builder(this)
                .title("Input todo item")
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input("", "", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        addTodoItemToDb(input.toString(), null);
                    }
                }).show();
    }

    private void addTodoItemToDb(final String title, final Date date) {

        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                TodoItem todoItem = realm.createObject(TodoItem.class);
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


    public void deleteTodoItem(TodoItem item) {
        final String id = item.getTitle();
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(TodoItem.class).equalTo(TodoItem.TITLE, id)
                        .findAll()
                        .deleteAllFromRealm();
            }
        });
    }
}
