package net.iesseveroochoa.FernandoMartinezPerez.practica5.model;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.Date;
import java.util.List;



@Dao
public interface DiarioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Dia dia);

    @Query("DELETE FROM " + Dia.TABLE_NAME)
    void deleteAll();

    @Delete
    void deleteByDia(Dia dia);

    @Query("DELETE FROM DIARIO WHERE _id = :id")
    void deleteByDiaId(int id);

    @Update
    void update(Dia dia);


    @Query("SELECT * FROM " + Dia.TABLE_NAME)
    LiveData<List<Dia>> getAllDias();

    @Query("SELECT * FROM diario ORDER BY :ordenadoPor ASC")
    LiveData<List<Dia>> getDiasOrdenadoPor(String ordenadoPor);


    @Query("SELECT COUNT(*) from diario")
    int countDias();

    @Query("SELECT * FROM diario WHERE fecha <= :fecha")
    LiveData<List<Dia>> DiasAnterioresQue(Date fecha);


    @Query("SELECT AVG(valoracionDia) FROM "+ Dia.TABLE_NAME)
    Single<Integer> getValoracionTotal();}
