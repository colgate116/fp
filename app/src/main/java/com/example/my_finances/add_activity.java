package com.example.my_finances;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class add_activity extends AppCompatActivity {
    ImageButton back,save;
    TextView toolBar;
    Spinner spinner;
    OpenHelper openHelper;
    SQLiteDatabase database;
    EditText score,ann;
    int category=0;
    boolean ch=false;
    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setContentView(R.layout.income);

        openHelper=new OpenHelper(getApplicationContext());
        database=openHelper.getWritableDatabase();

        ImageButton b1=findViewById(R.id.ras1);
        ImageButton b2=findViewById(R.id.ras2);
        ImageButton b3=findViewById(R.id.ras3);
        ImageButton b4=findViewById(R.id.ras4);
        ImageButton b5=findViewById(R.id.ras5);
        ImageButton b6=findViewById(R.id.ras6);
        ImageButton b7=findViewById(R.id.ras7);
        ImageButton b8=findViewById(R.id.ras8);
        ImageButton b9=findViewById(R.id.ras9);
        ImageButton b10=findViewById(R.id.ras10);
        ImageButton b11=findViewById(R.id.ras11);
        ImageButton b12=findViewById(R.id.ras12);

        b1.setOnClickListener(v -> {
            if(!ch) {
                category = 1;
                ch=true;
                b1.setScaleX((float) 1.2);
                b1.setScaleY((float) 1.2);
            }
            else {
                ch = false;
                category = 0;
                b1.setScaleX(1);
                b1.setScaleY(1);
            }
        });
        b2.setOnClickListener(v -> {
            if(!ch) {
                category = 2;
                ch=true;
                b2.setScaleX((float) 1.2);
                b2.setScaleY((float) 1.2);
            }
            else {
                ch = false;
                category = 0;
                b2.setScaleX(1);
                b2.setScaleY(1);
            }
        });
        b3.setOnClickListener(v -> {
            if(!ch) {
                category = 3;
                ch=true;
                b3.setScaleX((float) 1.2);
                b3.setScaleY((float) 1.2);
            }
            else {
                ch = false;
                category = 0;
                b3.setScaleX(1);
                b3.setScaleY(1);
            }
        });
        b4.setOnClickListener(v -> {
            if(!ch) {
                category = 4;
                ch=true;
                b4.setScaleX((float) 1.2);
                b4.setScaleY((float) 1.2);
            }
            else {
                ch = false;
                category = 0;
                b4.setScaleX(1);
                b4.setScaleY(1);
            }
        });
        b5.setOnClickListener(v -> {
            if(!ch) {
                category = 5;
                ch=true;
                b5.setScaleX((float) 1.2);
                b5.setScaleY((float) 1.2);
            }
            else {
                ch = false;
                category = 0;
                b5.setScaleX(1);
                b5.setScaleY(1);
            }
        });
        b6.setOnClickListener(v -> {
            if(!ch) {
                category = 6;
                ch=true;
                b6.setScaleX((float) 1.2);
                b6.setScaleY((float) 1.2);
            }
            else {
                ch = false;
                category = 0;
                b6.setScaleX(1);
                b6.setScaleY(1);
            }
        });
        b7.setOnClickListener(v -> {
            if(!ch) {
                category = 7;
                ch=true;
                b7.setScaleX((float) 1.2);
                b7.setScaleY((float) 1.2);
            }
            else {
                ch = false;
                category = 0;
                b7.setScaleX(1);
                b7.setScaleY(1);
            }
        });
        b8.setOnClickListener(v -> {
            if(!ch) {
                category = 8;
                ch=true;
                b8.setScaleX((float) 1.2);
                b8.setScaleY((float) 1.2);
            }
            else {
                ch = false;
                category = 0;
                b8.setScaleX(1);
                b8.setScaleY(1);
            }
        });
        b9.setOnClickListener(v -> {
            if(!ch) {
                category = 9;
                ch=true;
                b9.setScaleX((float) 1.2);
                b9.setScaleY((float) 1.2);
            }
            else {
                ch = false;
                category = 0;
                b9.setScaleX(1);
                b9.setScaleY(1);
            }
        });
        b10.setOnClickListener(v -> {
            if(!ch) {
                category = 10;
                ch=true;
                b10.setScaleX((float) 1.2);
                b10.setScaleY((float) 1.2);
            }
            else {
                ch = false;
                category = 0;
                b10.setScaleX(1);
                b10.setScaleY(1);
            }
        });
        b11.setOnClickListener(v -> {
            if(!ch) {
                category = 11;
                ch=true;
                b11.setScaleX((float) 1.2);
                b11.setScaleY((float) 1.2);
            }
            else {
                ch = false;
                category = 0;
                b11.setScaleX(1);
                b11.setScaleY(1);
            }
        });
        b12.setOnClickListener(v -> {
            if(!ch) {
                category = 12;
                ch=true;
                b12.setScaleX((float) 1.2);
                b12.setScaleY((float) 1.2);
            }
            else {
                ch = false;
                category = 0;
                b12.setScaleX(1);
                b12.setScaleY(1);
            }
        });
  score=findViewById(R.id.add_sum);
  ann=findViewById(R.id.annotation_dialog);
        back=findViewById(R.id.back_income);
        save=findViewById(R.id.save_income);
        toolBar=findViewById(R.id.actionBar_text);
        toolBar.setText(R.string.add_expenditure);
        spinner=findViewById(R.id.spinner);
        ArrayList spinnerArray=new ArrayList();
        Cursor cursor=database.query(OpenHelper.TABLE_NAME,
                new String[]{OpenHelper.COLUMN_NAME
                },null,null,null,null,null);
        cursor.move(0);
        int count=0;
        while (cursor.moveToNext()){
            @SuppressLint("Range") String name=cursor.getString(cursor.getColumnIndex(OpenHelper.COLUMN_NAME));
          spinnerArray.add(count,name);
          count++;
        }
        cursor.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
               R.layout.row , R.id.spinner_list, spinnerArray);
        spinner.setAdapter(adapter);


        back.setOnClickListener(v ->{ onBackPressed();
            database.close();});
        save.setOnClickListener(v -> {
            String card=(String)spinner.getSelectedItem();
            String acc=score.getText().toString();
            String  des=ann.getText().toString();
            if(!acc.equals("")&&!card.equals("")&&category!=0) {
                double num = Double.parseDouble(acc);
                double num1=Double.parseDouble(acc);
                Cursor cursor1=database.query(OpenHelper.TABLE_NAME,
                        new String[]{OpenHelper.COLUMN_NAME,OpenHelper.COLUMN_SCORE
                        },null,null,null,null,null);
                cursor1.move(0);
                while (cursor1.moveToNext()){
                    @SuppressLint("Range") String name=cursor1.getString(cursor1.getColumnIndex(OpenHelper.COLUMN_NAME));
                    if(name.equals(card))
                        num-=cursor1.getInt(cursor1.getColumnIndex(OpenHelper.COLUMN_SCORE));
                }
                cursor1.close();
                Intent intent=new Intent(add_activity.this,MainActivity.class);
                num*=-1;
                if (!(num < 0)) {
                    ContentValues values = new ContentValues();
                    values.put(OpenHelper.COLUMN_SUM, num1);
                    values.put(OpenHelper.COLUMN_TYPE, 0);
                    values.put(OpenHelper.COLUMN_DESCRIPTION, des);
                    values.put(OpenHelper.COLUMN_CARD_ID, card);
                    values.put(OpenHelper.COLUMN_CATEGORY, category);
                    database.insert(OpenHelper.TABLE_NAME2, null, values);

                    String sqlQuery = "Update " + OpenHelper.TABLE_NAME + " set " + OpenHelper.COLUMN_SCORE + "=" + num + " where " + OpenHelper.COLUMN_NAME + "=\"" + card + "\";";
                    database.execSQL(sqlQuery);

                    database.close();
                    startActivity(intent);
                    finish();
                }

            }
        });

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();
    }
}