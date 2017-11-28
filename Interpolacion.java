/**
* Esta clase contiene un método numérico llamado "Interpolación Cuadrática de Newton"
* nos permite calcular por medio de 3 puntos, la función para poder generar
* animación parabólica negativa o transición horizontal.
*/

import java.awt.Point;
import java.lang.Runnable.*;
import javax.swing.JLabel;
import java.lang.Thread;

public class Interpolacion implements Runnable {

    private double fx;    // Almacena resultado del modelo matemático.
    private double b0;    // Almacena Parametro modelo matemático.
    private double b1;    // Almacena Parametro modelo matemático.
    private double b2;    // Almacena Parametro modelo matemático.
    private Point p0;     // Almacena Punto Inicial del componente que se desea animar.
    private Point p1;     // Almacena Punto medio entre punto inicial y final con incremento constante en eje Y.
    private Point p2;     // Almacena Punto en el que se desea terminar la animación.
    public JLabel objeto; // Almacena componente que se desea animar.

    public Interpolacion(JLabel obj,Point ptn0,Point ptn1,Point ptn2) {
      this.objeto = obj;
      this.p0 = ptn0.getLocation();
      this.p1 = ptn1;
      this.p2 = ptn2;
      this.setb0(p0);
      this.setb1(p0,p1);
      this.setb2(p0,p1,p2);
    }

    public void run() {
      plot();
    }

    public void plot() {

      int x = this.getPoint0().x; //  Variable "x" optiene la posición en x del objeto que se desea animar.
      double b_0 = this.getb0();  // Almacena Parametro modelo matemático.
      double b_1 = this.getb1();  // Almacena Parametro modelo matemático.
      double b_2 = this.getb2();  // Almacena Parametro modelo matemático.

      /**
      *  Si el objeto se encuentra a la izquierda de punto 2, genera animacion
      *  parabólica, de lo contrario una animacion horizontal negativa.
      */
      if(x < this.getPoint2().x) {
        while( this.objeto.getLocation().x < this.getPoint2().x
              || this.objeto.getLocation().y < this.getPoint2().y ) {
          try {
              Thread.sleep(10);
          } catch(InterruptedException e) {
            System.out.println("InterruptedException");
          }

          /*  Calculo del fx */
          this.fx = b_0 +(b_1 * (x - this.getPoint0().x))
                          + (b_2 * (x - this.getPoint0().x)
                                  * (x - this.getPoint1().x));
          this.objeto.setLocation(x,(int) fx);
          x++;
        }
      }

      else {
        while((this.objeto.getLocation().x > this.getPoint2().x)) {
          x--;
          this.objeto.setLocation(x+objeto.getWidth()/2,objeto.getLocation().y);
          try {
            Thread.sleep(10);
          } catch(InterruptedException e) {
            System.out.println("InterruptedException");
          }
        }
      }

      this.objeto.setLocation(this.getPoint2().x+objeto.getWidth(),600);        /* Forzamos Animacion */
    }

    public Point getPoint0() {
      return this.p0;
    }

    public Point getPoint1() {
      return this.p1;
    }

    public Point getPoint2() {
      return this.p2;
    }

    public double getb0() {
      return this.b0;
    }

    public double getb1() {
      return this.b1;
    }

    public double getb2() {
      return this.b2;
    }

    public void setb0(Point ptn0) { // Calculo de parametros para modelo b0
      this.b0 = ptn0.y;
    }

    public void setb1(Point ptn0,Point ptn1) { // Calculo de parametros para modelo b1
      this.b1 = (ptn1.y - ptn0.y)/(ptn1.x - ptn0.x);
    }

    public void setb2(Point ptn0,Point ptn1,Point ptn2) {  // Calculo de parametros para modelo b2
      this.b2 = (((ptn2.y - ptn1.y)/(ptn2.x - ptn1.x)) - (this.getb1())) / (ptn2.x -ptn0.x);
    }

}
