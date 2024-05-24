import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactManager {
    private List<Contact> contactList;
    private final String fileName = "contacts.ser";

    public ContactManager() {
        this.contactList = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contactList.add(contact);
        System.out.println("Contact of " + contact.getName() + " added");
    }

    public List<Contact> viewContacts() {
        return contactList;
    }

    public void updateContact(int index, Contact contact) {
        if (index >= 0 && index < contactList.size()) {
            contactList.set(index, contact);
            System.out.println("Contact updated");
        } else {
            System.out.println("Wrong num!");
        }
    }

    public void deleteContact(int i) {
        if (i >= 0 && i < contactList.size()) {
            contactList.remove(i);
            System.out.println("Contact removed");
        } else {
            System.out.println("Wrong num!");
        }
    }

    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(contactList);
            System.out.println("Data saved!! ");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            contactList = (List<Contact>) ois.readObject();
            System.out.println("Data loaded successfully");
        } catch (FileNotFoundException e) {
            System.out.println("No previous data found, starting new list");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}
