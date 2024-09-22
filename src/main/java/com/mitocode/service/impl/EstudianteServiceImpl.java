package com.mitocode.service.impl;

import com.mitocode.model.Curso;
import com.mitocode.model.Estudiante;
import com.mitocode.repository.IEstudianteRepo;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.service.IEstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl extends CRUDImpl<Estudiante, Integer> implements IEstudianteService {

    private final IEstudianteRepo estudianteRepo;

    @Override
    protected IGenericRepo<Estudiante, Integer> getRepo() {return estudianteRepo;}
}
