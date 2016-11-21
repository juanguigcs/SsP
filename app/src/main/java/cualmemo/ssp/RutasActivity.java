package cualmemo.ssp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RutasActivity extends AppCompatActivity {

    //Instancia para el Navigation drawer
    ListView listz;
    private String [] opciones = new  String[]{"Principal","Rutas","Mis rutas","Perfil","Cerrar sesión"};
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    //instancia para Swipe tab
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutas);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

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
                        break;
                    case(1):

                        // finish();
                        // Toast.makeText(getApplicationContext(),"Opcion "+String.valueOf(i), Toast.LENGTH_SHORT).show();
                        break;
                    case(2):
                        Intent intent2= new Intent(getApplicationContext(),MisRutasActivity.class);

                        startActivity(intent2);
                        // finish();
                        // Toast.makeText(getApplicationContext(),"Opcion "+String.valueOf(i), Toast.LENGTH_SHORT).show();
                        break;
                    case(3):
                        Intent intent= new Intent(getApplicationContext(),PerfilActivity.class);
                        startActivity(intent);
                        //editor.clear();
                        //editor.remove("v_ingreso");
                        //editor.commit();
                        //  Toast.makeText(getApplicationContext(),"Opcion cerrar  "+String.valueOf(i), Toast.LENGTH_SHORT).show();
                        break;
                    case(4):
                        logout(view);
                        //finish();
                        break;
                }

                listz.setItemChecked(i,true);
                drawerLayout.closeDrawer(listz);
            }
        });
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.abierto, R.string.cerrado);
        drawerLayout.setDrawerListener(drawerToggle);

        //______________________
        PageAdapater  adapater= new PageAdapater(getSupportFragmentManager());
        mViewPager=(ViewPager)findViewById(R.id.pager);
        mViewPager.setAdapter(adapater);

        actionBar =getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.TabListener tabListener= new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                mViewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            }
            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
            }
        };

        //frgmentos a utilizar
        ActionBar.Tab tab = actionBar.newTab().setText("Mapa").setTabListener(tabListener);
        actionBar.addTab (tab);
        tab = actionBar.newTab().setText("Lugares").setTabListener(tabListener);
        actionBar.addTab (tab);
        tab = actionBar.newTab().setText("Hoteles").setTabListener(tabListener);
        actionBar.addTab (tab);
        tab = actionBar.newTab().setText("Restaurantes").setTabListener(tabListener);
        actionBar.addTab (tab);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                getSupportActionBar().setSelectedNavigationItem(position);

            }
        });
    }

    //Adapatador para swipe tab
    public class PageAdapater extends FragmentPagerAdapter {

        public PageAdapater(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: return new MapsFragment();
                case 1: return new LugarFragment();
                case 2: return new HotelFragment();
                case 3: return new RestauranteFragment();
                default:return null;
            }
        }
        @Override
        public int getCount() {
            return 4;
        }
    }
    //navigation draw
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.LEFT);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void goAutenticacionScreen() {
        Intent intent = new Intent(this, AutenticacionActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        goAutenticacionScreen();
    }
}