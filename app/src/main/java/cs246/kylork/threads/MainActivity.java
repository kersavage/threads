package cs246.kylork.threads;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected String filename = "numbers.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void create(Context context) {
        File file = new File(context.getFilesDir(), filename);

        Thread t = new Thread() {
            public void run() {
                FileOutputStream outputStream;
                try {
                    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                    String newLine = "\n\r";
                    for (int i = 0; i < 10; i++) {
                        outputStream.write(i);
                        outputStream.write(newLine.getBytes());
                        Thread.sleep(250);
                    }
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public void load(Context context) {

        File file = new File(context.getFilesDir(),filename);
        ListView lv;
        lv = (ListView) findViewById(R.id.listView);
        String line;
        ArrayList<String> lines = new ArrayList<String>();
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lines);

        lv.setAdapter(adapter);
    }

    public void clear() {
        ListView lv = (ListView)findViewById(R.id.listView);
        lv.setAdapter(null);
    }
}
