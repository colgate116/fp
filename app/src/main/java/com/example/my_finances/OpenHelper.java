package com.example.my_finances;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class OpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="finance.db";
    public static final int DATABASE_VERSION=1;
    public static final String TABLE_NAME="cards";
    public static final String COLUMN_NAME="name";
    public static final String COLUMN_SCORE="score";
    public static final String COLUMN_ANNOTATION="annotation";
    public static final String COLUMN_FAVOURITE="favourite";

    public static final String TABLE_NAME2="transactions";
    public static final String COLUMN_CATEGORY="category";
    public static final String COLUMN_SUM="sum";
    public static final String COLUMN_TYPE="type";
    public static final String COLUMN_DESCRIPTION="description";
    public static final String COLUMN_CARD_ID="card_id";

    public static final String TABLE_NAME3="income";
    public static final String COLUMN_TRANSACTIONS_NAME="transactions_name";

    public OpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery="CREATE TABLE "+TABLE_NAME+"( _id integer primary key autoincrement, "+
                COLUMN_NAME+" text not null unique, "+
                COLUMN_SCORE+" integer not null, "+
                COLUMN_ANNOTATION+" text, "+
                COLUMN_FAVOURITE+" integer not null);";
        db.execSQL(sqlQuery);
        sqlQuery="Insert into "+TABLE_NAME+" ("+COLUMN_NAME+
                ", "+COLUMN_SCORE+", "+
                COLUMN_ANNOTATION+", "+
                COLUMN_FAVOURITE+") values (\"Наличные\", 0,\"\",0);";
        db.execSQL(sqlQuery);
        sqlQuery="Insert into "+TABLE_NAME+" ("+COLUMN_NAME+
                ", "+COLUMN_SCORE+", "+
                COLUMN_ANNOTATION+", "+
                COLUMN_FAVOURITE+") values (\"карта 1\", 0,\"\",0);";
        db.execSQL(sqlQuery);
        sqlQuery="CREATE TABLE "+TABLE_NAME2+"( _id integer primary key autoincrement, "+
                COLUMN_CATEGORY+" integer not null, "+
                COLUMN_SUM+" integer not null, "+
                COLUMN_TYPE+" integer not null, "+
                COLUMN_DESCRIPTION+" text, "+
                COLUMN_CARD_ID+" text not null);";
        db.execSQL(sqlQuery);
        sqlQuery="CREATE TABLE "+TABLE_NAME3+"( _id integer primary key autoincrement, "+
                COLUMN_TRANSACTIONS_NAME+" text not null unique);";
        db.execSQL(sqlQuery);
        sqlQuery="Insert into "+TABLE_NAME3+" ("+COLUMN_TRANSACTIONS_NAME+ ") values (\"Транспорт\");";
        db.execSQL(sqlQuery);
        sqlQuery="Insert into "+TABLE_NAME3+" ("+COLUMN_TRANSACTIONS_NAME+ ") values (\"Продукты\");";
        db.execSQL(sqlQuery);
        sqlQuery="Insert into "+TABLE_NAME3+" ("+COLUMN_TRANSACTIONS_NAME+ ") values (\"Одежда\");";
        db.execSQL(sqlQuery);
        sqlQuery="Insert into "+TABLE_NAME3+" ("+COLUMN_TRANSACTIONS_NAME+ ") values (\"Развлечения\");";
        db.execSQL(sqlQuery);
        sqlQuery="Insert into "+TABLE_NAME3+" ("+COLUMN_TRANSACTIONS_NAME+ ") values (\"Бытовые товары\");";
        db.execSQL(sqlQuery);
        sqlQuery="Insert into "+TABLE_NAME3+" ("+COLUMN_TRANSACTIONS_NAME+ ") values (\"Лекарства\");";
        db.execSQL(sqlQuery);
        sqlQuery="Insert into "+TABLE_NAME3+" ("+COLUMN_TRANSACTIONS_NAME+ ") values (\"Налоги\");";
        db.execSQL(sqlQuery);
        sqlQuery="Insert into "+TABLE_NAME3+" ("+COLUMN_TRANSACTIONS_NAME+ ") values (\"Кредиты\");";
        db.execSQL(sqlQuery);
        sqlQuery="Insert into "+TABLE_NAME3+" ("+COLUMN_TRANSACTIONS_NAME+ ") values (\"Подарки\");";
        db.execSQL(sqlQuery);
        sqlQuery="Insert into "+TABLE_NAME3+" ("+COLUMN_TRANSACTIONS_NAME+ ") values (\"Мелкие покупки\");";
        db.execSQL(sqlQuery);
        sqlQuery="Insert into "+TABLE_NAME3+" ("+COLUMN_TRANSACTIONS_NAME+ ") values (\"Детские товары\");";
        db.execSQL(sqlQuery);
        sqlQuery="Insert into "+TABLE_NAME3+" ("+COLUMN_TRANSACTIONS_NAME+ ") values (\"Кафе\");";
        db.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        db.execSQL("drop table if exists "+TABLE_NAME2);
        db.execSQL("drop table if exists "+TABLE_NAME3);
    }
}
