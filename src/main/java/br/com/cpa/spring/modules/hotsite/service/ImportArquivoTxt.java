package br.com.cpa.spring.modules.hotsite.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cpa.spring.models.Patient;
import br.com.cpa.spring.models.Role;
import br.com.cpa.spring.modules.user.patient.PatientRepository;
import br.com.cpa.spring.repositories.AddressRepository;
import br.com.cpa.spring.repositories.RoleRepository;
import br.com.cpa.spring.models.Address;

@Service
public class ImportArquivoTxt {
  @Autowired
  AddressRepository addressRepository;
  @Autowired
  PatientRepository patientRepository;
  @Autowired
  RoleRepository roleRepository;

  private BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  public void execute(String arquivo) {
    String registro = null, tipoRegistro;

    String nome, email, cpf, telefoneFixo, telefoneCelular, rua, bairro, numero, complemento, cep, cidade, uf;
    LocalDate dataDeNascimento;

    List<Patient> listaLidaPaciente = new ArrayList<Patient>();
    List<Address> listaLidaEndereco = new ArrayList<Address>();
    int contaRegDadoLido = 0;
    int qtdRegDadoGravado;

    Scanner scanner = new Scanner(arquivo);
    // Leitura do primeiro registro do arquivo

    while (scanner.hasNext()) {
      registro = scanner.nextLine();
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
        numero = registro.substring(107, 112).trim();
        complemento = registro.substring(112, 133).trim();
        cidade = registro.substring(133, 153).trim();
        uf = registro.substring(153, 155).trim();

        contaRegDadoLido++;

        Address endereco = new Address(null, rua, bairro, cep, numero, complemento, cidade, uf);
        listaLidaEndereco.add(endereco);

      } else {
        System.out.println("Tipo de registro inválido!");
      }
    }

    // Exibindo a listaLida
    System.out.println("\nConteúdo da lista lida do arquivo");
    for (int i = 0; i < listaLidaPaciente.size(); i++) {
      System.out.println(listaLidaPaciente.get(i));
      System.out.println(listaLidaEndereco.get(i));
      Address a = listaLidaEndereco.get(i);
      Patient p = listaLidaPaciente.get(i);
      Patient patient = new Patient();
      patient.setName(p.getName());
      patient.setCpf(p.getCpf());
      patient.setLandline(p.getLandline());
      patient.setPhone(p.getPhone());
      patient.setBirthday(p.getBirthday());
      patient.setEmail(p.getEmail());
      patient.setAbout(null);
      patient.setPassword(passwordEncoder().encode("temppassword"));

      if (a != null) {
        Address address = new Address();
        address.setStreet(a.getStreet());
        address.setDistrict(a.getDistrict());
        address.setNumber(a.getNumber());
        address.setComplement(a.getComplement());
        address.setZipcode(a.getZipcode());
        address.setCity(a.getCity());
        address.setUf(a.getUf());
        addressRepository.save(address);
        patient.setAddress(address);
      }

      Role roleUser = roleRepository.findByName("USER");
      patient.addRole(roleUser);

      patientRepository.save(patient);
    }
    scanner.close();
    return;
  }
}
