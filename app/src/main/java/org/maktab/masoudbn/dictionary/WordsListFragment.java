package org.maktab.masoudbn.dictionary;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WordsListFragment extends Fragment {

    RecyclerView recyclerViewWordsList;
    WordAdapter wordAdapter;
    public WordsListFragment() {
        // Required empty public constructor
    }

    public class WordHolder extends RecyclerView.ViewHolder {
        private Word word;
        TextView tvWord;
        TextView tvMeaning;

        public WordHolder(View itemView) {
            super(itemView);
            tvWord = (TextView) itemView.findViewById(R.id.tv_word);
            tvMeaning = (TextView) itemView.findViewById(R.id.tv_meaning);
        }

        public void setUI(Word word)
        {
            this.word = word;
            tvWord.setText(word.getEnglish());
            tvMeaning.setText(word.getPersian());
        }
    }

    public class WordAdapter extends RecyclerView.Adapter<WordHolder> {
        List<Word> words;
        public WordAdapter(List<Word> words)
        {
            this.words = words;
        }

        public void setWords(List<Word> words)
        {
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
        View view = inflater.inflate(R.layout.fragment_words_list, container, false);
        recyclerViewWordsList = (RecyclerView) view.findViewById(R.id.recycler_view_words_list);

        recyclerViewWordsList.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI()
    {
        List<Word> words = new WordRepository(getActivity()).getWords();

        if(wordAdapter == null) {
            wordAdapter = new WordAdapter(words);
            recyclerViewWordsList.setAdapter(wordAdapter);
        } else {
            wordAdapter.setWords(words);
            wordAdapter.notifyDataSetChanged();
        }

    }

}
