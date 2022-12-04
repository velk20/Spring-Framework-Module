package bg.softuni.mobilele;

import bg.softuni.mobilele.model.entity.BrandEntity;
import bg.softuni.mobilele.repository.BrandRepository;
import bg.softuni.mobilele.util.FilesPath;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {
    private final Gson gson;
    private final BrandRepository brandRepository;

    public DataInitializer(Gson gson, BrandRepository brandRepository) {
        this.gson = gson;
        this.brandRepository = brandRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (brandRepository.count()<=0) populateBrands();

    }

    private void populateBrands() throws IOException {
        String brandsJson = Files.readString(FilesPath.BRANDS_PATH);
        BrandEntity[] brandEntities = gson.fromJson(brandsJson, BrandEntity[].class);
        this.brandRepository.saveAll(Arrays.asList(brandEntities));
    }

}
