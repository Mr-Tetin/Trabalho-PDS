package controladores;

import modelo.SistemaAcademiaFacade;
import visao.PanelCadastroAluno;

public class ControladorTelaCadastroAluno {

	private PanelCadastroAluno tela;

	private final SistemaAcademiaFacade facade = SistemaAcademiaFacade.getInstancia();

	public ControladorTelaCadastroAluno(PanelCadastroAluno tela) {
		this.tela = tela;
	}

	public void acaoPerformada(Object e) {
		if (e == tela.getBtnCadastrar()) {
			boolean cadastrado = facade.cadastrarAluno(
					tela.getTextNomeAluno().getText(),
					(int) tela.getSpinIdade().getValue(),
					(double) tela.getSpinAltura().getValue(),
					(double) tela.getSpinPeso().getValue()
			);
			if (cadastrado) {
				ControladorRedirecionar.caminho(1);
			}
		} else if (e == tela.getBtnVoltar()) {
			ControladorRedirecionar.caminho(1);
		}
	}
}