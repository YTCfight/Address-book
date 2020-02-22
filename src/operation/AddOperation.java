package operation;

import contact.Contact;
import contact.ContactList;

import java.util.Scanner;

public class AddOperation implements IOperation {
    @Override
    public void work(ContactList contactList) {
        System.out.println("添加联系人");
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入添加联系人的姓名：");
        String name = scanner.next();
        System.out.println("请输入添加联系人的号码：");
        String id = scanner.next();
        Contact contact = new Contact(name,id);
        contactList.setContact(contactList.getSize(),contact);
        contactList.setSize(contactList.getSize() + 1);
        System.out.println("添加成功!");
        if (contactList.getSize() == contactList.contacts.length) {
            //进行扩容
            Contact[] newContacts = new Contact[contactList.contacts.length * 2];
            for (int i = 0; i < contactList.getSize(); i++) {
                newContacts[i] = contactList.contacts[i];
            }
            contactList.contacts = newContacts;
            System.out.println("扩容成功 !");
        }
        return;
    }
}
