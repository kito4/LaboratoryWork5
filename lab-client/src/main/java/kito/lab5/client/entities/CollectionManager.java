package kito.lab5.client.entities;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс для работы с коллекцией экземпляров HumanBeing
 */
public class CollectionManager {

    private final PriorityQueue<HumanBeing> humanQueue = new PriorityQueue<>();
    private final LocalDate initializationDate;

    /**
     * Конструктор, задаюший дату инициализации коллекции и поле fileName
     * @param fileName имя файла, в котором хранятся данные о коллекции
     */
    public CollectionManager(String fileName) {
        initializationDate = LocalDate.now();
    }

    private ArrayList<HumanBeing> getSortedArrayListFromQueue () {
        PriorityQueue<HumanBeing> bufferQueue = new PriorityQueue<>(humanQueue);
        ArrayList<HumanBeing> sortedArrayList = new ArrayList<>();
        while (!bufferQueue.isEmpty()){
            sortedArrayList.add(bufferQueue.poll());
        }
        return sortedArrayList;
    }

    /**
     * Метод, добавляющий экземпляр HumanBeing в коллекцию
     * @param human экзепмпляр HumanBeing
     */
    public void addHuman(HumanBeing human) {
        humanQueue.add(human);
    }

    /**
     * Метод, удаляющий человека из коллекции по заданному ID
     * @param id id человека
     */
    public void removeHumanById(int id) {
        humanQueue.removeIf(human -> human.getId() == id);
    }

    /**
     * Метод, удаляющий любого человека из коллекции по заданному значению настроения
     * @param mood значение настроения
     */
//    public void removeHumanByAnyMood(Mood mood) {
//        for (HumanBeing human : humanQueue) {
//            if (human.getMood() == mood) {
//                humanQueue.remove(human);
//                break;
//            }
//        }
//    }

    /**
     * Метод, позволяющий получить человека из коллекции по id
     * @param id id человека
     * @return экземпляр HumanBeing, соответствующий полученному значению ID
     * @throws IllegalArgumentException
     */
    public HumanBeing getHumanById(int id) throws IllegalArgumentException {
        for (HumanBeing human : humanQueue) {
            if (human.getId() == id) {
                return human;
            }
        }
        throw new IllegalArgumentException("Человек с таким ID не найден");
    }

    /**
     * Метод, позволяющий обновить информацию о человеке из коллекции по id
     * @param id id человека
     * @param humanToSet новая информация о человеке
     * @throws IllegalArgumentException
     */
    public void setHumanById(int id, HumanBeing humanToSet) throws IllegalArgumentException {
        for (HumanBeing human : humanQueue) {
            if (human.getId() == id) {
                human = humanToSet;
                return;
            }
        }
        throw new IllegalArgumentException("Человек с таким ID не найден");
    }

    /**
     * Метод выводящий информацию о коллекции
     */
    public String getStringForShowing() {
        ArrayList<HumanBeing> listToShow = getSortedArrayListFromQueue();
        return listToShow.stream()
                .map(HumanBeing::toString)
                .collect(Collectors.joining("\n"));
    }

    /**
     * Метод, позволяющий заполнить коллекцию при помощи массива HumanBeing
     * @param arrayOfPeople массив HumanBeing
     */
    public void fillWithArray(ArrayList<HumanBeing> arrayOfPeople) {
        for (HumanBeing human : arrayOfPeople) {
            addHuman(human);
        }
    }

    /**
     * @return наибольший элемент коллекции
     */
    public HumanBeing returnHead() {
        return humanQueue.peek();
    }

    /**
     * @return массив людей коллекции, расположенных в порядке возрастания
     */
    public ArrayList<HumanBeing> returnDescending() {
        ArrayList<HumanBeing> descendingList = getSortedArrayListFromQueue();
        Collections.sort(descendingList);
        return descendingList;
    }

    /**
     * @return Возвращает длину коллекции
     */
    public int getLength() {
        return humanQueue.size();
    }

    /**
     * @param speed скорость машины
     * @return все элементы коллекции, скорость которых меньше заданной
     */
//    public ArrayList<HumanBeing> filterByCarSpeed(int speed) {
//        ArrayList<HumanBeing> filtered = new ArrayList<>();
//        for (HumanBeing human : humanQueue) {
//            if (human.getCar() != null && human.getCar().getCarSpeed() < speed) {
//                filtered.add(human);
//            }
//        }
//        return filtered;
//    }

    /**
     * Метод, добавляющий человека в коллекцию, если его значение является минимальным
     * @param newHuman человек для добавления
     */
    public void addIfMin(HumanBeing newHuman) {
        for (HumanBeing human : humanQueue) {
            if (newHuman.compareTo(human) > 0) {
                return;
            }
        }
        addHuman(newHuman);
    }

    /**
     * Метод, полностью очищающий коллекцию
     */
    public void clearCollection() {
        humanQueue.clear();
    }

    /**
     * @return информация о коллекции в строковом формате
     */
    public String getInfoAboutCollection() {
        return "Информация о коллекции. Тип: " + humanQueue.getClass() + " Дата инициализации: "
                + initializationDate.toString() + " Количество элементов: " + humanQueue.size();
    }

    /**
     * @return информация обо всех элементах коллекции в строковом формате
     */
    public List<String> getArrayOfInfo() {
        ArrayList<String> arrayOfInfo = new ArrayList<>();
        for (HumanBeing human : humanQueue) {
            String humanInfo = human.getName() + "," + human.getCoordinates().getX() + ","
                    + human.getCoordinates().getY() + "," + human.getCreationDate().toString() + ","
                     + "," + human.isHasToothpick() + ","
                    + (human.getImpactSpeed() == null ? "null," : human.getImpactSpeed() + ",")
                    + (human.getWeaponType() == null ? "null," : human.getWeaponType() + ",")
                      + (human.getCar() == null ? "," : (human.getCar().getCool()   + ","));
            arrayOfInfo.add(humanInfo);
        }
        return arrayOfInfo;
    }
}
