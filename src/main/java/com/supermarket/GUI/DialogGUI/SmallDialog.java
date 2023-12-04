package com.supermarket.GUI.DialogGUI;

import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;

public class SmallDialog {
    public static void showResult(String message) {
        JOptionPane.showMessageDialog(null, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
}
