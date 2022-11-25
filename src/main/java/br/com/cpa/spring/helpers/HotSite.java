package br.com.cpa.spring.helpers;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import br.com.cpa.spring.models.Patient;

public class Utils {


    public static Integer calculaIdadePorData(LocalDate data) {
        return data.getYear();
    }

    // ============= Ordenação ==============
    public static void ordenarPorIdade(List<Patient> v) {
        Patient aux;
        for (int i = 0; i < v.size(); i++) {
            for (int j = 0; j < v.size() - 1; j++) {
                if (Utils.calculaIdadePorData(v.get(i).getDataDeNascimento()) < Utils
                        .calculaIdadePorData(v.get(j).getDataDeNascimento())) {
                    aux = v.get(i);
                    v.set(i, v.get(j));
                    v.set(j, aux);
                }
            }
        }
    }

    // =============== Gravando arquvivo CSV =====================
    public static void gravaArquivoCsv(List<Patient> lista,
                                       String nomeArq) {
        FileWriter arq = null; // objeto que representa o arquivo de gravação
        Formatter saida = null; // objeto usado para gravar no arquivo
        Boolean deuRuim = false;
        nomeArq += ".csv"; // Acrescenta a extensão.csv ao nome do arquivo

        ordenarPorIdade(lista);

        // Bloco para abrir o arquivo
        try {
            arq = new FileWriter(nomeArq); // cria ou abre o arquivo
            saida = new Formatter(arq);
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        // Bloco para gravar o arquivo
        try {
            saida.format("NOME;EMAIL;CPF;DATA NASCIMENTO;TELEFONE FIXO;TELEFONE MOVEL\n");
            for (int i = 0; i < lista.size(); i++) {
                Patient p = lista.get(i);
                saida.format("%s;%s;%s;%s;%s;%s\n", p.getNome(), p.getEmail(), p.getCpf(), p.getDataDeNascimento(),
                        p.getTelefoneFixo(), p.getTelefoneCelular());
            }
        } catch (FormatterClosedException erro) {
            System.out.println(erro);
            System.out.println("Erro ao gravar o arquivo");
            deuRuim = true;
        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException erro) {
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
        FileReader arq = null; // objeto que representa o arquivo de leitura
        Scanner entrada = null; // objeto usado para ler do arquivo
        Boolean deuRuim = false;
        nomeArq += ".csv";

        // Bloco para abrir o arquivo
        try {
            arq = new FileReader(nomeArq);
            entrada = new Scanner(arq).useDelimiter(";|\\n");
        } catch (FileNotFoundException erro) {
            System.out.println("Arquivo não encontrado");
            System.exit(1);
        }

        // Bloco para ler o arquivo
        try {
            // System.out.printf("%-20s %11s %15s %20s,%16s,%30s \n", "NOME", "RG", "CPF",
            // "TELEFONE FIXO","TELEFONE MOVEL","ENDERECO");
            while (entrada.hasNext()) {
                String nome = entrada.next();
                String email = entrada.next();
                String cpf = entrada.next();
                String dataDeNascimento = entrada.next();
                String telefoneFixo = entrada.next();
                String telefoneMovel = entrada.next();


                System.out.printf("%-40s | %42s | %11s | %10s | %14s | %15s \n", nome,
                        email, cpf, dataDeNascimento, telefoneFixo, telefoneMovel);
            }
        } catch (NoSuchElementException erro) {
            System.out.println(erro);
            System.out.println("Arquivo com problemas");
            deuRuim = true;
        } catch (IllegalStateException erro) {
            System.out.println("Erro na leitura do arquivo");
            deuRuim = true;
            System.out.println(erro);
        } finally {
            entrada.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }

    }

    // ==================== Gravar arquivo TXT =========================

    public static void gravaRegistro(String registro, String nomeArq) {
        BufferedWriter saida = null;

        // try-catch para abrir o arquivo
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            erro.printStackTrace();
        }
        // try-catch para gravar e fechar o arquivo
        try {
            saida.append(registro + "\n");
            saida.close();
        } catch (IOException erro) {
            System.out.println("Erro ao gravar o arquivo");
            erro.printStackTrace();
        }
    }

    public static void gravaArquivoTxt(List<Patient> lista, String nomeArq) {
        int contaRegDados = 0;

        // Monta o registro de header
        String header = "00PACIENTE    ";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";

        // Grava o registro de header
        gravaRegistro(header, nomeArq);

        // Monta e grava os registros de corpo 02
        String corpo;
        for (int i = 0; i < lista.size(); i++) {
            Patient paciente = lista.get(i);
            corpo = "02";
            corpo += String.format("%-6.6s", null);
            corpo += String.format("%-40.40s", paciente.getNome());
            corpo += String.format("%-42.42s", paciente.getEmail());
            corpo += String.format("%-11.11s", paciente.getCpf());
            corpo += String.format("%-10.10s", paciente.getDataDeNascimento());
            corpo += String.format("%-14.14s", paciente.getTelefoneFixo());
            corpo += String.format("%-15.15s", paciente.getTelefoneCelular());
            gravaRegistro(corpo , nomeArq);
            contaRegDados++;

            corpo = "03";
            corpo += String.format("%-6.6s", null);
            corpo += String.format("%-60.60s", paciente.getEndereco().getRua());
            corpo += String.format("%-30.30s", paciente.getEndereco().getBairro());
            corpo += String.format("%-9.9s", paciente.getEndereco().getCep());
            corpo += String.format("%-5.5s", paciente.getEndereco().getNumero());
            corpo += String.format("%-21.21s", paciente.getEndereco().getComplemento());
            corpo += String.format("%-20.20s", paciente.getEndereco().getCidade());
            corpo += String.format("%-2.2s", paciente.getEndereco().getUf());

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

        String nome, email, cpf, telefoneFixo, telefoneCelular, rua, bairro, numero, complemento, cep, cidade, uf;
        LocalDate dataDeNascimento;

        int contaRegDadoLido = 0;
        int qtdRegDadoGravado;

        List<Patient> listaLidaPaciente = new ArrayList();
        List<Endereco> listaLidaEndereco = new ArrayList();

        // try-catch para abrir o arquivo
        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        } catch (IOException erro) {
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
                    System.out.println("Tipo do arquivo: " + registro.substring(2, 14));
                    System.out.println("Data e hora da gravação: " + registro.substring(14, 33));
                    System.out.println("Versão do documento: " + registro.substring(33, 35));


                } else if (tipoRegistro.equals("01")) {
                    System.out.println("Registro de Trailer");
                    qtdRegDadoGravado = Integer.valueOf(registro.substring(2, 12));
                    if (contaRegDadoLido == qtdRegDadoGravado) {
                        System.out.println("Quantidade de registros lidos é compatível com " +
                                " quantidade de registros gravados");
                    } else {
                        System.out.println("Quantidade de registros lidos não é compatível com " +
                                " quantidade de registros gravados");
                    }

                } else if (tipoRegistro.equals("02")) {
                    System.out.println("Registro de Corpo");
                    nome = registro.substring(8, 48).trim();
                    email = registro.substring(48, 90).trim();
                    cpf = registro.substring(90, 101).trim();
                    dataDeNascimento = LocalDate.parse(registro.substring(101, 111), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    telefoneFixo = registro.substring(111, 125).trim();
                    telefoneCelular = registro.substring(125, 140).trim();

                    contaRegDadoLido++;

                    Patient paciente = new Patient(nome, email, cpf, dataDeNascimento, telefoneFixo, telefoneCelular);
                    listaLidaPaciente.add(paciente);

                } else if (tipoRegistro.equals("03")) {

                        rua = registro.substring(8, 68).trim();
                        bairro = registro.substring(68, 98).trim();
                        cep = registro.substring(98, 107).trim();
                        numero = registro.substring(107,112).trim();
                        complemento = registro.substring(112, 133).trim();
                        cidade = registro.substring(133, 153).trim();
                        uf = registro.substring(153, 155).trim();

                        contaRegDadoLido++;

                        Endereco endereco = new Endereco(null,rua,bairro,cep,numero,complemento,cidade,uf);
                    listaLidaEndereco.add(endereco);

                    } else {
                    System.out.println("Tipo de registro inválido!");
                }

                // Lê o próximo registro
                registro = entrada.readLine();
            }
            entrada.close();
        } catch (IOException erro) {
            System.out.println("Erro ao ler o arquivo");
            erro.printStackTrace();
        }

        // Exibindo a listaLida
        System.out.println("\nConteúdo da lista lida do arquivo");
        for (int i = 0; i < listaLidaPaciente.size(); i++) {
            System.out.println(listaLidaPaciente.get(i));
            System.out.println(listaLidaEndereco.get(i));
        }

    }

}
