
package corpick15;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Ranking {

    
    public  static String texto = "";
    
    public static void RankingList() throws IOException{


             /*
            ALGORITMO QUE GERA OS RANKS LISTADOS EM ORDEM E OS COLOCA EM UM STRING 'RANKING.TEXTO';
            OBS """""""  ALGORITMO NÃO TRATA O RANKEAMENTO CORRETO QUANDO OCORREM CASOS DE NIVEIS IGUAIS E TEMPOS DIFERENTES. FICARÁ NA FRENTE QUEM FOR O PRIMEIRO
            */
            Charset utf8 = StandardCharsets.UTF_8;
            Path p = Paths.get("ranking.txt");
            BufferedReader  br = Files.newBufferedReader(p);
            
            String line = null;
            int[] nivel = new int[1000];
            String[]tempo = new String[1000];
            String[] data = null;
            
            while((line = br.readLine()) != null){
             data = line.split(":");

            
            }
            
            int m = 0;
            
            for (int i = 1; i < data.length; i+=2) {
            
                
              nivel[m] = Integer.parseInt(data[i]);System.out.println("nivel " + m + " :" + nivel[m] );
              m++;
                
                
            }
            m = 0;
            for (int i = 2; i < data.length; i+=2) {
            
             tempo[m] = data[i];System.out.println("tempo " + m+ " :" + tempo[m] );
              m++;
                
                
            }
               
            int i, j, x;
            String x2;
        
            for (i = 1; i < nivel.length; i++) {
		j = i;
		while (j > 0 && nivel[j - 1] < nivel[j]) {
			x = nivel[j];
                        
			nivel[j] = nivel[j - 1];
			nivel[j - 1] = x;
                        
                        x2 = tempo[j];
                        
                        tempo[j] = tempo[j-1];
                        tempo[j-1] = x2;
    
			j--;
		}
	}
        
             for (int k = 0; k < nivel.length; k++) {
            if(nivel[k] != 0){
                
            texto+=k+1+"º - "+nivel[k] + " - " + tempo[k] + "\n";
            }
            
           
            
    }

  
}
}
