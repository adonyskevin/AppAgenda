package npisoltec.com.ui.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import npisoltec.com.R;
import npisoltec.com.model.Aluno;

public class ListaAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;

    public ListaAlunosAdapter(Context context) {
        this.context = context;
    }

    @Override
    //Indica a quantidade de elementos que o adapter vai ter
    public int getCount() {
        //Retorna a quantidade de alunos no ArrayList
        return alunos.size();
    }

    //Retorna o Aluno na posição desejada
    @Override
    public Aluno getItem(int posicao) {
        return alunos.get(posicao);
    }

    //Retorna o Id do item na posição desejada
    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    //Retorna a view criada para cada elemento.
    @Override
    public View getView(int posicao, View convertView, ViewGroup viewGroup) {
        View viewCriada = criarView(viewGroup);
        vinculaAlunoView(posicao, viewCriada);
        return viewCriada;
    }

    private void vinculaAlunoView(int posicao, View view) {
        Aluno alunoDevolvido = alunos.get(posicao);

        TextView nome = view.findViewById(R.id.item_aluno_nome);
        nome.setText(alunoDevolvido.getNome());
        TextView telefone = view.findViewById(R.id.item_aluno_telefone);
        telefone.setText(alunoDevolvido.getTelefone());
    }

    private View criarView(ViewGroup viewGroup) {
        return LayoutInflater.
                from(context).
                inflate(R.layout.item_aluno, viewGroup, false);
    }

    public void atualizarLista(List<Aluno> alunos){
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
        notifyDataSetChanged();
    }
}
