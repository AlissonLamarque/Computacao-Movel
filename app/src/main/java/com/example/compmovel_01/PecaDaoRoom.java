package com.example.compmovel_01;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface PecaDaoRoom {
    @Insert
    long inserir(Peca peca);

    @Query("SELECT * FROM peca")
    List<Peca> obterTodas();

    @Delete
    void excluir(Peca peca);

    @Update
    void atualizar(Peca peca);

    @Query("SELECT * FROM peca WHERE nomePeca LIKE :nome")
    List<Peca> buscarPorNome(String nome);
}