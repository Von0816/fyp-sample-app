package com.example.messagingservice.model;

import com.example.messagingservice.util.JsonUtility;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Map;

@Converter
public class MapConverter  implements AttributeConverter<Map<String,Object>, String> {
    @Override
    public String convertToDatabaseColumn(Map<String, Object> stringObjectMap) {

        return JsonUtility.toJson(stringObjectMap);
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String s) {
        return JsonUtility.toMap(s);
    }
}
