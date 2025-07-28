import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class VideoStore {
    private LL<Video> vList = null;
    private LL<Video> rentedList = null;
    private LL<Customer> cList = null;
    private String listType;
//    private int nextVideoID = 0;
//    private int nextCustomerID = 0;

    private int videos = 0, customers = 0, requests = 0;

    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        VideoStore vs = new VideoStore();
        vs.processCommandLineArguments(args);
        vs.MenuLoop();
    }

    private void processCommandLineArguments(String[] args){
        listType = args[0];
        if (args.length > 1) {
            videos = Integer.parseInt(args[1]);
            customers = Integer.parseInt(args[2]);
            requests = Integer.parseInt(args[3]);
            long start = System.currentTimeMillis();
            generateList(listType, videos, customers, requests);
            long end = System.currentTimeMillis();
            System.out.println(end - start);
            System.exit(0);
        } else if (listType.equals("SLL")) {
            createSLL();
        } else if (listType.equals("DLL")) {
            createDLL();
        }
    }
    public void mainMenu() {
        System.out.println("""
                ===========================
                1: To add video
                2: To delete a video
                3: To add a customer
                4: To delete a customer
                5: To check if a particular video is in store
                6: To check out a video
                7: To check in a video
                8: To print all customers
                9: To print all videos
                10: To print in store videos
                11: To print all rent videos
                12: To print the videos rent by a customer
                13: To exit
                ===========================
                """);
    }

    private void MenuLoop() {
        while (true) {
            mainMenu();
            int choice = getUserInput();
            switch (choice) {
                case 1 -> setVideoInStore();
                case 2 -> deleteVideo();
                case 3 -> setCustomer();
                case 4 -> deleteCustomer();
                case 5 -> check(null, null);
                case 6 -> checkOut(null, null, null, null);
                case 7 -> checkIn(null,null,null,null);
                case 8 -> printAllCustomers();
                case 9 -> printAllVideos();
                case 10 -> printInStoreVideos();
                case 11 -> printRentedVideos();
                case 12 -> printCustomersVideos();
                case 13 -> {
                    System.out.println("Goodbye");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    public void setVideoInStore() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the videos title: ");
        String title = input.nextLine();
        Video v = new Video(title);
        vList.addLast(v);
    }

    public void setCustomer() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the customers name: ");
        String name = input.nextLine();
        Customer c = new Customer(name, generateCustomerList());
        cList.addLast(c);
    }

    public void deleteVideo() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter video title: ");
        String name = input.nextLine();
        System.out.println("Enter video ID");
        Integer id = input.nextInt();
        boolean removed = vList.remove(name, id);
        if (removed){
            System.out.println("Successfully removed: " + name + " with ID: " + id);
        } else {
            System.out.println("No video found with title: " + name + " and ID: " + id);
        }
    }
    public void deleteCustomer() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter customer name: ");
        String name = input.nextLine();
        System.out.println("Enter customer ID");
        Integer id = input.nextInt();
        boolean removed = cList.remove(name, id);
        if (removed){
            System.out.println("Successfully removed: " + name + " with ID: " + id);
        } else {
            System.out.println("No Customer found with name: " + name + " and ID: " + id);
        }
    }

    public void check(String title, Integer id) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter video title: ");
        title = input.nextLine();
        System.out.println("Enter the video ID");
        id = input.nextInt();
        if(vList.contains(title, id)){
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }
    }

    public void checkOut(String title, Integer videoID, String name, Integer customerID) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter video title: ");
        title = input.nextLine();
        System.out.println("Enter the video ID");
        videoID = input.nextInt();
        input.nextLine();
        System.out.println("Enter customer name: ");
        name = input.nextLine();
        System.out.println("Enter customer ID");
        customerID = input.nextInt();

        Video v = vList.get(title, videoID);
        Customer c = cList.get(name, customerID);

        if(v == null){
            System.out.println("Video not found");
        } else if (c == null){
            System.out.println("Customer not found");
        } else {
            vList.remove(v);
            c.getRentVideo().addFirst(v);
            rentedList.addLast(v);
        }
    }//End of Checkout method

    public void checkIn(String title, Integer videoID, String name, Integer customerID) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter video title: ");
        title = input.nextLine();
        System.out.println("Enter the video ID");
        videoID = input.nextInt();
        input.nextLine();
        System.out.println("Enter customer name: ");
        name = input.nextLine();
        System.out.println("Enter customer ID");
        customerID = input.nextInt();

        Customer c = cList.get(name, customerID);
        if(c == null){
            System.out.println("Customer not found");
        } else {
            Video v = c.getRentVideo().get(title, videoID);
            if(v == null){
                System.out.println("Video not found");
            } else {
                c.getRentVideo().remove(v);
                rentedList.remove(v);
                vList.addFirst(v);
            }
        }
    }

    public void printInStoreVideos() {
        System.out.println(vList.print());
    }

    public void printAllCustomers() {
        System.out.println(cList.print());
    }

    public void printRentedVideos() {
        System.out.println(rentedList.print());
    }

    public void printAllVideos() {
        System.out.println(vList.print() + rentedList.print());
    }

    public void printCustomersVideos() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter customer name: ");
        String name = input.nextLine();
        System.out.println("Enter customer ID");
        Integer customerID = input.nextInt();
        if (!cList.contains(name, customerID)) {
            System.out.println("Customer not found");
        } else {
            Customer customer = cList.get(name, customerID);
            System.out.println(customer.getRentVideo().print());
        }
    }

    private void createSLL() {
        vList = new SLL<>();
        cList = new SLL<>();
        rentedList = new SLL<>();
    }

    private void createDLL() {
        vList = new DLL<>();
        cList = new DLL<>();
        rentedList = new DLL<>();
    }

    private LL<Video> generateCustomerList() {
        if (listType.equals("SLL")) {
            return new SLL<>();
        }
        return new DLL<>();
    }

    public void generateList(String listType, int videos, int customers, int requests) {
        Queue<Integer> requestQueue = new LinkedList<>();

        if (listType.equals("SLL")) {
            createSLL();
        } else {
            createDLL();
        }

        for (int i = 1; i <= videos; i++) {
            Video v = new Video(generateVideoName());
            vList.addLast(v);
        }

        for (int i = 1; i <= customers; i++) {
            Customer c = new Customer(generateCustomerName(), generateCustomerList());
            cList.addLast(c);
        }

        for (int i = 1; i <= requests; i++) {
            Random r = new Random();
            int request = r.nextInt(7-5+1) + 5;
            requestQueue.add(request);
        }

        //Execute the queue.
        executeRequests(requestQueue);
    }

    private void executeRequests(Queue<Integer> q) {
        while (!q.isEmpty()) {
            int request = q.remove();
            Video video;
            if(request == 7 && rentedList.size() > 0){
                video = getRandomRentedVideo();
            } else {
                video = getRandomVideo();
            }

            Customer customer = getRandomCustomer();
            switch (request) {
                case 5 -> check(video.getName(), video.getID()); //Check to see is a particular video is in store.
                case 6 -> checkOut(video.getName(), video.getID(), customer.getName(), customer.getID());// A customer checks out a video
                case 7 -> checkIn(video.getName(), video.getID(), customer.getName(), customer.getID());// A customer returns a checked out video.
            }
        }
    }//End of execute Requests

    private Video getRandomVideo() {
        Random r = new Random();
        int index = r.nextInt(vList.size());
        return vList.get(index);
    }

    private Video getRandomRentedVideo(){
        Random r = new Random();
        int index = r.nextInt(rentedList.size());
        return rentedList.get(index);
    }

    private Customer getRandomCustomer() {
        Random r = new Random();
        int index = r.nextInt(cList.size());
        return cList.get(index);
    }

    private String generateCustomerName() {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 4;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
    private String generateVideoName() {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 4;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private int getUserInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.nextLine();
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

}//End of videoStore class
