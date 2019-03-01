package com.example.sharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText name, roll, dept, text;
    Button save, next, saveText;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String Roll = "rollKey";
    public static final String Dept = "deptKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        roll = findViewById(R.id.roll);
        dept = findViewById(R.id.dept);
        text = findViewById(R.id.text);

        save = findViewById(R.id.save);
        next = findViewById(R.id.next);
        saveText = findViewById(R.id.saveText);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n,r,d;

                n = name.getText().toString();
                r = roll.getText().toString();
                d = dept.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(Name, n);
                editor.putString(Roll, r);
                editor.putString(Dept, d);
                editor.apply();

                Toast.makeText(getApplicationContext(), "Saved preferences!", Toast.LENGTH_LONG).show();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DisplayActivity.class);
                startActivity(intent);
            }
        });

        saveText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getApplication().openFileOutput("config.txt", Context.MODE_PRIVATE));
                    outputStreamWriter.write(text.getText().toString());
                    outputStreamWriter.close();

                    Toast.makeText(getApplicationContext(), "Saved to a file!", Toast.LENGTH_LONG).show();
                }
                catch (IOException e) {
                    Log.e("Exception", "File write failed: " + e.toString());
                }
            }
        });
    }
}
