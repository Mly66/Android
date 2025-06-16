package cn.nbmly.test0616;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {
    public MyOpenHelper(Context context) {
        super(context, "test.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(id integer primary key autoincrement,name varchar(20))");
        db.execSQL("insert into user(name) values('张三')");
        db.execSQL("insert into user(name) values('李四')");
        db.execSQL("insert into user(name) values('王五')");
        db.execSQL("insert into user(name) values('赵六')");
        db.execSQL("insert into user(name) values('孙七')");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        onCreate(db);


    }
}
