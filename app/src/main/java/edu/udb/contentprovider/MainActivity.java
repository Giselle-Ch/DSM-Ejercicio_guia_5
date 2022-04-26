package edu.udb.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.content.CursorLoader;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends ListActivity implements
        LoaderCallbacks<Cursor> {

    private StudentsAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adaptador = new StudentsAdapter(this);
        setListAdapter(adaptador);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                this,
                StudentsContract.CONTENT_URI,
                null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adaptador.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adaptador.swapCursor(null);
    }

    public void pantallaAgregarEstudiante (View v) {
        Intent cambio = new Intent(MainActivity.this, Formulario.class);
        startActivity(cambio);
    }
}