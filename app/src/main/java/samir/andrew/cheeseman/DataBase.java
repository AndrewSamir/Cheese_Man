package samir.andrew.cheeseman;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Andrew Samir on 6/6/2016.
 */
public class DataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DBase";
    private static final int DATABASE_VERSION = 3;


    public static final String TBL_USER = "user";
    public static final String USER_ID = "_id";
    public static final String USER_NAME = "name";

    private static String SCORE="score";
    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TBL_USER + "(" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + USER_NAME
            + " TEXT ,"+ SCORE + " INTEGER )";


    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TBL_USER);
    }

    public boolean addUser( String name,int score)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_NAME, name);
        values.put(SCORE, score);

        // insert row
        long user_row = db.insertWithOnConflict(TBL_USER, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        return user_row!=-1;
    }
    public Cursor getusers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TBL_USER + " ORDER BY "+SCORE +" DESC ", null);
    }

}
