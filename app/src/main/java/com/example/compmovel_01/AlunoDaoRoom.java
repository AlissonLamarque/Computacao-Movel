package com.example.compmovel_01;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AlunoDaoRoom {
    @Insert
    long inserir(Aluno aluno);
    @Update
    void atualizar(Aluno aluno);
    @Query("SELECT * FROM aluno")
    List<Aluno> obterTodos();
    @Delete
    void excluir(Aluno aluno);
    @Query("SELECT COUNT(*) FROM aluno WHERE cpf = :cpf")
    int cpfExistente(String cpf);
    @Query("SELECT * FROM aluno WHERE nome LIKE :nome")
    List<Aluno> buscarPorNome(String nome);
}