package mydroidpet.mydroidpet.com.mydroidpet;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import mydroidpet.mydroidpet.com.mydroidpet.atsk.LocationAtsk;


public class LocalicedScreeen extends ActionBarActivity {

    private static Long TIMEOUT= 30000l;
    Location location;
    String petname;
    TextView txtpetname;
    TextView txtx;
    TextView txty;
    TextView txtz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localiced_screeen);
        LocationAtsk atsk = new LocationAtsk();
        atsk.execute(new Void[0]);
        try {
            location=atsk.get(TIMEOUT, TimeUnit.MILLISECONDS);
            SharedPreferences prefs = getSharedPreferences("general_prefs", Context.MODE_PRIVATE);
            petname=prefs.getString("pet_name","Toby");
            txtpetname= (TextView)findViewById(R.id.txtnombremascota);
            txtx=(TextView)findViewById(R.id.txtx);
            txty=(TextView)findViewById(R.id.txty);
            txtz=(TextView)findViewById(R.id.txtz);
            txtpetname.setText(petname);
            txtx.setText(Double.toString(location.getX()));
            txty.setText(Double.toString(location.getY()));
            txtz.setText(Double.toString(location.getZ()));
        }catch (Exception e){
            Toast.makeText(LocalicedScreeen.this,getString(R.string.errorwebservice),Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.localiced_screeen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
