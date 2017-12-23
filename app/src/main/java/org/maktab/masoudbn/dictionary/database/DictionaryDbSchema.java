package org.maktab.masoudbn.dictionary.database;

/**
 * Created by user on 2017/12/23.
 */

public class DictionaryDbSchema {
    public static final String Name = "dictionary.database";
    public static final int VERSION = 1;

    public static final class WordTable {
        public static final String NAME = "Words";
        public static final class Cols {
            public static final String WORD_ID = "WORD_ID";
            public static final String ENGLISH = "ENGLISH";
            public static final String PERSIAN = "PERSIAN";
            public static final String FRENCH = "FRENCH";
            public static final String ARABIC = "ARABIC";

        }


    }
}
