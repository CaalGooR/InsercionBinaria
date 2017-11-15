/*
* La clase ventana carga los componentes gráficos y crea las instancias y coloca
* los números a ordenar de manera responsiva.
* También se encarga de realizar las instancias y la iniciación del método de
* ordenamiento.
*/

import javax.swing.JFrame;
import java.lang.Thread;
import java.util.ArrayList;
import java.awt.Color;

public class Ventana extends JFrame {

  private int incremento;
  private Objeto primero;
  private Objeto centro;
  private Objeto ultimo;
  private Objeto auxiliar;
  private ArrayList<Objeto> obj;

  // "array" vector que se desea ordenar, este vector también ayuda a establecer
  // la cantidad de componentes ya que los números en el vector es proporcional
  // a los componentes del JFrame.
  public int array[] = {4,8,100,7,23,62,11,1,45};

  public Ventana() {
    this.setSize(600,700);
    this.setResizable(false);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setVisible(true);
    this.setLayout(null);

    obj = new ArrayList <Objeto>();
    primero = new Objeto(0);
    centro = new Objeto(0);
    ultimo = new Objeto(0);
    auxiliar = new Objeto(0);
    inicializarObjetos();
    new Ordenamiento(primero,centro,ultimo,auxiliar,obj,array);
  }

  private void inicializarObjetos() {
    // Componente Limite superior Izquierdo
    primero.setBounds(10,10,100,20);
    primero.setText("Inicio = "+primero.getDato());
    primero.setOpaque(true);
    primero.setBackground(Color.RED);
    centro.setBounds(120,10,100,20);
    centro.setText("Centro = "+centro.getDato());
    centro.setOpaque(true);
    centro.setBackground(Color.PINK);
    ultimo.setBounds(230,10,100,20);
    ultimo.setText("Ultimo = "+ultimo.getDato());
    ultimo.setOpaque(true);
    ultimo.setBackground(Color.YELLOW);
    // Componente central
    auxiliar.setBounds(230,400,100,20);
    auxiliar.setText("Auxiliar = "+auxiliar.getDato());
    auxiliar.setOpaque(true);
    auxiliar.setBackground(Color.ORANGE);

    //  Variable "incremento" ayuda a ajustar a una distancia adecuada los componentes
    //  de la clase Objeto.java
    incremento = ((this.getWidth())/(array.length-1))-10;
    // Margen los objetos con respecto a los limites del JFrame
    int x = 5;
    for (int i = 0 ;i<array.length;i++) {
      Objeto objInicial = new Objeto(array[i]);
      obj.add(objInicial);
    }

    this.add(primero);
    this.add(centro);
    this.add(ultimo);
    this.add(auxiliar);

    for(Objeto object: obj) {
      object.setLocation(x,600);
      object.setOpaque(true);
      this.add(object);
      x = incremento + object.getLocation().x;
    }
  }


}
