package com.vylgin.midiwow.settings;

import com.vylgin.midiwow.GameKeys;

import java.io.File;

/**
 * Created by vylgin on 26.09.14.
 */
public class Config {
    public static final String DIR_SEPARATOR = System.getProperty("file.separator");
    public static final String PROPERTIES_DIR_PATH = "." + DIR_SEPARATOR + GameKeys.GAME_KEYS_DIR_NAME_PROPERTIES;
    public static final File DIR_PROPERTIES = new File(PROPERTIES_DIR_PATH);
}
