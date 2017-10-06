package com.gemi.ahmedgemi.movie_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmed Gemi on 13/10/2017.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Movie_Favourites";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // table named Favourites with columns (ID,Title,Pub_Date,Rate,Poster_Url,Reviews,Videos_Key)...

        db.execSQL("CREATE TABLE Favourites (ID INTEGER PRIMARY KEY, Title VARCHAR, Overview VARCHAR, Pub_Date VARCHAR, " +
                "Rate VARCHAR, Poster_Url VARCHAR);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onUpgrade(db,oldVersion,newVersion);

    }

    public static class database {


        SQLiteDatabase db;
        private DBHelper helper;
        // Context context;


        public database(Context context) {
            helper = new DBHelper(context);
            db = helper.getReadableDatabase();
        }

        public boolean add_to_favourites(int id, String title, String overview, String pub_date, String rate, String poster_url) {
            // implementation for adding movies to favourites...

            //movie_class=new Movie_Class();
            ContentValues contentValues = new ContentValues();

            contentValues.put("ID", id);
            contentValues.put("Title", title);
            contentValues.put("Overview", overview);
            contentValues.put("Pub_Date", pub_date);
            contentValues.put("Rate", rate);
            contentValues.put("Poster_Url", poster_url);
           /* contentValues.put("Reviews", reviews);
            contentValues.put("Videos_Key", videos_key);*/
            db.insert("Favourites", null, contentValues);


            return true;

        }

        public List<Movie_Class> get_from_favourites() {

            // implementation for getting favourites...
            List<Movie_Class> data = new ArrayList<>();

            Cursor c = db.rawQuery("select * from Favourites ", null);
            int _id = c.getColumnIndex("ID");
            int _title = c.getColumnIndex("Title"); //
            int _overview = c.getColumnIndex("Overview");
            int _date = c.getColumnIndex("Pub_Date"); //
            int _rate = c.getColumnIndex("Rate"); //
            int _image = c.getColumnIndex("Poster_Url");

            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                Movie_Class movie_class = new Movie_Class(c.getInt(_id), c.getString(_image),
                        c.getString(_date), c.getString(_overview), c.getString(_title)
                        , c.getString(_rate));
                data.add(movie_class);

            }

            return data;

        }


        public void close_db() {
            db.close();
        }
    }
}
