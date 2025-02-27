package HMSSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDatabase {

	private boolean isPatientRegistered(int patientId) throws SQLException {
		String query = "SELECT * from Patient where id = ?;";
		try(Connection conn = DatabaseConnection.getConnection();
				PreparedStatement psmt = conn.prepareStatement(query)) {
			psmt.setInt(1, patientId);
			ResultSet rs = psmt.executeQuery();
			if(rs.next() && rs.getInt(1)>0) {
				return true;
			}
		}
		return false;
	}
 

    private boolean isDoctorRegistered(int doctorId) throws SQLException {
        String query = "SELECT COUNT(*) FROM Doctor WHERE id = ?;";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setInt(1, doctorId);
            ResultSet rs = psmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    return true;
                }
        }
        return false;
    }
    public void addAppointment(Appointment appointment) throws SQLException {
        if (!isPatientRegistered(appointment.getPatientId()) || !isDoctorRegistered(appointment.getDoctorId())) {
            System.out.println("Patient ID or Doctor ID not registered.");
            return;
        }
 

        String query = "INSERT INTO Appointment (patient_id, doctor_id, appointment_date) VALUES (?, ?, ?);";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setInt(1, appointment.getPatientId());
            psmt.setInt(2, appointment.getDoctorId());
            psmt.setString(3, appointment.getAppointmentDate());
            psmt.executeUpdate();
            System.out.println("Appointment added successfully!");
        }
    }
    public Appointment getAppointmentById(int id) throws SQLException {
        String query = "SELECT * FROM Appointment WHERE id = ?;";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setInt(1, id);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                return new Appointment(
                        rs.getInt("id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getString("appointment_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }
    public List<Appointment> getAllAppointments() throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM Appointment;";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement smt = conn.createStatement();
             ResultSet rs = smt.executeQuery(query)) {
            while (rs.next()) {
                Appointment appointment = new Appointment(
                        rs.getInt("id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getString("appointment_date")
                );
                appointments.add(appointment);
            }
            return appointments;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void updateAppointment(int id, Appointment appointment) throws SQLException {
        if (!isPatientRegistered(appointment.getPatientId())) {
            System.out.println("Patient ID not registered.");
            return;
        }
        if (!isDoctorRegistered(appointment.getDoctorId())) {
            System.out.println("Doctor ID not registered.");
            return;
        }
        String query = "UPDATE Appointment SET patient_id = ?, doctor_id = ?, appointment_date = ? WHERE id = ?;";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setInt(1, appointment.getPatientId());
            psmt.setInt(2, appointment.getDoctorId());
            psmt.setString(3, appointment.getAppointmentDate());
            psmt.setInt(4, id);
            psmt.executeUpdate();
            System.out.println("Appointment updated successfully!");
        }
    }

    public void deleteAppointment(int id) {
        String query = "DELETE FROM Appointment WHERE id = ?;";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setInt(1, id);
            psmt.executeUpdate();
            System.out.println("Appointment deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
