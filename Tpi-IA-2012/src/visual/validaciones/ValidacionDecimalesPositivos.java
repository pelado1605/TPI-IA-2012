/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visual.validaciones;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Ruben
 */
public class ValidacionDecimalesPositivos extends InputVerifier {

    @Override
    public boolean verify(JComponent input) {
        String text = ((JTextField) input).getText();
        try {
            boolean valido = true;
            if (!text.equals("")) {
                Float value = new Float(text);
                if (value < 0) {
                    JOptionPane.showMessageDialog(null, "El valor debe ser mayor a cero.", "Número no válido", JOptionPane.ERROR_MESSAGE);
                    valido = false;
                }
            } else {
                ((JTextField) input).setText("0.2");
            }
            return valido;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Solo se aceptan Decimales.", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
