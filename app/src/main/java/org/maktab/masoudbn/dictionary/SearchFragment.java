package org.maktab.masoudbn.dictionary;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    FragmentManager fragmentManager;
    private EditText searchWord;
    private Button btnSearch;
    private RadioButton btnEnglish;
    private RadioButton btnPersian;
    private RadioButton btnArabic;
    private RadioButton btnFrench;
    private Language language;
    private List<Word> words;
    private WordRepository wordRepository;


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        fragmentManager = getFragmentManager();
        WordListFragment wordsListFragment = new WordListFragment();
        fragmentManager.beginTransaction()
                .add(R.id.frame_layout_words_container, wordsListFragment)
                .commit();

        searchWord = (EditText) view.findViewById(R.id.edt_word);
        btnSearch = (Button) view.findViewById(R.id.btn_search);
        btnArabic = (RadioButton) view.findViewById(R.id.rdb_arabic);
        btnFrench = (RadioButton) view.findViewById(R.id.rdb_english);
        btnEnglish = (RadioButton) view.findViewById(R.id.rdb_french);
        btnPersian = (RadioButton) view.findViewById(R.id.rdb_persain);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = searchWord.getText().toString();
                if (btnArabic.isChecked()) {
                    language = Language.ARABIC;
                }
                if (btnFrench.isChecked()) {
                    language = Language.FRENCH;
                }
                if (btnEnglish.isChecked()) {
                    language = Language.ENGLISH;
                }
                if (btnPersian.isChecked()) {
                    language = Language.PERSIAN;
                }
                wordRepository.search(search, language);


            }
        });


        return view;
    }

}
