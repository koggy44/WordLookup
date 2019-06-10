package com.kogie.wordlookup;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText tv_word;
    Button translate_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        translate_button = findViewById(R.id.translate_button);
        tv_word = findViewById(R.id.input_word);

        translate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = tv_word.getText().toString();
                if(word.length() > 0){
                 //start new activity here later
                    //Toast.makeText(MainActivity.this, "we did it", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, WordsActivity.class);
                    try {
                        intent.putStringArrayListExtra("wordList", translate(word));
                    }catch(IOException ex){
                        Log.e("MainActivity", ex.toString());
                    }
                    startActivity(intent);
                }
            }
        });
    }
    public ArrayList translate(String word) throws IOException{
        ArrayList<String> wordList = new ArrayList<>();
        AssetManager assetManager = getAssets();
        InputStream inputStream = assetManager.open("data.csv");
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ( (line = br.readLine()) != null ) {
            String[] values = line.split(",");
            Log.e("MainActivity", line+"  ,"+values.length);
            if(values.length == 2 && values[1].equals(word)) {
                wordList.add(values[0]);
            }
        }
        br.close();
        return wordList;
    }

}
