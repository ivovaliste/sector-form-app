package com.example.backend.config;

import com.example.backend.model.Sector;
import com.example.backend.repository.SectorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initSectors(SectorRepository sectorRepository) {





        return args -> {

            boolean shouldClean = false;

            if (shouldClean) {
                System.out.println("Cleaning sector table...");
                sectorRepository.deleteAll(); // deletes all records
            }

            if (sectorRepository.count() > 0) return;

            // ===== Top-Level Categories =====
            Sector manufacturing = sectorRepository.save(new Sector("Manufacturing", null));
            Sector other = sectorRepository.save(new Sector("Other", null));
            Sector service = sectorRepository.save(new Sector("Service", null));


            // ===== Subcategories =====
            // Manufacturing
            Sector food = sectorRepository.save(new Sector("Food and Beverage", manufacturing));
            Sector furniture = sectorRepository.save(new Sector("Furniture", manufacturing));
            Sector machinery = sectorRepository.save(new Sector("Machinery", manufacturing));
            Sector metalworking = sectorRepository.save(new Sector("Metalworking", manufacturing));
            Sector plastic = sectorRepository.save(new Sector("Plastic and Rubber", manufacturing));
            Sector printing = sectorRepository.save(new Sector("Printing", manufacturing));
            Sector textile = sectorRepository.save(new Sector("Textile and Clothing", manufacturing));
            Sector wood = sectorRepository.save(new Sector("Wood", manufacturing));
            sectorRepository.save(new Sector("Construction materials", manufacturing));
            sectorRepository.save(new Sector("Electronics and Optics", manufacturing));




            // Food and Beverage
            sectorRepository.save(new Sector("Bakery & confectionery products", food));
            sectorRepository.save(new Sector("Beverages", food));
            sectorRepository.save(new Sector("Fish & fish products", food));
            sectorRepository.save(new Sector("Meat & meat products", food));
            sectorRepository.save(new Sector("Milk & dairy products", food));
            sectorRepository.save(new Sector("Sweets & snack food", food));
            sectorRepository.save(new Sector("Other", food));


            // Furniture
            sectorRepository.save(new Sector("Bathroom/sauna", furniture));
            sectorRepository.save(new Sector("Bedroom", furniture));
            sectorRepository.save(new Sector("Children’s room", furniture));
            sectorRepository.save(new Sector("Kitchen", furniture));
            sectorRepository.save(new Sector("Living room", furniture));
            sectorRepository.save(new Sector("Office", furniture));
            sectorRepository.save(new Sector("Other (Furniture)", furniture));
            sectorRepository.save(new Sector("Outdoor", furniture));
            sectorRepository.save(new Sector("Project furniture", furniture));

            // Machinery
            sectorRepository.save(new Sector("Machinery components", machinery));
            sectorRepository.save(new Sector("Machinery equipment/tools", machinery));
            sectorRepository.save(new Sector("Manufacture of machinery", machinery));

            Sector maritime= sectorRepository.save(new Sector("Maritime",machinery));
            sectorRepository.save(new Sector("Metal structures", machinery));
            sectorRepository.save(new Sector("Other", machinery));
            sectorRepository.save(new Sector("Repair and maintenance service", machinery));

            // Maritime
            sectorRepository.save(new Sector("Aluminium and steel workboats", maritime));
            sectorRepository.save(new Sector("Boat/Yacht building", maritime));
            sectorRepository.save(new Sector("Ship repair and conversion", maritime));

            // Metalworking
            sectorRepository.save(new Sector("Construction of metal structures", metalworking));
            sectorRepository.save(new Sector("Houses and buildings", metalworking));
            sectorRepository.save(new Sector("Metal products", metalworking));
            Sector metalWorks = sectorRepository.save(new Sector("Metal works", metalworking));

            sectorRepository.save(new Sector("CNC-machining", metalWorks));
            sectorRepository.save(new Sector("Forgings, Fasteners", metalWorks));
            sectorRepository.save(new Sector("Gas, Plasma, Laser cutting", metalWorks));
            sectorRepository.save(new Sector("MIG, TIG, Aluminum welding", metalWorks));

            // Plastic and Rubber
            sectorRepository.save(new Sector("Packaging", plastic));
            sectorRepository.save(new Sector("Plastic goods", plastic));
            Sector plasticProcessingTechnology = sectorRepository.save(new Sector("Plastic processing technology", plastic));
            sectorRepository.save(new Sector("Blowing", plasticProcessingTechnology));
            sectorRepository.save(new Sector("Moulding", plasticProcessingTechnology));
            sectorRepository.save(new Sector("Plastics welding and processing", plasticProcessingTechnology));
            sectorRepository.save(new Sector("Plastic profiles", plastic));

            // Printing
            sectorRepository.save(new Sector("Advertising", printing));
            sectorRepository.save(new Sector("Book/Periodicals printing", printing));
            sectorRepository.save(new Sector("Labelling and packaging printing", printing));

            // Textile and Clothing
            sectorRepository.save(new Sector("Clothing", textile));
            sectorRepository.save(new Sector("Textile", textile));

            // Wood
            sectorRepository.save(new Sector("Other (Wood)", wood));
            sectorRepository.save(new Sector("Wooden building materials", wood));
            sectorRepository.save(new Sector("Wooden houses", wood));

            // Other
            sectorRepository.save(new Sector("Creative industries", other));
            sectorRepository.save(new Sector("Energy technology", other));
            sectorRepository.save(new Sector("Environment", other));


            // Service
            sectorRepository.save(new Sector("Business services", service));
            sectorRepository.save(new Sector("Engineering", service));
            Sector informationTechnologyAndTelecommunications =sectorRepository.save(new Sector("Information Technology and Telecommunications", service));
            sectorRepository.save(new Sector("Data processing, Web portals, E-marketing", informationTechnologyAndTelecommunications));
            sectorRepository.save(new Sector("Programming, Consultancy", informationTechnologyAndTelecommunications));
            sectorRepository.save(new Sector("Software, Hardware", informationTechnologyAndTelecommunications));
            sectorRepository.save(new Sector("Telecommunications", informationTechnologyAndTelecommunications));
            sectorRepository.save(new Sector("Tourism", service));
            sectorRepository.save(new Sector("Translation services", service));
            Sector TransportLogistics = sectorRepository.save(new Sector("Transport and Logistics", service));
            sectorRepository.save(new Sector("Air", TransportLogistics));
            sectorRepository.save(new Sector("Rail", TransportLogistics));
            sectorRepository.save(new Sector("Road", TransportLogistics));
            sectorRepository.save(new Sector("Water", TransportLogistics));
        };
    }
}
