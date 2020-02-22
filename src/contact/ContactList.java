package contact;

public class ContactList {
    public Contact[] contacts = new Contact[1];
    private int size;



    public Contact getContact(int index) {
        return contacts[index];
    }

    public void setSize(int size) {
        this.size = size;
    }
    public int getSize() {
        return size;
    }

    public void setContact(int index, Contact contact) {
        contacts[index] = contact;
    }
}
