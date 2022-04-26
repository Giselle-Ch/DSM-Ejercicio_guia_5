package edu.udb.contentprovider;

import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        createTable(database);  //Crea la tabla Students
        loadDummyData(database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {

    }

    //Crear tabla en la base de datos
    private void createTable(SQLiteDatabase database){
        String cmd = "CREATE TABLE " + StudentsContract.STUDENTS + " (" +
                StudentsContract.Columnas._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                StudentsContract.Columnas.NOMBRE + " TEXT, " +
                StudentsContract.Columnas.APELLIDO + " TEXT, " +
                StudentsContract.Columnas.CARNET + " TEXT);";
        database.execSQL(cmd);
    }

    //Carga de datos de ejemplo en la tabla
    private void loadDummyData(SQLiteDatabase database){
        ContentValues values = new ContentValues();
        values.put(StudentsContract.Columnas.NOMBRE, "Hugo");
        values.put(StudentsContract.Columnas.APELLIDO, "Valencia");
        values.put(StudentsContract.Columnas.CARNET, "GV050221");
        database.insert(StudentsContract.STUDENTS, null, values);

        values = new ContentValues();
        values.put(StudentsContract.Columnas.NOMBRE, "Hugo2");
        values.put(StudentsContract.Columnas.APELLIDO, "Valencia");
        values.put(StudentsContract.Columnas.CARNET, "GV050222");
        database.insert(StudentsContract.STUDENTS, null, values);

        values = new ContentValues();
        values.put(StudentsContract.Columnas.NOMBRE, "Hugo3");
        values.put(StudentsContract.Columnas.APELLIDO, "Valencia");
        values.put(StudentsContract.Columnas.CARNET, "GV050223");
        database.insert(StudentsContract.STUDENTS, null, values);

        values = new ContentValues();
        values.put(StudentsContract.Columnas.NOMBRE, "Hugo4");
        values.put(StudentsContract.Columnas.APELLIDO, "Valencia");
        values.put(StudentsContract.Columnas.CARNET, "GV050224");
        database.insert(StudentsContract.STUDENTS, null, values);

        values = new ContentValues();
        values.put(StudentsContract.Columnas.NOMBRE, "Hugo5");
        values.put(StudentsContract.Columnas.APELLIDO, "Valencia");
        values.put(StudentsContract.Columnas.CARNET, "GV050225");
        database.insert(StudentsContract.STUDENTS, null, values);
    }
}
