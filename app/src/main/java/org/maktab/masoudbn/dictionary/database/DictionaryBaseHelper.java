package org.maktab.masoudbn.dictionary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Dictionary;

/**
 * Created by user on 2017/12/23.
 */

public class DictionaryBaseHelper extends SQLiteOpenHelper {
    public DictionaryBaseHelper(Context context) {
        super(context, DictionaryDbSchema.Name, null, DictionaryDbSchema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table" + DictionaryDbSchema.WordTable.NAME + "(" +
                "_id integer primary key autoincrement," +
                DictionaryDbSchema.WordTable.Cols.WORD_ID + "," +
                DictionaryDbSchema.WordTable.Cols.ARABIC + "," +
                DictionaryDbSchema.WordTable.Cols.ENGLISH + "," +
                DictionaryDbSchema.WordTable.Cols.FRENCH + "," +
                DictionaryDbSchema.WordTable.Cols.PERSIAN + "," +
                ")" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
