package es.studium.midialogo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

public class DialogoProfesion extends DialogFragment
{
    OnDialogoProfesionListener pListener;
    Spinner sp;

    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        //Construir el dialogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View myView = inflater.inflate(R.layout.dialogo_profesion, null);//Para tener acceso a los componentes
        sp = myView.findViewById(R.id.spinnerProfesion);

        builder.setView(myView)
                .setTitle("Elige una profesion")//mensaje que va a aparecer
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        pListener.onDataSetProfesion(sp.getSelectedItem().toString());
                        dialog.dismiss(); //Cerrar el dialogo
                        pListener.onDialogoGuardarListener();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                        pListener.OnDialogoCancelarListener();
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
            pListener = (OnDialogoProfesionListener)  context;
        }
        catch(ClassCastException e)
        {
            //La Actividad no implementa el interfaz
            throw new ClassCastException(context.toString()+ "debe implementar OnNuevoDialogoListener");
        }
    }
}

