import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ContactManager contactManager = new ContactManager();
        Scanner sc = new Scanner(System.in);
        contactManager.loadData();
        System.out.println("Simple contact manager");
        while (true) {

            System.out.println("1.Add new contact\n" +
                    "2.View all contacts\n" +
                    "3.Update contact\n" +
                    "4.Delete contact\n" +
                    "5.Exit & save data");

            int choice ;
            try {
                choice = sc.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Pls type option number!");
                sc.next();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("Enter name: ");
                    String name = sc.next();
                    System.out.println("Enter Phone num: ");
                    String phoneNum = sc.next();
                    System.out.println("Enter email: ");
                    String email = sc.next();
                    System.out.println("Enter address: ");
                    String address = sc.next();
                    contactManager.addContact(new Contact(name, phoneNum, email, address));
                    System.out.println("Contact added");
                    break;
                case 2:
                    System.out.println("//////////////////////////////////////");
                    List<Contact> contactList = contactManager.viewContacts();
                    for (int i = 0; i < contactList.size(); i++) {
                        System.out.println(i + 1 + ". " + contactList.get(i).toString());

                    }
                    System.out.println("//////////////////////////////////////");
                    break;
                case 3:
                    System.out.println("Enter num of contact");
                    int index = sc.nextInt() - 1;

                    if (index >= 0 && index < contactManager.viewContacts().size()) {
                        System.out.println("Press Enter to skip");

                        sc.nextLine();
                        System.out.println("Enter new name");
                        String newName = sc.nextLine();

                        System.out.println("Enter new phone number");
                        String newPhoneN = sc.nextLine();

                        System.out.println("Enter new email");
                        String newEmail = sc.nextLine();

                        System.out.println("Enter new address");
                        String newAddress = sc.nextLine();

                        Contact oldContact = contactManager.viewContacts().get(index);
                        if (newName.isEmpty()) {
                            newName = oldContact.getName();
                        }
                        if (newPhoneN.isEmpty()) {
                            newPhoneN = oldContact.getPhoneNumber();
                        }
                        if (newEmail.isEmpty()) {
                            newEmail = oldContact.getEmail();
                        }
                        if (newAddress.isEmpty()) {
                            newAddress = oldContact.getAddress();
                        }
                        Contact newContact = new Contact(newName, newPhoneN, newEmail, newAddress);
                        contactManager.updateContact(index, newContact);
                    } else {
                        System.out.println("Wrong num");
                    }
                    break;
                case 4:
                    System.out.println("Enter num of contact");
                    int indexToRemove = sc.nextInt() - 1;
                    if (indexToRemove >= 0 && indexToRemove < contactManager.viewContacts().size()) {
                        contactManager.deleteContact(indexToRemove);
                    } else {
                        System.out.println("Wrong num");
                    }
                    break;
                case 5:
                    contactManager.saveData();
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong choice!!!");
                    break;
            }

        }
    }

}