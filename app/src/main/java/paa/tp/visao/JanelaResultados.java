package paa.tp.visao;

import paa.tp.modelo.PontoCandidato;
import paa.tp.modelo.algoritmo.otimizacao.Solucao;

import javax.swing.*;
import java.util.List;

/**
 * Janel que mostra os resultados da execução dos algoritmos.
 */
public class JanelaResultados extends JFrame {

    /**
     * Solução do problema a ser exibida.
     */
    private final Solucao solucao;

    /**
     * Tabela utilizada para mostrar os resultados.
     */
    private final JTable jTable;

    /**
     * Scroll Pane que conterá a tabela.
     */
    private final JScrollPane jScrollPane;

    /**
     * Constrói uma nova instância da classe JanelaResultados.
     * @param solucao Solução a ser exibida
     */
    public JanelaResultados(final Solucao solucao) {
        super("Resultado");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.solucao = solucao;

        // Criando a tabela
        final String[] nomesColunas = {"Número franquia", "Coordenada X", "Coordenada Y", "Custo"};
        final List<PontoCandidato> pontosCandidatosEscolhidos = solucao.getPontosCandidatosEscolhidos();
        final String[][] dados = new String[pontosCandidatosEscolhidos.size()][4];
        for (int i = 0; i < pontosCandidatosEscolhidos.size(); i++)
        {
            dados[i][0] = String.valueOf(pontosCandidatosEscolhidos.get(i).getNumeroFranquia());
            dados[i][1] = String.valueOf(pontosCandidatosEscolhidos.get(i).getCoordenadaX());
            dados[i][2] = String.valueOf(pontosCandidatosEscolhidos.get(i).getCoordenadaY());
            dados[i][3] = String.valueOf(pontosCandidatosEscolhidos.get(i).getCustoInstalacao());
        }
        jTable = new JTable(dados, nomesColunas);
        jScrollPane = new JScrollPane(jTable);
        add(jScrollPane);
    }
}
