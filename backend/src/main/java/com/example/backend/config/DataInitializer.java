package com.example.backend.config;

import com.example.backend.model.SectorCategory;
import com.example.backend.model.SectorSubcategory;
import com.example.backend.repository.SectorCategoryRepository;
import com.example.backend.repository.SectorSubcategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initSectors(SectorCategoryRepository categoryRepo,
                                  SectorSubcategoryRepository subcategoryRepo) {
        return args -> {

         
            if (categoryRepo.count() > 0) return;

            // ----- Categories -----
            SectorCategory manufacturing = categoryRepo.save(new SectorCategory("Manufacturing"));
            SectorCategory food = categoryRepo.save(new SectorCategory("Food and Beverage"));
            SectorCategory furniture = categoryRepo.save(new SectorCategory("Furniture"));
            SectorCategory machinery = categoryRepo.save(new SectorCategory("Machinery"));
            SectorCategory metalworking = categoryRepo.save(new SectorCategory("Metalworking"));
            SectorCategory plastic = categoryRepo.save(new SectorCategory("Plastic and Rubber"));
            SectorCategory printing = categoryRepo.save(new SectorCategory("Printing"));
            SectorCategory textile = categoryRepo.save(new SectorCategory("Textile and Clothing"));
            SectorCategory wood = categoryRepo.save(new SectorCategory("Wood"));
            SectorCategory other = categoryRepo.save(new SectorCategory("Other"));
            SectorCategory service = categoryRepo.save(new SectorCategory("Service"));
            SectorCategory engineering = categoryRepo.save(new SectorCategory("Engineering"));

            // ----- Subcategories -----
            // Manufacturing
            subcategoryRepo.save(new SectorSubcategory("Construction materials", manufacturing));
            subcategoryRepo.save(new SectorSubcategory("Electronics and Optics", manufacturing));

            // Food
            subcategoryRepo.save(new SectorSubcategory("Bakery & confectionery products", food));
            subcategoryRepo.save(new SectorSubcategory("Beverages", food));
            subcategoryRepo.save(new SectorSubcategory("Fish & fish products", food));
            subcategoryRepo.save(new SectorSubcategory("Meat & meat products", food));
            subcategoryRepo.save(new SectorSubcategory("Milk & dairy products", food));
            subcategoryRepo.save(new SectorSubcategory("Sweets & snack food", food));

            // Furniture
            subcategoryRepo.save(new SectorSubcategory("Bathroom/sauna", furniture));
            subcategoryRepo.save(new SectorSubcategory("Bedroom", furniture));
            subcategoryRepo.save(new SectorSubcategory("Children’s room", furniture));
            subcategoryRepo.save(new SectorSubcategory("Kitchen", furniture));
            subcategoryRepo.save(new SectorSubcategory("Living room", furniture));
            subcategoryRepo.save(new SectorSubcategory("Office", furniture));
            subcategoryRepo.save(new SectorSubcategory("Other (Furniture)", furniture));
            subcategoryRepo.save(new SectorSubcategory("Outdoor", furniture));
            subcategoryRepo.save(new SectorSubcategory("Project furniture", furniture));

            // Machinery
            subcategoryRepo.save(new SectorSubcategory("Machinery components", machinery));
            subcategoryRepo.save(new SectorSubcategory("Machinery equipment/tools", machinery));
            subcategoryRepo.save(new SectorSubcategory("Manufacture of machinery", machinery));

            // Metalworking
            subcategoryRepo.save(new SectorSubcategory("Construction of metal structures", metalworking));
            subcategoryRepo.save(new SectorSubcategory("Houses and buildings", metalworking));
            subcategoryRepo.save(new SectorSubcategory("Metal products", metalworking));
            subcategoryRepo.save(new SectorSubcategory("Metal works", metalworking));
            subcategoryRepo.save(new SectorSubcategory("CNC-machining", metalworking));
            subcategoryRepo.save(new SectorSubcategory("Forgings, Fasteners", metalworking));
            subcategoryRepo.save(new SectorSubcategory("Gas, Plasma, Laser cutting", metalworking));
            subcategoryRepo.save(new SectorSubcategory("MIG, TIG, Aluminum welding", metalworking));

            // Plastic
            subcategoryRepo.save(new SectorSubcategory("Packaging", plastic));
            subcategoryRepo.save(new SectorSubcategory("Plastic goods", plastic));
            subcategoryRepo.save(new SectorSubcategory("Plastic processing technology", plastic));
            subcategoryRepo.save(new SectorSubcategory("Blowing", plastic));
            subcategoryRepo.save(new SectorSubcategory("Moulding", plastic));
            subcategoryRepo.save(new SectorSubcategory("Plastics welding and processing", plastic));
            subcategoryRepo.save(new SectorSubcategory("Plastic profiles", plastic));

            // Printing
            subcategoryRepo.save(new SectorSubcategory("Advertising", printing));
            subcategoryRepo.save(new SectorSubcategory("Book/Periodicals printing", printing));
            subcategoryRepo.save(new SectorSubcategory("Labelling and packaging printing", printing));

            // Textile
            subcategoryRepo.save(new SectorSubcategory("Clothing", textile));
            subcategoryRepo.save(new SectorSubcategory("Textile", textile));

            // Wood
            subcategoryRepo.save(new SectorSubcategory("Other (Wood)", wood));
            subcategoryRepo.save(new SectorSubcategory("Wooden building materials", wood));
            subcategoryRepo.save(new SectorSubcategory("Wooden houses", wood));

            // Other
            subcategoryRepo.save(new SectorSubcategory("Creative industries", other));

            // Service
            subcategoryRepo.save(new SectorSubcategory("Business services", service));
            subcategoryRepo.save(new SectorSubcategory("Information Technology and Telecommunications", service));
            subcategoryRepo.save(new SectorSubcategory("Data processing, Web portals, E-marketing", service));
            subcategoryRepo.save(new SectorSubcategory("Programming, Consultancy", service));
            subcategoryRepo.save(new SectorSubcategory("Software, Hardware", service));
            subcategoryRepo.save(new SectorSubcategory("Telecommunications", service));
            subcategoryRepo.save(new SectorSubcategory("Tourism", service));
            subcategoryRepo.save(new SectorSubcategory("Translation services", service));
            subcategoryRepo.save(new SectorSubcategory("Transport and Logistics", service));

            // Engineering
            subcategoryRepo.save(new SectorSubcategory("Engineering", engineering));
        };
    }
}
