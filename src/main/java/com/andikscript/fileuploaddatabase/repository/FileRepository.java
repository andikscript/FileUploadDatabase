package com.andikscript.fileuploaddatabase.repository;

import com.andikscript.fileuploaddatabase.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {

    File findByName(String name);
}
