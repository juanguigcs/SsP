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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    Button blAceptar,blCancelar;
    EditText erContrasena,erCorreo;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //pref compartidas
        prefs= getSharedPreferences("uno",MODE_PRIVATE);
        editor=prefs.edit();

        setContentView(R.layout.activity_login);
        blAceptar =(Button)findViewById(R.id.blAceptar);
        blCancelar=(Button)findViewById(R.id.blCancelar);
        erContrasena=(EditText)findViewById(R.id.erContrasena);
        erCorreo=(EditText)findViewById(R.id.erCorreo);

        blAceptar.setOnClickListener(this);
        blCancelar.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.blAceptar:
                if(valida()) {
                    if(startLogin()) {
                        int tempf=3;
                        editor.putInt("v_flagauth", tempf);
                        editor.commit();
                        Toast.makeText(getApplicationContext(), "pref:  "+prefs.getInt("v_flagauth",-1), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, AutenticacionActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        overridePendingTransition(0, 0);
                        startActivity(intent);
                    }
                }
                break;
            case R.id.blCancelar:
                Intent intent2=new Intent(getApplicationContext(),AutenticacionActivity.class);
                startActivity(intent2);
                finish();
                break;
        }
    }
    private boolean  startLogin() {
        //logging in the user
        firebaseAuth.signInWithEmailAndPassword(erCorreo.getText().toString(), erContrasena.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            //display some message here
                            Toast.makeText(getApplicationContext(),"Successfully registered",Toast.LENGTH_LONG).show();
                        }else{
                            //display some message here
                            Toast.makeText(getApplicationContext(),"correo y/o contraseña invalida ",Toast.LENGTH_LONG).show();
                            return ;
                        }

                    }
                });
        return true;
    }


    protected boolean valida () {
        String temp_contrasena = erContrasena.getText().toString();
        String temp_correo = erCorreo.getText().toString();

        if (TextUtils.isEmpty(temp_contrasena) || TextUtils.isEmpty(temp_correo)) {
            Toast.makeText(this, "Campos vacíos .-. ", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}