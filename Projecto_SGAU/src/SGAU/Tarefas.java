package SGAU;
import java.io.*;
import java.util.*;

public class Tarefas {


    Vector vector_ref;
    Pessoa pessoa;
    Autenticacao auth = new Autenticacao();
    BufferedReader x = new BufferedReader(new InputStreamReader(System.in));
    Arquivos arq = new Arquivos();
    String file_name = "File.bin";
    String usernameCredentials, passwordCredential;
    Menu menu;

    public Tarefas() {

        vector_ref = new Vector();
        menu = new Menu();

    }

    public void dados_candidato() throws IOException {

        byte choice;
        percorre_ficheiro();
        requisita_dados_candidato();
        cria_vector_candidato();
        regista_ficheiro();

        System.out.print("[0] Menu principal\nou\n[1] Terminar seccao\nReply:");

        choice = Byte.parseByte(x.readLine());
        if (choice == (byte) 0)
            menu.menu_candidato();
        else if (choice == (byte) 1)
            menu.menu();
    }

    public String requisita_nome_completo() throws IOException {

        String apelido, outros_Nomes;

        apelido = auth.authentify_String((byte) 3, (byte) 15, "\nApelido");
        outros_Nomes = auth.authentify_String((byte) 3, (byte) 25, "Outros Nomes");

        return outros_Nomes + " " + apelido;
    }

    public String requisita_data_nascimento() throws IOException {
        byte dia, mes;
        short ano;

        System.out.println("\nDATA DE NASCIMENTO");
        dia = auth.authentify_Byte((byte) 1, (byte) 31, "Dia");
        mes = auth.authentify_Byte((byte) 1, (byte) 12, "Mes");
        ano = auth.authentify_Short((short) 2005, "Ano");

        return dia + "/" + mes + "/" + ano;
    }

    public void requisita_dados_candidato() throws IOException {

        String nome, data_nascimento, id, estado_civil, pais_nascimento, contacto, curso, disciplinas_exame="",data_exame="",hora_exame="";
        char genero, nee;
        String user,pw;
        byte k=0,l=0,h=0,j=0,option;

        user = auth.authentify_String((byte) 3,(byte) 20,"Nome de utilizador");
        pw = auth.authentify_String((byte) 8,(byte) 16,"Palavra-passe (8 caracteres no minimo)");
        System.out.println("**SALVO**");

        while (h<55){
            System.out.print("-");
            h++;
        }
        System.out.println("\n              PROCESSO DE CANDIDATURA:");
        while (j<55){
            System.out.print("-");
            j++;
        }
        nome = requisita_nome_completo();
        data_nascimento = requisita_data_nascimento();
        genero = auth.genderMethod();
        id = auth.authentify_String((byte) 5, (byte) 15, "Numero de identificacao");
        estado_civil = auth.estadoCivil();
        pais_nascimento = auth.authentify_String((byte) 3, (byte) 15, "Pais de Nascimento");
        contacto = auth.authentify_String((byte) 9, (byte) 9, "Contacto");
        curso = auth.cursoMenu();
        nee = auth.neeMethod();


        if(curso.equalsIgnoreCase("Informatica")) {
            disciplinas_exame = "Matematica 70% - Portugues 30%";
            data_exame =        "17/01/2021 - 19/01/2021";
            hora_exame =        "08:00 - 13:00";

        }else if(curso.equalsIgnoreCase("Engenharia Informatica")) {
            disciplinas_exame = "Matematica 50% - Fisica 50%";
            data_exame =        "17/01/2021 - 15/01/2021";
            hora_exame =        "08:00 - 13:00";
        }

        pessoa = new Candidato(nome, genero, data_nascimento, id, estado_civil, pais_nascimento, contacto, nee,disciplinas_exame,data_exame,hora_exame,curso,user,pw);

        while (k<55){
            System.out.print("-");
            k++;
        }
        System.out.println("\n              CONFIRMACAO DOS DADOS:");
        while (l<55){
            System.out.print("-");
            l++;
        }

            print();


            System.out.print(  "\n      Deseja alterar alguma informacao?" +
                               "\n              [1] SIM     [2] NAO      " +
                               "\n  Reply:");
            option = Byte.parseByte(x.readLine());

            if(option == (byte) 1) {

                actualizar_candidato();
            }

            print();

        System.out.println("\n\rINFORMACAO GUARDADA\n");
    }

    public void cria_vector_candidato() {
        vector_ref.addElement(pessoa);
        vector_ref.trimToSize();
    }

    public void regista_ficheiro() {
        try {
            FileOutputStream file_out = new FileOutputStream(file_name);
            ObjectOutputStream write_object = new ObjectOutputStream(file_out);

            write_object.writeObject(vector_ref);
            write_object.close();

        } catch (FileNotFoundException a) {
            System.out.println("FICHEIRO NAO ENCONTRADO");
        } catch (IOException b) {
            System.out.println(b.getMessage());
        }
    }

    public void percorre_ficheiro() {

        try {
            FileInputStream file_in = new FileInputStream(file_name);
            ObjectInputStream obj_in = new ObjectInputStream(file_in);

            vector_ref = (Vector) obj_in.readObject();
            obj_in.close();

        } catch (FileNotFoundException a) {
            System.out.println("FICHEIRO NAO ENCONTRADO");
        } catch (IOException b) {
            System.out.println(b.getMessage());
        } catch (ClassNotFoundException c) {
            System.out.println("VERIFIQUE A EXISTENCIA DA CLASSE");
        }
    }

    public String toString() {

        percorre_ficheiro();
        String arg = "";
        for (int i = 0; i < vector_ref.size(); i++) {

            pessoa = (Pessoa) vector_ref.elementAt(i);

            arg += pessoa;
        }
        return arg;
    }

    public void deleteCandidate() throws IOException {

        percorre_ficheiro();

        String id;
        byte ans;

        System.out.print("\nInforme o id:");
        id = x.readLine();

        for (int i = 0; i < vector_ref.size(); i++) {

            pessoa = (Pessoa) vector_ref.elementAt(i);

            if(pessoa instanceof Candidato){

                if (id.equalsIgnoreCase("" + pessoa.getId())) {

                    System.out.print("\nApagar ["+pessoa.getNome()+"] com o id [" + id + "]?\n\t[1] Sim\t[2] Nao\nReply:");
                    ans = Byte.parseByte(x.readLine());
                    if (ans == (byte) 1) {

                        vector_ref.removeElementAt(i);

                        System.out.println("\tAPAGADO");
                    }
                } else {
                    System.out.println("\tID NAO ENCONTRADO");
                }
            }

        }
        regista_ficheiro();
    }

    public void getMyInfo(String user) {

        percorre_ficheiro();
        Candidato candidato;

        for (int i = 0; i < vector_ref.size(); i++) {

            pessoa = (Pessoa) vector_ref.elementAt(i);

            if(pessoa instanceof Candidato){

                candidato = (Candidato) pessoa;

                if (user.equalsIgnoreCase("" + candidato.getUsername())) {

                    byte k=0,l=0,m=0,n=0;

                    while (k<90){
                        System.out.print("-");
                        k++;
                    }
                    System.out.println("\n                                  MINHAS INFORMACOES:");
                    while (l<90){
                        System.out.print("-");
                        l++;
                    }
                    System.out.printf("\n%s %s %36s %s\n","Nome completo:",pessoa.getNome(),"Data de Nascimento:",pessoa.getData_nascimento());
                    System.out.printf("%s %s %52s %s\n","Genero       :",pessoa.getGenero(),"Pais de Nascimento:",pessoa.getPais_nascimento());
                    System.out.printf("%s %s %41s %s\n","Nr.Id        :",pessoa.getId(),"Estado Civil:",pessoa.getEstado_civil());
                    System.out.printf("%s %s %29s %s\n","Contacto     :",pessoa.getContacto(),"Nee:",candidato.getNee());
                    System.out.printf("%s %s %40s %s\n","Curso        :",candidato.getCurso(),"Disciplinas exame:",candidato.getDisciplinas_exame());

                    while (m<90){
                        System.out.print("-");
                        m++;
                    }
                    System.out.println("\n                             INFORMACOES SOBRE A CANDIDATURA:");
                    while (n<90){
                        System.out.print("-");
                        n++;
                    }
                    System.out.println("\nLocal exame: UEM-Complexo Pedagogico II");
                    System.out.println("Data exame : "+candidato.getData_exame());
                    System.out.println("Sala exame : B12");
                    System.out.println("Hora exame : "+candidato.getHora_exame());
                }
            }
        }
    }

    public String getInfo_byCourse(String curso) {

        String info = "";
        percorre_ficheiro();
        Candidato candidato;

        for (int i = 0; i < vector_ref.size(); i++) {

            pessoa = (Pessoa) vector_ref.elementAt(i);

            if(pessoa instanceof Candidato){

                candidato = (Candidato) pessoa;

                if (curso.equals(candidato.getCurso())) {

                    info += candidato.toString();

                }
            }
        }
        return info;
    }

    public void qtd_candidatos() {

        Candidato candidato;
        percorre_ficheiro();

        for (int i = 0; i < vector_ref.size(); i++) {

            pessoa = (Pessoa) vector_ref.elementAt(i);

            if (pessoa instanceof Candidato) {

                candidato = (Candidato) pessoa;

                if (candidato.getCurso().equalsIgnoreCase("Engenharia Informatica")) {

                    Candidato.qtd_engInformatica++;

                } else if (candidato.getCurso().equalsIgnoreCase("Informatica"))

                    Candidato.qtd_Informatica++;
            }
        }
        regista_ficheiro();
    }

    //Actualiza candidato no processo de inscricao

    public void actualizar_candidato()throws IOException{

        percorre_ficheiro();
        Candidato candidato;

        byte k=0,l=0,option,choice;
        char genero, nee;
        String nome, data_nascimento, id, estado_civil, pais_nascimento, contacto;



        while (k<55){
            System.out.print("-");
            k++;
        }
        System.out.println("\n    SELECIONE A INFORMACAO QUE PRETENDE ACTUALIZAR:");
        while (l<55){
            System.out.print("-");
            l++;
        }
        System.out.print("\n    [1] Nome completo"+"" +
                         "\n    [2] Data de Nascimento"+
                         "\n    [3] Genero"+
                         "\n    [4] Id"+
                         "\n    [5] Estado Civil"+
                         "\n    [6] Pais Nascimento"+
                         "\n    [7] Contacto"+
                         "\n    [8] Nee"+
                         "\nReply:");
        option = Byte.parseByte(x.readLine());

        switch (option){
            case (byte) 1:
                nome = requisita_nome_completo();
                pessoa.setNome(nome); break;

            case (byte) 2:
                data_nascimento = requisita_data_nascimento();
                pessoa.setData_nascimento(data_nascimento);break;

            case (byte) 3:
                genero = auth.genderMethod();
                pessoa.setGenero(genero); break;

            case (byte) 4:
                id = auth.authentify_String((byte) 5, (byte) 15, "Numero de identificacao");
                pessoa.setId(id);break;

            case (byte) 5:
                estado_civil = auth.estadoCivil();
                pessoa.setEstado_civil(estado_civil); break;

            case (byte) 6:
                pais_nascimento = auth.authentify_String((byte) 3, (byte) 15, "Pais de Nascimento");
                pessoa.setPais_nascimento(pais_nascimento);break;

            case (byte) 7:
                contacto = auth.authentify_String((byte) 9, (byte) 9, "Contacto");
                pessoa.setContacto(contacto);break;

            case (byte) 8:

                if (pessoa instanceof Candidato) {
                    candidato = (Candidato) pessoa;
                    nee = auth.neeMethod();
                    candidato.setNee(nee);
                }

            default:
                System.out.println("Invalido");
        }
        System.out.print(  "\n      Deseja alterar alguma outra informacao?" +
                           "\n              [1] SIM     [2] NAO      " +
                           "\n  Reply:");
        choice = Byte.parseByte(x.readLine());

        if(choice == (byte) 1)
            actualizar_candidato();

        regista_ficheiro();
    }

    //Actualiza candidato depois da inscricao

    public void update(String user)throws IOException{

        percorre_ficheiro();
        Candidato candidato;
        byte k=0,l=0,option,choice;
        char genero, nee;
        String nome, data_nascimento, id, estado_civil, pais_nascimento, contacto;

        for (int i = 0; i < vector_ref.size(); i++) {

            pessoa = (Pessoa) vector_ref.elementAt(i);

            if (pessoa instanceof Candidato) {
                candidato = (Candidato) pessoa;

                if (user.equalsIgnoreCase("" + candidato.getUsername())) {

                    while (k < 55) {
                        System.out.print("-");
                        k++;
                    }
                    System.out.println("\n    SELECIONE A INFORMACAO QUE PRETENDE ACTUALIZAR:");
                    while (l < 55) {
                        System.out.print("-");
                        l++;
                    }
                    System.out.print("\n    [1] Nome completo" + "" +
                            "\n    [2] Data de Nascimento" +
                            "\n    [3] Genero" +
                            "\n    [4] Id" +
                            "\n    [5] Estado Civil" +
                            "\n    [6] Pais Nascimento" +
                            "\n    [7] Contacto" +
                            "\n    [8] Nee" +
                            "\nReply:");
                    option = Byte.parseByte(x.readLine());

                    switch (option) {
                        case (byte) 1:
                            nome = requisita_nome_completo();
                            pessoa.setNome(nome);
                            regista_ficheiro();
                            break;

                        case (byte) 2:
                            data_nascimento = requisita_data_nascimento();
                            pessoa.setData_nascimento(data_nascimento);
                            regista_ficheiro();
                            break;

                        case (byte) 3:
                            genero = auth.genderMethod();
                            pessoa.setGenero(genero);
                            regista_ficheiro();
                            break;

                        case (byte) 4:
                            id = auth.authentify_String((byte) 5, (byte) 15, "Nr.Identificacao");
                            pessoa.setId(id);
                            regista_ficheiro();
                            break;

                        case (byte) 5:
                            estado_civil = auth.estadoCivil();
                            pessoa.setEstado_civil(estado_civil);
                            regista_ficheiro();
                            break;

                        case (byte) 6:
                            pais_nascimento = auth.authentify_String((byte) 3, (byte) 15, "Pais de Nascimento");
                            pessoa.setPais_nascimento(pais_nascimento);
                            regista_ficheiro();
                            break;

                        case (byte) 7:
                            contacto = auth.authentify_String((byte) 9, (byte) 9, "Contacto");
                            pessoa.setContacto(contacto);
                            regista_ficheiro();
                            break;

                        case (byte) 8:

                            nee = auth.neeMethod();
                            candidato.setNee(nee);
                            regista_ficheiro();
                            break;


                        default:
                            System.out.println("Invalido");
                    }
                    System.out.print("\n      Deseja alterar alguma outra informacao?" +
                                     "\n              [1] SIM     [2] NAO      " +
                                     "\n  Reply:");
                    choice = Byte.parseByte(x.readLine());

                    if (choice == (byte) 1)
                        update(usernameCredentials);
                } else {
                    System.out.println("NUMERO DE IDENTIFICACAO NAO ACHADO\nVERIFIQUE E TENTE NOVAMENTE");
                }
            }
        }
        percorre_ficheiro();
        getMyInfo(user);
    }

    public void print(){
        Candidato candidato;

        System.out.printf("\n%s %s %36s %s\n","Nome completo:",pessoa.getNome(),"Data de Nascimento:",pessoa.getData_nascimento());
        System.out.printf("%s %s %52s %s\n","Genero       :",pessoa.getGenero(),"Pais de Nascimento:",pessoa.getPais_nascimento());
        System.out.printf("%s %s %41s %s\n","Nr.Id        :",pessoa.getId(),"Estado Civil:",pessoa.getEstado_civil());

        if(pessoa instanceof Candidato) {
            candidato = (Candidato) pessoa;
            System.out.printf("%s %s %29s %s\n", "Contacto     :", pessoa.getContacto(), "Nee:", candidato.getNee());
            System.out.printf("%s %s %47s %s\n", "Curso        :", candidato.getCurso(), "Disciplinas exame:", candidato.getDisciplinas_exame());
        }
    }

    public boolean verificarCredenciaisCandidato(String user, String pass){

        percorre_ficheiro();
        Candidato candidato;
        boolean status = false;

        for(int i=0;i<vector_ref.size();i++){

            pessoa = (Pessoa) vector_ref.elementAt(i);

            if(pessoa instanceof Candidato){
                candidato = (Candidato) pessoa;
                if(user.equalsIgnoreCase(""+candidato.getUsername()) && pass.equalsIgnoreCase(""+candidato.getPassword())){
                    arq.regista_credenciais_candidato(user,pass);
                    status = true;
                }
            }
        }

        return status;
    }

    public void rootAccess(){

        StringTokenizer str;
        String s;
        try {
            FileReader fr = new FileReader("Candidate_loginCredentials.txt");
            BufferedReader frd = new BufferedReader(fr);

            while ((s=frd.readLine())!=null){
                str = new StringTokenizer(s,"-");

                usernameCredentials = str.nextToken();
                passwordCredential = str.nextToken();
            }
            frd.close();
        }catch(FileNotFoundException a){
            System.out.println("FICHEIRO NAO ENCONTRADO");
        }catch(IOException b){
            System.out.println(b.getMessage());
        }
    }

    public void permiteMinhasInformacoes() {

        percorre_ficheiro();
        Candidato candidato;
        rootAccess();
        System.out.println("Username:"+ usernameCredentials +"\tPass:"+ passwordCredential);
        for (int i = 0; i < vector_ref.size(); i++) {
            pessoa = (Pessoa) vector_ref.elementAt(i);
            if (pessoa instanceof Candidato) {
                candidato = (Candidato) pessoa;
                if (usernameCredentials.equalsIgnoreCase("" + candidato.getUsername()) && passwordCredential.equalsIgnoreCase("" + candidato.getPassword())) {
                    getMyInfo(usernameCredentials);
                }
            }
        }
    }

    public void permiteUpdate() throws IOException {
        percorre_ficheiro();
        Candidato candidato;
        rootAccess();
        for (int i=0;i<vector_ref.size();i++) {
            pessoa = (Pessoa) vector_ref.elementAt(i);
            if (pessoa instanceof Candidato) {
                candidato = (Candidato) pessoa;
                if (usernameCredentials.equalsIgnoreCase("" + candidato.getUsername()) && usernameCredentials.equalsIgnoreCase("" + candidato.getPassword())) {
                    update(usernameCredentials);
                }
            }
        }
    }

    public void estadoAdmissao(){

        Candidato candidato;
        percorre_ficheiro();
        rootAccess();
        float resultado;

        for(int i=0;i<vector_ref.size();i++){
            pessoa = (Pessoa) vector_ref.elementAt(i);
            if(pessoa instanceof Candidato){
                candidato = (Candidato) pessoa;
                if((candidato.getNotaExame1()!=0f) && (candidato.getNotaExame2()!= 0f)){
                    if(candidato.getCurso().equalsIgnoreCase("Informatica")){
                        System.out.println("Matematica:"+candidato.getNotaExame1()+"    |   Portugues:"+candidato.getNotaExame2());
                        resultado = (candidato.getNotaExame1()*0.7f) + (candidato.getNotaExame2()*0.3f);
                        System.out.println("Media = "+resultado);
                        if(resultado>13 && resultado<21)
                            System.out.println("Admitido");
                        else if(resultado >9 && resultado< 14)
                            System.out.println("Repescagem");
                        else
                            System.out.println("Nao Admitido");
                    }else if(candidato.getCurso().equalsIgnoreCase("Engenharia Informatica")) {

                        if (candidato.getCurso().equalsIgnoreCase("Informatica")) {
                            System.out.println("Matematica:" + candidato.getNotaExame1() + "    |   Fisica:" + candidato.getNotaExame2());
                            resultado = (candidato.getNotaExame1() * 0.5f) + (candidato.getNotaExame2() * 0.5f);
                            System.out.println("Media = " + resultado);
                            if (resultado > 13 && resultado < 21)
                                System.out.println("Admitido");
                            else if (resultado > 9 && resultado < 14)
                                System.out.println("Repescagem");
                            else
                                System.out.println("Nao Admitido");
                        }
                    }
                }else{
                    System.out.println("RESULTADOS INDISPONIVEIS");
                }
            }
        }
    }

    public void apagarCandidatura() throws IOException {

        percorre_ficheiro();
        Candidato candidato;
        rootAccess();
        byte reply;

        for (int i = 0; i < vector_ref.size(); i++) {

            pessoa = (Pessoa) vector_ref.elementAt(i);

            if (pessoa instanceof Candidato) {
                candidato = (Candidato) pessoa;

                if (usernameCredentials.equalsIgnoreCase("" + candidato.getUsername()) && passwordCredential.equalsIgnoreCase("" + candidato.getPassword())) {
                    System.out.println("\n\tDESEJA ELIMINAR A CANDIDATURA\nNome candidato: "+pessoa.getNome()+"\tId:"+pessoa.getId()+"\n\t[1] - SIM\t[2] - NAO\nReply:");
                    reply = Byte.parseByte(x.readLine());
                    if (reply == (byte) 1) {

                        vector_ref.removeElementAt(i);

                        System.out.println("===CANDIDATURA ELIMINADA===");
                    } else {
                        System.out.println("OPERACAO CANCELADA");
                    }
                    regista_ficheiro();
                }
            }
        }
    }
}
