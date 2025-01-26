package HospitalManagement;
// Doctors will not be added through the user, the administrator will add from the backend.
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Doctor {

    private Connection connection;

    public Doctor(Connection connection){
    this.connection =connection;
    }
    //In this, Doctors' data has to be taken from the database and printed
    public void viewDoctors(){
        String query = "SELECT * FROM doctors";
        try {
            PreparedStatement preparedStatement=  connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Doctors: ");
            System.out.println("+------------+---------------------------+--------------------+");
            System.out.println("| Doctor Id  | Name                      | Specialization     |");
            System.out.println("+------------+---------------------------+--------------------+");
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization= resultSet.getString("Specialization");
                System.out.printf("| %-10s | %-25s | %-18s |\n", id , name, specialization );
                System.out.println("+------------+---------------------------+--------------------+");
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    // GetDoctorByID is a method in which by passing id we will check whether that doctor exists from our database or not.
    public  boolean getDoctorById(int id){
        String query = "SELECT * FROM doctors WHERE id = ? ";// The value of this placeholder is set from the preparedStatement below
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

