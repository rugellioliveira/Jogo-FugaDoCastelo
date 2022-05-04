package br.ufla.gac103.s2021_2.Fuga_do_Castelo;

/**
 * Esta classe eh parte da aplicacao "Fuga do Castelo". 
 * 
 * Essa classe guarda uma enumeracao de todos os comandos conhecidos do
 * jogo. Ela eh usada no reconhecimento de comandos como eles sao digitados.
 *
 * @author de "Fuga do Castelo"(Rugelli Oliveira)
 * @version 2022.03.01
 */

public class PalavrasComando
{
    // um vetor constante que guarda todas as palavras de comandos validas
    private static final String[] comandosValidos = {
        "ir", "sair", "ajuda", "observar", "pegar", "usar"
    };
    
    //método estático retorna uma String contendo os comandos válidos no jogo
    public static String getComandos(){
        String comandos = " ";
        for(String comando : comandosValidos){
            comandos += comando + " ";
        }
        return comandos;
    }

    /**
     * Construtor - inicializa as palavras de comando.
     */
    public PalavrasComando()
    {
        // nada a fazer no momento...
    }

    /**
     * Verifica se uma dada String eh uma palavra de comando valida. 
     * @return true se a string dada eh um comando valido,
     * false se nao eh.
     */
    public boolean ehComando(String umaString)
    {
        for(int i = 0; i < comandosValidos.length; i++) {
            if(comandosValidos[i].equals(umaString))
                return true;
        }
        // se chegamos aqui, a string nao foi encontrada nos comandos.
        return false;
    }
}
