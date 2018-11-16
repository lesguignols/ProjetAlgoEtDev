package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InscriptionController 
{
	@FXML
    private TextField id;

    @FXML
    private PasswordField password;
    
	@FXML
    private Button inscrire;

    @FXML
    private Button retour;

    @FXML
    private Button quitter;

    @FXML
    void clickOnSInscrire(ActionEvent event) throws SQLException 
    {
    	if(id.getText().isEmpty() || password.getText().isEmpty())
    	{
    		Stage stage = new Stage();
    		Stage stage1 = new Stage();
    		try
    		{
    			Parent root = FXMLLoader.load(getClass().getResource("../ressources/ErreurInscription.fxml"));
    			stage.setScene(new Scene(root));
    			stage.setTitle("Bienvenue");
    			stage.setResizable(false);
    			stage.show();
			
    			stage1 = (Stage)inscrire.getScene().getWindow();
    			stage1.close();
    		
    		} catch(IOException e)
    		{
    			System.out.println(e);
    		}
    	}
    	else
    	{
    		try {
    			Class.forName("org.postgresql.Driver");
    			System.out.print("Driver OK.");

    			String url = "jdbc:postgresql://localhost:5432/AlgoEtDev";
    			String user = "postgres";
    			String passwd = "101506";

    			Connection con = DriverManager.getConnection(url, user, passwd);

    			String sql = "INSERT INTO player (ID, password) VALUES(?,?)";
    			PreparedStatement statement = con.prepareStatement(sql);
    			String Id = id.getText();
    			String psw = password.getText();
    			statement.setString(1, Id);
    			statement.setString(2, psw);
    			statement.executeUpdate();
    			String sql1 = "SELECT COUNT( * ) FROM player WHERE id = ? AND password = ?";
    			PreparedStatement state = con.prepareStatement(sql1);
    			state.setString(1, Id);
    			state.setString(2, psw);
    			ResultSet result = state.executeQuery();
    			
    			if(result.next())
    			{
    				Stage stage = new Stage();
    	    		Stage stage1 = new Stage();
    	    		try
    	    		{
    	    			Parent root = FXMLLoader.load(getClass().getResource("../ressources/ErreurInscription.fxml"));
    	    			stage.setScene(new Scene(root));
    	    			stage.setTitle("Bienvenue");
    	    			stage.setResizable(false);
    	    			stage.show();

    	    			stage1 = (Stage)inscrire.getScene().getWindow();
    	    			stage1.close();

    	    		} catch(IOException e)
    	    		{
    	    			System.out.println(e);
    	    		}
    			}else
        		{
        			Stage stage = new Stage();
        			Stage stage1 = new Stage();
        			try
        			{
        				Parent root = FXMLLoader.load(getClass().getResource("../ressources/Connexion.fxml"));
        				stage.setScene(new Scene(root));
        				stage.setTitle("Bienvenue");
        				stage.setResizable(false);
        				stage.show();

        				stage1 = (Stage)inscrire.getScene().getWindow();
        				stage1.close();

        			} catch(IOException e)
        			{
        				System.out.println(e);
        			}
        		}
    		} catch (ClassNotFoundException e) {
    			e.printStackTrace();
    		}
    		
    	}
    }

    @FXML
    void clickOnRetour(ActionEvent event) 
    {
    	Stage stage = new Stage();
    	Stage stage1 = new Stage();
    	try
    	{
    		Parent root = FXMLLoader.load(getClass().getResource("../ressources/Connexion.fxml"));
    		stage.setScene(new Scene(root));
			stage.setTitle("Bienvenue");
			stage.setResizable(false);
			stage.show();
			
			stage1 = (Stage)retour.getScene().getWindow();
			stage1.close();
    		
    	} catch(IOException e)
    	{
    		System.out.println(e);
    	}
    }

    @FXML
    void clickOnExit(ActionEvent event) 
    {
    	Stage stage = (Stage)quitter.getScene().getWindow();
    	stage.close();
    }
}