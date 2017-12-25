package org.maktab.masoudbn.dictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.maktab.masoudbn.dictionary.database.DictionaryBaseHelper;
import org.maktab.masoudbn.dictionary.database.DictionaryDbSchema;
import org.maktab.masoudbn.dictionary.database.WordCursorWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by MasoudBN on 23/12/2017.
 */

public class WordRepository {
    SQLiteDatabase database;
    private Context context;

    public WordRepository(Context context) {
        this.context = context.getApplicationContext();
        database = new DictionaryBaseHelper(this.context).getWritableDatabase();
    }

    public void addWord(Word word) {
        ContentValues contentValues = getContentValues(word);
        database.insert(DictionaryDbSchema.WordTable.NAME, null, contentValues);
    }

    public void updateWord(Word newWord) {
        ContentValues contentValues = getContentValues(newWord);
        database.update(DictionaryDbSchema.WordTable.NAME, contentValues,
                DictionaryDbSchema.WordTable.Cols.UUID + " = ?",
                new String[]{newWord.getWordId().toString()});
    }

    public List<Word> getWords() {
        WordCursorWrapper wordCursor = queryWords(null, null);
        if (wordCursor == null)
            return null;

        List<Word> words = new ArrayList<>();
        if (wordCursor.getCount() == 0)
            return words;

        try {
            wordCursor.moveToFirst();

            while (!wordCursor.isAfterLast()) {
                Word word = wordCursor.getWord();
                words.add(word);

                wordCursor.moveToNext();
            }
        } finally {
            wordCursor.close();
        }
        return words;
    }

    public List<Word> search(String searchWord, Language language) {
        String whereClause;
        List<Word> words = new ArrayList<>();

        switch (language) {
            case ARABIC:
                whereClause = DictionaryDbSchema.WordTable.Cols.ARABIC + " LIKE ?";
                break;
            case ENGLISH:
                whereClause = DictionaryDbSchema.WordTable.Cols.ENGLISH + " LIKE ?";
                break;
            case FRENCH:
                whereClause = DictionaryDbSchema.WordTable.Cols.FRENCH + " LIKE ?";
                break;
            case PERSIAN:
                whereClause = DictionaryDbSchema.WordTable.Cols.PERSIAN + " LIKE ?";
                break;
            default:
                whereClause = DictionaryDbSchema.WordTable.Cols.ENGLISH + " LIKE ?";
                break;
        }

        String[] whereArgs = new String[]{
                searchWord + "%"
        };

        WordCursorWrapper wordCursor = queryWords(whereClause, whereArgs);

        if (wordCursor == null)
            return null;
        if (wordCursor.getCount() == 0)
            return words;

        try {
            wordCursor.moveToFirst();
            while (!wordCursor.isAfterLast()) {
                Word word = wordCursor.getWord();
                words.add(word);

                wordCursor.moveToNext();
            }
        } finally {
            wordCursor.close();
        }

        return words;
    }


    public void deleteWord(UUID wordId) {
        database.delete(DictionaryDbSchema.WordTable.NAME,
                DictionaryDbSchema.WordTable.Cols.UUID + " = ?",
                new String[]{wordId.toString()});
    }

    private ContentValues getContentValues(Word word) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DictionaryDbSchema.WordTable.Cols.UUID, word.getWordId().toString());
        contentValues.put(DictionaryDbSchema.WordTable.Cols.ARABIC, word.getArabic());
        contentValues.put(DictionaryDbSchema.WordTable.Cols.ENGLISH, word.getEnglish());
        contentValues.put(DictionaryDbSchema.WordTable.Cols.FRENCH, word.getFrench());
        contentValues.put(DictionaryDbSchema.WordTable.Cols.PERSIAN, word.getPersian());

        return contentValues;
    }

    private WordCursorWrapper queryWords(String whereClause, String[] whereArgs) {
        Cursor cursor = database.query(DictionaryDbSchema.WordTable.NAME, null, whereClause, whereArgs, null, null, null);
        return new WordCursorWrapper(cursor);
    }
}
