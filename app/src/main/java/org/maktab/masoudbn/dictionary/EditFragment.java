package org.maktab.masoudbn.dictionary;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EditFragment extends Fragment {
    public static final String EXTRA_WORD_ID = "word_id";

    Button btnEdit;
    EditText edtPersian;
    EditText edtEnglish;
    EditText edtFrench;
    EditText edtArabic;

    public EditFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        btnEdit = (Button) view.findViewById(R.id.btnedit);
        edtPersian = (EditText) view.findViewById(R.id.edt_persian_word);
        edtEnglish = (EditText) view.findViewById(R.id.edt_english_word);
        edtFrench = (EditText) view.findViewById(R.id.edt_french_word);
        edtArabic = (EditText) view.findViewById(R.id.edt_arabic_word);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WordRepository wordRepository = new WordRepository(getActivity());
                Word newWord = new Word();
                newWord.setPersian(newWord.getPersian());
                newWord.setEnglish(newWord.getEnglish());
                newWord.setFrench(newWord.getFrench());
                newWord.setArabic(newWord.getArabic());

                wordRepository.updateWord(newWord);
                Toast.makeText(getActivity(), getString(R.string.edit_toast), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


}
