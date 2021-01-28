package npisoltec.com.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.Nullable;

import npisoltec.com.R;
import npisoltec.com.model.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String CHAVE_ALUNO = "aluno";
    private final ListaAlunosView listaAlunosView = new ListaAlunosView(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle("Lista de Alunos");
        configurarFabNovoAluno();
        configurarListaAlunos();
    }

    //Inflando o menu de contexto
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    // só funciona se a view registrada for um AdapterView
    @Override
    public boolean onContextItemSelected(@NonNull final MenuItem item) {
        // Verificando se o menu clicado é o Remover
        if (item.getItemId() == R.id.activity_lista_alunos_menu_remover) {
            listaAlunosView.confirmarRemocaoAluno(item);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaAlunosView.atualizarAlunos();
    }

    private void configurarFabNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novoAluno);
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirFormularioModoInsereAluno();
            }
        });
    }

    private void abrirFormularioModoInsereAluno() {
        startActivity(new Intent(ListaAlunosActivity.this,
                FormularioAlunoActivity.class));
    }

    private void configurarListaAlunos() {
        ListView listaAlunos = findViewById(R.id.activity_lista_alunos_ListView);
        listaAlunosView.configurarAdapter(listaAlunos);
        configurarListenerDeClickPorItem(listaAlunos);
        registerForContextMenu(listaAlunos);
    }

    private void configurarListenerDeClickPorItem(ListView listaAlunos) {
        //Setando o AdapterView
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
                abrirFormularioModoEditarAluno(alunoEscolhido);
            }
        });
    }

    private void abrirFormularioModoEditarAluno(Aluno aluno) {
        Intent vaiParaFormularioAluno = new Intent(ListaAlunosActivity.this,
                FormularioAlunoActivity.class);
        vaiParaFormularioAluno.putExtra(CHAVE_ALUNO, aluno);

        startActivity(vaiParaFormularioAluno);
    }
}