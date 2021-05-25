package com.example.sqlitedatabase;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private EditText nameET, contactET, dob;
    private Button insert, update, delete, view;

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nameET = findViewById(R.id.nameET);
        contactET = findViewById(R.id.contactTV);
        dob = findViewById(R.id.dateET);

        insert = findViewById(R.id.newDataBT);
        update = findViewById(R.id.updateBT);
        delete = findViewById(R.id.deleteDataBT);
        view = findViewById(R.id.viewDataBT);

        DB =new DBHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nametxt= nameET.getText().toString().trim();
                String contacttxt= contactET.getText().toString().trim();
                String dobtxt= dob.getText().toString().trim();

                Boolean checkinsertdata =DB.insertuserdata(nametxt,contacttxt,dobtxt);
                if (checkinsertdata ==true)
                {
                    Toast.makeText(MainActivity.this,"new entery instered",Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(MainActivity.this,"Not entery instered",Toast.LENGTH_SHORT).show();

                }

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nametxt= nameET.getText().toString().trim();
                String contacttxt= contactET.getText().toString().trim();
                String dobtxt= dob.getText().toString().trim();

                Boolean checkupdatedata =DB.updateuserdata(nametxt,contacttxt,dobtxt);
                if (checkupdatedata == true)
                {
                    Toast.makeText(MainActivity.this," entery uplated",Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(MainActivity.this,"Not entery id uplated instered",Toast.LENGTH_SHORT).show();

                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nametxt= nameET.getText().toString().trim();


                Boolean checkdeletedata =DB.deteleuserdata(nametxt);
                if (checkdeletedata == true)
                {
                    Toast.makeText(MainActivity.this," entery deleted",Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(MainActivity.this,"entery not deleted ",Toast.LENGTH_SHORT).show();

                }

            }
        });


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=DB.viewuserdata();
                if (res.getCount() == 0)
                {
                    Toast.makeText(MainActivity.this,"no entery exists",Toast.LENGTH_SHORT).show();

                    return;
                }



                StringBuffer stringBuffer = new StringBuffer();

                Log.d("dddaaataa","==="+stringBuffer);

                while (res.moveToNext())
                {
                    stringBuffer.append("Name : "+res.getString(0)+"\n");
                    stringBuffer.append("Contact : "+res.getString(1)+"\n");
                    stringBuffer.append("Date of birth : "+res.getString(2)+"\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User enteries");
                builder.setMessage(builder.toString());
                builder.show();

            }
        });



    }
}