package org.maktab.masoudbn.dictionary;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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
    private UUID id;
    private Callbacks callbacks;


    public EditDeleteFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_edit_delete, null);
        id = (UUID)getArguments().getSerializable(WORD_ID_ARGS);
        word = new WordRepository(getActivity()).getWord(id);
        btnDelete = (Button) view.findViewById(R.id.btnDelete);
        btnEdit = (Button) view.findViewById(R.id.btnedit);
        wordRepository = new WordRepository(getActivity());

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UUID id = (UUID) getArguments().getSerializable(WORD_ID_ARGS);
                wordRepository.deleteWord(id);
                callbacks.updateUI();
                Toast.makeText(getActivity(), "The word is deleted successfully!", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditActivity.class);
                intent.putExtra(EditFragment.WORD_ID_ARGS, word.getWordId());
                startActivity(intent);
                dismiss();
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setTitle("Select operation:")
                .setView(view)
                .create();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof MainActivity) {
            callbacks = (EditDeleteFragment.Callbacks) getActivity();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_delete, container, false);



        return view;
    }
    public static EditDeleteFragment newInstance(UUID id) {
        EditDeleteFragment editDeleteFragment = new EditDeleteFragment();
        Bundle args = new Bundle();
        args.putSerializable(WORD_ID_ARGS, id);
        editDeleteFragment.setArguments(args);
        return editDeleteFragment;
    }

    public interface Callbacks {
        void updateUI();
    }
}
