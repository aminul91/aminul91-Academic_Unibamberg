package com.example.cowcounter;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    TextView cowsCount;
    EditText breed, id;
    Button addButton, rejectButtion, clearButton;
    ListView cowsList;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);
        breed = (EditText)findViewById(R.id.breedText);
        id = (EditText)findViewById(R.id.idText);
        addButton = (Button)findViewById(R.id.addButton);
        rejectButtion = (Button)findViewById(R.id.rejectButton);
        clearButton = (Button)findViewById(R.id.clearButton);
        cowsList = (ListView)findViewById(R.id.cow_list);
        cowsCount = (TextView)findViewById(R.id.countField);
        addButton();
        rejectButton();
        refreshCowList();
        clearButton();
    }

    public void addButton() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkValidity()) {
                    CowDTO cowDTO = new CowDTO(Integer.parseInt(breed.getText().toString()), Integer.parseInt(id.getText().toString()));
                    boolean isAdded = myDb.insertData(cowDTO);
                    if (isAdded){
                        Toast.makeText(MainActivity.this, "Cow added successfully!", Toast.LENGTH_LONG).show();
                        clearInput();
                    } else {
                        Toast.makeText(MainActivity.this, "Unfortunately cow was not added. Try again later!", Toast.LENGTH_LONG).show();
                    }
                    refreshCowList();
                }
            }
        });
    }

    public void rejectButton() {
        rejectButtion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearInput();
            }
        });
    }

    public boolean checkValidity() {
        CowDTO cowDTO = null;
        if(breed.getText().length() <= 0 || id.getText().length() <= 0) {
            Toast.makeText(MainActivity.this, "Please insert valid entry for both id and breed!!", Toast.LENGTH_LONG).show();
            return false;
        } else {
            cowDTO = new CowDTO(Integer.parseInt(breed.getText().toString()), Integer.parseInt(id.getText().toString()));
        }
        if(myDb.alreadyExist(cowDTO)) {
            Toast.makeText(MainActivity.this, "Cow already exists!!!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public void clearInput() {
        id.setText("");
        breed.setText("");
    }

    public void refreshCowList() {
        ArrayList<CowDTO> cowsArray = new ArrayList<>();
        Cursor cows = myDb.getAllCows();

        if(cows.getCount() > 0) {
            while(cows.moveToNext()) {
                CowDTO cowDTO = new CowDTO(Integer.parseInt(cows.getString(0)),Integer.parseInt(cows.getString(1)));
                cowsArray.add(cowDTO);
            }

            adapter = new ListAdapter(this, R.layout.list_view, cowsArray);
            cowsList.setAdapter(adapter);

        } else {
            if(adapter != null) {
                adapter.clear();
                adapter.notifyDataSetChanged();
            }
            Toast.makeText(MainActivity.this, "List Cleared", Toast.LENGTH_LONG).show();
        }

        cowsCount.setText("Cows: "+cows.getCount());
    }

    public void clearButton() {
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myDb.removeAllCows() > 0) {
                    refreshCowList();
                }
            }
        });
    }
}
