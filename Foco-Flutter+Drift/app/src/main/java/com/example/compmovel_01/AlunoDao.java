package com.example.compmovel_01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AlunoDao {
    private Conexao conexao;
    private SQLiteDatabase banco;

    public AlunoDao(Context context){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Aluno aluno){
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("cpf", aluno.getCpf());
        values.put("telefone", aluno.getTelefone());
        values.put("endereco", aluno.getEndereco());
        values.put("curso", aluno.getCurso());
        values.put("fotoBytes", aluno.getFotoBytes());
        return banco.insert("aluno", null, values);
    }

    public List<Aluno> obterTodos(){
        List<Aluno> alunos = new ArrayList<>();
        Cursor cursor = banco.query("aluno", new String[]{"id", "nome", "cpf", "telefone", "endereco", "curso", "fotoBytes"},
                null, null, null, null, null, null);
        while (cursor.moveToNext()){
            Aluno a = new Aluno();
            a.setId(cursor.getInt(0));
            a.setNome(cursor.getString(1));
            a.setCpf(cursor.getString(2));
            a.setTelefone(cursor.getString(3));
            a.setEndereco(cursor.getString(4));
            a.setCurso(cursor.getString(5));
            a.setFotoBytes(cursor.getBlob(6));
            alunos.add(a);
        }
        return alunos;
    }

    public void excluir(Aluno a){
        banco.delete("aluno", "id = ?",new String[]{a.getId().toString()});
    }

    public void atualizar(Aluno aluno){
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("cpf", aluno.getCpf());
        values.put("telefone", aluno.getTelefone());
        values.put("endereco", aluno.getEndereco());
        values.put("curso", aluno.getCurso());
        values.put("fotoBytes", aluno.getFotoBytes());
        banco.update("aluno", values, "id = ?", new String[]{aluno.getId().toString()});
    }
}
