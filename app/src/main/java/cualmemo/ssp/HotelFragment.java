package cualmemo.ssp;


import android.content.Context;
import android.os.Bundle;
import android.renderscript.Double2;
import android.support.v4.app.Fragment;
import android.support.v4.provider.DocumentFile;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HotelFragment extends Fragment {


    public HotelFragment() {
        // Required empty public constructor
    }

    ListView listH;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference hotelref = database.getReference("Ruta").child("1").child("Hotel");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_hotel, container, false);

        final ArrayList<Hotel> vamoaver = new ArrayList<Hotel>();


        listH =(ListView) view.findViewById(R.id.listH);

        hotelref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(int i=1;i <=2;i++){
                    Hotel Htemp= dataSnapshot.child(Integer.toString(i)).getValue(Hotel.class);
                    vamoaver.add(new Hotel(Htemp.lat,Htemp.lgt,Htemp.descripcion,Htemp.precio,Htemp.pais));
                }
                //textView.setText(Double.toString(Htemp.lat));
                //Htemp= dataSnapshot.child("2").getValue(Hotel.class);
                //textView2.setText(Double.toString(Htemp.lat));
                Myadapter  myadapter =new Myadapter(getContext(),vamoaver);
                listH.setAdapter(myadapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        return view;
    }
    public class Myadapter extends ArrayAdapter<Hotel> {
        private final Context context;
        private final ArrayList<Hotel>  datos ;

        public Myadapter(Context context, ArrayList<Hotel>  datos) {
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

            Hotel hactual =  datos.get(position);

            precio.setText(hactual.precio);
            descripcion.setText(hactual.descripcion);
            pais.setText(hactual.pais);
            imagen.setImageResource(R.drawable.imghoteln);

            return (item);
        }
    }

}
