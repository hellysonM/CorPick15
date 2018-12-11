
package corpick15;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.swing.JButton;
import javax.swing.JFrame;

/*
*CLASSE QUE FORNECE A INTERFACE GRÁFICA DA TELA PRINCIPAL DO JOGO
*
*
**/


public class Gui extends JFrame implements Runnable   {
    
    public static JButton bVerde;
    public static JButton bAzul;
    public static JButton bAmarelo;
    public static JButton bLaranja;
    public static JButton bVermelho;
    
    public static String time;

    
   public Gui(){

       GridLayout layout = new GridLayout(2,2,10,10);//Cria um Layout de grade com 2 linhas e 2 colunas com disposição de 10 por 10
       
       super.setSize(800,600);// Tamanho inicial da janela
       super.setLayout(layout);// Adiciona o Layout criado ao JFrame 
       super.setLocationRelativeTo(null); // Determina que a janela ao ser aberta centralize-se na tela
       super.setDefaultCloseOperation(3);// Determina que o programa encerre ao fechar a janela do JFrame
       
       /*
       Criação dos Botões
       */
       bVerde = new JButton();
       bAzul = new JButton();
       bAmarelo = new JButton();  
       bVermelho = new JButton();

       /*
       Tira a borda dos Botões
       */
       bVerde.setBorderPainted(false);
       bAzul.setBorderPainted(false);
       bAmarelo.setBorderPainted(false);
       bVermelho.setBorderPainted(false);
       
       /*
       Determina a cor de fundo dos Botões 
       */
            
       bVerde.setBackground(new Color(0,255,0));
       bAmarelo.setBackground(new Color(255,255,0));
       bAzul.setBackground(new Color(0,0,255));
       bVermelho.setBackground(new Color(255,0,0));
       
       
       
       /*
       TRATA OS EVENTOS DE CLIQUE NOS BOTÕES
       */
       bVermelho.addActionListener(new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent e) {
       
          CorPick15.match(0);
          
           
       }});
       
       
       
       bVerde.addActionListener(new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent e) {
           
           CorPick15.match(1);
           
       }});
       
       bAmarelo.addActionListener(new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent e) {
           
           CorPick15.match(2);
           
       }});
       
       bAzul.addActionListener(new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent e) {
           
           CorPick15.match(3);
           
       }});

       
       /*
       ADICIONA OS BOTÕES AO JFRAME
       */
       super.add(bVermelho);
       super.add(bVerde);
       super.add(bAmarelo);
       super.add(bAzul);

       /*
       Torna o JFrame visível 
       */
       super.setVisible(true);
  
   }

   
     /*
     Thread da GUI QUE SERVE PARA MUDAR DINAMICAMENTE O TÍTULO DA JANELA DO JFRAME E CALCULAR O TEMPO
   
   */
    @Override
    public void run() {
        
        int tempo = 0;
        int segundos = 0;
        int minutos = 0;
   
        try {
    
            while(true){  
                
            tempo++;
            segundos++;
            
                 if(tempo == 60){
                     tempo = 0;
                     segundos = 0;
                     minutos++;
                 }
       
            super.setTitle("NÍVEL : " + CorPick15.level + " - TEMPO : " + minutos + "m" + " " + segundos + "s:" );
            time = (minutos + "m" + segundos+ "s");
            Thread.sleep(1000);
            
            }
            
        } catch (InterruptedException ex) {
            
        }
    }
    
    
    
    
    
    
    
    
    
    
    
}
