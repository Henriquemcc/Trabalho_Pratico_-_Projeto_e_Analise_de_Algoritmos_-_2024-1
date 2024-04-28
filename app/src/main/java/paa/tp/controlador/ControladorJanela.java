package paa.tp.controlador;

import paa.tp.modelo.ListaPontosCandidatos;
import paa.tp.modelo.algoritmo.otimizacao.ForcaBruta;
import paa.tp.visao.*;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;

/**
 * Controlador de janela.
 */
public class ControladorJanela {

    /**
     * Janela principal.
     */
    private final JanelaPrincipal janelaPrincipal = new JanelaPrincipal(this);

    /**
     * Lista dos pontos candidatos.
     */
    private final ListaPontosCandidatos listaPontosCandidatos = new ListaPontosCandidatos();

    /**
     * Distância mínima entre duas filiais.
     */
    private Double distanciaMinima = null;

    /**
     * Constrói uma nova instância de ControladorJanela.
     */
    public ControladorJanela() {
        janelaPrincipal.setVisible(true);
        janelaPrincipal.setSize(FuncoesGraficas.obterResolucaoTela());
        janelaPrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Realiza o procedimento para abrir arquivo.
     */
    public void abrirArquivo() {
        final JanelaArquivo janelaArquivo = new JanelaArquivo();
        final String caminho = janelaArquivo.abrir(janelaPrincipal);
        try {
            listaPontosCandidatos.abrirArquivo(new File(caminho));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(janelaPrincipal, String.format("IOException: %s", e), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Realiza o procedimento para salvar arquivo.
     */
    public void salvarArquivo() {
        final JanelaArquivo janelaArquivo = new JanelaArquivo();
        final String caminho = janelaArquivo.salvar(janelaPrincipal);
        try {
            listaPontosCandidatos.salvarArquivo(new File(caminho));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(janelaPrincipal, String.format("IOException: %s", e), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Realiza o procedimento para gerar dados aleatoriamente, exibindo a janela.
     */
    public void gerarDadosAleatorios() {
        final JanelaGerarDados janelaGerarDados = new JanelaGerarDados(this);
        janelaGerarDados.setVisible(true);
        janelaGerarDados.setSize(280, 280);
    }

    /**
     * Realiza o procedimento para gerar dados aleatoriamente, depois de exibir a janela.
     * @param quantidadeFranquias Quantidade de franquias.
     * @param quantidadeLojasPorFranquia Quantidade de lojas por franquia.
     */
    public void gerarDadosAleatorios(final int quantidadeFranquias, final int quantidadeLojasPorFranquia) {
        listaPontosCandidatos.gerarArquivo(quantidadeFranquias, quantidadeLojasPorFranquia, new SecureRandom());
    }

    /**
     * Realiza o procedimento para obter a distância mínima entre duas filiais.
     */
    public void obterDistanciaMinima() {
        final JanelaDistanciaMinima janelaDistanciaMinima = new JanelaDistanciaMinima(this);
        janelaDistanciaMinima.setVisible(true);
        janelaDistanciaMinima.setSize(280, 280);
    }

    /**
     * Executa o algoritmo da força bruta.
     */
    public void executarForcaBruta() {
        if (distanciaMinima == null)
            obterDistanciaMinima();
        else {
            final ForcaBruta forcaBruta = new ForcaBruta(listaPontosCandidatos.getPontosCandidatos(), distanciaMinima);
            forcaBruta.executar();
            System.out.println("Melhor solução = " + forcaBruta.getMelhorSolucao());
            // Falta implementar a parte gráfica
        }
    }

    /**
     * Altera o valor da variável distância mínima.
     * @param distanciaMinima Novo valor para a variável distância mínima.
     */
    public void setDistanciaMinima(Double distanciaMinima) {
        this.distanciaMinima = distanciaMinima;
    }
}
