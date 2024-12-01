import java.io.*;
import java.util.Scanner;

public class Veiculos {
    private String Modelo;
    private String Marca;
    private float Valor;
    private int id;

    public void setModelo(String Modelo) { this.Modelo = Modelo; }
    public String getModelo() { return Modelo; }
    public void setMarca(String Marca) { this.Marca = Marca; }
    public String getMarca() { return Marca; }
    public void setValor(float Valor) { this.Valor = Valor; }
    public float getValor() { return Valor; }
    public void setId(int id) { this.id = id; }
    public int getId() { return id; }

    private static final String FILE_PATH = "veiculos/veiculos.txt";

    public void CadastrarVeiculos() {
        Scanner scanner = new Scanner(System.in);
        Veiculos veiculo = new Veiculos();

        int idVeiculo = GerarIdUnico();
        veiculo.setId(idVeiculo);

        System.out.println("Digite o Modelo:");
        veiculo.setModelo(scanner.nextLine());
        System.out.println("Digite a Marca:");
        veiculo.setMarca(scanner.nextLine());
        System.out.println("Digite o Valor:");
        veiculo.setValor(scanner.nextFloat());

        File file = new File("veiculos");
        if (!file.exists()) {
            file.mkdir();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write("ID: " + veiculo.getId());
            writer.newLine();
            writer.write("Modelo: " + veiculo.getModelo());
            writer.newLine();
            writer.write("Marca: " + veiculo.getMarca());
            writer.newLine();
            writer.write("Valor: R$" + veiculo.getValor());
            writer.newLine();
            writer.write("------------------------------");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Veículo salvo com sucesso!");
    }

    private int GerarIdUnico() {
        int id = 1;
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("ID: ")) {
                        int lastId = Integer.parseInt(line.substring(4));
                        id = lastId + 1;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    public void ListarVeiculos() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void AtualizarVeiculo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o ID do veículo que deseja atualizar: ");
        int veiculoId = scanner.nextInt();
        scanner.nextLine();

        File file = new File(FILE_PATH);
        StringBuilder fileContent = new StringBuilder();
        boolean veiculoEncontrado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            Veiculos veiculoAtualizado = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("ID: ")) {
                    int idVeiculo = Integer.parseInt(line.substring(4));
                    if (idVeiculo == veiculoId) {
                        veiculoEncontrado = true;
                        veiculoAtualizado = new Veiculos();
                        veiculoAtualizado.setId(idVeiculo);

                        System.out.println("Veículo encontrado. Digite as novas informações:");

                        System.out.print("Modelo (atual: " + line.substring(7) + "): ");
                        veiculoAtualizado.setModelo(scanner.nextLine());

                        System.out.print("Marca (atual: " + reader.readLine().substring(7) + "): ");
                        veiculoAtualizado.setMarca(scanner.nextLine());

                        System.out.print("Valor (atual: " + reader.readLine().substring(7) + "): ");
                        veiculoAtualizado.setValor(scanner.nextFloat());
                        scanner.nextLine();

                        fileContent.append("ID: ").append(veiculoAtualizado.getId()).append("\n");
                        fileContent.append("Modelo: ").append(veiculoAtualizado.getModelo()).append("\n");
                        fileContent.append("Marca: ").append(veiculoAtualizado.getMarca()).append("\n");
                        fileContent.append("Valor: R$").append(veiculoAtualizado.getValor()).append("\n");
                        fileContent.append("------------------------------\n");

                    } else {
                        fileContent.append(line).append("\n");
                    }
                } else {
                    fileContent.append(line).append("\n");
                }
            }

            if (!veiculoEncontrado) {
                System.out.println("Veículo não encontrado.");
                return;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(fileContent.toString());
            }

            System.out.println("Veículo atualizado com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
