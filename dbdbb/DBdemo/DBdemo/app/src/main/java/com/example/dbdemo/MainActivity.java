package com.example.dbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    Button b1;
    Intent i;
    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=(TextView)findViewById(R.id.textView);

        DatabaseHelper h=new DatabaseHelper(this,"test");

        SQLiteDatabase db=h.getWritableDatabase();

        System.out.println("version "+ db.getVersion());

        Cursor rs=db.rawQuery("SELECT id,name FROM stud",null);

        //  Cursor rs=db.rawQuery("SELECT sid,name FROM mytable",new String[] {"David", "2"});

        while(rs.moveToNext())
        {
            tv.append(rs.getString(0)+"   "+rs.getString(1)+"\n");
        }

        b1=(Button)findViewById(R.id.button);

        b1.setOnClickListener(new A());
        i=new Intent(this,Options.class);
        /*b1.setOnClickListener(new A());
        i=new Intent(this,Options.class);*/
    }
    class A implements View.OnClickListener {

        @Override
        public void onClick(View v)
        {
            startActivity(i);
        }
    }




}

