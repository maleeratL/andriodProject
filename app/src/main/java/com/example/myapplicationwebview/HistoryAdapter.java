package com.example.myapplicationwebview;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private Context mContext;
    private Cursor mCursor;

    //Cursor cursor - get the data out of database
    public HistoryAdapter(Context context, Cursor cursor){
        mContext = context;
        mCursor = cursor;
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder{
        public TextView nameText;
        public TextView countText;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.textview_name_item);
            countText = itemView.findViewById(R.id.textview_amount_item);

        }
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.history_item, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)){
            return;
        }
        String name = mCursor.getString(mCursor.getColumnIndex(HistoryContract.HistoryEntry.COLUMN_NAME));
        int amount = mCursor.getInt(mCursor.getColumnIndex(HistoryContract.HistoryEntry.COLUMN_AMOUNT));

        holder.nameText.setText(name);
        holder.countText.setText(String.valueOf(amount));
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    //Every time that update database has o pass a new cursor
    public void swapCursor(Cursor newCursor){
        if (mCursor != null){
            mCursor.close();
        }
        mCursor = newCursor;
        if (newCursor != null){
            notifyDataSetChanged();
        }
    }

}
