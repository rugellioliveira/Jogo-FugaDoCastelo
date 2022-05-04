package br.ufla.gac103.s2021_2.baseJogo;


/**
 * Define uma interface genérica de Interface com o Usuário.
 * 
 * A ideia é que classes que implementem essa interface definam como o usuário interage com
 * o jogo. Pode existir, por exemplo, uma classe que implemente uma janela gráfica e uma
 * classe que implemente interação via terminal.
 * 
 * @author Julio César Alves
 * @version 2022-03-22
 */
public interface InterfaceUsuario
{
    /**
     * Exibe uma nova mensagem para o jogador
     * 
     * @param mensagem Mensagem a ser exibida para o usuário.
     */
    public void exibirMensagem(String mensagem);
    
    /**
     * Acrescenta informação à uma mensagem anterior
     * 
     * @param mensagem Informação a ser acrecentada na mensagem anterior
     */
    public void continuarMensagem(String mensagem);    
    
    /**
     * Obtém um comando do jogador. Deve ser chamado apenas na classe Analisador.
     */
    String obterComando();
    
    /**
     * Obtém uma informação do usuário como String. 
     * 
     * Obs.: não deve ser usado para comandos. No caso de comandos use 'obterComando'.
     * 
     * @param instrucao Mensagem de instrução para o usuário (diz qual informação está sendo solicitada)
     */
    String obterInformacao(String instrucao);
    
    /**
     * Informa a Interface do Usuário que o jogador mudou de ambiente.
     * 
     * @param ambiente Objeto do novo ambiente atual.
     */
    public void ambienteAtualMudou(EntidadeGrafica ambiente);
    
    /**
     * Informa a Interface do Usuário que o jogador pegou um item (ou similar).
     * 
     * @param item Objeto do item que o usuário pegou.
     */
    public void jogadorPegouItem(EntidadeGrafica item);
    
    /**
     * Informa a Interface do Usuário que o jogador não tem mais um item (ou similar).
     * 
     * @param item Objeto do item que o usuário não tem mais.
     */
    public void jogadorDescartouItem(EntidadeGrafica item);
}
