package es.studium.midialogo;

import static android.app.PendingIntent.getActivity;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;


import androidx.fragment.app.DialogFragment;

public class DialogoSexo extends DialogFragment
{
    OnDialogoSexoListener sListener;
    RadioButton rb1, rb2;
    String sexo;

    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        //Construir el dialogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View myView = inflater.inflate(R.layout.dialogo_sexo, null);//Para tener acceso a los componentes
        rb1 = myView.findViewById(R.id.radioButton1);
        rb2 = myView.findViewById(R.id.radioButton2);

        builder.setView(myView)
                .setTitle("Escribe el sexo")//mensaje que va a aparecer
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialogS, int which)
                    {
                        if (rb1.isChecked() == true) {
                            sexo = (String) getText(R.string.hombre);
                        }
                        else {
                            if (rb2.isChecked() == true) {
                                sexo = (String) getText(R.string.mujer);
                            }

                        }
                        sListener.onDataSetSexo(sexo.toString());
                        dialogS.dismiss(); //Cerrar el dialogo
                        sListener.onDialogoGuardarListener();
                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                        sListener.OnDialogoCancelarListener();
                    }
                });
        //Crear el objeto y devolverlo
        return builder.create();
    }

    public void onAttach(Context context)
    {
        super.onAttach(context);
        //Verificamos que la actividad principal ha implementado la interfaz
        try
        {
            //Instanciamos OnNuevoDialogoListener para poder enviar eventos a la clase Principal
            sListener = (OnDialogoSexoListener) context;
        }
        catch(ClassCastException e)
        {
            //La Actividad no implementa el interfaz
            throw new ClassCastException(context.toString()+ "debe implementar OnNuevoDialogoListener");
        }
    }
}