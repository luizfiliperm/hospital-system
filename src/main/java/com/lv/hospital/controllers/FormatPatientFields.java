package com.lv.hospital.controllers;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.util.converter.IntegerStringConverter;

public class FormatPatientFields {

    public static void configureAge(TextField tfAge){
        tfAge.setTextFormatter(new TextFormatter<>(new IntegerStringConverter(), null, getNumericFilter()));
    }

    private static UnaryOperator<TextFormatter.Change> getNumericFilter() {
        return change -> {
            String newText = change.getControlNewText();
            if (Pattern.matches("\\d*", newText)) {
                return change;
            }
            return null;
        };
    }

    public static void configureCellphone(TextField tfCellphone) {
        final int MAX_DIGITS = 15;

        tfCellphone.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            String newText = tfCellphone.getText() + event.getCharacter();
    
            if (!isNumericCharacter(event.getCharacter()) || newText.length() > MAX_DIGITS) {
                event.consume();
            } else {
                String formattedText = formatPhoneText(newText);
                tfCellphone.setText(formattedText);
                tfCellphone.positionCaret(formattedText.length());
                event.consume();
            }
        });
    }
    
    private static boolean isNumericCharacter(String character) {
        return character.matches("[0-9]");
    }


    private static String formatPhoneText(String text) {
        String digitsOnly = text.replaceAll("\\D", "");

        StringBuilder formattedText = new StringBuilder();
        int i = 0;
        for (char c : digitsOnly.toCharArray()) {
            if (i == 0) {
                formattedText.append("(");
            } else if (i == 2) {
                formattedText.append(") ");
            } else if (i == 7) {
                formattedText.append("-");
            }
            formattedText.append(c);
            i++;
        }

        return formattedText.toString();
    }
}
