package cn.nbmly.bookkeep.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

import cn.nbmly.bookkeep.models.User;

public class UserDao {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public UserDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long addUser(User user) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USERNAME, user.getUsername());
        values.put(DatabaseHelper.COLUMN_PASSWORD, user.getPassword());
        long currentTime = new Date().getTime();
        values.put(DatabaseHelper.COLUMN_CREATE_TIME, currentTime);
        values.put(DatabaseHelper.COLUMN_UPDATE_TIME, currentTime);
        return database.insert(DatabaseHelper.TABLE_USERS, null, values);
    }

    public User getUserByUsername(String username) {
        Cursor cursor = database.query(
                DatabaseHelper.TABLE_USERS,
                new String[]{
                    DatabaseHelper.COLUMN_ID,
                    DatabaseHelper.COLUMN_USERNAME,
                    DatabaseHelper.COLUMN_PASSWORD,
                    DatabaseHelper.COLUMN_CREATE_TIME,
                    DatabaseHelper.COLUMN_UPDATE_TIME
                },
                DatabaseHelper.COLUMN_USERNAME + " = ?",
                new String[]{username},
                null, null, null
        );

        User user = null;
        if (cursor != null && cursor.moveToFirst()) {
            user = new User(
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USERNAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PASSWORD))
            );
            user.setCreateTime(new Date(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CREATE_TIME))));
            user.setUpdateTime(new Date(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_UPDATE_TIME))));
            cursor.close();
        }
        return user;
    }

    public boolean checkUser(String username, String password) {
        User user = getUserByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    public int getUserId(String username) {
        User user = getUserByUsername(username);
        return user != null ? user.getId() : -1;
    }
}

