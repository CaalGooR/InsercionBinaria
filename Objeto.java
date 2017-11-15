/*
* Esta clase la cual hereda de un componente grafico "JLabel", ayuda a almacenar
* datos enteros y la localización de dicho componente por medio de la clase
* "Point"
*/
import javax.swing.JLabel;
import java.awt.Point;

public class Objeto extends JLabel {

  private int dato;
  private Point p;

  public Objeto (int dt) {
    this.dato = dt;
    this.setSize(20,10);
    this.setText(""+dato);
  }

  public int getDato(){
    return this.dato;
  }

  public Point getPoint(){
    return this.p;
  }

  public void setDato(int dt){
    this.dato = dt;
  }

  public void setPoint(){
    this.p = this.getLocation();
  }

}
