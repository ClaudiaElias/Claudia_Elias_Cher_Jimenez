package laboratorio_2;

import javax.swing.JOptionPane;

/**
 *
 * @author clau
 */
public class Metodos_Lab2 {
    //validar que la cadena sea un número entero
    public boolean validarNumEntero(String cadena) {    
        int tam = cadena.length();
        boolean entero = true;
        int i = 0;
        while (i < tam && entero == true) {
            String caracter = cadena.substring(i, i + 1);
            if (caracter.equals("0") || caracter.equals("1") || caracter.equals("2") || caracter.equals("3") || caracter.equals("4") || caracter.equals("5") || caracter.equals("6") || caracter.equals("7") || caracter.equals("8") || caracter.equals("9")) {
                i++;
            } else {
                entero = false;
            }
        }
        return entero;
    }
    
    //valida que la cadena no haya sido registrada anteriormente
    public boolean repetido(String cad, int numestudiantes, String M[][]) { 
        int i = 0, j = 0;
        while (i < numestudiantes) {
            if (cad.equals(M[i][j])) {
                return true;
            } else {
                i++;
            }
        }
        return false;
    }
    
    //valida que el codigo tenga  digitos, sea un nùmero entero y no estè repetido
    public boolean validarCodigoEst(String cadena, int numestudiantes, String M[][]) {  //valida que el código del estudiante no tenga más de 8 dígitos y sea un número entero
        if (cadena.length() != 8) {
            JOptionPane.showMessageDialog(null, "El código debe tener 8 dígitos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (validarNumEntero(cadena) == false) {
            JOptionPane.showMessageDialog(null, "El código debe ser un número entero.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (repetido(cadena, numestudiantes, M)) {
            JOptionPane.showMessageDialog(null, "El código ya está registrado. \nIngrese datos de un nuevo estudiante.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    //Valida que no hayan quedado campos en blanco
    public boolean camposVacios(String cadena) {
        if (cadena.equals("")) {
            return true;
        }
        return false;
    }
    
    //Valida que el semestre estè en el rango 1-12
    public boolean validarSemestre(String cadena) {
        if (validarNumEntero(cadena)) {
            int semestre = Integer.parseInt(cadena);
            if (semestre < 1 || semestre > 12) {
                JOptionPane.showMessageDialog(null, "El semestre cursado debe estar entre 1 y 12.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "El semestre cursado debe ser un número entero entre 1 y 12.", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    //Calcula las notas definitivas de cada estudiante
    public double calcularNotaDefinitiva(String nota1, String nota2, String nota3, String nota4, String nota5) {
        double n1 = Double.parseDouble(nota1);
        double n2 = Double.parseDouble(nota2);
        double n3 = Double.parseDouble(nota3);
        double n4 = Double.parseDouble(nota4);
        double n5 = Double.parseDouble(nota5);
        double notadef = (n1 + n2 + n3 + n4 + n5) * 0.2;
        return notadef;
    }
    
    //Realiza un conteo de los puntos que hay en la cadena
    public boolean validarCantPuntos(String cadena, int longitudcad) {
        int i = 0, puntos = 0;
        while (i < longitudcad && puntos <= 1) {
            String aux = cadena.substring(i, i + 1);
            if (aux.equals(".")) {
                puntos++;
            }
            i++;
        }
        if (puntos > 1 || puntos == 0) {
            return false;
        }
        return true;
    }
    
    //Valida que las notas estén en el rango 0.0 - 5.0, que no tenga más de un punto y que sean valores númericos.
    public boolean validarNotas(String cadena) { //verifica que las notas estén escritas correctamente
        int tam = cadena.length();
        if (tam < 3 || tam > 4) {
            JOptionPane.showMessageDialog(null, "Las notas deben ser entre 0.0 y 5.0", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (validarCantPuntos(cadena, tam) == false) {
            JOptionPane.showMessageDialog(null, "Las notas deben ser entre 0.0 y 5.\n Ingrese valores válidos", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String pentera = cadena.substring(0, 1), decimales;
        decimales = cadena.substring(2);
        if (validarNumEntero(pentera) && validarNumEntero(decimales)) {
            if (Integer.parseInt(pentera) > 5 || (Integer.parseInt(pentera) == 5 && Integer.parseInt(decimales) != 0)) {
                JOptionPane.showMessageDialog(null, "Las notas deben ser entre 0.0 y 5.\n Ingrese valores válidos", "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Las notas deben ser entre 0.0 y 5.\n Ingrese valores válidos", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    //Compara dos cadenas caracter por caracter para indicar el orden correcto
    public int compararCaracteres(String cadena1, String cadena2) {
        String aux1 = cadena1.toUpperCase();
        String aux2 = cadena2.toUpperCase();
        int comparahasta;
        int tam1 = aux1.length();
        int tam2 = aux2.length();
        if (tam1 > tam2) {
            comparahasta = tam2;
        } else {
            comparahasta = tam1;
        }
        int i = 0;
        while (i < comparahasta) {
            char caracter1 = aux1.charAt(i);
            char caracter2 = aux2.charAt(i);
            int num1 = (int) caracter1;
            int num2 = (int) caracter2;
            if (num1 < num2) {
                return 1;
            }
            if (num2 < num1) {
                return 2;
            }
            i++;
            if (i == comparahasta) {
                if (tam1 < tam2) {
                    return 1;
                } else {
                    return 2;
                }
            }
        }
        return 2;
    }
    
    //Ordena la matriz en orden alfabetico teniendo en cuenta el apellido de los estudiantes
    public void ordenAlfabetico(String M[][], int n, int m) {
        String aux;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (compararCaracteres(M[j][1], M[j + 1][1]) == 2) {
                    for (int l = 0; l < m; l++) {
                        aux = M[j][l];
                        M[j][l] = M[j + 1][l];
                        M[j + 1][l] = aux;
                    }
                }
            }
        }
    }
    
    //determina y escribe los estudiantes que obtuvieron una nota definitiva superior de 4.0
    public void estPorSemConNotaMay(String mEstudiantes[][], int estudiantes, String nota) {
        String resultado = "";
        double notaev = Double.parseDouble(nota);
        for (int i = 0; i < estudiantes; i++) {
            int j = 9;
            double notaDef = Double.parseDouble(mEstudiantes[i][j]);
            int semestre = Integer.parseInt(mEstudiantes[i][3]);
            if (notaDef > notaev) {
                resultado += "\n \nSEMESTRE " + semestre + ": \n " + mEstudiantes[i][0] + " " + mEstudiantes[i][1] + " " + mEstudiantes[i][2] + " ";
            }
        }
        JOptionPane.showMessageDialog(null, resultado, "ESTUDIANTES CON NOTAS SUPERIORES A 4.0 POR SEMESTRE", JOptionPane.INFORMATION_MESSAGE);
    }
    
    //Determina y escribe los nombres de los estudiantes que obtuvieron una nota definitiva superior al promedio de su respectivo semestre
    public void notasSupPromSemestre(String mEstudiantes[][], int estudiantes, String nota, int semestreev) {
        String resultado = "";
        int k = semestreev;
        double notaev = Double.parseDouble(nota);
        for (int i = 0; i < estudiantes; i++) {
            int j = 9;
            int semestre = Integer.parseInt(mEstudiantes[i][3]);
            if (k == semestre) {
                double notaDef = Double.parseDouble(mEstudiantes[i][j]);
                if (notaDef > notaev) {
                    resultado += "\n \nSEMESTRE " + semestre + ": \n " + mEstudiantes[i][0] + " " + mEstudiantes[i][1] + " " + mEstudiantes[i][2] + " ";
                }
            }
        }
        if (!resultado.equals("")){
            JOptionPane.showMessageDialog(null, resultado, "ESTUDIANTES CON NOTAS SUPERIORES A "+notaev+" POR SEMESTRE", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //Calcula el promedio de las notas
    public double promedio(String mEstudiantes[][], int estudiantes, int fila, int semestreev) {
        int cont = 0, k = semestreev;
        double nota = 0;
        double prom = 0;
        for (int i = 0; i < estudiantes; i++) {
            int j = fila;
            int semestre = Integer.parseInt(mEstudiantes[i][3]);
            if (k == semestre) {
                nota = Double.parseDouble(mEstudiantes[i][j]);
                cont++;
                prom = prom + nota;
            }
        }
        if (cont != 0) {
            prom = prom / cont;
        }
        return prom;
    }
    
    //Determina y escribe los nombres de los estudiantes que obtuvieron una nota definitiva inferior al promedio global
    public void calcEstDebajoPromGlobal(String mEstudiantes[][], int estudiantes) {
        double sumNotasTot = 0;
        for (int i = 0; i < estudiantes; i++) {
            double sumNotas = 0;
            for (int j = 4; j < 9; j++) {
                sumNotas += Double.parseDouble(mEstudiantes[i][j]);

            }
            double notaDef = sumNotas / 5;
            sumNotasTot += notaDef;
        }
        double promGlobal = sumNotasTot / estudiantes;
        JOptionPane.showMessageDialog(null, "Promedio global notas definitivas:" + promGlobal);
        String estPorDebajo = "Estudiantes debajo del promedio global: \n";
        for (int i = 0; i < estudiantes; i++) {
            double sumNotas = 0;
            for (int j = 4; j < 9; j++) {
                sumNotas += Double.parseDouble(mEstudiantes[i][j]);
            }
            double notaDef = sumNotas / 5;
            if (notaDef < promGlobal) {
                estPorDebajo += mEstudiantes[i][1] + " " + mEstudiantes[i][2] + ", Nota Definitiva: " + notaDef + "\n";
            }
        }
        JOptionPane.showMessageDialog(null, estPorDebajo, "ESTUDIANTES DEBAJO DEL PROMEDIO GLOBAL", JOptionPane.WARNING_MESSAGE);
    }
    
    //valida que no haya espacios en las palabras, útil para el nombre y apellido de los estudiantes
    public boolean validarNumOEspaciosPalabra(String cadena) {
        String aux = cadena;
        int tam = aux.length();
        for (int i = 0; i < tam; i++) {
            char h = aux.charAt(i);
            if (h == '0' || h == '1' || h == '2' || h == '3' || h == '4' || h == '5' || h == '6' || h == '7' || h == '8' || h == '9' || h == ' ') {
                JOptionPane.showMessageDialog(null, "Nombres y apellidos no deben tener valores númericos o espacios", "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }

}
