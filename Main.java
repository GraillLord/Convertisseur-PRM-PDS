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
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Application {
	private static int isMoved = 0;
	private static int isSwitched = 0;
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

			/////////////////\\\\\\\\\\\\\\\\\\
			/// INITIALISATION DES ELEMENTS \\\
			/////////////////\\\\\\\\\\\\\\\\\\

			/* BORDERPANES */ 
			//primaryScreenBounds est utilisée pour placer la fenêtre dans un coin 
			final Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
			final BorderPane root = new BorderPane();
			final BorderPane rootb = new BorderPane();
			root.setStyle("-fx-background-color: #061E5F");
			rootb.setStyle("-fx-background-color: #FF9B27");

			/* MENUBAR */
			final MenuBar menuBar = new MenuBar();
			final Menu prmMenu = new Menu("Convertisseur PRM/PDS");
			final Menu pdsMenu = new Menu("Convertisseur PDS/PRM");
			//couleur orange (très clair)
			prmMenu.setStyle("-fx-background-color: #F7D358");
			//couleur bleu-ciel
			pdsMenu.setStyle("-fx-background-color: #01A9D8");
			menuBar.getMenus().setAll(pdsMenu, prmMenu);

			/* SCENES */
			final Scene scene = new Scene(root, stageWidth, stageHeight);
			final Scene sceneb = new Scene(rootb, stageWidth, stageHeight);

			/* VBOXES (contiennent les textes d'erreurs) */
			VBox vboxd1 = new VBox();
			VBox vboxd2 = new VBox();
			VBox vboxd3 = new VBox();
			VBox vboxd4 = new VBox();
			VBox vboxd5 = new VBox();
			InitVbox(vboxd1, "Format PDS incorrect !");
			InitVbox(vboxd2, "Format PRM incorrect !");
			InitVbox(vboxd3, "Selectionnez un centre !");
			InitVbox(vboxd4, "Centre incorrect !");
			InitVbox(vboxd5, "Num\u00e9ro de PRM invalide !");

			/* DIALOGUES D'ERREURS (fenêtres qui contiennent les vboxes) */
			final Stage dialog1 = new Stage();
			final Stage dialog2 = new Stage();
			final Stage dialog3 = new Stage();
			final Stage dialog4 = new Stage();
			final Stage dialog5 = new Stage();
			InitDialog(dialog1, vboxd1);
			InitDialog(dialog2, vboxd2);
			InitDialog(dialog3, vboxd3);
			InitDialog(dialog4, vboxd4);
			InitDialog(dialog5, vboxd5);
			
			/* BOUTTONS ET LEURS ICONES*/
			final ImageView im1 = new ImageView(new Image(getClass()
					.getResource("/icons/cal2.jpg").toExternalForm()));
			final ImageView im2 = new ImageView(new Image(getClass()
					.getResource("/icons/cal3.jpg").toExternalForm()));
			im1.setFitHeight(35);
			im1.setFitWidth(35);
			im2.setFitHeight(35);
			im2.setFitWidth(35);
			final Button convertBtn1 = new Button("", im1);
			final Button convertBtn2 = new Button("", im2);
			convertBtn1.setStyle("-fx-background-color: #ffffff");
			//convertBtn1.setStyle("-fx-background-radius: 5em");
			convertBtn2.setStyle("-fx-background-color: #ffffff");
			//convertBtn2.setStyle("-fx-background-radius: 5em");

			/* LABELS + TEXTVIEWS + COMBOBOXES */
			//bis = b, soit les éléments du 2nd onglet (cf doc pour plus de détails)
			final Label txtSt = new Label("Centre : ");
			txtSt.setTextFill(Color.web("#ffffff"));
			final Label txtStb = new Label("Centre : ");
			txtStb.setTextFill(Color.web("#000000"));
			final Label txtPds = new Label("PDS : ");
			txtPds.setTextFill(Color.web("#ffffff"));
			final Label txtPdsb = new Label("PRM : ");
			txtPdsb.setTextFill(Color.web("#000000"));
			final Label txtPrm = new Label("PRM : ");
			txtPrm.setTextFill(Color.web("#ffffff"));
			final Label txtPrmb = new Label("PDS : ");
			txtPrmb.setTextFill(Color.web("#000000"));
			final Label txtEdl = new Label("EDL : ");
			txtEdl.setTextFill(Color.web("#ffffff"));
			final Label txtEdlb = new Label("EDL : ");
			txtEdlb.setTextFill(Color.web("#000000"));
			final Label txtTc = new Label("Type client : ");
			txtTc.setTextFill(Color.web("#ffffff"));
			final Label txtTcb = new Label("Type client : ");
			txtTcb.setTextFill(Color.web("#000000"));
			final Label txtEn = new Label("Energie : ");
			txtEn.setTextFill(Color.web("#ffffff"));
			final Label txtEnb = new Label("Energie : ");
			txtEnb.setTextFill(Color.web("#000000"));
			final Label txtNc = new Label("Compteur : ");
			txtNc.setTextFill(Color.web("#ffffff"));
			final Label txtNcb = new Label("Compteur : ");
			txtNcb.setTextFill(Color.web("#000000"));
			ObservableList<String> ob = FXCollections.observableArrayList(
				"Corse",
				"Guadeloupe",
				"Guyane",
				"Martinique",
				"R\u00e9union"
			);
			final ComboBox<String> cb = new ComboBox<String>(ob);
			//b signifie "bis" pour la scene2
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
			//combobox blanche, avec les bords gris-clair et des contours arrondis
			cb.setStyle("-fx-background-color: white; -fx-border-color: lightgray;"
					+ "-fx-background-radius: 5 5 5 5; -fx-border-radius: 5 5 5 5");
			//ATTENTION cbb n'est pas "combobox-bis" mais un textview
			cbb.setStyle("-fx-control-inner-background: #F8DFAE");
			cbb.setEditable(false);
			txtF1.setPromptText("Entrez un PDS");
			txtF1b.setPromptText("Entrez un PRM");
			InitText(txtF2, true);
			InitText(txtF2b, false);
			InitText(txtF3, true);
			InitText(txtF3b, false);
            InitText(txtF5, true);
			InitText(txtF5b, false);
			InitText(txtF6, true);
			InitText(txtF6b, false);
			InitText(txtF7, true);
			InitText(txtF7b, false);
			txtF6.setPrefSize(35, 20);
			txtF6b.setPrefSize(35, 20);
			txtF7.setPrefSize(30, 20);
			txtF7b.setPrefSize(30, 20);

			/* CREATION DES CHAMPS (labels + textviews + comboboxes) */
			HBox hb0 = new HBox();
			HBox hb0b = new HBox();
			HBox hb1 = new HBox();
			HBox hb1b = new HBox();
			HBox hb2 = new HBox();
			HBox hb2b = new HBox();
			HBox hb3 = new HBox();
			HBox hb3b = new HBox();
			HBox hb4 = new HBox();
			HBox hb4b = new HBox();
			HBox hb5 = new HBox();
			HBox hb5b = new HBox();
			hb0.setPadding(new Insets(-280, 50, -280, 50));
		    hb0.getChildren().addAll(txtSt, cb);
		    hb0.setSpacing(10);
            InitHBoxes(hb0b, new Insets(-100, 45, -100, 45), txtStb, cbb);
            InitHBoxes(hb1, new Insets(-230, 60, -230, 60), txtPds, txtF1);
            InitHBoxes(hb1b, new Insets(-290, 55, -290, 55), txtPdsb, txtF1b);
            InitHBoxes(hb2, new Insets(-100, 56, -100, 56), txtPrm, txtF2);
            InitHBoxes(hb2b, new Insets(-150, 60, -150, 60), txtPrmb, txtF2b);
            InitHBoxes(hb3, new Insets(-50, 60, -50, 60), txtEdl, txtF3);
            InitHBoxes(hb3b, new Insets(-50, 60, -50, 60), txtEdlb, txtF3b);
            InitHBoxes(hb4, new Insets(0, 22, 0, 22), txtTc, txtF5);
            InitHBoxes(hb4b, new Insets(0, 22, 0, 22), txtTcb, txtF5b);
			hb5.setPadding(new Insets(50, 40, 50, 40));
			hb5.getChildren().addAll(txtEn, txtF6, txtNc, txtF7);
			hb5.setSpacing(10);
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

            ////////////////\\\\\\\\\\\\\\\\
            /// ACTIONS SUR LES ELEMENTS \\\
            ////////////////\\\\\\\\\\\\\\\\

			/* ACTION QUAND CLICK SUR BOUTTON CONVERTIR PDS EN PRM */
			convertBtn1.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					try {
						//count permet de compter le nombre d'éléments du pds saisi
						int i, count;
						boolean isCorrect=false;
						String centre=null, edl=null;
						pds = new char[9];
						prm = new char[14];
						//si l'utilisateur n'a pas selectionné d'élément dans la combobox alors erreur
						if(cb.getSelectionModel().getSelectedIndex() == -1) {
							reinitValues(txtF2, txtF3, txtF5, txtF6, txtF7);
							dialog3.show();
							return;
						}
						//variable centre prend une valeur en fonction de la selection de la combobox
						else if(nCentre.equals("Guyane"))
							centre = "764";
						else if(nCentre.equals("R\u00e9union"))
							centre = "763";
						else if(nCentre.equals("Martinique"))
							centre = "762";
						else if(nCentre.equals("Guadeloupe"))
							centre = "761";
						else if(nCentre.equals("Corse"))
							centre = "757";
						if(isColored) {
							reinitValues(txtF2, txtF3, txtF5, txtF6, txtF7);
							dialog1.show();
							return;
						}
						//ajout de 0 au pds jusqu'à avoir une taille de pds max (9) 
						StringBuilder zeros = new StringBuilder();
						if((count = txtF1.getText().length()) <= 9) {
							for(i=0; i<(9-count); i++)
								zeros.append('0');
							setPds(zeros.toString() + txtF1.getText());
						}
						//controle sur la présence du numero de compteur (dernier element)
						if(Character.toUpperCase(pds_input.charAt(8)) == 'C'
						|| Character.toUpperCase(pds_input.charAt(8)) == 'P') {
						    reinitValues(txtF2, txtF3, txtF5, txtF6, txtF7); 
							dialog1.show();
							return;
						}
						for(i=0; i<9; i++) {
							//edl = 6 premiers elements du pds (avec 0 inclus)
							if(i == 6) {
								edl = new String(pds).replaceFirst("^0+(?!$)", "");
								if(edl.length() <= 3) {
									reinitValues(txtF2, txtF3, txtF5, txtF6, txtF7);
							    	dialog1.show();
									return;
								}
							}
							//remplissage du pds
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
						//controle secondaire sur la saisie avec isCorrect (cf doc pour plus de details)
						if(isCorrect) {
							convertPds(pds);
							for(i=0; i<3; i++)
								prm[i] = centre.charAt(i);
							for(i=0; i<9; i++)
								prm[i+3] = pds[i];
							//remplissage des 2 derniers éléments du tab prm correspondant aux clées
							prm[12] = Character.forDigit(getX1(), 10);
							prm[13] = Character.forDigit(getX2(), 10);
							txtF2.setText(new String(prm));
							txtF3.setText(edl.replaceFirst("^0+(?!$)", ""));
							txtF7.setText(String.valueOf(prm[11]));
						}
						else {
							reinitValues(txtF2, txtF3, txtF5, txtF6, txtF7);
							dialog1.show();
						}
					} catch(Exception ex) {
						reinitValues(txtF2, txtF3, txtF5, txtF6, txtF7);
						dialog1.show();				
					}
				}
			});

			/* ACTION QUAND CLICK SUR BOUTTON CONVERTIR PRM EN PDS */
			convertBtn2.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					try {
						//zcount permet de compter le nombre de 0
						int i=0, zCount=0;
						pds = new char[9];
						setPrm(txtF1b.getText());
						//centre prend comme valeur les 3 permiers éléments du champ prm
						String centre = new String(Character.toString(prm_input.charAt(0))
								+ Character.toString(prm_input.charAt(1))
								+ Character.toString(prm_input.charAt(2)));
						//controle sur la longeur du prm (14)
						if(txtF1b.getText().length() == 14) {
							//controle sur le prm saisi (cf doc pour plus de détails)
							convertPds((txtF1b.getText().substring(3, 12)).toCharArray());
							String verify = centre + String.valueOf(pds) + x1 + x2;
							if(verify.equals(prm_input)) {
								while(prm_input.charAt(i+3) == '0') {
									zCount++;
									i++;
								}
								//controle sur la saisie (cf doc pour plus de détails)
								if(!isColored) {
									if(centre.toString().equals("764"))
										cbb.setText("Guyane");
									else if(centre.toString().equals("763"))
										cbb.setText("R\u00e9union");
									else if(centre.toString().equals("762"))
										cbb.setText("Martinique");
									else if(centre.toString().equals("761"))
										cbb.setText("Guadeloupe");
									else if(centre.toString().equals("757"))
										cbb.setText("Corse");
									//boucle qui permet de remplir pds (on ne prend pas en compte le num de centre s'ou i+3)
									for(i=0; i<9; i++) {
										if(i == 6) {
											String edl = new String(pds);
											//enlève les 0 inutiles au début et les 3 derniers elements du pds (lettres + num compteur)
											if(edl.substring(0, edl.length()-3).replaceFirst("^0+(?!$)", "").equals("0")) {
												reinitValues(cbb, txtF2b, txtF3b, txtF5b, txtF6b, txtF7b);
												dialog2.show();
												return;
											}
											txtF3b.setText(edl.substring(0, edl.length()-3).replaceFirst("^0+(?!$)", ""));
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
										//num de compteur
										txtF7b.setText(String.valueOf(pds[pds.length-1]));
									}
									//num de pds en supprimant les 0 inutiles du début
									txtF2b.setText(new String(pds).replaceFirst("^0+(?!$)", ""));
								}
								else {
									reinitValues(cbb, txtF2b, txtF3b, txtF5b, txtF6b, txtF7b);
									dialog2.show();
								}
							}
							else {
								reinitValues(cbb, txtF2b, txtF3b, txtF5b, txtF6b, txtF7b);
								dialog5.show();
							}
						}
						else {
							reinitValues(cbb, txtF2b, txtF3b, txtF5b, txtF6b, txtF7b);
							dialog2.show();
						}
					} catch(Exception ex) {
						reinitValues(cbb, txtF2b, txtF3b, txtF5b, txtF6b, txtF7b);
						dialog2.show();
					}
				}
			});

			/* ACTION QUAND TOUCHE PRESSEE & CONTROLE DE LA SAISIE SUR LE PDS */ 
			txtF1.setOnKeyReleased(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent ke) {
					//isEG, isCP et isCn correspondent aux lettres et au num de compteur
					int i, isEG=0, isCP=0, isCn=0;
					//isFollowed sert de controle à isEG et isCP
					//(true quand isEG=1 && isCP=0 ; false quand isEG=isCP=0 ou isEG=isCP=1)
					boolean isFollowed = false;
					//permet de controller si c'est une lettre autre que e,g,c,p
					boolean isLetter = false;
					//permet de controller si c'est un nombre
					boolean isNumber = false;
					isColored = false;
					nbChar = new ArrayList<String>(Arrays.asList(txtF1.getText().split("(?!^)")));
					//permet de ne pas afficher plus de 9 characteres et positionne le caret à la fin
					if(nbChar.size() > 9) {
						txtF1.setText(txtF1.getText().substring(0, 9));
						txtF1.positionCaret(9);
					}
					//série de controles sur la saisie (soit isColored reste false soit il devient true)
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
								isLetter = true;
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
					//colore le texte en fonction de isColored
					if(isColored)
						txtF1.setStyle("-fx-text-fill: red");
					else
						txtF1.setStyle("-fx-text-fill: black");
				}
			});

			/* ACTION QUAND TOUCHE PRESSEE & CONTROLE DE LA SAISIE SUR LE PRM */
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

			/* ACTION SUR LA COMBOBOX */
			cb.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent ae) {
					nCentre = cb.getSelectionModel().getSelectedItem().toString();
					//focus redirigé vers le texte en dessous après une selection
					txtF1.requestFocus();
				}	
			});

			/* ACTION QUAND UNE TOUCHE EST PRESSE SUR LA SCENE1 */
			root.setOnKeyReleased(new EventHandler<KeyEvent>() {
				@Override
				//permet de deplacer le focus des éléments en fonctions des touches
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
						//si le focus est sur le texte1 ou le bouton, lance convertBtn1
						case ENTER:
							if(scene.getFocusOwner() == txtF1 || scene.getFocusOwner() == convertBtn1)
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
					//permet de placer le focus en fonction de la valeur de isMoved
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

			/* ACTION QUAND UNE TOUCHE EST PRESSE SUR LA SCENE2 */
			rootb.setOnKeyReleased(new EventHandler<KeyEvent>() {
				@Override
				//permet de deplacer le focus des éléments en fonctions des touches
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
						//si le focus est sur le texte1 ou le bouton, lance convertBtn1
						case ENTER:
							if(sceneb.getFocusOwner() == txtF1b || sceneb.getFocusOwner() == convertBtn2)
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
					//permet de placer le focus en fonction de la valeur de isMoved
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

			/* ACTION QUAND CLICK SUR LA SCENE1 */
			root.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
				@Override
				//permet de placer le focus sur les éléments en fonction de l'endroit ou l'on clique sur la scene
				public void handle(MouseEvent mv) {
					int i;
					//ferme tous les dialogues si click ailleur sur la scene1
					dialog1.close();
					dialog2.close();
					dialog3.close();
					dialog4.close();
					dialog5.close();
					if(mv.getY() < 25 && mv.getX() > 150)
						pdsMenu.fire();
					if(mv.getY() > 25 && mv.getY() < 70) {
						cb.requestFocus();
					}
					else if(mv.getY() > 70 && mv.getY() < 140) {
						txtF1.requestFocus();
						//gère la position du caret dans le texte
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
					else if(mv.getY() > 140 && mv.getY() < 200) {
						convertBtn1.requestFocus();
						isMoved = 1;
					}
					else if(mv.getY() > 200 && mv.getY() < 250) {
						txtF2.requestFocus();
						isMoved = 2;
					}
					else if(mv.getY() > 250 && mv.getY() < 300) {
						txtF3.requestFocus();
						isMoved = 3;
					}
					else if(mv.getY() > 300 && mv.getY() < 350) {
						txtF5.requestFocus();
						isMoved = 4;
					}
					else if(mv.getY() > 350 && mv.getX() < 300) {
						txtF6.requestFocus();
						isMoved = 5;
					}
					else if(mv.getY() > 350 && mv.getX() > 300) {
						txtF7.requestFocus();
						isMoved = 6;
					}
				}
			});

			/* ACTION QUAND CLICK SUR LA SCENE2 */
			rootb.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
				//permet de placer le focus sur les éléments en fonction de l'endroit ou l'on clique sur la scene
				@Override
				public void handle(MouseEvent mv) {
					int i;
					//ferme tous les dialogues si click ailleur sur la scene1
					dialog1.close();
					dialog2.close();
					dialog3.close();
					dialog4.close();
					dialog5.close();
					if(mv.getY() < 25 && mv.getX() > 7 && mv.getX() < 150)
						prmMenu.fire();
					if(mv.getY() > 25 && mv.getY() < 90) {
						txtF1b.requestFocus();
						//gère la position du caret dans le texte
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
					else if(mv.getY() > 70 && mv.getY() < 150) {
						convertBtn2.requestFocus();
						isMoved = 1;
					}
					else if(mv.getY() > 150 && mv.getY() < 200) {
						txtF2b.requestFocus();
						isMoved = 2;
					}
					else if(mv.getY() > 200 && mv.getY() < 250) {
						cbb.requestFocus();
						isMoved = 3;
					}
					else if(mv.getY() > 250 && mv.getY() < 300) {
						txtF3b.requestFocus();
						isMoved = 4;
					}
					else if(mv.getY() > 300 && mv.getY() < 350) {
						txtF5b.requestFocus();
						isMoved = 5;
					}
					else if(mv.getY() > 350 && mv.getX() < 300) {
						txtF6b.requestFocus();
						isMoved = 6;
					}
					else if(mv.getY() > 350 && mv.getX() > 300) {
						txtF7b.requestFocus();
						isMoved = 7;
					}
				}
			});

			/* ACTION QUAND CLICK SUR LE MENU PRM */
			prmMenu.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				//réinitialise la page avec tous les éléments
				public void handle(ActionEvent ae) {
					isColored = false;
					isMoved = 0;
					nbChar = null;
					//Attention nullpointerexception sur 32-Bit!
					//cb.valueProperty().set(null);
					reinitValues(txtF1, txtF2, txtF3, txtF5, txtF6, txtF7);
					txtF1.requestFocus();
					root.setTop(null);
					root.setTop(menuBar);
					root.setCenter(mvb1);
					primaryStage.setScene(scene);
				}
			});

			/* ACTION QUAND CLICK SUR LE MENU PDS */
			pdsMenu.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				//réinitialise la page avec tous les éléments
				public void handle(ActionEvent ae) {
					isColored = false;
					isMoved = 0;
					nbChar = null;
					reinitValues(cbb, txtF1b, txtF2b, txtF3b, txtF5b, txtF6b, txtF7b);
					txtF1b.requestFocus();
					rootb.setTop(null);
					rootb.setTop(menuBar);
					rootb.setCenter(mvb2);
					primaryStage.setScene(sceneb);
				}
			});

			/* ACTION QUAND ON FERME LA FENETRE */
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				//ferme tous les dialogues si fermeture de l'application
				public void handle(WindowEvent event) {
					dialog1.close();
					dialog2.close();
					dialog3.close();
					dialog4.close();
					dialog5.close();
				}
			});

			//////////////\\\\\\\\\\\\\ 
			/// DEMARAGE DE L'APPLI \\\
			//////////////\\\\\\\\\\\\\

			/* AJOUTER & AFFICHER LES ELEMENTS */
			txtF1.requestFocus();
			root.setTop(menuBar);
			root.setCenter(mvb1);
			primaryStage.setWidth(stageWidth);
			primaryStage.setHeight(stageHeight);
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image(getClass()
					.getResource("/icons/cal1.jpg").toExternalForm()));
			primaryStage.setTitle("Convertisseur");
			primaryStage.setResizable(false);
			//place le stage dans le coin en bas à droite de windows
			primaryStage.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - 300);
			primaryStage.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() - 450*1.5);
			primaryStage.show();
			//doit etre placé apres stage.show et permet de corriger les problèmes de fenêtrage 
			primaryStage.sizeToScene();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	//////////////\\\\\\\\\\\\\ 
	/// METHODES DE L'APPLI \\\
	//////////////\\\\\\\\\\\\\

	public void InitVbox(VBox arg, String str) {
		arg.getChildren().addAll(new Text(str));
		arg.setAlignment(Pos.CENTER);
		arg.setPadding(new Insets(15));
	}

	public void InitDialog(Stage arg, VBox vb) {
		arg.initModality(Modality.WINDOW_MODAL);
		arg.setScene(new Scene(vb));
		arg.initStyle(StageStyle.UTILITY);
		arg.setResizable(false);
		arg.setWidth(400);
		arg.setHeight(100);
	}

	public void InitText(TextField arg, boolean myCol) {
		if(myCol)
	    	arg.setStyle("-fx-control-inner-background: #CBD8FB");
	    else
			arg.setStyle("-fx-control-inner-background: #F8DFAE");
	    arg.setEditable(false);
	}

	public void InitHBoxes(HBox arg, Insets ins, Label lab, TextField tf) {
		arg.setPadding(ins);
		arg.getChildren().addAll(lab, tf);
		arg.setSpacing(10);
	}

	public void reinitValues(TextField... args) {
		int i;
		for(i=0; i<args.length; i++)
			args[i].setText("");
	}
	
	/* ALGO DE CONVERTION PDS -> PRM */
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
	
	/* GETTERS & SETTERS (Si création d'autres classes à l'avenir) */
	public void setPds(String pds_input) {
		this.pds_input = pds_input;
	}
	
	public void setPrm(String prm_input) {
		this.prm_input = prm_input;
	}
	
	public int getX1() {
		return x1;
	}
	
	public int getX2() {
		return x2;
	} 
}
