package br.ufla.gac103.s2021_2.Fuga_do_Castelo;


import br.ufla.gac103.s2021_2.baseJogo.InterfaceUsuario;
import br.ufla.gac103.s2021_2.baseJogo.EntidadeGrafica;

//importa a classe Scanner que permite utilizar objeto para capturar a entrada de dados do usuário
import java.util.Scanner;

/**
 * Escreva uma descrição da classe Terminal aqui.
 * 
 * @author Rugelli Oliveira
 * @version 2022.04.20
 */


public class Terminal implements InterfaceUsuario
{
    private Scanner entrada;
    
    public Terminal(){
        entrada = new Scanner (System.in); //Cria objeto da classe Scanner
    }
    
    
    @Override
    public void exibirMensagem(String mensagem){
        
        System.out.println(mensagem);
        
    }
    
    /**
     * Acrescenta informação à uma mensagem anterior
     * 
     * @param mensagem Informação a ser acrecentada na mensagem anterior
     */
    @Override
    public void continuarMensagem(String mensagem){
        
        System.out.println(mensagem);
        
    }
    
    /**
     * Obtém um comando do jogador. Deve ser chamado apenas na classe Analisador.
     */
    @Override
    public String obterComando(){
        
        return  entrada.nextLine();
        
    }
    
    /**
     * Obtém uma informação do usuário como String. 
     * 
     * Obs.: não deve ser usado para comandos. No caso de comandos use 'obterComando'.
     * 
     * @param instrucao Mensagem de instrução para o usuário (diz qual informação está sendo solicitada)
     */
    @Override
    public String obterInformacao(String instrucao){
        
        System.out.println(instrucao);
        
        return entrada.nextLine();
        
    }
    
    /**
     * Informa a Interface do Usuário que o jogador mudou de ambiente.
     * 
     * @param ambiente Objeto do novo ambiente atual.
     */
    @Override
    public void ambienteAtualMudou(EntidadeGrafica ambiente){
        
    }
    
    /**
     * Informa a Interface do Usuário que o jogador pegou um item (ou similar).
     * 
     * @param item Objeto do item que o usuário pegou.
     */
    @Override
    public void jogadorPegouItem(EntidadeGrafica item){
        
    }
    
    /**
     * Informa a Interface do Usuário que o jogador não tem mais um item (ou similar).
     * 
     * @param item Objeto do item que o usuário não tem mais.
     */
    @Override
    public void jogadorDescartouItem(EntidadeGrafica item){
        
    }
}
