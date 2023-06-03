package com.lv.hospital.controllers.util;

import javafx.scene.text.Font;

public class LoadFonts {
    public static Font tittleFont(Double size){
        return Font.loadFont(LoadFonts.class.getResourceAsStream("/com/lv/hospital/views/fonts/HighVoltage.ttf"), size);
    }

    public static Font commonFont(Double size){
        return Font.loadFont(LoadFonts.class.getResourceAsStream("/com/lv/hospital/views/fonts/BebasNeue-Regular.otf"), size);
    }
}
