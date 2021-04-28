package net.iesseveroochoa.FernandoMartinezPerez.practica5.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class DiaViewModel extends AndroidViewModel {
    private MutableLiveData<List<Dia>> listaDiasLiveData;

    private List<Dia> listaDias;

    public DiaViewModel(@NonNull Application application) {
        super(application);
        listaDiasLiveData = new MutableLiveData<>();
        listaDiasLiveData.setValue(listaDias);
    }
    public void setListaDias(List<Dia> listaDias) {
        this.listaDias = listaDias;
    }

    public void addDia(Dia dia) {
        int i = listaDias.indexOf(dia);
        if (i < 0) {
            listaDias.add(dia);
            listaDiasLiveData.setValue(listaDias);

        } else {
            listaDias.remove(i);
            listaDias.add(i, dia);
            listaDiasLiveData.setValue(listaDias);

        }
    }
    public void delDia(Dia dia) {
        if (listaDias.size() > 0) {
            listaDias.remove(dia);
            listaDiasLiveData.setValue(listaDias);
        }
    }
    public int getItemCount() {
        if (listaDias != null) {
            return listaDias.size();
        } else {
            return 0;
        }
    }
    public int diaIndexOf(Dia dia) {
        if (dia == null) {
            return 0;
        } else {
            return listaDias.indexOf(dia);
        }
    }

}
