package com.kogie.wordlookup;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WordsActivity extends AppCompatActivity {
    RecyclerView mWordRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        mWordRecyclerView = findViewById(R.id.words_recycler_view);
        mWordRecyclerView.setLayoutManager(new LinearLayoutManager(WordsActivity.this));
        ArrayList<String> words = getIntent().getStringArrayListExtra("wordList");
        updateUI(words);
        //Toast.makeText(this, "asdfasdfadsf", Toast.LENGTH_SHORT).show();
    }

    public void updateUI(List words){
        WordAdapter adapter = new WordAdapter(words);
        mWordRecyclerView.setAdapter(adapter);
    }

    private class WordHolder extends RecyclerView.ViewHolder {
        TextView mWord;
        public WordHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_word, parent, false));
            mWord = itemView.findViewById(R.id.tv_word);
        }
        public void bind(String word){
            mWord.setText(word);
        }
    }

    private class WordAdapter extends RecyclerView.Adapter<WordHolder> {
        private List<String> mWords;
        public WordAdapter(List<String> words) {
            mWords = words;
        }

        @NonNull
        @Override
        public WordHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(WordsActivity.this);
            return new WordHolder(layoutInflater, viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull WordHolder wordHolder, int i) {
            wordHolder.bind(mWords.get(i));
        }

        @Override
        public int getItemCount() {
            return mWords.size();
        }
    }
}
