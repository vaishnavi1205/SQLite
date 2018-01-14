package com.swishsoftwaresolutions.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by DELL on 12/16/2017.
 */

public class DataBase extends SQLiteOpenHelper {
    ModuleClass moduleClass = new ModuleClass();

    private static final String TAG = "DATABASE";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "infoData";
    //column names
    private static final String TABLE_NAME = "publicInfo";
    private static final String KEY_ID = "id";
    private static final String NAME = "name";
    private static final String PH_NUMBER = "ph_number";
    //queries
    private static final String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT," + PH_NUMBER + " TEXT" +")";

    private static final String DROP_QUERY = "DROP TABLE IF EXISTS "+TABLE_NAME;
    private static final String SELECT_QUERY = "SELECT * FROM "+TABLE_NAME;


    public DataBase(Context context) {

        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(DROP_QUERY);
        onCreate(sqLiteDatabase);
    }
    //adding row to database
    public void addData(String name,String phnum){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME,name);
        values.put(PH_NUMBER,phnum);
        db.insert(TABLE_NAME,null,values);
        db.close();
        Log.i(TAG, "Data Inserted");
    }

    public ArrayList<ModuleClass> retriveData(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ModuleClass> arrayList = new ArrayList<>();
        Cursor cursor = db.rawQuery(SELECT_QUERY,null);
        if(cursor.moveToFirst()){
            for(int i = 0;i<cursor.getCount();i++){
                moduleClass = new ModuleClass();
                moduleClass.id = Integer.parseInt(cursor.getString(0));
                moduleClass.name1 = cursor.getString(1);
                moduleClass.ph_number = cursor.getString(2);
                arrayList.add(moduleClass);
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return arrayList;
    }

    public void updateData(String name,String phnum,int ID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME,name);
        values.put(PH_NUMBER,phnum);
        db.update(TABLE_NAME,values,KEY_ID +"= ?",new String[]{String.valueOf(ID)});
        db.close();
    }

    public void deleteData(int ID){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,KEY_ID + "= ?", new String[]{String.valueOf(ID)});
    }
}
