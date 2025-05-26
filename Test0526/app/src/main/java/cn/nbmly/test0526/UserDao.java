package cn.nbmly.test0526;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private MyDataHelper dbHelper;

    public UserDao(Context context) {
        dbHelper = new MyDataHelper(context);
    }

    // 插入用户
    public long insertUser(String name, int age, String email) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("age", age);
        values.put("email", email);
        long newRowId = db.insert("users", null, values);
        db.close();
        return newRowId;
    }

    // 根据ID查询用户
    public User getUserById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = { "id", "name", "age", "email" };
        String selection = "id = ?";
        String[] selectionArgs = { String.valueOf(id) };

        Cursor cursor = db.query("users", projection, selection, selectionArgs, null, null, null);

        User user = null;
        if (cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndexOrThrow("name");
            int ageIndex = cursor.getColumnIndexOrThrow("age");
            int emailIndex = cursor.getColumnIndexOrThrow("email");

            user = new User(
                    id,
                    cursor.getString(nameIndex),
                    cursor.getInt(ageIndex),
                    cursor.getString(emailIndex));
        }
        cursor.close();
        db.close();
        return user;
    }

    // 查询所有用户
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("users", null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndexOrThrow("id");
            int nameIndex = cursor.getColumnIndexOrThrow("name");
            int ageIndex = cursor.getColumnIndexOrThrow("age");
            int emailIndex = cursor.getColumnIndexOrThrow("email");

            User user = new User(
                    cursor.getInt(idIndex),
                    cursor.getString(nameIndex),
                    cursor.getInt(ageIndex),
                    cursor.getString(emailIndex));
            users.add(user);
        }
        cursor.close();
        db.close();
        return users;
    }

    // 更新用户信息
    public int updateUser(int id, String name, int age, String email) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("age", age);
        values.put("email", email);

        String selection = "id = ?";
        String[] selectionArgs = { String.valueOf(id) };

        int count = db.update("users", values, selection, selectionArgs);
        db.close();
        return count;
    }

    // 删除用户
    public int deleteUser(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = "id = ?";
        String[] selectionArgs = { String.valueOf(id) };

        int deletedRows = db.delete("users", selection, selectionArgs);
        db.close();
        return deletedRows;
    }
}