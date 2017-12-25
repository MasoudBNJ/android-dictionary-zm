package org.maktab.masoudbn.dictionary;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditDeleteFragment extends DialogFragment {
    public static final int REQUEST_CODE = 1;
    public static final String ARG_DIALOG = "ARGS_DIALOG";
    public static final String WORD_ID_ARGS = "word_id_args";


    private Button btnEdit;
    private Button btnDelete;
    private WordRepository wordRepository;
    private Word word;




    public EditDeleteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_delete, container, false);
        btnDelete = (Button) view.findViewById(R.id.btnDelete);
        btnEdit = (Button) view.findViewById(R.id.btnedit);





        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UUID id = (UUID) getArguments().getSerializable(WORD_ID_ARGS);
                wordRepository.deleteWord(id);

            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditActivity.class);
                intent.putExtra(EditFragment.EXTRA_WORD_ID, word.getWordId());
                startActivity(intent);

            }
        });


        return view;
    }
    public static EditDeleteFragment newInstance(UUID id) {
        EditDeleteFragment editDeleteFragment = new EditDeleteFragment();
        Bundle args = new Bundle();
        args.putSerializable(WORD_ID_ARGS, id);
        editDeleteFragment.setArguments(args);
        return editDeleteFragment;
    }

}
