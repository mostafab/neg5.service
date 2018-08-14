package org.neg5.core;

import org.neg5.enums.Identifiable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class EnumSerializer {

    private Set<Class<? extends Enum>> enumClazzes = new HashSet<>();

    private static final String ID_TO_OBJ_KEY = "idToObj";
    private static final String PREFIX = "g_";

    public <T extends Enum<T>> void addEnums(Class<T>... enumClazzes) {
        this.enumClazzes.addAll(Arrays.asList(enumClazzes));
    }

    @SuppressWarnings("unchecked")
    public Map<String, ?> serialize() {
        return enumClazzes.stream()
                .collect(Collectors.toMap(this::buildKey, this::buildValue));
    }

    private <T extends Enum<T>> String buildKey(Class<T> enumClazz) {
        String simpleName = enumClazz.getSimpleName();
        return PREFIX.concat(simpleName.substring(0, 1).toLowerCase().concat(simpleName.substring(1)));
    }

    private <T extends Enum<T>> Map<String, ?> buildValue(Class<T> enumClazz) {
        Map<String, Object> enumMap = new HashMap<>();
        Map<String, Object> idToEnumMap = new HashMap<>();
        Arrays.stream(enumClazz.getEnumConstants()).forEach(enumValue -> {
            if (enumValue instanceof Identifiable) {
                Identifiable identifiable = (Identifiable) enumValue;
                enumMap.put(enumValue.name(), identifiable.getId());
                idToEnumMap.put(identifiable.getId().toString(), new HashMap());
            }
        });
        enumMap.put(ID_TO_OBJ_KEY, idToEnumMap);
        return enumMap;
    }
}
