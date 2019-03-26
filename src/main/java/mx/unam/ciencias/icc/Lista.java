package mx.unam.ciencias.icc;

import java.util.NoSuchElementException;

/**
 * <p>Clase para listas genéricas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas son iterables utilizando sus nodos. Las listas no aceptan a
 * <code>null</code> como elemento.</p>
 *
 * @param <T> El tipo de los elementos de la lista.
 */
public class Lista<T> {

    /**
     * Clase interna para nodos.
     */
    public class Nodo {

        /* El elemento del nodo. */
        private T elemento;
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nodo con un elemento. */
        private Nodo(T elemento) {
            this.elemento = elemento;
        }

        /**
         * Regresa el nodo anterior del nodo.
         * @return el nodo anterior del nodo.
         */
        public Nodo getAnterior() {
            return this.anterior;
        }

        /**
         * Regresa el nodo siguiente del nodo.
         * @return el nodo siguiente del nodo.
         */
        public Nodo getSiguiente() {
            return this.siguiente;
        }

        /**
         * Regresa el elemento del nodo.
         * @return el elemento del nodo.
         */
        public T get() {
            return this.elemento;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        longitud = 0;
        if(cabeza == null)
        longitud = 0;
        Nodo n = cabeza;
        while(n!= null){
         longitud ++;
         n = n.siguiente;
        }
        return longitud;
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean esVacia() {
        return longitud == 0;
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(T elemento) {
        if(elemento == null){
          throw new IllegalArgumentException();
        }
        if(elemento != null){
        Nodo n = new Nodo(elemento);
        longitud++;
        if(rabo == null){
          cabeza=rabo=n;
          }else {
          rabo.siguiente = n;
          n.anterior = rabo;
          rabo = n;
        }
      }
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(T elemento) {
        if(elemento == null){
          throw new IllegalArgumentException();
        }
        if(elemento != null){
        Nodo n = new Nodo(elemento);
        longitud++;
        if(cabeza == null){
          cabeza=rabo=n;
          }else {
          cabeza.anterior = n;
          n.siguiente = cabeza;
          cabeza = n;
        }
      }
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio de la lista, y si es mayor o igual
     *          que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void inserta(int i, T elemento) {
        if(elemento == null){
          throw new IllegalArgumentException();
        }
        if(longitud == 0) {
          agregaFinal(elemento);
          return;
        } else if(i >= longitud) {
          agregaFinal(elemento);
          return;
        } else if(i <= 0){
          agregaInicio(elemento);
          return;
        }
            longitud++;
            Nodo n = cabeza;
                int j = 0;
                while(n!=null && j<i){
                    n = n.siguiente;
                    j++;
                  }
                Nodo m = new Nodo(elemento);
                m.siguiente = n;
                m.anterior = n.anterior;
                (n.anterior).siguiente = m;
                n.anterior = m;
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    public void elimina(T elemento) {
        Nodo n = buscaNodo(elemento);
           if(n!= null){
               if(n.equals(cabeza)){
               eliminaPrimero();
               }else{
               if(n.equals(rabo)){
                 eliminaUltimo();
                 }else{
                   (n.anterior).siguiente = n.siguiente;
                   (n.siguiente).anterior = n.anterior;
             longitud--;
             }
            }
           }
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaPrimero() {
        if(cabeza == null){
          throw new NoSuchElementException();
        }

        T r = cabeza.elemento;
          if(longitud == 1){
            cabeza = rabo = null;
            longitud--;
            return r;
          }
          else{
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
            longitud--;
            return r;
          }
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaUltimo() {
        if(rabo == null){
          throw new NoSuchElementException();
        }
        T r = rabo.elemento;
        if(longitud == 1){
        cabeza = rabo = null;
        longitud--;
        return r;
       }
        else{
        rabo = rabo.anterior;
        rabo.siguiente = null;
        longitud--;
        return r;
       }
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <tt>true</tt> si <tt>elemento</tt> está en la lista,
     *         <tt>false</tt> en otro caso.
     */
    public boolean contiene(T elemento) {
        if (elemento != null) {
          Nodo n = cabeza;
          while(n != null){
            if (n.elemento.equals(elemento))
              return true;
          n = n.siguiente;
          }
        }
        return false;
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista<T> reversa() {
        Lista<T> r = new Lista<T>();
       Nodo n = rabo;
       while (n != null){
         r.agregaFinal(n.elemento);
         n = n.anterior;
       }
       return r;
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public Lista<T> copia() {
        Lista<T> copiaLista = new Lista<T>();
        Nodo n = cabeza;
        while(n!=null){
          copiaLista.agregaFinal(n.elemento);
          n = n.siguiente;
        }
        return copiaLista;
    }

    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    public void limpia() {
        cabeza = rabo = null;
        longitud = 0;
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getPrimero() {
        if(cabeza == null){
          throw new NoSuchElementException();
        }
          return cabeza.elemento;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getUltimo() {
        if(rabo == null){
          throw new NoSuchElementException();
        }
          return rabo.elemento;
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista.
     * @throws ExcepcionIndiceInvalido si <em>i</em> es menor que cero o mayor o
     *         igual que el número de elementos en la lista.
     */
    public T get(int i) {
        if (i < 0 || i >= longitud) {
           throw new ExcepcionIndiceInvalido();
        }
        Nodo n = cabeza;
        while( i -- > 0){
          n = n.siguiente;
          }
        return n.elemento;
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(T elemento) {
        Nodo n = cabeza;
        int r = -1;
        while (n != null){
          r++;
          if (n.elemento.equals(elemento)){
            return r;
          }
         n = n.siguiente;
        }
        return -1;
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override public String toString() {
        if(cabeza == null)
          return "[]";
        Nodo n = cabeza;
        String r = "[" + cabeza.elemento.toString();
        n = cabeza.siguiente;
        while(n != null){
          r += ", " + n.elemento.toString();
          n = n.siguiente;
        }
        return r + "]";
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param objeto el objeto con el que hay que comparar.
     * @return <tt>true</tt> si la lista es igual al objeto recibido;
     *         <tt>false</tt> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (objeto == null || getClass() != objeto.getClass())
            return false;
        @SuppressWarnings("unchecked") Lista<T> lista = (Lista<T>)objeto;
        if (lista == null || longitud != lista.longitud)
             return false;
        Nodo n = cabeza;
        Lista<T>.Nodo listaDiferente = lista.cabeza;
        if(longitud != lista.longitud)
          return false;
        while(n != null){
          if(n.elemento.equals(listaDiferente.elemento)){
             n = n.siguiente;
             listaDiferente = listaDiferente.siguiente;
            }
            else{
            return false;
            }
        }
        return true;
    }

    /**
     * Regresa el nodo cabeza de la lista.
     * @return el nodo cabeza de la lista.
     */
    public Nodo getCabeza() {
        return this.cabeza;
    }

    /**
     * Regresa el nodo rabo de la lista.
     * @return el nodo rabo de la lista.
     */
    public Nodo getRabo() {
        return this.rabo;
    }

    /*metodo auxiliar para buscar nodo*/
    private Nodo buscaNodo(T elemento){
      Nodo n = cabeza;
      while(n!=null){
       if(n.elemento.equals(elemento))
          return n;
        n = n.siguiente;
       }
      return null;
     }
}
