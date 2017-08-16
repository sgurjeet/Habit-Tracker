package com.example.gurjeet.habittracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.gurjeet.habittracker.data.HabitContract.HabitEntry;
import com.example.gurjeet.habittracker.data.HabitDb;

public class MainActivity extends AppCompatActivity {

    private HabitDb mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        mDb=new HabitDb(this);
        displayDb();
    }
    private Cursor read(){
        SQLiteDatabase db= mDb.getReadableDatabase();
        return db.query(HabitEntry.TABLE_NAME,null,null,null,null,null,null);
    }
    private void displayDb() {
        Cursor cursor=read();

        TextView res=(TextView)findViewById(R.id.text_view_habit);
        res.setText("There are "+cursor.getCount()+ " habits added.");
        if(cursor.getCount()>0)
            res.append("\n\nID\t\t\t"+HabitEntry.COLUMN_TASK+"\t\t\t"+HabitEntry.COLUMN_DURATION+"\n");

        int idInd=cursor.getColumnIndex(HabitEntry._ID);
        int taskInd=cursor.getColumnIndex(HabitEntry.COLUMN_TASK);
        int durInd=cursor.getColumnIndex(HabitEntry.COLUMN_DURATION);

        try
        {
            while(cursor.moveToNext()){
                int currId=cursor.getInt(idInd);
                String currTask=cursor.getString(taskInd);
                int currDur=cursor.getInt(durInd);

                res.append(currId+"\t\t\t"
                        +currTask+"\t\t\t"
                        +currDur+"\n");
            }
        }
        finally {
            cursor.close();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    protected void onStart() {
        super.onStart();
        displayDb();
    }
    private void deleteAll(){
        SQLiteDatabase db=mDb.getWritableDatabase();
        String delAll="DELETE from "+HabitEntry.TABLE_NAME+";";
        db.execSQL(delAll);
        displayDb();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.del){
            deleteAll();
        }
        return super.onOptionsItemSelected(item);
    }
}
