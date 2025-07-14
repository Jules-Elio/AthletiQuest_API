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
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Stadiums")
@JsonComponent
@CompoundIndex(def = "{'name': 1, 'description': 1, 'freeAccess': 1, 'city': 1, 'postalCode': 1, 'codeInsee': 1}")
public class Stadium {

    private String id;

    private String name;

    private String description;

    private String freeAccess;

    @GeoSpatialIndexed(name = "coordinates", type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint coordinates;

    private String city;

    private String postalCode;

    private String codeInsee;

    public static class Deserializer extends JsonObjectDeserializer<Stadium> {

        @Override
        public Stadium deserializeObject(
                JsonParser jsonParser,
                DeserializationContext context,
                ObjectCodec codec,
                JsonNode tree
        ) {

            String id = nullSafeValue(tree.get("equip_numero"), String.class);
            String name = tree.get("inst_nom").asText("");
            String description = nullSafeValue(tree.get("equip_type_name"), String.class);
            String freeAccess = tree.get("equip_acc_libre").asText("");
            String longitude = nullSafeValue(tree.get("equip_x"), String.class);
            String latitude = nullSafeValue(tree.get("equip_y"), String.class);
            String city = nullSafeValue(tree.get("new_name"), String.class);
            String postalCode = nullSafeValue(tree.get("inst_cp"), String.class);
            String codeInsee = nullSafeValue(tree.get("new_code"), String.class);
            return new Stadium(
                    id,
                    name,
                    description,
                    freeAccess,
                    new GeoJsonPoint(Double.parseDouble(longitude), Double.parseDouble(latitude)),
                    city,
                    postalCode,
                    codeInsee);
        }
    }
}
