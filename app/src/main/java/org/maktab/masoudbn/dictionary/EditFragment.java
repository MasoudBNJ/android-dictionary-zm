package org.maktab.masoudbn.dictionary;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;


public class EditFragment extends Fragment {
    public static final String WORD_ID_ARGS = "word_id_args";

    Button btnEdit;
    Word word;
    EditText edtPersian;
    EditText edtEnglish;
    EditText edtFrench;
    EditText edtArabic;
    private UUID id;
    private EditFragment.Callbacks callbacks;

    public EditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = (UUID)getArguments().getSerializable(WORD_ID_ARGS);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof EditActivity)
            callbacks = (EditFragment.Callbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        btnEdit = (Button) view.findViewById(R.id.btn_add);
        btnEdit.setText("Save");
        edtPersian = (EditText) view.findViewById(R.id.edt_persian_word);
        edtEnglish = (EditText) view.findViewById(R.id.edt_english_word);
        edtFrench = (EditText) view.findViewById(R.id.edt_french_word);
        edtArabic = (EditText) view.findViewById(R.id.edt_arabic_word);
        final Word word = new WordRepository(getActivity()).getWord(id);
        edtPersian.setText(word.getPersian());
        edtArabic.setText(word.getArabic());
        edtFrench.setText(word.getFrench());
        edtEnglish.setText(word.getEnglish());
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WordRepository wordRepository = new WordRepository(getActivity());
                word.setPersian(edtPersian.getText().toString());
                word.setEnglish(edtEnglish.getText().toString());
                word.setFrench(edtFrench.getText().toString());
                word.setArabic(edtArabic.getText().toString());
                wordRepository.updateWord(word);
                Toast.makeText(getActivity(), getString(R.string.edit_toast), Toast.LENGTH_SHORT).show();
                callbacks.updateUI();
                getActivity().finish();
            }
        });
        return view;
    }

    public static EditFragment newInstance(UUID id) {
        EditFragment editFragment = new EditFragment();
        Bundle args = new Bundle();
        args.putSerializable(WORD_ID_ARGS, id);
        editFragment.setArguments(args);
        return editFragment;
    }

    public interface Callbacks {
        public void updateUI();
    }
}
