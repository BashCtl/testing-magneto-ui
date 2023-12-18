package org.magneto.entities;


import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;

public class EntityProvider<T extends Entity> {
        private static final String PATH_TO_ENTITIES = System.getProperty("user.dir") + "/src/test/resources/entities/%s";

        public static <T> T getEntity(EntityType entityType) {
            try {
                return  getDateOnject(entityType.getFile(), entityType.getClassType());
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Unable to load Entity.");
            }
        }

        @SuppressWarnings("unchecked")
        private static <T> T getDateOnject(String jsonFile, Class classPattern) throws IOException {
            var pathToFile = String.format(PATH_TO_ENTITIES, jsonFile);
            return (T) new Gson().fromJson(new FileReader(pathToFile), classPattern);
        }

    }

