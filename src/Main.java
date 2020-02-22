import contact.ContactList;
import operation.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ContactList contactList = new ContactList();
        IOperation[] operations = {
                new ExitOperation(),
                new AddOperation(),
                new SearchOperation(),
                new DelectOperation(),
                new ShowOperation(),
                new ClearOperation(),
                new SortOperation()
        };


        while (true) {
            int chioce = menu();
            operations[chioce].work(contactList);
        }
    }
    public static int menu() {
        System.out.println("-----------------------------");
        System.out.println("1.添加联系人     2.查找联系人");
        System.out.println("3.删除联系人     4.展示所有联系人信息");
        System.out.println("5.清空联系人     6.排序联系人");
        System.out.println("0.退出");
        System.out.println("-----------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入您的选择:");
        int choice = scanner.nextInt();
        return choice;
    }

}
