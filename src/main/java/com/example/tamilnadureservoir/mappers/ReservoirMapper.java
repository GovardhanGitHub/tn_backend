package com.example.tamilnadureservoir.mappers;


import com.example.tamilnadureservoir.dto.ReservoirDto;
import com.example.tamilnadureservoir.model.Reservoir;
import com.example.tamilnadureservoir.model.ReservoirEveryDayUpdate;
import com.example.tamilnadureservoir.model.ReservoirEveryDayUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

//@Mapper
public interface ReservoirMapper {

    ReservoirMapper INSTANCE = Mappers.getMapper( ReservoirMapper.class );

    Reservoir reservoirDto2Reservoir(ReservoirDto reservoirDto);
//    ReservoirEveryDayUpdate ReservoirEveryDayUpdateDto2ReservoirEveryDayUpdate(ReservoirEveryDayUpdateDto reservoirEveryDayUpdate);
}
