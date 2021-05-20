package org.hillel.service;

import org.hillel.persistence.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@Component
public class TicketClient  /* implements DisposableBean implements InitializingBean */ {

//    @Autowired()
//    @Qualifier("JDBCJourneyService")
//    private JourneyService journeyService;

    @Autowired
    private TransactionalJourneyService journeyService;

    @Autowired
    private TransactionalStopService stopService;

    @Autowired
    private TransactionalStopTimeService stopTimeService;

    @Autowired
    private TransactionalVehicleService vehicleService;

    @Autowired
    private TransactionalSeatService seatService;

    @Value("${system.message:journeyService init}")
    private String systemMessage;

//    @Autowired
//private Environment environment;

    public TicketClient() {
    }


    public JourneyEntity createOrUpdateJourney(final JourneyEntity journey) {
        if (Objects.isNull(journey)) throw new IllegalArgumentException("journey is null");
        return journeyService.createOrUpdateJourney(journey);
    }

    public Optional<JourneyEntity> findJourneyById(Long id, boolean withDependencies) {
        return id == null ? Optional.empty() : journeyService.findById(id, withDependencies);
    }

    public StopEntity createOrUpdateStop(final StopEntity stop) {
        if (Objects.isNull(stop)) throw new IllegalArgumentException("stop is null");
        return stopService.createOrUpdateStop(stop);
    }

    public StopTimeEntity createOrUpdateStopTime(final StopTimeEntity stopTime) {
        if (Objects.isNull(stopTime)) throw new IllegalArgumentException("stopTime is null");
        return stopTimeService.createOrUpdateStopTime(stopTime);
    }

    public VehicleEntity createOrUpdateVehicle(final VehicleEntity vehicle) {
        if (Objects.isNull(vehicle)) throw new IllegalArgumentException("vehicle is null");
        return vehicleService.createOrUpdateVehicle(vehicle);
    }

    public SeatEntity createOrUpdateSeat(final SeatEntity seat) {
        if (Objects.isNull(seat)) throw new IllegalArgumentException("seat is null");
        return seatService.createOrUpdateSeat(seat);
    }


    @PostConstruct
    public void journeyServiceInit() throws Exception {
        if (journeyService == null) {
            throw new IllegalStateException("journeySevice not init");
        } else {
            System.out.println(systemMessage);
        }
    }

    @PreDestroy
    public void destroy() throws Exception {
        System.out.println("bean destroyed");
    }


    public void removeJourney(JourneyEntity journey) {
        journeyService.remove(journey);
    }

    public void removeJourneyById(Long journeyId) {
        journeyService.removeById(journeyId);
    }


    public void removeVehicle(VehicleEntity vehicle) {
        vehicleService.remove(vehicle);
    }

    public void removeVehicleById(Long vehicleId) {
        vehicleService.removeById(vehicleId);
    }


    public void removeStop(StopEntity stop) {
        stopService.remove(stop);
    }

    public void removeStopById(Long stopId) {
        stopService.removeByID(stopId);
    }


    public void removeStopTime(StopTimeEntity stopTime) {
        stopTimeService.remove(stopTime);
    }

    public void removeStopTimeById(Long stopTimeId) {
        stopTimeService.removeById(stopTimeId);
    }


    public void removeSeat(SeatEntity seatEntity) {
        seatService.remove(seatEntity);
    }

    public void removeSeatById(Long seatId) {
        seatService.removeById(seatId);
    }



    public Collection<VehicleEntity> findAllVehiclesByName(String name) {
        return vehicleService.findAllByName(name);
    }

    public Collection<StopEntity> findAllStopsByName(String name){
        return  stopService.findAllByName(name);
    }


    public  Collection<VehicleEntity> findVehicleByIds(Long ... ids){
        return vehicleService.findByIds(ids);
    }

    public  Optional<VehicleEntity> findVehicleById(Long id){
        return vehicleService.findById(id,true);
    }

    public Collection<VehicleEntity> findAllVehicles(){
        return vehicleService.findAll();
    }

    public Collection<JourneyEntity> findAllJourneys(){
        return journeyService.findAll();

    }public Collection<SeatEntity> findAllSeats(){
        return seatService.findAll();

    }public Collection<StopEntity> findAllStops(){
        return stopService.findAll();

    }public Collection<StopTimeEntity> findAllStopsTime(){
        return stopTimeService.findAll();
    }




    public Collection<VehicleEntity> findAllAsNativeVehicles(){
        return vehicleService.findAllAsNative();
    }
    public Collection<JourneyEntity> findAllAsNativeJourneys(){
        return journeyService.findAllAsNative();

    }public Collection<SeatEntity> findAllAsNativeSeats(){
        return seatService.findAllAsNative();

    }public Collection<StopEntity> findAllAsNativeStops(){
        return stopService.findAllAsNative();

    }public Collection<StopTimeEntity> findAllAsNativeStopsTime(){
        return stopTimeService.findAllAsNative();
    }




    public Collection<VehicleEntity> findAllAsNamedVehicles(){
        return vehicleService.findAllAsNamed();
    }
    public Collection<JourneyEntity> findAllAsNamedJourneys(){
        return journeyService.findAllAsNamed();

    }public Collection<SeatEntity> findAllAsNamedSeats(){
        return seatService.findAllAsNamed();

    }public Collection<StopEntity> findAllAsNamedStops(){
        return stopService.findAllAsNamed();

    }public Collection<StopTimeEntity> findAllAsNamedStopsTime(){
        return stopTimeService.findAllAsNamed();
    }




    public Collection<VehicleEntity> findAllAsCriteriaVehicles(){
        return vehicleService.findAllAsCriteria();
    }
    public Collection<JourneyEntity> findAllAsCriteriaJourneys(){
        return journeyService.findAllAsCriteria();

    }public Collection<SeatEntity> findAllAsCriteriaSeats(){
        return seatService.findAllAsCriteria();

    }public Collection<StopEntity> findAllAsCriteriaStops(){
        return stopService.findAllAsCriteria();

    }public Collection<StopTimeEntity> findAllAsCriteriaStopsTime(){
        return stopTimeService.findAllAsCriteria();
    }



    public Collection<VehicleEntity> findAllAsStoredProcedureVehicles(){
        return vehicleService.findAllAsStoredProcedure();
    }
    public Collection<JourneyEntity> findAllAsStoredProcedureJourneys(){
        return journeyService.findAllAsStoredProcedure();

    }public Collection<SeatEntity> findAllAsStoredProcedureSeats(){
        return seatService.findAllAsStoredProcedure();

    }public Collection<StopEntity> findAllAsStoredProcedureStops(){
        return stopService.findAllAsStoredProcedure();

    }public Collection<StopTimeEntity> findAllAsStoredProcedureStopsTime(){
        return stopTimeService.findAllAsStoredProcedure();
    }

    public Collection<JourneyEntity> findAllJourneysUsingPagingSorting(String orderName,boolean ascOrder,int firstRes,int maxRes){
        return journeyService.findAllUsingPagingSorting(orderName,ascOrder,firstRes,maxRes);
    }
    public Collection<VehicleEntity> findAllVehiclesUsingPagingSorting(String orderName,boolean ascOrder,int firstRes,int maxRes){
        return vehicleService.findAllUsingPagingSorting(orderName,ascOrder,firstRes,maxRes);
    }
    public Collection<StopTimeEntity> findAllStopTimeUsingPagingSorting(String orderName,boolean ascOrder,int firstRes,int maxRes){
        return stopTimeService.findAllUsingPagingSorting(orderName,ascOrder,firstRes,maxRes);
    }
    public Collection<StopEntity> findAllStopsUsingPagingSorting(String orderName,boolean ascOrder,int firstRes,int maxRes){
        return stopService.findAllUsingPagingSorting(orderName,ascOrder,firstRes,maxRes);
    }
    public Collection<SeatEntity> findAllSeatsUsingPagingSorting(String orderName,boolean ascOrder,int firstRes,int maxRes){
        return seatService.findAllUsingPagingSorting(orderName,ascOrder,firstRes,maxRes);
    }
    public Collection<VehicleEntity> findAllVehicleWithMaxFreeSeats(){
        return vehicleService.findAllVehicleWithMaxFreeSeats();
    }
    public Collection<VehicleEntity> findAllVehicleWithMinFreeSeats(){
        return vehicleService.findAllVehicleWithMinFreeSeats();
    }
}
