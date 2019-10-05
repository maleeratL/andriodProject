package com.example.myapplicationwebview;

import android.provider.BaseColumns;

public class HistoryContract {
    private HistoryContract(){}

    public static final class HistoryEntry implements BaseColumns{
        public static final String TABLE_NAME = "historylist";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AMOUNT ="amount";
        public static final String COLUMN_TIMESTAMP ="timestamp";
    }

}
