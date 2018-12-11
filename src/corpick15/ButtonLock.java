
package corpick15;


/*
ESSA CLASSE TEM A FUNÇÃO DE EVITAR QUE O USUÁRIO CLIQUE NO BOTÃO ENQUANTO O JOGO MOSTRA A ELE QUAL DEVE SER A SEQUENCIA SER INSERIDA
*/

public class ButtonLock extends Thread {
    
  long tempo;  
    ButtonLock(long tempo){
     this.tempo = tempo;
    }
  @Override
 public void run(){
     
      try {
            
          Gui.bVermelho.setEnabled(false);
          Gui.bVerde.setEnabled(false);
          Gui.bAmarelo.setEnabled(false);
          Gui.bAzul.setEnabled(false);     
          
          Thread.sleep(tempo);
          
          CorPick15.alert("SUA VEZ" , 24);
          
          Gui.bVermelho.setEnabled(true);
          Gui.bVerde.setEnabled(true);
          Gui.bAmarelo.setEnabled(true);
          Gui.bAzul.setEnabled(true);  
     
          
      } catch (InterruptedException ex) {
         
      }
 }   
    
 
  
  
}
