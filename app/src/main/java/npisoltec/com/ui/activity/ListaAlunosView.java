package npisoltec.com.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;

import npisoltec.com.dao.AlunoDAO;
import npisoltec.com.model.Aluno;

public class ListaAlunosView {

    @SuppressLint("StaticFieldLeak")
    private static ListaAlunosAdapter adapter;
    private final AlunoDAO dao;
    private final Context context;

    public ListaAlunosView(Context context) {
        this.context = context;
        adapter = new ListaAlunosAdapter(this.context);
        this.dao = new AlunoDAO();
    }

    public void confirmarRemocaoAluno(final MenuItem item) {
        //Dialog de confirmação de remoção
        new AlertDialog.
                Builder(context).
                setTitle("Removendo aluno").
                setMessage("Tem certeza que deseja remover o aluno?").
                setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
                        remove(alunoEscolhido);
                    }
                }).
                setNegativeButton("Não", null).
                show();
    }

    public void atualizarAlunos() {
        adapter.atualizarLista(dao.todos());
    }

    public void configurarAdapter(ListView listaAlunos) {
        adapter = new ListaAlunosAdapter(context);
        listaAlunos.setAdapter(adapter);
    }

    private void remove(Aluno aluno) {
        dao.remove(aluno);
        adapter.remove(aluno);
    }

}
