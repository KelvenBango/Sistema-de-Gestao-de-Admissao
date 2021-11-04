package SGAU;
import java.io.Serializable;
class Pessoa implements Serializable {

    protected String nome;
    protected char genero;
    protected String data_nascimento;
    protected String id;
    protected String estado_civil;
    protected String pais_nascimento;
    protected String contacto;

    public Pessoa(String nome, char genero, String data_nascimento, String id, String estado_civil, String pais_nascimento, String contacto) {
        this.nome = nome;
        this.genero = genero;
        this.data_nascimento = data_nascimento;
        this.id = id;
        this.estado_civil = estado_civil;
        this.pais_nascimento = pais_nascimento;
        this.contacto = contacto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public String getPais_nascimento() {
        return pais_nascimento;
    }

    public void setPais_nascimento(String pais_nascimento) {
        this.pais_nascimento = pais_nascimento;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String toString() {

        return String.format("%s %5c %20s %23s %24s %18s %25s",nome,genero,data_nascimento,id,estado_civil,pais_nascimento,contacto);

    }
}
