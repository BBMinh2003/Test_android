package com.example.lab1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import kotlin.text.Regex;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    private final int GALLERY_REQ_CODE = 1000;
    EditText name,phonenumber;
    Button button;
    RadioGroup radioGroup;

    CheckBox checkBox;
    CheckBox checkBox2;
    CheckBox checkBox3;

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
        checkBox = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);

        list = new ArrayList<>();
        list.add("Từng Dương");
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        imageView = findViewById(R.id.imageView);
        Button button2 = findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery,GALLERY_REQ_CODE);
//                someActivityResultLauncher.launch(iGallery);
            }
        });

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, country);
        spinner.setAdapter(spinnerAdapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtname = name.getText().toString();
                String phone = phonenumber.getText().toString();
                String gender = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
                String hometown = spinner.getSelectedItem().toString();
                String hobby1 = checkBox.isChecked() ? checkBox.getText().toString() : "";
                String hobby2 = checkBox2.isChecked() ? checkBox2.getText().toString() : "";
                String hobby3 = checkBox2.isChecked() ? checkBox3.getText().toString() : "";

                String phoneRegex = "^\\d{10}";
                if(Pattern.matches(phoneRegex, phone)) {
                    list.add(txtname + "-" + gender + "-" + phone + "-" + hometown  + hobby1 + "-" + hobby2 + "-" + hobby3);
                    adapter.notifyDataSetChanged();
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK){
            if(requestCode==GALLERY_REQ_CODE){
                imageView.setImageURI(data.getData());
            }
        }
    }
}