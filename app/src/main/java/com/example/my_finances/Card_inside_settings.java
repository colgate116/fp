package com.example.my_finances;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Card_inside_settings extends AppCompatActivity {
    TextView actionBar_text;
    ImageButton back, delete;
    EditText acc,description;
    OpenHelper openHelper;
    SQLiteDatabase database;
    ArrayList array=new ArrayList<>();
    RecyclerView recyclerView;
    String des="";
    Double account=0.0;
    String tittle="";
    String score="";
    Button plus, minus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        openHelper=new OpenHelper(getApplicationContext());
        setContentView(R.layout.card_inside_settings);
        description=findViewById(R.id.descr);
        delete =findViewById(R.id.delete);
        acc=findViewById(R.id.ET_acc_in);
        actionBar_text=findViewById(R.id.actionBar_text);
        back=findViewById(R.id.back);
recyclerView=findViewById(R.id.list);
plus=findViewById(R.id.plusBILLS1);
minus=findViewById(R.id.minusBILLS1);

        database=openHelper.getWritableDatabase();


        plus.setOnClickListener(v -> {
            Intent intent1=new Intent(Card_inside_settings.this,Add_income_activity.class);
           startActivity(intent1);
            finish();
        });

        minus.setOnClickListener(v -> {
            Intent intent1=new Intent(Card_inside_settings.this,add_activity.class);
           startActivity(intent1);
            finish();
        });

        Bundle extra=getIntent().getExtras();
        if(extra!=null){
            tittle=extra.getString("card_give_tittle");
            account=extra.getDouble("card_give_score");
            des=extra.getString("card_give_annotation");
        }

        if (account%1==0){
            DecimalFormat decimalFormat=new DecimalFormat("#.###");
            score=decimalFormat.format(account);
        }
        else
            score=String.format("%.2f",account);
        Intent intent=new Intent(Card_inside_settings.this,MainActivity.class);
        acc.setText(score);
        actionBar_text.setText(tittle);
        description.setText(des);
String tt=tittle;
        back.setOnClickListener(v -> {
            String score1 =acc.getText().toString();
            String des1 =description.getText().toString();
            String tittle1 =actionBar_text.getText().toString();
            double sc=Double.parseDouble(score1);
            String sqlQuery="Update "+OpenHelper.TABLE_NAME+" set "+OpenHelper.COLUMN_NAME+"=\""+ tittle1 +"\", "+
                    OpenHelper.COLUMN_SCORE+"="+sc+", "+OpenHelper.COLUMN_ANNOTATION+"=\""+ des1 +"\" where "+OpenHelper.COLUMN_NAME+"=\""
                    +tt+"\";";
            database.execSQL(sqlQuery);
            database.close();
            startActivity(intent);
            finish();
        });

        delete.setOnClickListener(v -> {
            Button yes,no;
            String tittle1 =actionBar_text.getText().toString();

          Dialog dialog=new Dialog(Card_inside_settings.this);
            dialog.setContentView(R.layout.check_delete);

            yes=dialog.findViewById(R.id.delete_yes);
            no=dialog.findViewById(R.id.delete_no);

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


            yes.setOnClickListener(v1 -> {
                String sqlQuery="Delete from "+OpenHelper.TABLE_NAME+" where "+OpenHelper.COLUMN_NAME+"=\""+ tittle1+"\";";
                database.execSQL(sqlQuery);
                database.close();
                startActivity(intent);
                finish();
            });
            no.setOnClickListener(v12 -> {
                dialog.cancel();
            });
            dialog.show();
        });
        addItems();
        RecyclerAdapter_transactions adapter=new RecyclerAdapter_transactions(array);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void addItems() {
        boolean inc;
        String catStr;
            Cursor cursor=database.query(OpenHelper.TABLE_NAME2,
                    new String[]{OpenHelper.COLUMN_CATEGORY,
                            OpenHelper.COLUMN_SUM,
                            OpenHelper.COLUMN_TYPE,
                            OpenHelper.COLUMN_CARD_ID
                    },null,null,null,null,null);
            cursor.move(0);
            while (cursor.moveToNext()){
                @SuppressLint("Range") String name=cursor.getString(cursor.getColumnIndex(OpenHelper.COLUMN_CARD_ID));
                @SuppressLint("Range") double score=cursor.getDouble(cursor.getColumnIndex(OpenHelper.COLUMN_SUM));
                @SuppressLint("Range") int type=cursor.getInt(cursor.getColumnIndex(OpenHelper.COLUMN_TYPE));
                if (type==1)
                    inc=true;
                else
                    inc=false;
                @SuppressLint("Range") int category=cursor.getInt(cursor.getColumnIndex(OpenHelper.COLUMN_CATEGORY));
                switch (category){
                    case 1: catStr="Транспорт";
                    break;
                    case 2: catStr="Продукты";
                        break;
                    case 3: catStr="Одежда";
                        break;
                    case 4: catStr="Развлечения";
                        break;
                    case 5: catStr="Дом";
                        break;
                    case 6: catStr="Подарки";
                        break;
                    case 7: catStr="Долги";
                        break;
                    case 8: catStr="Лекарства";
                        break;
                    case 9: catStr="Кредит";
                        break;
                    case 10: catStr="Кафе";
                        break;
                    case 11: catStr="Семья";
                        break;
                    case 12: catStr="Мелочи";
                        break;
                    default:catStr="";

                }
                if(name.equals(tittle))
                array.add(new Transactions(inc,score,catStr));
            }
        cursor.close();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();

    }
}