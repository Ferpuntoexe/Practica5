package net.iesseveroochoa.FernandoMartinezPerez.practica5;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import net.iesseveroochoa.FernandoMartinezPerez.practica5.model.Dia;

import java.util.Calendar;
import java.util.Date;

public class EdicionDiaActivty extends AppCompatActivity {
    public final static String EXTRA_DIA = "Activity.dia";


    Calendar calendar = Calendar.getInstance();
    Dia diaAnt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion_dia_activty);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = new Intent();
        TextView tvFecha = findViewById(R.id.tvFecha);
        EditText etResumenDia = findViewById(R.id.etResumenDia);
        SeekBar sbValoracion = findViewById(R.id.SBValoracion);
        FloatingActionButton fabGuardar = findViewById(R.id.fabGuardar);
        TextView tvValor = findViewById(R.id.tvValor);
        EditText etmDescripcion = findViewById(R.id.etmDescripcion);
        diaAnt = getIntent().getParcelableExtra(EXTRA_DIA);
        int maxValoracion = 10;

        sbValoracion.setMax(maxValoracion);
        if (diaAnt != null){
            etResumenDia.setText(diaAnt.getResumen().toString());
            sbValoracion.setProgress(diaAnt.getValoracionDia());
            tvValor.setText(String.valueOf(diaAnt.getValoracionDia()));
            tvFecha.setText(diaAnt.getFecha().toString());
            etmDescripcion.setText(diaAnt.getContenido());

        } else {

            sbValoracion.setProgress(maxValoracion / 2);
            String valoracion = String.valueOf(maxValoracion / 2);
            tvValor.setText(valoracion);
        }



        fabGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean todoCompleto = true;
                long fecha = 0;
                if (tvFecha.getText().toString().equals(R.string.fecha)) {
                    todoCompleto = false;
                }
                String resumen = "";
                if (etResumenDia.getText().toString().equals("")) {
                    todoCompleto = false;
                } else {
                    resumen = etResumenDia.getText().toString();
                }
                String descripcion = "";
                if (etmDescripcion.getText().toString().equals("")) {
                    todoCompleto = false;
                } else {
                    descripcion = etmDescripcion.getText().toString();
                }

                if (todoCompleto == true) {
                    Dia dia = new Dia(calendar.getTime(), sbValoracion.getProgress(), resumen, descripcion);
                    intent.putExtra(EXTRA_DIA, dia);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                } else {
                    Snackbar.make(view, String.valueOf(R.string.noCompleto), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }

        });

        sbValoracion.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvValor.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });
        ImageButton ibCalendario = findViewById(R.id.ibCalendario);
        ibCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();

                DatePickerDialog dialogo = new DatePickerDialog(EdicionDiaActivty.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        calendar.set(year, monthOfYear, dayOfMonth);
                        tvFecha.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
                    }

                }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                dialogo.show();
            }
        });
    }

}
