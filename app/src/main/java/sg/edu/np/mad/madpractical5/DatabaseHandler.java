package sg.edu.np.mad.madpractical5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "userDB.db";
    public static final String TABLE_USER = "User";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_DESCRIPTION = "Description";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_FOLLOWED = "Followed";

    // Reference:
    // https://developer.android.com/reference/android/database/sqlite/SQLiteOpenHelper#public-constructors_1
    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // SQL Query to create User table
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_DESCRIPTION + " TEXT," +
                COLUMN_FOLLOWED + " TEXT" + ")";

        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    // Add user into database
    public void addUser(User user) {

        // Reference:
        // https://developer.android.com/training/data-storage/sqlite#WriteDbRow
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_FOLLOWED, String.valueOf(user.getFollowed()));

        db.insert(TABLE_USER, null, values);
    }

    // Get all users in database
    public ArrayList<User> getUsers() {
        ArrayList<User> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER, null);

        // iterate through cursor if there are rows
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            Boolean followed = Boolean.parseBoolean(cursor.getString(3));

            // create user objects and add them to userList
            userList.add(new User(name, description, id, followed));
        }
        cursor.close();
        return userList;
    }

    public void updateUser(User user) {

        // new values of attributes in user
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_FOLLOWED, String.valueOf(user.getFollowed()));

        // find row to update
        String selection = COLUMN_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(user.getId()) };

        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_USER, values, selection, selectionArgs);
    }
}
