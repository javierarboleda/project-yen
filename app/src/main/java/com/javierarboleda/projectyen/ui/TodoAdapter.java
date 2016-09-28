package com.javierarboleda.projectyen.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.javierarboleda.projectyen.R;
import com.javierarboleda.projectyen.data.TodoItem;

import java.util.List;

/**
 * Created on 9/28/16.
 */
public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private final Context mContext;
    private List<TodoItem> mTodoItems;

    public TodoAdapter(Context context, List<TodoItem> todoItems) {
        mContext = context;
        mTodoItems = todoItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View todoItemView = inflater.inflate(R.layout.list_item_todo, parent, false);

        return new ViewHolder(todoItemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TodoItem todoItem = mTodoItems.get(position);

        TextView titleTextView = holder.titleTextView;

        titleTextView.setText(todoItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return mTodoItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            titleTextView = (TextView) itemView.findViewById(R.id.title_text_view);
        }
    }



}
