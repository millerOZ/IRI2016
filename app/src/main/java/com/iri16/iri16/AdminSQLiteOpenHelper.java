package com.iri16.iri16;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by frontier on 3/03/16.
 */
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table integrantes(cc integer primary key,nombre text,grupo text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int VersionAnterior, int VersionPosterior) {
        db.execSQL("drop table if exists integrantes");
        db.execSQL("create table integrantes(cc integer primary key,nombre text,grupo text)");
    }
}
