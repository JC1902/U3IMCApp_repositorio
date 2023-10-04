/*------------------------------------------------------------------------------------------
:*                         TECNOLOGICO NACIONAL DE MEXICO
:*                                CAMPUS LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                             DESARROLLO EN ANDROID "A"
:*
:*                   SEMESTRE: AGO-DIC/2023    HORA: 08-09 HRS
:*
:*                                Calculadora IMC
:*
:*  Archivo     : MainActivity.java
:*  Autor       : Jorge Cisneros de la Torre     20130789
:*  Fecha       : 01/Oct/2023
:*  Compilador  : Android Studio Giraffe 2022.3.1
:*  Descripción : Calculadora de IMC con método para mostrar la condición de salud
                  según el valor resultante. Para mostrar el resultado se usa la
                  clase AlertDialog.Builder
:*  Ultima modif:
:*  Fecha       Modificó             Motivo
:*==========================================================================================
:*
:*------------------------------------------------------------------------------------------*/

package mx.edu.itl.u3imcapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//----------------------------------------------------------------------------------------------

public class MainActivity extends AppCompatActivity {

    //----------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //----------------------------------------------------------------------------------------------
    // Método para calcular el IMC y mostrar el resultado y la condición de salud
    public void btnCalcularIMCClick ( View v ) {
        float peso = 0, estatura = 0, IMC = 0;

        try {
            // Obtener el valor de los campos de texto
            EditText edtPeso = findViewById(R.id.etxPeso);
            EditText edtEstatura = findViewById(R.id.etxEstatura);

            // Convertir esos valores a tipo float
            peso = Float.parseFloat(edtPeso.getText().toString());
            estatura = Float.parseFloat(edtEstatura.getText().toString());

            if ( peso == 0 || estatura == 0 ) {
                Toast.makeText( this, "Debe ingresar valores mayor que cero", Toast.LENGTH_LONG).show();
            } else {

                IMC = peso / (estatura * estatura);
                CharSequence condSalud = mostrarCondicionSalud(IMC);

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("IMCApp")
                        .setMessage("IMC = " + IMC + "\nSu condición de salud es: " + condSalud)
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create().show();
            }
        } catch ( NumberFormatException e ) {
            // En caso de que los datos estén equivocados salta este Toast
            Toast.makeText( this, "Ingrese valores correctos para hacer el cálculo", Toast.LENGTH_LONG).show();
        }
    }

    //----------------------------------------------------------------------------------------------

    public void btnAcercaDeClick ( View v ) {
        // Cambiar al activity del Acerca De
        Intent intent = new Intent( this, AcercaDeActivity.class );
        startActivity( intent );
    }

    //----------------------------------------------------------------------------------------------

    // Método para comprobar la condición de salud según el IMC
    public CharSequence mostrarCondicionSalud ( float IMC ) {

        CharSequence condicionSalud = "";

        if ( IMC <= 15 ) {
            condicionSalud = "Delgadez muy severa";
        } else if ( IMC > 15 && IMC <= 15.9 ) {
            condicionSalud = "Delgadez severa";
        } else if ( IMC >= 16 && IMC <= 18.4 ) {
            condicionSalud = "Delgadez";
        } else if ( IMC >= 18.5 && IMC <= 24.9 ) {
            condicionSalud = "Peso Saludable";
        } else if ( IMC >= 25 && IMC <= 29.9 ) {
            condicionSalud = "Sobrepeso";
        } else if ( IMC >= 30 && IMC <= 34.9 ) {
            condicionSalud = "Obesidad Moderada";
        } else if ( IMC >= 35 && IMC <= 39.9 ) {
            condicionSalud = "Obesidad severa";
        } else if ( IMC >= 40 ) {
            condicionSalud = "Obesidad muy severa (obesidad mórbida)";
        }

        return condicionSalud;
    }

    //----------------------------------------------------------------------------------------------
}