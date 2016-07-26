package energigas.apps.systemstrategy.energigas.entities;

/**
 * Created by Steve on 21/07/2016.
 */

public class Dispatch {

    private int id;
    private String nameCompany;
    private String addressCompany;
    private String districtCompany;
    private String telephoneCompany;
    private String departmentCompany;
    private String countryCompany;
    private String rucCompany;
    private String nameOfDispacth;
    private String addressOfDispacth;
    private String coordinatesLatLon;
    private String numberPlate;
    private String driverCar;
    private String numberPayment;
    private String date;
    private String timeStart;
    private String timeEnd;
    private String countStart;
    private String countEnd;
    private String quantityDispatch;
    private String percentageStart;
    private String percentageEnd;
    private String serialTank;
    private String numberTransport;
    private String instalationOdDispatch;


    public Dispatch() {
    }

    public Dispatch(int id, String nameCompany, String addressCompany, String districtCompany, String telephoneCompany, String departmentCompany, String countryCompany, String rucCompany, String nameOfDispacth, String addressOfDispacth, String coordinatesLatLon, String numberPlate, String driverCar, String numberPayment, String date, String timeStart, String timeEnd, String countStart, String countEnd, String quantityDispatch, String percentageStart, String percentageEnd, String serialTank, String numberTransport,String instalationOdDispatch) {
        this.id = id;
        this.nameCompany = nameCompany;
        this.addressCompany = addressCompany;
        this.districtCompany = districtCompany;
        this.telephoneCompany = telephoneCompany;
        this.departmentCompany = departmentCompany;
        this.countryCompany = countryCompany;
        this.rucCompany = rucCompany;
        this.nameOfDispacth = nameOfDispacth;
        this.addressOfDispacth = addressOfDispacth;
        this.coordinatesLatLon = coordinatesLatLon;
        this.numberPlate = numberPlate;
        this.driverCar = driverCar;
        this.numberPayment = numberPayment;
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.countStart = countStart;
        this.countEnd = countEnd;
        this.quantityDispatch = quantityDispatch;
        this.percentageStart = percentageStart;
        this.percentageEnd = percentageEnd;
        this.serialTank = serialTank;
        this.numberTransport = numberTransport;
        this.instalationOdDispatch = instalationOdDispatch;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getAddressCompany() {
        return addressCompany;
    }

    public void setAddressCompany(String addressCompany) {
        this.addressCompany = addressCompany;
    }

    public String getDistrictCompany() {
        return districtCompany;
    }

    public void setDistrictCompany(String districtCompany) {
        this.districtCompany = districtCompany;
    }

    public String getTelephoneCompany() {
        return telephoneCompany;
    }

    public void setTelephoneCompany(String telephoneCompany) {
        this.telephoneCompany = telephoneCompany;
    }

    public String getDepartmentCompany() {
        return departmentCompany;
    }

    public void setDepartmentCompany(String departmentCompany) {
        this.departmentCompany = departmentCompany;
    }

    public String getCountryCompany() {
        return countryCompany;
    }

    public void setCountryCompany(String countryCompany) {
        this.countryCompany = countryCompany;
    }

    public String getRucCompany() {
        return rucCompany;
    }

    public void setRucCompany(String rucCompany) {
        this.rucCompany = rucCompany;
    }

    public String getNameOfDispacth() {
        return nameOfDispacth;
    }

    public void setNameOfDispacth(String nameOfDispacth) {
        this.nameOfDispacth = nameOfDispacth;
    }

    public String getAddressOfDispacth() {
        return addressOfDispacth;
    }

    public void setAddressOfDispacth(String addressOfDispacth) {
        this.addressOfDispacth = addressOfDispacth;
    }

    public String getCoordinatesLatLon() {
        return coordinatesLatLon;
    }

    public void setCoordinatesLatLon(String coordinatesLatLon) {
        this.coordinatesLatLon = coordinatesLatLon;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getDriverCar() {
        return driverCar;
    }

    public void setDriverCar(String driverCar) {
        this.driverCar = driverCar;
    }

    public String getNumberPayment() {
        return numberPayment;
    }

    public void setNumberPayment(String numberPayment) {
        this.numberPayment = numberPayment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getCountStart() {
        return countStart;
    }

    public void setCountStart(String countStart) {
        this.countStart = countStart;
    }

    public String getCountEnd() {
        return countEnd;
    }

    public void setCountEnd(String countEnd) {
        this.countEnd = countEnd;
    }

    public String getQuantityDispatch() {
        return quantityDispatch;
    }

    public void setQuantityDispatch(String quantityDispatch) {
        this.quantityDispatch = quantityDispatch;
    }

    public String getPercentageStart() {
        return percentageStart;
    }

    public void setPercentageStart(String percentageStart) {
        this.percentageStart = percentageStart;
    }

    public String getPercentageEnd() {
        return percentageEnd;
    }

    public void setPercentageEnd(String percentageEnd) {
        this.percentageEnd = percentageEnd;
    }

    public String getSerialTank() {
        return serialTank;
    }

    public void setSerialTank(String serialTank) {
        this.serialTank = serialTank;
    }

    public String getNumberTransport() {
        return numberTransport;
    }

    public void setNumberTransport(String numberTransport) {
        this.numberTransport = numberTransport;
    }


    public String getInstalationOdDispatch() {
        return instalationOdDispatch;
    }

    public void setInstalationOdDispatch(String instalationOdDispatch) {
        this.instalationOdDispatch = instalationOdDispatch;
    }
}
