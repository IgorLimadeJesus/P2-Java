import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Clientes {
    private String Nome;
    private String CPF;
    private String Telefone;
    private String Veiculo;
    private String Data;

    public void setNome(String Nome){
        this.Nome = Nome;
    }

    public String getNome(){
        return Nome;
    }

    public void setCPF(String CPF){
        this.CPF = CPF;
    }

    public String getCPF(){
        return CPF;
    }

    public void setTel(String Telefone){
        this.Telefone = Telefone;
    }

    public String getTel(){
        return Telefone;
    }

    public void setVeiculo(String Veiculo){
        this.Veiculo = Veiculo;
    }

    public String getVeiculo(){
        return Veiculo;
    }

    public void setData(String Data){
        this.Data = Data;
    }

    public String getData(){
        return Data;
    }

    public void CadastrarClientes(){
        Scanner scanner = new Scanner(System.in);
        Clientes cliente = new Clientes();

        String pasta = "clientes";

        File file =  new File(pasta);
        if (!file.exists()) {
            file.mkdir();
        }


        System.out.println("Digite o Nome\n Numero de Telefone\n CPF\n Veiculo Comprado \n Data da compra");
        cliente.setNome(scanner.nextLine());
        cliente.setTel(scanner.nextLine());
        cliente.setCPF(scanner.nextLine());
        cliente.setVeiculo(scanner.nextLine());
        cliente.setData(scanner.nextLine());

        String dir = pasta + File.separator + cliente.getNome();
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dir))){
            writer.write("Nome: " + cliente.getNome());
            writer.newLine();
            writer.write("Telefone: " + cliente.getTel());
            writer.newLine();
            writer.write("CPF: " + cliente.getCPF());
            writer.newLine();
            writer.write("Veiculo: " + cliente.getVeiculo());
            writer.newLine();
            writer.write("Data: " + cliente.getData());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Salvo com sucesso");

        scanner.close();
    }

}
