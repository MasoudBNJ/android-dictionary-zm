package org.maktab.masoudbn.dictionary;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.maktab.masoudbn.dictionary.R;

import java.util.UUID;

public class EditActivity extends AppCompatActivity implements EditFragment.Callbacks{
    FragmentManager fragmentManager;
    private UUID id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        id = (UUID) getIntent().getSerializableExtra(EditFragment.WORD_ID_ARGS);
        fragmentManager = getSupportFragmentManager();
        EditFragment editFragment = EditFragment.newInstance(id);
        fragmentManager.beginTransaction()
                .replace(R.id.frame_layout_edit,editFragment )
                .commit();
    }

    @Override
    public void updateUI() {
        SearchFragment.newInstance().updateUI();
    }
}
