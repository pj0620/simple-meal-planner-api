package com.simplemealplanner.common.repository;

import com.simplemealplanner.common.model.IdModel;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
public class SMPCrudController<D extends IdModel, CD, DTO> {
    private Function<CD, D> createToDataMapper;
    private Function<D, DTO> dataToDTOMapper;
    private Function<DTO, D> DTOToDataMapper;
    private CrudRepository<DTO, String> baseRepository;

    public Iterable<D> listData() {
        return StreamSupport.stream(baseRepository.findAll().spliterator(), false).map(
                DTOToDataMapper).collect(Collectors.toList());
    }

    public ResponseEntity<D> getData(
            String id
    ) {
        if (id == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return baseRepository.findById(id)
                .map(DTOToDataMapper)
                .map(ingredient -> new ResponseEntity<>(ingredient, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    public D createData(
            CD createUpdateModel
    ) {
        D dataModel = createToDataMapper.apply(createUpdateModel);
        DTO createdDataModel = baseRepository.save(dataToDTOMapper.apply(dataModel));
        return DTOToDataMapper.apply(createdDataModel);
    }

    public ResponseEntity<D> update(
            String id,
            CD createUpdateModel
    ) {
        if (id == null) {
            // return a 400 Bad Request response
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        D dataModel = createToDataMapper.apply(createUpdateModel);

        HttpStatus responseCode;
        if (baseRepository.existsById(id)) {
            dataModel.setId(id);
            responseCode = HttpStatus.OK;
        } else {
            responseCode = HttpStatus.CREATED;
        }

        baseRepository.save(dataToDTOMapper.apply(dataModel));
        return new ResponseEntity<>(dataModel, responseCode);
    }

    public ResponseEntity<String> delete(
            String id
    ) {
        if (id == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        baseRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
