public class lesson 6 {

        }

        public class Animal {
            public Animal(){

            }
           public void run(float distance){
                try {
                    if (distanc <= 500)
                        System.out.println("run: " + true);
                    else
                        System.out.println("run: " + false);
                } catch (new IllegalArgumeentException e)
                {}
            }

           public void jump(float hight){
                try {
                    if (hight <= 0.5)
                        System.out.println("run: " + true);
                    else
                        System.out.println("run: " + false);
                } catch (new IllegalArgumeentException e)
                {}
            }

           public void swim(float distance){
                try {
                    if (distanc <= 10)
                        System.out.println("run: " + true);
                    else
                        System.out.println("run: " + false);
                } catch (new IllegalArgumeentException e)
                {}
            }
        }
public class Cat extends Animal {
    @java.lang.Override
    public void run(float distance) {
        super.run(distance);
    }

    @java.lang.Override
    public void jump(float hight) {
        super.jump(hight);
    }

    @java.lang.Override
    public void swim(float distance) {
        System.out.println("run: " + false);
    }
}

public class Dog extends Animal {
    @java.lang.Override
    public void run(float distance) {
        super.run(distance);
    }

    @java.lang.Override
    public void jump(float hight) {
        super.jump(hight);
    }

    @java.lang.Override
    public void swim(float distance) {
        return super.swim(distance);
    }
}



public class MainClass {
    public static void main(String[] args) {
        Cat cat = new Cat();
        Dog dog = new Dog();

    }

    }