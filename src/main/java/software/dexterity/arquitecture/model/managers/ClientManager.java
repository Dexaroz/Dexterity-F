package software.dexterity.arquitecture.model.managers;

import software.dexterity.arquitecture.model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientManager {
    private final List<Client> clients;

    public ClientManager() {
        this.clients = new ArrayList<>();
    }

    public void addClient(String name, String email, String phoneNumber, String country, String province, String city, int postalCode, String street, int number, String suite , String taxID){
        clients.add(new Client(name, email, phoneNumber, country, province, city, postalCode, street, number, suite, taxID));
    }

    public void removeClient(Client client){
        clients.remove(client);
    }

    public void editClient(Client client, String name, String email, String phoneNumber, String country, String province, String city, int postalCode, String street, int number, String suite , String taxID){
        clients.remove(client);
        clients.add(client.editClient(name, email, phoneNumber, country, province, city, postalCode, street, number, suite, taxID));
    }

    public List<Client> getClients(){
        return clients;
    }
}
