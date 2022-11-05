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

        ListaObj<Paciente> usuarioListaObj = new ListaObj<>(10);
        List<Paciente> lista = new ArrayList();

        do{
            System.out.println("Escolha a opcao desejada: (digite 1, 2, 3, 4, 5, 6, 7 ou 8)");
            System.out.println("1 - Adicionar um Usuario");
            System.out.println("2 - Exibir Relatorio");
            System.out.println("3 - Gravar CSV");
            System.out.println("4 - Ler CSV");
            System.out.println("5 - GravarTxt");
            System.out.println("6 - Ler Txt");
            System.out.println("7 - Buscar por nome de usuario");
            System.out.println("8 - Sair");
            opcao = leitor.nextInt();
            leitor.nextLine();

            switch (opcao){
                case 1:
                    System.out.println("Digite seu nome:");
                    String nome = leitor.nextLine();

                    System.out.println("Digite seu RG:");
                    String rg = leitor.nextLine();

                    System.out.println("Digite seu CPF:");
                    String cpf = leitor.nextLine();


                    System.out.println("Digite sua data de nascimento AAAA-MM--DD");
                    LocalDate dataDeNascimento = LocalDate.parse(leitor.nextLine());

                    System.out.println("Digite seu Telefone Fixo:");
                    String telefoneFixo = leitor.nextLine();

                    System.out.println("Digite seu Telefone Celular:");
                    String  telefoneMovel = leitor.nextLine();

                    System.out.println("Digite seu endereco:");
                    String endereco = leitor.nextLine();

                    usuarioListaObj.adiciona(new Paciente(nome,rg,cpf,dataDeNascimento,telefoneFixo,telefoneMovel,
                            endereco,null,null));

                    System.out.println("Usuario adicionado!");
                    break;

                case 2:
                    System.out.println("");
                    System.out.printf("%-20s | %11s | %15s | %17s | %15s | %16s | %-20s \n", "NOME", "RG", "CPF",
                            " DATA NASCIMENTO ", "TELEFONE FIXO","TELEFONE MOVEL","ENDERECO");
                    for (int i = 0; i < usuarioListaObj.getTamanho(); i++){
                        Usuario p = usuarioListaObj.getElemento(i);
                        System.out.printf("%-20s | %11s | %15s | %17s | %15s | %16s | %-20s \n", p.getNome(), p.getRg(),
                                p.getCpf(),p.getDataDeNascimento(),p.getTelefoneFixo(),p.getTelefoneCelular(),p.getEndereco());
                    }
                    System.out.printf("");
                    break;

                case 3:
                    System.out.println("Gravar CSV :)");
                    Utils.gravaArquivoCsv(usuarioListaObj,"Pacientes");
                    break;

                case 4:
                    System.out.println("Ler CSV :)");
                    Utils.leExibeArquivoCsv("Pacientes");
                    break;

                case 5:
                    Utils.gravaArquivoTxt(lista,"Pacientes");
                    break;
                case 6:
                    Utils.leArquivoTxt("Paciente");
                    break;
                case 7:
                    System.out.println("Digite o nome de um comprador:");
                    String comprador = leitor.nextLine();

                    for (int i = 0; i < usuarioListaObj.getTamanho(); i++) {
                        Paciente paciente = usuarioListaObj.getElemento(i);
                        if (comprador.equals(paciente.getNome())){
                            System.out.println("Comprador " + comprador + " encontrado");
                        }
                    }
                    break;
                case 8:
                    System.out.println("Obrigada por utilizar :)");
                    break;
                default:
                    System.out.println("Opção digitada inválida");
                    break;
            }
        }while (opcao != 8);
    }
    }


