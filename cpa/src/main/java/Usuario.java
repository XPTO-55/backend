import java.time.LocalDate;
import java.util.Date;

public abstract class Usuario {

    private String nome;
    private String email;
    private String cpf;
    private LocalDate dataDeNascimento;
    private String telefoneFixo;
    private String telefoneCelular;

    private Endereco endereco;


    public Usuario(String nome, String email, String cpf, LocalDate dataDeNascimento, String telefoneFixo,
                   String telefoneCelular, Endereco endereco) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataDeNascimento = dataDeNascimento;
        this.telefoneFixo = telefoneFixo;
        this.telefoneCelular = telefoneCelular;
        this.endereco = endereco;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataDeNascimento=" + dataDeNascimento +
                ", telefoneFixo='" + telefoneFixo + '\'' +
                ", telefoneCelular='" + telefoneCelular + '\'' +
                ", endereco=" + endereco.toString() +
                '}';
    }
}
