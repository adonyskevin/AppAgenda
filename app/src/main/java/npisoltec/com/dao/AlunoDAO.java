package npisoltec.com.dao;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import npisoltec.com.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salvar(Aluno aluno) {
        alunos.add(aluno);
        aluno.setId(contadorDeIds);
        contadorDeIds++;
    }

    public void editar(Aluno aluno) {
        Aluno alunoEncontrado = buscarAlunoPeloId(aluno);
        if (alunoEncontrado != null) {
            int posicaoAlunoEncontrado = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoAlunoEncontrado, aluno);
        }
    }

    @Nullable
    private Aluno buscarAlunoPeloId(Aluno aluno) {
        for (Aluno a : alunos) {
            if (a.getId() == aluno.getId()) {
                return a;
            }
        }
        return null;
    }

    // envia uma cópia da lista de alunos para fora da classe
    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

    public void remove(Aluno aluno) {
        Aluno alunoDevolvido = buscarAlunoPeloId(aluno);
        if(alunoDevolvido != null){
            alunos.remove(alunoDevolvido);
        }
    }
}
