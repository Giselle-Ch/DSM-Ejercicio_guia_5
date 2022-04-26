package edu.udb.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etCodigo, etNombre, etEdad, etTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCodigo = (EditText) findViewById(R.id.etCodigo);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etEdad = (EditText) findViewById(R.id.etEdad);
        etTelefono = (EditText) findViewById(R.id.etTelefono);
    }

    public void alta(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String cod = etCodigo.getText().toString();
        String nom = etNombre.getText().toString();
        String edad = etEdad.getText().toString();
        String tel = etTelefono.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("codigo", cod);
        registro.put("nombre", nom);
        registro.put("edad", edad);
        registro.put("telefono", tel);

        db.insert("personas", null, registro);

        db.close();

        etCodigo.setText("");
        etNombre.setText("");
        etEdad.setText("");
        etTelefono.setText("");

        Toast.makeText(this, "Se cargaron los datos de la persona", Toast.LENGTH_SHORT).show();
    }

    public void consutlaporcodigo(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String cod = etCodigo.getText().toString();

        Cursor fila = db.rawQuery("select nombre, edad, telefono from personas where codigo=" + cod, null);

        if(fila.moveToFirst()) {
            etNombre.setText(fila.getString(0));
            etEdad.setText(fila.getString(1));
            etTelefono.setText(fila.getString(2));
        } else
            Toast.makeText(this, "No existe una persona registrada con dicho código", Toast.LENGTH_SHORT).show();

        db.close();
    }

    public void consultapornombre(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String nom = etNombre.getText().toString();

        Cursor fila = db.rawQuery("select codigo, edad, telefono from personas where nombre='" + nom + "'", null);

        if(fila.moveToFirst()) {
            etCodigo.setText(fila.getString(0));
            etEdad.setText(fila.getString(1));
            etTelefono.setText(fila.getString(2));
        } else
            Toast.makeText(this, "No existe una persona registrada con dicho nombre", Toast.LENGTH_SHORT).show();

        db.close();
    }

    public void bajaporcodigo(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String cod = etCodigo.getText().toString();

        int cant = db.delete("personas", "codigo=" + cod, null);

        db.close();

        etCodigo.setText("");
        etNombre.setText("");
        etEdad.setText("");
        etTelefono.setText("");

        if(cant == 1)
            Toast.makeText(this, "Se borró la persona registrada con dicho código", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe una persona registrada con dicho código", Toast.LENGTH_SHORT).show();
    }

    public void modificacion(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String cod = etCodigo.getText().toString();
        String nom = etNombre.getText().toString();
        String edad = etEdad.getText().toString();
        String tel = etTelefono.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("codigo", cod);
        registro.put("nombre", nom);
        registro.put("edad", edad);
        registro.put("telefono", tel);

        int cant = db.update("personas", registro, "codigo=" + cod, null);
        db.close();

        if(cant == 1)
            Toast.makeText(this, "Se modificacion los datos", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe una persona con el código ingresado", Toast.LENGTH_SHORT).show();
    }
}