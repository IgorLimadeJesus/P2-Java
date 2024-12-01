import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Clientes clientes = new Clientes();
        Veiculos veiculos = new Veiculos();

        int escolha;

        System.out.println("Escolha oq deseja fazer: \n1.Cadastrar Clientes. \n2.Cadastrar Veiculos.");
        escolha = scanner.nextInt();
        
        switch (escolha) {
            case 1:
                clientes.CadastrarClientes();
                break;
            case 2:
                veiculos.CadastrarVeiculos();
                break;
            default:
                break;
        }

        scanner.close();
    }
}
