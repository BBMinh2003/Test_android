package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText name,phonenumber;
    Button button;
    RadioGroup radioGroup;
    Spinner spinner;
    ListView listView;

    ArrayList<String> list;
    ArrayAdapter adapter;

    String[] country = { "Hà Nội", "Lào Cai", "Đà Nẵng", "Quảng Ninh","Hải Dương","Thanh Hóa" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.editTextText);
        phonenumber = findViewById(R.id.editTextText2);
        spinner = findViewById(R.id.spinner);
        listView = findViewById(R.id.listView);
        radioGroup = findViewById(R.id.radioGroup);
        button = findViewById(R.id.button);

        list = new ArrayList<>();
        list.add("Từng Dương");
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, country);
        spinner.setAdapter(spinnerAdapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtname = name.getText().toString();
                String phone = phonenumber.getText().toString();
                String gender = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
                String hometown = spinner.getSelectedItem().toString();

                list.add(txtname + "-" + gender + "-" + phone + "-" + hometown);
                adapter.notifyDataSetChanged();
            }
        });
    }
}