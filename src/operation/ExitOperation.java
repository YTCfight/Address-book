package operation;

import contact.ContactList;

public class ExitOperation implements IOperation {
    @Override
    public void work(ContactList contactList) {
        System.out.println("goodbye!");
        System.exit(0);
    }
}
