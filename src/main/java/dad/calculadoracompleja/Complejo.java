package dad.calculadoracompleja;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Complejo {
    private DoubleProperty real = new SimpleDoubleProperty();
    private DoubleProperty imagin = new SimpleDoubleProperty();


    @Override
    public String toString() {
        return "(" + getReal() + ", " + getImagin() + ")";
    }


    /////////////////////// GETTERS y SETTERS /////////////////////////////

    public double getReal() {
        return real.get();
    }

    public DoubleProperty realProperty() {
        return real;
    }

    public void setReal(double real) {
        this.real.set(real);
    }

    public double getImagin() {
        return imagin.get();
    }

    public DoubleProperty imaginProperty() {
        return imagin;
    }

    public void setImagin(double imagin) {
        this.imagin.set(imagin);
    }
}
