package samir.andrew.cheeseman;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Andrew Samir on 6/6/2016.
 */
public class Score extends AppCompatActivity {

    DataBase DB;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score);

        ArrayList<String> arrayList=new ArrayList<>();


        DB=new DataBase(Score.this);


        Cursor cursor = DB.getusers();
        if (cursor.moveToFirst()) {
            arrayList.clear();
            do {

              arrayList.add(cursor.getString(1)+"   "+cursor.getInt(2));

            } while (cursor.moveToNext());
        }

        ListView listView = (ListView) findViewById(R.id.listView);


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                arrayList );

        listView.setAdapter(arrayAdapter);

    }
}
