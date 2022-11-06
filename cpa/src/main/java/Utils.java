import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Utils {


    public static Integer calculaIdadePorData(LocalDate data){
        return data.getYear();
    }

//     desafio ordenação
    public static void ordenarPorIdade(ListaObj<Paciente> v) {
        Paciente aux;
        for (int i = 0; i < v.getTamanho(); i++) {
            for (int j = 0; j < v.getTamanho() - 1; j++) {
                if (Utils.calculaIdadePorData(v.getElemento(i).getDataDeNascimento())
                        < Utils.calculaIdadePorData(v.getElemento(j).getDataDeNascimento())) {
                    aux = v.getElemento(i);
                    v.setElemento(i, v.getElemento(j));
                    v.setElemento(j, aux);
                }
            }
        }
    }
//    Gravando arquvivo CSV
    public static void gravaArquivoCsv(ListaObj<Paciente> lista,
                                       String nomeArq) {
        FileWriter arq = null;   // objeto que representa o arquivo de gravação
        Formatter saida = null;  // objeto usado para gravar no arquivo
        Boolean deuRuim = false;
        nomeArq += ".csv";      // Acrescenta a extensão .csv ao nome do arquivo

        ordenarPorIdade(lista);


        // Bloco para abrir o arquivo
        try {
            arq = new FileWriter(nomeArq);  // cria ou abre o arquivo
            saida = new Formatter(arq);
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        // Bloco para gravar o arquivo
        try {
            saida.format("NOME;RG;CPF;DATA NASCIMENTO;TELEFONE FIXO;TELEFONE MOVEL;ENDERECO \n");
            for (int i = 0; i < lista.getTamanho(); i++) {
                Paciente p = lista.getElemento(i);
                saida.format("%s;%s;%s;%s;%s;%s;%s\n",p.getNome(), p.getRg(),p.getCpf(),p.getDataDeNascimento(),
                        p.getTelefoneFixo(),p.getTelefoneCelular(),p.getEndereco());
            }
        }
        catch (FormatterClosedException erro) {
            System.out.println(erro);
            System.out.println("Erro ao gravar o arquivo");
            deuRuim = true;
        }
        finally {
            saida.close();
            try {
                arq.close();
            }
            catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }

// Ler / exibe arquivo CSV
    public static void leExibeArquivoCsv(String nomeArq) {
        FileReader arq = null;  // objeto que representa o arquivo de leitura
        Scanner entrada = null; // objeto usado para ler do arquivo
        Boolean deuRuim = false;
        nomeArq += ".csv";

        // Bloco para abrir o arquivo
        try {
            arq = new FileReader(nomeArq);
            entrada = new Scanner(arq).useDelimiter(";|\\n");
        }
        catch (FileNotFoundException erro) {
            System.out.println("Arquivo não encontrado");
            System.exit(1);
        }

        // Bloco para ler o arquivo
        try {
            // System.out.printf("%-20s %11s %15s %20s,%16s,%30s \n", "NOME", "RG", "CPF", "TELEFONE FIXO","TELEFONE MOVEL","ENDERECO");
            while (entrada.hasNext()) {
                String nome = entrada.next();
                String rg = entrada.next();
                String cpf = entrada.next();
                String dataDeNascimento = entrada.next();
                String telefoneFixo = entrada.next();
                String telefoneMovel = entrada.next();
                String endereco = entrada.next();

                System.out.printf("%-20s | %11s | %15s | %17s | %15s | %16s | %-20s \n", nome,
                        rg,cpf,dataDeNascimento,telefoneFixo,telefoneMovel,endereco);
            }
        }
        catch (NoSuchElementException erro) {
            System.out.println(erro);
            System.out.println("Arquivo com problemas");
            deuRuim = true;
        }
        catch (IllegalStateException erro) {
            System.out.println("Erro na leitura do arquivo");
            deuRuim = true;
            System.out.println(erro);
        }
        finally {
            entrada.close();
            try {
                arq.close();
            }
            catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }

    }

// Gravar arquivo TXT

    public static void gravaRegistro(String registro, String nomeArq) {
        BufferedWriter saida = null;

        // try-catch para abrir o arquivo
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            erro.printStackTrace();
        }
        // try-catch para gravar e fechar o arquivo
        try {
            saida.append(registro + "\n");
            saida.close();
        }
        catch (IOException erro) {
            System.out.println("Erro ao gravar o arquivo");
            erro.printStackTrace();
        }
    }

    public static void gravaArquivoTxt(List<Paciente> lista, String nomeArq) {
        int contaRegDados = 0;

        // Monta o registro de header
        String header = "00PACIENTE";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";

        // Grava o registro de header
        gravaRegistro(header, nomeArq);

        // Monta e grava os registros de corpo
        String corpo;
        for (Paciente paciente : lista) {
            corpo = "02";
            corpo += String.format("%-40.40s", paciente.getNome());
            corpo += String.format("%-10.10s", paciente.getRg());
            corpo += String.format("%-50.50s", paciente.getCpf());
            corpo += String.format("%-40.40s", paciente.getDataDeNascimento());
            corpo += String.format("%05.2f", paciente.getTelefoneFixo());
            corpo += String.format("%03d", paciente.getTelefoneCelular());
            corpo += String.format("%03d", paciente.getEndereco());

            gravaRegistro(corpo, nomeArq);
            contaRegDados++;
        }

        // Monta e grava o registro de trailer
        String trailer = "01";
        trailer += String.format("%010d", contaRegDados);
        gravaRegistro(trailer, nomeArq);
    }


    public static void leArquivoTxt(String nomeArq) {
        BufferedReader entrada = null;
        String registro, tipoRegistro;

        String nome,rg,cpf, telefoneFixo, telefoneCelular, endereco;
        LocalDate dataDeNascimento;

        int contaRegDadoLido = 0;
        int qtdRegDadoGravado;

        List<Paciente> listaLida = new ArrayList();

        // try-catch para abrir o arquivo
        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            erro.printStackTrace();
        }

        // try-catch para ler e fechar o arquivo
        try {
            // Leitura do primeiro registro do arquivo
            registro = entrada.readLine();

            while (registro != null) {
                tipoRegistro = registro.substring(0, 2);
                if (tipoRegistro.equals("00")) {
                    System.out.println("Registro de Header");
                    System.out.println("Tipo do arquivo: " + registro.substring(2, 6));
                    System.out.println("Ano e semestre: " + registro.substring(6, 11));
                    System.out.println("Data e hora da gravação: " + registro.substring(11, 30));
                    System.out.println("Versão do documento: " + registro.substring(30, 32));
                }
                else if (tipoRegistro.equals("01")) {
                    System.out.println("Registro de Trailer");
                    qtdRegDadoGravado = Integer.valueOf(registro.substring(2, 12));
                    if (contaRegDadoLido == qtdRegDadoGravado) {
                        System.out.println("Quantidade de registros lidos é compatível com " +
                                " quantidade de registros gravados");
                    }
                    else {
                        System.out.println("Quantidade de registros lidos não é compatível com " +
                                " quantidade de registros gravados");
                    }
                }
                else if (tipoRegistro.equals("02")) {
                    System.out.println("Registro de Corpo");
                    nome = registro.substring(15, 65).trim();
                    rg = registro.substring(7, 15).trim();
                    cpf = registro.substring(7, 15).trim();
                    dataDeNascimento = LocalDate.parse(registro.substring(7,15), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    telefoneCelular = registro.substring(7, 15).trim();
                    telefoneFixo = registro.substring(7, 15).trim();
                    endereco = registro.substring(7, 15).trim();



                    contaRegDadoLido++;

                    Paciente paciente = new Paciente( nome, rg, cpf, dataDeNascimento, telefoneFixo, telefoneCelular, endereco);
                    listaLida.add(paciente);

                }
                else {
                    System.out.println("Tipo de registro inválido!");
                }

                // Lê o próximo registro
                registro = entrada.readLine();
            }
            entrada.close();
        }
        catch (IOException erro) {
            System.out.println("Erro ao ler o arquivo");
            erro.printStackTrace();
        }

        // Exibindo a listaLida
        System.out.println("\nConteúdo da lista lida do arquivo");
        for (Paciente paciente : listaLida) {
            System.out.println(paciente);
        }

    }

}
