package es.studium.midialogo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.Random;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentoAvatar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentoAvatar extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageView img1,img2;
    View  view;
    TextView vida, magia, fuerza, velocidad;
    ProgressBar barraVida, barraMagia, barraFuerza, barraVelocidad;
    public FragmentoAvatar() {
        // Required empty public constructor
    }

    public static FragmentoAvatar newInstance(String param1, String param2, String param3, String param4)
    {
        FragmentoAvatar fragment = new FragmentoAvatar();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragmento, container, false);

        barraVida = view.findViewById(R.id.progressBarVi);
        barraMagia = view.findViewById(R.id.progressBarM);
        barraFuerza = view.findViewById(R.id.progressBarF);
        barraVelocidad = view.findViewById(R.id.progressBarVe);
        vida = view.findViewById(R.id.textViewPV);
        magia = view.findViewById(R.id.textViewPM);
        fuerza = view.findViewById(R.id.textViewPF);
        velocidad = view.findViewById(R.id.textViewPVe);
        return view;
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        Bundle datosRecuperados = getArguments(); //Bundle para recibir los datos
        if(datosRecuperados == null)
        {
            Log.d("PasandoDatos", "Error"); //error si no llegan bien
        }
        else
        {
            //Guardamos datos
            String nombreFinal = datosRecuperados.getString("Nombre").toString();
            Log.d("pasandoDatos", "El nombre es "+nombreFinal);
            String sexoFinal = datosRecuperados.getString("Sexo");
            Log.d("pasandoDatos", "El sexo es "+sexoFinal);
            String especieFinal = datosRecuperados.getString("Especie");
            Log.d("pasandoDatos", "La especie es "+especieFinal);
            String profesionFinal = datosRecuperados.getString("Profesion");
            Log.d("pasandoDatos", "La profesion es "+profesionFinal);

            //Generamos numeros aleatorios para cada atributo
            Random random = new Random();
            int randomVida = random.nextInt(101); // Esto generará un número entre 0 y 100
            int randomMagia = random.nextInt(11); // Esto generará un número entre 0 y 10
            int randomFuerza = random.nextInt(21); // Esto generará un número entre 0 y 20
            int randomVelocidad = random.nextInt(6); // Esto generará un número entre 0 y 5
            //Numero aleatorio en los puntos
            vida.setText(String.valueOf(randomVida));
            magia.setText(String.valueOf(randomMagia));
            fuerza.setText(String.valueOf(randomFuerza));
            velocidad.setText(String.valueOf(randomVelocidad));
            //Mismo numero aleatorio en las barras
            barraVida.setProgress(randomVida);
            barraMagia.setProgress(randomMagia);
            barraFuerza.setProgress(randomFuerza);
            barraVelocidad.setProgress(randomVelocidad);

        }
    }
}