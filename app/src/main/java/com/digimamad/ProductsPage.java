package com.digimamad;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;


public class ProductsPage extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void ReadImgFromDatabase()
    {
        DatabaseAccess dbAccess = new DatabaseAccess(this);

        Cursor c = dbAccess.getDb().rawQuery("SELECT * FROM users", null);
        c.moveToFirst();
        while (!c.isAfterLast()) {

            String title = c.getString(0);
            int price = c.getInt(1);
            String details = c.getString(2);
            String color = c.getString(3);
            int id = c.getInt(4);
            byte[] blob = c.getBlob(5);

            c.moveToNext();
        }
    }
}
