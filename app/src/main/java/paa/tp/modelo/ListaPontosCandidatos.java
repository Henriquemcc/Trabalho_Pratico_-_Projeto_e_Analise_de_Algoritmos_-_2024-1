package paa.tp.modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Classe que armazena os pontos candidatos.
 */
public class ListaPontosCandidatos {

    /**
     * Array list dos pontos candidatos.
     */
    private ArrayList<PontoCandidato> pontoCandidatos = new ArrayList<>();

    /**
     * Abre arquivo contendo os pontos candidatos.
     * @param arquivo Objeto Arquivo a ser lido.
     * @throws IOException Exceção lançada caso ocorra erro de IO.
     * @throws ClassNotFoundException Exceção lançada caso a classe lida não seja encontrada.
     */
    public void abrirArquivo(final File arquivo) throws IOException, ClassNotFoundException {
        try(final FileInputStream fileInputStream = new FileInputStream(arquivo)) {
            try(final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                final Object object = objectInputStream.readObject();
                    if (!(object instanceof ArrayList))
                        throw new ClassCastException("Arquivo Incompatível");
                    final ArrayList<?> objetos = (ArrayList<?>) object;
                    final ArrayList<PontoCandidato> pontoCandidatosLidos = new ArrayList<>();
                    for(Object elemento: objetos)
                    {
                        if (!(elemento instanceof PontoCandidato))
                            throw new ClassCastException("Arquivo Incompatível");
                        pontoCandidatosLidos.add((PontoCandidato) elemento);
                    }
                    pontoCandidatos = pontoCandidatosLidos;

            }
        }
    }

    /**
     * Salva arquivo contendo pontos candidatos.
     * @param arquivo Objeto Arquivo a ser salvo.
     * @throws IOException Exceção lançada caso ocorra erro de IO.
     */
    public void salvarArquivo(final File arquivo) throws IOException {
        try(final FileOutputStream fileOutputStream = new FileOutputStream(arquivo)) {
            try(final ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(pontoCandidatos);
            }
        }
    }

    /**
     * Gera aleatóriamente um arquivo de dados.
     * @param quantidadeFranquias Quantidade de franquias.
     * @param quantidadeLojasPorFranquia Quantidade de lojas por franquia.
     * @param random Objeto Random para gerar aleatóriamente os dados dos pontos candidatos.
     */
    public void gerarArquivo(final int quantidadeFranquias, final int quantidadeLojasPorFranquia, final Random random)
    {
        final ArrayList<PontoCandidato> pontoCandidatosGerados = new ArrayList<>(quantidadeFranquias * quantidadeLojasPorFranquia);
        for (int franquia = 0; franquia < quantidadeFranquias; franquia++)
        {
            for (int loja = 0; loja < quantidadeLojasPorFranquia; loja++)
            {
                pontoCandidatosGerados.add(new PontoCandidato(franquia, random.nextInt(), random.nextInt(), random.nextInt()));
            }
        }
        pontoCandidatos = pontoCandidatosGerados;
    }
}
