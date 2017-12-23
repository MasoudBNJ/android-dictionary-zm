package org.maktab.masoudbn.dictionary;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by MasoudBN on 23/12/2017.
 */

public class WordRepository {
    private Context context;

    public WordRepository(Context context)
    {
        this.context = context.getApplicationContext();
    }

    public void addWord(Word word)
    {

    }

    public void updateWord(Word word)
    {

    }

    public List<Word> getWords()
    {
        List<Word> words = new ArrayList<>();
        Word word = new Word();
        word.setEnglish("salam");
        word.setFrench("salut");
        word.setArabic("السلام");
        word.setPersian("سلام");
        words.add(word);
        Word word2 = new Word();
        word2.setEnglish("salam");
        word2.setFrench("salut");
        word2.setArabic("السلام");
        word2.setPersian("سلام");
        words.add(word2);
        return words;
    }

    public Word getWord(UUID wordId)
    {
        return new Word();
    }

    public void deleteWord(UUID wordId)
    {

    }

}
