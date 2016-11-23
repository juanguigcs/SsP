package cualmemo.ssp;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class LugarFragment extends Fragment {


    public LugarFragment() {
        // Required empty public constructor
    }
    ListView listL;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference lugarref = database.getReference("Ruta").child("1").child("Lugar");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_lugar, container, false);
        final ArrayList<Lugar> vamoaver = new ArrayList<Lugar>();


        listL =(ListView) view.findViewById(R.id.listL);

        lugarref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(int i=1;i <=2;i++){
                    Lugar Ltemp= dataSnapshot.child(Integer.toString(i)).getValue(Lugar.class);
                    vamoaver.add(new Lugar(Ltemp.lat,Ltemp.lgt,Ltemp.descripcion,Ltemp.precio,Ltemp.pais));
                }
                //textView.setText(Double.toString(Htemp.lat));
                //Htemp= dataSnapshot.child("2").getValue(Hotel.class);
                //textView2.setText(Double.toString(Htemp.lat));
                Myadapter myadapter =new Myadapter(getContext(),vamoaver);
                listL.setAdapter(myadapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        return view;
    }
    public class Myadapter extends ArrayAdapter<Lugar> {
        private final Context context;
        private final ArrayList<Lugar>  datos ;

        public Myadapter(Context context, ArrayList<Lugar>  datos) {
            super(context, -1, datos);
            this.context = context;
            this.datos = datos;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View item = inflater.inflate(R.layout.item_firebase,parent,false);


            TextView precio= (TextView)item.findViewById(R.id.tprecio);
            TextView descripcion= (TextView)item.findViewById(R.id.tdescripcion);
            TextView pais= (TextView)item.findViewById(R.id.tpais);
            ImageView imagen =(ImageView) item.findViewById(R.id.iImagen);

            Lugar lactual =  datos.get(position);

            precio.setText(lactual.precio);
            descripcion.setText(lactual.descripcion);
            pais.setText(lactual.pais);
            imagen.setImageResource(R.drawable.imgplacen);

            return (item);
        }
    }

}