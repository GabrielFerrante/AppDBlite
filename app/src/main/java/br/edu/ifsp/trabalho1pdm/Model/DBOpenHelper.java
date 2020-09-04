package br.edu.ifsp.trabalho1pdm.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "trabalho1PDMdb";
    private static final int DB_VERSION = 1;

    public DBOpenHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDB = "CREATE TABLE pessoa (" +
                "    id  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "    nome    STRING," +
                "    idade   INTEGER," +
                "    email   STRING," +
                "    celular STRING" +
                ");";
        db.execSQL(createDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
