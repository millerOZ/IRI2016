package com.iri16.iri16;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class acercaDe extends AppCompatActivity {

    private EditText cc1,nombre1,grupo1;
    private Cursor fila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);

        cc1 =(EditText)findViewById(R.id.editTextcc);
        nombre1 = (EditText)findViewById(R.id.editTextNomb);
        grupo1 = (EditText)findViewById(R.id.editTextgru);
    }

    public void registrar(View v)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase bd= admin.getWritableDatabase();
        String cc  = cc1.getText().toString();
        String nombre  = nombre1.getText().toString();
        String grupo  = grupo1.getText().toString();

        ContentValues registro = new ContentValues(); //clase para guardar registros

        registro.put("cc",cc);
        registro.put("nombre",nombre);
        registro.put("grupo",grupo);

        bd.insert("integrantes", null, registro);
        bd.close();
        cc1.setText("");
        nombre1.setText("");
        grupo1.setText("");

        Toast.makeText(this,"registro exitoso", Toast.LENGTH_SHORT).show();

    }

    public void consulta(View v)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase bd= admin.getWritableDatabase();

        String cc = cc1.getText().toString();

        Cursor fila = bd.rawQuery(  //devuelve 0 o 1 fila // es una consulta
                "select cc,nombre,grupo from integrantes where cc =" + cc, null);

        if(fila.moveToFirst())
        {
            cc1.setText(fila.getString(0));
            cc1.setText(fila.getString(1));
            cc1.setText(fila.getString(2));
        }else
            Toast.makeText(this,"no existe una persona con dicha cc", Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void borrar (View v)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase bd= admin.getWritableDatabase();

        String cc = cc1.getText().toString();

        int cant = bd.delete("integrantes", "cc=" + cc, null);
        bd.close();

        cc1.setText("");
        nombre1.setText("");
        grupo1.setText("");

        if(cant == 1)
            Toast.makeText(this,"se borro el integrante con dicha cc", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"no existe un integrante con dicha cc",Toast.LENGTH_SHORT).show();
    }

    public void modificacion  (View v)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase bd= admin.getWritableDatabase();

        String cc  = cc1.getText().toString();
        String nombre  = nombre1.getText().toString();
        String grupo  = grupo1.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("nombre", nombre);
        registro.put("grupo", grupo);

        int cant = bd.update("integrantes", registro, "cc=" + cc, null);
        bd.close();
        if(cant == 1)
            Toast.makeText(this,"se modificaron los datos", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"no existe un integrante con dicha cc",Toast.LENGTH_SHORT).show();

    }

    public void inicio (View v)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase bd= admin.getWritableDatabase();

        Cursor fila = bd.rawQuery("select * from integrantes order by cc asc", null);

        if (fila.moveToFirst())
        {
            cc1.setText(fila.getString(0));
            nombre1.setText(fila.getString(1));
            grupo1.setText(fila.getString(2));
        }else
            Toast.makeText(this,"No hay registros",Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void anterior (View v)
    {
        try{
            if(!fila.isFirst())
            {
                fila.moveToPrevious();
                cc1.setText(fila.getString(0));
                nombre1.setText(fila.getString(1));
                grupo1.setText(fila.getString(2));
            }else
                Toast.makeText(this," Llego al primer registro",Toast.LENGTH_SHORT).show();
        }catch(Exception e)
        {
           e.printStackTrace();
        }

    }

    public void siguiente (View v)
    {
        try{
            if(!fila.isLast())
            {
                fila.moveToNext();
                cc1.setText(fila.getString(0));
                nombre1.setText(fila.getString(1));
                grupo1.setText(fila.getString(2));
            }else
                Toast.makeText(this," Llego al ultimo registro",Toast.LENGTH_SHORT).show();
        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public void fin(View v)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase bd= admin.getWritableDatabase();

        Cursor fila = bd.rawQuery("select * from integrantes order by cc asc", null);

        if (fila.moveToLast())
        {
            cc1.setText(fila.getString(0));
            nombre1.setText(fila.getString(1));
            grupo1.setText(fila.getString(2));
        }else
            Toast.makeText(this,"No hay registros",Toast.LENGTH_SHORT).show();
        bd.close();
    }






}
