import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class App {

    public static void main(String[] args) {
        Integer opcao = 0;
        Scanner leitor = new Scanner(System.in);
        Scanner leitorNl = new Scanner(System.in);
        System.out.println("Bem vindo ao CPA");

       // ListaObj<Paciente> usuarioListaObj = new ListaObj<>(10);
        List<Paciente> listaCsv = new ArrayList();
        List<Paciente> listaTxt = new ArrayList();


        do {
            System.out.println("Escolha a opcao desejada: (digite 1, 2, 3, 4, 5, 6, 7, 8 ou 9)");
            System.out.println("1 - Adicionar um Usuario CSV");
            System.out.println("2 - Adicionar um Usuario TXT");
            System.out.println("3 - Exibir Relatorio");
            System.out.println("4 - Gravar CSV");
            System.out.println("5 - Ler CSV");
            System.out.println("6 - GravarTxt");
            System.out.println("7 - Ler Txt");
            System.out.println("8 - Buscar por nome de usuario");
            System.out.println("9 - Sair");
            opcao = leitor.nextInt();
            leitor.nextLine();

            // CSV
            switch (opcao) {
                case 1:
                    System.out.println("Digite seu nome:");
                    String nomeCsv = leitor.nextLine();

                    System.out.println("Digite seu email:");
                    String emailCsv = leitor.nextLine();

                    System.out.println("Digite seu CPF:");
                    String cpfCsv = leitor.nextLine();

                    System.out.println("Digite sua data de nascimento AAAA-MM--DD");
                    LocalDate dataDeNascimentoCsv = LocalDate.parse(leitor.nextLine());

                    System.out.println("Digite seu Telefone Fixo:");
                    String telefoneFixoCsv = leitor.nextLine();

                    System.out.println("Digite seu Telefone Celular:");
                    String telefoneMovelCsv = leitor.nextLine();

                    listaCsv.add(new Paciente(nomeCsv, emailCsv, cpfCsv, dataDeNascimentoCsv,
                            telefoneFixoCsv, telefoneMovelCsv));

                    System.out.println("Usuario adicionado!");
                    break;

                    // TXT
                case 2:
                    System.out.println("Digite seu nome:");
                    String nome = leitor.nextLine();

                    System.out.println("Digite seu email:");
                    String email = leitor.nextLine();

                    System.out.println("Digite seu CPF:");
                    String cpf = leitor.nextLine();

                    System.out.println("Digite sua data de nascimento AAAA-MM--DD");
                    LocalDate dataDeNascimento = LocalDate.parse(leitor.nextLine());

                    System.out.println("Digite seu Telefone Fixo:");
                    String telefoneFixo = leitor.nextLine();

                    System.out.println("Digite seu Telefone Celular:");
                    String telefoneMovel = leitor.nextLine();

                    System.out.println("Digite sua Rua:");
                    String rua = leitor.nextLine();

                    System.out.println("Digite seu Bairro:");
                    String bairro = leitor.nextLine();

                    System.out.println("Digite o numero da residencia:");
                    String numero = leitor.nextLine();

                    System.out.println("Digite o complemento da residencia:");
                    String complemento = leitor.nextLine();

                    System.out.println("Digite o cep do seu endereço:");
                    String cep = leitor.nextLine();

                    System.out.println("Digite sua cidade:");
                    String cidade = leitor.nextLine();

                    System.out.println("Digite a sigla do seu estado:");
                    String uf = leitor.nextLine();

                    Endereco endereco = new Endereco(null,rua,bairro, numero,complemento,cep,cidade,uf);

                    listaTxt.add(new Paciente(nome, email, cpf, dataDeNascimento, telefoneFixo,
                            telefoneMovel,endereco));

                    System.out.println("Usuario adicionado!");
                    break;

                case 3:
                    System.out.println("");
                    System.out.printf("%-40s | %42s | %11s | %10s | %14s | %15s  \n", "NOME", "EMAIL", "CPF",
                            "DATA NASC", "TELEFONE FIXO", "TELEFONE MOVEL");
                    for (int i = 0; i < listaCsv.size(); i++) {
                        Usuario p = listaCsv.get(i);
                        System.out.printf("%-40s | %42s | %11s | %10s | %14s | %15s  \n", p.getNome(), p.getEmail(),
                                p.getCpf(), p.getDataDeNascimento(), p.getTelefoneFixo(), p.getTelefoneCelular());
                    }
                    System.out.printf("");
                    break;

                case 4:
                    System.out.println("Gravar CSV :)");
                    Utils.gravaArquivoCsv(listaCsv, "Pacientes");
                    break;

                case 5:
                    System.out.println("Ler CSV :)");
                    Utils.leExibeArquivoCsv("Pacientes");
                    break;

                case 6:
                    Utils.gravaArquivoTxt(listaTxt, "Pacientes.txt");
                    break;
                case 7:
                    Utils.leArquivoTxt("Pacientes.txt");
                    break;
                case 8:
                    System.out.println("Digite o nome de um paciente:");
                    String paciente2 = leitor.nextLine();

                    for (int i = 0; i < listaTxt.size(); i++) {
                        Paciente paciente = listaTxt.get(i);
                        if (paciente2.equals(paciente.getNome())) {
                            System.out.println("Paciente: " + paciente2 + " encontrado");
                        }
                    }
                    break;
                case 9:
                    System.out.println("Obrigada por utilizar :)");
                    break;
                default:
                    System.out.println("Opção digitada inválida");
                    break;
            }
        } while (opcao != 9);
    }
}
