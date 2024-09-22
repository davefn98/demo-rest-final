package com.mitocode.repository;

import com.mitocode.model.Curso;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICursoRepo extends IGenericRepo<Curso, Integer>{

}
