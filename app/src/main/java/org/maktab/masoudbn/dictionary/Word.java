package org.maktab.masoudbn.dictionary;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by MasoudBN on 23/12/2017.
 */

public class Word{
    private UUID wordId;
    private String english;
    private String persian;
    private String arabic;
    private String french;

    public Word()
    {
        wordId = UUID.randomUUID();
    }

    public Word(UUID uuid)
    {
        this.wordId = uuid;
    }

    public UUID getWordId() {
        return wordId;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getPersian() {
        return persian;
    }

    public void setPersian(String persian) {
        this.persian = persian;
    }

    public String getArabic() {
        return arabic;
    }

    public void setArabic(String arabic) {
        this.arabic = arabic;
    }

    public String getFrench() {
        return french;
    }

    public void setFrench(String french) {
        this.french = french;
    }


}
