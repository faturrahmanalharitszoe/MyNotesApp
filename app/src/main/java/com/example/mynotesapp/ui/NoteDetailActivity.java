package com.example.mynotesapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mynotesapp.R;
import com.example.mynotesapp.database.Note;
import com.example.mynotesapp.repository.NoteRepository;
import com.google.android.material.button.MaterialButton;

public class NoteDetailActivity extends AppCompatActivity implements View.OnClickListener {

    public final static String EXTRA_NOTE = "extra_note";

    private NoteRepository noteRepository;
    private Note note;
    private TextView tvTitle, tvDescription, tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.detail_note);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        noteRepository = new NoteRepository(getApplication());
        note = getIntent().getParcelableExtra(EXTRA_NOTE);

        tvTitle = findViewById(R.id.tv_note_title);
        tvDescription = findViewById(R.id.tv_note_description);
        tvDate = findViewById(R.id.tv_note_date);

        MaterialButton btnDelete = findViewById(R.id.btn_delete_note);
        btnDelete.setOnClickListener(this);

        parseNoteView(note);
    }

    private void parseNoteView(Note note) {
        tvTitle.setText(note.getTitle());
        tvDescription.setText(note.getDescription());
        tvDate.setText(note.getDate());
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_delete_note) {
            // TODO 11: Panggil method delete() pada kelas NoteRepository
            noteRepository.delete(note);
            finish();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}