/*
* AMRIT – Accessible Medical Records via Integrated Technology
* Integrated EHR (Electronic Health Records) Solution
*
* Copyright (C) "Piramal Swasthya Management and Research Institute"
*
* This file is part of AMRIT.
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see https://www.gnu.org/licenses/.
*/
package com.iemr.ecd.repo.call_conf_allocation;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iemr.ecd.dao.SampleSelectionConfiguration;

@Repository

public interface SampleSelectionConfigurationRepo extends CrudRepository<SampleSelectionConfiguration, Integer> {

	List<SampleSelectionConfiguration> findByPsmId(Integer psmId);

	List<SampleSelectionConfiguration> findByCycleIdAndDeletedAndPsmId(Integer cycleId, Boolean deleted, Integer psmId);

	@Query("SELECT t FROM SampleSelectionConfiguration t WHERE t.psmId=:psmId AND DAY(:fromDate) BETWEEN t.fromDay AND t.toDay")
	List<SampleSelectionConfiguration> findPreviousCycle(@Param("psmId") Integer psmId,
			@Param("fromDate") Timestamp fromDate);
	@Query("SELECT t.fromDay, t.toDay FROM SampleSelectionConfiguration t WHERE t.cycleId=:cycleId AND t.psmId=:psmId AND t.deleted = false")
	List<Object[]> getdate(@Param("cycleId") Integer cycleId, @Param("psmId") Integer psmId);

	@Query("SELECT t FROM SampleSelectionConfiguration t WHERE t.toDay=:preDate AND t.deleted = false")
	SampleSelectionConfiguration getSampleSelectionConfiguration(Integer preDate);
	
	@Query("SELECT t FROM SampleSelectionConfiguration t WHERE t.toDay<:preDate AND t.deleted = false")
	List<SampleSelectionConfiguration> getSampleSize(Integer preDate);

}
