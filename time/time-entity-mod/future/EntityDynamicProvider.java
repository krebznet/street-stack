package com.dunkware.time.entity.mod;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dunkware.time.entity.mod.entity.DBEntity;
import com.dunkware.time.entity.mod.entity.DBEntityType;
import com.dunkware.time.entity.mod.repository.DBEntityRepository;
import com.dunkware.time.entity.mod.repository.DBEntityTypeRepository;
import com.dunkware.time.entity.model.data.EntityDTO;
import com.dunkware.time.entity.model.search.EntitySearchCriteria;
import com.dunkware.time.entity.model.search.EntitySearchReq;
import com.dunkware.utils.core.data.DataType;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class EntityDynamicProvider {

	
    @Autowired
    private DBEntityRepository entityRepository;

    @Autowired
    private DBEntityTypeRepository entityTypeRepository;

    @Autowired
    private EntityManager entityManager;

    // Search entities based on criteria
    public List<EntityDTO> searchEntities(EntitySearchReq searchReq) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<DBEntity> query = cb.createQuery(DBEntity.class);
        Root<DBEntity> root = query.from(DBEntity.class);

        Predicate finalPredicate = cb.conjunction();

        for (EntitySearchCriteria criteria : searchReq.getCriteria()) {
            Predicate predicate = buildPredicate(cb, root, criteria);
            finalPredicate = cb.and(finalPredicate, predicate);
        }

        query.where(finalPredicate);
        TypedQuery<DBEntity> typedQuery = entityManager.createQuery(query);
        List<DBEntity> results = typedQuery.getResultList();
     
        return results.stream()
                .map(entity -> new EntityDTO(entity.getId(), entity.getEntityType(), entity.getProperties(), entity.getVersion()))
                .collect(Collectors.toList());
    }

    private Predicate buildPredicate(CriteriaBuilder cb, Root<DBEntity> root, EntitySearchCriteria criteria) {
        Path<Object> path = root.get("properties").get(criteria.getAttributeName());
        Object value = (String)convertValue(criteria.getValue(), criteria.getDataType());
        
        switch (criteria.getOperator()) {
            case EQUAL:
                return cb.equal(path, value);
            case NOT_EQUAL:
                return cb.notEqual(path, value);
            case GREATER_THAN:
                return cb.gt(path.as(Number.class), (Number) value);
            case LESS_THAN:
                return cb.lt(path.as(Number.class), (Number) value);
            case GREATER_THAN_OR_EQUAL:
                return cb.ge(path.as(Number.class), (Number) value);
            case LESS_THAN_OR_EQUAL:
                return cb.le(path.as(Number.class), (Number) value);
            case LIKE:
                return cb.like(path.as(String.class), "%" + value + "%");
            case IN:
                return path.in((Object[]) value.toString().split(","));
            case BETWEEN:
            	String stringValue = (String)value;
                String[] range = stringValue.split(",");
                return cb.between(path.as(Comparable.class), (Comparable) convertValue(range[0], criteria.getDataType()), (Comparable) convertValue(range[1], criteria.getDataType()));
            default:
                throw new IllegalArgumentException("Unknown operator: " + criteria.getOperator());
        }
    }

    private Object convertValue(String value, DataType dataType) {
        switch (dataType) {
            case Integer:
                return Integer.valueOf(value);
            case Long:
                return Long.valueOf(value);
            case Double:
                return Double.valueOf(value);
            case String:
                return value;
            case Boolean:
                return Boolean.valueOf(value);
            case Date:
            case Time:
            	
            case DateTime:
                // Placeholder for date/time parsing
                return value;
            default:
                throw new IllegalArgumentException("Unknown data type: " + dataType);
        }
    }

    // Bulk import entities from CSV using header row
    public void importEntitiesFromCsv(String entityTypeName, MultipartFile file, Map<String, String> columnMapping) {
        DBEntityType entityType = entityTypeRepository.findByName(entityTypeName)
                .orElseThrow(() -> new IllegalArgumentException("Entity type not found: " + entityTypeName));

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String headerLine = br.readLine(); // Read the header row
            if (headerLine == null) {
                throw new RuntimeException("CSV file is empty");
            }

            String[] headers = headerLine.split(",");
            Map<String, Integer> headerMapping = new HashMap<>();
            for (int i = 0; i < headers.length; i++) {
                headerMapping.put(headers[i].trim(), i); // Map attribute names to column indices
            }

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Map<String, String> properties = new HashMap<>();
                for (Map.Entry<String, String> entry : columnMapping.entrySet()) {
                    String attributeName = entry.getKey();
                    String columnName = entry.getValue();
                    Integer columnIndex = headerMapping.get(columnName);
                    if (columnIndex != null && columnIndex < values.length) {
                        properties.put(attributeName, values[columnIndex].trim());
                    }
                }

                DBEntity entity = new DBEntity();
                entity.setEntityType(entityType.getName());
                entity.setProperties(properties);
                entityRepository.save(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to import entities from CSV", e);
        }
    }
}