package org.maktab.masoudbn.dictionary.database;

/**
 * Created by user on 2017/12/23.
 */

public class DictionaryDbSchema {
    public static final String Name = "dictionary.db";
    public static final int VERSION = 1;

    public static final class WordTable {
        public static final String NAME = "Word";
        public static final class Cols {
            public static final String UUID = "UUID";
            public static final String ENGLISH = "English";
            public static final String PERSIAN = "Persian";
            public static final String FRENCH = "French";
            public static final String ARABIC = "Arabic";
        }

    }
}
