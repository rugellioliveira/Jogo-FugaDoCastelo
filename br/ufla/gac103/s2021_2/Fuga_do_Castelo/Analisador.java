package br.ufla.gac103.s2021_2.Fuga_do_Castelo;

import br.ufla.gac103.s2021_2.baseJogo.InterfaceUsuario;

//importa a classe Scanner que permite coletar o que foi digitado pelo usuário
import java.util.Scanner;

/**
 * Esta classe eh parte da aplicacao "Fuga do Castelo".  
 * 
 * Esse analisador lê a entrada do usuario e tenta interpreta-la como um
 * comando "Adventure". Cada vez que eh chamado ele lê uma linha do terminal
 * e tenta interpretar a linha como um comando de duas palavras. Ele retorna
 * o comando como um objeto da classe Comando.
 *
 * O analisador tem um conjunto de palavras de comando conhecidas. Ele compara
 * a entrada do usuario com os comandos conhecidos, e se a entrada nao eh um
 * dos comandos conhecidos, ele retorna um objeto comando que eh marcado como
 * um comando desconhecido.
 * 
 * @author de "Fuga do Castelo"(Rugelli Oliveira)
 * @version 2022.03.25
 */

//Classe faz uso do padrão de projeto Singleton

public class Analisador 
{
    private PalavrasComando palavrasDeComando;  // guarda todas as palavras de comando validas
    
    
    private static Analisador instanciaUnica; //atributo estático para guardar instância única da classe.
    
    private InterfaceUsuario interfaceUsuarioAnalisador;

    /**
     * Cria um analisador para ler do terminal.
     */
    private Analisador(InterfaceUsuario interfaceUsuarioAnalisador) 
    {
        this.interfaceUsuarioAnalisador = interfaceUsuarioAnalisador;
        palavrasDeComando = new PalavrasComando();
    }
    
    /**
     * O método cria a instância apenas na primeira vez que é chamado. 
     * A partir daí retorna a instância que havia sido criada antes.
     */
    public static Analisador getInstanceAnalisador(InterfaceUsuario interfaceUsuarioAnalisador){
        
        if(instanciaUnica == null){
            instanciaUnica = new Analisador(interfaceUsuarioAnalisador);
        }
        
        return instanciaUnica;
    }
    
    /**
     * @return O proximo comando do usuario
     */
    public Comando pegarComando() 
    {
       
        String linha;   // guardara uma linha inteira
        String palavra1 = null;
        String palavra2 = null;
        
        
        //System.out.print("> "); // imprime o prompt

        //linha = entrada.nextLine();
        linha = interfaceUsuarioAnalisador.obterComando();
        
        
        // Tenta encontrar ate duas palavras na linha
        Scanner tokenizer = new Scanner(linha);
        
        if(tokenizer.hasNext()) {
            palavra1 = tokenizer.next();      // pega a primeira palavra
            if(tokenizer.hasNext()) {
                palavra2 = tokenizer.next();      // pega a segunda palavra
                // obs: nos simplesmente ignoramos o resto da linha.
            }
        }
        

        // Agora verifica se esta palavra eh conhecida. Se for, cria um
        // com ela. Se nao, cria um comando "null" (para comando desconhecido)
        if(palavrasDeComando.ehComando(palavra1)) {
            return new Comando(palavra1, palavra2);
        }
        else {
            return new Comando(null, palavra2); 
        }
    }
    
    //método para retornar comandos analisados
    public String getComandos(){
        return PalavrasComando.getComandos();
    }
}
