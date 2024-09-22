package com.mitocode.service.impl;

import com.mitocode.model.Curso;
import com.mitocode.repository.ICursoRepo;
import com.mitocode.repository.IGenericRepo;
import com.mitocode.service.ICursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl extends CRUDImpl<Curso, Integer> implements ICursoService {

    private final ICursoRepo cursoRepo;

    @Override
    protected IGenericRepo<Curso, Integer> getRepo() {return cursoRepo;}

    @Override
    public Page<Curso> getPage(Pageable pageable) {
        return cursoRepo.findAll(pageable);
    }

}
