package com.company;



class lesson5 {
    public static void main(String[] args) {
        Member[] memberMas = new Member[5];
        memberMas[0] = new Member("Ivanov", "Ivan", "Ivanovich", "Developer", "Ivanov@gmail.com", 8464354, 10000000, 41);
        memberMas[1] = new Member("Ivanov", "Ivan", "Ivanovich", "Developer", "Ivanov@gmail.com", 8464354, 10000000, 39);
        memberMas[2] = new Member("Ivanov", "Ivan", "Ivanovich", "Developer", "Ivanov@gmail.com", 8464354, 10000000, 42);
        memberMas[3] = new Member("Ivanov", "Ivan", "Ivanovich", "Developer", "Ivanov@gmail.com", 8464354, 10000000, 22);
        memberMas[4] = new Member("Ivanov", "Ivan", "Ivanovich", "Developer", "Ivanov@gmail.com", 8464354, 10000000, 31);

        for (Member m : memberMas){
            if (m.age > 40)
                m.printMember();
        }
    }
}
class Member{
    String name;
    String surname;
    String second_name;
    String position;
    String email;
    int phone_number;
    int payroll;
    int age;
    public void printMember(){
        System.out.println(surname + " " + name + " " + second_name);
        System.out.println("age: " + age);
        System.out.println("Contacts: " + phone_number);
        System.out.println("          " + email);
        System.out.println("Position: " + position);
        System.out.println("          " + payroll);
    }
    public Member(String name, String surname, String second_name, String position, String email, int phone_number,  int payroll, int age){
        this.name = name;
        this.surname = surname;
        this.second_name = second_name;
        this.position = position;
        this.email = email;
        this.phone_number = phone_number;
        this.payroll = payroll;
        this.age = age;
    }
}