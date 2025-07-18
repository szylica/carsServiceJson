package org.szylica.collector;

import org.szylica.model.car.Car;
import org.szylica.model.car.CarMapper;
import org.szylica.service.records.PriceAndSpeedStats;
import org.szylica.service.records.PriceStatistics;
import org.szylica.service.records.SpeedStatistics;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class CarsStatsCollector implements Collector<Car, Map<String, Double>, PriceAndSpeedStats> {
    @Override
    public Supplier<Map<String, Double>> supplier() {
        return () -> new HashMap<>(Map.of(
                "TopSpeed", 0.0,
                "AvgSpeed", 0.0,
                "MinSpeed", 0.0,
                "TopPrice", 0.0,
                "AvgPrice", 0.0,
                "MinPrice", 0.0,
                "NumberOfElements", 0.0,
                "SumOfSpeed", 0.0,
                "SumOfPrice", 0.0
        ));
    }

    @Override
    public BiConsumer<Map<String, Double>, Car> accumulator() {
        return (map, item) -> {

            if(map.get("NumberOfElements") == 0.0){
                map.replace("TopSpeed", CarMapper.carToSpeed.apply(item).doubleValue());
                map.replace("MinSpeed", CarMapper.carToSpeed.apply(item).doubleValue());
                map.replace("TopPrice", CarMapper.carToPrice.apply(item).doubleValue());
                map.replace("MinPrice", CarMapper.carToPrice.apply(item).doubleValue());

            }

            map.merge("NumberOfElements", 0.0, (oldV, newV) -> oldV +1);
            map.merge("SumOfSpeed", 0.0, (oldV, newV) -> oldV + CarMapper.carToSpeed.apply(item));
            map.merge("SumOfPrice", 0.0, (oldV, newV) -> oldV + CarMapper.carToPrice.apply(item).doubleValue());


            if(CarMapper.carToPrice.apply(item).compareTo(BigDecimal.valueOf(map.get("TopPrice"))) > 0) {
                map.replace("TopPrice", CarMapper.carToPrice.apply(item).doubleValue());
            }
            if(CarMapper.carToPrice.apply(item).compareTo(BigDecimal.valueOf(map.get("MinPrice"))) < 0) {
                System.out.println("here");
                map.replace("MinPrice", CarMapper.carToPrice.apply(item).doubleValue());
            }
            if(CarMapper.carToSpeed.apply(item) > map.get("TopSpeed")) {
                map.replace("TopSpeed", CarMapper.carToSpeed.apply(item).doubleValue());
            }
            if(CarMapper.carToSpeed.apply(item) < map.get("MinSpeed")) {
                map.replace("MinSpeed", CarMapper.carToSpeed.apply(item).doubleValue());
            }


            map.replace("AvgSpeed", map.get("SumOfSpeed")/map.get("NumberOfElements"));

            map.replace("AvgPrice", map.get("SumOfPrice")/map.get("NumberOfElements"));


        };
    }

    @Override
    public BinaryOperator<Map<String, Double>> combiner() {
        return (map1, map2) -> {
            map2.put("TopSpeed", Double.max(map1.get("TopSpeed"), map2.get("TopSpeed")));
            map2.put("MinSpeed", Double.min(map1.get("MinSpeed"), map2.get("MinSpeed")));

            map2.put("TopPrice", Double.max(map1.get("TopPrice"), map2.get("TopPrice")));
            map2.put("MinPrice", Double.min(map1.get("MinPrice"), map2.get("MinPrice")));

            map2.put("AvgSpeed", map1.get("AvgSpeed")+map2.get("AvgSpeed")/map1.get("NumberOfElements")+map2.get("NumberOfElements"));
            map2.put("AvgPrice", map1.get("AvgPrice")+map2.get("AvgPrice")/map1.get("NumberOfElements")+map2.get("NumberOfElements"));

            return map2;
        };
    }

    @Override
    public Function<Map<String, Double>, PriceAndSpeedStats> finisher() {
        return map -> new PriceAndSpeedStats(
                new PriceStatistics(
                        BigDecimal.valueOf(map.get("TopPrice")),
                        BigDecimal.valueOf(map.get("AvgPrice")),
                        BigDecimal.valueOf(map.get("MinPrice"))),
                new SpeedStatistics(
                        map.get("TopSpeed").intValue(),
                        map.get( "AvgSpeed"),
                        map.get("MinSpeed").intValue()
                ));
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of();
    }
}
