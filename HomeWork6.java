import Animals.Animal;
import Animals.Cat;
import Animals.Dog;

public class HomeWork6 {

    public static void runDistance(Animals.Animal[] animals, double dDistance){
        System.out.println(String.format("Бег по прямой %.2fм", dDistance));

        for(int i = 0; i < animals.length; ++i){
            if(animals[i] == null)
                continue;

            if(animals[i].run(dDistance))
                continue;

            System.out.println("Участник " + animals[i].getInfo() + " сошел с дистанции!");
            animals[i] = null;
        }
    }

    public static void jumpDistance(Animals.Animal[] animals, double dDistance){
        System.out.println(String.format("Прыжок на препятствие %.2fм", dDistance));

        for(int i = 0; i < animals.length; ++i){
            if(animals[i] == null)
                continue;

            if(animals[i].jump(dDistance))
                continue;

            System.out.println("Участник " + animals[i].getInfo() + " сошел с дистанции!");
            animals[i] = null;
        }
    }

    public static void swimDistance(Animals.Animal[] animals, double dDistance){
        System.out.println(String.format("Заплыв на %.2fм", dDistance));

        for(int i = 0; i < animals.length; ++i){
            if(animals[i] == null)
                continue;

            if(animals[i].swim(dDistance))
                continue;

            String str = animals[i].getClassName().equalsIgnoreCase("кот")?
                    " Бедный котик ;(" : "";

            System.out.println("Участник " + animals[i].getInfo() + " сошел с дистанции!" + str);
            animals[i] = null;
        }
    }

    public static void runMarathon(Animals.Animal[] animals){
        if(animals.length == 0){
            System.out.println("На забег никто не пришел!");
            return;
        }

        System.out.println("В забеге принимают участие:");
        for(Animals.Animal animal : animals){
            System.out.println(animal.getInfo());
        }

        runDistance(animals, 100);
        jumpDistance(animals, 0.3);
        runDistance(animals, 170);
        jumpDistance(animals, 0.55);
        swimDistance(animals, 5);
        runDistance(animals, 400.0);
        jumpDistance(animals, 1.0);

        System.out.println("Победители:");
        boolean bFound = false;

        for(Animals.Animal animal : animals){
            if(animal == null)
                continue;

            bFound = true;
            System.out.println(animal.getInfo());
        }

        if(!bFound)
            System.out.println("К сожалению никто не смог пройти данный марафон!");
    }

    public static void main(String[] args) {

        Animals.Animal[] animals = { new Cat("Шкурич"), new Cat("Симба"), new Cat("Ушанка"),
            new Dog("Верный"), new Dog("Тяпа"), new Dog("Кай")};

        runMarathon(animals);
    }
}
