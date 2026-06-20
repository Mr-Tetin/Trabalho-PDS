package controladores;

import modelo.SistemaAcademiaFacade;
import visao.PanelCadastroExercicio;

public class ControladorTelaCadastroExercicio {

	private PanelCadastroExercicio tela;
	private final SistemaAcademiaFacade facade = SistemaAcademiaFacade.getInstancia();

	public ControladorTelaCadastroExercicio(PanelCadastroExercicio tela) {
		this.tela = tela;
	}

	public void acaoPerformada(Object e) {
		if (e == tela.getBtnCadastrar()) {

			boolean cadastrado = facade.cadastrarExercicio(
					tela.getNomeExercicio().getText(),
					tela.getComboBoxExercicio().getSelectedIndex(),
					tela.getTextArea().getText()
			);

			if (cadastrado) {
				ControladorRedirecionar.caminho(1);
			}

		} else if (e == tela.getBtnVoltar()) {
			ControladorRedirecionar.caminho(1);
		}
	}
}