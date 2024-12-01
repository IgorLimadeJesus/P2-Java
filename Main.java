import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

         Scanner scanner = new Scanner(System.in);
        Clientes clientes = new Clientes();
        Veiculos veiculos = new Veiculos();

        int escolha;

        
        do {
            System.out.println("Escolha o que deseja fazer: \n1. Cadastrar Clientes. \n2. Cadastrar Veículos. \n3. Listar Veículos. \n4. Atualizar Cliente. \n5. Carregar Cliente. \n6. Atualizar Veiculo. \n7. Sair.");
            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    clientes.CadastrarClientes();
                    break;
                case 2:
                    veiculos.CadastrarVeiculos();
                    break;
                case 3:
                    veiculos.ListarVeiculos();
                    break;
                case 4:
                    clientes.AtualizarCliente();
                    break;
                case 5:
                    clientes.CarregarCliente();
                    break;
                case 6:
                    veiculos.AtualizarVeiculo();
                    break;
                case 7:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (escolha != 7);  

        scanner.close();
    }
}
