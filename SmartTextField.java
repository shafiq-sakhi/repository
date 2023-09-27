/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.Set;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Firooz
 */
class SmartTextField {

    public static void bindAutoCompletion(TextField textField, ContextMenu contextMenu, Set<String> data, boolean isHaveKeyRelease) {

        if (!isHaveKeyRelease) {

            textField.setOnKeyReleased(ev -> {
                KeyCode code = ev.getCode();
                if (code == KeyCode.DOWN || code == KeyCode.UP || code == KeyCode.ENTER) {
                    return;
                }
                if (textField.getText().equals("")) {
                    contextMenu.hide();
                    contextMenu.getItems().clear();;
                } else {
                    contextMenu.hide();
                    contextMenu.getItems().clear();
                    for (String item : data) {
                        if (item.toLowerCase().contains(textField.getText().toLowerCase())) {
                            MenuItem menuItem = new MenuItem(item);

                            menuItem.setOnAction(e -> {
                                MenuItem selectedItem = (MenuItem) e.getSource();
                                textField.clear();
                                textField.appendText(selectedItem.getText());
                                contextMenu.hide();
                            });
                            contextMenu.getItems().add(menuItem);
                        }

                    }
                   
                    contextMenu.show(textField, Side.BOTTOM, 0, 0);
                }

            });

        } else {
            textField.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue observable, String oldValue, String newValue) {
                    if (newValue.equals("")) {
                        contextMenu.hide();
                        contextMenu.getItems().clear();;
                    } else {
                        contextMenu.hide();
                        contextMenu.getItems().clear();
                        for (String item : data) {
                            if (item.toLowerCase().contains(newValue.toLowerCase())) {
                                MenuItem menuItem = new MenuItem(item);

                                menuItem.setOnAction(e -> {
                                    MenuItem selectedItem = (MenuItem) e.getSource();
                                    textField.clear();
                                    textField.appendText(selectedItem.getText());
                                    contextMenu.hide();
                                });
                                contextMenu.getItems().add(menuItem);
                            }

                        }
                       
                        contextMenu.show(textField, Side.BOTTOM, 0, 0);
                    }
                }
            });
        }
    }
}
