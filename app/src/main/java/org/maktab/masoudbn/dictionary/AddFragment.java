package org.maktab.masoudbn.dictionary;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class AddFragment extends Fragment {
    Button btnAdd;
    EditText edtPersian;
    EditText edtEnglish;
    EditText edtFrench;
    EditText edtArabic;

    public AddFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        btnAdd = (Button) view.findViewById(R.id.btn_add);
        edtPersian = (EditText) view.findViewById(R.id.edt_persian_word);
        edtEnglish = (EditText) view.findViewById(R.id.edt_english_word);
        edtFrench = (EditText) view.findViewById(R.id.edt_french_word);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WordRepository wordRepository = new WordRepository(getActivity());
                Word word = new Word();
                word.setPersian(edtPersian.getText().toString());
                word.setEnglish(edtEnglish.getText().toString());
                word.setFrench(edtFrench.getText().toString());
                word.setArabic(edtArabic.getText().toString());
                wordRepository.addWord(word);
            }
        });
        return view;
    }

}
