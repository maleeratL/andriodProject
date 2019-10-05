package com.example.myapplicationwebview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private SQLiteDatabase mDatabase;
    private HistoryAdapter mAdapter;
    private EditText mEditTextName;
    private TextView mTextViewAmount;
    private int mAmount =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        HistoryDBHelper dbHelper = new HistoryDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        RecyclerView recyclerView = findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new HistoryAdapter(this,getAllItems());
        recyclerView.setAdapter(mAdapter);

        mEditTextName = findViewById(R.id.edittext_name);
        mTextViewAmount = findViewById(R.id.textview_amount);

        Button buttonIncrease = findViewById(R.id.button_increase);
        Button buttonDecrease = findViewById(R.id.button_decrease);
        Button buttonAdd = findViewById(R.id.button_add);

        buttonIncrease.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                increase();
            }
        });

        buttonDecrease.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                decrease();
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                addItem();
            }
        });
    }

    private void increase(){
        mAmount++;
        mTextViewAmount.setText(String.valueOf(mAmount));
    }

    private void decrease(){
        if(mAmount>0){
            mAmount--;
            mTextViewAmount.setText(String.valueOf(mAmount));
        }
    }

    private void addItem(){
        if (mEditTextName.getText().toString().trim().length() ==0 ||mAmount==0){
            return;
        }
        String name = mEditTextName.getText().toString();
        ContentValues cv = new ContentValues();
        cv.put(HistoryContract.HistoryEntry.COLUMN_NAME, name);
        cv.put(HistoryContract.HistoryEntry.COLUMN_AMOUNT, mAmount);

        mDatabase.insert(HistoryContract.HistoryEntry.TABLE_NAME, null, cv);
        mAdapter.swapCursor(getAllItems());

        mEditTextName.getText().clear();
    }

    //Method to return a cursor
    //because normally have to pass a cursor to adapter
    private Cursor getAllItems(){
        return  mDatabase.query(
                HistoryContract.HistoryEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                HistoryContract.HistoryEntry.COLUMN_TIMESTAMP + " DESC"
        );
    }
}
