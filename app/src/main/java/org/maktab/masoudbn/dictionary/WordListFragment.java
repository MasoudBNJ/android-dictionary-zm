package org.maktab.masoudbn.dictionary;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.maktab.masoudbn.dictionary.database.DictionaryDbSchema;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WordListFragment extends Fragment {

    private RecyclerView recyclerViewWordsList;
    private WordAdapter wordAdapter;
    private Language fromLanguage;
    private Language toLanguage;
    private List<Word> words;
    private EditDeleteFragment editDeleteFragment;
    private static WordListFragment wordListFragment;

    public WordListFragment() {
        // Required empty public constructor
    }

    public static WordListFragment newInstance() {
        if(wordListFragment == null)
            wordListFragment = new WordListFragment();
        return wordListFragment;
    }


    public class WordHolder extends RecyclerView.ViewHolder {
        private Word word;
        TextView tvWord;
        TextView tvMeaning;

        public WordHolder(final View itemView) {
            super(itemView);
            tvWord = (TextView) itemView.findViewById(R.id.tv_word);
            tvMeaning = (TextView) itemView.findViewById(R.id.tv_meaning);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentManager fragmentManager = getFragmentManager();
                    editDeleteFragment = EditDeleteFragment.newInstance(word.getWordId());
                    editDeleteFragment.setTargetFragment(WordListFragment.this, EditDeleteFragment.REQUEST_CODE);
                    editDeleteFragment.show(fragmentManager, EditDeleteFragment.ARG_DIALOG);

                }
            });
        }

        public void setUI(Word word) {
            this.word = word;
            String fromWord, toWord;
            if (fromLanguage == Language.ARABIC)
                fromWord = word.getArabic();
            else if (fromLanguage == Language.ENGLISH)
                fromWord = word.getEnglish();
            else if (fromLanguage == Language.FRENCH)
                fromWord = word.getFrench();
            else
                fromWord = word.getPersian();

            if (toLanguage == Language.ARABIC)
                toWord = word.getArabic();
            else if (toLanguage == Language.ENGLISH)
                toWord = word.getEnglish();
            else if (toLanguage == Language.FRENCH)
                toWord = word.getFrench();
            else
                toWord = word.getPersian();
            tvWord.setText(fromWord);
            tvMeaning.setText(toWord);
        }
    }

    public class WordAdapter extends RecyclerView.Adapter<WordHolder> {
        List<Word> words;

        public WordAdapter(List<Word> words) {
            this.words = words;
        }

        public void setWords(List<Word> words) {
            this.words = words;
        }

        @Override
        public WordHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View view = inflater.inflate(R.layout.words_list_item, parent, false);

            WordHolder wordHolder = new WordHolder(view);
            return wordHolder;
        }

        @Override
        public void onBindViewHolder(WordHolder holder, int position) {
            Word word = words.get(position);
            holder.setUI(word);
        }

        @Override
        public int getItemCount() {
            return words.size();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_word_list, container, false);
        recyclerViewWordsList = (RecyclerView) view.findViewById(R.id.recycler_view_words_list);

        recyclerViewWordsList.setLayoutManager(new LinearLayoutManager(getActivity()));


        return view;
    }

    @Override
    public void onResume()
    {
        super.onResume();

    }

    public void updateUI(List<Word> words, Language fromLanguage, Language toLanguage) {
        this.fromLanguage = fromLanguage;
        this.toLanguage = toLanguage;
        if (wordAdapter == null) {
            wordAdapter = new WordAdapter(words);
            recyclerViewWordsList.setAdapter(wordAdapter);
        } else {
            wordAdapter.setWords(words);
            wordAdapter.notifyDataSetChanged();
        }
    }
}
