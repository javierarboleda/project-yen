package com.javierarboleda.projectyen.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.javierarboleda.projectyen.R;
import com.javierarboleda.projectyen.data.TodoItem;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/*
 * Copyright 2016 Realm Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Adopted from  http://nemanjakovacevic.net/blog/english/2016/01/12/recyclerview-swipe-to-delete-no-3rd-party-lib-necessary/
 */

/**
 * Created on 9/28/16.
 *
 * class 'borrowed' from:
 * https://github.com/realm/realm-android-adapters/blob/03a978fafc3c01f1c690f0bbb92058cd46d3ad2c/
   example/src/main/java/io/realm/examples/adapters/ui/recyclerview/
   RecyclerViewExampleActivity.java#L80
 *
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

        final TodoItem todoItem = getData().get(position);

        holder.data = todoItem;

        holder.titleTextView.setText(todoItem.getTitle());

//        holder.titleTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mActivity.openEditItemScreen(todoItem.getTitle());
//            }
//        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
            implements View.OnLongClickListener, View.OnClickListener {

        public TextView titleTextView;
        public TodoItem data;

        public MyViewHolder(View itemView) {
            super(itemView);

            titleTextView = (TextView) itemView.findViewById(R.id.title_text_view);

            itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mActivity.openEditItemScreen(data.getTitle());
        }

        @Override
        public boolean onLongClick(View view) {
            mActivity.deleteTodoItem(data);
            return false;
        }
    }



}
