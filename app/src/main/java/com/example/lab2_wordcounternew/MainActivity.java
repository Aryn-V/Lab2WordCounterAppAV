package com.example.lab2_wordcounternew;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
/// Fin
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //initialising elements
        EditText edUserInput = findViewById(R.id.edUserInput);
        Button btnCount = findViewById(R.id.btnCount);
        TextView tvResult = findViewById(R.id.tvResult);
        Spinner spCountingOptions = findViewById(R.id.spCountingOptions);
        //spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.user_selections,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCountingOptions.setAdapter(adapter);
            // Shows the selected item from menu
        spCountingOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedItem = parentView.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this,getString(R.string.toast_selected, selectedItem), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//trimming user input
                String userInput = edUserInput.getText().toString().trim();
                String selected = spCountingOptions.getSelectedItem().toString();
                int result = 0;

                // Validation check
                if (userInput.isEmpty()) {
                    Toast.makeText(MainActivity.this,getString(R.string.warning_empty), Toast.LENGTH_SHORT).show();//using string value in xml
                    return;
                }

                // If else logic for spinner
                if (selected.equals("Chars")) {
                    // Using CharsCounter class
                    CharsCounter cc = new CharsCounter();
                    result = cc.countChars(userInput);
                    // using integer value in xml file
                    tvResult.setText(getString(R.string.chars_count, result));

                } else if (selected.equals("Words")) {
                    result = WordsCounter.countWords(userInput);
                    tvResult.setText(getString(R.string.words_count, result));

                } else if (selected.equals("Sentences")) {
                    result = RegexFunctions.countSentences(userInput);
                    tvResult.setText(getString(R.string.sentences_count, result));

                } else if (selected.equals("Numbers")) {
                    result = RegexFunctions.countNumbers(userInput);
                    tvResult.setText(getString(R.string.numbers_count, result));

                } else {
                    Toast.makeText(MainActivity.this,getString(R.string.invalid_selection), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}