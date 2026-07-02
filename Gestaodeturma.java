import java.util.Scanner; 
 
class IO { 
    private static Scanner scanner = new Scanner(System.in); 
 
    public static void println(String s) { 
        System.out.println(s); 
    } 
 
    public static void print(String s) { 
        System.out.print(s); 
    } 
 
    public static String readln(String prompt) { 
        System.out.print(prompt); 
        return scanner.nextLine(); 
    } 
} 
 
public class GestaoDeTurma { 
 
    public static void main(String[] args) { 
        String[] nomes = new String[20]; 
        double[] medias = new double[20]; 
        int totalAlunos = 0; 
        int opcao = 0; 
 
        do { 
            IO.println("\n === MENU PRINCIPAL: GESTÃO DE TURMA === "); 
            IO.println("1 - Cadastrar Alunos e Médias"); 
            IO.println("2 - Exibir Lista de Alunos e Notas"); 
            IO.println("3 - Buscar Aluno"); 
            IO.println("4 - Sair"); 
             
            try { 
                opcao = Integer.parseInt(IO.readln("Escolha uma opção: ")); 
            } catch (NumberFormatException e) { 
                IO.println("Opção inválida! Digite um número entre 1 e 4."); 
                continue; 
            } 
 
            switch (opcao) { 
                case 1: 
                    totalAlunos = cadastrarAlunos(nomes, medias); 
                    ordenarDados(nomes, medias, totalAlunos); 
                    break; 
                case 2: 
                    listarAlunos(nomes, medias, totalAlunos); 
                    break; 
                case 3: 
                    if (totalAlunos == 0) { 
                        IO.println("Nenhum aluno cadastrado."); 
                    } else { 
                        String nomeProcurado = IO.readln("Informe o nome do aluno: "); 
                        int indice = pesquisarAluno(nomes, totalAlunos, nomeProcurado); 
                         
                        if (indice != -1) { 
                            String situacao = (medias[indice] >= 7.0) ? "Aprovado" : "Reprovado"; 
                            IO.println("Aluno encontrado!"); 
                            IO.println("Nome: " + nomes[indice] + 
                            " | Média: " + medias[indice] + " | Situação: " + situacao); 
                        } else { 
                            IO.println(" Aluno não encontrado! "); 
                        } 
                    } 
                    break; 
                case 4: 
                    IO.println("Encerrando o programa..."); 
                    break; 
                default: 
                    IO.println("Opção inválida!"); 
                    break; 
            } 
        } while (opcao != 4); 
    } 
 
    public static int cadastrarAlunos(String[] nomes, double[] medias) { 
        int quantidade; 
        do { 
            try { 
                quantidade = Integer.parseInt(IO.readln("Quantos alunos deseja cadastrar? (máximo 20)  ")); 
                if (quantidade < 1 || quantidade > 20) { 
                    IO.println("Quantidade inválida! Deve ser entre 1 e 20."); 
                } else { 
                    break; 
                } 
            } catch (NumberFormatException e) { 
                IO.println("Por favor, insira um número válido."); 
            } 
        } while (true); 
 
        for (int i = 0; i < quantidade; i++) { 
            IO.println("\n--- Cadastro do Aluno " + (i + 1) + " ---"); 
             
            String nome; 
            boolean duplicado; 
            do { 
                duplicado = false; 
                nome = IO.readln("Nome: "); 
                for (int j = 0; j < i; j++) { 
                    if (nomes[j].equalsIgnoreCase(nome)) { 
                        IO.println("Erro: Este nome já foi cadastrado. Insira um nome diferente."); 
                        duplicado = true; 
                        break; 
                    } 
                } 
            } while (duplicado); 
            nomes[i] = nome; 
 
            double media; 
            do { 
                try { 
                    media = Double.parseDouble(IO.readln("Média Final: ")); 
                    if (media < 0.0 || media > 10.0) { 
                        IO.println("Média inválida! Deve estar entre 0.0 e 10.0."); 
                    } else { 
                        break; 
                    } 
                } catch (NumberFormatException e) { 
                    IO.println("Por favor, insira um valor numérico para a média."); 
                } 
            } while (true); 
            medias[i] = media; 
        } 
         
        IO.println("\nCadastro realizado com sucesso!"); 
        return quantidade; 
    } 
 
    public static void listarAlunos(String[] nomes, double[] medias, int total) { 
        if (total == 0) { 
            IO.println("Nenhum aluno cadastrado para exibir."); 
            return; 
        } 
 
        IO.println("\n--- LISTAGEM DE ALUNOS ---"); 
        IO.println("NOME\t\tMÉDIA\tSITUAÇÃO"); 
        for (int i = 0; i < total; i++) { 
            String situacao = (medias[i] >= 7.0) ? "Aprovado" : "Reprovado"; 
            IO.println(nomes[i] + "\t\t" + medias[i] + "\t" + situacao); 
        } 
    } 
 
    public static int pesquisarAluno(String[] nomes, int total, String alvo) { 
        int inicio = 0; 
        int fim = total - 1; 
 
        while (inicio <= fim) { 
            int meio = (inicio + fim) / 2; 
            int comparacao = nomes[meio].compareToIgnoreCase(alvo); 
 
            if (comparacao == 0) { 
                return meio; 
            } else if (comparacao < 0) { 
                inicio = meio + 1; 
            } else { 
                fim = meio - 1; 
            } 
        } 
        return -1; 
    } 
 
    public static void ordenarDados(String[] nomes, double[] medias, int total) { 
        for (int i = 0; i < total - 1; i++) { 
            for (int j = 0; j < total - 1 - i; j++) { 
                if (nomes[j].compareToIgnoreCase(nomes[j + 1]) > 0) { 
                   
                    String tempNome = nomes[j]; 
                    nomes[j] = nomes[j + 1]; 
                    nomes[j + 1] = tempNome; 
 
                    double tempMedia = medias[j]; 
                    medias[j] = medias[j + 1]; 
                    medias[j + 1] = tempMedia; 
                } 
            } 
        } 
    } 
}
