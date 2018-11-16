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
import player.Player;

public class ConnexionController 
{
	@FXML
	private PasswordField password;

	@FXML
	private TextField id;
	
	@FXML
	private Button connecter;

	@FXML
	private Button inscrire;

	@FXML
	private Button quitter;
	
	@FXML
	void clickOnSeConnecter(ActionEvent event) throws ClassNotFoundException, SQLException 
	{
		if(id.getText().isEmpty() || password.getText().isEmpty())
    	{
    		Stage stage = new Stage();
    		Stage stage1 = new Stage();
    		try
    		{
    			Parent root = FXMLLoader.load(getClass().getResource("../ressources/IdPswNull.fxml"));
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
			Class.forName("org.postgresql.Driver");
			System.out.print("Driver OK.");

			String url = "jdbc:postgresql://localhost:5432/AlgoEtDev";
			String user = "postgres";
			String passwd = "101506";

			Connection con = DriverManager.getConnection(url, user, passwd);


			try {
				String sql = "SELECT 1 FROM player WHERE  id = ? and password = ?";
				PreparedStatement statement = con.prepareStatement(sql);
				statement.setString(1, id.getText());
				statement.setString(2, password.getText());
				ResultSet resultSet = statement.executeQuery();
				if(resultSet.next())
				{
					System.out.println("OK");
					Player p = new Player();
					p.setId(id.getText());
					p.setPassword(password.getText());
					Stage stage = new Stage();
					Stage stage1 = new Stage();
					try
					{
						Parent root = FXMLLoader.load(getClass().getResource("../ressources/MenuIndex.fxml"));
						stage.setScene(new Scene(root));
						stage.setTitle("Bienvenue");
						stage.setResizable(false);
						stage.show();

						stage1 = (Stage)connecter.getScene().getWindow();
						stage1.close();

					} catch(IOException e)
					{
						System.out.println(e);
					}	
				} else {
					Stage stage = new Stage();
		    		Stage stage1 = new Stage();
		    		try
		    		{
		    			Parent root = FXMLLoader.load(getClass().getResource("../ressources/IdPswNull.fxml"));
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
				statement.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	@FXML
	void clickOnSInscrire(ActionEvent event) 
	{
		Stage stage = new Stage();
    	Stage stage1 = new Stage();
    	try
    	{
    		Parent root = FXMLLoader.load(getClass().getResource("../ressources/Inscription.fxml"));
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

	@FXML
	void clickOnExit(ActionEvent event) 
	{
		Stage stage = (Stage)quitter.getScene().getWindow();
    	stage.close();
	}
}
