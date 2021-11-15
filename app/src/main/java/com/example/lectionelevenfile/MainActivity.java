package com.example.lectionelevenfile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String[] students = {"Иванов", "Петров", "Сидорова"};
    String[] subj = {"История", "Математика", "Русский"};

    StudMark[] grades = new StudMark[20];
    ListView lvAll;
    ArrayAdapter<StudMark> adap;
    FileOutputStream fos;
    OutputStreamWriter osw;
    BufferedWriter bw;
    String fileName = "Ocenki";

    protected void fillStudMark()
    {
        int k1,k2,k3;
        StudMark student;
        for (int i=0; i<grades.length; i++)
        {
            k1 = (int)(3*Math.random());
            k2 = (int)(3*Math.random());
            k3 = 1+(int)(5*Math.random());
            student = new StudMark(students[k1], subj[k2], k3);
            grades[i] = student;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvAll = findViewById(R.id.lvAll);
        fillStudMark();
        adap = new ArrayAdapter<StudMark>(this, android.R.layout.simple_list_item_1, grades);
        lvAll.setAdapter(adap);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId==R.id.writeInternalF) {
            goWriteInternalFile();
        }
        if (itemId==R.id.clearList)
        {
            lvAll.setAdapter(null);
        }
        if (itemId==R.id.readFile)
        {
            goReadInternalFile();
        }

        return super.onOptionsItemSelected(item);
    }

    private void goReadInternalFile() {
        FileInputStream is;
        InputStreamReader isr;
        BufferedReader br;
        ArrayList<String> strList = new ArrayList<String>();
        try {
            is = openFileInput(fileName);
            isr =  new InputStreamReader(is);
            br = new BufferedReader(isr);

            String line = "";
            while ((line = br.readLine())!=null)
                strList.add(line+"\n");
            ArrayAdapter<String> adap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strList);
            lvAll.setAdapter(adap);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void goWriteInternalFile() {

        try {
            FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
            osw = new OutputStreamWriter(fos);
            bw = new BufferedWriter(osw);
            for (int i=0; i<grades.length; i++)
                bw.write(grades[i].toString()+"\n");
            bw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}