package tsp;
// Programa em Java para gerar 3 grafos não direcionados randomicos
// com 15 nos para um número dado de arestas
 
// Importando bibliotecas
import java.util.Random;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
 
public class GeraGrafo {
    // Funcao para gerar o grafo randomico
    public static void geraGrafo(String arq, int n_aresta, int n_grafo, int n_no, int max_peso) {
        // String de texto que sera utilizada para gravar no arquivo
        String grafo = "";

        int k = 0;

        // Cria um numero de grafos passado como parametro
        for(k = 1; k <= n_grafo; k++) {
        
            int i = 0, j = 0, cont = 0;
    
            int[][] aresta = new int[n_aresta][2];
            Random rand = new Random();

            int[][] peso = new int[n_aresta][2];
            Random randP = new Random();

            // Cria um numero de arestas passado como parametro entre dois nos
            while (i < n_aresta) {
                // Usamos rand para pegar um valor randomico no range
                // de 1 ate o numero de nos passado como parametro
                // 0 seria a 'origem' e 1 o 'fim'
                aresta[i][0] = rand.nextInt(n_no) + 1;
                aresta[i][1] = rand.nextInt(n_no) + 1;
    
                // Usamos randP para pegar um valor randomico no range
                // de 1 ate o numero max de peso passado como parametro
                peso[i][0] = randP.nextInt(max_peso) + 1;
                peso[i][1] = peso[i][0];
                
                i++;
            }
    
            System.out.println("\nGrafo "+ k + ":\n");
    
            // Escreve no file o grafo
            // Percorre os nos e verifica se existe uma aresta neles
            for (i = 0; i < n_no; i++) {
                cont = 0;
    
                System.out.print("No " + (i + 1) + " -> { ");
    
                // Cada no tera os nos que formam suas arestas separados por ; 
                for (j = 0; j < n_aresta; j++) {
                    if (aresta[j][0] == i + 1) {
                        System.out.print(aresta[j][1] + ":" + peso[j][1] + " ");
                        grafo = grafo+aresta[j][1] + ":" + peso[j][1] + ";";
                        cont++;
                    }
    
                    else if (aresta[j][1] == i + 1) {
                        System.out.print(aresta[j][0] + ":" + peso[j][0] + " ");
                        grafo = grafo+aresta[j][0] + ":" + peso[j][0] + ";";
                        cont++;
                    }
    
                    // Printa "No Isolado" para um no
                    // que tenha grau 0.
                    else if (j == n_aresta - 1 && cont == 0) {
                        System.out.print("No Isolado ");
                        // Salvamos no txt os nos isolados como 0
                        grafo = grafo + "0;";
                    }
                }
                System.out.print("}\n");
                // Cada no eh separado por um espaco no txt
                grafo = grafo+" ";
            }
            // Cada grafo eh separado por uma msg de FIM GRAFO e uma quebra de linha
            System.out.println("________________");
            grafo = grafo+"FIM GRAFO\r\n";
        }
        // Salva os grafos gerados no arquivo txt
        salvaGrafo(arq, grafo);
    }

    public static boolean salvaGrafo(String nome_arquivo, String grafo) {
        try{
            FileWriter arq = new FileWriter(nome_arquivo);
            PrintWriter gravaArq = new PrintWriter(arq);
            gravaArq.print(grafo);
            gravaArq.close();

            //System.out.println("\nArquivo gerado com sucesso.");
            return true;
        }catch(IOException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
 
    public static void main(String args[]) throws Exception
    {
        // Nome do arquivo que sera gerado
        String arq = "grafos_gerados.txt";

        // N aleatorio de arestas entre 1 e 10
        /*
        int max_arestas = 10;
        Random randA = new Random();
        int n_aresta = randA.nextInt(max_arestas) + 1;*/

        // N de arestas, grafos, nos e peso maximo
        int n_aresta = 5;
        int n_grafo = 3;
        int n_no = 15;
        int max_peso = 10;

        System.out.println("*****GERADOR DE GRAFOS NAO DIRECIONAIS ALEATORIOS*****");
        System.out.println("\nNumero de arestas: " + n_aresta);
        System.out.println("Numero de grafos: " + n_grafo);
        System.out.println("Numero de nos: " + n_no);
        System.out.println("________________");
 
        // Gerando grafos nao direcionados aleatorios
        geraGrafo(arq, n_aresta, n_grafo, n_no, max_peso);
    }
}