package com.example.compmovel_01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText nome;
    private EditText cpf;
    private EditText telefone;
    private EditText endereco;
    private EditText curso;
    private AlunoDao dao;
    private Aluno aluno = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = findViewById(R.id.editNome);
        cpf = findViewById(R.id.editCpf);
        telefone = findViewById(R.id.editTelefone);
        endereco = findViewById(R.id.editEndereco);
        curso = findViewById(R.id.editCurso);

        dao = new AlunoDao(this);

        Intent it = getIntent(); //pega intenção
        if(it.hasExtra("aluno")){
            aluno = (Aluno) it.getSerializableExtra("aluno");
            nome.setText(aluno.getNome().toString());
            cpf.setText(aluno.getCpf());
            telefone.setText(aluno.getTelefone());
            endereco.setText(aluno.getEndereco());
            curso.setText(aluno.getCurso());
        }
    }

    public void salvar(View view){
        if(aluno==null) {
            Aluno a = new Aluno();
            a.setNome(nome.getText().toString());
            a.setCpf(cpf.getText().toString());
            a.setTelefone(telefone.getText().toString());
            a.setEndereco(endereco.getText().toString());
            a.setCurso(curso.getText().toString());
            long id = dao.inserir(a);
            Toast.makeText(this, "Aluno inserido com id: "+id, Toast.LENGTH_SHORT).show();
        } else {
            aluno.setNome(nome.getText().toString());
            aluno.setCpf(cpf.getText().toString());
            aluno.setTelefone(telefone.getText().toString());
            dao.atualizar(aluno);
            Toast.makeText(this,"Aluno Atualizado!! com id: ", Toast.LENGTH_SHORT).show();
        }
    }

    public void irParaListar(View view){
        Intent intent = new Intent(this, TelaListagem.class);
        startActivity(intent);
    }
}