package controladores;

import modelo.SistemaAcademiaFacade;
import visao.PanelCadastroTreino;

import javax.swing.*;

/**
 * Controlador da tela de cadastro de treino REFATORADO com Facade.
 *
 * Nao ha mais importacoes diretas de Aluno, Treino, Exercicio,
 * TipoDeGrupamento ou BancoDeDados.
 */
@SuppressWarnings("unchecked")
public class ControladorTelaCadastroTreino {

	private PanelCadastroTreino tela;
	private final SistemaAcademiaFacade facade = SistemaAcademiaFacade.getInstancia();

	public ControladorTelaCadastroTreino(PanelCadastroTreino tela) {
		this.tela = tela;
	}

	public void acaoPerformada(Object e) {
		if (e == tela.getBtnCadastrar()) {

			boolean cadastrado = facade.cadastrarTreino(
					tela.getTextField().getText(),
					tela.getComboGrupo().getSelectedIndex(),
					tela.getComboAluno().getSelectedItem().toString(),
					tela.getListExerciciosTreino().getSelectedValuesList(),
					(int) tela.getSpinRep().getValue(),
					(int) tela.getSpinSerie().getValue()
			);

			if (cadastrado) {
				ControladorRedirecionar.caminho(1);
			} else {
				JOptionPane.showMessageDialog(ControladorRedirecionar.getTela(), "Número máximo de treinos atingidos ou dados inconsistentes\n(Ex: Dados faltando, itens não selecionados ou nomes iguais).", "Cadastro Incompleto", JOptionPane.WARNING_MESSAGE);
			}

		} else if (e == tela.getBtnInicio()) {
			ControladorRedirecionar.caminho(1);

		} else if (e == tela.getBtnExercicios()) {
			// A listagem de exercicios por grupo tambem passa pela Facade
			tela.getListExerciciosTreino().setModel(
					facade.listarExerciciosPorGrupo(tela.getComboGrupo().getSelectedIndex())
			);
			tela.getPanelMain1().setVisible(false);
			tela.getPanelMain2().setVisible(true);

		} else if (e == tela.getBtnVoltar()) {
			tela.getPanelMain1().setVisible(true);
			tela.getPanelMain2().setVisible(false);
		}
	}
}