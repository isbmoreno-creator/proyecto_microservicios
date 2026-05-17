package com.duoc.reportes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.duoc.reportes.model.ReportesModel;
@Repository
public interface ReportesRespository extends JpaRepository <ReportesModel, Integer> {







}
