package npisoltec.com;

import android.app.Application;

import npisoltec.com.dao.AlunoDAO;
import npisoltec.com.model.Aluno;

public class AgendaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        adicionarAlunosExemplo();
    }

    private void adicionarAlunosExemplo() {
        AlunoDAO dao = new AlunoDAO();
        dao.salvar(new Aluno("Kevin", "85 999999999", "kevin@gmail.com"));
        dao.salvar(new Aluno("Maria", "85 999999999", "maria@gmail.com"));
        dao.salvar(new Aluno("João", "85 999999999", "joao@gmail.com"));
    }
}
