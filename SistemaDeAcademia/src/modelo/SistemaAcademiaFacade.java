package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SistemaAcademiaFacade {
    private static SistemaAcademiaFacade instancia;

    private SistemaAcademiaFacade() {}

    public static SistemaAcademiaFacade getInstancia() {
        if (instancia == null) {
            instancia = new SistemaAcademiaFacade();
        }
        return instancia;
    }


    //-----------Parte Aluno-----------
    public boolean cadastrarAluno(String nome, int idade, double altura, double peso){
        if(nome == null || nome.isBlank()) return false;
        if(buscarAluno(nome) != null) return false;

        Aluno aluno = new Aluno(nome, idade, altura, peso);
        aluno.cadastro();
        return true;
    }
    public boolean editarAluno(String nomeOriginal, String nomeNovo, int idadeNova, double alturaNova, double pesoNovo){
        if(nomeOriginal == null || nomeOriginal.isBlank() || nomeNovo == null || nomeNovo.isBlank()) return false;

        Aluno alunoNovo = new Aluno(nomeNovo, idadeNova, alturaNova, pesoNovo);
        alunoNovo.editar(nomeOriginal);
        return true;
    }
    public boolean deletarAluno(String nome){
        if(nome == null || nome.isBlank()) return false;
        Aluno aluno = buscarAluno(nome);

        aluno.deletar();
        return true;
    }
    public Aluno buscarAluno(String nome) {
        return Aluno.getUmAluno(nome);
    }
    public String[] listarAlunos(){
        return Aluno.getTodosAlunos();
    }


    //-----------Parte Exercício-----------
    public boolean cadastrarExercicio(String nome, int tipoIndice, String descricao){
        if(nome == null || nome.isBlank()) return false;
        if(buscarExercicio(nome) != null) return false;

        Exercicio exercicio = new Exercicio(nome, TipoDeGrupamento.pegarTipo(tipoIndice + 1), descricao);
        exercicio.cadastro();
        return true;
    }
    public boolean editarExercicio(String nomeOriginal, String nomeNovo, int tipoIndice, String descricaoNova){
        if(nomeOriginal == null || nomeOriginal.isBlank() || nomeNovo == null || nomeNovo.isBlank()) return false;

        Exercicio exercicioNovo = new Exercicio(nomeNovo, TipoDeGrupamento.pegarTipo(tipoIndice + 1), descricaoNova);
        exercicioNovo.editar(nomeOriginal);
        return true;
    }
    public Exercicio buscarExercicio(String nome) {
        return Exercicio.getUmExercicio(nome);
    }
    public javax.swing.ListModel<String> listarExerciciosPorGrupo(int grupoIndex) {
        return Exercicio.listaExercicio(grupoIndex);
    }


    //-----------Parte Treino-----------
    public boolean cadastrarTreino(String nomeTreino, int tipoIndice, String nomeAluno, List<String> nomeExercicio, int repeticoes, int series){
        int treinosMaximoAluno = 4;
        if(nomeTreino == null || nomeTreino.isBlank()) return false;
        if(Treino.getUmTreino(nomeTreino) != null) return false;
        if(nomeExercicio == null || nomeExercicio.isEmpty()) return false;


        Aluno aluno = buscarAluno(nomeAluno);
        //verifica a quantida de aluno e dados
        if(aluno == null || aluno.getTreinos().size() >= treinosMaximoAluno) return false;

        Treino treino = new Treino(nomeTreino, TipoDeGrupamento.pegarTipo(tipoIndice + 1), Exercicio.parearExercicios(nomeExercicio), repeticoes, series);
        treino.cadastro();
        anexarTreino(nomeAluno, treino);
        return true;
    }
    public Treino buscarTreino(String nome) {
        return Treino.getUmTreino(nome);
    }
    public ArrayList<Treino> listarTreinos() {
        return BancoDeDados.getInstancia().getTreinos();
    }
    private void anexarTreino(String nomeAluno, Treino treino) {
        for(Aluno aluno : BancoDeDados.getInstancia().getAlunos()) {
            if(aluno.getNome().equals(nomeAluno)){
                ArrayList<Treino> lista = new ArrayList<>(aluno.getTreinos());
                if(lista.size() < 5){
                    lista.add(treino);
                    aluno.setTreinos(lista);
                }
                return;
            }
        }
    }
}