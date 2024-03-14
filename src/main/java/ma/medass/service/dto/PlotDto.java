package ma.medass.service.dto;

public class PlotDto {

    private String time;
    private Long value;
    private String type;

    public PlotDto() {
        // Empty constructor needed for Jackson.
    }

    public PlotDto(String time, Long value, String type) {
        this.time = time;
        this.value = value;
        this.type = type;
    }
 

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getValue() {
        return this.value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }


    
}
