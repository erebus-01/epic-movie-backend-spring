package com.programing.series.impl;

import com.programing.series.dto.SeriesRequest;
import com.programing.series.dto.SeriesResponse;

import java.util.List;
import java.util.UUID;

public interface SeriesImpl {

    SeriesResponse save(SeriesRequest request);
    SeriesResponse update(SeriesRequest request, UUID id);
    SeriesResponse getOne(UUID id);
    List<SeriesResponse> getAll();
    String delete(UUID id);


}
