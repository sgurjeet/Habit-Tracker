package com.example.gurjeet.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by Gurjeet on 02-Mar-17.
 */

public final class HabitContract {
    public static final class HabitEntry implements BaseColumns{
        public static final String TABLE_NAME="Habits";
        public static final String COLUMN_TASK="Task";
        public static final String COLUMN_DURATION="Duration";
    }
}
