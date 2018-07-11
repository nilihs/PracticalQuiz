package sg.edu.rp.c346.practicalquiz;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etName, etAge;
    Spinner spinnerClass;
    Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAge = findViewById(R.id.editTextAge);
        etName = findViewById(R.id.editTextName);
        spinnerClass = findViewById(R.id.spinnerClass);
        btnSubmit = findViewById(R.id.buttonSubmit);

        etName.requestFocus();


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();

        // Step 1a: Get the user input from the EditText and store it in a variable
        String name = etName.getText().toString();
        int age = Integer.parseInt(etAge.getText().toString());
        int selectedPosition = spinnerClass.getSelectedItemPosition();

        // Step 1b: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // Step 1c: Obtain an instance of the SharedPreference Editor for update later
        SharedPreferences.Editor prefEdit = prefs.edit();

        // Step 1d: Add the key-value pair
        // The value should be from the variable defined in Step 1a
        prefEdit.putString("name", name);
        prefEdit.putInt("age", age);
        prefEdit.putInt("spinner", selectedPosition);

        // Step 1e: Call commit() method to save the changes into SharedPreferences
        prefEdit.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Step 2a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // Step 2b: Retrieve the saved data from the SharedPreferences object
        String msgName = prefs.getString("name", "");
        int msgAge = prefs.getInt("age", 0);
        int msgPosition = prefs.getInt("spinner", 0);

        // Step 2c: Update the UI element with the value
        etName.setText(msgName);
        etAge.setText(String.valueOf(msgAge));
        spinnerClass.setSelection(msgPosition);


    }
}
