package org.maktab.masoudbn.dictionary;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    FragmentManager fragmentManager;
    private EditText edtSearchWord;
    private RadioButton rdbFromEnglish;
    private RadioButton rdbFromPersian;
    private RadioButton rdbFromArabic;
    private RadioButton rdbFromFrench;
    private RadioButton rdbToEnglish;
    private RadioButton rdbToPersian;
    private RadioButton rdbToArabic;
    private RadioButton rdbToFrench;
    private RadioGroup rdgFromLanguages;
    private RadioGroup rdgToLanguages;
    private Language language;
    private WordRepository wordRepository;
    private static SearchFragment searchFragment;
    WordListFragment wordsListFragment;

    public SearchFragment() {
        // Required empty public constructor
    }

    public void onRadioButtonsClick(View v)
    {
        if(edtSearchWord.getText().length()>=2)
        {
            search(edtSearchWord.getText().toString());
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_search, container, false);
        fragmentManager = getFragmentManager();
        wordsListFragment= WordListFragment.newInstance();
        fragmentManager.beginTransaction()
                .add(R.id.frame_layout_words_container, wordsListFragment)
                .commit();
        wordRepository = new WordRepository(getActivity());
        edtSearchWord = (EditText) view.findViewById(R.id.edt_word);
        rdbFromArabic = (RadioButton) view.findViewById(R.id.rdb_from_arabic);
        rdbFromFrench = (RadioButton) view.findViewById(R.id.rdb_from_french);
        rdbFromEnglish = (RadioButton) view.findViewById(R.id.rdb_from_english);
        rdbFromPersian = (RadioButton) view.findViewById(R.id.rdb_from_persain);

        rdbToArabic = (RadioButton) view.findViewById(R.id.rdb_to_arabic);
        rdbToFrench = (RadioButton) view.findViewById(R.id.rdb_to_french);
        rdbToEnglish = (RadioButton) view.findViewById(R.id.rdb_to_english);
        rdbToPersian = (RadioButton) view.findViewById(R.id.rdb_to_persain);
        rdgFromLanguages = (RadioGroup) view.findViewById(R.id.rdg_from_language);
        rdgToLanguages = (RadioGroup) view.findViewById(R.id.rdg_to_language);

        rdbFromArabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioButtonsClick(v);
            }
        });
        rdbFromEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioButtonsClick(v);
            }
        });
        rdbFromFrench.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioButtonsClick(v);
            }
        });
        rdbFromPersian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioButtonsClick(v);
            }
        });
        rdbToArabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioButtonsClick(v);
            }
        });
        rdbToEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioButtonsClick(v);
            }
        });
        rdbToFrench.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioButtonsClick(v);
            }
        });
        rdbToPersian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioButtonsClick(v);
            }
        });
        edtSearchWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>=2)
                    search(s.toString());
                else
                    updateList(new ArrayList<Word>());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }


    private void search(String search) {
        List<Word> words = wordRepository.search(search, getFromLanguage());
        updateList(words);
    }

    public static SearchFragment newInstance() {
        if(searchFragment == null)
            searchFragment = new SearchFragment();
        return searchFragment;
    }

    private Language getFromLanguage() {

        if(rdgFromLanguages.getCheckedRadioButtonId() == R.id.rdb_from_arabic)
            return Language.ARABIC;
        else if(rdgFromLanguages.getCheckedRadioButtonId() == R.id.rdb_from_english)
            return Language.ENGLISH;
        else if(rdgFromLanguages.getCheckedRadioButtonId() == R.id.rdb_from_french)
            return Language.FRENCH;
        else if(rdgFromLanguages.getCheckedRadioButtonId() == R.id.rdb_from_persain)
            return Language.PERSIAN;
        else
            return Language.PERSIAN;
    }

    private Language getToLanguage() {
        if(rdgToLanguages.getCheckedRadioButtonId() == R.id.rdb_to_arabic)
            return Language.ARABIC;
        else if(rdgToLanguages.getCheckedRadioButtonId() == R.id.rdb_to_english)
            return Language.ENGLISH;
        else if(rdgToLanguages.getCheckedRadioButtonId() == R.id.rdb_to_french)
            return Language.FRENCH;
        else if(rdgToLanguages.getCheckedRadioButtonId() == R.id.rdb_to_persain)
            return Language.PERSIAN;
        else
            return Language.PERSIAN;
    }
    private void updateList(List<Word> words) {
        wordsListFragment.updateUI(words, getFromLanguage(), getToLanguage());
    }

    public void updateUI() {

        if(edtSearchWord.getText().length() >= 2) {
            search(edtSearchWord.getText().toString());
        } else {
            updateList(new ArrayList<Word>());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
