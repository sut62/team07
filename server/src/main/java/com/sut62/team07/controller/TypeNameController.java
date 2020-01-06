package com.cpe.backend.controller;
import com.cpe.backend.entity.TypeName;
import com.cpe.backend.repository.TypeNameRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
public class TypeNameController {

   
    @Autowired
    private final TypeNameRep typeNameRep;

    public TypeNameController(TypeNameRep typeNameRep) {
        this.typeNameRep = typeNameRep;
    }

    @GetMapping("/typeName")
    public Collection<TypeName> TypeName() {
        return typeNameRep.findAll().stream().collect(Collectors.toList());
    }
}


