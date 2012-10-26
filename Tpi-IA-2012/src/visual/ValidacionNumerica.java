/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visual;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Ruben
 */
public class ValidacionNumerica extends InputVerifier{

        @Override
        public boolean verify(JComponent input) {
            String text = ((JTextField) input).getText();
            try {
                if (!text.equals("")) {
                    Integer value = new Integer(text);
                }
                return true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Solo se aceptan numeros", "Error de formato",JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
}
