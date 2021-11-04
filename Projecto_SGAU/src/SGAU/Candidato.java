package SGAU;

public class Candidato extends Pessoa{

    public static int qtd_engInformatica=0;
    public static int qtd_Informatica=0;

    private char nee;
    private String disciplinas_exame;
    private String data_exame;
    private String hora_exame;
    private String curso;
    private String username;
    private String password;
    private float notaExame1;
    private float notaExame2;


    public Candidato(String nome, char genero, String data_nascimento, String id, String estado_civil, String pais_nascimento, String contacto, char nee, String disciplinas_exame, String data_exame, String hora_exame, String curso, String username, String password) {
        super(nome, genero, data_nascimento, id, estado_civil, pais_nascimento, contacto);
        this.nee = nee;
        this.disciplinas_exame = disciplinas_exame;
        this.data_exame = data_exame;
        this.hora_exame = hora_exame;
        this.curso = curso;
        this.username = username;
        this.password = password;
    }

    public char getNee() {
        return nee;
    }

    public void setNee(char nee) {
        this.nee = nee;
    }

    public String getDisciplinas_exame() {
        return disciplinas_exame;
    }

    public String getData_exame() {
        return data_exame;
    }

    public String getHora_exame() {
        return hora_exame;
    }

    public String getCurso() {
        return curso;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getNotaExame1() {
        return notaExame1;
    }

    public void setNotaExame1(float notaExame1) {
        this.notaExame1 = notaExame1;
    }

    public float getNotaExame2() {
        return notaExame2;
    }

    public void setNotaExame2(float notaExame2) {
        this.notaExame2 = notaExame2;
    }

    public static String total_candidados(){
        int total = qtd_engInformatica+qtd_Informatica;
        return ""+total;
    }

    public String toString(){

        System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("|%7s %20s %23s %20s %20s %25s %16s %10s %10s %10s                 |\n","Nome completo:","Genero:","Data de Nascimento:","Identificacao:","Estado Civil:","Pais de Nascimento:","Contacto:","Nee:","Curso:","Disciplinas exame:");
        System.out.printf("|%s %7c %18s %18s|\n",super.toString(),nee,curso,disciplinas_exame);
        return            "-----------------------------------------------------------------------------------------------------";
    }
}
