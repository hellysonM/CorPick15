
package corpick15;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/* Classe principal do programa CorPick 1.5 que cria um jogo de memorização baseado em cores.
*  Essa classe tem como principal objetivo linkar a interface ao algoritmo. A classe utiliza
orientação a objetos para manipular Threads dela mesma passando como parâmentros o tempo que irá
decorrer até um botão mudar de cor, que botão é esse na interface gráfica e qual cor ele irá alterar.
Essa classe possui também os algoritmos utilizados para a geração, iteração e incrementação das variáveis 
que tem como função gerar o algoritmo de cores aleatórias infinitas.
*
*
*@VERSION 1.5
*@AUTHOR Hellyson
*/

public class CorPick15 extends Thread {
    
    long time;//VARIÁVEL QUE ARMAZENA O TEMPO DA THREAD CORRENTE(TEMPO DECORRIDO ATÉ UM BOTÃO MUDAR DE COR)
    JButton button;//VARIÁVEL QUE ARMAZENA QUAL BOTÃO ESTÁ SENDO MUDADO A COR DA THREAD CORRENTE 
    Color color;///VARIÁVEL QUE ARMAZENA QUAL COR O BOTÃO DA THREAD CORRENTE IRÁ RECEBER E MUDAR
    
    
    
    /*
    ATRIBUTOS ESTÁTICOS
    */
    public static int level = 1;//NÍVEL INICIAL 
    public static ArrayList<Integer> answer = new ArrayList();//RESPOSTA CORRETA DA ORDEM DAS CORES  
    public static ArrayList<Integer> answerUser = new ArrayList();// RESPOSTA DO USUÁRIO
    public static int counter = 0;// CONTADOR DO BLOCO DE VALIDAÇÃO DE RESULTADOS
    
    /*
    ATRIBUTOS LÓGICOS DO ALGORITMO
    */
    public static boolean start = false;//BOOLEANO QUE DETERMINA SE O JOGO ACABOU DE COMEÇAR
    public static boolean match = false;//BOOLEANO QUE DE DETERMINA SE O JOGADOR ACERTOU A ÚLTIMA RODADA
    
    
    
    /*
    Método implementado run que é executado quando se usa o método .start.
    Tem a função de alteraR a cor do Button no tempo determinado.
    */
     @Override
    public synchronized void run(){
        
        if(this.time != 1){
            
        try{
            Thread.sleep(time);
            button.setBackground(color);
            }catch (InterruptedException ex){       
                alert("Ocorreu um erro na Thread no método run",30);      
            }
     }
    }
    
    /*
    MÉTODO QUE CONFERE SE OS CLICKS DOS BOTÕES CONFEREM COM A RESPOSTA CORRETA - ESSE É O MÉTODO QUE O BOTÃO ATIVA AO SER 
    CLICADO
    */
    public  static void match(int n){
 
        if(n == answer.get(counter)){
       
            answerUser.add(n);
            counter++;
            
            
        /*
        EVENTOS QUE OCORREm QUANDO O USUÁRIO PERDE    
        */
        }else{
            
            alert("FIM DE JOGO",30);
            alert("NÍVEL : " + level + " \n" + " - TEMPO : " + Gui.time,12);
            ranking(level,Gui.time);
            
            
            System.exit(0);
                    
 
        }   
    }
    
    
    
    public static void main(String[] args) {
        
       /*A INTERFACE GRÁFICA DO MENU É CRIADA.
        
        OBS: ESSA CLASSE IRÁ CONTINUAR EM EXECUÇÃO EM BACKGROUND, MAS SEU ALGORITMO SO IRÁ PROGREDIR
        QUANDO CORPICK15.START FOR IGUAL A 'TRUE'. O BOTÃO "COMEÇAR" DO MENU FAZ ESSA FUNÇÃO.
       */
       new Menu().setVisible(true);
        
       /*
       GERAÇÃO DA RESPOSTA CORRETA DO NÍVEL 1
       */
        Random r = new Random();

        answer.add(r.nextInt(4));
        
       
       
       
       /*
        INÍCIO DO ALGORTIMO LAÇO INFINITO PARA TRATAR AS REQUISIÇÕES SEM PARADAS 
       */
       while(true){       
           
         /*
         PARTE DO ALOGORITMO QUE TRATA O FLUXO DE AUMENTO DE NÍVEIS PARTA O USUÁRIO CASO ELE ACERTE O NÍVEL ANTERIOR   
         */  
         try{
         if(answer.equals(answerUser)){ 
 
            alert("Correto!",30);

            level++;//AUMENTA O NÍVEL
            answerUser.clear();//LIMPA A RESPOSTA DO USUÁRIO
            answer.add(r.nextInt(4));//ADICIONA À RESPOSTA CORRETA MAIS 1 COR.
            
            match = true;//A RESPOSTA ENCONTRA-SE CORRETA E O PROGRAMAR IRÁ PROGREDIR NO ALGORITMO
            counter = 0;//CONTADOR DAS REPOSTAS É ZERADO 
         }
         }catch(ConcurrentModificationException e){
             
         }
          
            
        /*
        ESTA PARTE DO ALGORITMO FUNCIONA COMO UMA TRAVA PARA EVITAR QUE O PROGRAMA ENTRE EM LOOP
        INFINITO SEM ESPERAR QUE O JOGADOR ESCOLHA SUA RESPOSTA. OU SEJA, O ALGORITMO SO PROGREDI-
        RÁ SÓ E SOMENTE SE O JOGADOR ACERTAR O JOGO ANTERIOR OU FOR A PRIMEIRA RODADA.
        */      
        if(start || match){
            
            if(start){
            start = false;// O USUÁRIO A PARTIR DAQUI NÃO EXECUTA O PRIMEIRO JOGO, E ESTA VARIÁVEL NUNCA IRÁ VOLTAR SER TRUE
            }
            
            match = false;// SO VOLTARÁ A SER TRUE SE O USUÁRIO ACERTAR A RESPOSTA
            
            alert("Nível " + level,30);//MOSTRA O NÍVEL QUE O USUÁRIO ESTÁ
            
            
            
           /*
           ALGORITMO QUE REALIZADA A CRIAÇÃO DAS THREADS COM BASE NO NÍVEL E ATRIBUI OS SEUS TEMPOS DE 'SLEEP' NO CONSTRUTOR 
           */ 
           int i = 0;// TEMPO COMO ATRIBUTO DO CONSTRUTOR

                for (int j = 0; j < level; j++) {

                     i+=3000;// A  CADA PISCADA DE TECLA O TEMPO É AUMENTADO PARA A PRÓXIMA TECLA

                         switch(answer.get(j)){
                              case  0 :
                                 new CorPick15(i,Gui.bVermelho,new Color(200,0,0)).start();
                                 new CorPick15(i+500,Gui.bVermelho,new Color(255,0,0)).start();
                         break;

                             case  1 :
                                 new CorPick15(i,Gui.bVerde,new Color(0,200,0)).start();
                                 new CorPick15(i+500,Gui.bVerde,new Color(0,255,0)).start();
                         break;

                             case  2 :
                                 new CorPick15(i,Gui.bAmarelo,new Color(200,200,0)).start();
                                 new CorPick15(i+500,Gui.bAmarelo,new Color(255,255,0)).start();
                         break;

                             case  3 :
                                 new CorPick15(i,Gui.bAzul,new Color(0,0,200)).start();
                                 new CorPick15(i+500,Gui.bAzul,new Color(0,0,255)).start();
                         break;

                    }
                }
                
           new ButtonLock(i+500).start();     
           }  
        }
       
       
    }
    
    /*
    SET DA CLASSE
    */
    CorPick15(long time,JButton button,Color color){
        this.time = time;    
        this.button = button;    
        this.color = color;    
        
    } 
    
    /*
    MÉTODO QUE CRIA UM ALERTA PADRONIZADO PARA EVITAR EXCESSO DE CÓDIGO
    */
    public static void alert(String texto, int size){
        
       JLabel label = new JLabel(texto);        
       label.setFont(new Font("Dialog", Font.PLAIN, size));
       label.setHorizontalAlignment(SwingConstants.CENTER);
       label.setPreferredSize(new Dimension(300,70));
       JOptionPane.showMessageDialog(null, label,"CorPick",-1,null);   
        
    }
    
    
    /*
    MÉTODO RESPONSÁVEL POR ESCREVER O RESULTADO NO RANKING
    */
    public static void ranking(int level, String time){
        
        Path p = Paths.get("ranking.txt");
        File arq = new File("ranking.txt");        
                
                Charset utf8 = StandardCharsets.UTF_8;
                try {
                        if(arq.exists()){
                        BufferedWriter w = Files.newBufferedWriter(p,utf8,StandardOpenOption.APPEND);

                        w.write(":"+ level+":" + time);

                        w.flush();
                        w.close();
                    
                    }else{
                        BufferedWriter w = Files.newBufferedWriter(p,utf8,StandardOpenOption.CREATE);

                        w.write(":"+ level+":" + time);

                        w.flush();
                        w.close();
                    
                }
                    
                    
                } catch (IOException ex) {
                   
                }
        
        
    }
    
    
    
}
