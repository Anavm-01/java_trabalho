/*TRABALHO FINAL - ALGORITMO E PROGRAMAÇÃO
Autor(es):Ana Luisa Vieira Moraes e Maria Luíza Bomfim Augusto*/

void main() {
var alunos = new String[20];
var medias = new double[20];
        int totalAlunos = 0;
        int opcao = 0;

        do {
            IO.println("\n === MENU PRINCIPAL: GESTÃO DE TURMA === ");
            IO.println("1 - Cadastrar Alunos e Médias");
            IO.println("2 - Exibir Lista de Alunos e Notas");
            IO.println("3 - Buscar Aluno");
            IO.println("4 - Sair");

           opcao = Integer.parseInt(IO.readln("Escolha uma opção: "));

            switch (opcao) {
                case 1:
                    totalAlunos = cadastrarAlunos(alunos, medias);
                    ordenarDados(alunos, medias, totalAlunos);
                    break;
                case 2:
                    listarAlunos(alunos, medias, totalAlunos);
                    break;
                case 3:
                    if (totalAlunos == 0) {
                        IO.println("Nenhum aluno cadastrado.");
                    } else {
                        String nomeProcurado = IO.readln("Informe o nome do aluno: ");
                        int indice = pesquisarAluno(alunos, totalAlunos, nomeProcurado);

                        if (indice != -1) {
                            String situacao = (medias[indice] >= 7.0) ? "Aprovado" : "Reprovado";
                            IO.println("Aluno encontrado!");
                            IO.println("Nome: " + alunos[indice] +
                                       " | Média: " + medias[indice] + " | Situação: " + situacao);
                        } else {
                            IO.println("Aluno não encontrado!");
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

    int cadastrarAlunos(String[] nomes, double[] medias) {
        int quantidade;
        do {
           quantidade = Integer.parseInt(IO.readln("Quantos alunos deseja cadastrar (máximo 20): "));
           if (quantidade < 1 || quantidade > 20) {
               IO.println("Quantidade inválida!  Deve ser entre 1 e 20.");
           }
      } while (quantidade < 1 || quantidade > 20);
        

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

    media = Double.parseDouble(IO.readln("Média Final: "));

    if (media < 0 || media > 10) {
        IO.println("Média inválida! Deve estar entre 0 e 10.");
    }

} while (media <= 0 || media >= 10);
            medias[i] = media;
        }

        IO.println("\nCadastro realizado com sucesso!");
        return quantidade;
    }

    void listarAlunos(String[] nomes, double[] medias, int total) {
        if (total == 0) {
            IO.println("Nenhum aluno cadastrado.");
            return;
        }

        IO.println("\n--- LISTAGEM DE ALUNOS ---");
        IO.println("NOME\t\tMÉDIA\tSITUAÇÃO");
        for (int i = 0; i < total; i++) {
            String situacao = (medias[i] >= 7.0) ? "Aprovado" : "Reprovado";
            IO.println(nomes[i] + "\t\t" + medias[i] + "\t" + situacao);
        }
    }

    int pesquisarAluno(String[] nomes, int total, String alvo) {
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

    void ordenarDados(String[] nomes, double[] medias, int total) {
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
