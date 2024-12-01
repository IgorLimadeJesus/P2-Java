import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Clientes {
    private int ClienteId;
    private String Nome;
    private String CPF;
    private String Telefone;
    private int VeiculoId;
    private String Data;

    public void setClienteId(int ClienteId) { this.ClienteId = ClienteId; }
    public int getClienteId() { return ClienteId; }
    public void setNome(String Nome) { this.Nome = Nome; }
    public String getNome() { return Nome; }
    public void setCPF(String CPF) { this.CPF = CPF; }
    public String getCPF() { return CPF; }
    public void setTel(String Telefone) { this.Telefone = Telefone; }
    public String getTel() { return Telefone; }
    public void setVeiculoId(int VeiculoId) { this.VeiculoId = VeiculoId; }
    public int getVeiculoId() { return VeiculoId; }
    public void setData(String Data) { this.Data = Data; }
    public String getData() { return Data; }

    private static final String CLIENTES_FILE_PATH = "clientes/clientes.txt";
    private static final String VEICULOS_FILE_PATH = "veiculos/veiculos.txt";

    public void CadastrarClientes() {
        Scanner scanner = new Scanner(System.in);
        Clientes cliente = new Clientes();

        System.out.println("Digite o Nome, Telefone, CPF, ID do Veículo e Data da compra:");
        cliente.setNome(scanner.nextLine());
        cliente.setTel(scanner.nextLine());
        cliente.setCPF(scanner.nextLine());
        int veiculoId = scanner.nextInt();
        cliente.setVeiculoId(veiculoId);
        scanner.nextLine();
        cliente.setData(scanner.nextLine());

        if (VerificarVeiculoNoArquivo(veiculoId)) {
            cliente.setClienteId(GerarIdUnicoCliente());

            SalvarCliente(cliente);
            System.out.println("Cliente salvo com sucesso!");
        } else {
            System.out.println("Veículo não cadastrado. Cadastro não realizado.");
        }
    }

    private int GerarIdUnicoCliente() {
        int idCliente = 1;

        List<Clientes> clientes = CarregarTodosClientes();
        if (!clientes.isEmpty()) {
            idCliente = clientes.get(clientes.size() - 1).getClienteId() + 1;
        }

        return idCliente;
    }

    public void CarregarCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o Nome do Cliente: ");
        String nomeCliente = scanner.nextLine();

        List<Clientes> clientes = CarregarTodosClientes();
        boolean encontrado = false;

        for (Clientes cliente : clientes) {
            if (cliente.getNome().equalsIgnoreCase(nomeCliente)) {
                System.out.println("ID Cliente: " + cliente.getClienteId());
                System.out.println("Nome: " + cliente.getNome());
                System.out.println("Telefone: " + cliente.getTel());
                System.out.println("CPF: " + cliente.getCPF());
                System.out.println("ID do Veículo: " + cliente.getVeiculoId());
                System.out.println("Data: " + cliente.getData());
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Cliente não encontrado.");
        }
    }

    private boolean VerificarVeiculoNoArquivo(int veiculoId) {
        File pastaVeiculos = new File("veiculos");
        if (!pastaVeiculos.exists()) {
            System.out.println("A pasta de veículos não existe.");
            return false;
        }

        File file = new File(pastaVeiculos, "veiculos.txt");
        if (!file.exists()) {
            System.out.println("Arquivo de veículos não encontrado.");
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("ID: ") && Integer.parseInt(line.substring(4)) == veiculoId) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private void SalvarCliente(Clientes cliente) {
        File pasta = new File("clientes");
        if (!pasta.exists()) {
            pasta.mkdir();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CLIENTES_FILE_PATH, true))) {
            writer.write("ID Cliente: " + cliente.getClienteId());
            writer.newLine();
            writer.write("Nome: " + cliente.getNome());
            writer.newLine();
            writer.write("Telefone: " + cliente.getTel());
            writer.newLine();
            writer.write("CPF: " + cliente.getCPF());
            writer.newLine();
            writer.write("ID do Veículo: " + cliente.getVeiculoId());
            writer.newLine();
            writer.write("Data: " + cliente.getData());
            writer.newLine();
            writer.write("--------------------");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Clientes> CarregarTodosClientes() {
        List<Clientes> clientes = new ArrayList<>();
        File file = new File(CLIENTES_FILE_PATH);

        if (!file.exists()) {
            return clientes;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(CLIENTES_FILE_PATH))) {
            Clientes cliente = null;
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("ID Cliente: ")) {
                    cliente = new Clientes();
                    cliente.setClienteId(Integer.parseInt(line.substring(12)));
                } else if (line.startsWith("Nome: ")) {
                    if (cliente != null) cliente.setNome(line.substring(6));
                } else if (line.startsWith("Telefone: ")) {
                    if (cliente != null) cliente.setTel(line.substring(10));
                } else if (line.startsWith("CPF: ")) {
                    if (cliente != null) cliente.setCPF(line.substring(5));
                } else if (line.startsWith("ID do Veículo: ")) {
                    if (cliente != null) cliente.setVeiculoId(Integer.parseInt(line.substring(15)));
                } else if (line.startsWith("Data: ")) {
                    if (cliente != null) cliente.setData(line.substring(6));
                } else if (line.equals("--------------------")) {
                    if (cliente != null) clientes.add(cliente);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    public void AtualizarCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o ID do Cliente que deseja atualizar: ");
        int clienteId = scanner.nextInt();
        scanner.nextLine();
    
        List<Clientes> clientes = CarregarTodosClientes();
        boolean encontrado = false;
    
        for (Clientes cliente : clientes) {
            if (cliente.getClienteId() == clienteId) {
                System.out.println("Cliente encontrado. Digite as novas informações:");
    
                System.out.print("Nome (atual: " + cliente.getNome() + "): ");
                cliente.setNome(scanner.nextLine());
    
                System.out.print("Telefone (atual: " + cliente.getTel() + "): ");
                cliente.setTel(scanner.nextLine());
    
                System.out.print("CPF (atual: " + cliente.getCPF() + "): ");
                cliente.setCPF(scanner.nextLine());
    
                System.out.print("ID do Veículo (atual: " + cliente.getVeiculoId() + "): ");
                cliente.setVeiculoId(scanner.nextInt());
                scanner.nextLine();
    
                System.out.print("Data da compra (atual: " + cliente.getData() + "): ");
                cliente.setData(scanner.nextLine());
    
                SalvarClientesAtualizados(clientes);
                System.out.println("Informações do cliente atualizadas com sucesso.");
                encontrado = true;
                break;
            }
        }
    
        if (!encontrado) {
            System.out.println("Cliente não encontrado.");
        }
    }
    
    private void SalvarClientesAtualizados(List<Clientes> clientes) {
        File file = new File(CLIENTES_FILE_PATH);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Clientes cliente : clientes) {
                writer.write("ID Cliente: " + cliente.getClienteId());
                writer.newLine();
                writer.write("Nome: " + cliente.getNome());
                writer.newLine();
                writer.write("Telefone: " + cliente.getTel());
                writer.newLine();
                writer.write("CPF: " + cliente.getCPF());
                writer.newLine();
                writer.write("ID do Veículo: " + cliente.getVeiculoId());
                writer.newLine();
                writer.write("Data: " + cliente.getData());
                writer.newLine();
                writer.write("--------------------");
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void MostrarTodosClientes() {
        List<Clientes> clientes = CarregarTodosClientes();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Clientes cliente : clientes) {
                System.out.println("ID Cliente: " + cliente.getClienteId());
                System.out.println("Nome: " + cliente.getNome());
                System.out.println("Telefone: " + cliente.getTel());
                System.out.println("CPF: " + cliente.getCPF());
                System.out.println("ID do Veículo: " + cliente.getVeiculoId());
                System.out.println("Data: " + cliente.getData());
                System.out.println("--------------------");
            }
        }
    }
}
