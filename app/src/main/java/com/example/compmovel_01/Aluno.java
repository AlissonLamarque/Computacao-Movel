package com.example.compmovel_01;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "aluno")
public class Aluno implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;
    private String curso;
    private byte[] fotoBytes;

    @Override
    public String toString() {return nome;}
    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}
    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}
    public String getCpf() {return cpf;}
    public void setCpf(String cpf) {this.cpf = cpf;}
    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {this.telefone = telefone;}
    public String getEndereco() {return endereco;}
    public void setEndereco(String endereco) {this.endereco = endereco;}
    public String getCurso() {return curso;}
    public void setCurso(String curso) {this.curso = curso;}
    public byte[] getFotoBytes() { return fotoBytes; }
    public void setFotoBytes(byte[] fotoBytes) { this.fotoBytes = fotoBytes; }
}
