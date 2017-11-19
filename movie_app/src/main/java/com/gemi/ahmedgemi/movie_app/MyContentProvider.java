package com.gemi.ahmedgemi.movie_app;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by pc on 11/04/2017.
 */

public class MyContentProvider extends ContentProvider {

    public static final String DATABASE_NAME = "Movie_Favourites";
    private SQLiteDatabase db;
    static final String FAVOURITE_TABLE_NAME = "Favourites";
    static final String PROVIDER_NAME = "com.gemi.ahmedgemi.movie_app.MyContentProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/"+FAVOURITE_TABLE_NAME+"";
    static final Uri CONTENT_URI = Uri.parse(URL);

    private static HashMap<String, String> FAVOURITE_PROJECTION_MAP;

    static final int MOVIE = 1;
    static final int MOVIE_ID = 2;

    static final String _ID = "ID";
    static final String TITLE = "Title";
    static final String POSTER = "Poster_Url";
    static final String RATE="Rate";

    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "Favourites", MOVIE);
        uriMatcher.addURI(PROVIDER_NAME, "Favourites/#", MOVIE_ID);
    }



  public List<Movie_Class> get_from_favourites() {
  /*
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
*/
  return null;
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DBHelper dbHelper = new DBHelper(context);

        /**
         * Create a writable database which will trigger its
         * creation if it doesn't already exist.
         */

        db = dbHelper.getWritableDatabase();
        return (db == null)? false:true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(FAVOURITE_TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case MOVIE:
                qb.setProjectionMap(FAVOURITE_PROJECTION_MAP);
                break;

            case MOVIE_ID:
                qb.appendWhere( _ID + "=" + uri.getPathSegments().get(1));
                break;

            default:
        }

        if (sortOrder == null || sortOrder == ""){
            /**
             * By default sort on Movie names
             */
            sortOrder = TITLE;
        }

        Cursor c = qb.query(db,	projection,	selection,
                selectionArgs,null, null, sortOrder);
        /**
         * register to watch a content URI for changes
         */
        c.setNotificationUri(getContext().getContentResolver(), uri);

        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            /**
             * Get all student records
             */
            case MOVIE:
                return "vnd.android.cursor.dir/vnd.example.Favourites";
            /**
             * Get a particular student
             */
            case MOVIE_ID:
                return "vnd.android.cursor.item/vnd.example.Favourites";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        /**
         * Add a new favourite record
         */
        long rowID = db.insert(FAVOURITE_TABLE_NAME, "", contentValues);

        /**
         * If record is added successfully
         */
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }

        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)){
            case MOVIE:
                count = db.delete(FAVOURITE_TABLE_NAME, selection, selectionArgs);
                break;

            case MOVIE_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete( FAVOURITE_TABLE_NAME, _ID +  " = " + id +
                                (!TextUtils.isEmpty(selection) ?
                                        " AND (" + selection + ')' : ""), selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case MOVIE:
                count = db.update(FAVOURITE_TABLE_NAME, contentValues, selection, selectionArgs);
                break;

            case MOVIE_ID:
                count = db.update(FAVOURITE_TABLE_NAME, contentValues,
                        _ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ? " AND (" +selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri );
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }


    public class DBHelper extends SQLiteOpenHelper {

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

            onUpgrade(db, oldVersion, newVersion);

        }
    }

}
