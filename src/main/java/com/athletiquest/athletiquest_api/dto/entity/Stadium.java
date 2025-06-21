package com.athletiquest.athletiquest_api.dto.entity;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Stadiums")
@JsonComponent
public class Stadium {

    private String id;
    private String name;
    private String description;
    private boolean freeAccess;

    @GeoSpatialIndexed(name = "coordinates", type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint coordinates;

    public static class Deserializer extends JsonObjectDeserializer<Stadium> {

        @Override
        public Stadium deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {

            String id = nullSafeValue(tree.get("equip_numero"), String.class);
            String name = nullSafeValue(tree.get("inst_nom"), String.class);
            String description = nullSafeValue(tree.get("equip_type_name"), String.class);
            boolean freeAccess = nullSafeValue(tree.get("equip_acc_libre"), Boolean.class);
            String longitude = nullSafeValue(tree.get("equip_y"), String.class);
            String latitude = nullSafeValue(tree.get("equip_x"), String.class);
            return new Stadium(id, name, description, freeAccess, new GeoJsonPoint(Double.parseDouble(longitude), Double.parseDouble(latitude)));
        }

    }
}