package org.maktab.masoudbn.dictionary.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import org.maktab.masoudbn.dictionary.Word;

import java.util.UUID;

/**
 * Created by MasoudBN on 23/12/2017.
 */

public class WordCursorWrapper extends CursorWrapper {

    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public WordCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Word getWord() {
        String uuidString = getString(getColumnIndex(DictionaryDbSchema.WordTable.Cols.UUID));
        String arabic = getString(getColumnIndex(DictionaryDbSchema.WordTable.Cols.ARABIC));
        String english = getString(getColumnIndex(DictionaryDbSchema.WordTable.Cols.ENGLISH));
        String french = getString(getColumnIndex(DictionaryDbSchema.WordTable.Cols.FRENCH));
        String persian = getString(getColumnIndex(DictionaryDbSchema.WordTable.Cols.PERSIAN));

        Word word = new Word(UUID.fromString(uuidString));
        word.setArabic(arabic);
        word.setEnglish(english);
        word.setFrench(french);
        word.setPersian(persian);

        return word;
    }
}
