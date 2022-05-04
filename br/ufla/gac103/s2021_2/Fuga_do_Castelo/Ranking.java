package br.ufla.gac103.s2021_2.Fuga_do_Castelo;

//importa classe FileWriter que permite salvar arquivos de texto
import java.io.FileWriter;


//importa classe BufferedReader e FileReader que permitem recuperar dados de um arquivo de texto
import java.io.BufferedReader;
import java.io.FileReader;

//importa a classe ArrayList
import java.util.ArrayList;

//importa a classe Comparator que pode ser utilizada para promover a ordenação de ArrayList
import java.util.Comparator;

import br.ufla.gac103.s2021_2.baseJogo.InterfaceUsuario;

/**
 * Esta classe faz parte da aplicação "Fuga do Castelo"
 * Ela representa o ranking do jogo, ou seja, a classificação dos usuários de acordo com o tempo que cada um levou para vencer o jogo.
 * 
 * @author Rugelli Oliveira
 * @version 2022.04.10
 */

//Classe faz uso do padrão de projeto Singleton

public class Ranking
{
    //atributos
    private ArrayList<Usuario> usuarios;
    private long inicioJogo;
    private long fimJogo;
    private InterfaceUsuario interfaceUsuario;
    
    private static Ranking instanciaUnica;
    
    /**
     * Construtor para objetos da classe Ranking
     */
    private Ranking()
    {
       usuarios = new ArrayList <Usuario>();
    }
    
    /**
     * O método cria a instância apenas na primeira vez que é chamado. 
     * A partir daí retorna a instância que havia sido criada antes.
     */
    public static Ranking getInstanceRanking(){
        
        if(instanciaUnica == null){
            instanciaUnica = new Ranking();
        }
        
        return instanciaUnica;
    }
    
    /**
     * Método para retornar a quantidade de milissegundos que se passou desde 1 de janeiro de 1970, à meia-noite, em UTC (instante zero)
     * Método para ser chamado ao iniciar o jogo
     */
    
    public long iniciarContagem(){
        inicioJogo = System.currentTimeMillis();
        return inicioJogo;
    }
    
    /**
     * Método para ser chamado quando finalizar o jogo, somente na situação em que o usuário foi vencedor
     */
    public long tempoDeJogo(){
        fimJogo = System.currentTimeMillis();
        return fimJogo;
    }
    
    /**
     * Método para incluir (salvar) usuario no ranking
     */
    public void incluirNoRanking(Usuario usuario){
      
     //seta o tempo que o usuario levou para vencer o jogo (em segundos).
      usuario.setTempoJogo((fimJogo-inicioJogo)/1000);
      
      //salva dados do usuario no arquivo de texto (.txt)
      try {
         FileWriter arq = new FileWriter("ranking.txt", true);
             
        arq.write(usuario.getNomeUsuario() + ";" + usuario.getTempoJogo() + "\n");
        
        arq.close();
      }
      catch (Exception e) {
        e.printStackTrace();
      }
        
        
    }
    
    /**
     * Método para carregar dados salvos no arquivo texto
     */
    public ArrayList<Usuario> carregarRanking(){
        
       //Leitura do arquivo txt 
        try {
              BufferedReader arq = new BufferedReader(new FileReader("ranking.txt"));
              String linha = arq.readLine();
        
          //realiza a leitura do arquivo texto linha a linha até encontrar uma sem dados
          while (linha != null) {
            String[] termos = linha.split(";");
            
            //Criamos o usuario com os dados lidos da linha do arquivo.
            Usuario usuario = new Usuario(termos[0]); //lê dado antes da quebra de string
            usuario.setTempoJogo(Long.parseLong(termos[1])); //lê dado após quebra de string
            
            usuarios.add(usuario);
            
            
            linha = arq.readLine();
            System.out.println();
          }
          
        }
        catch (Exception e) {
           e.printStackTrace();
        }
       
       
       // Ordena os usuarios de acordo com o tempo que levaram para vencer o jogo
        Comparator<Usuario> comparador = new Comparator<>(){
            @Override
            public int compare(Usuario usuario1, Usuario usuario2){
                return (int)usuario1.getTempoJogo() - (int)usuario2.getTempoJogo();
            }
        };
        usuarios.sort(comparador);
    
        return usuarios;
    
    }
    
}
