package com.example.compmovel_01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editNomePeca;
    private EditText editPreco;
    private EditText editNomeOficina;

    private PecaDaoRoom pecaDaoRoom;
    private Peca peca = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNomePeca = findViewById(R.id.editNomePeca);
        editPreco = findViewById(R.id.editPreco);
        editNomeOficina = findViewById(R.id.editNomeOficina);

        pecaDaoRoom = AppDatabase.getInstance(this).pecaDaoRoom();

        Intent it = getIntent();
        if (it.hasExtra("peca")) {
            peca = (Peca) it.getSerializableExtra("peca");
            editNomePeca.setText(peca.getNomePeca());
            editPreco.setText(peca.getPreco().toString());
            editNomeOficina.setText(peca.getNomeOficina());
        }
    }

    public void salvar(View view) {
        if (editNomePeca.getText().toString().isEmpty() || editPreco.getText().toString().isEmpty()) {
            Toast.makeText(this, "Preencha o nome e o preço!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            if (peca == null) {
                Peca p = new Peca();
                p.setNomePeca(editNomePeca.getText().toString());
                p.setPreco(Double.parseDouble(editPreco.getText().toString()));
                p.setNomeOficina(editNomeOficina.getText().toString());

                long id = pecaDaoRoom.inserir(p);
                Toast.makeText(this, "Peça inserida com ID: " + id, Toast.LENGTH_SHORT).show();
            } else {
                peca.setNomePeca(editNomePeca.getText().toString());
                peca.setPreco(Double.parseDouble(editPreco.getText().toString()));
                peca.setNomeOficina(editNomeOficina.getText().toString());

                pecaDaoRoom.atualizar(peca);
                Toast.makeText(this, "Peça Atualizada", Toast.LENGTH_SHORT).show();
            }
            limparCampos();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Preço inválido", Toast.LENGTH_SHORT).show();
        }
    }

    private void limparCampos() {
        editNomePeca.setText("");
        editPreco.setText("");
        editNomeOficina.setText("");
        peca = null;
    }

    public void irParaListar(View view) {
        Intent intent = new Intent(this, TelaListagem.class);
        startActivity(intent);
    }
}