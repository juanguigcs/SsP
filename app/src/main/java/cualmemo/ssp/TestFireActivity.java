package cualmemo.ssp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TestFireActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "userrrr:" ;
    ListView listz;
    private String [] opciones = new  String[]{"Principal","Rutas","Mis rutas","Perfil","Cerrar sesión"};
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    EditText fid,fuser,fpass,fmail;
    Button bfin,bfact,bfbus,bfdel;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference UserRef = database.getReference("User");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fire);

        fid=(EditText)findViewById(R.id.fid);
        fuser=(EditText)findViewById(R.id.fusuario);
        fpass=(EditText)findViewById(R.id.fcontrasena);
        fmail=(EditText)findViewById(R.id.fcorreo);

        bfin=(Button)findViewById(R.id.bfingresar);
        bfact=(Button)findViewById(R.id.bfactualizar);
        bfbus=(Button)findViewById(R.id.bfbuscar);
        bfdel=(Button)findViewById(R.id.bfborrar);

        bfin.setOnClickListener(this);
        bfact.setOnClickListener(this);
        bfbus.setOnClickListener(this);
        bfdel.setOnClickListener(this);



        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        drawerLayout = (DrawerLayout)findViewById(R.id.contenedor);
        listz=(ListView)findViewById(R.id.mizq);
        listz.setAdapter(new ArrayAdapter<String>(getSupportActionBar().getThemedContext(),
                android.R.layout.simple_list_item_1, opciones));
        listz.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fragment fragment = null;
                switch (i){
                    case(0):
                        Intent intent3= new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent3);
                        //   Toast.makeText(getApplicationContext(),"Opcion "+String.valueOf(i), Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    case(1):
                        Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        finish();
                        // Toast.makeText(getApplicationContext(),"Opcion "+String.valueOf(i), Toast.LENGTH_SHORT).show();
                        break;
                    case(2):
                        Intent intent2= new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent2);
                        finish();
                        // Toast.makeText(getApplicationContext(),"Opcion "+String.valueOf(i), Toast.LENGTH_SHORT).show();
                        break;
                    case(3):

                        //editor.clear();
                        //editor.remove("v_ingreso");
                        //editor.commit();
                        //  Toast.makeText(getApplicationContext(),"Opcion cerrar  "+String.valueOf(i), Toast.LENGTH_SHORT).show();
                        break;
                    case(4):
                        Intent intent4= new Intent(getApplicationContext(),AutenticacionActivity.class);
                        startActivity(intent4);
                        finish();
                }

                listz.setItemChecked(i,true);
                drawerLayout.closeDrawer(listz);
            }
        });
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.abierto, R.string.cerrado);
        drawerLayout.setDrawerListener(drawerToggle);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.LEFT);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();

        switch (id){
            case R.id.bfingresar:
                Usuario user=new Usuario(Integer.parseInt(fid.getText().toString()),fuser.getText().toString(),fmail.getText().toString(),fpass.getText().toString(),"co");
                UserRef.child(fid.getText().toString()).setValue(user);
                break;
            case R.id.bfactualizar:

                break;
            case R.id.bfbuscar:
                ValueEventListener postListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get Post object and use the values to update the UI
                        Usuario u1 = dataSnapshot.child(fid.getText().toString()).getValue(Usuario.class);
                        fid.setText(Integer.toString(u1.id));
                        fuser.setText(u1.usuario);
                        fmail.setText(u1.correo);
                        fpass.setText(u1.contrasena);
                        // ...
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Getting Post failed, log a message
                        Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                        // ...
                    }
                };
                UserRef.addValueEventListener(postListener);
                break;
            case R.id.bfborrar:

                break;

        }
    }
}