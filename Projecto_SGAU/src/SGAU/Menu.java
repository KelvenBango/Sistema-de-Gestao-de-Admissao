package SGAU;
import java.io.*;
public class Menu {

    BufferedReader x = new BufferedReader(new InputStreamReader(System.in));
    Autenticacao auth = new Autenticacao();
    Arquivos arq_ref = new Arquivos();
    Tarefas tarefas;
    String error_message = "\nINVALIDO\nVERIFIQUE E TENTE NOVAMENTE\n";

    public void menu() throws IOException {

        logo();
        menu_inicial();
    }

    public void logo(){
        byte i=0,j=0,l=0,m=0;
        while(l<55){
            System.out.print("-");
            l++;
        }
        System.out.println("\n                BEM-VINDO AO: ");
        while (m<55){
            System.out.print("-");
            m++;
        }
        System.out.println();
        while(i<55){
            System.out.print("-");
            i++;
        }
        System.out.println( "\n        S I S T E M A _D E_ G E S T A O"+
                            "\n                     D E"+
                            "\n               A D M I S S A O");
        while (j<55){
            System.out.print("-");
            j++;
        }
    }

    public void menu_inicial() throws IOException {

        byte choice;
        System.out.print("\n    [1] Entrar"+
                         "\n    [2] Registar"+
                         "\n    [3] Ajuda"+
                         "\n    [4] Sair"+
                         "\nReply:");
        try {
            choice = Byte.parseByte(x.readLine());
            switch (choice){
                case 1: login();break;
                case 2: registar_Candidato();break;
                case 3: helpMethod(); break;
                case 4: System.out.println("Saiu"); break;
            }
        }catch (NumberFormatException a){
            System.out.println(error_message);
            menu();
        }
    }

    public void helpMethod() throws IOException {
        String arg;
        System.out.println("\nIntroduza as suas credenciais para autenticar clicando na opcao 1\nou\nregiste-se clicando na opcao 2");
        System.out.print("Pressione ENTER para voltar ... ");
        arg = x.readLine();

        if(arg.equalsIgnoreCase("")) {
            System.out.println("\n\n");
            menu();
        }
    }

    public void login()throws IOException{
        byte choice,l=0,m=0;
        while(l<55){
            System.out.print("-");
            l++;
        }
        System.out.println("\n                      LOGIN          ");
        while (m<55){
            System.out.print("-");
            m++;
        }
        System.out.print("\n    [1] Administrador"+
                         "\n    [2] Candidato"+
                         "\n    [3] Voltar"+
                         "\nReply:");
        try {
            choice = Byte.parseByte(x.readLine());
            switch (choice){
                case 1: administrador_Autenticacao();break;
                case 2: candidato_Autenticacao();break;
                case 3: menu();break;

                default:
                    System.out.println("Opcao invalida!");
            }
        }catch (NumberFormatException a){
            System.out.println(error_message);
            login();
        }
    }

    public void administrador_Autenticacao() throws IOException {

        String nome,password;
        System.out.println("\n          Autenticacao do Administrador");
        System.out.print("          -Nome:");
        nome = x.readLine();
        System.out.print("          -Palavra-passe:");
        password = x.readLine();

        if(arq_ref.verifica_credenciais_admin(nome,password)){
            menu_Administrador();
        }else{
            System.out.println("CREDENCIAIS INVALIDAS\nTENTE NOVAMENTE\n");
            administrador_Autenticacao();
        }

    }

    public void candidato_Autenticacao() throws IOException {

        String user,pass,arg;
        System.out.println("\n          Autenticacao do Candidato");
        System.out.print("          -Nome de utilizador:");
        user = x.readLine();
        System.out.print("          -Palavra-passe:");
        pass = x.readLine();

        if(tarefas.verificarCredenciaisCandidato(user,pass)){
            menu_candidato();
        }else{
            System.out.println("NAO EXISTE CANDIDATO COM ESSAS CREDENCIAIS\nQUER REGISTAR-SE?\nPRESSIONE 'ENTER' PARA REGISTAR\nOU\n'#' PARA TENTAR NOVAMENTE");
            arg = x.readLine();
            if(arg.equalsIgnoreCase(""))
                registar_Candidato();
            else if(arg.equalsIgnoreCase("#"))
                candidato_Autenticacao();
        }
    }

    public void registar_Candidato() throws IOException {
        tarefas = new Tarefas();
        String arg;
        System.out.println("\nNesta seccao sera solicitado a escolher um nome de usuario e uma palavra-passe"+
                "\nNote que os mesmos serao solicitados para entrar na plataforma\nPressione ENTER para continuar\nou\n# Para voltar a pagina inicial...");
        arg = x.readLine();
        if(arg.equalsIgnoreCase("#"))
            menu();
        System.out.println("\nRegistando novo utilizador");
        tarefas.dados_candidato();
    }
/*
    public void menu_registo()throws IOException{

        tarefas = new Tarefas();
        String user,password;
        byte k=0,l=0;
        user = auth.authentify_String((byte) 3,(byte) 20,"Nome do utilizador");
        password = auth.authentify_String((byte) 8,(byte) 16,"Palavra-passe (8 caracteres no minimo)");
        tarefas.configCredenciaisCandidato(user,password);
        System.out.println("*** Salvo...");

        while (k<55){
            System.out.print("-");
            k++;
        }
        System.out.println("\n              PROCESSO DE CANDIDATURA:");
        while (l<55){
            System.out.print("-");
            l++;
        }
        tarefas.dados_candidato();

    }

 */

    public void menu_candidato()throws IOException{

        tarefas = new Tarefas();

        byte choice,i=0,j=0;
        while (i<55){
            System.out.print("-");
            i++;
        }
        System.out.println("\n              SEJA BEM-VINDO CANDIDATO");
        while (j<55){
            System.out.print("-");
            j++;
        }

        System.out.print(
                          "\n   [1] Minhas informacaoes"+
                          "\n   [2] Actualizar minhas informacoes"+
                          "\n   [3] Estado de admissao" +
                          "\n   [4] Apagar candidatura" +
                          "\n   [5] Alterar palavra-passe" +
                          "\n   [6] Terminar Seccao"+
                          "\nReply:");
        try{

            choice = Byte.parseByte(x.readLine());

            switch (choice){

                case 1:
                    tarefas.permiteMinhasInformacoes();
                        System.out.print("\n**[0] Voltar\n**[1] Terminar seccao\nReply:");
                        try {
                            byte ans = Byte.parseByte(x.readLine());

                            if (ans == (byte) 0)
                                menu_candidato();
                            else if (ans == (byte) 1)
                                menu();
                            else
                                menu();

                        } catch (NumberFormatException a) {
                            System.out.println(error_message);
                        }
                    break;

                case 2: tarefas.permiteUpdate();
                        System.out.print("\n    [0] Voltar\n    [1] Terminar seccao\nReply:");
                        byte escolha = Byte.parseByte(x.readLine());
                        if(escolha == (byte) 0)
                            menu_candidato();
                        else if(escolha == (byte) 1)
                            menu();
                        else {
                            System.out.println("ESCOLHA INVALIDA\nTERMINANDO SECCAO");
                            menu();
                        }
                        break;

                case 3: tarefas.estadoAdmissao();break;

                case 4: tarefas.apagarCandidatura();
                        menu();
                    break;

                case 5: menu(); break;
                    default:
                    System.out.println("Opcao invalida");
            }
        }catch(NumberFormatException a){
            System.out.println("Ocorreu uma excepcao\nVerifique e tente novamente");
            menu_candidato();
        }
    }

    /*public void minhas_informacoes() throws IOException {

        String id;
        System.out.print("Informe o seu id:");
        id = x.readLine();
        System.out.println(tarefas.getMyInfo(id));
    }
     */

    public void menu_Administrador()throws IOException {

        byte choice,i=0,j=0;
        tarefas = new Tarefas();

        while (i<55){
            System.out.print("-");
            i++;
        }
        System.out.println("\n              SEJA BEM-VINDO ADMINISTRADOR");
        while (j<55){
            System.out.print("-");
            j++;
        }

        System.out.print("\n    [1] Visualizar candidatos" +
                         "\n    [2] Quantidade de candidatos" +
                         "\n    [3] Apagar Candadidato" +
                         "\n    [4] Terminar Seccao"+
                         "\nReply:");

        try {
            choice = Byte.parseByte(x.readLine());

            switch (choice){

                case 1: visualizar_candidatos(); break;

                case 2: qtd_candidatos(); break;

                case 3: tarefas.deleteCandidate();
                    menu_Administrador();break;

                case 4: menu(); break;

                default:
                    System.out.println("Opcao invalida");
            }
        } catch (NumberFormatException a) {
            System.out.println(error_message);
            menu_Administrador();
        }
    }

    public void qtd_candidatos() throws IOException {

        byte k=0,l=0;

        while (k<55){
            System.out.print("-");
            k++;
        }
        System.out.println("\n              QUANRIDADE DE CANDIDATOS POR CURSO:");
        while (l<55){
            System.out.print("-");
            l++;
        }

        System.out.println( "\nEngenharia Informatica  : "+Candidato.qtd_engInformatica+
                            "\nInformatica             : "+Candidato.qtd_Informatica);
        System.out.println("\n__________________________________");
        System.out.println("VISUALIZACAO GRAFICA DOS DADOS   |");
        System.out.println("----------------------------------");
        byte i=0,j=0;
        System.out.print("Eng.Informatica |");
        while(i<Candidato.qtd_engInformatica){
            System.out.print("-");
            i++;
        }

        System.out.print("\nInformatica     |");
        while(j<Candidato.qtd_Informatica){
            System.out.print("-");
            j++;
        }
        System.out.println("\n__________________________________");
        System.out.println("TOTAL CANDIDATOS: "+Candidato.total_candidados()+"");
        System.out.println("----------------------------------");

        System.out.print("    [0] Voltar\n    [1] Terminar seccao\nReply:");
        byte choice = Byte.parseByte(x.readLine());
        if (choice == (byte) 0)
            menu_Administrador();
        else if (choice == (byte) 1)
            menu();
        else {
            System.out.println("Invalido");
            qtd_candidatos();
        }
    }

    public void visualizar_candidatos() throws IOException {
        byte choice,i=0,j=0,ans;
        String curso;
        while (i<55){
            System.out.print("-");
            i++;
        }
        System.out.println("\n              VISUALIZAR CANDIDATO");
        while (j<55){
            System.out.print("-");
            j++;
        }

        System.out.print("  \n[1] Visualizar por curso"+
                         "  \n[2] Visualizar todos os candidatos\nReply:");
        choice = Byte.parseByte(x.readLine());
        switch (choice){
            case 1:
                curso = auth.cursoMenu();
                System.out.printf(tarefas.getInfo_byCourse(curso));
                System.out.print("[0] Voltar\n[1] Terminar seccao\nReply:");
                ans = Byte.parseByte(x.readLine());

                if(ans == (byte) 0)
                    menu_Administrador();
                else if(ans == (byte) 1)
                    menu();
                else {
                    System.out.println(error_message);
                }
                break;
            case 2:
                System.out.println(tarefas.toString());
                System.out.print("[0] Voltar\n[1] Terminar seccao\nReply:");
                ans = Byte.parseByte(x.readLine());
                if(ans == (byte) 0)
                    menu_Administrador();
                else if(ans == (byte) 1)
                    menu();
                break;

            default:
                System.out.println(error_message);
                visualizar_candidatos();
        }
    }

    public void manipula_qtdCandidatos(){

        tarefas = new Tarefas();
        tarefas.qtd_candidatos();
    }

}
