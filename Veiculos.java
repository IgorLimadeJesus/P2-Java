import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Veiculos {
    private String Modelo;
    private String Marca;
    private Float Valor;

    public void setModelo(String Modelo){
        this.Modelo = Modelo;
    }

    public String getModelo(){
        return Modelo;
    }

    public void setMarca(String Marca){
        this.Marca = Marca;
    }

    public String getMarca(){
        return Marca;
    }

    public void setValor(float Valor){
        this.Valor = Valor;
    }

    public float getValor(){
        return Valor;
    }

    public void CadastrarVeiculos(){
        Scanner scanner = new Scanner(System.in);

        String pasta = "veiculos";

        File file =  new File(pasta);
        if (!file.exists()) {
            file.mkdir();
        }


        System.out.println("Digite o \n Modelo\n Marca\n Valor");
        setMarca(scanner.nextLine());
        setModelo(scanner.nextLine());
        setValor(scanner.nextFloat());

        String dir = pasta + File.separator + getModelo();
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dir))){
            writer.write("Marca: " + getMarca());
            writer.newLine();
            writer.write("Modelo: " + getModelo());
            writer.newLine();
            writer.write("Valor: R$" + getValor());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Salvo com sucesso");

        scanner.close();
    }

}
