package org.maktab.masoudbn.dictionary;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
        private RecyclerView recyclerView;
    FragmentManager fragmentManager;
    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        fragmentManager = getFragmentManager();
        WordsListFragment wordsListFragment = new WordsListFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_layout_words_container, wordsListFragment)
                .commit();
        return view;
    }

}
