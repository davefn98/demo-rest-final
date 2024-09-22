package com.mitocode.service.impl;

import com.mitocode.model.Matricula;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.repository.IMatriculaRepo;
import com.mitocode.service.IMatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatriculaServiceImpl extends CRUDImpl<Matricula, Integer> implements IMatriculaService {

    private final IMatriculaRepo matriculaRepo;
    @Override
    protected IGenericRepo<Matricula, Integer> getRepo() { return matriculaRepo; }
}