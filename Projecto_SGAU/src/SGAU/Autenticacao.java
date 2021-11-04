package SGAU;

import java.io.*;

public class Autenticacao {

    BufferedReader x = new BufferedReader(new InputStreamReader(System.in));

    public String authentify_String(byte lower,byte higher,String prompt) throws IOException {

        String arg;
        do{
            System.out.print(prompt+":");
            arg = x.readLine();

            if(arg.length()<lower || arg.length() > higher)
                System.out.println("\nError\nArgumento invalido\nVerifique e tente novamente");
        }while (arg.length()<lower || arg.length() > higher);
        return  arg;
    }

    public byte authentify_Byte(byte lower,byte higher,String prompt) throws IOException {

        byte arg;
        do{
            System.out.print(prompt+":");
            arg = Byte.parseByte(x.readLine());

            if(arg<lower || arg > higher)
                System.out.println("\nError\nArgumento invalido\nVerifique e tente novamente");
        }while (arg<lower || arg > higher);
        return  arg;
    }

    public short authentify_Short(short higher,String prompt) throws IOException {
        short arg;
        do{
            System.out.print(prompt+":");
            arg = Short.parseShort(x.readLine());

            if(arg > higher)
                System.out.println("\nError\nArgumento invalido\nVerifique e tente novamente");
        }while (arg > higher);
        return  arg;
    }

    public char genderMethod() throws IOException {
        char arg;

        do {
            System.out.print("Genero [M/F]:");
            arg = x.readLine().toUpperCase().charAt(0);

            if (arg != 'M' && arg != 'F')
                System.out.println("Genero desconhecido!");
        }while (arg != 'M' && arg != 'F');
        return arg;
    }

    public String estadoCivil() throws IOException {
        String arg="";
        byte choice;

        System.out.print("\n\tEstado civil\n[1]-Solteiro(a)\n[2]-Casado(a)\n[3]-Divorciado(a)\n[4]-Viuvo(a)\nReply:");
        choice = Byte.parseByte(x.readLine());

        switch (choice){
            case 1: arg = "Solteiro(a)";break;
            case 2: arg = "Casado(a)";break;
            case 3: arg = "Divorciado(a)";break;
            case 4: arg = "Viuvo(a)";break;
            default:
                System.out.println("Error\nOpcao invalida");
        }
        return arg;
    }

    public String cursoMenu() throws IOException {
        String arg="";
        byte choice;

        System.out.print("\n\tCursos disponiveis\n  [1] Engenharia Informatica\n  [2] Informatica]\nReply:");
        choice = Byte.parseByte(x.readLine());

        switch (choice){
            case 1: arg = "Engenharia Informatica";break;

            case 2: arg = "Informatica";break;

            default:
                System.out.println("Error\nOpcao invalida");
        }
        return arg;
    }

    public char neeMethod() throws IOException {
        char arg;

            System.out.print("\n\tNecessidade de educacao especial\n[S/N]\nReply:");
            arg = x.readLine().toUpperCase().charAt(0);

            if(arg == 'S')
                arg = 'S';
            else if(arg == 'N')
                arg = 'N';
            else
                System.out.println("Invalido");
        return arg;
    }
}
