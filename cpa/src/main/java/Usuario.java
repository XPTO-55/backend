import java.time.LocalDate;
import java.util.Date;

public abstract class  Usuario {

    private String nome;
    private String rg;
    private String cpf;
    private LocalDate dataDeNascimento;
    private String telefoneFixo;
    private String telefoneCelular;
    private String endereco;

    public Usuario(String nome,
                   String rg,
                   String cpf,
                   LocalDate dataDeNascimento,
                   String telefoneFixo,
                   String telefoneCelular,
                   String endereco) {
        this.nome = nome;
        this.rg = rg;
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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", rg='" + rg + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataDeNascimento=" + dataDeNascimento +
                ", telefoneFixo='" + telefoneFixo + '\'' +
                ", telefoneCelular='" + telefoneCelular + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
