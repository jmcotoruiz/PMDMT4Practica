package es.studium.midialogo;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnDialogoNombreListener, OnDialogoSexoListener, OnDialogoProfesionListener, OnDialogoEspecieListener, View.OnClickListener
{
    DialogoNombre dialogoNombre;
    DialogoSexo dialogoSexo;
    DialogoEspecie dialogoEspecie;
    DialogoProfesion dialogoProfesion;
    Button btnNombre, btnSexo, btnEspecie, btnProfesion, btnAvatar;
    TextView txtNombre, txtSexo, txtEspecie, txtProfesion;
    String Nombre,Sexo,Profesion,Especie;
    FragmentManager fm = getSupportFragmentManager(); //Gestor de fragment
    Fragment Fragmento;
    FragmentTransaction ft; //Para cambiar un fragment por otro

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNombre = findViewById(R.id.botonNombre);
        btnNombre.setOnClickListener(this);

        btnSexo= findViewById(R.id.botonSexo);
        btnSexo.setOnClickListener(this);

        btnEspecie= findViewById(R.id.botonEspecie);
        btnEspecie.setOnClickListener(this);

        btnProfesion= findViewById(R.id.botonProfesion);
        btnProfesion.setOnClickListener(this);

        btnAvatar= findViewById(R.id.botonAvatar);
        btnAvatar.setOnClickListener(this);

        txtNombre = findViewById(R.id.textViewN);
        txtSexo = findViewById(R.id.textViewS);
        txtEspecie = findViewById(R.id.textViewE);
        txtProfesion = findViewById(R.id.textViewP);

    }

    @Override
    public void onClick(View v)
    {
        int id = v.getId();
        if (id == R.id.botonNombre)
        {
            dialogoNombre = new DialogoNombre();
            dialogoNombre.setCancelable(false); //para que sea modal, es decir, se quede en primer plano
            dialogoNombre.show(getSupportFragmentManager(), "Nuevo Diálogo");

        }
        if (id == R.id.botonSexo)
        {
            dialogoSexo = new DialogoSexo();
            dialogoSexo.setCancelable(false); //para que sea modal, es decir, se quede en primer plano
            dialogoSexo.show(getSupportFragmentManager(), "Nuevo Diálogo");
        }
        if (id == R.id.botonProfesion)
        {
            dialogoProfesion = new DialogoProfesion();
            dialogoProfesion.setCancelable(false); //para que sea modal, es decir, se quede en primer plano
            dialogoProfesion.show(getSupportFragmentManager(), "Nuevo Diálogo");
        }
        if (id == R.id.botonEspecie)
        {
            dialogoEspecie = new DialogoEspecie();
            dialogoEspecie.setCancelable(false); //para que sea modal, es decir, se quede en primer plano
            dialogoEspecie.show(getSupportFragmentManager(), "Nuevo Diálogo");
        }
        if (id == R.id.botonAvatar)
        {
            if(txtNombre.getText().toString().isEmpty() || txtSexo.getText().toString().isEmpty() || txtEspecie.getText().toString().isEmpty() || txtProfesion.getText().toString().isEmpty())
            {
                Toast.makeText(this, "Faltan datos por rellenar", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Fragmento = fm.findFragmentByTag("avatar");//lo mismo que arriba
                if (Fragmento == null) {
                    //Creamos un bundle, que contendrá los datos a pasar
                    Bundle datos = new Bundle();
                    //Ponemos todos los datos que queremos pasar, formato clave:valor
                    datos.putString("Nombre", Nombre);
                    datos.putString("Sexo", Sexo);
                    datos.putString("Especie", Especie);
                    datos.putString("Profesion", Profesion);

                    Log.d("voyameter", "Nombre " + Nombre);
                    Log.d("voyameter", "Sexo " + Sexo);
                    Log.d("voyameter", "Profesion " + Profesion);
                    Log.d("voyameter", "Especie " + Especie);

                    //Creamos el fragment
                    Fragment fragmento = new FragmentoAvatar();
                    //Asignamos los datos
                    fragmento.setArguments(datos);

                    ft = fm.beginTransaction();
                    ft.replace(R.id.contenedorFragmento, fragmento, "avatar");
                    ft.commit();
                }

            }
        }
    }

    @Override
    public void onDialogoGuardarListener()
    {
        Toast.makeText(this,  "Dato asignado", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void OnDialogoCancelarListener()
    {
        Toast.makeText(this,  "Cancelado", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onDataSetNombre(String nombre) //para capturar la información
    {
        Nombre = nombre;
        txtNombre.setText(nombre);
    }
    @Override
    public void onDataSetSexo(String sexo) //para capturar la información
    {
        Sexo = sexo;
        txtSexo.setText(sexo);
    }
    @Override
    public void onDataSetProfesion(String profesion) //para capturar la información
    {
        Profesion = profesion;
        txtProfesion.setText(profesion);
    }
    @Override
    public void onDataSetEspecie(String especie) //para capturar la información
    {
        Especie = especie;
        txtEspecie.setText(especie);
    }
}