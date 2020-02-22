package operation;

import contact.Contact;
import contact.ContactList;

import java.util.Scanner;

public class DelectOperation implements IOperation {
    @Override
    public void work(ContactList contactList) {
        System.out.println("删除联系人");
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入删除联系人的姓名：");
        String name = scanner.next();
        //先查找
        for (int i = 0; i < contactList.getSize(); i++) {
            Contact contact = contactList.getContact(i);
            if (contact.getName().equals(name)) {
                //找到了，进行具体的删除
                contactList.setContact(i,contactList.getContact(contactList.getSize() -1));
                contactList.setSize(contactList.getSize() - 1);
                System.out.println("删除成功!");
                return;
            }
        }
        System.out.println("查无此人");
        return;
    }
}
