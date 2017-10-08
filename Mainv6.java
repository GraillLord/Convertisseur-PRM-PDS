import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Application {
	private static int isMoved = 0;
	private static int isSwitched = 0;
	private static boolean isKey = false;
	private static boolean isColored = false;
	private double stageWidth = 300.0;
	private double stageHeight = 450.0;
	private ArrayList<String> nbChar = null;
	private String nCentre = null;
	private String prm_input;
	private String pds_input;
	private char [] prm;
	private char [] pds;
	private int x1;
	private int x2;

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(final Stage primaryStage) {
		try {
			final Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
			final BorderPane root = new BorderPane();
			final BorderPane rootb = new BorderPane();
			root.setStyle("-fx-background-color: lightblue");
			rootb.setStyle("-fx-background-color: #A9BCF5");
			final MenuBar menuBar = new MenuBar();
			final Menu prmMenu = new Menu("Convertisseur PRM/PDS");
			final Menu pdsMenu = new Menu("Convertisseur PDS/PRM");
			prmMenu.setStyle("-fx-background-color: #A9BCF5");
			pdsMenu.setStyle("-fx-background-color: lightblue");
			menuBar.getMenus().setAll(pdsMenu, prmMenu);
			primaryStage.setWidth(stageWidth);
			primaryStage.setHeight(stageHeight);
			final Scene scene = new Scene(root, stageWidth, stageHeight);
			final Scene sceneb = new Scene(rootb, stageWidth, stageHeight);
			VBox vboxd1 = new VBox();
			vboxd1.getChildren().addAll(new Text("Format PDS incorrect !"));
			vboxd1.setAlignment(Pos.CENTER);
			vboxd1.setPadding(new Insets(15));
			VBox vboxd2 = new VBox();
			vboxd2.getChildren().addAll(new Text("Format PRM incorrect !"));
			vboxd2.setAlignment(Pos.CENTER);
			vboxd2.setPadding(new Insets(15));
			VBox vboxd3 = new VBox();
			vboxd3.getChildren().addAll(new Text("Selectionnez un centre !"));
			vboxd3.setAlignment(Pos.CENTER);
			vboxd3.setPadding(new Insets(15));
			VBox vboxd4 = new VBox();
			vboxd4.getChildren().addAll(new Text("Centre incorrect !"));
			vboxd4.setAlignment(Pos.CENTER);
			vboxd4.setPadding(new Insets(15));
			VBox vboxd5 = new VBox();
			vboxd5.getChildren().addAll(new Text("PRM invalide !"));
			vboxd5.setAlignment(Pos.CENTER);
			vboxd5.setPadding(new Insets(15));
			final ImageView im1 = new ImageView(new Image(getClass()
					.getResource("/icons/btn.png").toExternalForm()));
			final ImageView im2 = new ImageView(new Image(getClass()
					.getResource("/icons/btn.png").toExternalForm()));
			im1.setFitHeight(30);
			im1.setFitWidth(30);
			im2.setFitHeight(30);
			im2.setFitWidth(30);
			final Stage dialog1 = new Stage();
			final Stage dialog2 = new Stage();
			final Stage dialog3 = new Stage();
			final Stage dialog4 = new Stage();
			final Stage dialog5 = new Stage();
			dialog1.initModality(Modality.WINDOW_MODAL);
			dialog1.setScene(new Scene(vboxd1));
			dialog1.initStyle(StageStyle.UTILITY);
			dialog1.setResizable(false);
			dialog1.setWidth(400);
			dialog1.setHeight(100);
			dialog1.getIcons().add(new Image(getClass()
					.getResource("/icons/cross.jpg").toExternalForm()));
			dialog2.initModality(Modality.WINDOW_MODAL);
			dialog2.setScene(new Scene(vboxd2));
			dialog2.initStyle(StageStyle.UTILITY);
			dialog2.setResizable(false);
			dialog2.setWidth(400);
			dialog2.setHeight(100);
			dialog2.getIcons().add(new Image(getClass()
					.getResource("/icons/cross.jpg").toExternalForm()));
			dialog3.initModality(Modality.WINDOW_MODAL);
			dialog3.setScene(new Scene(vboxd3));
			dialog3.initStyle(StageStyle.UTILITY);
			dialog3.setResizable(false);
			dialog3.setWidth(400);
			dialog3.setHeight(100);
			dialog3.getIcons().add(new Image(getClass()
					.getResource("/icons/cross.jpg").toExternalForm()));
			dialog4.initModality(Modality.WINDOW_MODAL);
			dialog4.setScene(new Scene(vboxd4));
			dialog4.initStyle(StageStyle.UTILITY);
			dialog4.setResizable(false);
			dialog4.setWidth(400);
			dialog4.setHeight(100);
			dialog4.getIcons().add(new Image(getClass()
					.getResource("/icons/cross.jpg").toExternalForm()));
			dialog5.initModality(Modality.WINDOW_MODAL);
			dialog5.setScene(new Scene(vboxd5));
			dialog5.initStyle(StageStyle.UTILITY);
			dialog5.setResizable(false);
			dialog5.setWidth(400);
			dialog5.setHeight(100);
			dialog5.getIcons().add(new Image(getClass()
					.getResource("/icons/cross.jpg").toExternalForm()));
			final Button convertBtn1 = new Button("PRM", im1);
			final Button convertBtn2 = new Button("PDS", im2);
			convertBtn1.setStyle("-fx-background-radius: 5em");
			convertBtn2.setStyle("-fx-background-radius: 5em");
			final Label txtSt = new Label("Centre : ");
			final Label txtStb = new Label("Centre : ");
			final Label txtPds = new Label("PDS : ");
			final Label txtPdsb = new Label("PRM : ");
			final Label txtPnr = new Label("PRM : ");
			final Label txtPnrb = new Label("PDS : ");
			final Label txtEdl = new Label("EDL : ");
			final Label txtEdlb = new Label("EDL : ");
			final Label txtTc = new Label("Type client : ");
			final Label txtTcb = new Label("Type client : ");
			final Label txtEn = new Label("Energie : ");
			final Label txtEnb = new Label("Energie : ");
			final Label txtNc = new Label("Compteur : ");
			final Label txtNcb = new Label("Compteur : ");
			ObservableList<String> ob = FXCollections.observableArrayList(
					"Corse",
					"Guadeloupe",
					"Guyane",
					"Martinique",
					"Réunion"
					);
			final ComboBox<String> cb = new ComboBox<String>(ob);
			final TextField cbb = new TextField();
			final TextField txtF1 = new TextField();
			final TextField txtF1b = new TextField();
			final TextField txtF2 = new TextField();
			final TextField txtF3 = new TextField();
			final TextField txtF2b = new TextField();
			final TextField txtF3b = new TextField();
			final TextField txtF5 = new TextField();
			final TextField txtF5b = new TextField();
			final TextField txtF6 = new TextField();
			final TextField txtF6b = new TextField();
			final TextField txtF7 = new TextField();
			final TextField txtF7b = new TextField();
			cb.setStyle("-fx-background-color: white; -fx-border-color: lightgray;"
					+ "-fx-background-radius: 5 5 5 5; -fx-border-radius: 5 5 5 5");
			cbb.setStyle("-fx-control-inner-background: #F0F0F0");
			txtF1.setPromptText("Entrez un PDS");
			txtF1b.setPromptText("Entrez un PRM");
			txtF2.setEditable(false);
			txtF2.setStyle("-fx-control-inner-background: #F0F0F0");
			txtF2b.setEditable(false);
			txtF2b.setStyle("-fx-control-inner-background: #F0F0F0");
			txtF3.setEditable(false);
			txtF3.setStyle("-fx-control-inner-background: #F0F0F0");
			txtF3b.setEditable(false);
			txtF3b.setStyle("-fx-control-inner-background: #F0F0F0");
			txtF5.setEditable(false);
			txtF5.setStyle("-fx-control-inner-background: #F0F0F0");
			txtF5b.setEditable(false);
			txtF5b.setStyle("-fx-control-inner-background: #F0F0F0");
			txtF6.setEditable(false);
			txtF6.setStyle("-fx-control-inner-background: #F0F0F0");
			txtF6b.setEditable(false);
			txtF6b.setStyle("-fx-control-inner-background: #F0F0F0");
			txtF7.setEditable(false);
			txtF7.setStyle("-fx-control-inner-background: #F0F0F0");
			txtF7b.setEditable(false);
			txtF7b.setStyle("-fx-control-inner-background: #F0F0F0");
			txtF6.setPrefSize(35, 20);
			txtF6b.setPrefSize(35, 20);
			txtF7.setPrefSize(30, 20);
			txtF7b.setPrefSize(30, 20);
			HBox hb0 = new HBox();
			hb0.setPadding(new Insets(-280, 50, -280, 50));
			hb0.getChildren().addAll(txtSt, cb);
			hb0.setSpacing(10);
			HBox hb0b = new HBox();
			hb0b.setPadding(new Insets(-100, 45, -100, 45));
			hb0b.getChildren().addAll(txtStb, cbb);
			hb0b.setSpacing(10);
			HBox hb1 = new HBox();
			hb1.setPadding(new Insets(-230, 60, -230, 60));
			hb1.getChildren().addAll(txtPds, txtF1);
			hb1.setSpacing(10);
			HBox hb1b = new HBox();
			hb1b.setPadding(new Insets(-290, 55, -290, 55));
			hb1b.getChildren().addAll(txtPdsb, txtF1b);
			hb1b.setSpacing(10);
			HBox hb2 = new HBox();
			hb2.setPadding(new Insets(-100, 56, -100, 56));
			hb2.getChildren().addAll(txtPnr, txtF2);
			hb2.setSpacing(10);
			HBox hb3 = new HBox();
			hb3.setPadding(new Insets(-50, 60, -50, 60));
			hb3.getChildren().addAll(txtEdl, txtF3);
			hb3.setSpacing(10);
			HBox hb2b = new HBox();
			hb2b.setPadding(new Insets(-150, 60, -150, 60));
			hb2b.getChildren().addAll(txtPnrb, txtF2b);
			hb2b.setSpacing(10);
			HBox hb3b = new HBox();
			hb3b.setPadding(new Insets(-50, 60, -50, 60));
			hb3b.getChildren().addAll(txtEdlb, txtF3b);
			hb3b.setSpacing(10);
			HBox hb4 = new HBox();
			hb4.setPadding(new Insets(0, 22, 0, 22));
			hb4.getChildren().addAll(txtTc, txtF5);
			hb4.setSpacing(10);
			HBox hb4b = new HBox();
			hb4b.setPadding(new Insets(0, 22, 0, 22));
			hb4b.getChildren().addAll(txtTcb, txtF5b);
			hb4b.setSpacing(10);
			HBox hb5 = new HBox();
			hb5.setPadding(new Insets(50, 40, 50, 40));
			hb5.getChildren().addAll(txtEn, txtF6, txtNc, txtF7);
			hb5.setSpacing(10);
			HBox hb5b = new HBox();
			hb5b.setPadding(new Insets(50, 40, 50, 40));
			hb5b.getChildren().addAll(txtEnb, txtF6b, txtNcb, txtF7b);
			hb5b.setSpacing(10);
			VBox vb1 = new VBox(10);
			vb1.setPadding(new Insets(-180, 110, -180, 110));
			vb1.getChildren().addAll(convertBtn1);
			VBox vb2 = new VBox(10);
			vb2.setPadding(new Insets(-230, 110, -230, 110));
			vb2.getChildren().addAll(convertBtn2);
			final Group mvb1 = new Group();
			final Group mvb2 = new Group();
			mvb1.getChildren().addAll(hb1, hb2, hb3, hb4, hb5, hb0, vb1);
			mvb2.getChildren().addAll(hb1b, hb2b, hb3b, hb4b, hb5b, hb0b, vb2);
			convertBtn1.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					try {
						int i, count;
						boolean isCorrect=false;
						String centre=null, edl=null;
						pds = new char[9];
						prm = new char[14];
						if(cb.getSelectionModel().getSelectedIndex() == -1) {
							dialog3.show();
							return;
						}
						else if(nCentre.equals("Guyane"))
							centre = "764";
						else if(nCentre.equals("Réunion"))
							centre = "763";
						else if(nCentre.equals("Martinique"))
							centre = "762";
						else if(nCentre.equals("Guadeloupe"))
							centre = "761";
						else if(nCentre.equals("Corse"))
							centre = "757";
						if(isColored) {
							dialog1.show();
							return;
						}
						StringBuilder zeros = new StringBuilder();
						if((count = txtF1.getText().split("(?!^)").length) <= 9) {
							for(i=0; i<(9-count); i++)
								zeros.append('0');
							setPds(zeros.toString() + txtF1.getText());
						}
						if(Character.toUpperCase(pds_input.charAt(8)) == 'C'
						|| Character.toUpperCase(pds_input.charAt(8)) == 'P') { 
							dialog1.show();
							return;
						}
						for(i=0; i<9; i++) {
							if(i == 6)
								edl = new String(pds);
							pds[i] = pds_input.charAt(i);
							if(Character.toUpperCase(pds_input.charAt(i)) == 'E')
								txtF6.setText("Elec");
							else if(Character.toUpperCase(pds_input.charAt(i)) == 'G')
								txtF6.setText("Gaz");
							if(Character.toUpperCase(pds_input.charAt(i)) == 'C') {
								txtF5.setText("Consommateur");
								isCorrect = true;
							}
							else if(Character.toUpperCase(pds_input.charAt(i)) == 'P') {
								txtF5.setText("Producteur");
								isCorrect = true;
							}
						}
						if(isCorrect) {
							convertPds(pds);
							for(i=0; i<3; i++)
								prm[i] = centre.charAt(i);
							for(i=0; i<9; i++)
								prm[i+3] = pds[i];
							prm[12] = Character.forDigit(getX1(), 10);
							prm[13] = Character.forDigit(getX2(), 10);
							txtF2.setText(new String(prm));
							txtF3.setText(edl.replaceFirst("^0+(?!$)", ""));
							txtF7.setText(String.valueOf(prm[11]));
						}
						else
							dialog1.show();
					} catch(Exception ex) {
						dialog1.show();				
					}
				}
			});
			convertBtn2.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					try {
						int i=0, zCount=0;
						pds = new char[9];
						setPnr(txtF1b.getText());
						String centre = new String(Character.toString(prm_input.charAt(0))
								+ Character.toString(prm_input.charAt(1))
								+ Character.toString(prm_input.charAt(2)));
						if(txtF1b.getText().length() == 14) {
							convertPds((txtF1b.getText().substring(3, 12)).toCharArray());
							String verify = centre + String.valueOf(pds) + x1 + x2;
							if(verify.equals(prm_input)) {
								while(prm_input.charAt(i+3) == '0') {
									zCount++;
									i++;
								}
								if(!isColored) {
									if(centre.toString().equals("764"))
										cbb.setText("Guyane");
									else if(centre.toString().equals("763"))
										cbb.setText("Réunion");
									else if(centre.toString().equals("762"))
										cbb.setText("Martinique");
									else if(centre.toString().equals("761"))
										cbb.setText("Guadeloupe");
									else if(centre.toString().equals("757"))
										cbb.setText("Corse");
									for(i=0; i<9; i++) {
										if(i == 6) {
											txtF3b.setText(new String(pds));
											if(prm_input.charAt(i+3) == '1' && prm_input.charAt(i+4) == '0') {
												pds[i] = 'E';
												pds[i+1] = 'C';
												txtF6b.setText("Elec");
												txtF5b.setText("Consommateur");
											}
											else if(prm_input.charAt(i+3) == '1' && prm_input.charAt(i+4) == '1') {
												pds[i] = 'E';
												pds[i+1] = 'P';
												txtF6b.setText("Elec");
												txtF5b.setText("Producteur");
											}
											else if(prm_input.charAt(i+3) == '2' && prm_input.charAt(i+4) == '0') {
												pds[i] = 'G';
												pds[i+1] = 'C';
												txtF6b.setText("Gaz");
												txtF5b.setText("Consomateur");
											}
											else if(prm_input.charAt(i+3) == '2' && prm_input.charAt(i+4) == '1') {
												pds[i] = 'G';
												pds[i+1] = 'P';
												txtF6b.setText("Gaz");
												txtF5b.setText("Producteur");
											}
										}
										else if(i != 7 && zCount <= 0)
											pds[i] = prm_input.charAt(i+3);
										zCount--;
										txtF7b.setText(String.valueOf(pds[pds.length-1]));
									}
									txtF2b.setText(new String(pds));
								}
								else
									dialog2.show();
							}
							else
								dialog5.show();
						}
						else
							dialog2.show();
					} catch(Exception ex) {
						dialog2.show();
					}
				}
			});
			txtF1.setOnKeyReleased(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent ke) {
					int i, isEG=0, isCP=0, isCn=0;
					boolean isFollowed = false;
					boolean isLetter = false;
					boolean isNumber = false;
					isColored = false;
					nbChar = new ArrayList<String>(Arrays.asList(txtF1.getText().split("(?!^)")));
					if(nbChar.size() > 9) {
						txtF1.setText(txtF1.getText().substring(0, 9));
						txtF1.positionCaret(9);
					}
					for(i=0; i<nbChar.size(); i++) {
						if(!nbChar.get(i).matches("[0-9.]")) {
							if(!nbChar.get(i).toUpperCase().matches("[EGCP]"))
								isLetter = true;
							if(isEG == 1 && isCP == 1)
								isCn += 2;
							isColored = true;
						}
						else if(isFollowed)
							isColored = true;
						else if(isEG == 1 && isCP == 1)
							isCn++;
						else
							isNumber = true;
						if(nbChar.size() >= 2 && nbChar.size() < 10 && isNumber) {
							if(!isLetter && isEG == 0 && nbChar.get(i).toUpperCase().matches("[EG]")) {
								isColored = false;
								isLetter = true;
								isFollowed = true;
								isEG++;
							}
							else if(nbChar.get(i).toUpperCase().matches("[CP]")) {
								if(nbChar.get(i-1).toUpperCase().matches("[EG]"))
									isFollowed = false;
									isCP++;
							}
							if(isEG == 1 && isCP <= 1 && !isFollowed) {
									if(isCn <= 1 && !nbChar.get(i).matches("0"))
										isColored = false;
									else
										isColored = true;
							}
						}
					}
					if(nbChar.size() > 6 
						&& (!nbChar.contains("E") && !nbChar.contains("G"))
						&& (!nbChar.contains("e") && !nbChar.contains("g")))
							isColored = true;
					if(isColored)
						txtF1.setStyle("-fx-text-fill: red");
					else
						txtF1.setStyle("-fx-text-fill: black");
				}
			});
			txtF1b.setOnKeyReleased(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent ke) {
					int i;
					isColored = false;
					nbChar = new ArrayList<String>(Arrays.asList(txtF1b.getText().split("(?!^)")));
					if(nbChar.size() > 14) {
						txtF1b.setText(txtF1b.getText().substring(0, 14));
						txtF1b.positionCaret(14);
					}
					for(i=0; i<nbChar.size(); i++) {
						if(!nbChar.get(i).matches("[0-9.]"))	
							isColored = true;
						if(i == 0 && nbChar.get(0).matches("[0-9.]")
							&&!nbChar.get(0).equals("7"))
							isColored = true;
						if(i == 1 && nbChar.get(1).matches("[0-9.]")
							&& (!nbChar.get(1).equals("5") && !nbChar.get(1).equals("6"))) 
							isColored = true;
						if(i == 2 && nbChar.get(2).matches("[0-9.]") 
							&& (!nbChar.get(2).equals("1") && !nbChar.get(2).equals("2")
							&& !nbChar.get(2).equals("3") && !nbChar.get(2).equals("4")
							&& !nbChar.get(2).equals("7")))
							isColored = true;
						if(i == 9 && !nbChar.get(9).matches("[1-2.]"))
							isColored = true;
						if(i == 10 && !nbChar.get(10).matches("[0-1.]"))
							isColored = true;
					}
					if(isColored)
						txtF1b.setStyle("-fx-text-fill: red");
					else
						txtF1b.setStyle("-fx-text-fill: black");
				}
			});
			cb.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent ae) {
					nCentre = cb.getSelectionModel().getSelectedItem().toString();
					if(isKey)
						txtF1.requestFocus();
				}	
			});
			root.setOnKeyReleased(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					switch(event.getCode()) {
						case UP:
							isMoved--;
							if(isMoved == -1)
								isMoved = 6;
							break;
						case DOWN:
							isMoved++;
							if(isMoved == 7)
								isMoved = 0;
							break;
						case RIGHT:
							try {
								isSwitched = 0;
								if(scene.getFocusOwner() == txtF6)
									isSwitched++;
							} catch(Exception ex) {}
							if(isSwitched > 0) {
								if(isMoved == 5)
									isMoved++;
								else
									isMoved--;
							}
							break;
						case LEFT:
							try {
								isSwitched = 0;
								if(scene.getFocusOwner() == txtF7)
									isSwitched++;
							} catch(Exception ex) {}
							if(isSwitched > 0) {
								if(isMoved == 6)
									isMoved--;
								else
									isMoved++;
							}
							break;
						case ENTER:
							if(scene.getFocusOwner() == convertBtn1)
								convertBtn1.fire();
							else {
								isMoved++;
								if(isMoved == 7)
									isMoved = 0;
							}	
							break;
						default:
							break;
					}
					switch(isMoved) {
						case 0:	txtF1.requestFocus();	break;
						case 1:	convertBtn1.requestFocus();	break;
						case 2:	txtF2.requestFocus();	break;
						case 3:	txtF3.requestFocus();	break;
						case 4:	txtF5.requestFocus();	break;
						case 5:	txtF6.requestFocus();	break;
						case 6:	txtF7.requestFocus();	break;
						default:
							break;
					}
				}
			});
			rootb.setOnKeyReleased(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					switch(event.getCode()) {
						case UP:
							isMoved--;
							if(isMoved == -1)
								isMoved = 7;
							break;
						case DOWN:
							isMoved++;
							if(isMoved == 8)
								isMoved = 0;
							break;
						case RIGHT:
							try {
								isSwitched = 0;
								if(sceneb.getFocusOwner() == txtF6b)
									isSwitched++;
							} catch(Exception ex) {}
							if(isSwitched > 0) {
								if(isMoved == 6)
									isMoved++;
								else
									isMoved--;
							}
							break;
						case LEFT:
							try {
								isSwitched = 0;
								if(sceneb.getFocusOwner() == txtF7b)
									isSwitched++;
							} catch(Exception ex) {}
							if(isSwitched > 0) {
								if(isMoved == 7)
									isMoved--;
								else
									isMoved++;
							}
							break;
						case ENTER:
							if(sceneb.getFocusOwner() == convertBtn2)
								convertBtn2.fire();
							else {
								isMoved++;
								if(isMoved == 8)
									isMoved = 0;
							}
							break;
						default:
							break;
					}
					switch(isMoved) {
						case 0:	txtF1b.requestFocus();	break;
						case 1:	convertBtn2.requestFocus();	break;
						case 2:	txtF2b.requestFocus();	break;
						case 3:	cbb.requestFocus();	break;
						case 4:	txtF3b.requestFocus();	break;
						case 5:	txtF5b.requestFocus();	break;
						case 6:	txtF6b.requestFocus();	break;
						case 7:	txtF7b.requestFocus();	break;
						default:
							break;
					}
				}
			});
			root.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mv) {
					//System.out.println(mv.getY());
					int i;
					dialog1.close();
					dialog2.close();
					dialog3.close();
					dialog4.close();
					if(mv.getY() < 25 && mv.getX() > 150)
						pdsMenu.fire();
					if(mv.getY() > 25 && mv.getY() < 70) {
						cb.requestFocus();
						isKey = true;
					}
					else if(mv.getY() > 70 && mv.getY() < 140) {
						txtF1.requestFocus();
						if(mv.getY() > 70 && mv.getY() < 115 ) {
							for(i=0; i<=txtF1.getText().length(); i++) {
								if(i == txtF1.getText().length() && mv.getX() > 100+(i*7))
									txtF1.positionCaret(i);
								else if(mv.getX() > 100+(i*7) && mv.getX() < 115+(i*7))
									txtF1.positionCaret(i);
							}
						}
						isMoved = 0;
					}
					else if(mv.getY() > 140 && mv.getY() < 220) {
						convertBtn1.requestFocus();
						isMoved = 1;
					}
					else if(mv.getY() > 220 && mv.getY() < 270) {
						txtF2.requestFocus();
						isMoved = 2;
					}
					else if(mv.getY() > 270 && mv.getY() < 320) {
						txtF3.requestFocus();
						isMoved = 3;
					}
					else if(mv.getY() > 330 && mv.getY() < 370) {
						txtF5.requestFocus();
						isMoved = 4;
					}
					else if(mv.getY() > 370 && mv.getX() < 300) {
						txtF6.requestFocus();
						isMoved = 5;
					}
					else if(mv.getY() > 370 && mv.getX() > 300) {
						txtF7.requestFocus();
						isMoved = 6;
					}
				}
			});
			rootb.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent mv) {
					//System.out.println(mv.getY());
					int i;
					dialog1.close();
					dialog2.close();
					dialog3.close();
					dialog4.close();
					if(mv.getY() < 25 && mv.getX() > 7 && mv.getX() < 150)
						prmMenu.fire();
					if(mv.getY() > 25 && mv.getY() < 90) {
						txtF1b.requestFocus();
						if(mv.getY() > 20 && mv.getY() < 70 ) {
							for(i=0; i<=txtF1b.getText().length(); i++) {
								if(i == txtF1b.getText().length() && mv.getX() > 100+(i*7))
									txtF1b.positionCaret(i);
								else if(mv.getX() > 100+(i*7) && mv.getX() < 115+(i*7))
									txtF1b.positionCaret(i);
							}
						}
						isMoved = 0;
					}
					else if(mv.getY() > 90 && mv.getY() < 170) {
						convertBtn2.requestFocus();
						isMoved = 1;
					}
					else if(mv.getY() > 170 && mv.getY() < 220) {
						txtF2b.requestFocus();
						isMoved = 2;
					}
					else if(mv.getY() > 220 && mv.getY() < 270) {
						cbb.requestFocus();
						isMoved = 3;
					}
					else if(mv.getY() > 270 && mv.getY() < 320) {
						txtF3b.requestFocus();
						isMoved = 4;
					}
					else if(mv.getY() > 320 && mv.getY() < 370) {
						txtF5b.requestFocus();
						isMoved = 5;
					}
					else if(mv.getY() > 370 && mv.getX() < 300) {
						txtF6b.requestFocus();
						isMoved = 6;
					}
					else if(mv.getY() > 370 && mv.getX() > 300) {
						txtF7b.requestFocus();
						isMoved = 7;
					}
				}
			});
			prmMenu.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent ae) {
					isKey = isColored = false;
					isMoved = 0;
					nbChar = null;
					cb.valueProperty().set(null);
					txtF1.setText("");
					txtF2.setText("");
					txtF3.setText("");
					txtF5.setText("");
					txtF6.setText("");
					txtF7.setText("");
					txtF1.requestFocus();
					root.setTop(null);
					root.setTop(menuBar);
					root.setCenter(mvb1);
					primaryStage.setScene(scene);
				}
			});
			pdsMenu.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent ae) {
					isKey = isColored = false;
					isMoved = 0;
					nbChar = null;
					cbb.setText("");
					txtF1b.setText("");
					txtF2b.setText("");
					txtF3b.setText("");
					txtF5b.setText("");
					txtF6b.setText("");
					txtF7b.setText("");
					txtF1b.requestFocus();
					rootb.setTop(null);
					rootb.setTop(menuBar);
					rootb.setCenter(mvb2);
					primaryStage.setScene(sceneb);
				}
			});
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					dialog1.close();
					dialog2.close();
					dialog3.close();
					dialog4.close();
				}
			});
			txtF1.requestFocus();
			root.setTop(menuBar);
			root.setCenter(mvb1);
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image(getClass()
					.getResource("/icons/icon.png").toExternalForm()));
			primaryStage.setTitle("Convertisseur");
			primaryStage.setResizable(false);
			primaryStage.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - 300);
			primaryStage.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() - 450);
			primaryStage.show();
			primaryStage.sizeToScene();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void convertPds(char [] pds) {
		// TODO Auto-generated method stub
		int i;
		int x1 = 0, x2 = 0;
		for(i=0; i<pds.length; i++) {
			if(Character.toUpperCase(pds[i]) == 'P' || Character.toUpperCase(pds[i]) == '1')
				x1 += 1;
			else if(Character.toUpperCase(pds[i]) == 'E' || Character.toUpperCase(pds[i]) == '1')
				x1 += 1;
			else if(Character.toUpperCase(pds[i]) == 'G' || Character.toUpperCase(pds[i]) == '2')
				x1 += 2;
			else if(Character.toUpperCase(pds[i]) != 'C')
				x1 += Character.getNumericValue(pds[i]);  
		}
		x1 += 2;
		x1 %= 11;
		for(i=0; i<pds.length; i++) {
			if(Character.toUpperCase(pds[i]) == 'C' || Character.toUpperCase(pds[i]) == '0')
				pds[i] = '0';
			else if(Character.toUpperCase(pds[i]) == 'P' || Character.toUpperCase(pds[i]) == '1') {
				x2 += 1*(pds.length-i+1);
				pds[i] = '1';
			}
			else if(Character.toUpperCase(pds[i]) == 'E' || Character.toUpperCase(pds[i]) == '1') {
				x2 += 1*(pds.length-i+1);
				pds[i] = '1';
			}
			else if(Character.toUpperCase(pds[i]) == 'G' || Character.toUpperCase(pds[i]) == '2') {
				x2 += 2*(pds.length-i+1);
				pds[i] = '2';
			}
			else
				x2 += Character.getNumericValue(pds[i])*(pds.length-i+1);
		}
		x2 += 2;
		x2 %= 11;
		if(x1 == 10 && x2 == 10)	x1 = x2 = 9; 
		if(x1 == 10 && x2 == 0) {
			x1 = 9;
			x2 = 8;				
		}
		if(x1 != 0 && x2 == 10)	x2 = 0;
		if(x1 == 0 && x2 == 10) {
			x1 = 8;
			x2 = 9;				
		}
		if(x1 == 10 && x2 != 0)	x1 = 0;
		this.pds = pds;
		this.x1 = x1;
		this.x2 = x2;
	}
	
	public void setPds(String pds_input) {
		this.pds_input = pds_input;
	}
	
	public void setPnr(String prm_input) {
		this.prm_input = prm_input;
	}
	
	public int getX1() {
		return x1;
	}
	
	public int getX2() {
		return x2;
	} 
}
