package cualmemo.ssp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity  {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    String[] nombrerutas = new String[]{"- Medellín-Machu Picchu"};
    ListView Lst, listz;

    private String[] opciones = new String[]{"Principal", "Rutas", "Mis rutas", "Perfil", "firebase", "Cerrar sesión"};
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    Button Blogout;
    public static final String TAG = "NOTICIAS";
    private TextView infoTextView;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference UserRef = database.getReference("User");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //pref compartidas
        prefs= getSharedPreferences("uno",MODE_PRIVATE);
        editor=prefs.edit();

       // Blogout=(Button)findViewById(R.id.blogout);
       // Blogout.setOnClickListener(this);

        FirebaseUser userF = FirebaseAuth.getInstance().getCurrentUser();

        if (userF != null) {
            if(prefs.getInt("v_flagauth",-1)==2) {

                Toast.makeText(getApplicationContext(), "user" + userF.getDisplayName()+"pref:  "+prefs.getInt("v_flagauth",-1), Toast.LENGTH_SHORT).show();
                Usuario user = new Usuario(userF.getUid(), userF.getDisplayName(), userF.getEmail());
                //UserRef.child(userF.getDisplayName()).setValue(user);
            }
            else {
                Toast.makeText(getApplicationContext(), "user" + userF.getDisplayName()+"pref:  "+prefs.getInt("v_flagauth",-1), Toast.LENGTH_SHORT).show();


            }

        } else {
            goAutenticacionScreen();
        }
        /*notificaciones
        infoTextView = (TextView) findViewById(R.id.textnoti);
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                String value = getIntent().getExtras().getString(key);
                infoTextView.append("\n" + key + ": " + value);
            }
        }
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Token: " + token);*/

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, nombrerutas);
        Lst = (ListView) findViewById(R.id.lst);
        Lst.setAdapter(adapter);
        Lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent2 = new Intent(getApplicationContext(), RutasActivity.class);
                startActivity(intent2);
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.contenedor);
        listz = (ListView) findViewById(R.id.mizq);
        listz.setAdapter(new ArrayAdapter<String>(getSupportActionBar().getThemedContext(),
                android.R.layout.simple_list_item_1, opciones));
        listz.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fragment fragment = null;
                switch (i) {
                    case (0):
                        //   Toast.makeText(getApplicationContext(),"Opcion "+String.valueOf(i), Toast.LENGTH_SHORT).show();
                        break;
                    case (1):
                        Intent intent = new Intent(getApplicationContext(), RutasActivity.class);
                        startActivity(intent);
                        // finish();
                        // Toast.makeText(getApplicationContext(),"Opcion "+String.valueOf(i), Toast.LENGTH_SHORT).show();
                        break;
                    case (2):
                        Intent intent2 = new Intent(getApplicationContext(), MisRutasActivity.class);

                        startActivity(intent2);
                        // finish();
                        // Toast.makeText(getApplicationContext(),"Opcion "+String.valueOf(i), Toast.LENGTH_SHORT).show();
                        break;
                    case (3):
                        Intent intent3 = new Intent(getApplicationContext(), PerfilActivity.class);
                        startActivity(intent3);
                        //editor.clear();
                        //editor.remove("v_ingreso");
                        //editor.commit();
                        //  Toast.makeText(getApplicationContext(),"Opcion cerrar  "+String.valueOf(i), Toast.LENGTH_SHORT).show();
                        break;
                    case (4):
                        Intent intent5 = new Intent(getApplicationContext(), TestFireActivity.class);
                        startActivity(intent5);
                        finish();
                        break;
                    case (5):
                        logout(view);
                        //finish();
                        break;
                }

                listz.setItemChecked(i, true);
                drawerLayout.closeDrawer(listz);
            }
        });
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.abierto, R.string.cerrado);
        drawerLayout.setDrawerListener(drawerToggle);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        // client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.LEFT);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


   /* @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.blogout:
                logout(view);
                break;
        }
    }*/

private void goAutenticacionScreen() {
        Intent intent = new Intent(this, AutenticacionActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        }

public void logout(View view) {
    FirebaseAuth.getInstance().signOut();
    LoginManager.getInstance().logOut();
    editor.remove("v_flagauth");
    editor.commit();
        goAutenticacionScreen();
        }
}
