package com.example.tamilnadureservoir.mappers;

import com.example.tamilnadureservoir.dto.ReservoirDto;
import com.example.tamilnadureservoir.model.KeyValuePair;
import com.example.tamilnadureservoir.model.Reservoir;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Service
public class ReservoirMapperImpl implements ReservoirMapper {

    @Override
    public Reservoir reservoirDto2Reservoir(ReservoirDto reservoirDto) {
        if (reservoirDto == null) {
            return null;
        }

        Reservoir reservoir = new Reservoir();

        reservoir.setId(reservoirDto.getId());
        reservoir.setName(reservoirDto.getName());
        reservoir.setRegion(reservoirDto.getRegion());
        reservoir.setCapacity(reservoirDto.getCapacity());
        reservoir.setFullHeight(reservoirDto.getFullHeight());
//        reservoir.setKeyValuePairs( keyValuePairDtoListToKeyValuePairList( reservoirDto.getKeyValuePairs() ) );

        return reservoir;
    }

    protected KeyValuePair keyValuePairDtoToKeyValuePair(ReservoirDto.KeyValuePairDto keyValuePairDto) {
        if (keyValuePairDto == null) {
            return null;
        }

        KeyValuePair keyValuePair = new KeyValuePair();

        keyValuePair.setId(keyValuePairDto.getId());
        keyValuePair.setKeyType(keyValuePairDto.getKeyType());
        keyValuePair.setValue(keyValuePairDto.getValue());

        return keyValuePair;
    }

    protected List<KeyValuePair> keyValuePairDtoListToKeyValuePairList(List<ReservoirDto.KeyValuePairDto> list) {
        if (list == null) {
            return null;
        }

        List<KeyValuePair> list1 = new ArrayList<KeyValuePair>(list.size());
        for (ReservoirDto.KeyValuePairDto keyValuePairDto : list) {
            list1.add(keyValuePairDtoToKeyValuePair(keyValuePairDto));
        }

        return list1;
    }
}
