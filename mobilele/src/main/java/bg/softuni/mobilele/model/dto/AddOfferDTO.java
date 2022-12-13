package bg.softuni.mobilele.model.dto;

import bg.softuni.mobilele.model.enums.EngineEnum;
import bg.softuni.mobilele.model.enums.TransmissionEnum;
import bg.softuni.mobilele.model.validation.DateNowLimit;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class AddOfferDTO {
    @NotEmpty
    private String description;
    @NotNull
    @Min(0)
    @Max(900000)
    private Integer mileage;
    @NotNull(message = "Manufacturing year is required.")
    @Min(1900)
    @DateNowLimit(message = "Year must be lower than the current year.")
    private Integer year;
    @NotNull
    @Positive
    private BigDecimal price;
    @NotNull
    @Positive
    private Long modelId;
    @NotNull
    private EngineEnum engine;
    @NotNull
    private TransmissionEnum transmission;
    @NotEmpty
    private String imageUrl;


    public String getDescription() {
        return description;
    }

    public AddOfferDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public AddOfferDTO setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public AddOfferDTO setYear(Integer year) {
        this.year = year;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AddOfferDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Long getModelId() {
        return modelId;
    }

    public AddOfferDTO setModelId(Long modelId) {
        this.modelId = modelId;
        return this;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public AddOfferDTO setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AddOfferDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public AddOfferDTO setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }
}
