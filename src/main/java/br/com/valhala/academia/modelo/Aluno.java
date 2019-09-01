package br.com.valhala.academia.modelo;

import br.com.valhala.academia.db.entities.listeners.AlunoEntityListener;
import br.com.valhala.academia.modelo.enums.EnumSexoAluno;
import br.com.valhala.academia.modelo.enums.EnumSituacaoAluno;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@EntityListeners(value = {AlunoEntityListener.class})
@Table(name = "tb_aluno")
public class Aluno implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "seq_id_aluno", sequenceName = "seq_id_aluno", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_id_aluno")
    private Long id;

    @NotBlank(message = "{aluno.nome.notnull}")
    @Size(min = 3, max = 100, message = "{aluno.nome.size}")
    @Column(name = "nome", unique = true, nullable = false, length = 100)
    private String nome;

    @CPF(message = "{aluno.cpf.invalido}")
    @NotBlank(message = "{aluno.cpf.notnull}")
    @Column(name = "cpf", unique = true, nullable = false, length = 20)
    private String cpf;

    @Email(message = "{aluno.email.invalido}")
    @NotBlank(message = "{aluno.email.notnull}")
    @Column(name = "email", nullable = false, length = 320)
    private String email;

    @NotNull(message = "{aluno.data_nascimento.notnull}")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento", nullable = false)
    private Date dataNascimento;

    @NotNull(message = "{aluno.situacao.notnull}")
    @Enumerated(EnumType.STRING)
    @Column(name = "situacao", nullable = false, length = 30)
    private EnumSituacaoAluno situacao = EnumSituacaoAluno.ATIVO;

    @NotNull(message = "{aluno.sexo.notnull}")
    @Enumerated(EnumType.STRING)
    @Column(name = "sexo", nullable = false, length = 30)
    private EnumSexoAluno sexo = EnumSexoAluno.NAO_INFORMADO;

    @Column(name = "uuid", nullable = false, length = 255)
    private String uuid;

    @Column(name = "path_foto", nullable = true, length = 255)
    private String pathFoto;

    @Valid
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tb_aluno_endereco", joinColumns = {
            @JoinColumn(name = "id_aluno", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "id_endereco", referencedColumnName = "id")})
    private Set<Endereco> enderecos;

    @Valid
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aluno", orphanRemoval = true)
    private Set<Telefone> telefones;

    public void adicionaEndereco(final Endereco endereco) {
        if (enderecos == null) {
            enderecos = new HashSet<>();
        }
        enderecos.add(endereco);
    }

    public void adicionaTelefone(final Telefone telefone) {
        if (telefones == null) {
            telefones = new HashSet<>();
        }
        telefones.add(telefone);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EnumSexoAluno getSexo() {
        return sexo;
    }

    public void setSexo(EnumSexoAluno sexo) {
        this.sexo = sexo;
    }

    public EnumSituacaoAluno getSituacao() {
        return situacao;
    }

    public void setSituacao(EnumSituacaoAluno situacao) {
        this.situacao = situacao;
    }

    public Set<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<Telefone> telefones) {
        this.telefones = telefones;
    }

    public void removeEndereco(final Endereco endereco) {
        if (enderecos != null) {
            enderecos.remove(endereco);
        }
    }

    public void removeTelefone(Telefone telefone) {
        if (telefones != null) {
            System.out.println("Esta na lista: " + telefones.contains(telefone));
            telefones.remove(telefone);
        }
    }

    @Override
    public String toString() {
        return "Aluno [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", email=" + email + ", dataNascimento="
                + dataNascimento + ", situacao=" + situacao + ", sexo=" + sexo + "]";
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPathFoto() {
        return pathFoto;
    }

    public void setPathFoto(String pathFoto) {
        this.pathFoto = pathFoto;
    }
}
