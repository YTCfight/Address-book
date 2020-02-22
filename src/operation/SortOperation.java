package operation;

import contact.Contact;
import contact.ContactList;

public class SortOperation implements IOperation {
    @Override
    public void work(ContactList contactList) {
        System.out.println("进行联系人排序（首字母大小）");
        for (int i = 0; i < contactList.getSize() - 1; i++) {
            contactList.contacts[i] = contactList.getContact(i);
            contactList.contacts[i + 1] = contactList.getContact(i + 1);
            if (contactList.contacts[i + 1].getName().compareTo(contactList.contacts[i].getName()) < 0) {
                //进行交换
                Contact tmp = contactList.contacts[i];
                contactList.contacts[i] = contactList.contacts[i + 1];
                contactList.contacts[i + 1] = tmp;
            }
        }
        new ShowOperation().work(contactList);
        return;
    }
}
