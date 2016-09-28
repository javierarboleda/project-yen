package com.javierarboleda.projectyen.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.javierarboleda.projectyen.R;
import com.javierarboleda.projectyen.data.TodoItem;

import java.util.ArrayList;

public class TodoListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<TodoItem> mTodoItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        mTodoItems = generateTodoItems(20);

        initRecyclerView();

        initFab();


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

        TodoAdapter todoAdapter = new TodoAdapter(this, mTodoItems);

        todoRecyclerView.setAdapter(todoAdapter);

        todoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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

                    }
                }).show();
    }


}
