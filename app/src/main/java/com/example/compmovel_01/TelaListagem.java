package com.example.compmovel_01;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class TelaListagem extends AppCompatActivity {
    private ListView listView;
    private PecaDaoRoom daoRoom;
    private EditText editPesquisa;
    private List<Peca> pecas;
    private List<Peca> pecasFiltradas = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_listagem);

        editPesquisa = findViewById(R.id.editTextPesquisar);
        listView = findViewById(R.id.lista_pecas);

        daoRoom = AppDatabase.getInstance(this).pecaDaoRoom();
        registerForContextMenu(listView);

        pecas = daoRoom.obterTodas();
        pecasFiltradas.addAll(pecas);

        atualizarLista();
    }

    private void atualizarLista() {
        ArrayAdapter<Peca> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pecasFiltradas);
        listView.setAdapter(adaptador);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);
    }

    public void voltarParaCadastro(View view){
        finish();
    }

    public void excluir(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Peca pecaExcluir = pecasFiltradas.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Realmente deseja excluir: " + pecaExcluir.getNomePeca() + "?")
                .setNegativeButton("NÃO", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        daoRoom.excluir(pecaExcluir);
                        pecasFiltradas.remove(pecaExcluir);
                        pecas.remove(pecaExcluir);
                        listView.invalidateViews();
                        Toast.makeText(TelaListagem.this, "Peça excluída", Toast.LENGTH_SHORT).show();
                    }
                } ).create();
        dialog.show();
    }

    public void atualizar(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Peca pecaAtualizar = pecasFiltradas.get(menuInfo.position);

        Intent it = new Intent(this, MainActivity.class);
        it.putExtra("peca", pecaAtualizar);
        startActivity(it);
    }

    @Override
    protected void onResume() {
        super.onResume();
        pecas = daoRoom.obterTodas();
        pecasFiltradas.clear();
        pecasFiltradas.addAll(pecas);
        atualizarLista();
    }

    public void pesquisarPeca(View view){
        String nome = editPesquisa.getText().toString();
        pecasFiltradas.clear();

        if(nome.isEmpty()){
            pecasFiltradas.addAll(daoRoom.obterTodas());
        } else {
            pecasFiltradas.addAll(daoRoom.buscarPorNome("%" + nome + "%"));
        }

        atualizarLista();
    }
}