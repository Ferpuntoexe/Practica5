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

    public static final int OPTION_REQUEST_CREAR = 1;
    public static final int OPTION_REQUEST_EDITAR = 2;
    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion_dia_activty);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = new Intent();
        TextView tvFecha = findViewById(R.id.tvFecha);
        TextView tvResumen = findViewById(R.id.tvResumen);
        SeekBar sbValoracion = findViewById(R.id.SBValoracion);
        FloatingActionButton fabGuardar = findViewById(R.id.fabGuardar);
        TextView tvValor = findViewById(R.id.tvValor);
        EditText etmDescripcion = findViewById(R.id.etmDescripcion);

        fabGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean todoCompleto = true;
                long fecha = 0;
                if (tvFecha.equals(R.string.fecha)) {
                    todoCompleto = false;
                } else {
                    fecha = Long.parseLong(tvFecha.getText().toString());
                }
                String resumen = "";
                if (tvResumen.equals("")) {
                    todoCompleto = false;
                } else {
                    resumen = tvResumen.getText().toString();
                }
                String descripcion = "";
                if (etmDescripcion.equals("")) {
                    todoCompleto = false;
                } else {
                    descripcion = etmDescripcion.getText().toString();
                }

                if (todoCompleto = true) {
                    Dia dia = new Dia(fecha, sbValoracion.getProgress(), resumen, descripcion);
                    intent.putExtra(EXTRA_DIA, dia);
                    setResult(Activity.RESULT_OK, intent);
                } else if (todoCompleto = false) {
                    Snackbar.make(view, String.valueOf(R.string.noCompleto), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }

        });
        int maxValoracion = 10;
        sbValoracion.setMax(maxValoracion);
        sbValoracion.setProgress(maxValoracion / 2);
        String valoracion = String.valueOf(maxValoracion / 2);
        tvValor.setText(valoracion);
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
