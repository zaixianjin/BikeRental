package BikeRental;

public class BikeUpdated extends AbstractEvent {

    private Long id;
    private String status;


    // @Autowired
    // BikeRepository bikeRepository;

    public BikeUpdated(){
        super();
    }
    
    // public BikeUpdated(Bike bike){
    //     System.out.println("#############BikeUpdated########");
    //     // bike.setStatus(""status""); = "occupied";
    //     // bikeRepository.save(bike);
    // }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
