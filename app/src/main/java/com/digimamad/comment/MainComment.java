package com.digimamad.comment;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.digimamad.DatabaseAccess;
import com.digimamad.R;
import com.digimamad.model.Comments;

import java.util.ArrayList;
import java.util.List;

public class MainComment extends Activity {

    public static List<Comments> comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        comment = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_layout);
        SelectCommentsFromDatabase();
        ListView l = findViewById(R.id.comment_list);
        CommentAdaptor c = new CommentAdaptor(this);
        l.setAdapter(c);
    }

    public void SelectCommentsFromDatabase()
    {
        DatabaseAccess db = new DatabaseAccess(this);

        Cursor c = db.getDb().rawQuery("Select * from comments",null);
        c.moveToFirst();
        while(!c.isAfterLast())
        {
            String text = c.getString(1);
            int product_id = c.getInt(2);
            String username = c.getString(3);
            int like = c.getInt(4);
            int dislike = c.getInt(5);

            Comments comments = new Comments(username,text,product_id, like,dislike);
            comment.add(comments);
            c.moveToNext();
        }
    }
}
