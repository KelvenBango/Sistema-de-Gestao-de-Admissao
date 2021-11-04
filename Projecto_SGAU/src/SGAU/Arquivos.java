package SGAU;
import java.io.*;
import java.util.StringTokenizer;

public class Arquivos {

    String error_message = "FICHEIRO NAO ENCONTRADO";

    public void regista_credenciais_candidato(String userName,String password){

        try {
            FileWriter fw = new FileWriter("Candidate_loginCredentials.txt");
            BufferedWriter fwd = new BufferedWriter(fw);

            fwd.write(userName+"-"+password);
            fwd.newLine();
            fwd.close();

        }catch(FileNotFoundException a){
            System.out.println(error_message);
        }catch(IOException b){
            System.out.println(b.getMessage());
        }
    }

    public void regista_credenciais_admin(){

        try {
            FileWriter fw = new FileWriter("Admin_loginCredentials.txt");
            BufferedWriter fwd = new BufferedWriter(fw);

            fwd.write("Admin-admin");
            fwd.newLine();
            fwd.close();

        }catch(FileNotFoundException a){
            System.out.println(error_message);
        }catch(IOException b){
            System.out.println(b.getMessage());
        }
    }

    public boolean verifica_credenciais_admin(String userName,String password){

        regista_credenciais_admin();
        StringTokenizer str;
        String s,user,pass;
        boolean status= false;
        try {
            FileReader fr = new FileReader("Admin_loginCredentials.txt");
            BufferedReader frd = new BufferedReader(fr);

            while ((s=frd.readLine())!=null){
                str = new StringTokenizer(s,"-");

                user = str.nextToken();
                pass = str.nextToken();

                if(user.equalsIgnoreCase(userName) && pass.equalsIgnoreCase(password))
                    status = true;
            }
            frd.close();
        }catch(FileNotFoundException a){
            System.out.println(error_message);
        }catch(IOException b){
            System.out.println(b.getMessage());
        }
        return status;
    }

}
