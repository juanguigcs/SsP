package cualmemo.ssp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    Button brAceptar,brCancelar;
    EditText erUsario, erContrasena,erRContrasena,erCorreo;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference UserRef = database.getReference("User");

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //pref compartidas
        prefs= getSharedPreferences("uno",MODE_PRIVATE);
        editor=prefs.edit();

        brAceptar =(Button)findViewById(R.id.brAceptar);
        brCancelar=(Button)findViewById(R.id.brCancelar);
        erUsario=(EditText)findViewById(R.id.erUsuario);
        erContrasena=(EditText)findViewById(R.id.erContrasena);
        erRContrasena=(EditText)findViewById(R.id.erRContrasena);
        erCorreo=(EditText)findViewById(R.id.erCorreo);

        brAceptar.setOnClickListener(this);
        brCancelar.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.brAceptar:
                if(valida()) {
                    if(startRegitro()) {
                        int tempf=3;
                        editor.putInt("v_flagauth", tempf);
                        editor.commit();
                        Toast.makeText(getApplicationContext(), "pref:  "+prefs.getInt("v_flagauth",-1), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, AutenticacionActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                    }
                }
                break;
            case R.id.brCancelar:
                Intent intent2=new Intent(getApplicationContext(),AutenticacionActivity.class);
                startActivity(intent2);
                break;
        }
    }

    private boolean  startRegitro(){

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(erCorreo.getText().toString(), erContrasena.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            //display some message here
                            Toast.makeText(getApplicationContext(),"Successfully registered",Toast.LENGTH_LONG).show();
                        }else{
                            //display some message here
                            Toast.makeText(getApplicationContext(),"Ingrese un correo valido 'xx@xx.com' ",Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                });
return true;
    }

    protected boolean valida () {
        String temp_usuario=erUsario.getText().toString();
        String temp_contrasena=erContrasena.getText().toString();
        String temp_rcontrasena=erRContrasena.getText().toString();
        String temp_correo=erCorreo.getText().toString();

        if(TextUtils.isEmpty(temp_usuario)||TextUtils.isEmpty(temp_contrasena)||TextUtils.isEmpty(temp_rcontrasena)||TextUtils.isEmpty(temp_correo)){
            Toast.makeText(this, "Campos vacíos .-. ",Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            if(temp_contrasena.equals(temp_rcontrasena)) {
                if (temp_contrasena.length() >= 6) {
                    return true;
                } else {
                    Toast.makeText(this, "La contraseña debe tener mínimo 6 caracteres .-. ", Toast.LENGTH_SHORT).show();
                    erContrasena.setText("");
                    erRContrasena.setText("");
                    return false;
                }
            }
            else{
                Toast.makeText(this, "La contraseña no coincide .-. ",Toast.LENGTH_SHORT).show();
                erContrasena.setText("");
                erRContrasena.setText("");
                return false;
            }
        }
    }
}

