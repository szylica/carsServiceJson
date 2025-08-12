package org.szylica.data.xml.impl;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.szylica.config.AppTestBeansConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppTestBeansConfig.class)
public class CarsDataXmlDeserializerTest {

//    @Autowired
//    private XmlFileDeserializer<CarsData> xmlFileDeserializer;
//
//    @Test
//    @DisplayName("when XML data is loaded correctly")
//    void test1(){
//        var path = Paths.get("src", "test","resources", "cars-test.xml");
//
//        var carsData = xmlFileDeserializer.deserializeFromFile(path);
//
//        Assertions.assertThat(carsData.getCars()).hasSize(2);
//    }
//
//    @Test
//    @DisplayName("when XML data is loaded correctly")
//    void test2(){
//        var xml = """
//                <?xml version="1.0" encoding="UTF-8"?>
//                <cars>
//                    <car>
//                        <brand>BMW</brand>
//                        <model>i5</model>
//                        <speed>170</speed>
//                        <color>WHITE</color>
//                        <price>100</price>
//                        <components>
//                            <item>ABS</item>
//                            <item>SPARE TIRE</item>
//                        </components>
//                    </car>
//                    <car>
//                        <make>AUDI</make>
//                        <model>Q8</model>
//                        <speed>260</speed>
//                        <color>BLACK</color>
//                        <price>300</price>
//                        <components>
//                            <item>AIR CONDITIONING</item>
//                            <item>BLUETOOTH</item>
//                        </components>
//                    </car>
//                </cars>
//                """;
//
//        var carsData = xmlFileDeserializer.fromXml(xml);
//
//        Assertions.assertThat(carsData.getCars()).hasSize(2);
//    }
//
//    @Test
//    @DisplayName("when XML is malformed")
//    void test3(){
//        var xml = """
//                <?xml version="1.0" encoding="UTF-8"?>
//                <cars>
//                    <car>
//                        <brand>BMW</brand>
//                        <model>i5</model>
//                        <speed>170</speed>
//                        <color>WHITE</color>
//                        <price>100</price>
//                        <components>
//                            <item>ABS</item>
//                            <item>SPARE TIRE</item>
//                        </components>
//                    </car>
//                    <car>
//                        <make>AUDI</make>
//                        <model>Q8</model>
//                        <speed>260</speed>
//                        <color>BLACK</color>
//                        <price>300</price>
//                        <components>
//                            <item>AIR CONDITIONING</item>
//                            <item>BLUETOOTH
//                        </components>
//                    </car>
//                </cars>
//                """;
//
//
//        Assertions.assertThatThrownBy(() -> xmlFileDeserializer.fromXml(xml))
//                .isInstanceOf(IllegalStateException.class)
//                .hasCauseInstanceOf(jakarta.xml.bind.UnmarshalException.class);
//    }
//
//    @Test
//    @DisplayName("when XML is malformed")
//    void test4(){
//        var path = Paths.get("src", "test","resource", "cars-test.xml");;
//
//
//        Assertions.assertThatThrownBy(() -> xmlFileDeserializer.deserializeFromFile(path))
//                .isInstanceOf(IllegalStateException.class);
//    }

}
