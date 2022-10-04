package com.example.zhihu;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import androidx.annotation.Nullable;

public class DBOpenHelper extends SQLiteOpenHelper
{

    private static String name = "mydb.db";// 表示数据库的名称
    private static int version = 2;// 表示数据库的版本号码

    public DBOpenHelper(Context context) {
        super(context, name, null, version);
    }


    // 当数据库创建的时候，是第一次被执行,完成对数据库的表的创建
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sql = "create table person(id integer primary key autoincrement,name varchar(64),address varchar(64))";
        db.execSQL(sql);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String sql = "alter table person add sex varchar(8)";
        db.execSQL(sql);
    }

}
