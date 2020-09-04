package br.edu.ifsp.trabalho1pdm.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class DBAccess {
    private DBOpenHelper dbHelper;
    private SQLiteDatabase db;
    private String table = "pessoa";

    public DBAccess(Context context){
        dbHelper = new DBOpenHelper(context);
    }

    public void open() throws SQLException{
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        if(db != null){
            db.close();
        }
    }
    public void deletePessoa(int id) {
        open();
        db.delete(table,"id = " + id, null);
        close();
    }
    public void insertPessoa(Pessoa p){
        open();

        ContentValues cv = new ContentValues();
        cv.put("nome",p.getNome());
        cv.put("email", p.getEmail());
        cv.put("idade", p.getIdade());
        cv.put("celular", p.getCelular());

        db.insert(table,null, cv);
        close();
    }
    public void updatePessoa(Pessoa p){
        open();
        ContentValues cv = new ContentValues();
        cv.put("nome",p.getNome());
        cv.put("email", p.getEmail());
        cv.put("idade", p.getIdade());
        cv.put("celular", p.getCelular());

        db.update(table,cv,"id =" + p.getId(),null);

        close();
    }

    public Pessoa selectPessoa(int id){
        Pessoa p = null;

        open();

        String[] columns = new String[] {"id","nome","email","idade","celular"};

        Cursor c = db.query(table,columns,"id=" + id,null,null,null,null);

        if(c.getCount() > 0){
            c.moveToFirst();
            p = new Pessoa(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2),
                    c.getInt(3),
                    c.getString(4));
        }
        close();

        return p;
    }

    public ArrayList<Pessoa> selectAll(){
        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
        Pessoa p = null;

        open();

        String[] columns = new String[] {"id","nome","email","idade","celular"};

        Cursor c = db.query(
                table,
                columns,
                null,
                null,
                null,
                null,
                "id"
        );
        if(c.getCount() > 0){
            c.moveToFirst();

            do {
                p = new Pessoa(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getInt(3),
                        c.getString(4));

                pessoas.add(p);
            }while (c.moveToNext());
        }
        close();

        return pessoas;
    }
}
