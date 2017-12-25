package org.maktab.masoudbn.dictionary;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        EditFragment editFragment = new EditFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.edit_fragment, editFragment)
                .commit();
    }
}
