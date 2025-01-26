package HospitalManagement;
//(Getting from java.sql package)A Connection object's database is able to provide information describing its tables, its supported SQL grammar, its stored procedures, the capabilities of this connection, and so on. This information is obtained with the getMetaData method.
import java.sql.*;
import java.util.Scanner;

public class Patient {

    private Connection connection;
    private Scanner scanner;

    public Patient(Connection connection, Scanner scanner){
        this.connection =connection;
        this.scanner= scanner;
    }

  // Now all three methods of Patient class have to be created, AddPatent, ViewPatent and CheckPate
  // Creating addPatient fucntion
    public void addPatient(){
        System.out.print("Enter Patient Name:  ");
        String name = scanner.next();
        System.out.print("Enter Patient Age:  ");
        int age = scanner.nextInt();
        System.out.print("Enter Patient Gender:  ");
        String gender = scanner.next();
        //ID is auto increment


        try{

          String query= "INSERT INTO patients(name, age , gender) VALUES ( ?, ?, ?)";
          PreparedStatement preparedStatement = connection.prepareStatement(query);

          preparedStatement.setString(1, name);
          preparedStatement.setInt ( 2, age);
          preparedStatement.setString(3, gender);
          int affectedRows = preparedStatement.executeUpdate();
          if(affectedRows>0){
              System.out.println("Patient added successfully!");
          }else {
              System.out.println("Failed to add Patient");
          }
        }
        catch (SQLException e){
            e.printStackTrace();

        }
    }

    //In this, patients' data has to be taken from the database and printed.
    public  void viewPatients(){
        String query = "SELECT * FROM Patients";
        try {
            PreparedStatement preparedStatement=  connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Patients: ");
            System.out.println("+-------------+---------------------------+--------+-----------+");
            System.out.println("| Patient Id  | Name                      | Age    | Gender    |");
            System.out.println("+-------------+---------------------------+--------+-----------+");
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender= resultSet.getString("gender");
                System.out.printf("| %-11s | %-25s | %-6s | %-9s |\n", id, name, age, gender);
                System.out.println("+-------------+---------------------------+--------+-----------+");
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public  boolean getPatientById(int id){
        String query = "SELECT * FROM patients WHERE id = ? ";
        try{
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else {
                return  false;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return  false;

    }
}













