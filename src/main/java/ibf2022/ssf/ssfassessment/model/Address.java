package ibf2022.ssf.ssfassessment.model;

import jakarta.validation.constraints.NotNull;

public class Address {

    @NotNull(message="Please enter your name")
    private String name;
    @NotNull(message="Please enter your address")
    private String address;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public Address() {
    }
    
    public Address(@NotNull(message = "Please enter your name") String name,
            @NotNull(message = "Please enter your address") String address) {
        this.name = name;
        this.address = address;
    }

    

    
}
