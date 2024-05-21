package sg.edu.np.mad.madpractical5;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "userDB.db";
    public static final String TABLE_USER = "User";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_DESCRIPTION = "Description";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_FOLLOWED = "Followed";

    // Reference:
    // https://developer.android.com/reference/android/database/sqlite/SQLiteOpenHelper#public-constructors_1
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // SQL Query to create User table
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + "TEXT," +
                COLUMN_DESCRIPTION + "TEXT," +
                COLUMN_FOLLOWED + "TEXT," + ")";

        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    // Add user attributes into database
    public void addUser(User user) {

        // Reference:
        // https://developer.android.com/training/data-storage/sqlite#WriteDbRow
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_FOLLOWED, user.getFollowed());

        db.insert(TABLE_USER, null, values);
        db.close();
    }
}
