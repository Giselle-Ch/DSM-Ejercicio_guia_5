package edu.udb.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Formulario extends AppCompatActivity {

    private EditText etCarnet, etNombre, etApellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etCarnet = (EditText) findViewById(R.id.etCarnet);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellido = (EditText) findViewById(R.id.etApellido);
    }

    public void pantallainicial(View v){
        Intent cambio = new Intent(Formulario.this, MainActivity.class);
        startActivity(cambio);
    }

    public void agregarestudiante(View v) {
        DatabaseHelper admin = new DatabaseHelper(this, "students.db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String carnet = etCarnet.getText().toString();
        String nombre = etNombre.getText().toString();
        String apellido = etApellido.getText().toString();

        ContentValues values = new ContentValues();
        values.put(StudentsContract.Columnas.NOMBRE, nombre);
        values.put(StudentsContract.Columnas.APELLIDO, apellido);
        values.put(StudentsContract.Columnas.CARNET, carnet);
        db.insert(StudentsContract.STUDENTS, null, values);
        db.close();

        Toast.makeText(this, "Se cargaron los datos de la persona", Toast.LENGTH_SHORT).show();

        etCarnet.setText("");
        etNombre.setText("");
        etApellido.setText("");

    }
}