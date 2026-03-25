package com.example.compmovel_01;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity(tableName = "peca")
public class Peca implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String nomePeca;
    private Double preco;
    private String nomeOficina;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNomePeca() { return nomePeca; }
    public void setNomePeca(String nomePeca) { this.nomePeca = nomePeca; }
    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
    public String getNomeOficina() { return nomeOficina; }
    public void setNomeOficina(String nomeOficina) { this.nomeOficina = nomeOficina; }

    @Override
    public String toString() {
        return nomePeca + " (" + nomeOficina + ") - R$ " + (preco != null ? preco : "0.00");
    }
}