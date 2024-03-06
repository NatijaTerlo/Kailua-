import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n___________________\nVælg en operation:\n___________________");
            System.out.println("1: Tilføj ny lejer");
            System.out.println("2: Opdater lejekontrakt");
            System.out.println("3: Slet lejer");
            System.out.println("4: Vis alle biler");
            System.out.println("5: Afslut");

            int valg = scanner.nextInt();
            scanner.nextLine();

            switch (valg) {
                case 1:
                    CarRentalApp.addNewLejer("Natija Tl", "1234 Vejnavn", 1234, "Frederikssund", "12345678", "nat@stud.kea", "kø123456");
                    break;
                case 2:
                    CarRentalApp.updateLejekontrakt(1, 23, "2024-12-31");
                    break;
                case 3:
                    CarRentalApp.deleteLejer(1);
                    break;
                case 4:
                    showAllBiler();
                    break;
                case 5:
                    System.out.println("Afslutter programmet...");
                    return;
                default:
                    System.out.println("Ugyldug valg, prøv igen.");
                    break;
            }
        }
    }
    public static void showAllBiler(){
        String query = "SELECT * FROM Biler;";



        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/kailuacarrental", "kailua", "123");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getInt("BilId") + " : " + rs.getString("Mærke"));
            }
            con.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}