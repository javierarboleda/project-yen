package com.javierarboleda.projectyen.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.javierarboleda.projectyen.R;
import com.javierarboleda.projectyen.data.TodoItem;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created on 9/28/16.
 */
public class TodoAdapter extends RealmRecyclerViewAdapter<TodoItem, TodoAdapter.MyViewHolder> {

    private final TodoListActivity mActivity;

    public TodoAdapter(TodoListActivity activity, OrderedRealmCollection<TodoItem> data) {
        super(activity, data, true);
        mActivity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View todoItemView = inflater.inflate(R.layout.list_item_todo, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(todoItemView);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        TodoItem todoItem = getData().get(position);

        holder.data = todoItem;

        holder.titleTextView.setText(todoItem.getTitle());
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
            implements View.OnLongClickListener {

        public TextView titleTextView;
        public TodoItem data;

        public MyViewHolder(View itemView) {
            super(itemView);

            titleTextView = (TextView) itemView.findViewById(R.id.title_text_view);

            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View view) {
            mActivity.deleteTodoItem(data);
            return false;
        }
    }



}
