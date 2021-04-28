package net.iesseveroochoa.FernandoMartinezPerez.practica5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.*;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.iesseveroochoa.FernandoMartinezPerez.practica5.model.Dia;
import net.iesseveroochoa.FernandoMartinezPerez.practica5.model.DiaViewModel;

public class MainActivity extends AppCompatActivity {
    public static final int OPTION_REQUEST_CREAR = 1;
    public static final int OPTION_REQUEST_EDITAR = 2;
    private DiaViewModel diaViewModel;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it ispresent.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fbNuevo = findViewById(R.id.fabNuevo);
        fbNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EdicionDiaActivty.class);
                startActivityForResult(intent, OPTION_REQUEST_CREAR);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == OPTION_REQUEST_CREAR) {
            Dia dia = data.getParcelableExtra(EdicionDiaActivty.EXTRA_DIA);
            diaViewModel.addDia(dia);


            } else if (requestCode == OPTION_REQUEST_EDITAR) {


            }
        }
    }
}