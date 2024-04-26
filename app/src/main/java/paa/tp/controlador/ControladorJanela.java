package paa.tp.controlador;

import paa.tp.modelo.ListaPontosCandidatos;
import paa.tp.visao.FuncoesGraficas;
import paa.tp.visao.JanelaArquivo;
import paa.tp.visao.JanelaPrincipal;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * Controlador de janela.
 */
public class ControladorJanela {

    /**
     * Janela principal.
     */
    private final JanelaPrincipal janelaPrincipal = new JanelaPrincipal(this);

    private final ListaPontosCandidatos listaPontosCandidatos = new ListaPontosCandidatos();

    /**
     * Constrói uma nova instância de ControladorJanela.
     */
    public ControladorJanela() {
        janelaPrincipal.setVisible(true);
        janelaPrincipal.setSize(FuncoesGraficas.obterResolucaoTela());
        janelaPrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void abrirArquivo() {
        final JanelaArquivo janelaArquivo = new JanelaArquivo();
        final String caminho = janelaArquivo.abrir(janelaPrincipal);
        try {
            listaPontosCandidatos.abrirArquivo(new File(caminho));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(janelaPrincipal, String.format("IOException: %s", e), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(janelaPrincipal, String.format("ClassNotFoundException: %s", e), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
