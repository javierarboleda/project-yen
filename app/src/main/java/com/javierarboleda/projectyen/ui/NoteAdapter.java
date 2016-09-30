package com.javierarboleda.projectyen.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.javierarboleda.projectyen.R;
import com.javierarboleda.projectyen.data.Note;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created on 9/29/16.
 */
public class NoteAdapter extends RealmRecyclerViewAdapter<Note, NoteAdapter.MyViewHolder> {

    private final TodoDetailsEditActivity mActivity;

    public NoteAdapter(TodoDetailsEditActivity activity, OrderedRealmCollection<Note> data) {
        super(activity, data, true);
        mActivity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View noteItemView = inflater.inflate(R.layout.list_item_note, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(noteItemView);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Note note = getData().get(position);

        holder.data = note;

        holder.noteTextView.setText(note.getNoteText());
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView noteTextView;
        public Note data;

        public MyViewHolder(View itemView) {
            super(itemView);

            noteTextView = (TextView) itemView.findViewById(R.id.note_item_text_view);
        }

    }


}
