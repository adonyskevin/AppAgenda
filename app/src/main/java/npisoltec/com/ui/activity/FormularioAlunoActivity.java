package npisoltec.com.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import npisoltec.com.R;
import npisoltec.com.dao.AlunoDAO;
import npisoltec.com.model.Aluno;

import static npisoltec.com.ui.activity.ListaAlunosActivity.CHAVE_ALUNO;

public class FormularioAlunoActivity extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;

    private Aluno aluno;

    private final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        inicializacaoDosCampos();
        carregarAluno();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_formulario_aluno_menu_salvar){
            finalizarFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregarAluno() {
        //Recebendo os dados da outra Activity
        Intent dadosAlunoEscolhido = getIntent();

        //Verificando se o Extra recebido não é nulo
        if(dadosAlunoEscolhido.hasExtra(CHAVE_ALUNO)){
            setTitle("Editar Aluno");
            aluno = (Aluno) dadosAlunoEscolhido.getSerializableExtra(CHAVE_ALUNO);

            //Setando os dados do aluno escolhido no formulário para alterá-los
            campoNome.setText(aluno.getNome());
            campoTelefone.setText(aluno.getTelefone());
            campoEmail.setText(aluno.getEmail());
        }
        else{
            setTitle("Novo aluno");
            aluno = new Aluno();
        }
    }

    private void finalizarFormulario() {
        preencherAluno();
        //Verifica se aluno possui um id válido para edição
        if(aluno.temIdValido()){
            dao.editar(aluno);
        }

        //Caso não tenha id válido, adicionamos um novo Aluno a lista de alunos, finalizamos a Activity atual e voltamos para a Activity anterior
        else{
            dao.salvar(aluno);
        }
        finish();
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_input_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_input_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_input_email);
    }

    // Método criado através do atalho Ctrl + Alt + M com a função de preencher um Aluno
    private void preencherAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
    }
}