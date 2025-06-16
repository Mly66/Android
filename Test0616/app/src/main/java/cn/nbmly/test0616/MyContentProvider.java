package cn.nbmly.test0616;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyContentProvider extends ContentProvider {
    SQLiteDatabase db;

    public MyContentProvider() {
        super();
    }

    static UriMatcher matcher;

    static {
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI("cn.nbmly.test0616", "user", 1);
        matcher.addURI("cn.nbmly.test0616", "user/#", 2);
        matcher.addURI("cn.nbmly.test0616", "user/*", 3);

    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return "";
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        if (matcher.match(uri) == 1) {
            db.delete("user", selection, selectionArgs);
            return 1;
        } else if (matcher.match(uri) == 2) {
            db.delete("user", selection, selectionArgs);
            return 1;
        } else if (matcher.match(uri) == 3) {
            db.delete("user", selection, selectionArgs);
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
