package com.danlls.daniel.todule_android.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.danlls.daniel.todule_android.R;
import com.danlls.daniel.todule_android.provider.ToduleDBContract;

/**
 * Created by danieL on 10/20/2017.
 */

public class LabelAdapter extends CursorAdapter {
    private LayoutInflater cursorInflater;
    private boolean showCheckbox;

    public LabelAdapter(Context context, Cursor cursor, int flags){
        super(context, cursor, flags);
        cursorInflater = LayoutInflater.from(context);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        final ViewHolder holder = (ViewHolder) view.getTag();
        String tag = cursor.getString(cursor.getColumnIndexOrThrow(ToduleDBContract.TodoLabel.COLUMN_NAME_TAG));
        int color = cursor.getInt(cursor.getColumnIndexOrThrow(ToduleDBContract.TodoLabel.COLUMN_NAME_COLOR));
        int text_color = cursor.getInt(cursor.getColumnIndexOrThrow(ToduleDBContract.TodoLabel.COLUMN_NAME_TEXT_COLOR));

        holder.label.setText(tag);
        holder.label.setBackgroundColor(color);
        holder.label.setTextColor(text_color);

        if(showCheckbox){
            holder.checkBox.setVisibility(View.VISIBLE);
        } else {
            holder.checkBox.setVisibility(View.GONE);
        }
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View rowView = cursorInflater.inflate(R.layout.fragment_label_item, parent, false);
        ViewHolder holder = new ViewHolder();
        holder.label = rowView.findViewById(R.id.label_tag);
        holder.checkBox = rowView.findViewById(R.id.checkbox);
        rowView.setTag(holder);
        return rowView;
    }

    public void setShowCheckbox(boolean b){
        showCheckbox = b;
    }

    static class ViewHolder {
        TextView label;
        CheckBox checkBox;
    }
}
