package id.my.asadullah.intentimplicitapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BluetoothActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 1;

    // todo -- deklarasi global
    BluetoothAdapter mbluetoothAdapter;

    @BindView(R.id.btndisable)
    Button btndisable;
    @BindView(R.id.btnenable)
    Button btnenable;
    @BindView(R.id.btnlistpaired)
    Button btnlistpaired;
    @BindView(R.id.btndiscoverdevice)
    Button btndiscoverdevice;
    @BindView(R.id.listview)
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        ButterKnife.bind(this);

        getSupportActionBar().setHomeButtonEnabled(true);

        //todo -- inisialisasi bluetooth
        mbluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    }

    @OnClick({R.id.btndisable, R.id.btnenable, R.id.btnlistpaired, R.id.btndiscoverdevice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btndisable:
                gakAktif();
                break;
            case R.id.btnenable:
                aktif();
                break;
            case R.id.btnlistpaired:
                paired();
                break;
            case R.id.btndiscoverdevice:
                discover();
                break;
        }
    }

    // TODO -- Mengaktifkan bluetooth selama beberapa detik
    private void discover() {
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(discoverableIntent);
    }

    // TODO -- Melihat bluetooth yang pernah disandingkan
    private void paired() {

        //todo -- set informasi bluetooth
        Set<BluetoothDevice> paired = mbluetoothAdapter.getBondedDevices();

        //todo -- check paired device kosong apa tidak
        if (paired.size()>0){
            //todo -- looping
            ArrayList marrayAdapter = new ArrayList();
            for (BluetoothDevice device : paired){
                marrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
            //todo -- include isi array ke adapter
            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,marrayAdapter);
            listview.setAdapter(adapter);
        }
    }

    // TODO -- Disable Bluetooth
    private void gakAktif() {

        //todo -- check kalau sudah aktif
        if (mbluetoothAdapter.isEnabled()){
            mbluetoothAdapter.disable();
        }
        else{
            Toast.makeText(this, "Sudah tidak aktif", Toast.LENGTH_SHORT).show();
        }
    }

    // TODO -- Enable Bluetooth
    private void aktif() {

        //todo -- check dari bluetooth aktif apa tidak
        if(!mbluetoothAdapter.isEnabled()){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
        else{
            Toast.makeText(this, "Udah aktif", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != 0 && requestCode == REQUEST_ENABLE_BT){
            Toast.makeText(this, "Sudah aktif", Toast.LENGTH_SHORT).show();
        }
    }

}
