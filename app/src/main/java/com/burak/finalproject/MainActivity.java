package com.burak.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText nameSurname, class_no, mail, age, sector;
    DatabaseReference connection = FirebaseDatabase.getInstance().getReference("Internship");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameSurname = (EditText)findViewById(R.id.et_name);
        class_no = (EditText)findViewById(R.id.et_classNo);
        mail = (EditText) findViewById(R.id.et_mail);
        age = (EditText) findViewById(R.id.et_age);
        sector = (EditText) findViewById(R.id.et_sector);

    }

    public void saveToDatabase(View view){
        if (nameSurname.getText().toString().equals("")||
                class_no.getText().toString().equals("")||
                mail.getText().toString().equals("")||
                age.getText().toString().equals("")||
                sector.getText().toString().equals("")){

            Toast.makeText(getApplicationContext(),"Alanlar boş bırakılamaz!",Toast.LENGTH_SHORT).show();

        }else {
            String id = connection.push().getKey();
            Internship newIntern = new Internship(id, nameSurname.getText().toString(), class_no.getText().toString(),
                    mail.getText().toString(), age.getText().toString(), sector.getText().toString(), "Aksiyon bekliyor...");
            connection.child(id).setValue(newIntern);

            nameSurname.setText("");
            class_no.setText("");
            mail.setText("");
            age.setText("");
            sector.setText("");

            Toast.makeText(getApplicationContext(), "Kaydınız başarıyla alınmıştır.", Toast.LENGTH_SHORT).show();
        }
    }

}