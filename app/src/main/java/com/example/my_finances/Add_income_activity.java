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

public class Add_income_activity extends AppCompatActivity {
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

            setContentView(R.layout.activity_add_income);

            openHelper=new OpenHelper(getApplicationContext());
            database=openHelper.getWritableDatabase();

            score=findViewById(R.id.add_sum1);
            ann=findViewById(R.id.annotation_dialog1);
            back=findViewById(R.id.back_income1);
            save=findViewById(R.id.save_income1);
            toolBar=findViewById(R.id.actionBar_text);
            toolBar.setText(R.string.add_income);
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
                if(!acc.equals("")&&!card.equals("")) {
                    double num = Double.parseDouble(acc);
                    double num1=Double.parseDouble(acc);
                    Cursor cursor1=database.query(OpenHelper.TABLE_NAME,
                            new String[]{OpenHelper.COLUMN_NAME,OpenHelper.COLUMN_SCORE
                            },null,null,null,null,null);
                    cursor1.move(0);
                    while (cursor1.moveToNext()){
                        @SuppressLint("Range") String name=cursor1.getString(cursor1.getColumnIndex(OpenHelper.COLUMN_NAME));
                        if(name.equals(card))
                            num+=cursor1.getInt(cursor1.getColumnIndex(OpenHelper.COLUMN_SCORE));
                    }
                    cursor1.close();
                    Intent intent=new Intent(Add_income_activity.this,MainActivity.class);
                        ContentValues values = new ContentValues();
                        values.put(OpenHelper.COLUMN_SUM, num1);
                        values.put(OpenHelper.COLUMN_TYPE, 1);
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
            });

        }



        @Override
        protected void onDestroy() {
            super.onDestroy();
            database.close();
        }
}