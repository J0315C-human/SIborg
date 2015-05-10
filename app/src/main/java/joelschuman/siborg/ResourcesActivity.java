package joelschuman.siborg;

import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import junit.framework.Assert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;



public class ResourcesActivity extends ActionBarActivity {
    /* Checks if external storage is available for read and write */
    public String str_Name = "Enter your name";

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    //create output stream
    public FileOutputStream getDocStorageDir(String DocName) {
        // Get the directory for the user's public documents, if that doesn't work, try Downloads

        File nfile = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), DocName);

        //Try opening directory and creating new output stream

        nfile.mkdirs();

        FileOutputStream OutputFile = null;
        try {
            OutputFile = new FileOutputStream(nfile + "/DocName.pdf");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return OutputFile;
    }


    public void onClickEnterName(View arg0) {
        Assert.assertNotNull(arg0);
        // Get string entered
        TextView newName = (TextView) findViewById(R.id.enter_name);
        // Add string to underlying data structure
        str_Name = newName.getText().toString();
    }

    public void CreatePDF(View v) {
        if (isExternalStorageReadable() && isExternalStorageWritable()) {
            //File newPDFfile = getDocStorageDir("TEST_PDF");
            Document doc = new Document();
            try {
                PdfWriter.getInstance(doc, getDocStorageDir("testPDF"));
                doc.open();
                Paragraph par1 = new Paragraph();
                par1.add("ATTENDANCE SHEET");
                Paragraph par2 = new Paragraph();
                par2.add("Name: " + str_Name);
                Paragraph par3 = new Paragraph();
                String strdate = String.valueOf(Calendar.MONTH) + "/" +String.valueOf(Calendar.DAY_OF_MONTH) + "/" +String.valueOf(Calendar.YEAR);
                strdate += "\nCurrent Time: " + String.valueOf(Calendar.HOUR_OF_DAY) + ":" + String.valueOf(Calendar.MINUTE);
                par3.add("\nToday's Date is: " + strdate);

                par3.add("_________________\n_________________\n_________________\n_________________\n");

                doc.add(par1);

                doc.add(par2);
                doc.add(par3);
                doc.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else Log.wtf("Errors", "External Storage Not Read/Writeable");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resources, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
