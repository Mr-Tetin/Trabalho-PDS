package modelo;

import java.util.ArrayList;

/**
 * @author Kaua Vinicius
 * 
 *         Classe responsavel por armanezar todos os bancos de dados
 * 
 */
//teste
public class BancoDeDados {

	private static BancoDeDados instancia;

	private ArrayList<Aluno> alunos;
	private ArrayList<Exercicio> exercicios;
	private ArrayList<Treino> treinos;

	private BancoDeDados() {
		alunos = new ArrayList<>();
		exercicios = new ArrayList<>();
		treinos = new ArrayList<>();
	}

	public static BancoDeDados getInstancia() {

		if (instancia == null) {
			instancia = new BancoDeDados();
		}

		return instancia;
	}

	public ArrayList<Aluno> getAlunos() {
		return alunos;
	}

	public ArrayList<Exercicio> getExercicios() {
		return exercicios;
	}

	public ArrayList<Treino> getTreinos() {
		return treinos;
	}
}


