package operation;

import contact.ContactList;

public class ClearOperation implements IOperation {
    @Override
    public void work(ContactList contactList) {
        System.out.println("清空链表");
        contactList.setSize(0);
        System.out.println("清空成功!");
        return;
    }
}
