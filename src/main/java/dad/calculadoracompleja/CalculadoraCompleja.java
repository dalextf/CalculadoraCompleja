package dad.calculadoracompleja;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class CalculadoraCompleja extends Application {

    private TextField primReal;
    private TextField segReal;
    private TextField resuReal;
    private TextField primImagin;
    private TextField segImagin;
    private TextField resuImagin;


    private TextField aText, bText, cText, dText, resuRealText, resulImaginText;
    private String suma = "+", resta = "-", multiplicacion = "*", division = "/";
    private String[] operandos = {suma, resta, multiplicacion, division};
    private ComboBox<String> operandosCombo;
    private Separator lineaSeparator;
    private Complejo primerComplejo, segundoComplejo, resultadoComplejo;

    @Override
    public void start(Stage primaryStage) throws Exception {
        operandosCombo = new ComboBox<String>();
        operandosCombo.getItems().addAll(operandos);
        operandosCombo.getSelectionModel().selectFirst();

        primerComplejo = new Complejo();
        segundoComplejo = new Complejo();
        resultadoComplejo = new Complejo();

        aText = new TextField("0");
        aText.setMaxWidth(50);

        bText = new TextField("0");
        bText.setMaxWidth(50);

        cText = new TextField("0");
        cText.setMaxWidth(50);

        dText = new TextField("0");
        dText.setMaxWidth(50);

        lineaSeparator = new Separator();
        lineaSeparator.setMaxWidth(50);

        resuRealText = new TextField("0");
        resuRealText.setMaxWidth(50);
        resuRealText.setDisable(true);

        resulImaginText = new TextField("0");
        resulImaginText.setMaxWidth(50);
        resulImaginText.setDisable(true);

        VBox operandosVbox = new VBox();
        operandosVbox.setAlignment(Pos.CENTER);
        operandosVbox.setSpacing(5);
        operandosVbox.getChildren().addAll(operandosCombo);

        HBox primeraOperacionBox = new HBox();
        primeraOperacionBox.setAlignment(Pos.CENTER);
        primeraOperacionBox.setSpacing(5);
        primeraOperacionBox.getChildren().addAll(aText, new Label("+"), bText, new Label("i"));

        HBox segundaOperacionBox = new HBox();
        segundaOperacionBox.setAlignment(Pos.CENTER);
        segundaOperacionBox.setSpacing(5);
        segundaOperacionBox.getChildren().addAll(cText, new Label("+"), dText, new Label("i"));

        HBox resultadoBox = new HBox();
        resultadoBox.setAlignment(Pos.CENTER);
        resultadoBox.setSpacing(5);
        resultadoBox.getChildren().addAll(resuRealText, new Label("+"), resulImaginText, new Label("i"));

        VBox operacionesVbox = new VBox();
        operacionesVbox.setAlignment(Pos.CENTER);
        operacionesVbox.setSpacing(5);
        operacionesVbox.getChildren().addAll(primeraOperacionBox, segundaOperacionBox, lineaSeparator, resultadoBox);

        HBox root = new HBox();
        root.setSpacing(5);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(operandosVbox, operacionesVbox);

        Scene scene = new Scene(root, 320, 230);

        primaryStage.setTitle("CalculadoraView.fxml");
        primaryStage.setScene(scene);
        primaryStage.show();

        aText.textProperty().bindBidirectional(primerComplejo.realProperty(), new NumberStringConverter());
        bText.textProperty().bindBidirectional(primerComplejo.imaginProperty(), new NumberStringConverter());
        cText.textProperty().bindBidirectional(segundoComplejo.realProperty(), new NumberStringConverter());
        dText.textProperty().bindBidirectional(segundoComplejo.imaginProperty(), new NumberStringConverter());

        resuRealText.textProperty().bindBidirectional(resultadoComplejo.realProperty(), new NumberStringConverter());
        resulImaginText.textProperty().bindBidirectional(resultadoComplejo.imaginProperty(),
                new NumberStringConverter());

        operandosCombo.getSelectionModel().selectedIndexProperty().addListener((o, ov, nv) -> {
            operacionesComplejas();
        });
    }

    private void operacionesComplejas() {
        if (operandosCombo.getSelectionModel().getSelectedIndex() == 0) {
            resultadoComplejo.realProperty().bind(primerComplejo.realProperty().add(segundoComplejo.realProperty()));
            resultadoComplejo.imaginProperty()
                    .bind(primerComplejo.imaginProperty().add(segundoComplejo.imaginProperty()));
        } else if (operandosCombo.getSelectionModel().getSelectedIndex() == 1) {
            resultadoComplejo.realProperty()
                    .bind(primerComplejo.realProperty().subtract(segundoComplejo.realProperty()));
            resultadoComplejo.imaginProperty()
                    .bind(primerComplejo.imaginProperty().subtract(segundoComplejo.imaginProperty()));
        } else if (operandosCombo.getSelectionModel().getSelectedIndex() == 2) {
            resultadoComplejo.realProperty()
                    .bind(primerComplejo.realProperty().multiply(segundoComplejo.realProperty()));
            resultadoComplejo.imaginProperty()
                    .bind(primerComplejo.imaginProperty().multiply(segundoComplejo.imaginProperty()));
        } else if (operandosCombo.getSelectionModel().getSelectedIndex() == 3) {
            resultadoComplejo.realProperty()
                    .bind((primerComplejo.realProperty().multiply(segundoComplejo.realProperty())).add((primerComplejo
                            .imaginProperty()
                            .multiply(segundoComplejo.imaginProperty()
                                    .divide(segundoComplejo.realProperty().multiply(segundoComplejo.realProperty()))
                                    .add((segundoComplejo.imaginProperty()
                                            .multiply(segundoComplejo.imaginProperty())))))));
            resultadoComplejo.imaginProperty()
                    .bind((primerComplejo.imaginProperty().multiply(segundoComplejo.realProperty()))
                            .subtract((primerComplejo.realProperty().multiply(segundoComplejo.imaginProperty()
                                    .divide(segundoComplejo.realProperty().multiply(segundoComplejo.realProperty()))
                                    .add((segundoComplejo.imaginProperty()
                                            .multiply(segundoComplejo.imaginProperty())))))));
        }


    }

    public static void main(String[] args) {
        launch(args);
    }
}
