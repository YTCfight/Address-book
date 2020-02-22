package operation;

import contact.Contact;
import contact.ContactList;

import java.util.Scanner;

public class SearchOperation implements IOperation {
    @Override
    public void work(ContactList contactList) {
        System.out.println("查找联系人信息");
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要查找联系人的姓名：");
        String name = scanner.next();
        for (int i = 0; i < contactList.getSize(); i++) {
            Contact contact = contactList.getContact(i);
            if (contact.getName().equals(name)) {
                System.out.println(contact);
                return;
            }
        }
        System.out.println("查无此人");
        return;
    }
}
