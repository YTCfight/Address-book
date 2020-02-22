package operation;

import contact.Contact;
import contact.ContactList;

public class ShowOperation implements IOperation {
    @Override
    public void work(ContactList contactList) {
        System.out.println("展示所有联系人信息");
        for (int i = 0; i < contactList.getSize(); i++) {
            Contact contact = contactList.getContact(i);
            System.out.println(contact);
        }
        return;
    }
}
