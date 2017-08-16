package com.example.gurjeet.habittracker;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gurjeet.habittracker.data.HabitContract.HabitEntry;
import com.example.gurjeet.habittracker.data.HabitDb;

public class EditorActivity extends AppCompatActivity{
    private EditText mHabit;
    private EditText mDur;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        mHabit=(EditText)findViewById(R.id.habitEdit);
        mDur=(EditText)findViewById(R.id.durEdit);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }
    private void insertPet(){
        HabitDb dbHelper=new HabitDb(this);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        String habit=mHabit.getText().toString().trim();
        int duration=Integer.parseInt(mDur.getText().toString().trim());
        ContentValues values=new ContentValues();
        values.put(HabitEntry.COLUMN_TASK,habit);
        values.put(HabitEntry.COLUMN_DURATION,duration);
        long rowId=db.insert(HabitEntry.TABLE_NAME,null,values);
        if(rowId==-1)
            Toast.makeText(this, "Error while adding Habit", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "Successfully added Habit no. "+rowId, Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                insertPet();
                finish();
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
