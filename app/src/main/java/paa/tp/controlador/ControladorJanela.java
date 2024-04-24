package paa.tp.controlador;

import paa.tp.visao.FuncoesGraficas;
import paa.tp.visao.JanelaPrincipal;

import javax.swing.*;

/**
 * Controlador de janela.
 */
public class ControladorJanela {

    /**
     * Janela principal.
     */
    private final JanelaPrincipal janelaPrincipal = new JanelaPrincipal();

    /**
     * Constrói uma nova instância de ControladorJanela.
     */
    public ControladorJanela() {
        janelaPrincipal.setVisible(true);
        janelaPrincipal.setSize(FuncoesGraficas.obterResolucaoTela());
        janelaPrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
