package main.repository;

import main.domain.Client;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;


public class RepositoryClientsCSV {

    private List<Client> clients;

    public  RepositoryClientsCSV(){

    }
    public RepositoryClientsCSV(List<Client> clients) {
        this.clients = clients;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Client> readFromCSV(){
//        String path = "C:\\Users\\Maria\\IdeaProjects\\FirstAttempt\\Clients.csv";
        try(BufferedReader br = new BufferedReader(new FileReader("Clients.csv"))){
            String line;
            while((line = br.readLine())!= null){
                List<String> values = Arrays.asList(line.split(";"));
                System.out.println(values);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clients;

    }

    public void WriteToCSV(){

//        List<List<Client>> rows = Arrays.asList(Arrays.asList("da", "da","da"), Arrays.asList("add", "aa"));
//        try(BufferedWriter br = new BufferedWriter(new FileWriter("clients.csv"))){
//            for(List<String> rowData : rows){
//                br.append(String.join(",", rowData));
//                br.append("\n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
