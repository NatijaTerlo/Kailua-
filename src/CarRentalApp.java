import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class CarRentalApp {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/kailuacarrental";
    private static final String DATABASE_USER = "kailua";
    private static final String DATABASE_PASSWORD = "123";

    public static void main(String[] args) {
        addNewCar("Ford", "Focus", "Benzin", "FD12345", "2021-06-15", 10000, "Familie");
    }

    public static void addNewCar(String mærke, String model, String brændstoftype, String registreringsnummer, String førsteRegistrering, int kilometertæller, String kategori) {
        String sql = "INSERT INTO Biler (Mærke, Model, Brændstoftype, Registreringsnummer, FørsteRegistrering, Kilometertæller, Kategori) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, mærke);
            pstmt.setString(2, model);
            pstmt.setString(3, brændstoftype);
            pstmt.setString(4, registreringsnummer);
            pstmt.setString(5, førsteRegistrering);
            pstmt.setInt(6, kilometertæller);
            pstmt.setString(7, kategori);

            pstmt.executeUpdate();
            System.out.println("En ny bil er tilføjet succesfuldt.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addNewLejer(String navn, String adresse, int postnummer, String byNavn, String mobiltelefon, String email, String kørekortNummer) {
        String sql = "INSERT INTO Lejere (Navn, Adresse, Postnummer, ByNavn, Mobiltelefon, Email, KørekortNummer) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, navn);
            pstmt.setString(2, adresse);
            pstmt.setInt(3, postnummer);
            pstmt.setString(4, byNavn);
            pstmt.setString(5, mobiltelefon);
            pstmt.setString(6, email);
            pstmt.setString(7, kørekortNummer);


            pstmt.executeUpdate();

            System.out.println("En ny lejer er tilføjet succesfuldt.");
        } catch (SQLException e) {
            System.out.println("Fejl ved tilføjelse af ny lejer: " + e.getMessage());
        }
    }
    public static void deleteLejer(int lejerId) {
        String sql = "DELETE FROM Lejere WHERE LejerId = ?";
        try (Connection con = getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, lejerId);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Lejeren er slettet succesfuldt.");
            } else {
                System.out.println("Ingen lejer fundet med det angivne ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateLejekontrakt(int kontraktId, int maxKm, String tilDatoTid) {
        String sql = "UPDATE Lejekontrakter SET TilDatoTid = ?, MaxKm = ? WHERE KontraktId = ?";
        try (Connection con = getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, tilDatoTid);
            pstmt.setInt(2, maxKm);
            pstmt.setInt(3, kontraktId);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Lejekontrakten er opdateret succesfuldt.");
            } else {
                System.out.println("Ingen lejekontrakt fundet med det angivne ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
