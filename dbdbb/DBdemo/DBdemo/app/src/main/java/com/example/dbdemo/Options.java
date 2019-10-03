package com.example.dbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Options extends AppCompatActivity {

    Button b1;
    Intent i;
    private Button b2;
    private Button b3;
    private EditText t1;
    private EditText t2;
    private SQLiteDatabase db;
    private Button p;
    private Button n;
    private Cursor rs;
    private int pos;
    /** Called when the activity is first created. */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);



        t1=(EditText)findViewById(R.id.editText2);
        t2=(EditText)findViewById(R.id.editText3);


        DatabaseHelper h=new DatabaseHelper(this,"test");

        db=h.getWritableDatabase();

        rs=db.rawQuery("SELECT id,name FROM stud",null);

        // while(rs.moveToNext())
        //{
        // tv.append(rs.getString(0)+"   "+rs.getString(1)+"\n");
        //}

        p=(Button)findViewById(R.id.p);
        n=(Button)findViewById(R.id.n);

        p.setOnClickListener(new P());
        n.setOnClickListener(new N());


        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);


        b1.setOnClickListener(new A());//insert
        b2.setOnClickListener(new up());//update
        b3.setOnClickListener(new D()); //delete
        //i=new Intent(this,Form.class);

    }

    class D implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            rs=db.rawQuery("SELECT id,name FROM stud",null);
            String s1=t1.getText().toString();
            db.delete("stud", "id="+s1, null);

            /*
             * db.delete(table, whereClause, whereArgs);
             * db.delete("tablename","id=? and name=?",new String[]{"1","jack"});
             * */

            /*
             * One example of rawQuery - db.rawQuery("select * from table where column = ?",new String[]{"data"});
             * */

            //db.update(table, values, whereClause, whereArgs)

			/*
			 * db.update(TABLE_NAME,    contentValues,    NAME + " = ? AND " + LASTNAME + " = ?",
    new String[]{"Manas", "Bajaj"});

    ***************
    *
    *ContentValues values = new ContentValues();
values.put(KEY_WORD, word.getWord());
values.put(KEY_DEFINITION, word.getDefinition());
int rowsUpdated = db.update(TABLE_WORDS, values, KEY_ID + "=" + word.getID(), null);
			 * */
        }
    }
    class up implements View.OnClickListener
    {

        @Override
        public void onClick(View arg0)
        {
            String s1=t1.getText().toString();
            String s2=t2.getText().toString();

            ContentValues cv=new ContentValues();

            cv.put("id", Integer.parseInt(s1));
            cv.put("name", s2);

            db.update("stud", cv, "id = ?",new String[] { s1 });
            rs=db.rawQuery("SELECT id,name FROM stud",null);


        }


    }

    class A implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String s1=t1.getText().toString();
            String s2=t2.getText().toString();

            ContentValues cv=new ContentValues();

            cv.put("id", Integer.parseInt(s1));
            cv.put("name", s2);

            db.insert("stud", null , cv);

            rs=db.rawQuery("SELECT id,name FROM stud",null);
            System.out.println("version "+ db.getVersion());
			/*
			 *
Sometimes you want to insert an empty row, in that case ContentValues have no content value,
and you should use nullColumnHack.

For example, you want to insert an empty row into a table student(id, name),
which id is auto generated and name is null. You could invoke like this:

ContentValues cv = new ContentValues();
db.insert("student", "name", cv);
			 * */
        }
    }

    class P implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            //rs=db.rawQuery("SELECT sid,name FROM stud",null);
            if(rs.moveToPrevious()){
                t1.setText(rs.getString(0));
                t2.setText(rs.getString(1));
            }

        }
    }
    class N implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if(rs.moveToNext()){
                t1.setText(rs.getString(0));
                t2.setText(rs.getString(1));
            }
        }
    }

}

