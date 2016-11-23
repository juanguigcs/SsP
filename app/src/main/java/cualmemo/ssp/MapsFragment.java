package cualmemo.ssp;


import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.maps.android.PolyUtil;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap map;

    private final static String LINE ="acbe@nwglMfsIbt@biD|_DfrCxgCf{AsGht@eBpgDpG|mAhRfb@qGbaA{KjtCc^rxDg}@||ByeAfrBg{AbpBal@rxAmEfrCse@xzAmTjtAsGz|@se@|m@ia@nv@qGrXp_BzkAvv@dr@jp@jcCpGxk@vXpg@re@hrB|ZviBtIti@dA~mA{Kba@yg@~m@rG`_AwI`_ChCltA?`_Ayg@?wv@leA}x@xzAtaBdpAmEbpAmThrAsGjt@wX`nBiRdpBcAltBia@tx@iR~mAd_@viAvg@ntB_Nb_B{KpeDvg@tgBre@pv@lT`nBhR`nBja@hrArt@haC|i@~gHljC`_@flA`nBbjA|iDiRtvBg}@d_B_NrgAwg@bnB~\\zk@xv@vxAha@`}B|ZlrBlr@tgBmr@rv@yeAtvBiCfr@iRpeA_wAxz@asC|iC}vAzxB{KziBpVvZd_@lEre@peAjp@`}AhC|iCtIzxB`]b}BhRb}BpGhpB`N~|@hRgCnaAdRjp@rg@rrAptA|pClt@~tB`_@|i@|LtrAiCf`@tIf`@~]`]eBjzBpWxv@iSf~@fB|_DnVvIth@neEvYl`Axy@p_BbnAnr@fq@bm@~m@flArg@~MdoA|Ljc@zYvi@|j@f`Apd@~jBxY`n@pFxy@pGfpAcAxy@yJnsA_\\tx@pUrv@lq@d_AvsAruAtrAtgAlc@~yB|x@fq@`l@teB`iA~]b|@~l@vu@jc@dn@|jA|LptAgQrg@`Nzj@nd@ne@pUndA~j@ncAxh@tgA|i@vh@bPh`Bpd@pdAhC`mA`Orf@b@ndAeAxhApd@d_AdPd_A}LnbB?dp@aNnt@zK`}@pU~{@zKrv@vg@vi@ho@foArcA~{@dn@ls@b{@xy@zi@xx@xg@foAzi@ls@f}@ld@lTjbAtWbn@xYhb@ps@b~@`]dmBd_@pe@xJ~kAnFtv@lTne@eQpsAoTnsAkDbn@}Lpf@}LtrCrqAdo@f{A~]b^zy@xYf{CxeAtuAnr@lqAxv@xwAzfApdAvsAxx@|x@~jAdn@tfAzh@pt@lq@h_BxcB`n@hRlbAjDteAgQb~@al@zhAcm@tuAhRttAlTzxA~[|gBtWjpAzfAncAlTdlBa@h{CeP`lAmTb{AyJjr@re@naBdn@ncAdwBd{Bb`ExeBnr@rpCj_A`hCre@`fDtcArnDdn@dyCvv@f_AzrBtXpwD`dEfzGtv@j_AjpAiCblA|i@|tCvXrpCoTh_AytA|\\or@trB?xeBgCjbHbPjr@ja@pG~Mzi@iCf_A?b}@wg@vg@wg@jr@ss@rsAm`AzgAc^vfA}gAblAuu@`zAuf@`|@mq@lpAjDh|BjSp}C|[ru@zK|y@bOlTzKvdBnFzi@yJ~iA|Lvw@xYnd@toB|LdBnqAa@`|@}L|j@cO|y@lb@jSl`Abn@|x@lc@tW|y@rHnd@hRfnAa@pt@_N|Llq@tf@d_@vh@jb@f`@d_@|[uW~\\a@vh@tHdn@tI`|@`Nlc@_Mvh@mEhp@aOxw@dPru@ka@|hAc^j`AhCha@pd@`|@|Llc@d_@rdA`@lr@`NpqAuHrdAa@xh@uWrsAaOxh@wXfo@c@ns@sW|uBut@xfAdApt@zZ~fBgBhp@zKd~@wg@hp@kSxuAi~@ha@nr@ru@dPtu@`O`^xg@zi@rcAlr@noAnpAlThp@lEtg@}[`l@mSdo@_NtWyYjq@se@`m@hCtWjb@vh@re@b^vg@wIpaAfo@xeAxw@zZoFjzBd}@ppAlr@~hAzi@lb@ha@sVtv@e_@`]_k@tg@oUpd@nr@`m@fo@nd@`]`{@d_@pe@vXzZ|x@fo@tu@xYfn@lpAtf@lq@xJ~gBrcAtf@_Npe@nF~j@{Zjb@kD|LsVzZvImE~j@pVd_@eB`l@b_@?rt@cOb_@~[`]ePzZ|[uWvu@dPjb@lc@lEb_@tt@fQjb@lT|i@rHdn@|[|i@lb@hRfRd_@`{@b^ePha@f`@nr@~\\vXrcAdkA`jAzKha@vv@zi@nr@qGzrB~k@lnAvIdn@ha@naA~Mrt@lTpaAhRvg@vv@trA~k@rV`{@`l@?d_@zx@zrBdPzaCbn@xeAdn@lTd}@vg@nnBnr@qVja@qGhp@~\\nc@`jAxv@~\\lc@~Mre@hRvaB|gAd_@d}@hp@n_Bre@lr@dP`hBtrArcA`l@rrAnr@j_Ad_@~\\pVzi@f}@d}@f}@|i@zZePbyA`{@|i@dlAvXrcAxtAdn@d_@hp@zZd}@nr@|gA`{@vg@|i@hRjnAzZdwBd{AnaAxtAztA|eBre@dlA~\\f}@a{@dn@nc@h}Ad_@ps@fo@dn@f`@lc@wI~[nc@poBd|@tu@zKj_AzZjb@hClq@`l@dlAlc@`]qd@jSlq@ha@xYjq@b@hp@iCfBjp@`^tIxJia@ps@dBdlAfBvdAnr@xtAxh@vv@hR~y@nFlr@iDf`@io@nc@nc@veAxh@vg@pUzh@|Lpe@qGg`@uoBnTg`@|[{uAbPe|@ja@zh@xh@|Ldn@sViR}i@?yh@b|@xYdn@yh@xJwu@fQoaAhp@qd@vv@a]rjDvIh~@zKf~@lTflA~LxY?rcA`@zdByJ~hAiC`l@jDj_AfBxw@gQvXka@nr@iDvg@hRlq@sV|j@_k@b^nFfo@i~@bWdu@ja@cHpVvPpz@qNtf@pV{K}x@naAtHn|BjDveAkDzKooA`l@}gAppAfB|vAiC|[re@|gA`l@`l@`OxYt`BdAnr@sG|j@fQtrArcAvdAxYzrBgRflAxw@|i@pbAf{AtW|x@xYfQ`l@hp@re@_Nlc@dPpUtf@dPd}@fo@rqAxw@tf@vu@bOoFpd@bm@fQxh@|LfB~hAxh@rt@pVzh@h~@re@b|@rVd|@tdAj_AmTjnAsV`jAsWppAeP~hAlE~y@`]flAl`A|[flAsH~\\zi@fn@tf@f~@dn@|Zvf@|x@l`Aj}Al~AnmBlq@lq@dxB`iAdkApd@ha@`{@~[|x@wI|dBkD`{@zKxh@vdA~eBhRzw@e_@noAqGtaBnpApnBxtAdfC~vAzKfjBor@flAa{@jiCd_@beCurAvnCcA~oDb|@tjDxh@l`ArnBfo@|_Db^|vAbApzCzcBpmB|sBpVloAj_A~`Df{AvdAdm@`l@lTpGxlD{KdiBxeA~eBdzAhmAh~@ja@xeAeAzfAuWtu@xg@`l@`{@hCr}Bse@rlC_dCtpB}x@~dHqaAvfFcPtaBrjD{KldEha@zaC|Znr@waBfjBe_@|ZvX|nDgCre@j_Avg@zaCmEn`GvXxcBflAv_C`sCtrA|_D|x@naAja@~lEt_C`dC}eBzpCwwE~eBmlBjwCglAv_CqG|}DbP`kFb{@nr@naApGlsExtAhjB~eBbwBp_Bb`ExlDx}CldEzrBflA~rC~cCtt@fn@zpC~cCb`EpwDjhCliIePx{DtcA`iGvv@rlCw}CtqGqpAbPtaBldEfkGtaBpnBzcB|_Df{Adn@jfDtt@`jAnaAcwBthEiRxlDdP~}DbAjdEqGtyDpVd~EytArlCq_YfjgA|yh@~kb@zkb@|vXnwg@hhCjen@_mEl{Z}lEp`l@r_Nfb^~lEjkb@ucAn}[t_NzjSrmTrbHh}LzcPcaV`yw@czRhhRuxJpzKxl[~m\\rqGnr^txJl{b@xbHt_cFrb^fxRdeIxjLbzRphKdzRb}\\bsOz|\\~sH~o^hvIluQr_Nr}Mxs^bpn@ybH~ofAioFppg@ihCzke@utWlz\\}go@jxK_bOj|UidPtbQ_bOhyd@muPvgd@iqs@hze@clLrbb@xtAb|^i`]vrb@ypNdpPocWrv~@s{Zx`k@}kb@tnGufQ|jh@i}LfqZytA`{cA_~[|`IsjD|mb@_iRngh@ywQrlb@sxJn}Ynr@trf@yeXjer@ehYvlZ}lEptUywQ|bKkvIxkZidP`y`AsfQnc`@yeXlp^ys^x{[iyYbpi@oyCv~SihCvcBirVbcWihCrp`@ufQzi\\s{Ztnv@stWlue@stW|pz@_~[dtNstWjds@ioFf~V_mE{mJ}trAuiSinc@loKcaV}tOmfg@md[crl@{sBi}LafDu{Zl`@}|x@gjGco\\}oK_fBmh]sqGddZujDjtf@}nr@zlIs{Zxf]slq@dgEsoaAuaA}vXaXmnXgoQikSeuCypNuh@sqGbcBidPajGyeXc|NouPnkBivId|BmrKr}C}aO{sB{iKqdCs_NknP`iGm`FtqGatHrxJc_E|~I";

    public MapsFragment() {
        // Required empty public constructor
    }

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference lugarref = database.getReference("Ruta").child("1").child("Lugar");
    DatabaseReference hotelref = database.getReference("Ruta").child("1").child("Hotel");
    DatabaseReference restauranteref = database.getReference("Ruta").child("1").child("Restaurante");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        mapView = (MapView) view.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);

        return view;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        //Toast.makeText(getApplicationContext(),"ESSSSTOY ACA ", Toast.LENGTH_SHORT).show();

        final LatLng casa = new LatLng(6.2109713, -75.6029396);
        map.addMarker(new MarkerOptions().position(casa).title("Marker in casa"));

        final LatLng pereira = new LatLng(4.8049518, -75.6956589);
        map.addMarker(new MarkerOptions().position(pereira).title("Marker in pereira"));

        final LatLng cali = new LatLng(3.4421887, -76.525981);
        map.addMarker(new MarkerOptions().position(cali).title("Marker in cali"));

        final LatLng pasto = new LatLng(1.1747445, -77.2637584);
        map.addMarker(new MarkerOptions().position(pasto).title("Marker in pasto"));

        final LatLng quito = new LatLng(-0.1828996, -78.4600724);
        map.addMarker(new MarkerOptions().position(quito).title("Marker in quito"));

        lugarref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(int i=1;i <=2;i++){
                    Lugar Ltemp= dataSnapshot.child(Integer.toString(i)).getValue(Lugar.class);
                    map.addMarker(new MarkerOptions().position(new LatLng(Ltemp.lat,Ltemp.lgt)).title(Ltemp.descripcion));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        hotelref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(int i=1;i <=2;i++){
                    Hotel Htemp= dataSnapshot.child(Integer.toString(i)).getValue(Hotel.class);
                    map.addMarker(new MarkerOptions().position(new LatLng(Htemp.lat,Htemp.lgt)).title(Htemp.descripcion));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        restauranteref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(int i=1;i <=2;i++){
                    Restaurante Rtemp= dataSnapshot.child(Integer.toString(i)).getValue(Restaurante.class);
                    map.addMarker(new MarkerOptions().position(new LatLng(Rtemp.lat,Rtemp.lgt)).title(Rtemp.descripcion));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });




        map.moveCamera(CameraUpdateFactory.newLatLngZoom(pasto, 5));


        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        map.setMyLocationEnabled(true);
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        List decodedPath = PolyUtil.decode(LINE);
        map.addPolyline( new PolylineOptions().addAll(decodedPath));


        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                //map.addMarker(new MarkerOptions().position(latLng).title("Marker"));
                if(latLng.equals(casa)){
                    Toast.makeText(getApplicationContext(),"ESSSSTOY ACA ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}