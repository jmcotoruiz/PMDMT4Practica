package es.studium.midialogo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

public class DialogoNombre extends DialogFragment
{
    OnDialogoNombreListener mListener;
    EditText nombre;

    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        //Construir el dialogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View myView = inflater.inflate(R.layout.dialogo_nombre, null);//Para tener acceso a los componentes
        nombre = myView.findViewById(R.id.editTextTextPersonName);

        builder.setView(myView)
                .setTitle("Escribe el nombre")//mensaje que va a aparecer
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        try
                        {
                            mListener.onDataSetNombre(nombre.getText().toString());
                        }
                        catch(Exception e)
                        {
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        dialog.dismiss(); //Cerrar el dialogo
                        mListener.onDialogoGuardarListener();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                        mListener.OnDialogoCancelarListener();
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
            mListener = (OnDialogoNombreListener) context;
        }
        catch(ClassCastException e)
        {
            //La Actividad no implementa el interfaz
            throw new ClassCastException(context.toString()+ "debe implementar OnNuevoDialogoListener");
        }
    }
}
